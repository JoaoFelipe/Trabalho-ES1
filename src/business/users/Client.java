package business.users;

import business.musics.Music;
import business.musics.MusicList;

public class Client extends User {

    private int credits;
    private MusicList myMusics;
    
    public Client(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        myMusics = new MusicList();
    }
    
    public void buy(Music music) throws Exception {
        if (this.getMyMusics().contains(music)) {
            throw new Exception("Você já possui esta música");
        } else if (music.getPrice() > this.getCredits()) {
            throw new Exception("Você não possui créditos suficientes");
        } else {
            this.getMyMusics().add(music);
            this.payCredits(music);
            music.increasePopularity();
        }
    }
   
    public MusicList getMyMusics() {
        return myMusics;
    }

    public void addCredits(int credits) {
        this.setCredits(this.getCredits() + credits);
    }
    
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
