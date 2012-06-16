package ui.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import ui.sessions.ClientSession;
import business.store.Store;

public class AcquireCreditsAction {
    
    private ClientSession session;
    private JDialog acquireCreditsDialog;
    
    public AcquireCreditsAction(ClientSession session) {
        this.session = session;
    }

    public void execute(String code) {
        try {
            Store store = Store.getInstance();
            store.acquireCredits(code);
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
