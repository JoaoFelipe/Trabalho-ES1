package ui.musics;

import business.musics.Music;
import business.store.Store;

public class RemoveCommand {
    
    private Music music;
    
    public RemoveCommand(Music music) {
        this.music = music;
    }

    public void execute() throws Exception {
        Store store = Store.getInstance();
        store.removeMusic(this.getMusic());
    }

    private Music getMusic() {
        return music;
    }
    
    public String getMusicName() {
        return music.getName();
    }
    
}
