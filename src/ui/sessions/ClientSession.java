package ui.sessions;

import javax.swing.JOptionPane;
import ui.dialogs.AcquireCreditsDialog;
import ui.dialogs.ClientFrame;
import ui.formcomponents.DialogWithCatalogInterface;
import ui.formcomponents.DialogWithCreditsInterface;
import ui.actions.AcquireCreditsAction;
import ui.musics.ClientCatalogTable;
import ui.musics.MusicListTable;
import ui.musics.MyMusicTable;
import ui.musics.BuyCommand;

public class ClientSession extends UserSession {
    
    public ClientSession() {
        super();
        myMusicTable = new MyMusicTable();
        this.setCatalog(new ClientCatalogTable());
    }
    
    private MusicListTable myMusicTable;
    
    public void buy(BuyCommand command) {
        try {
            if (JOptionPane.showConfirmDialog(this.getComponent(), "Você realmente deseja comprar a música " + command.getMusicName() + "?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                command.execute();
                JOptionPane.showMessageDialog(this.getComponent(), "A compra foi realizada com sucesso!", "Tchu Tcha Tcha Store", JOptionPane.INFORMATION_MESSAGE);
                this.buildGreetings();
                myMusicTable.reload();
                this.getCatalog().reload();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.getComponent(), e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    @Override
    public void setComponent(javax.swing.JFrame component) {
        super.setComponent(component);
        myMusicTable.setComponent(component);
        myMusicTable.setJTable(((ClientFrame) component).getMyMusicTable());
    }
    

    public void filterMyMusic(String field, String keywords) {
        myMusicTable.filter(field, keywords);
    }
    
    @Override
    public void buildGreetings() {
        ((DialogWithCatalogInterface) this.getComponent()).getGreetings().setText("Olá "+this.getStore().getLoggedUserName()+", você possui");
        ((DialogWithCreditsInterface) this.getComponent()).getCreditsLabel().setText(this.getStore().getLoggedUserCredits()+" créditos");
    }
    
    @Override
    public void showDialog() {
        ClientFrame clientFrame = new ClientFrame(this);
        this.setComponent(clientFrame);
        myMusicTable.reload();
        this.getCatalog().reload();
        this.buildGreetings();
        clientFrame.setVisible(true);
    }
 
    public void showAcquireCreditsDialog() {
        AcquireCreditsDialog acquireCreditsDialog = new AcquireCreditsDialog(this.getComponent(), new AcquireCreditsAction(this));
        acquireCreditsDialog.setVisible(true);
    }
    
}
