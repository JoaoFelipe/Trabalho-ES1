package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.AdminSession;

public class GenerateCreditsAction {
    
    AdminSession session;
    JDialog generateCreditsDialog;
    
    public GenerateCreditsAction(AdminSession session) {
        this.session = session;
    }

    public void execute(String count, String value) {
        try {
            session.getAdmin().generateCredits(count, value);
            session.buildCreditsTable();
            generateCreditsDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(generateCreditsDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setGenerateCreditsDialog(JDialog dialog) {
        this.generateCreditsDialog = dialog;
    }
    
}
