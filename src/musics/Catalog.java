/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musics;

import credits.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao
 */
public class Catalog {
    
    private static Catalog instance;
    private List<Music> list;
    
    private Catalog() {
        list = new ArrayList<Music>();
    }
    
    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }
    
    public static void eraseInstance() {
        instance = null;
    }
    
    public boolean publish(Music music) {
        return list.add(music);
    }
    
    public int count() {
        return list.size();
    }    
    
    public boolean remove(Music music) {
        return list.remove(music);
    }
    
    public List<Music> getCatalog() {
        return list;
    }
    
}
