/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import sessions.UserSession;

/**
 *
 * @author casa
 */
public abstract class User {
    
    private String name;
    private String email;
    private String login;
    private String password;

    /**
     * @param name
     * @param email
     * @param login
     * @param password
     */
    protected User(String name, String email, String login, String password) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    /**
     * 
     * @param name
     * @param email
     * @param login
     * @param password
     * @param repeatPassword
     * @throws Exception if email is invalid or if have an empty value or if the passwords doesn't match
     */
    public User(String name, String email, String login, String password, String repeatPassword) throws Exception {
        this(name, email, login, password);
        if (name.isEmpty() || email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            throw new Exception("Todos os campos devem ser preenchidos");
        }
        if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
            throw new Exception("O formato do email está errado");
        }
        if (!password.equals(repeatPassword)) {
            throw new Exception("As senhas digitadas não coincidem");
        }
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * @param password Takes a password to be validated with the user password
     * @return true if the password matches
     */
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    /**
     * 
     * @param obj Takes another User
     * @return true if both users have the same login 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        final User other = (User) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.login.hashCode();
    }

    public void changePassword(String oldPassword, String newPassword, String repeatNewPassword) throws Exception {
        if (!this.validatePassword(oldPassword)) {
            throw new Exception("A senha antiga está incorreta");
        }
        if (!newPassword.equals(repeatNewPassword)) {
            throw new Exception("A senha nova não coincide com a repetição da senha");
        }
        this.password = newPassword;
    }
    
}
