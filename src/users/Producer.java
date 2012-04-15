package users;

import java.util.ArrayList;
import java.util.List;
import musics.Catalog;
import musics.Music;

public class Producer extends User {
    
    private List<Music> publications;
    private int credits;

    public Producer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        publications = new ArrayList<Music>();
    }

    public int getCredits() {
        return credits;
    }
    
    public void addCredits(int value) {
        credits += Math.round(value * 0.8);
    }
    
    public Music publish(String name, String genre, String album, String artist, String price) throws Exception {
        Music music = new Music(this, name, genre, album, artist, price);
        Catalog.getInstance().publish(music);
        publications.add(music);
        return music;
    }
    
    public void changeMusic(Music music, String name, String genre, String album, String artist, String price) throws Exception {
        if (music.getProducer() != this) {
            throw new Exception("Você não é o produtor desta música");
        }
        music.change(name, genre, album, artist, price);
    }
    
    public void removeAllPublications() throws Exception {
        Catalog.getInstance().removeAll(publications);
        publications = new ArrayList<Music>();
    }
    
    public void removeMusic(Music music) throws Exception {
        if (music.getProducer() != this) {
            throw new Exception("Você não é o produtor desta música");
        }
        publications.remove(music);
        Catalog.getInstance().remove(music);
    }
    
    public int publicationsCount() {
        return publications.size();
    }

    public List<Music> getPublications() {
        return publications;
    }

    
}
