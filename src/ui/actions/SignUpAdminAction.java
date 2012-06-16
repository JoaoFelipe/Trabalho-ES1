package ui.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import business.store.Store;

public class SignUpAdminAction implements SignUpAction{
    
    private JDialog signUpDialog;
    
    public SignUpAdminAction() {
    }

    public void execute(String name, String email, String login, String password, String repeatPassword) {
        try {
            Store.getInstance().signUpAdmin(name, email, login, password, repeatPassword);
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
