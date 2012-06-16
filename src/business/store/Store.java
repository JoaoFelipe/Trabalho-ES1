package business.store;

import java.util.Set;
import java.util.HashSet;
import business.codes.Code;
import business.codes.CodeIterator;
import business.musics.Music;
import business.musics.MusicList;
import business.users.Admin;
import business.users.Client;
import business.users.Producer;
import business.users.ProducerIterator;
import business.users.User;

public class Store {
    
    private static Store instance;
    
    private Set<User> users;
    private User loggedUser = null;
    
    private MusicList catalog;
    
    private HashSet<Code> codes;
    
    private Store() {
        users = new HashSet<User>();
        
        users.add(new Admin());
        codes = new HashSet<Code>();
        catalog = new MusicList();
    }
    
    public void populate() {
        try {
            Producer producer = this.signUpProducer("Producer", "producer@email.com", "producer", "1", "1");
            this.login("producer", "1");
            this.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "2");
            this.publishMusic("Innocence", "Punk Rock", "The Best Damn Thing", "Avril Lavigne", "10");
            this.publishMusic("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "21");
            this.publishMusic("Loba", "Nu-Disco, electropop latin", "She Wolf", "Shakira", "5");
            this.publishMusic("Estoy Aqui", "Latin", "Piez Descalzos", "Shakira", "1");
            this.signUpClient("Cliente", "client@email.com", "client", "1", "1");
            this.logout();
        } catch (Exception e) {
            
        }
    }
    
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
            instance.populate();
        }
        return instance;
    }
    
    public static void eraseInstance() {
        instance = null;
    }
        
    public User findByLogin(String login) {
        for (User user : this.getUsers()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
    
    public User login(String login, String password) throws Exception {
        User user = this.findByLogin(login);
        if (user == null || !user.validatePassword(password)) {
            throw new Exception("O login e/ou a senha estão incorretos");
        } 
        this.setLoggedUser(user);
        return user;
    }
   
    public void logout() {
        this.setLoggedUser(null);
    }
     
    public boolean removeUser(User user) throws Exception {
        return this.getUsers().remove(user);
    }

    public Client signUpClient(String name, String email, String login, String password, String repeatPassword) throws Exception {
        Client client = new Client(name, email, login, password, repeatPassword);
        this.getUsers().add(client);
        return client;
    }
    
    public Admin signUpAdmin(String name, String email, String login, String password, String repeatPassword) throws Exception {
        Admin admin = new Admin(name, email, login, password, repeatPassword);
        this.getUsers().add(admin);
        return admin;
    }
    
    public Producer signUpProducer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        Producer producer = new Producer(name, email, login, password, repeatPassword);
        this.getUsers().add(producer);
        return producer;
    }

    public void changeLoggedUserPassword(String oldPassword, String newPassword, String repeatNewPassword) throws Exception {
        User user = this.getLoggedUser();
        user.changePassword(oldPassword, newPassword, repeatNewPassword);
    }
    
    public boolean hasCode(String key) {
        Code code = new Code(key);
        return this.getCodes().contains(code);
    }
    
    public boolean registerCode(Code code) {
        return this.getCodes().add(code);
    }
    
    public void generateCodes(String count, String value) throws Exception {
        try {
            int c = Integer.parseInt(count);
            int v = Integer.parseInt(value);
            if (c < 0 || v < 0) {
                throw new NumberFormatException("-");
            } else {
                for (int i = 0; i < c; i++) {
                    this.registerCode(new Code(v));
                }
            }
        } catch (NumberFormatException e) {
            throw new Exception("As informações digitadas estão inconsistentes");
        }
        
    }
    
    public Code findCode(String key) {
        for (Code code : this.getCodes()) {
            if (code.getKey().equals(key) && !code.isActivated()) {
                return code;
            }
        }
        return null;
    }
    
    public Music publishMusic(String name, String genre, String album, String artist, String price) throws Exception {
        User user = this.getLoggedUser();
        if (user instanceof Producer) {
            Producer producer = (Producer) user;
            Music.validateInformations(name, genre, album, artist, price);
            Music music = new Music(producer, name, genre, album, artist, price);
            this.getCatalog().add(music);
            producer.getPublications().add(music);
            return music;
        }
        return null;
    }
    
    public void removeMusic(Music music) {
        Producer producer = music.getProducer();
        MusicList publications = producer.getPublications();
        publications.remove(music);
        this.getCatalog().remove(music);
    }
    
    public void changeMusic(Music music, String name, String genre, String album, String artist, String price) throws Exception {
        music.change(name, genre, album, artist, price);
    }
    
    public void buyMusic(Music music) throws Exception {
        User user = this.getLoggedUser();
        if (user instanceof Client) {
            Client client = (Client) user;
            client.buy(music);
        }
    }
    
    public void acquireCredits(String key) throws Exception {
        User user = this.getLoggedUser();
        if (user instanceof Client) {
            Client client = (Client) user;
            Code code = this.findCode(key);
            if (code == null) {
                throw new Exception("O código digitado não existe");
            } else {
                code.activate();
                client.addCredits(code.getValue());
            }
        }
    }
    
    public void blockProducer(String login) throws Exception {
        User user = this.findByLogin(login);
        if (user instanceof Producer) {
            Producer producer = (Producer) user;
            MusicList musics = producer.getPublications();
            this.getCatalog().removeAll(musics);
            this.removeUser(user);
        }
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }
    
    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }
    
    public Set<User> getUsers() {
        return users;
    }
    
    public HashSet<Code> getCodes() {
        return codes;
    }
    
    public MusicList getCatalog() {
        return catalog;
    }
    
    
    public String getLoggedUserName() {
        return this.getLoggedUser().getName();
    }
    
    public MusicList getLoggedUserMusics() {
        User user = this.getLoggedUser();
        if (user instanceof Client) {
            return ((Client) user).getMyMusics();
        } else if (user instanceof Producer) {
            return ((Producer) user).getPublications();
        }
        return new MusicList();
    }
    
    public Integer getLoggedUserCredits() {
        User user = this.getLoggedUser();
        if (user instanceof Client) {
            return ((Client) user).getCredits();
        } else if (user instanceof Producer) {
            return ((Producer) user).getCredits();
        }
        return 0;
    }
    
    public String getLoggedUserType() {
        User user = this.getLoggedUser();
        return (user instanceof Admin)? "Admin" :
               (user instanceof Client)? "Client" :
               (user instanceof Producer)? "Producer" :
               null;
    }
    
    
    public Iterable<Object[]> getCodeTableIterator() {
        return new CodeIterator(this.getCodes());
    }
    
    public Iterable<Object[]> getProducersTable() {
        return new ProducerIterator(this.getUsers());
    }
    
    
}
