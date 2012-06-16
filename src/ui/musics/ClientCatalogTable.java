package ui.musics;

import business.store.Store;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import ui.formcomponents.ClientCatalogTableModel;
import ui.formcomponents.TableButton;
import ui.formcomponents.TableButtonListener;
import ui.sessions.ClientSession;
import ui.sessions.Session;

public class ClientCatalogTable extends MusicListTable {

    @Override
    public DefaultTableModel getTableModel() {
        return new ClientCatalogTableModel(this.getTable());
    }

    @Override
    public void reloadMusicList() {
        setMusicList(Store.getInstance().getCatalog());
    }

    @Override
    public List<String> getCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Comprar");
    }
    
    @Override
    public void addButtons() {
        TableColumn column;
        TableButton button;
        
        column = this.getJTable().getColumnModel().getColumn(6);
        button = new TableButton("Comprar");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                ((ClientSession) Session.getInstance()).buy(new BuyCommand(getMusicList().get(row)));
            }
        });
        column.setCellRenderer(new TableButton("Comprar"));
        column.setCellEditor(button);
    }
    
    @Override
    public List<String> getSortableCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço");
    }
    
}
