package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.AdminSession;
import users.Users;

public class SignUpAdminAction implements SignUpAction{
    
    AdminSession session;
    JDialog signUpDialog;
    
    public SignUpAdminAction(AdminSession session) {
        this.session = session;
    }

    public void execute(String name, String email, String login, String password, String repeatPassword) {
        try {
            Users.getInstance().getUserFactory().signUpAdmin(name, email, login, password, repeatPassword);
            JOptionPane.showMessageDialog(signUpDialog, "O cadastro foi realizado com sucesso!", "Tchu Tcha Tcha Store", JOptionPane.INFORMATION_MESSAGE);
            this.signUpDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(signUpDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setSignUpDialog(JDialog dialog) {
        this.signUpDialog = dialog;
    }
    
}
