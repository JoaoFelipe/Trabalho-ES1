/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import credits.Credit;
import credits.Credits;
import java.util.ArrayList;
import java.util.List;
import musics.Music;

/**
 *
 * @author casa
 */
public class Client extends User {

    private int credits;
    private List<Music> myMusics;
    
    public Client(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        myMusics = new ArrayList<Music>();
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void acquireCredits(String code) throws Exception {
        Credit credit = Credits.getInstance().activate(code);
        credits += credit.getValue();
    }
    
    public int musicCount() {
        return myMusics.size();
    }
    
    public void buy(Music music) throws Exception {
        if (myMusics.contains(music)) {
            throw new Exception("Você já possui esta música");
        }
        if (music.getPrice() > credits) {
            throw new Exception("Você não possui créditos suficientes");
        }
        myMusics.add(music);
        credits -= music.getPrice();
        music.getProducer().addCredits(music.getPrice());
        music.increasePopularity();
    }
   
    public List<Music> getMusics() {
        return myMusics;
    }
    
}
