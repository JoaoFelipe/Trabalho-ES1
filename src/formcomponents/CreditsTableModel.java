package formcomponents;

import codes.Code;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CreditsTableModel extends DefaultTableModel {

    public CreditsTableModel(List<Code> credits) {
        super(
            new Object[][] {}, 
            new String [] {
                "Código", "Valor"
            }
        );
        for (Code credit : credits) {
            this.addRow(Arrays.asList(credit.getKey(), credit.getValue()).toArray());
        }
    }
    
    
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.Integer.class
    };
    boolean[] canEdit = new boolean [] {
        false, false
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