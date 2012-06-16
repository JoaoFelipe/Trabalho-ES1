package ui.actions;

import javax.swing.JOptionPane;
import ui.sessions.ProducerSession;
import ui.musics.ChangeCommand;

public class ChangeMusicAction extends MusicInformationAction {
    
    private ChangeCommand command;
    
    public ChangeMusicAction(ProducerSession session, ChangeCommand command) {
        super(session);
        this.command = command;
    }

    public void execute(String name, String genre, String album, String artist, String price) {
        try {
            command.execute(name, genre, album, artist, price);
            getSession().rebuildMusics();
            getPublishDialog().dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getPublishDialog(), e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String getName() {
        return command.getMusicName();
    }

    @Override
    public String getGenre() {
        return command.getMusicGenre();
    }

    @Override
    public String getAlbum() {
        return command.getMusicAlbum();
    }

    @Override
    public String getArtist() {
        return command.getMusicArtist();
    }

    @Override
    public String getPrice() {
        return command.getMusicPrice();
    }
    
}
