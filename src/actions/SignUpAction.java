package actions;

import javax.swing.JDialog;

public interface SignUpAction {
    
    public void execute(String name, String email, String login, String password, String repeatPassword);
    public void setSignUpDialog(JDialog dialog);
    
}
