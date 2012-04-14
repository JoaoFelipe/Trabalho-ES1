/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.ArrayList;
import java.util.List;
import musics.Catalog;
import musics.Music;

/**
 *
 * @author casa
 */
public class Producer extends User {
    
    private List<Music> publications;
    
    public Producer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        publications = new ArrayList<Music>();
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
        for (Music music : publications) {
            this.removeMusic(music);
        }
    }
    
    public void removeMusic(Music music) throws Exception {
        if (music.getProducer() != this) {
            throw new Exception("Você não é o produtor desta música");
        }
        Catalog.getInstance().remove(music);
    }
    
    public int publicationsCount() {
        return publications.size();
    }

    
}
