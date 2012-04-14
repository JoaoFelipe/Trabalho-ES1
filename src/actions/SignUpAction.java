/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.swing.JDialog;

/**
 *
 * @author Joao
 */
public interface SignUpAction {
    
    public void execute(String name, String email, String login, String password, String repeatPassword);
    public void setSignUpDialog(JDialog dialog);
    
}
