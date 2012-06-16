package ui.formcomponents;

import javax.swing.table.DefaultTableModel;

public class CodesTableModel extends DefaultTableModel {

    public CodesTableModel(Iterable<Object[]> table) {
        super(
            new Object[][] {}, 
            new String [] {
                "Código", "Valor"
            }
        );
        for (Object[] row : table) {
            this.addRow(row);
        }
    }
    
    
    private Class[] types = new Class [] {
        java.lang.String.class, java.lang.Integer.class
    };
    private boolean[] canEdit = new boolean [] {
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