package business.users;

import business.musics.MusicList;

public class Producer extends User {
    
    private MusicList publications;
    private int credits;

    public Producer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        publications = new MusicList();
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
