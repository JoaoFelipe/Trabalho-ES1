package sessions;

import actions.ChangePasswordAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import store.Store;
import formcomponents.CatalogTableModel;
import dialogs.ChangePasswordDialog;
import formcomponents.DialogWithCatalogInterface;
import formcomponents.HeaderListener;
import musics.MusicList;
import users.Admin;
import users.Client;
import users.Producer;
import users.User;

public abstract class UserSession extends Session {
    
    public static final Map<String, String> map = new HashMap<String, String>(){
        {
            put("Nome", "name");
            put("Gênero", "genre");
            put("Álbum", "album");
            put("Artista/Banda", "artist");
            put("Popularidade", "popularity");
            put("Preço", "price");
        }
    };
    
    private User user;
    
    protected MusicList catalog;
    private String catalogFilterField = "";
    private String catalogFilterKeywords = "";
    private String catalogSortField = "";
    
    public UserSession(User user) {
        this.user = user;
        catalog = Store.getInstance().getMusics();
        catalog = catalog.sort("popularity");
    }
    
    public static Session create() {
        User user = Store.getInstance().getLoggedUser();
        return (user instanceof Admin)? new AdminSession((Admin) user) :
               (user instanceof Client)? new ClientSession((Client) user) :
               (user instanceof Producer)? new ProducerSession((Producer) user) :
               null;
    }
    
    public Session logout() {
        if (component != null) {
            component.dispose();
        }
        this.getUser().logout();
        Session.eraseInstance();
        return Session.getInstance();
    }
    
    public void reloadCatalog() {
        catalog = Store.getInstance().getMusics();
        catalog = catalog.filter(catalogFilterField, catalogFilterKeywords);
        catalog = catalog.sort(catalogSortField);
        this.buildCatalogTable();
    }
    
    public void filterCatalog(String field, String keywords) {
        catalogFilterField = map.get(field);
        catalogFilterKeywords = keywords;
        reloadCatalog();
    }
    
    public void sortCatalog(String field) {
        catalogSortField = map.get(field);
        catalog = catalog.sort(catalogSortField);
        this.buildCatalogTable();
    }
    
    public void showChangePasswordDialog() {
        ChangePasswordDialog alterarSenhaDialog = new ChangePasswordDialog(component, new ChangePasswordAction(this));
        alterarSenhaDialog.setVisible(true);
    }
    
    public void buildCatalogTable() {
        JTable catalogTable = ((DialogWithCatalogInterface) component).getCatalogTable();
        catalogTable.setModel(new CatalogTableModel(catalog));
        JTableHeader header = catalogTable.getTableHeader();
        header.addMouseListener(new HeaderListener(
            catalogTable.getColumnModel(),
            Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço")
        ) {
            @Override
            public void sort(String column) {
                ((UserSession) session).sortCatalog(column);
            }
        });
    }
    
    public User getUser() {
        return user;
    }
    
    public abstract void buildGreetings();
}
