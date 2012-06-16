package ui.actions;

import javax.swing.JDialog;
import ui.sessions.ProducerSession;

public abstract class MusicInformationAction {
    
    private ProducerSession session;
    private JDialog publishDialog;
    
    public MusicInformationAction(ProducerSession session) {
        this.session = session;
    }

    public void setPublishDialog(JDialog dialog) {
        this.publishDialog = dialog;
    }
    
    public abstract void execute(String name, String genre, String album, String artist, String price);
    
    public abstract String getName();
    public abstract String getGenre();
    public abstract String getAlbum();
    public abstract String getArtist();
    public abstract String getPrice();

    public ProducerSession getSession() {
        return session;
    }

    public JDialog getPublishDialog() {
        return publishDialog;
    }
    
}
