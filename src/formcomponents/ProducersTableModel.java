package formcomponents;

import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import users.Producer;

public class ProducersTableModel extends DefaultTableModel {

    public ProducersTableModel(List<Producer> producers) {
        super(
            new Object[][] {}, 
            new String [] {
                "Nome", "Email", "Login", "Remover"
            }
        );
        for (Producer producer : producers) {
            this.addRow(Arrays.asList(producer.getName(), producer.getEmail(), producer.getLogin(), producer).toArray());
        }
    }
    
    
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
    };
    boolean[] canEdit = new boolean [] {
        false, false, false, true
    };

    @Override
    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
};