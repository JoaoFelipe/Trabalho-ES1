/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import users.User;

/**
 *
 * @author Joao
 */
public class Session {
    
    public static Session session = null;
    
    public static void eraseInstance() {
        session = null;
    }
    
    public static Session getInstance() {
        if (session == null) {
            session = new LoginSession();
        }
        return session;
    }
    
    
    
}
