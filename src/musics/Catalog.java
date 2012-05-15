package musics;

public class Catalog {
    
    private static Catalog instance;
    private MusicList musicList;
    
    private Catalog() {
        musicList = new MusicList();
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
        return this.getMusicList().add(music);
    }
    
    public boolean remove(Music music) {
        return this.getMusicList().remove(music);
    }
    
    public boolean removeAll(MusicList musics) {
        return this.getMusicList().removeAll(musics);
    }
    
    public MusicList getMusicList() {
        return musicList;
    }
    
}
