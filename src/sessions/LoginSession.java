package sessions;

import actions.SignUpClientAction;
import javax.swing.JOptionPane;
import dialogs.LoginFrame;
import dialogs.SignUpDialog;
import users.User;
import users.Users;

public class LoginSession extends Session {

    public static Session startUserSession() {
        session = UserSession.create();
        return session;
    }
    
    public Session login(String login, String password) {
        try {
            User user = Users.getInstance().login(login, password);
            LoginSession.startUserSession().showDialog();
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
