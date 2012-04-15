package formcomponents;

import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import musics.Music;

public class PublicationsTableModel extends DefaultTableModel {

    public PublicationsTableModel(List<Music> catalog) {
        super(
            new Object[][] {}, 
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Alterar", "Remover"
            }
        );
        for (Music music : catalog) {
            this.addRow(Arrays.asList(music.getName(), music.getGenre(), music.getAlbum(), music.getArtist(), music.getPopularity(), music.getPrice(), "Alterar", "Remover").toArray());
        }
    }
    
    
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
    };
    boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, true, true
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