/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.awt.Component;
import javax.swing.JOptionPane;
import musics.Music;
import users.Admin;
import users.Client;
import users.User;

/**
 *
 * @author Joao
 */
public class AdminSession extends UserSession {
    
    public AdminSession(Admin admin) {
        super(admin);
    }
     
    public Admin getAdmin() {
        return (Admin) super.getUser();
    }
    
    public void signUpAdmin(String name, String email, String login, String password, String repeatPassword) {
        try {
            this.getAdmin().signUpAdmin(name, email, login, password, repeatPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void signUpProducer(String name, String email, String login, String password, String repeatPassword) {
        try {
            this.getAdmin().signUpProducer(name, email, login, password, repeatPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void generateCredits(String count, String value) {
        try {
            this.getAdmin().generateCredits(count, value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeProducer(String login) {
        try {
            if (JOptionPane.showConfirmDialog(component, "Você realmente deseja remover o produtor "+login+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.getAdmin().removeProducer(login);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
