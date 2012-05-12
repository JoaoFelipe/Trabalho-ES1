package users;

import credits.Credit;
import credits.Credits;
import musics.Music;
import musics.MusicList;

public class Client extends User {

    private int credits;
    private MusicList myMusics;
    
    public Client(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        myMusics = new MusicList();
    }
    
    public void acquireCredits(String code) throws Exception {
        Credit credit = Credits.getInstance().activate(code);
        this.addCredits(credit.getValue());
    }
    
    public void buy(Music music) throws Exception {
        if (this.getMusics().contains(music)) {
            throw new Exception("Você já possui esta música");
        }
        if (music.getPrice() > this.getCredits()) {
            throw new Exception("Você não possui créditos suficientes");
        }
        this.getMusics().add(music);
        this.payCredits(music.getPrice());
        music.getProducer().addCredits(music.getPrice());
        music.increasePopularity();
    }
   
    public MusicList getMusics() {
        return myMusics;
    }

    public void addCredits(int credits) {
        this.setCredits(this.getCredits() + credits);
    }
    
    public void payCredits(int credits) {
        this.setCredits(this.getCredits() - credits);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
}
