package ui.sessions;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import ui.actions.CodeGeneratorAction;
import ui.actions.SignUpAdminAction;
import ui.actions.SignUpProducerAction;
import ui.dialogs.AdminFrame;
import ui.dialogs.CodeGeneratorDialog;
import ui.dialogs.SignUpDialog;
import ui.formcomponents.CodesTableModel;
import ui.formcomponents.ProducersTableModel;
import ui.formcomponents.DialogWithCatalogInterface;
import ui.formcomponents.TableButton;
import ui.formcomponents.TableButtonListener;
import business.store.Store;

public class AdminSession extends UserSession {
    
    public AdminSession() {
        super();
    }
    
    public void blockProducer(String login) {
        try {
            if (JOptionPane.showConfirmDialog(this.getComponent(), "Você realmente deseja bloquear o produtor "+login+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Store.getInstance().blockProducer(login);
                this.buildProducersTable();
                this.getCatalog().reload();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.getComponent(), e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buildCodesTable() {
        Store store = Store.getInstance();
        JTable codesTable = ((AdminFrame) this.getComponent()).getCodesTable();
        codesTable.setModel(new CodesTableModel(store.createCodeTableIterator()));
    }
    
    public void buildProducersTable() {
        Store store = Store.getInstance();
        final JTable producersTable = ((AdminFrame) this.getComponent()).getProducersTable();     
        final DefaultTableModel model = new ProducersTableModel(store.createProducersTableIterator());
        producersTable.setModel(model);
        TableColumn column = producersTable.getColumnModel().getColumn(3);
        TableButton button = new TableButton("Bloquear");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                ((AdminSession) getInstance()).blockProducer(model.getValueAt(row, 2).toString());
            }
        });
        
        column.setCellRenderer(new TableButton("Bloquear"));
        column.setCellEditor(button);
    }
 
    @Override
    public void buildGreetings() {
        ((DialogWithCatalogInterface) this.getComponent()).getGreetings().setText("Olá " + this.getStore().getLoggedUserName());
    }
    
    public void showGenerateCreditsDialog() {
        CodeGeneratorDialog codeGeneratorDialog = new CodeGeneratorDialog(this.getComponent(), new CodeGeneratorAction(this));
        codeGeneratorDialog.setVisible(true);
    }
    
    public void showAdminSignUpDialog() {
        SignUpDialog signUpFrame = new SignUpDialog(this.getComponent(), new SignUpAdminAction());
        signUpFrame.setVisible(true);
    }
    
    public void showProducerSignUpDialog() {
        SignUpDialog signUpFrame = new SignUpDialog(this.getComponent(), new SignUpProducerAction(this));
        signUpFrame.setVisible(true);
    }
    
    @Override
    public void showDialog() {
        AdminFrame adminFrame = new AdminFrame(this);
        this.setComponent(adminFrame);
        this.buildProducersTable();
        this.buildCodesTable();
        this.getCatalog().build();
        this.buildGreetings();
        adminFrame.setVisible(true);
    }
    
}
