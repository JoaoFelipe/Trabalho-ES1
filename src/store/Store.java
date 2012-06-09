package store;

import codes.Code;
import java.util.HashSet;
import java.util.Set;
import musics.Music;
import musics.MusicList;
import users.Admin;
import users.Client;
import users.Producer;
import users.User;

public class Store {
    
    private static Store instance;
    
    private Set<User> users;
    private User loggedUser = null;
    
    private MusicList musics;
    
    private HashSet<Code> codes;
    
    private Store() {
        users = new HashSet<User>();
        
        users.add(new Admin());
//        try {
//            Producer producer = new Producer("Producer", "producer@email.com", "producer", "1", "1");
//            list.add(new Producer("Producer", "producer@email.com", "producer", "1", "1"));
//            producer.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "2");
//            producer.publishMusic("Innocence", "Punk Rock", "The Best Damn Thing", "Avril Lavigne", "10");
//            producer.publishMusic("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "21");
//            producer.publishMusic("Loba", "Nu-Disco, electropop latin", "She Wolf", "Shakira", "5");
//            producer.publishMusic("Estoy Aqui", "Latin", "Piez Descalzos", "Shakira", "1");
//            list.add(new Client("Cliente", "client@email.com", "client", "1", "1"));
//        } catch (Exception e) {
//            
//        }
        codes = new HashSet<Code>();
        musics = new MusicList();
    }
    
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
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

    
    public boolean hasCode(String key) {
        Code code = new Code(key);
        return this.getCodes().contains(code);
    }
    
    public boolean registerCode(Code code) {
        return this.getCodes().add(code);
    }
    
    public void generateCodes(int count, int value) {
        for (int i = 0; i < count; i++) {
            this.registerCode(new Code(value));
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
    
    public int activateCode(String key) throws Exception {
        Code code = this.findCode(key);
        if (code == null) {
            throw new Exception("O código digitado não existe");
        }
        code.activate();
        return code.getValue();
    }
    
    public boolean publishMusic(Music music) {
        return this.getMusics().add(music);
    }
    
    public boolean removeMusic(Music music) {
        return this.getMusics().remove(music);
    }
    
    public boolean removeAllMusics(MusicList musics) {
        return this.getMusics().removeAll(musics);
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }
    
    public Set<User> getUsers() {
        return users;
    }
    
    public void setLoggedUser(User user) {
        this.loggedUser = user;
    }
    
    public HashSet<Code> getCodes() {
        return codes;
    }
    
    public MusicList getMusics() {
        return musics;
    }
}
