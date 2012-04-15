package users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sessions.LoginSession;

public class Users {
    
    private static Users instance;
    
    private Set<User> list;
    
    private Users() {
        list = new HashSet<User>();
        list.add(new Admin());
//        try {
//            Producer producer = new Producer("Producer", "producer@email.com", "producer", "1", "1");
//            list.add(new Producer("Producer", "producer@email.com", "producer", "1", "1"));
//            producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "2");
//            producer.publish("Innocence", "Punk Rock", "The Best Damn Thing", "Avril Lavigne", "10");
//            producer.publish("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "21");
//            producer.publish("Loba", "Nu-Disco, electropop latin", "She Wolf", "Shakira", "5");
//            producer.publish("Estoy Aqui", "Latin", "Piez Descalzos", "Shakira", "1");
//            list.add(new Client("Cliente", "client@email.com", "client", "1", "1"));
//        } catch (Exception e) {
//            
//        }
    }
    
    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }
    
    public static void eraseInstance() {
        instance = null;
    }
    
    public int count() {
        return list.size();
    }
    
    public User findByLogin(String login) {
        for (User user : list) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
    
    public void signUp(User user) throws Exception {
        if (!list.add(user)) {
            throw new Exception("Este login já existe");
        }
    }
    
    public User login(String login, String password) throws Exception {
        User user = this.findByLogin(login);
        if (user == null || !user.validatePassword(password)) {
            throw new Exception("O login e/ou a senha estão incorretos");
        }
        LoginSession.startSession(user);
        return user;
    }
    
    public boolean remove(User user) throws Exception {
        return list.remove(user);
    }
    
    public List<Producer> getProducers() {
        List<Producer> result = new ArrayList<Producer>();
        for (User user : list) {
            if (user instanceof Producer) {
                result.add((Producer) user);
            }
        }
        return result;
    }
    
    
}
