import sessions.Session;
import store.Store;
import musics.Music;
import users.Producer;
import java.util.List;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MusicTest {
    
    Store catalog;
    
    Producer producer;
    Producer producer2;
    
    @Before
    public void setUp() throws Exception {
        catalog = Store.getInstance();
        producer = new Producer("Ana", "ana@email.com", "aninha", "123456", "123456");
        producer2 = new Producer("Bia", "bia@email.com", "bia", "123456", "123456");
    }
    
    @After
    public void tearDown() {
        Store.eraseInstance();
        Store.eraseInstance();
        Store.eraseInstance();
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
        assertEquals(0, catalog.getMusics().size());
        assertTrue(catalog.publishMusic(music));
        assertEquals(1, catalog.getMusics().size());
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
        assertEquals(0, catalog.getMusics().size());
        assertTrue(catalog.publishMusic(music));
        assertEquals(1, catalog.getMusics().size());
        assertTrue(catalog.removeMusic(music));
        assertEquals(0, catalog.getMusics().size());
    }
    
    @Test
    public void testCantRemoveMusicTwice() throws Exception {
        Music music = new Music(producer, "Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(0, catalog.getMusics().size());
        assertTrue(catalog.publishMusic(music));
        assertEquals(1, catalog.getMusics().size());
        assertTrue(catalog.removeMusic(music));
        assertFalse(catalog.removeMusic(music));
        assertEquals(0, catalog.getMusics().size());
        
    }
    
    @Test
    public void testProducersCanPublishMusics() throws Exception {
        assertEquals(0, catalog.getMusics().size());
        assertEquals(0, producer.getPublications().size());
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(producer, music.getProducer());
        assertEquals(1, producer.getPublications().size());
        assertEquals(1, catalog.getMusics().size());
    }
    
    @Test
    public void testProducersCanChangeInformationsFromMusicsThatHePublished() throws Exception {
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(1, catalog.getMusics().size());
        assertEquals("Sk8er Boi", music.getName());
        producer.changeMusic(music, "Skater Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals("Skater Boi", music.getName());
    }
    
    @Test
    public void testProducersCanRemoveMusicsThatHePublished() throws Exception {
        Music music = producer.publish("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals(1, catalog.getMusics().size());
        producer.removeMusic(music);
        assertEquals(0, catalog.getMusics().size());
    }
    
}
