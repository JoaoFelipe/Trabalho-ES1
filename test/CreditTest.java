import sessions.Session;
import credits.Credit;
import credits.Credits;
import users.Admin;
import musics.Catalog;
import users.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import users.Client;
import static org.junit.Assert.*;

public class CreditTest {
    
    Credits credits;
    
    Client client;
    Admin admin;
    
    @Before
    public void setUp() throws Exception {
        credits = Credits.getInstance();
        client = new Client("Ana", "ana@email.com", "aninha", "123456", "123456");
        admin = ((Admin) Users.getInstance().findByLogin("admin"));
    }
    
    @After
    public void tearDown() {
        Users.eraseInstance();
        Credits.eraseInstance();
        Catalog.eraseInstance();
        Session.eraseInstance();
    }
    
    @Test
    public void testCreateCredit() throws Exception {
        Credit credit = new Credit(25);
        assertEquals(25, credit.getValue());
    }
    
    @Test
    public void testCreateCreditByCode() throws Exception {
        Credit credit = new Credit("25-tg2q-x60n-bylj");
        assertEquals(25, credit.getValue());
    }

    @Test
    public void testIdentifyCrediyByCode() throws Exception {
        Credit credit = new Credit(10);
        Credit copy = new Credit(credit.getCode());
        assertEquals(credit, copy);
    }
    
    @Test
    public void testRegisterCredit() throws Exception {
        Credit credit = new Credit(10);
        assertEquals(0, credits.count());
        assertFalse(credits.hasCredit(credit.getCode()));
        assertTrue(credits.register(credit));
        assertTrue(credits.hasCredit(credit.getCode()));
        assertEquals(1, credits.count());
    }
    
    @Test
    public void testNotAllowToRegisterTheSameCreditTwice() throws Exception {
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
    public void testGenerateNCredits() throws Exception {
        credits.generate(5, 25);
        assertEquals(5, credits.count());
    }
    
    @Test
    public void testActivateCredit() throws Exception {
        Credit credit = new Credit(25);
        credits.register(credit);
        credit = credits.find(credit.getCode());
        assertFalse(credit.isActive());
        credit.activate();
        assertTrue(credit.isActive());
    }
    
    @Test
    public void testActivatedCreditCantBeFoundButStillExistsToAvoidCodeRepetition() throws Exception {
        Credit credit = new Credit(25);
        credits.register(credit);
        assertTrue(credits.hasCredit(credit.getCode()));
        assertNotNull(credits.find(credit.getCode()));
        credit.activate();
        assertNull(credits.find(credit.getCode()));
        assertTrue(credits.hasCredit(credit.getCode()));
    }
    
    @Test
    public void testActivateCreditByCode() throws Exception {
        Credit credit = new Credit(25);
        credits.register(credit);
        assertFalse(credit.isActive());
        credits.activate(credit.getCode());
        assertTrue(credit.isActive());
    }
    
    @Test
    public void testActivateUnknownCodeInstPossible() throws Exception {
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
    public void testClientCanAcquireCredits() throws Exception {
        Credit credit = new Credit(25);
        credits.register(credit);
        assertFalse(credit.isActive());
        assertEquals(0, client.getCredits());
        client.acquireCredits(credit.getCode());
        assertEquals(25, client.getCredits());
        assertTrue(credit.isActive());
    }
    
    @Test
    public void testAdminCanGenerateCredits() throws Exception {
        assertEquals(0, credits.count());
        admin.generateCredits("5", "25");
        assertEquals(5, credits.count());
    }
    
    @Test
    public void testAdminCannotGenerateCreditsWhenCountOrValueIsInvalid() throws Exception {
        assertEquals(0, credits.count());
        try {
            admin.generateCredits("a", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            admin.generateCredits("5", "2b");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, credits.count());
    }
    
    @Test
    public void testNegativeValueOrCountAreInvalid() throws Exception {
        assertEquals(0, credits.count());
        try {
            admin.generateCredits("-5", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            admin.generateCredits("5", "-25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, credits.count());
    }
    
}
