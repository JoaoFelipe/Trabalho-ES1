package ui.musics;

import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import ui.actions.ChangeMusicAction;
import ui.dialogs.PublishDialog;
import ui.formcomponents.PublicationsTableModel;
import ui.formcomponents.TableButton;
import ui.formcomponents.TableButtonListener;
import ui.sessions.ProducerSession;
import ui.sessions.Session;
import business.store.Store;

public class PublicationTable extends MusicListTable {

    @Override
    public DefaultTableModel getTableModel() {
        return new PublicationsTableModel(this.getTable());
    }

    @Override
    public void reloadMusicList() {
        setMusicList(Store.getInstance().getCatalog());
    }

    @Override
    public List<String> getCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Alterar", "Remover");
    }
    
    @Override
    public void addButtons() {
        TableColumn column;
        TableButton button;
        
        column = this.getJTable().getColumnModel().getColumn(6);
        button = new TableButton("Alterar");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                ProducerSession session = (ProducerSession) Session.getInstance();
                ChangeMusicAction action = new ChangeMusicAction(session, new ChangeCommand(getMusicList().get(row)));
                PublishDialog publishDialog = new PublishDialog(getComponent(), action);
                publishDialog.setVisible(true);
            }
        });
        column.setCellRenderer(new TableButton("Alterar"));
        column.setCellEditor(button);
        
        column = this.getJTable().getColumnModel().getColumn(7);
        button = new TableButton("Remover");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                ProducerSession session = (ProducerSession) Session.getInstance();
                session.removeMusic(new RemoveCommand(getMusicList().get(row)));
            }
        });
        column.setCellRenderer(new TableButton("Remover"));
        column.setCellEditor(button);
    }
    
    @Override
    public List<String> getSortableCollumns() {
        return Arrays.asList("Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço");
    }
    
    
    
}
