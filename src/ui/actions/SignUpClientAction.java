package ui.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import ui.sessions.LoginSession;
import business.store.Store;

public class SignUpClientAction implements SignUpAction{
    
    private LoginSession session;
    private JDialog signUpDialog;
    
    public SignUpClientAction(LoginSession session) {
        this.session = session;
    }

    public void execute(String name, String email, String login, String password, String repeatPassword) {
        try {
            Store.getInstance().signUpClient(name, email, login, password, repeatPassword);
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
