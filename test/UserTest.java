import ui.sessions.Session;
import business.users.Admin;
import java.util.List;
import java.util.Arrays;
import business.store.Store;
import business.users.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import business.users.Client;
import static org.junit.Assert.*;

public class UserTest {
    
    Store catalog;
    Store credits;
    Store store;
    
    Admin admin;
    
    @Before
    public void setUp() {
        catalog = Store.getInstance();
        credits = Store.getInstance();
        store = Store.getInstance();
        
        admin = (Admin) store.findByLogin("admin");
    }
    
    @After
    public void tearDown() {
        Store.eraseInstance();
        Session.eraseInstance();
    }
    
    @Test
    public void testUsersDefaultInstanceShouldCreateAnAdmin() throws Exception {
        assertEquals(1, store.getUsers().size());
        User user = store.findByLogin("admin");
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
        assertNull(store.findByLogin("aninha"));
        store.signUpClient("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertNotNull(store.findByLogin("aninha"));
        assertEquals(2, store.getUsers().size());
    }
    
    @Test
    public void testClientLoginShouldBeUnique() throws Exception {
        try {
            store.signUpClient("Ana Admin", "ana@email.com", "admin", "123456", "123456");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Este login já existe", e.getMessage());
        }
        assertEquals(1, store.getUsers().size());
    }
    
    @Test
    public void testCreateAdmin() throws Exception {
        assertNull(store.findByLogin("aninha"));
        User user = store.signUpAdmin("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(user, store.findByLogin("aninha"));
        assertEquals(2, store.getUsers().size());
        assertEquals(user.getName(), "Ana");
        assertEquals(user.getEmail(), "ana@email.com");
        assertEquals(user.getLogin(), "aninha");
        assertTrue(user.validatePassword("123456"));
    }
    
    @Test
    public void testCreateProducer() throws Exception {
        assertNull(store.findByLogin("aninha"));
        User user = store.signUpProducer("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(user, store.findByLogin("aninha"));
        assertEquals(2, store.getUsers().size());
        assertEquals(user.getName(), "Ana");
        assertEquals(user.getEmail(), "ana@email.com");
        assertEquals(user.getLogin(), "aninha");
        assertTrue(user.validatePassword("123456"));
    }
    
    @Test
    public void testUserLogin() throws Exception {
        store.login("admin", "admin");
        assertEquals(admin, store.getLoggedUser());
    }
    
    @Test
    public void testLoginMustExist() throws Exception {
        try {
            store.login("teste", "admin");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O login e/ou a senha estão incorretos", e.getMessage());
        }
    }
    
    @Test
    public void testPasswordShouldMatch() throws Exception {
        try {
            store.login("admin", "123456");
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
        store.login("admin", "admin");
        assertEquals(admin, store.getLoggedUser());
        store.logout();
        assertNull(store.getLoggedUser());
    }



    
    
}
