package users;

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
    
    public boolean validatePassword(String password) {
        return this.password.equals(password);
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
