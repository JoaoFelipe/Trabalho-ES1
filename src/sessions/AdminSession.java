package sessions;

import actions.GenerateCreditsAction;
import actions.SignUpAdminAction;
import actions.SignUpProducerAction;
import credits.Credits;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import formcomponents.CreditsTableModel;
import formcomponents.ProducersTableModel;
import dialogs.AdminFrame;
import formcomponents.DialogWithCatalogInterface;
import dialogs.GenerateCreditsDialog;
import dialogs.SignUpDialog;
import formcomponents.TableButton;
import formcomponents.TableButtonListener;
import users.Admin;
import users.Users;

public class AdminSession extends UserSession {
    
    public AdminSession(Admin admin) {
        super(admin);
    }
     
    public Admin getAdmin() {
        return (Admin) super.getUser();
    }
    
    public void removeProducer(String login) {
        try {
            if (JOptionPane.showConfirmDialog(component, "Você realmente deseja remover o produtor "+login+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.getAdmin().removeProducer(login);
                this.buildProducersTable();
                this.reloadCatalog();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buildCreditsTable() {
        Credits credits = Credits.getInstance();
        JTable creditsTable = ((AdminFrame) component).getCreditsTable();
        creditsTable.setModel(new CreditsTableModel(credits.getCredits()));
    }
    
    public void buildProducersTable() {
        Users users = Users.getInstance();
        final JTable producersTable = ((AdminFrame) component).getProducersTable();     
        final DefaultTableModel model = new ProducersTableModel(users.getProducers());
        producersTable.setModel(model);
        TableColumn column = producersTable.getColumnModel().getColumn(3);
        TableButton button = new TableButton("Remover");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                ((AdminSession) session).removeProducer(model.getValueAt(row, 2).toString());
            }
        });
        
        column.setCellRenderer(new TableButton("Remover"));
        column.setCellEditor(button);
    }
 
    @Override
    public void buildGreetings() {
        ((DialogWithCatalogInterface) component).getGreetings().setText("Olá "+this.getUser().getName());
    }
    
    public void showGenerateCreditsDialog() {
        GenerateCreditsDialog generateCreditsDialogo = new GenerateCreditsDialog(component, new GenerateCreditsAction(this));
        generateCreditsDialogo.setVisible(true);
    }
    
    public void showAdminSignUpDialog() {
        SignUpDialog signUpFrame = new SignUpDialog(component, new SignUpAdminAction(this));
        signUpFrame.setVisible(true);
    }
    
    public void showProducerSignUpDialog() {
        SignUpDialog signUpFrame = new SignUpDialog(component, new SignUpProducerAction(this));
        signUpFrame.setVisible(true);
    }
    
    @Override
    public void showDialog() {
        AdminFrame adminFrame = new AdminFrame(this);
        this.component = adminFrame;
        this.buildProducersTable();
        this.buildCreditsTable();
        this.buildCatalogTable();
        this.buildGreetings();
        adminFrame.setVisible(true);
    }
    
}
