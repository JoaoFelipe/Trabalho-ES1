/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import musics.Catalog;
import musics.Music;
import credits.Credit;
import credits.Credits;
import users.Producer;
import users.Admin;
import java.util.List;
import java.util.Arrays;
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
public class MusicTest {
    
    Producer producer;
    Producer producer2;
    Client client;
    
    public MusicTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
        Users.eraseInstance();
        Credits.eraseInstance();
        Catalog.eraseInstance();
        Session.endSession();
        producer = new Producer("Ana", "ana@email.com", "aninha", "123456", "123456");
        producer2 = new Producer("Bia", "bia@email.com", "bia", "123456", "123456");
        client = new Client("Carol", "carol@email.com", "carol", "123456", "123456");

    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void itShouldBePossibleToCreateAMusic() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals("Sk8er Boi", music.getName());
        assertEquals("Pop Rock", music.getGenre());
        assertEquals("Let Go", music.getAlbum());
        assertEquals("Avril Lavigne", music.getArtist());
        assertEquals(5, music.getPrice());
        assertEquals(0, music.getPopularity());
    }
    
    @Test
    public void musicConstructorShouldVerifyIfTheresAEmptyField() throws Exception {
        for (int i = 0; i < 5; i++) {
            List<String> param = Arrays.asList("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
            param.set(i, "");
            try {
                new Music(producer, param.get(0), param.get(1), param.get(2), param.get(3), param.get(4));
                assertFalse(true);
            } catch (Exception e) {
                assertEquals("Todos os atributos devem estar preenchidos", e.getMessage());
            }
        }
    }
    
    @Test
    public void musicConstructorShouldVerifyIfPriceIsValid() throws Exception {
        try {
            new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "a");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O preço é inválido", e.getMessage());
        }
    }

    @Test
    public void itShouldBePossibleToPublishAMusicInCatalog() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.count());
        assertTrue(catalog.publish(music));
        assertEquals(1, catalog.count());
    }
    
    @Test
    public void itShouldBePossibleToIncrementMusicPopularity() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, music.getPopularity());
        music.increasePopularity();
        assertEquals(1, music.getPopularity());
    }
    
    @Test
    public void itShouldBePossibleToChangeMusicInformations() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        music.increasePopularity();
        music.change("Skater Boy", "Pop/Rock", "Live Acoustic", "Avril Lavigne", "4");
        assertEquals("Skater Boy", music.getName());
        assertEquals("Pop/Rock", music.getGenre());
        assertEquals("Live Acoustic", music.getAlbum());
        assertEquals("Avril Lavigne", music.getArtist());
        assertEquals(4, music.getPrice());
        assertEquals(1, music.getPopularity());
        music.change("Skater Boy", "Pop/Rock", "Live Acoustic", "Avril", "4");
        assertEquals("Avril", music.getArtist());
    }
    
    @Test
    public void itShouldBePossibleToRemoveAMusicFromCatalog() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.count());
        assertTrue(catalog.publish(music));
        assertEquals(1, catalog.count());
        assertTrue(catalog.remove(music));
        assertEquals(0, catalog.count());
    }
    
    @Test
    public void itShouldNotBePossibleToRemoveAMusicTwice() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.count());
        assertTrue(catalog.publish(music));
        assertEquals(1, catalog.count());
        assertTrue(catalog.remove(music));
        assertFalse(catalog.remove(music));
        assertEquals(0, catalog.count());
        
    }
    
    @Test
    public void aProducerCanPublishAMusic() throws Exception {
        Catalog catalog = Catalog.getInstance();
        assertEquals(0, catalog.count());
        assertEquals(0, producer.publicationsCount());
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(producer, music.getProducer());
        assertEquals(1, producer.publicationsCount());
        assertEquals(1, catalog.count());
    }
    
    @Test
    public void aProducerCanChangeMusicInformationsIfHePublishedTheMusic() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        producer2.publish("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "6");
        assertEquals(2, catalog.count());
        try {
            producer2.changeMusic(music, "Skater Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Você não é o produtor desta música", e.getMessage());
        }
        assertEquals("Sk8er Boi", music.getName());
        producer.changeMusic(music, "Skater Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals("Skater Boi", music.getName());
    }
    
    @Test
    public void aProducerCanRemoveMusicFromCatalogInformationsIfHePublishedTheMusic() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        producer2.publish("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "6");
        assertEquals(2, catalog.count());
        try {
            producer2.removeMusic(music);
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Você não é o produtor desta música", e.getMessage());
        }
        assertEquals(2, catalog.count());
        producer.removeMusic(music);
        assertEquals(1, catalog.count());
    }
    
    @Test
    public void musicMatchesSearchConditionIgnoreCase() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertTrue(music.match("name", "Sk8er Boi"));
        assertFalse(music.match("name", "Sk8er Boy"));
        assertTrue(music.match("name", "Sk8er"));
        assertTrue(music.match("name", "8er"));
        assertTrue(music.match("genre", "pop"));
        assertFalse(music.match("genre", "rck"));
        assertTrue(music.match("album", "Let"));
        assertFalse(music.match("album", "Get"));
        assertTrue(music.match("artist", "avril"));
        assertFalse(music.match("artist", "shakira"));
        assertFalse(music.match("bla", "a"));
        assertTrue(music.match("name", ""));
        assertTrue(music.match("bla", ""));
    }
    
    @Test
    public void filterCatalogForSearch() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music m1 = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        Music m2 = producer.publish("Innocence", "Punk Rock", "The Best Damn Thing", "Avril Lavigne", "6");
        Music m3 = producer2.publish("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "6");
        Music m4 = producer2.publish("Loba", "Nu-Disco, electropop latin", "She Wolf", "Shakira", "6");
        assertEquals(4, catalog.count());
        assertArrayEquals(Arrays.asList(m1,m2,m3,m4).toArray(), catalog.filter("name", "o").toArray());
        assertArrayEquals(Arrays.asList(m4).toArray(), catalog.filter("name", "a").toArray());
        assertArrayEquals(Arrays.asList(m1,m2).toArray(), catalog.filter("artist", "Avril").toArray());
        assertArrayEquals(Arrays.asList(m3,m4).toArray(), catalog.filter("artist", "Shakira").toArray());
        assertArrayEquals(Arrays.asList(m1,m3,m4).toArray(), catalog.filter("genre", "pop").toArray());
        assertArrayEquals(Arrays.asList(m1,m3).toArray(), Music.filterMusicList(Arrays.asList(m1,m2,m3), "genre", "pop").toArray());
        
    }
    
    @Test
    public void sortCatalogByField() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Music m1 = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "1");
        Music m2 = producer.publish("Innocence", "Punk Rock", "The Best Damn Thing", "Avril Lavigne", "10");
        Music m3 = producer2.publish("She Wolf", "Nu-Disco, electropop", "She Wolf", "Shakira", "11");
        Music m4 = producer2.publish("Loba", "Nu-Disco, electropop latin", "She Wolf", "Shakira", "2");
        m4.increasePopularity();
        m4.increasePopularity();
        m2.increasePopularity();
        assertEquals(4, catalog.count());
        assertArrayEquals(Arrays.asList(m2,m4,m3,m1).toArray(), catalog.sort("name").toArray()); //String: crescente
        assertArrayEquals(Arrays.asList(m3,m2,m4,m1).toArray(), catalog.sort("price").toArray()); //Int: decrescente
        assertArrayEquals(Arrays.asList(m3,m2,m1).toArray(), Music.sortMusicList(Arrays.asList(m1,m2,m3), "price").toArray()); //Int: decrescente
        assertArrayEquals(Arrays.asList(m4,m2,m1,m3).toArray(), Music.sortMusicList(Arrays.asList(m1,m2,m3,m4), "popularity").toArray()); //Int: decrescente
    }
    
    @Test
    public void aClientCanBuyAMusic() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        client.acquireCredits(credit.getCode());
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(25, client.getCredits());
        assertEquals(0, client.musicCount());
        assertEquals(0, music.getPopularity());
        client.buy(music);
        assertEquals(20, client.getCredits());
        assertEquals(1, client.musicCount());
        assertEquals(1, music.getPopularity());
    }
    
    @Test
    public void aClientCannotBuyTheSameMusicTwice() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(25);
        credits.register(credit);
        client.acquireCredits(credit.getCode());
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(25, client.getCredits());
        assertEquals(0, client.musicCount());
        assertEquals(0, music.getPopularity());
        client.buy(music);
        try {
            client.buy(music);
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Você já possui esta música", e.getMessage());
        }
        assertEquals(20, client.getCredits());
        assertEquals(1, client.musicCount());
        assertEquals(1, music.getPopularity());
    }
    
    @Test
    public void aClientCannotBuyTheAMusicIfItIsHighestThanHisCredits() throws Exception {
        Catalog catalog = Catalog.getInstance();
        Credits credits = Credits.getInstance();
        Credit credit = new Credit(4);
        credits.register(credit);
        client.acquireCredits(credit.getCode());
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(4, client.getCredits());
        assertEquals(0, client.musicCount());
        assertEquals(0, music.getPopularity());
        try {
            client.buy(music);
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("Você não possui créditos suficientes", e.getMessage());
        }
        assertEquals(4, client.getCredits());
        assertEquals(0, client.musicCount());
        assertEquals(0, music.getPopularity());
    }
    
}
