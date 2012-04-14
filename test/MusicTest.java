import sessions.Session;
import musics.Catalog;
import musics.Music;
import credits.Credits;
import users.Producer;
import java.util.List;
import java.util.Arrays;
import users.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sessions.UserSession;
import static org.junit.Assert.*;

public class MusicTest {
    
    Catalog catalog;
    
    Producer producer;
    Producer producer2;
    
    @Before
    public void setUp() throws Exception {
        catalog = Catalog.getInstance();
        producer = new Producer("Ana", "ana@email.com", "aninha", "123456", "123456");
        producer2 = new Producer("Bia", "bia@email.com", "bia", "123456", "123456");
    }
    
    @After
    public void tearDown() {
        Users.eraseInstance();
        Credits.eraseInstance();
        Catalog.eraseInstance();
        Session.eraseInstance();
    }
        
    @Test
    public void testCreateAMusic() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals("Sk8er Boi", music.getName());
        assertEquals("Pop Rock", music.getGenre());
        assertEquals("Let Go", music.getAlbum());
        assertEquals("Avril Lavigne", music.getArtist());
        assertEquals(5, music.getPrice());
        assertEquals(0, music.getPopularity());
    }
    
    @Test
    public void testCantCreateMusicWithEmptyField() throws Exception {
        for (int i = 0; i < 5; i++) {
            List<String> param = Arrays.asList("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
            param.set(i, "");
            try {
                Music music = new Music(producer, param.get(0), param.get(1), param.get(2), param.get(3), param.get(4));
                assertFalse(true);
            } catch (Exception e) {
                assertEquals("Todos os atributos devem estar preenchidos", e.getMessage());
            }
        }
    }
    
    @Test
    public void testCantCreateMusicWithInvalidPrice() throws Exception {
        try {
            Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "a");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O preço é inválido", e.getMessage());
        }
    }
    
    @Test
    public void testCantCreateMusicWithNegativePrice() throws Exception {
        try {
            Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "a");
            assertFalse(true);
        } catch (Exception e) {
            assertEquals("O preço é inválido", e.getMessage());
        }
    }

    @Test
    public void testPublishMusicInCatalog() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.count());
        assertTrue(catalog.publish(music));
        assertEquals(1, catalog.count());
    }
    
    @Test
    public void testIncreaseMusicPopularity() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, music.getPopularity());
        music.increasePopularity();
        assertEquals(1, music.getPopularity());
    }
    
    @Test
    public void testChangeMusicInformations() throws Exception {
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
    public void testRemoveMusicFromCatalog() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.count());
        assertTrue(catalog.publish(music));
        assertEquals(1, catalog.count());
        assertTrue(catalog.remove(music));
        assertEquals(0, catalog.count());
    }
    
    @Test
    public void testCantRemoveMusicTwice() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.count());
        assertTrue(catalog.publish(music));
        assertEquals(1, catalog.count());
        assertTrue(catalog.remove(music));
        assertFalse(catalog.remove(music));
        assertEquals(0, catalog.count());
        
    }
    
    @Test
    public void testProducersCanPublishMusics() throws Exception {
        assertEquals(0, catalog.count());
        assertEquals(0, producer.publicationsCount());
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(producer, music.getProducer());
        assertEquals(1, producer.publicationsCount());
        assertEquals(1, catalog.count());
    }
    
    @Test
    public void testProducersCanChangeInformationsFromMusicsThatHePublished() throws Exception {
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
    public void testProducersCanRemoveMusicsThatHePublished() throws Exception {
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
    
}
