package ui.formcomponents;

import javax.swing.table.DefaultTableModel;

public class ClientCatalogTableModel extends DefaultTableModel {

    public ClientCatalogTableModel(Iterable<Object[]> table) {
        super(
            new Object[][] {}, 
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Comprar"
            }
        );
        for (Object[] row : table) {
            this.addRow(row);
        }
    }
    
    
    private Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
    };
    private boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, true
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