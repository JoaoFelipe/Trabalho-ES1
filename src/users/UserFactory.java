package users;

public class UserFactory {
    
    private Users users = null;
    
    public Client signUpClient(String name, String email, String login, String password, String repeatPassword) throws Exception {
        Client client = new Client(name, email, login, password, repeatPassword);
        this.getUsers().signUp(client);
        return client;
    }
    
    public Admin signUpAdmin(String name, String email, String login, String password, String repeatPassword) throws Exception {
        Admin admin = new Admin(name, email, login, password, repeatPassword);
        this.getUsers().signUp(admin);
        return admin;
    }
    
    public Producer signUpProducer(String name, String email, String login, String password, String repeatPassword) throws Exception {
        Producer producer = new Producer(name, email, login, password, repeatPassword);
        this.getUsers().signUp(producer);
        return producer;
    }
    
    public Users getUsers() {
        if (this.users == null) {
            users = Users.getInstance();
        }
        return this.users;
    }
    
}
