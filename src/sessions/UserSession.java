/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import musics.Catalog;
import musics.Music;
import users.Admin;
import users.Client;
import users.User;

/**
 *
 * @author Joao
 */
public class UserSession extends Session {
    
    private User user;
    protected Component component = null;
    
    private List<Music> catalog;
    
    public UserSession(User user) {
        this.user = user;
        catalog = Music.sortMusicList(Catalog.getInstance().getCatalog(), "popularity");
    }
    
    public static Session create(User user) {
        return (user instanceof Admin)? new AdminSession((Admin) user) :
               (user instanceof Client)? new ClientSession((Client) user) :
               null;
    }
    
    public User getUser() {
        return user;
    }
    
    public void changePassword(String oldPassword, String newPassword, String repeatNewPassword) {
        try {
            this.getUser().changePassword(oldPassword, newPassword, repeatNewPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Session logout() {
        Session.eraseInstance();
        return Session.getInstance();
    }
    
    public void filterCatalog(String field, String keywords) {
        catalog = Catalog.getInstance().getCatalog();
        catalog = Music.filterMusicList(catalog, field, keywords);
    }
    
    public void sortCatalog(String field) {
        catalog = Music.sortMusicList(catalog, field);
    }
    
    
}
