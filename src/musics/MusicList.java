package musics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MusicList extends ArrayList<Music> {

    public MusicList(Collection<Music> list) {
        super(list);
    }

    public MusicList() {
        super();
    }
    
    public MusicList filter(String field, String keywords) {
        MusicList result = new MusicList();
        for (Music music : this) {
            if (music.match(field, keywords)) {
                result.add(music);
            }
        }
        return result;
    }
    
    public MusicList sort(final String field) {
        MusicList result = new MusicList(this);
        Collections.sort(result, comparatorForField(field));
        return result;
    }
    
    public Comparator<Music> comparatorForField(final String field) {
        return new Comparator<Music>(){

            public int compare(Music o1, Music o2) {
                return o1.mapField(field).compareTo(o2.mapField(field));
            }
        };
    }
    
}
