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
    
    private static Session session = null;
    
    private User user;
    
    private Session(User user) {
        this.user = user;
    }
    
    public static Session startSession(User user) {
        session = new Session(user);
        return session;
    }
    
    public static void endSession() {
        session = null;
    }
    
    public static Session getInstance() {
        return session;
    }
    
    public User getUser() {
        return user;
    }
    
}
