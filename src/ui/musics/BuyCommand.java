package ui.musics;

import business.musics.Music;
import business.store.Store;

public class BuyCommand {
    
    private Music music;
    
    public BuyCommand(Music music) {
        this.music = music;
    }

    public void execute() throws Exception {
        Store store = Store.getInstance();
        store.buyMusic(this.getMusic());
    }

    private Music getMusic() {
        return music;
    }
    
    public String getMusicName() {
        return music.getName();
    }
    
}
