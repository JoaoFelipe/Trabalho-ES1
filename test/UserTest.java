/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.Arrays;
import users.User;
import users.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import users.Client;
import static org.junit.Assert.*;

/**
 *
 * @author casa
 */
public class UserTest {
    
    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Users.eraseInstance();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void usersDefaultInstanceShouldCreateAnAdmin() throws Exception {
        Users users = Users.getInstance();
        assertEquals(1, users.getUserCount());
        User user = users.findByLogin("admin");
        assertNotNull(user);
        assertEquals(user.getName(), "Administrador");
        assertEquals(user.getEmail(), "admin@admin.com");
        assertEquals(user.getLogin(), "admin");
        assertTrue(user.validatePassword("admin"));
    }
    
    @Test
    public void itShouldBePossibleToCreateAValidClient() throws Exception {
        User user = new Client("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(user.getName(), "Ana");
        assertEquals(user.getEmail(), "ana@email.com");
        assertEquals(user.getLogin(), "aninha");
        assertTrue(user.validatePassword("123456"));
    }
    
    @Test
    public void userConstructorShouldValidateEmail() throws Exception {
        try {
            new Client("Ana", "ana", "aninha", "123456", "123456");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O formato do email está errado", e.getMessage());
        }
    }
    
    @Test
    public void userConstructorShouldVerifyIfTheresAEmptyField() throws Exception {
        for (int i = 0; i < 4; i++) {
            List<String> param = Arrays.asList("Ana", "ana@email.com", "aninha", "123456");
            param.set(i, "");
            try {
                new Client(param.get(0), param.get(1), param.get(2), param.get(3), param.get(3));
                assertFalse(true);
            } catch (Exception e) {
                assertEquals("Todos os campos devem ser preenchidos", e.getMessage());
            }
            
        }
        
    }
    
    @Test
    public void userConstructorShouldValidateThePasswords() throws Exception {
        try {
            new Client("Ana", "ana@email.com", "aninha", "123456", "654321");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As senhas digitadas não coincidem", e.getMessage());
        }
    }

    @Test
    public void clientCanSignUp() throws Exception {
        Users users = Users.getInstance();
        assertNull(users.findByLogin("aninha"));
        User user = new Client("Ana", "ana@email.com", "aninha", "123456", "123456");
        users.signUp(user);
        assertNotNull(users.findByLogin("aninha"));
        assertEquals(2, users.getUserCount());
    }
    
    @Test
    public void clientLoginShouldBeUnique() throws Exception {
        Users users = Users.getInstance();
        User user = new Client("Ana Admin", "ana@email.com", "admin", "123456", "123456");
        try {
            users.signUp(user);
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Este login já existe", e.getMessage());
        }
        assertEquals(1, users.getUserCount());
    }
}
