package ui.musics;

import business.musics.Music;
import business.store.Store;

public class ChangeCommand {
    
    private Music music;
    
    public ChangeCommand(Music music) {
        this.music = music;
    }

    public void execute(String name, String genre, String album, String artist, String price) throws Exception {
        Store store = Store.getInstance();
        store.changeMusic(music, name, genre, album, artist, price);
    }

    private Music getMusic() {
        return music;
    }
    
    public String getMusicName() {
        return music.getName();
    }
    

    public String getMusicGenre() {
        return music.getGenre();
    }

    public String getMusicAlbum() {
        return music.getAlbum();
    }

    public String getMusicArtist() {
        return music.getArtist();
    }

    public String getMusicPrice() {
        return music.getPrice()+"";
    }
    
}
