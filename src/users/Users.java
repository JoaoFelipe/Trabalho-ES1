package users;
import java.util.HashSet;
import java.util.Set;

public class Users {
    
    private static Users instance;
    
    private Set<User> userList;
    
    private User loggedUser = null;
    
    private UserFactory userFactory = null;
    
    private Users() {
        userList = new HashSet<User>();
        userFactory = new UserFactory();
        
        userList.add(new Admin());
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
    
    public User findByLogin(String login) {
        for (User user : this.getUserList()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
    
    public void signUp(User user) throws Exception {
        if (!this.getUserList().add(user)) {
            throw new Exception("Este login já existe");
        }
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
    
    public boolean remove(User user) throws Exception {
        return this.getUserList().remove(user);
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    private void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Set<User> getUserList() {
        return userList;
    }
    
    public UserFactory getUserFactory() {
        return userFactory;
    }
    
}
