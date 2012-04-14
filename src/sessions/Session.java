/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.awt.Component;
import javax.swing.JFrame;
import users.User;

/**
 *
 * @author Joao
 */
public abstract class Session {
    
    public static Session session = null;
    protected JFrame component = null;

    public static void eraseInstance() {
        session = null;
    }
    
    public static Session getInstance() {
        if (session == null) {
            session = new LoginSession();
        }
        return session;
    }
    
    public void setComponent(javax.swing.JFrame component) {
        this.component = component;
    }
    
    public JFrame getComponent() {
        return component;
    }
    
    public abstract void showDialog();
}
