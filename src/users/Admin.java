package users;

import store.Store;

public class Admin extends User {

    public Admin() {
        super("Administrador", "admin@admin.com", "admin", "admin");
    }

    public Admin(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
    }

    public void generateCodes(String count, String value) throws Exception {
        try {
            int c = Integer.parseInt(count);
            int v = Integer.parseInt(value);
            if (c < 0 || v < 0) {
                throw new NumberFormatException("-");
            }
            Store.getInstance().generateCodes(c, v);
        } catch (NumberFormatException e) {
            throw new Exception("As informações digitadas estão inconsistentes");
        }
    }
    
    public void removeProducer(String login) throws Exception {
        User user = Store.getInstance().findByLogin(login);
        ((Producer) user).removeAllPublications();
        Store.getInstance().removeUser(user);
    }
    
}
