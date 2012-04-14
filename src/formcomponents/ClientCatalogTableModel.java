/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formcomponents;

import credits.Credit;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import musics.Music;

/**
 *
 * @author Joao
 */
public class ClientCatalogTableModel extends DefaultTableModel {

    public ClientCatalogTableModel(List<Music> catalog) {
        super(
            new Object[][] {}, 
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Comprar"
            }
        );
        for (Music music : catalog) {
            this.addRow(Arrays.asList(music.getName(), music.getGenre(), music.getAlbum(), music.getArtist(), music.getPopularity(), music.getPrice(), "Comprar").toArray());
        }
    }
    
    
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
    };
    boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, true
    };

    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
};