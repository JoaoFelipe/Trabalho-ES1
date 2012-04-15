package actions;

import javax.swing.JOptionPane;
import musics.Music;
import sessions.ProducerSession;

public class ChangeMusicAction extends MusicInformationAction {
    
    Music music;
    
    public ChangeMusicAction(ProducerSession session, Music music) {
        super(session);
        this.music = music;
    }

    public void execute(String name, String genre, String album, String artist, String price) {
        try {
            session.getProducer().changeMusic(music, name, genre, album, artist, price);
            session.rebuildMusics();
            publishDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(publishDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String getName() {
        return music.getName();
    }

    @Override
    public String getGenre() {
        return music.getGenre();
    }

    @Override
    public String getAlbum() {
        return music.getAlbum();
    }

    @Override
    public String getArtist() {
        return music.getArtist();
    }

    @Override
    public String getPrice() {
        return music.getPrice()+"";
    }
    
}
