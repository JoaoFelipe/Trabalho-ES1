/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import actions.ChangePasswordAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import musics.Catalog;
import musics.Music;
import formcomponents.CatalogTableModel;
import dialogs.ChangePasswordDialog;
import formcomponents.DialogWithCatalogInterface;
import formcomponents.HeaderListener;
import users.Admin;
import users.Client;
import users.Producer;
import users.User;

/**
 *
 * @author Joao
 */
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
    
    protected List<Music> catalog;
    private String catalogFilterField = "";
    private String catalogFilterKeywords = "";
    private String catalogSortField = "";
    
    public UserSession(User user) {
        this.user = user;
        catalog = Music.sortMusicList(Catalog.getInstance().getCatalog(), "popularity");
    }
    
    public static Session create(User user) {
        return (user instanceof Admin)? new AdminSession((Admin) user) :
               (user instanceof Client)? new ClientSession((Client) user) :
               (user instanceof Producer)? new ProducerSession((Producer) user) :
               null;
    }
    
    public User getUser() {
        return user;
    }

    public Session logout() {
        if (component != null) {
            component.dispose();
        }
        Session.eraseInstance();
        return Session.getInstance();
    }
    
    public void reloadCatalog() {
        catalog = Catalog.getInstance().getCatalog();
        catalog = Music.filterMusicList(catalog, catalogFilterField, catalogFilterKeywords);
        catalog = Music.sortMusicList(catalog, catalogSortField);
        this.buildCatalogTable();
    }
    
    public void filterCatalog(String field, String keywords) {
        catalogFilterField = map.get(field);
        catalogFilterKeywords = keywords;
        reloadCatalog();
    }
    
    public void sortCatalog(String field) {
        catalogSortField = map.get(field);
        catalog = Music.sortMusicList(catalog, catalogSortField);
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
    
    public abstract void buildGreetings();
}
