/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import musics.Music;
import users.Client;
import users.Producer;
import users.User;

/**
 *
 * @author Joao
 */
public class ProducerSession extends UserSession {
    
    private List<Music> publications;
    
    public ProducerSession(Producer producer) {
        super(producer);
        publications = Music.sortMusicList(producer.getPublications(), "name");
    }
     
    public Producer getProducer() {
        return (Producer) super.getUser();
    }
    
    public void publish(String name, String genre, String album, String artist, String price) {
        try {
            this.getProducer().publish(name, genre, album, artist, price);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void changeMusic(Music music, String name, String genre, String album, String artist, String price) {
        try {
            this.getProducer().changeMusic(music, name, genre, album, artist, price);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeMusic(Music music) {
        try {
            if (JOptionPane.showConfirmDialog(component, "Você realmente deseja remover a música "+music.getName()+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.getProducer().removeMusic(music);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void filterPublications(String field, String keywords) {
        publications = getProducer().getPublications();
        publications = Music.filterMusicList(publications, field, keywords);
    }
    
    public void sortPublications(String field) {
        publications = Music.sortMusicList(publications, field);
    }
    
}
