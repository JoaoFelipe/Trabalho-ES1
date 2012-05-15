import musics.MusicList;
import sessions.Session;
import users.Admin;
import musics.Catalog;
import musics.Music;
import credits.Credit;
import credits.Credits;
import users.Producer;
import java.util.Arrays;
import users.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import users.Client;
import static org.junit.Assert.*;

public class PopulatedTest {
    
    Catalog catalog;
    Users users;
    
    Admin admin;
    Client client;
    Producer producer;
    Producer producer2;
    Music m1;
    Music m2;
    Music m3;
    Music m4;
    Music m5;
    
    @Before
    public void setUp() throws Exception {
        catalog = Catalog.getInstance();
        users = Users.getInstance();
        admin = (Admin) users.findByLogin("admin");
        producer = (Producer)users.getUserFactory().signUpProducer("Ana", "ana@email.com", "aninha", "123456", "123456");
        producer2 = (Producer) users.getUserFactory().signUpProducer("Bia", "bia@email.com", "bia", "123456", "123456");
        client = (Client) users.getUserFactory().signUpClient("Carol", "carol@email.com", "carol", "123456", "123456");
        m1 = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "2");
        m2 = producer.publish("Innocence", "Punk Rock", "The Best Damn Thing", "Avril Lavigne", "10");
        m3 = producer2.publish("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "21");
        m4 = producer2.publish("Loba", "Nu-Disco, electropop latin", "She Wolf", "Shakira", "5");
        m5 = producer2.publish("Estoy Aqui", "Latin", "Piez Descalzos", "Shakira", "1");
        m4.increasePopularity();
        m4.increasePopularity();
        m2.increasePopularity();
        Credit credit = new Credit(25);
        Credits.getInstance().register(credit);
        client.acquireCredits(credit.getCode());
    }
    
    @After
    public void tearDown() {
        Users.eraseInstance();
        Credits.eraseInstance();
        Catalog.eraseInstance();
        Session.eraseInstance();
    }
        
    @Test
    public void testMusicMatchesSearchConditionIgnoringCase() throws Exception {
        assertTrue(m1.match("name", "Sk8er Boi"));
        assertFalse(m1.match("name", "Sk8er Boy"));
        assertTrue(m1.match("name", "Sk8er"));
        assertTrue(m1.match("name", "8er"));
        assertTrue(m1.match("genre", "pop"));
        assertFalse(m1.match("genre", "rck"));
        assertTrue(m1.match("album", "Let"));
        assertFalse(m1.match("album", "Get"));
        assertTrue(m1.match("artist", "avril"));
        assertFalse(m1.match("artist", "shakira"));
        assertFalse(m1.match("bla", "a"));
        assertTrue(m1.match("name", ""));
        assertTrue(m1.match("bla", ""));
    }
    
    @Test
    public void testFilterMusicListAndCatalogForSearch() throws Exception {
        assertEquals(5, catalog.getMusicList().size());
        assertArrayEquals(Arrays.asList(m1,m2,m3,m4,m5).toArray(), catalog.getMusicList().filter("name", "o").toArray());
        assertArrayEquals(Arrays.asList(m4,m5).toArray(), catalog.getMusicList().filter("name", "a").toArray());
        assertArrayEquals(Arrays.asList(m1,m2).toArray(), catalog.getMusicList().filter("artist", "Avril").toArray());
        assertArrayEquals(Arrays.asList(m3,m4,m5).toArray(), catalog.getMusicList().filter("artist", "Shakira").toArray());
        assertArrayEquals(Arrays.asList(m1,m3,m4).toArray(), catalog.getMusicList().filter("genre", "pop").toArray());
        assertArrayEquals(Arrays.asList(m1,m3).toArray(), new MusicList(Arrays.asList(m1,m2,m3)).filter("genre", "pop").toArray());
        
    }
    
    @Test
    public void testSortMusicListAndCatalogByField() throws Exception {
        assertEquals(5, catalog.getMusicList().size());
        assertArrayEquals(Arrays.asList(m5,m2,m4,m3,m1).toArray(), catalog.getMusicList().sort("name").toArray()); //String: crescente
        assertArrayEquals(Arrays.asList(m3,m2,m4,m1,m5).toArray(), catalog.getMusicList().sort("price").toArray()); //Int: decrescente
        assertArrayEquals(Arrays.asList(m3,m2,m1).toArray(), new MusicList(Arrays.asList(m1,m2,m3)).sort("price").toArray()); //Int: decrescente
        assertArrayEquals(Arrays.asList(m4,m2,m1,m3).toArray(), new MusicList(Arrays.asList(m1,m2,m3,m4)).sort("popularity").toArray()); //Int: decrescente
    }
    
    @Test
    public void testClientCanBuyMusic() throws Exception {
        assertEquals(0, producer.getCredits());
        assertEquals(25, client.getCredits());
        assertEquals(0, client.getMyMusics().size());
        assertEquals(0, m1.getPopularity());
        client.buy(m1);
        assertEquals(23, client.getCredits());
        assertEquals(1, client.getMyMusics().size());
        assertEquals(1, m1.getPopularity());
        assertEquals(2, producer.getCredits());
    }
    
    @Test
    public void testProducerReceiveTheCreditIfTheMusicValueIs1() throws Exception {
        assertEquals(0, producer2.getCredits());
        assertEquals(25, client.getCredits());
        assertEquals(0, client.getMyMusics().size());
        assertEquals(0, m5.getPopularity());
        client.buy(m5);
        assertEquals(24, client.getCredits());
        assertEquals(1, client.getMyMusics().size());
        assertEquals(1, m5.getPopularity());
        assertEquals(1, producer2.getCredits());
    }
    
    @Test
    public void testClientCannotBuyTheSameMusicTwice() throws Exception {
        assertEquals(0, producer.getCredits());
        assertEquals(25, client.getCredits());
        assertEquals(0, client.getMyMusics().size());
        assertEquals(0, m1.getPopularity());
        client.buy(m1);
        try {
            client.buy(m1);
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Você já possui esta música", e.getMessage());
        }
        assertEquals(23, client.getCredits());
        assertEquals(1, client.getMyMusics().size());
        assertEquals(1, m1.getPopularity());
        assertEquals(2, producer.getCredits());
    }
    
    @Test
    public void testClientCannotBuyAMusicIfItsPriceIsHighestThanHisCredits() throws Exception {
        assertEquals(0, producer2.getCredits());
        assertEquals(25, client.getCredits());
        client.buy(m3);
        assertEquals(4, client.getCredits());
        assertEquals(1, client.getMyMusics().size());
        assertEquals(2, m4.getPopularity());
        assertEquals(17, producer2.getCredits());
        try {
            client.buy(m4);
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Você não possui créditos suficientes", e.getMessage());
        }
        assertEquals(4, client.getCredits());
        assertEquals(1, client.getMyMusics().size());
        assertEquals(2, m4.getPopularity());
        assertEquals(17, producer2.getCredits());
    }
    
    @Test
    public void testRemoveProducer() throws Exception {
        client.buy(m1);
        client.buy(m2);
        client.buy(m4);
        assertEquals(5, catalog.getMusicList().size());
        assertEquals(4, users.getUserList().size());
        assertEquals(3, client.getMyMusics().size());
        admin.removeProducer("aninha");
        assertEquals(3, catalog.getMusicList().size());
        assertEquals(3, users.getUserList().size());
        assertEquals(3, client.getMyMusics().size());
    }
    
    @Test
    public void testPerformanceFilter10000musicsInLessThan10seconds() throws Exception {
        for (int i = 0; i < 10000-5; i++) {
            producer.publish(Credit.generateCode(i), Credit.generateCode(i), Credit.generateCode(i), Credit.generateCode(i), Math.round(Math.random()*100)+"");
        }
        long t = System.currentTimeMillis();
        Catalog.getInstance().getMusicList().filter("name", "10").sort("price");
        t = System.currentTimeMillis() - t;
        assertTrue(t<10000);
        System.out.println(t+"ms");
    }
    
}
