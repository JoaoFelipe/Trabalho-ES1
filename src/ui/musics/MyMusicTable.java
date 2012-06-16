package ui.musics;

import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import ui.formcomponents.MyMusicTableModel;
import business.store.Store;

public class MyMusicTable extends MusicListTable {

    @Override
    public DefaultTableModel getTableModel() {
        return new MyMusicTableModel(this.getTable());
    }

    @Override
    public void reloadMusicList() {
        setMusicList(Store.getInstance().getLoggedUserMusics());
    }

    @Override
    public List<String> getCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade");
    }
    
    @Override
    public List<String> getSortableCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade");
    }
    
}
