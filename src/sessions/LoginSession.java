/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.awt.Component;
import javax.swing.JOptionPane;
import users.Client;
import users.User;
import users.Users;

/**
 *
 * @author Joao
 */
public class LoginSession extends Session {

    private Component component = null;
    
    public static Session startSession(User user) {
        session = UserSession.create(user);
        return session;
    }
    
    public Session login(String login, String password) {
        try {
            User user = Users.getInstance().login(login, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
        
        return session;
    }
   
    public Session signUpClient(String name, String email, String login, String password, String repeatPassword) {
        try {
            Client client = new Client(name, email, login, password, repeatPassword);
            Users.getInstance().signUp(client); 
            LoginSession.startSession(client);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
        
        return session;
    }
    
}
