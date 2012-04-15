package formcomponents;

import credits.Credit;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CreditsTableModel extends DefaultTableModel {

    public CreditsTableModel(List<Credit> credits) {
        super(
            new Object[][] {}, 
            new String [] {
                "Código", "Valor"
            }
        );
        for (Credit credit : credits) {
            this.addRow(Arrays.asList(credit.getCode(), credit.getValue()).toArray());
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