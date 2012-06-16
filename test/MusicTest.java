import ui.sessions.Session;
import business.store.Store;
import business.musics.Music;
import business.users.Producer;
import java.util.List;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MusicTest {
    
    Store store;
    
    Producer producer;
    Producer producer2;
    
    @Before
    public void setUp() throws Exception {
        store = Store.getInstance();
        store.signUpProducer("Ana", "ana@email.com", "ana", "123456", "123456");
        store.signUpProducer("Bia", "bia@email.com", "bia", "123456", "123456");
        producer = (Producer) store.findByLogin("ana");
        producer2 = (Producer) store.findByLogin("bia");
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
        store.login("ana", "123456");
        assertEquals(0, store.getCatalog().size());
        assertNotNull(store.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5"));
        assertEquals(1, store.getCatalog().size());
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
        store.login("ana", "123456");
        assertEquals(0, store.getCatalog().size());
        assertNotNull(store.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5"));
        Music music = store.getCatalog().get(store.getCatalog().size()-1);
        assertEquals(1, store.getCatalog().size());
        store.removeMusic(music);
        assertEquals(0, store.getCatalog().size());
    }
    

    @Test
    public void testProducersCanPublishMusics() throws Exception {
        store.login("ana", "123456");
        assertEquals(0, store.getCatalog().size());
        assertEquals(0, producer.getPublications().size());
        assertNotNull(store.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5"));
        Music music = store.getCatalog().get(store.getCatalog().size()-1);
        assertEquals(producer, music.getProducer());
        assertEquals(1, producer.getPublications().size());
        assertEquals(1, store.getCatalog().size());
    }
    
    @Test
    public void testProducersCanChangeInformationsFromMusicsThatHePublished() throws Exception {
        store.login("ana", "123456");
        assertNotNull(store.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5"));
        Music music = store.getCatalog().get(store.getCatalog().size()-1);
        assertEquals(1, store.getCatalog().size());
        assertEquals("Sk8er Boi", music.getName());
        store.changeMusic(music, "Skater Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5");
        assertEquals("Skater Boi", music.getName());
    }
    
    @Test
    public void testProducersCanRemoveMusicsThatHePublished() throws Exception {
        store.login("ana", "123456");
        assertNotNull(store.publishMusic("Sk8er Boi", "Pop Rock", "Let Go", "Avril Lavigne", "5"));
        Music music = store.getCatalog().get(store.getCatalog().size()-1);
        assertEquals(1, store.getCatalog().size());
        store.removeMusic(music);
        assertEquals(0, store.getCatalog().size());
    }
    
}
