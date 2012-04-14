/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sessions.Session;

/**
 *
 * @author casa
 */
public class Users {
    
    private static Users instance;
    
    private Set<User> list;
    
    private Users() {
        list = new HashSet<User>();
        list.add(new Admin());
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
        Session.startSession(user);
        return user;
    }
    
    public boolean remove(User user) throws Exception {
        return list.remove(user);
    }
    
    
}
