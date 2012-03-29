/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoes1;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellEditor;

/**
 *
 * @author casa
 */
public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor{
    
//
//  Instance Variables
//

    /** The Swing component being edited. */
    protected JComponent editorComponent;
    /**
     * The delegate class which handles all methods sent from the
     * <code>CellEditor</code>.
     */
    protected EditorDelegate delegate;
    /**
     * An integer specifying the number of clicks needed to start editing.
     * Even if <code>clickCountToStart</code> is defined as zero, it
     * will not initiate until a click occurs.
     */
    protected int clickCountToStart = 1;

//
//  Constructors
//

    /**
     * Constructs a <code>ButtonCellEditor</code> object that uses a
     * JButton
     *
     * @param button  a <code>JComboBox</code> object
     */
    public ButtonCellEditor(final JButton button) {
        editorComponent = button;
//        button.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        delegate = new EditorDelegate() {
            public void setValue(Object value) {
//                button.setText((String) value);
            }

            public Object getCellEditorValue() {
                return button.getText();
            }

            public boolean shouldSelectCell(EventObject anEvent) {
                if (anEvent instanceof MouseEvent) {
                    MouseEvent e = (MouseEvent)anEvent;
                    return e.getID() != MouseEvent.MOUSE_DRAGGED;
                }
                return false;
            }
            public boolean stopCellEditing() {
//                if (button.isEditable()) {
                    // Commit edited value.
//                    button.actionPerformed(new ActionEvent(
//                                     DefaultCellEditor.this, 0, ""));
//                }
                return super.stopCellEditing();
            }
        };
//        button.addActionListener(delegate);
    }

    /**
     * Returns a reference to the editor component.
     *
     * @return the editor <code>Component</code>
     */
    public Component getComponent() {
        return editorComponent;
    }

//
//  Modifying
//

    /**
     * Specifies the number of clicks needed to start editing.
     *
     * @param count  an int specifying the number of clicks needed to start editing
     * @see #getClickCountToStart
     */
    public void setClickCountToStart(int count) {
        clickCountToStart = count;
    }

    /**
     * Returns the number of clicks needed to start editing.
     * @return the number of clicks needed to start editing
     */
    public int getClickCountToStart() {
        return clickCountToStart;
    }

//
//  Override the implementations of the superclass, forwarding all methods
//  from the CellEditor interface to our delegate.
//

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#getCellEditorValue
     */
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#isCellEditable(EventObject)
     */
    public boolean isCellEditable(EventObject anEvent) {
        return delegate.isCellEditable(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#shouldSelectCell(EventObject)
     */
    public boolean shouldSelectCell(EventObject anEvent) {
        return delegate.shouldSelectCell(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#stopCellEditing
     */
    public boolean stopCellEditing() {
        return delegate.stopCellEditing();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#cancelCellEditing
     */
    public void cancelCellEditing() {
        delegate.cancelCellEditing();
    }

//
//  Implementing the TreeCellEditor Interface
//

    /** Implements the <code>TreeCellEditor</code> interface. */
    public Component getTreeCellEditorComponent(JTree tree, Object value,
                                                boolean isSelected,
                                                boolean expanded,
                                                boolean leaf, int row) {
        String         stringValue = tree.convertValueToText(value, isSelected,
                                            expanded, leaf, row, false);

        delegate.setValue(stringValue);
        return editorComponent;
    }

//
//  Implementing the CellEditor Interface
//
    /** Implements the <code>TableCellEditor</code> interface. */
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {
        delegate.setValue(value);
//        if (editorComponent instanceof JButton) {
//            //in order to avoid a "flashing" effect when clicking a checkbox
//            //in a table, it is important for the editor to have as a border
//            //the same border that the renderer has, and have as the background
//            //the same color as the renderer has. This is primarily only
//            //needed for JCheckBox since this editor doesn't fill all the
//            //visual space of the table cell, unlike a text field.
//            TableCellRenderer renderer = table.getCellRenderer(row, column);
//            Component c = renderer.getTableCellRendererComponent(table, value,
//                    isSelected, true, row, column);
//            if (c != null) {
//                editorComponent.setOpaque(true);
//                editorComponent.setBackground(c.getBackground());
//                if (c instanceof JComponent) {
//                    editorComponent.setBorder(((JComponent)c).getBorder());
//                }
//            } else {
//                editorComponent.setOpaque(false);
//            }
//        }
        return editorComponent;
    }


//
//  Protected EditorDelegate class
//

    /**
     * The protected <code>EditorDelegate</code> class.
     */
    protected class EditorDelegate implements ActionListener, ItemListener, Serializable {

        /**  The value of this cell. */
        protected Object value;

       /**
        * Returns the value of this cell.
        * @return the value of this cell
        */
        public Object getCellEditorValue() {
            return value;
        }

       /**
        * Sets the value of this cell.
        * @param value the new value of this cell
        */
        public void setValue(Object value) {
            this.value = value;
        }

       /**
        * Returns true if <code>anEvent</code> is <b>not</b> a
        * <code>MouseEvent</code>.  Otherwise, it returns true
        * if the necessary number of clicks have occurred, and
        * returns false otherwise.
        *
        * @param   anEvent         the event
        * @return  true  if cell is ready for editing, false otherwise
        * @see #setClickCountToStart
        * @see #shouldSelectCell
        */
        public boolean isCellEditable(EventObject anEvent) {
            if (anEvent instanceof MouseEvent) {
                return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;
            }
            return true;
        }

       /**
        * Returns true to indicate that the editing cell may
        * be selected.
        *
        * @param   anEvent         the event
        * @return  true
        * @see #isCellEditable
        */
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

       /**
        * Returns true to indicate that editing has begun.
        *
        * @param anEvent          the event
        */
        public boolean startCellEditing(EventObject anEvent) {
            return true;
        }

       /**
        * Stops editing and
        * returns true to indicate that editing has stopped.
        * This method calls <code>fireEditingStopped</code>.
        *
        * @return  true
        */
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

       /**
        * Cancels editing.  This method calls <code>fireEditingCanceled</code>.
        */
       public void cancelCellEditing() {
           fireEditingCanceled();
       }

       /**
        * When an action is performed, editing is ended.
        * @param e the action event
        * @see #stopCellEditing
        */
        public void actionPerformed(ActionEvent e) {
            ButtonCellEditor.this.stopCellEditing();
        }

       /**
        * When an item's state changes, editing is ended.
        * @param e the action event
        * @see #stopCellEditing
        */
        public void itemStateChanged(ItemEvent e) {
            ButtonCellEditor.this.stopCellEditing();
        }
    }
}
