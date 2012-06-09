package users;

import codes.Code;
import musics.Music;
import musics.MusicList;
import store.Store;

public class Client extends User {

    private int credits;
    private MusicList myMusics;
    
    public Client(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        myMusics = new MusicList();
    }
    
    public void acquireCredits(String key) throws Exception {
        int value = Store.getInstance().activateCode(key);
        this.addCredits(value);
    }
    
    public void buy(Music music) throws Exception {
        if (this.getMyMusics().contains(music)) {
            throw new Exception("Você já possui esta música");
        }
        if (music.getPrice() > this.getCredits()) {
            throw new Exception("Você não possui créditos suficientes");
        }
        this.getMyMusics().add(music);
        this.payCredits(music);
        music.increasePopularity();
    }
   
    public MusicList getMyMusics() {
        return myMusics;
    }

    public void addCredits(int credits) {
        this.setCredits(this.getCredits() + credits);
    }
    
    // alterar uml
    public void payCredits(Music music) {
        this.setCredits(this.getCredits() - music.getPrice());
        music.getProducer().addCredits(music.getPrice());
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
}
