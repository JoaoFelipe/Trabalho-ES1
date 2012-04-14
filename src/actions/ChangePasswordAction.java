/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.UserSession;

/**
 *
 * @author Joao
 */
public class ChangePasswordAction {
    
    UserSession session;
    JDialog changePasswordDialog;
    
    public ChangePasswordAction(UserSession session) {
        this.session = session;
    }

    public void execute(String oldPassword, String newPassword, String repeatNewPassword) {
        try {
            session.getUser().changePassword(oldPassword, newPassword, repeatNewPassword);
            changePasswordDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(changePasswordDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setChangePasswordDialog(JDialog dialog) {
        this.changePasswordDialog = dialog;
    }
    
}
