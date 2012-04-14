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
import users.User;

/**
 *
 * @author Joao
 */
public class ClientSession extends UserSession {
    
    private List<Music> myMusic;
    
    public ClientSession(Client client) {
        super(client);
        myMusic = Music.sortMusicList(client.getMusics(), "name");
    }
     
    public Client getClient() {
        return (Client) super.getUser();
    }
    
    public void buy(Music music) {
        try {
            if (JOptionPane.showConfirmDialog(component, "Você realmente deseja comprar a música "+music.getName()+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.getClient().buy(music);
                JOptionPane.showMessageDialog(component, "A música foi comprada com sucesso!", "Tchu Tcha Tcha Store", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void acquireCredits(String code) {
        try {
            this.getClient().acquireCredits(code);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void filterMyMusics(String field, String keywords) {
        myMusic = getClient().getMusics();
        myMusic = Music.filterMusicList(myMusic, field, keywords);
    }
    
    public void sortMyMusics(String field) {
        myMusic = Music.sortMusicList(myMusic, field);
    }
    
}
