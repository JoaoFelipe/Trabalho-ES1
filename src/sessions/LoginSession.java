/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import actions.SignUpClientAction;
import java.awt.Component;
import javax.swing.JOptionPane;
import dialogs.LoginFrame;
import dialogs.SignUpDialog;
import users.Client;
import users.User;
import users.Users;

/**
 *
 * @author Joao
 */
public class LoginSession extends Session {

    public static Session startSession(User user) {
        session = UserSession.create(user);
        return session;
    }
    
    public Session login(String login, String password) {
        try {
            User user = Users.getInstance().login(login, password);
            ((UserSession) session).showDialog();
            component.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
        
        return session;
    }
      
    public void showSignUpDialog() {
        SignUpDialog signUpFrame = new SignUpDialog(component, new SignUpClientAction(this));
        signUpFrame.setVisible(true);
    }
    
    @Override
    public void showDialog() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
