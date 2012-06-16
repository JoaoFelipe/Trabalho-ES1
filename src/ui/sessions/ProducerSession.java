package ui.sessions;

import javax.swing.JOptionPane;
import ui.actions.PublishAction;
import ui.dialogs.ProducerFrame;
import ui.dialogs.PublishDialog;
import ui.formcomponents.DialogWithCatalogInterface;
import ui.formcomponents.DialogWithCreditsInterface;
import ui.musics.MusicListTable;
import ui.musics.PublicationTable;
import ui.musics.RemoveCommand;

public class ProducerSession extends UserSession {
    
    private MusicListTable publications;
    
    public ProducerSession() {
        super();
        publications = new PublicationTable();
    }
    
    public void removeMusic(RemoveCommand command) {
        try {
            if (JOptionPane.showConfirmDialog(this.getComponent(), "Você realmente deseja remover a música "+command.getMusicName()+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                command.execute();
                this.rebuildMusics();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.getComponent(), e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void setComponent(javax.swing.JFrame component) {
        super.setComponent(component);
        publications.setComponent(component);
        publications.setJTable(((ProducerFrame) this.getComponent()).getPublicationsTable());
    }
    
    public void filterPublications(String field, String keywords) {
        publications.filter(field, keywords);
    }
      
    
    @Override
    public void buildGreetings() {
        ((DialogWithCatalogInterface) this.getComponent()).getGreetings().setText("Olá "+this.getStore().getLoggedUserName()+", você já recebeu");
        ((DialogWithCreditsInterface) this.getComponent()).getCreditsLabel().setText(this.getStore().getLoggedUserCredits()+" créditos");
    }
    
    public void rebuildMusics() {
        publications.reload();
        this.getCatalog().reload();
    }
    
    public void showPublishDialog() {
        PublishDialog publishDialog = new PublishDialog(this.getComponent(), new PublishAction(this));
        publishDialog.setVisible(true);
    }
    
    @Override
    public void showDialog() {
        ProducerFrame producerFrame = new ProducerFrame(this);
        this.setComponent(producerFrame);
        this.rebuildMusics();
        this.buildGreetings();
        producerFrame.setVisible(true);
    }
    
}
