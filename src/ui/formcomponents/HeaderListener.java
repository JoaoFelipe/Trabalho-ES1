package ui.formcomponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.TableColumnModel;

public abstract class HeaderListener extends MouseAdapter {
    
    private List<String> header;
    private TableColumnModel colModel;
    private static int current = -1;
    private static int lastX = -1;
    
    public HeaderListener(TableColumnModel colModel, List<String> header) {
        this.header = header;
        this.colModel = colModel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        if (x == lastX) {
            return;
        }
        lastX = x;
        int columnModelIndex = colModel.getColumnIndexAtX(x);
        int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();
        
        if (modelIndex < 0 || modelIndex >= header.size()) {
            return;
        }
        if (current == modelIndex) {
            return;
        }
        current = modelIndex;
        
        sort(header.get(modelIndex));
    }
    
    public abstract void sort(String column);
    
}
