package formcomponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.TableColumnModel;

public abstract class HeaderListener extends MouseAdapter {
    
    List<String> header;
    TableColumnModel colModel;
    static int current = -1;
    static int lastX = -1;
    
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
//        JTableHeader h = (JTableHeader) e.getSource();
//        int nColumn = h.columnAtPoint(e.getPoint());

//        if (nColumn >= 0 && nColumn < header.size()) {
//            sort(header.get(nColumn));
//        }
    }
    
    public abstract void sort(String column);
    
}
