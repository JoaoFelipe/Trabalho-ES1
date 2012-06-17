package business.users;

import business.musics.MusicList;

public abstract class UserTemplate extends User {

    private int credits;
    private MusicList musicList;
    
    public UserTemplate(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
        musicList = new MusicList();
    }
   
    public MusicList getMusicList() {
        return musicList;
    }

    public void addCredits(int credits) {
        int oldValue = this.getCredits();
        int sum = addCreditsTemplate(credits);
        int newValue = oldValue + sum;
        this.setCredits(newValue);
    }
    
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public abstract int addCreditsTemplate(int credits);
    
}
