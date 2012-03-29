/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoginFrame.java
 *
 * Created on 28/03/2012, 20:46:35
 */
package trabalhoes1;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Joao
 */
public class ProdutorFrame extends javax.swing.JFrame {

    /** Creates new form LoginFrame */
    public ProdutorFrame() {
        initComponents();
        
        TableColumn column = listaTable.getColumnModel().getColumn(6);
        TableButton button = new TableButton("Alterar");
        final JFrame self = this;
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                PublicarDialog publicarDialog = new PublicarDialog(self);
                publicarDialog.setVisible(true);
//                JOptionPane.showMessageDialog(null, "Linha: "+row);
            }
        });
        
        column.setCellRenderer(new TableButton("Alterar"));
        column.setCellEditor(button);
        
        
        column = listaTable.getColumnModel().getColumn(7);
        button = new TableButton("Remover");
        button.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                JOptionPane.showMessageDialog(null, "Linha: "+row);
            }
        });
        
        column.setCellRenderer(new TableButton("Remover"));
        column.setCellEditor(button);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        catalogoPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        catalogoTable = new javax.swing.JTable();
        buscarCatalogoPanel = new javax.swing.JPanel();
        buscarCatalogoLabel = new javax.swing.JLabel();
        buscarCatalogoField = new javax.swing.JTextField();
        atributoCatalogoComboBox = new javax.swing.JComboBox();
        buscarCatalogoOKButton = new javax.swing.JButton();
        listaPanel = new javax.swing.JPanel();
        buscarListaPanel = new javax.swing.JPanel();
        buscarListaLabel = new javax.swing.JLabel();
        buscarListaField = new javax.swing.JTextField();
        atributoListaComboBox = new javax.swing.JComboBox();
        buscarListaOKButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        saudacaoLabel = new javax.swing.JLabel();
        creditosLabel = new javax.swing.JLabel();
        publicarButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        SistemaMenu = new javax.swing.JMenu();
        publicarMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        senhaMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        logoutMenuItem = new javax.swing.JMenuItem();
        sairMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(trabalhoes1.TrabalhoES1App.class).getContext().getResourceMap(ProdutorFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        catalogoPanel.setName("catalogoPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        catalogoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        catalogoTable.setName("catalogoTable"); // NOI18N
        catalogoTable.setRowHeight(25);
        catalogoTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(catalogoTable);
        catalogoTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title0")); // NOI18N
        catalogoTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title1")); // NOI18N
        catalogoTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title2")); // NOI18N
        catalogoTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title3")); // NOI18N
        catalogoTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title4")); // NOI18N
        catalogoTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title5")); // NOI18N

        buscarCatalogoPanel.setName("buscarCatalogoPanel"); // NOI18N

        buscarCatalogoLabel.setFont(resourceMap.getFont("buscarCatalogoLabel.font")); // NOI18N
        buscarCatalogoLabel.setText(resourceMap.getString("buscarCatalogoLabel.text")); // NOI18N
        buscarCatalogoLabel.setName("buscarCatalogoLabel"); // NOI18N

        buscarCatalogoField.setFont(resourceMap.getFont("buscarCatalogoField.font")); // NOI18N
        buscarCatalogoField.setText(resourceMap.getString("buscarCatalogoField.text")); // NOI18N
        buscarCatalogoField.setName("buscarCatalogoField"); // NOI18N

        atributoCatalogoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço" }));
        atributoCatalogoComboBox.setName("atributoCatalogoComboBox"); // NOI18N

        buscarCatalogoOKButton.setText(resourceMap.getString("buscarCatalogoOKButton.text")); // NOI18N
        buscarCatalogoOKButton.setName("buscarCatalogoOKButton"); // NOI18N

        javax.swing.GroupLayout buscarCatalogoPanelLayout = new javax.swing.GroupLayout(buscarCatalogoPanel);
        buscarCatalogoPanel.setLayout(buscarCatalogoPanelLayout);
        buscarCatalogoPanelLayout.setHorizontalGroup(
            buscarCatalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarCatalogoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscarCatalogoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarCatalogoField, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(atributoCatalogoComboBox, 0, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarCatalogoOKButton)
                .addContainerGap())
        );
        buscarCatalogoPanelLayout.setVerticalGroup(
            buscarCatalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscarCatalogoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buscarCatalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buscarCatalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(atributoCatalogoComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(buscarCatalogoOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buscarCatalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarCatalogoLabel)
                        .addComponent(buscarCatalogoField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout catalogoPanelLayout = new javax.swing.GroupLayout(catalogoPanel);
        catalogoPanel.setLayout(catalogoPanelLayout);
        catalogoPanelLayout.setHorizontalGroup(
            catalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buscarCatalogoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );
        catalogoPanelLayout.setVerticalGroup(
            catalogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catalogoPanelLayout.createSequentialGroup()
                .addComponent(buscarCatalogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("catalogoPanel.TabConstraints.tabTitle"), catalogoPanel); // NOI18N

        listaPanel.setName("listaPanel"); // NOI18N

        buscarListaPanel.setName("buscarListaPanel"); // NOI18N

        buscarListaLabel.setFont(resourceMap.getFont("buscarListaLabel.font")); // NOI18N
        buscarListaLabel.setText(resourceMap.getString("buscarListaLabel.text")); // NOI18N
        buscarListaLabel.setName("buscarListaLabel"); // NOI18N

        buscarListaField.setFont(resourceMap.getFont("buscarListaField.font")); // NOI18N
        buscarListaField.setText(resourceMap.getString("buscarListaField.text")); // NOI18N
        buscarListaField.setName("buscarListaField"); // NOI18N

        atributoListaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço" }));
        atributoListaComboBox.setName("atributoListaComboBox"); // NOI18N

        buscarListaOKButton.setText(resourceMap.getString("buscarListaOKButton.text")); // NOI18N
        buscarListaOKButton.setName("buscarListaOKButton"); // NOI18N

        javax.swing.GroupLayout buscarListaPanelLayout = new javax.swing.GroupLayout(buscarListaPanel);
        buscarListaPanel.setLayout(buscarListaPanelLayout);
        buscarListaPanelLayout.setHorizontalGroup(
            buscarListaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarListaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscarListaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarListaField, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(atributoListaComboBox, 0, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarListaOKButton)
                .addContainerGap())
        );
        buscarListaPanelLayout.setVerticalGroup(
            buscarListaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscarListaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buscarListaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buscarListaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(atributoListaComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(buscarListaOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buscarListaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarListaLabel)
                        .addComponent(buscarListaField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        listaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Alterar", "Remover"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listaTable.setName("listaTable"); // NOI18N
        listaTable.setRowHeight(25);
        listaTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(listaTable);
        listaTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title0")); // NOI18N
        listaTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title1")); // NOI18N
        listaTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title2")); // NOI18N
        listaTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title3")); // NOI18N
        listaTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("catalogoTable.columnModel.title4")); // NOI18N
        listaTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("listaTable.columnModel.title6")); // NOI18N
        listaTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("listaTable.columnModel.title7")); // NOI18N
        listaTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("listaTable.columnModel.title5")); // NOI18N

        javax.swing.GroupLayout listaPanelLayout = new javax.swing.GroupLayout(listaPanel);
        listaPanel.setLayout(listaPanelLayout);
        listaPanelLayout.setHorizontalGroup(
            listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buscarListaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );
        listaPanelLayout.setVerticalGroup(
            listaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaPanelLayout.createSequentialGroup()
                .addComponent(buscarListaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("listaPanel.TabConstraints.tabTitle"), listaPanel); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        saudacaoLabel.setFont(resourceMap.getFont("saudacaoLabel.font")); // NOI18N
        saudacaoLabel.setText(resourceMap.getString("saudacaoLabel.text")); // NOI18N
        saudacaoLabel.setName("saudacaoLabel"); // NOI18N

        creditosLabel.setFont(resourceMap.getFont("creditosLabel.font")); // NOI18N
        creditosLabel.setForeground(resourceMap.getColor("creditosLabel.foreground")); // NOI18N
        creditosLabel.setText(resourceMap.getString("creditosLabel.text")); // NOI18N
        creditosLabel.setName("creditosLabel"); // NOI18N

        publicarButton.setText(resourceMap.getString("publicarButton.text")); // NOI18N
        publicarButton.setName("publicarButton"); // NOI18N
        publicarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publicarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saudacaoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(creditosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                .addComponent(publicarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(publicarButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saudacaoLabel)
                        .addComponent(creditosLabel)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        SistemaMenu.setText(resourceMap.getString("SistemaMenu.text")); // NOI18N
        SistemaMenu.setName("SistemaMenu"); // NOI18N

        publicarMenuItem.setText(resourceMap.getString("publicarMenuItem.text")); // NOI18N
        publicarMenuItem.setName("publicarMenuItem"); // NOI18N
        publicarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publicarButtonActionPerformed(evt);
            }
        });
        SistemaMenu.add(publicarMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        SistemaMenu.add(jSeparator2);

        senhaMenuItem.setText(resourceMap.getString("senhaMenuItem.text")); // NOI18N
        senhaMenuItem.setName("senhaMenuItem"); // NOI18N
        senhaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaMenuItemActionPerformed(evt);
            }
        });
        SistemaMenu.add(senhaMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        SistemaMenu.add(jSeparator1);

        logoutMenuItem.setText(resourceMap.getString("logoutMenuItem.text")); // NOI18N
        logoutMenuItem.setName("logoutMenuItem"); // NOI18N
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        SistemaMenu.add(logoutMenuItem);

        sairMenuItem.setText(resourceMap.getString("sairMenuItem.text")); // NOI18N
        sairMenuItem.setName("sairMenuItem"); // NOI18N
        sairMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairMenuItemActionPerformed(evt);
            }
        });
        SistemaMenu.add(sairMenuItem);

        jMenuBar1.add(SistemaMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuItemActionPerformed
        // TODO add your handling code here:
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void sairMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairMenuItemActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_sairMenuItemActionPerformed

    private void publicarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publicarButtonActionPerformed
        // TODO add your handling code here:
        PublicarDialog publicarDialog = new PublicarDialog(this);
        publicarDialog.setVisible(true);
    }//GEN-LAST:event_publicarButtonActionPerformed

    private void senhaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaMenuItemActionPerformed
        // TODO add your handling code here:
        AlterarSenhaDialog alterarSenhaDialog = new AlterarSenhaDialog(this);
        alterarSenhaDialog.setVisible(true);
    }//GEN-LAST:event_senhaMenuItemActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProdutorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ProdutorFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu SistemaMenu;
    private javax.swing.JComboBox atributoCatalogoComboBox;
    private javax.swing.JComboBox atributoListaComboBox;
    private javax.swing.JTextField buscarCatalogoField;
    private javax.swing.JLabel buscarCatalogoLabel;
    private javax.swing.JButton buscarCatalogoOKButton;
    private javax.swing.JPanel buscarCatalogoPanel;
    private javax.swing.JTextField buscarListaField;
    private javax.swing.JLabel buscarListaLabel;
    private javax.swing.JButton buscarListaOKButton;
    private javax.swing.JPanel buscarListaPanel;
    private javax.swing.JPanel catalogoPanel;
    private javax.swing.JTable catalogoTable;
    private javax.swing.JLabel creditosLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel listaPanel;
    private javax.swing.JTable listaTable;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JButton publicarButton;
    private javax.swing.JMenuItem publicarMenuItem;
    private javax.swing.JMenuItem sairMenuItem;
    private javax.swing.JLabel saudacaoLabel;
    private javax.swing.JMenuItem senhaMenuItem;
    // End of variables declaration//GEN-END:variables
}
