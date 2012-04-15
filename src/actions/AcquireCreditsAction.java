package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.ClientSession;

public class AcquireCreditsAction {
    
    ClientSession session;
    JDialog acquireCreditsDialog;
    
    public AcquireCreditsAction(ClientSession session) {
        this.session = session;
    }

    public void execute(String code) {
        try {
            session.getClient().acquireCredits(code);
            session.buildGreetings();
            acquireCreditsDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(acquireCreditsDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setAcquireCreditsDialog(JDialog dialog) {
        this.acquireCreditsDialog = dialog;
    }
    
}
