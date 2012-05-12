package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.LoginSession;
import sessions.Session;
import sessions.UserSession;
import users.Client;
import users.Users;

public class SignUpClientAction implements SignUpAction{
    
    LoginSession session;
    JDialog signUpDialog;
    
    public SignUpClientAction(LoginSession session) {
        this.session = session;
    }

    public void execute(String name, String email, String login, String password, String repeatPassword) {
        try {
            Users.getInstance().signUpClient(name, email, login, password, repeatPassword);
            JOptionPane.showMessageDialog(signUpDialog, "O cadastro foi realizado com sucesso!", "Tchu Tcha Tcha Store", JOptionPane.INFORMATION_MESSAGE);
            session.login(login, password);
            session.getComponent().dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(signUpDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setSignUpDialog(JDialog dialog) {
        this.signUpDialog = dialog;
    }
    
}
