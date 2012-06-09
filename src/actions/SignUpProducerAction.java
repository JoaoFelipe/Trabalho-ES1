package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.AdminSession;
import store.Store;

public class SignUpProducerAction implements SignUpAction{
    
    AdminSession session;
    JDialog signUpDialog;
    
    public SignUpProducerAction(AdminSession session) {
        this.session = session;
    }

    public void execute(String name, String email, String login, String password, String repeatPassword) {
        try {
            Store.getInstance().signUpProducer(name, email, login, password, repeatPassword);
            JOptionPane.showMessageDialog(signUpDialog, "O cadastro foi realizado com sucesso!", "Tchu Tcha Tcha Store", JOptionPane.INFORMATION_MESSAGE);
            session.buildProducersTable();
            this.signUpDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(signUpDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setSignUpDialog(JDialog dialog) {
        this.signUpDialog = dialog;
    }
    
}
