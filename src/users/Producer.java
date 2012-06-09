package users;

import store.Store;
import musics.Music;
import musics.MusicList;

public class Producer extends User {
    
    private MusicList publications;
    private int credits;

    public Producer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        publications = new MusicList();
    }

    public Music publish(String name, String genre, String album, String artist, String price) throws Exception {
        Music music = new Music(this, name, genre, album, artist, price);
        Store.getInstance().publishMusic(music);
        this.getPublications().add(music);
        return music;
    }
    
    public void changeMusic(Music music, String name, String genre, String album, String artist, String price) throws Exception {
        music.change(name, genre, album, artist, price);
    }
    
    public void removeAllPublications() throws Exception {
        Store.getInstance().removeAllMusics(this.getPublications());
        this.getPublications().clear();
    }
    
    public void removeMusic(Music music) throws Exception {
        this.getPublications().remove(music);
        Store.getInstance().removeMusic(music);
    }
    
    public void addCredits(int value) {
        this.setCredits(this.getCredits() + (int)Math.round(value * 0.8));
    }
    
    public MusicList getPublications() {
        return publications;
    }
    
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
}
