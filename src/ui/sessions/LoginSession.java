package ui.sessions;

import javax.swing.JOptionPane;
import ui.dialogs.LoginFrame;
import ui.dialogs.SignUpDialog;
import ui.actions.SignUpClientAction;
import business.store.Store;

public class LoginSession extends Session {

    public static Session startUserSession() {
        setInstance(UserSession.create());
        return getInstance();
    }
    
    public Session login(String login, String password) {
        try {
            Store.getInstance().login(login, password);
            LoginSession.startUserSession().showDialog();
            this.getComponent().dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.getComponent(), e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
        
        return getInstance();
    }
      
    public void showSignUpDialog() {
        SignUpDialog signUpFrame = new SignUpDialog(this.getComponent(), new SignUpClientAction(this));
        signUpFrame.setVisible(true);
    }
    
    @Override
    public void showDialog() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
