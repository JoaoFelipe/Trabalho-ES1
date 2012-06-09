package users;

import store.Store;

public abstract class User {
    
    private String name;
    private String email;
    private String login;
    private String password;

    protected User(String name, String email, String login, String password) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }

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
        if (Store.getInstance().findByLogin(login) != null) {
            throw new Exception("Este login já existe");
        }
        
    }
    
    public boolean validatePassword(String password) {
        return this.getPassword().equals(password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        final User other = (User) obj;
        if ((this.getLogin() == null) ? (other.getLogin() != null) : !this.getLogin().equals(other.getLogin())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.getLogin().hashCode();
    }

    public void changePassword(String oldPassword, String newPassword, String repeatNewPassword) throws Exception {
        if (!this.validatePassword(oldPassword)) {
            throw new Exception("A senha antiga está incorreta");
        }
        if (!newPassword.equals(repeatNewPassword)) {
            throw new Exception("A senha nova não coincide com a repetição da senha");
        }
        this.setPassword(newPassword);
    }
    
    public void logout() {
        Store.getInstance().setLoggedUser(null);
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
    
    private String getPassword() {
        return password;
    }
    
    private void setPassword(String password) {
        this.password = password;
    }
    
}
