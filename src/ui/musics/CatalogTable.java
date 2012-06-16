package ui.musics;

import business.store.Store;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import ui.formcomponents.CatalogTableModel;

public class CatalogTable extends MusicListTable {

    @Override
    public DefaultTableModel getTableModel() {
        return new CatalogTableModel(this.getTable());
    }

    @Override
    public void reloadMusicList() {
        setMusicList(Store.getInstance().getCatalog());
    }

    @Override
    public List<String> getCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço");
    }
    
    @Override
    public List<String> getSortableCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço");
    }
}
