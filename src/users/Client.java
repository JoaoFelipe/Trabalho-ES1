/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import credits.Credit;
import credits.Credits;

/**
 *
 * @author casa
 */
public class Client extends User {

    private int credits;
    
    public Client(String name, String email, String login, String password, String repeatPassword) throws Exception {
        super(name, email, login, password, repeatPassword);
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void acquireCredits(String code) throws Exception {
        Credit credit = Credits.getInstance().activate(code);
        credits += credit.getValue();
    }
   
   
    
}
