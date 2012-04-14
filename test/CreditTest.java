/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import credits.Credit;
import credits.Credits;
import users.Producer;
import users.Admin;
import java.util.List;
import java.util.Arrays;
import musics.Catalog;
import users.User;
import users.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sessions.Session;
import users.Client;
import static org.junit.Assert.*;

/**
 *
 * @author casa
 */
public class CreditTest {
    
    Catalog catalog;
    Credits credits;
    Users users;
    
    @Before
    public void setUp() {
        Users.eraseInstance();
        Credits.eraseInstance();
        Catalog.eraseInstance();
        Session.endSession();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void itShouldBePossibleToCreateACredit() throws Exception {
        Credit credit = new Credit(25);
        assertEquals(25, credit.getValue());
    }
    
    @Test
    public void itShouldBePossibleToFigureTheCreditValueByCode() throws Exception {
        Credit credit = new Credit("25-tg2q-x60n-bylj");
        assertEquals(25, credit.getValue());
    }

    @Test
    public void creditMustBeIdentifiedByCode() throws Exception {
        Credit credit = new Credit(10);
        Credit copy = new Credit(credit.getCode());
        assertEquals(credit, copy);
    }
    
    @Test
    public void itShouldBePossibleToRegisterACredit() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(10);
        assertEquals(0, credits.count());
        assertFalse(credits.hasCredit(credit.getCode()));
        assertTrue(credits.register(credit));
        assertTrue(credits.hasCredit(credit.getCode()));
        assertEquals(1, credits.count());
    }
    
    @Test
    public void itShouldNotBePossibleToRegisterTheSameCreditTwice() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(10);
        assertEquals(0, credits.count());
        assertFalse(credits.hasCredit(credit.getCode()));
        assertTrue(credits.register(credit));
        assertTrue(credits.hasCredit(credit.getCode()));
        assertFalse(credits.register(credit));
        assertFalse(credits.register(new Credit(credit.getCode())));
        assertTrue(credits.hasCredit(credit.getCode()));
        assertEquals(1, credits.count());
    }

    @Test
    public void itShouldBePossibleToGenerateNCredits() throws Exception {
        Credits credits = Credits.getInstance();
        credits.generate(5, 25);
        assertEquals(5, credits.count());
    }
    
    @Test
    public void itShouldBePossibleToActivateACredit() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        credit = credits.find(credit.getCode());
        assertFalse(credit.isActive());
        credit.activate();
        assertTrue(credit.isActive());
    }
    
    @Test
    public void aCreditMustNotBeFoundIfActiveButMustStillExistsToAvoidCodeRepetition() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        assertTrue(credits.hasCredit(credit.getCode()));
        assertNotNull(credits.find(credit.getCode()));
        credit.activate();
        assertNull(credits.find(credit.getCode()));
        assertTrue(credits.hasCredit(credit.getCode()));
    }
    
    @Test
    public void itShouldBePossibleToActivateACreditByCode() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        assertFalse(credit.isActive());
        credits.activate(credit.getCode());
        assertTrue(credit.isActive());
    }
    
    @Test
    public void itShouldNotBePossibleToActivateAUnknownCode() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        credit.activate();
        assertTrue(credit.isActive());
        try {
            credits.activate(credit.getCode());
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O código digitado não existe", e.getMessage());
        }
        assertTrue(credit.isActive());
    }
    
    @Test
    public void clientCanAcquireCredits() throws Exception {
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        assertFalse(credit.isActive());
        Client client = new Client("Ana", "ana@email.com", "aninha", "123456", "123456");
        assertEquals(0, client.getCredits());
        client.acquireCredits(credit.getCode());
        assertEquals(25, client.getCredits());
        assertTrue(credit.isActive());
    }
    
    @Test
    public void adminCanGenerateCredits() throws Exception {
        Credits credits = Credits.getInstance();
        Users users = Users.getInstance();
        assertEquals(0, credits.count());
        ((Admin) users.findByLogin("admin")).generateCredits("5", "25");
        assertEquals(5, credits.count());
    }
    
    @Test
    public void adminCannotGenerateCreditsWhenCountOrValueIsInvalid() throws Exception {
        Credits credits = Credits.getInstance();
        Users users = Users.getInstance();
        assertEquals(0, credits.count());
        try {
            ((Admin) users.findByLogin("admin")).generateCredits("a", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            ((Admin) users.findByLogin("admin")).generateCredits("5", "2b");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, credits.count());
    }
    
}
