import sessions.Session;
import codes.Code;
import users.Admin;
import store.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import users.Client;
import static org.junit.Assert.*;

public class CreditTest {
    
    Store credits;
    
    Client client;
    Admin admin;
    
    @Before
    public void setUp() throws Exception {
        credits = Store.getInstance();
        client = new Client("Ana", "ana@email.com", "aninha", "123456", "123456");
        admin = ((Admin) Store.getInstance().findByLogin("admin"));
    }
    
    @After
    public void tearDown() {
        Store.eraseInstance();
        Session.eraseInstance();
    }
    
    @Test
    public void testCreateCredit() throws Exception {
        Code credit = new Code(25);
        assertEquals(25, credit.getValue());
    }
    
    @Test
    public void testCreateCreditByCode() throws Exception {
        Code credit = new Code("25-tg2q-x60n-bylj");
        assertEquals(25, credit.getValue());
    }

    @Test
    public void testIdentifyCrediyByCode() throws Exception {
        Code credit = new Code(10);
        Code copy = new Code(credit.getKey());
        assertEquals(credit, copy);
    }
    
    @Test
    public void testRegisterCredit() throws Exception {
        Code credit = new Code(10);
        assertEquals(0, credits.getCodes().size());
        assertFalse(credits.hasCode(credit.getKey()));
        assertTrue(credits.registerCode(credit));
        assertTrue(credits.hasCode(credit.getKey()));
        assertEquals(1, credits.getCodes().size());
    }
    
    @Test
    public void testNotAllowToRegisterTheSameCreditTwice() throws Exception {
        Code credit = new Code(10);
        assertEquals(0, credits.getCodes().size());
        assertFalse(credits.hasCode(credit.getKey()));
        assertTrue(credits.registerCode(credit));
        assertTrue(credits.hasCode(credit.getKey()));
        assertFalse(credits.registerCode(credit));
        assertFalse(credits.registerCode(new Code(credit.getKey())));
        assertTrue(credits.hasCode(credit.getKey()));
        assertEquals(1, credits.getCodes().size());
    }

    @Test
    public void testGenerateNCredits() throws Exception {
        credits.generateCodes(5, 25);
        assertEquals(5, credits.getCodes().size());
    }
    
    @Test
    public void testActivateCredit() throws Exception {
        Code credit = new Code(25);
        credits.registerCode(credit);
        credit = credits.findCode(credit.getKey());
        assertFalse(credit.isActivated());
        credit.activate();
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testActivatedCreditCantBeFoundButStillExistsToAvoidCodeRepetition() throws Exception {
        Code credit = new Code(25);
        credits.registerCode(credit);
        assertTrue(credits.hasCode(credit.getKey()));
        assertNotNull(credits.findCode(credit.getKey()));
        credit.activate();
        assertNull(credits.findCode(credit.getKey()));
        assertTrue(credits.hasCode(credit.getKey()));
    }
    
    @Test
    public void testActivateCreditByCode() throws Exception {
        Code credit = new Code(25);
        credits.registerCode(credit);
        assertFalse(credit.isActivated());
        credits.activateCode(credit.getKey());
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testActivateUnknownCodeInstPossible() throws Exception {
        Code credit = new Code(25);
        credits.registerCode(credit);
        credit.activate();
        assertTrue(credit.isActivated());
        try {
            credits.activateCode(credit.getKey());
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O código digitado não existe", e.getMessage());
        }
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testClientCanAcquireCredits() throws Exception {
        Code credit = new Code(25);
        credits.registerCode(credit);
        assertFalse(credit.isActivated());
        assertEquals(0, client.getCredits());
        client.acquireCredits(credit.getKey());
        assertEquals(25, client.getCredits());
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testAdminCanGenerateCredits() throws Exception {
        assertEquals(0, credits.getCodes().size());
        admin.generateCodes("5", "25");
        assertEquals(5, credits.getCodes().size());
    }
    
    @Test
    public void testAdminCannotGenerateCreditsWhenCountOrValueIsInvalid() throws Exception {
        assertEquals(0, credits.getCodes().size());
        try {
            admin.generateCodes("a", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            admin.generateCodes("5", "2b");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, credits.getCodes().size());
    }
    
    @Test
    public void testNegativeValueOrCountAreInvalid() throws Exception {
        assertEquals(0, credits.getCodes().size());
        try {
            admin.generateCodes("-5", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            admin.generateCodes("5", "-25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, credits.getCodes().size());
    }
    
}
