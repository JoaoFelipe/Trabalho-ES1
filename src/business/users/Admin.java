package business.users;

import business.musics.MusicList;
import business.store.Store;

public class Admin extends User {

    public Admin() {
        super("Administrador", "admin@admin.com", "admin", "admin");
    }

    public Admin(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
    }

    public void blockProducer(String login) throws Exception {
        Store store = Store.getInstance();
        User user = store.findByLogin(login);
        if (user instanceof Producer) {
            Producer producer = (Producer) user;
            MusicList musics = producer.getMusicList();
            store.getCatalog().removeAll(musics);
            store.removeUser(user);
        }
    }

}
