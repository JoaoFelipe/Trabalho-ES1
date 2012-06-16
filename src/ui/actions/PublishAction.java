package ui.actions;

import javax.swing.JOptionPane;
import ui.sessions.ProducerSession;
import business.store.Store;

public class PublishAction extends MusicInformationAction {
    
    public PublishAction(ProducerSession session) {
        super(session);
    }

    public void execute(String name, String genre, String album, String artist, String price) {
        try {
            Store store = Store.getInstance();
            store.publishMusic(name, genre, album, artist, price);
            getSession().rebuildMusics();
            getPublishDialog().dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getPublishDialog(), e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getGenre() {
        return "";
    }

    @Override
    public String getAlbum() {
        return "";
    }

    @Override
    public String getArtist() {
        return "";
    }

    @Override
    public String getPrice() {
        return "";
    }
    
}
