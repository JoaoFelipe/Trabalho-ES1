package business.users;

import business.codes.Code;
import business.musics.Music;
import business.store.Store;

public class Client extends UserTemplate {

    public Client(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
    }

    public void buy(Music music) throws Exception {
        if (this.getMusicList().contains(music)) {
            throw new Exception("Você já possui esta música");
        } else if (music.getPrice() > this.getCredits()) {
            throw new Exception("Você não possui créditos suficientes");
        } else {
            this.getMusicList().add(music);
            this.payCredits(music);
            music.increasePopularity();
        }
    }

    public void acquireCredits(String key) throws Exception {
        Code code = Store.getInstance().findCode(key);
        if (code == null) {
            throw new Exception("O código digitado não existe");
        } else {
            code.activate();
            this.addCredits(code.getValue());
        }
    }

    public void payCredits(Music music) {
        this.setCredits(this.getCredits() - music.getPrice());
        music.getProducer().addCredits(music.getPrice());
    }

    @Override
    public int addCreditsTemplate(int credits) {
        return credits;
    }
}
