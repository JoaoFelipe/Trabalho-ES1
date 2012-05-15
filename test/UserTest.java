import sessions.Session;
import credits.Credits;
import users.Admin;
import java.util.List;
import java.util.Arrays;
import musics.Catalog;
import users.User;
import users.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import users.Client;
import static org.junit.Assert.*;

public class UserTest {
    
    Catalog catalog;
    Credits credits;
    Users users;
    
    Admin admin;
    
    @Before
    public void setUp() {
        catalog = Catalog.getInstance();
        credits = Credits.getInstance();
        users = Users.getInstance();
        
        admin = (Admin) users.findByLogin("admin");
    }
    
    @After
    public void tearDown() {
        Users.eraseInstance();
        Credits.eraseInstance();
        Catalog.eraseInstance();
        Session.eraseInstance();
    }
    
    @Test
    public void testUsersDefaultInstanceShouldCreateAnAdmin() throws Exception {
        assertEquals(1, users.getUserList().size());
        User user = users.findByLogin("admin");
        assertNotNull(user);
        assertEquals(user.getName(), "Administrador");
        assertEquals(user.getEmail(), "admin@admin.com");
        assertEquals(user.getLogin(), "admin");
        assertTrue(user.validatePassword("admin"));
    }
    
    @Test
    public void testCreateClient() throws Exception {
        User user = new Client("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(user.getName(), "Ana");
        assertEquals(user.getEmail(), "ana@email.com");
        assertEquals(user.getLogin(), "aninha");
        assertTrue(user.validatePassword("123456"));
    }
    
    @Test
    public void testCantCreateUserWithInvalidEmail() throws Exception {
        try {
            Client client = new Client("Ana", "ana", "aninha", "123456", "123456");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O formato do email está errado", e.getMessage());
        }
    }
    
    @Test
    public void testCantCreateUserWithEmptyField() throws Exception {
        for (int i = 0; i < 4; i++) {
            List<String> param = Arrays.asList("Ana", "ana@email.com", "aninha", "123456");
            param.set(i, "");
            try {
                Client client = new Client(param.get(0), param.get(1), param.get(2), param.get(3), param.get(3));
                assertFalse(true);
            } catch (Exception e) {
                assertEquals("Todos os campos devem ser preenchidos", e.getMessage());
            }
            
        }
        
    }
    
    @Test
    public void testCantCreateUserIfPasswordRepetitionIsInvalid() throws Exception {
        try {
            Client client = new Client("Ana", "ana@email.com", "aninha", "123456", "654321");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As senhas digitadas não coincidem", e.getMessage());
        }
    }

    @Test
    public void testClientCanSignUp() throws Exception {
        assertNull(users.findByLogin("aninha"));
        users.getUserFactory().signUpClient("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertNotNull(users.findByLogin("aninha"));
        assertEquals(2, users.getUserList().size());
    }
    
    @Test
    public void testClientLoginShouldBeUnique() throws Exception {
        try {
            users.getUserFactory().signUpClient("Ana Admin", "ana@email.com", "admin", "123456", "123456");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Este login já existe", e.getMessage());
        }
        assertEquals(1, users.getUserList().size());
    }
    
    @Test
    public void testCreateAdmin() throws Exception {
        assertNull(users.findByLogin("aninha"));
        User user = users.getUserFactory().signUpAdmin("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(user, users.findByLogin("aninha"));
        assertEquals(2, users.getUserList().size());
        assertEquals(user.getName(), "Ana");
        assertEquals(user.getEmail(), "ana@email.com");
        assertEquals(user.getLogin(), "aninha");
        assertTrue(user.validatePassword("123456"));
    }
    
    @Test
    public void testCreateProducer() throws Exception {
        assertNull(users.findByLogin("aninha"));
        User user = users.getUserFactory().signUpProducer("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(user, users.findByLogin("aninha"));
        assertEquals(2, users.getUserList().size());
        assertEquals(user.getName(), "Ana");
        assertEquals(user.getEmail(), "ana@email.com");
        assertEquals(user.getLogin(), "aninha");
        assertTrue(user.validatePassword("123456"));
    }
    
    @Test
    public void testUserLogin() throws Exception {
        users.login("admin", "admin");
        assertEquals(admin, users.getLoggedUser());
    }
    
    @Test
    public void testLoginMustExist() throws Exception {
        try {
            users.login("teste", "admin");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O login e/ou a senha estão incorretos", e.getMessage());
        }
    }
    
    @Test
    public void testPasswordShouldMatch() throws Exception {
        try {
            users.login("admin", "123456");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O login e/ou a senha estão incorretos", e.getMessage());
        }
    }
    
    @Test
    public void testChangeUserPassword() throws Exception {
        assertFalse(admin.validatePassword("123456"));
        admin.changePassword("admin", "123456", "123456");
        assertTrue(admin.validatePassword("123456"));
    }
    
    @Test
    public void testCantChangePasswordIfTheOldDontMatch() throws Exception {
        assertFalse(admin.validatePassword("123456"));
        try {
            admin.changePassword("abcdef", "123456", "123456");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("A senha antiga está incorreta", e.getMessage());
        }
        assertFalse(admin.validatePassword("123456"));
    }
    
    @Test
    public void testCantChangePassowrdifTheNewPasswordDontMatchTheRepetition() throws Exception {
        assertFalse(admin.validatePassword("123456"));
        try {
            admin.changePassword("admin", "123456", "654321");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("A senha nova não coincide com a repetição da senha", e.getMessage());
        }
        assertFalse(admin.validatePassword("123456"));
    }
    
    @Test
    public void testLogout() throws Exception {
        users.login("admin", "admin");
        assertEquals(admin, users.getLoggedUser());
        users.logout();
        assertNull(users.getLoggedUser());
    }



    
    
}
