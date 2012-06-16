package ui.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import ui.sessions.AdminSession;
import business.store.Store;

public class CodeGeneratorAction {
    
    private AdminSession session;
    private JDialog generateCreditsDialog;
    
    public CodeGeneratorAction(AdminSession session) {
        this.session = session;
    }

    public void execute(String count, String value) {
        try {
            Store.getInstance().generateCodes(count, value);
            session.buildCodesTable();
            generateCreditsDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(generateCreditsDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setGenerateCreditsDialog(JDialog dialog) {
        this.generateCreditsDialog = dialog;
    }
    
}
