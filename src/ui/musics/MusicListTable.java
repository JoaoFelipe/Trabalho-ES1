package ui.musics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import ui.formcomponents.HeaderListener;
import business.musics.Music;
import business.musics.MusicList;

public abstract class MusicListTable {
    
    public static final Map<String, String> map = new HashMap<String, String>(){
        {
            put("Nome", "name");
            put("Gênero", "genre");
            put("Álbum", "album");
            put("Artista/Banda", "artist");
            put("Popularidade", "popularity");
            put("Preço", "price");
        }
    };
    
    
    private MusicList musicList;
    private JFrame component = null;
    private JTable table;
    private String filterField = "";
    private String filterKeywords = "";
    private String sortField = "";
    
    
    public void setMusicList(MusicList musicList) {
        this.musicList = musicList;
    }
    
    public void setJTable(JTable table) {
        this.table = table;
    }
    
    public void setComponent(JFrame component) {
        this.component = component;
    }
    
    public MusicList getMusicList() {
        return this.musicList;
    }
    
    
    public JTable getJTable() {
        return this.table;
    }
    
    public JFrame getComponent() {
        return this.component;
    }
    
    public void reload() {
        this.reloadMusicList();
        musicList = musicList.filter(filterField, filterKeywords);
        musicList = musicList.sort(sortField);
        this.build();
    }
    
    public void filter(String field, String keywords) {
        filterField = map.get(field);
        filterKeywords = keywords;
        this.reload();
    }
    
    public void sort(String field) {
        sortField = map.get(field);
        musicList = musicList.sort(sortField);
        this.build();
    }
    
    public void build() {
        if (table != null) {
            table.setModel(this.getTableModel());
            this.addButtons();
            
            table.getTableHeader().addMouseListener(this.getHeaderListener(table.getColumnModel()));
        }
    }
    
    public void addButtons() {
        
    }
    
    public MusicListTable self() {
        return this;
    }
    
    public HeaderListener getHeaderListener(TableColumnModel model) {
        return new HeaderListener(
            model,
            getSortableCollumns()
        ) {
            @Override
            public void sort(String column) {
                self().sort(column);
            }
        };
    };
    
    public abstract DefaultTableModel getTableModel(); 
    
    public abstract void reloadMusicList();
    
    public abstract List<String> getCollumns();
    public abstract List<String> getSortableCollumns();
    
    public List<Object[]> getTable() {
        List<Object[]> result = new ArrayList<Object[]>();
        List<String> collumns = this.getCollumns();
        for (Music music : musicList) {
            List<Object> row = new ArrayList<Object>();
            for (String collumn : collumns) {
                if (map.containsKey(collumn)) {
                    Object o = music.mapField(map.get(collumn));
                    if (o instanceof Integer) {
                        o = ((Integer) o) * -1;
                    }
                    row.add(o);
                } else {
                    row.add(collumn);
                }
            }
            result.add(row.toArray());
        }
        return result;
    }
    
}
