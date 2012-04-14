/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import sessions.ProducerSession;

/**
 *
 * @author Joao
 */
public class PublishAction extends MusicInformationAction {
    
    public PublishAction(ProducerSession session) {
        super(session);
    }

    public void execute(String name, String genre, String album, String artist, String price) {
        try {
            session.getProducer().publish(name, genre, album, artist, price);
            session.rebuildMusics();
            publishDialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(publishDialog, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
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
