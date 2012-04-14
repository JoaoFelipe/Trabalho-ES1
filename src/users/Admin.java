/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import credits.Credits;

/**
 *
 * @author casa
 */
public class Admin extends User {

    public Admin() {
        super("Administrador", "admin@admin.com", "admin", "admin");
    }

    public Admin(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
    }

    public void generateCredits(String count, String value) throws Exception {
        try {
            int c = Integer.parseInt(count);
            int v = Integer.parseInt(value);
            Credits.getInstance().generate(c, v);
        } catch (NumberFormatException e) {
            throw new Exception("As informações digitadas estão inconsistentes");
        }
    }
    
    public void removeProducer(String login) throws Exception {
        User user = Users.getInstance().findByLogin(login);
        if (!(user instanceof Producer)) {
            throw new Exception("Apenas produtores podem ser removidos");
        }
        ((Producer) user).removeAllPublications();
        Users.getInstance().remove(user);
    }
    
}
