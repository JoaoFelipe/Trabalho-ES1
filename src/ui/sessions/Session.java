package ui.sessions;

import javax.swing.JFrame;

public abstract class Session {
    
    private static Session session = null;

    private JFrame component = null;

    public static void eraseInstance() {
        session = null;
    }
    
    public static Session getInstance() {
        if (session == null) {
            session = new LoginSession();
        }
        return session;
    }
    
    public static void setInstance(Session session) {
        Session.session = session;
    }
    
    public void setComponent(javax.swing.JFrame component) {
        this.component = component;
    }
    
    public JFrame getComponent() {
        return component;
    }
    
    public abstract void showDialog();
}
