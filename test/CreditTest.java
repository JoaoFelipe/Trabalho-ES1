import ui.sessions.Session;
import business.codes.Code;
import business.users.Admin;
import business.store.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import business.users.Client;
import static org.junit.Assert.*;

public class CreditTest {
    
    Store store;
    
    Client client;
    Admin admin;
    
    @Before
    public void setUp() throws Exception {
        store = Store.getInstance();
        store.signUpClient("aninha", "ana@email.com", "aninha", "123456", "123456");
        client = (Client) store.findByLogin("aninha");
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
        assertEquals(0, store.getCodes().size());
        assertFalse(store.hasCode(credit.getKey()));
        assertTrue(store.registerCode(credit));
        assertTrue(store.hasCode(credit.getKey()));
        assertEquals(1, store.getCodes().size());
    }
    
    @Test
    public void testNotAllowToRegisterTheSameCreditTwice() throws Exception {
        Code credit = new Code(10);
        assertEquals(0, store.getCodes().size());
        assertFalse(store.hasCode(credit.getKey()));
        assertTrue(store.registerCode(credit));
        assertTrue(store.hasCode(credit.getKey()));
        assertFalse(store.registerCode(credit));
        assertFalse(store.registerCode(new Code(credit.getKey())));
        assertTrue(store.hasCode(credit.getKey()));
        assertEquals(1, store.getCodes().size());
    }

    @Test
    public void testGenerateNCredits() throws Exception {
        store.generateCodes("5", "25");
        assertEquals(5, store.getCodes().size());
    }
    
    @Test
    public void testActivateCredit() throws Exception {
        Code credit = new Code(25);
        store.registerCode(credit);
        credit = store.findCode(credit.getKey());
        assertFalse(credit.isActivated());
        credit.activate();
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testActivatedCreditCantBeFoundButStillExistsToAvoidCodeRepetition() throws Exception {
        Code credit = new Code(25);
        store.registerCode(credit);
        assertTrue(store.hasCode(credit.getKey()));
        assertNotNull(store.findCode(credit.getKey()));
        credit.activate();
        assertNull(store.findCode(credit.getKey()));
        assertTrue(store.hasCode(credit.getKey()));
    }
    
    @Test
    public void testActivateCreditByCode() throws Exception {
        Code credit = new Code(25);
        store.registerCode(credit);
        store.login("aninha", "123456");
        assertFalse(credit.isActivated());
        store.acquireCredits(credit.getKey());
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testActivateUnknownCodeInstPossible() throws Exception {
        Code credit = new Code(25);
        store.registerCode(credit);
        store.login("aninha", "123456");
        credit.activate();
        assertTrue(credit.isActivated());
        try {
            store.acquireCredits(credit.getKey());
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O código digitado não existe", e.getMessage());
        }
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testClientCanAcquireCredits() throws Exception {
        Code credit = new Code(25);
        store.registerCode(credit);
        store.login("aninha", "123456");
        assertFalse(credit.isActivated());
        assertEquals(0, client.getCredits());
        store.acquireCredits(credit.getKey());
        assertEquals(25, client.getCredits());
        assertTrue(credit.isActivated());
    }
    
    @Test
    public void testAdminCanGenerateCredits() throws Exception {
        assertEquals(0, store.getCodes().size());
        store.generateCodes("5", "25");
        assertEquals(5, store.getCodes().size());
    }
    
    @Test
    public void testAdminCannotGenerateCreditsWhenCountOrValueIsInvalid() throws Exception {
        assertEquals(0, store.getCodes().size());
        try {
            store.generateCodes("a", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            store.generateCodes("5", "2b");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, store.getCodes().size());
    }
    
    @Test
    public void testNegativeValueOrCountAreInvalid() throws Exception {
        assertEquals(0, store.getCodes().size());
        try {
            store.generateCodes("-5", "25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        try {
            store.generateCodes("5", "-25");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("As informações digitadas estão inconsistentes", e.getMessage());
        }
        assertEquals(0, store.getCodes().size());
    }
    
}
