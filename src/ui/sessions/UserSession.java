package ui.sessions;

import ui.formcomponents.DialogWithCatalogInterface;
import ui.dialogs.ChangePasswordDialog;
import ui.actions.ChangePasswordAction;
import ui.musics.CatalogTable;
import ui.musics.MusicListTable;
import business.store.Store;

public abstract class UserSession extends Session {
    
    private Store store;
    
    private MusicListTable catalog;
    
    public UserSession() {
        this.store = Store.getInstance();
        catalog = new CatalogTable();
        catalog.reloadMusicList();
        catalog.sort("Popularidade");
    }
    
    @Override
    public void setComponent(javax.swing.JFrame component) {
        super.setComponent(component);
        catalog.setComponent(component);
        catalog.setJTable(((DialogWithCatalogInterface) component).getCatalogTable());
    }
    
    public static Session create() {
        String userType = Store.getInstance().getLoggedUserType();
        return (userType.equals("Admin"))? new AdminSession() :
               (userType.equals("Client"))? new ClientSession() :
               (userType.equals("Producer"))? new ProducerSession() :
               null;
    }
    
    public Session logout() {
        if (this.getComponent() != null) {
            this.getComponent().dispose();
        }
        Store.getInstance().logout();
        Session.eraseInstance();
        return Session.getInstance();
    }
    
    public void filterCatalog(String field, String keywords) {
        catalog.filter(field, keywords);
    }
    
    public void showChangePasswordDialog() {
        ChangePasswordDialog alterarSenhaDialog = new ChangePasswordDialog(this.getComponent(), new ChangePasswordAction());
        alterarSenhaDialog.setVisible(true);
    }
    
    public abstract void buildGreetings();

    protected MusicListTable getCatalog() {
        return catalog;
    }
    
    protected void setCatalog(MusicListTable catalog) {
        this.catalog = catalog;
    }
    
    protected Store getStore() {
        return store;
    }
}
