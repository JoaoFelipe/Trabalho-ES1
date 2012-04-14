/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formcomponents;

import java.util.EventListener;

/**
 *
 * @author casa
 */
public interface TableButtonListener extends EventListener {

    public void tableButtonClicked(int row, int col);
}
