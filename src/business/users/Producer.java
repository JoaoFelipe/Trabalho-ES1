package business.users;

import business.musics.Music;
import business.store.Store;

public class Producer extends UserTemplate {

    public Producer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
    }

    @Override
    public int addCreditsTemplate(int credits) {
        return (int) Math.round(credits * 0.8);
    }

    public void publish(String name, String genre, String album, String artist, String price) throws Exception {
        Music.validateInformations(name, genre, album, artist, price);
        Music music = new Music(this, name, genre, album, artist, price);
        Store.getInstance().getCatalog().add(music);
        this.getMusicList().add(music);
    }
    
    public void removeMusic(Music music) {
        this.getMusicList().remove(music);
        Store.getInstance().getCatalog().remove(music);
    }
}
