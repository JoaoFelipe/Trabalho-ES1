package ui.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import business.store.Store;

public class ChangePasswordAction {
    
    private JDialog changePasswordDialog;
    
    public void execute(String oldPassword, String newPassword, String repeatNewPassword) {
        try {
            Store store = Store.getInstance();
            store.changeLoggedUserPassword(oldPassword, newPassword, repeatNewPassword);
            changePasswordDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(changePasswordDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setChangePasswordDialog(JDialog dialog) {
        this.changePasswordDialog = dialog;
    }
    
}
