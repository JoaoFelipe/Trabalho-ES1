package sessions;

import actions.ChangeMusicAction;
import actions.PublishAction;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import musics.Music;
import formcomponents.PublicationsTableModel;
import formcomponents.DialogWithCatalogInterface;
import formcomponents.DialogWithCreditsInterface;
import formcomponents.HeaderListener;
import dialogs.ProducerFrame;
import dialogs.PublishDialog;
import formcomponents.TableButton;
import formcomponents.TableButtonListener;
import musics.MusicList;
import users.Producer;

public class ProducerSession extends UserSession {
    
    private MusicList publications;
    private String publicationsFilterField = "";
    private String publicationsFilterKeywords = "";
    private String publicationsSortField = "";
    
    public ProducerSession(Producer producer) {
        super(producer);
    }
     
    public Producer getProducer() {
        return (Producer) super.getUser();
    }
    
    public void removeMusic(Music music) {
        try {
            if (JOptionPane.showConfirmDialog(component, "Você realmente deseja remover a música "+music.getName()+"?", "Tchu Tcha Tcha Store", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.getProducer().removeMusic(music);
                this.rebuildMusics();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Tchu Tcha Tcha Store", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reloadPublications() {
        publications = getProducer().getPublications();
        publications = publications.filter(publicationsFilterField, publicationsFilterKeywords);
        publications = publications.sort(publicationsSortField);
        this.buildPublications();
    }
    
    public void filterPublications(String field, String keywords) {
        publicationsFilterField = map.get(field);
        publicationsFilterKeywords = keywords;
        this.rebuildMusics();
    }
    
    public void sortPublications(String field) {
        publicationsSortField = map.get(field);
        publications = publications.sort(publicationsSortField);
        this.buildPublications();
    }
    
    public void buildPublications() {
        final JTable publicationsTable = ((ProducerFrame) component).getPublicationsTable();     
        final DefaultTableModel model = new PublicationsTableModel(publications);
        publicationsTable.setModel(model);
        TableColumn column;
        TableButton button;
        
        column = publicationsTable.getColumnModel().getColumn(6);
        button = new TableButton("Alterar");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                PublishDialog publishDialog = new PublishDialog(component, new ChangeMusicAction((ProducerSession) session, publications.get(row)));
                publishDialog.setVisible(true);
            }
        });
        column.setCellRenderer(new TableButton("Alterar"));
        column.setCellEditor(button);
        
        column = publicationsTable.getColumnModel().getColumn(7);
        button = new TableButton("Remover");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                ((ProducerSession) session).removeMusic(publications.get(row));
                
            }
        });
        column.setCellRenderer(new TableButton("Remover"));
        column.setCellEditor(button);
        
        JTableHeader header = publicationsTable.getTableHeader();
        header.addMouseListener(new HeaderListener(
            publicationsTable.getColumnModel(),
            Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço")
        ) {
            @Override
            public void sort(String column) {
                ((ProducerSession) session).sortPublications(column);
            }
        });
    }
    
    @Override
    public void buildGreetings() {
        ((DialogWithCatalogInterface) component).getGreetings().setText("Olá "+this.getUser().getName()+", você já recebeu");
        ((DialogWithCreditsInterface) component).getCreditsLabel().setText(this.getProducer().getCredits()+" créditos");
    }
    
    public void rebuildMusics() {
        this.reloadPublications();
        this.reloadCatalog();
        this.buildCatalogTable();
    }
    
    public void showPublishDialog() {
        PublishDialog publishDialog = new PublishDialog(component, new PublishAction(this));
        publishDialog.setVisible(true);
    }
    
    @Override
    public void showDialog() {
        ProducerFrame producerFrame = new ProducerFrame(this);
        this.component = producerFrame;
        this.rebuildMusics();
        this.buildGreetings();
        producerFrame.setVisible(true);
    }
    
}
