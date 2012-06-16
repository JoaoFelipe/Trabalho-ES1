package ui.dialogs;

import ui.formcomponents.DialogWithCreditsInterface;
import ui.formcomponents.DialogWithCatalogInterface;
import ui.sessions.ClientSession;

public class ClientFrame extends javax.swing.JFrame implements DialogWithCatalogInterface, DialogWithCreditsInterface {

    private ClientSession session;
    
    /** Creates new form ClientFrame */
    public ClientFrame(ClientSession session) {
        initComponents();
        this.session = session;
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
        catalogPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        catalogTable = new javax.swing.JTable();
        catalogSearchPanel = new javax.swing.JPanel();
        catalogSearchLabel = new javax.swing.JLabel();
        catalogSearchField = new javax.swing.JTextField();
        catalogFieldComboBox = new javax.swing.JComboBox();
        catalogSearchOKButton = new javax.swing.JButton();
        myMusicPanel = new javax.swing.JPanel();
        myMusicSearchPanel = new javax.swing.JPanel();
        myMusicSearchLabel = new javax.swing.JLabel();
        myMusicSearchField = new javax.swing.JTextField();
        myMusicFieldComboBox = new javax.swing.JComboBox();
        myMusicSearchOKButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        myMusicTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        greetingsLabel = new javax.swing.JLabel();
        creditosLabel = new javax.swing.JLabel();
        acquireCreditsButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        systemMenu = new javax.swing.JMenu();
        acquireCreditsMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        changePasswordMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        logoutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ui.dialogs.TrabalhoES1App.class).getContext().getResourceMap(ClientFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        catalogPanel.setName("catalogPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        catalogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço", "Comprar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        catalogTable.setName("catalogTable"); // NOI18N
        catalogTable.setRowHeight(25);
        catalogTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        catalogTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(catalogTable);

        catalogSearchPanel.setName("catalogSearchPanel"); // NOI18N

        catalogSearchLabel.setFont(resourceMap.getFont("catalogSearchLabel.font")); // NOI18N
        catalogSearchLabel.setText(resourceMap.getString("catalogSearchLabel.text")); // NOI18N
        catalogSearchLabel.setName("catalogSearchLabel"); // NOI18N

        catalogSearchField.setName("catalogSearchField"); // NOI18N

        catalogFieldComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade", "Preço" }));
        catalogFieldComboBox.setName("catalogFieldComboBox"); // NOI18N

        catalogSearchOKButton.setText(resourceMap.getString("catalogSearchOKButton.text")); // NOI18N
        catalogSearchOKButton.setName("catalogSearchOKButton"); // NOI18N
        catalogSearchOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalogSearchOKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout catalogSearchPanelLayout = new javax.swing.GroupLayout(catalogSearchPanel);
        catalogSearchPanel.setLayout(catalogSearchPanelLayout);
        catalogSearchPanelLayout.setHorizontalGroup(
            catalogSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catalogSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(catalogSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(catalogSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(catalogFieldComboBox, 0, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(catalogSearchOKButton)
                .addContainerGap())
        );
        catalogSearchPanelLayout.setVerticalGroup(
            catalogSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, catalogSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(catalogSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, catalogSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(catalogFieldComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(catalogSearchOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, catalogSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(catalogSearchLabel)
                        .addComponent(catalogSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout catalogPanelLayout = new javax.swing.GroupLayout(catalogPanel);
        catalogPanel.setLayout(catalogPanelLayout);
        catalogPanelLayout.setHorizontalGroup(
            catalogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(catalogSearchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        catalogPanelLayout.setVerticalGroup(
            catalogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catalogPanelLayout.createSequentialGroup()
                .addComponent(catalogSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Catálogo", catalogPanel);

        myMusicPanel.setName("myMusicPanel"); // NOI18N

        myMusicSearchPanel.setName("myMusicSearchPanel"); // NOI18N

        myMusicSearchLabel.setFont(resourceMap.getFont("myMusicSearchLabel.font")); // NOI18N
        myMusicSearchLabel.setText(resourceMap.getString("myMusicSearchLabel.text")); // NOI18N
        myMusicSearchLabel.setName("myMusicSearchLabel"); // NOI18N

        myMusicSearchField.setName("myMusicSearchField"); // NOI18N

        myMusicFieldComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade" }));
        myMusicFieldComboBox.setName("myMusicFieldComboBox"); // NOI18N

        myMusicSearchOKButton.setText(resourceMap.getString("myMusicSearchOKButton.text")); // NOI18N
        myMusicSearchOKButton.setName("myMusicSearchOKButton"); // NOI18N
        myMusicSearchOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myMusicSearchOKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myMusicSearchPanelLayout = new javax.swing.GroupLayout(myMusicSearchPanel);
        myMusicSearchPanel.setLayout(myMusicSearchPanelLayout);
        myMusicSearchPanelLayout.setHorizontalGroup(
            myMusicSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myMusicSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myMusicSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myMusicSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myMusicFieldComboBox, 0, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myMusicSearchOKButton)
                .addContainerGap())
        );
        myMusicSearchPanelLayout.setVerticalGroup(
            myMusicSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myMusicSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(myMusicSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myMusicSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(myMusicFieldComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(myMusicSearchOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myMusicSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(myMusicSearchLabel)
                        .addComponent(myMusicSearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        myMusicTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Gênero", "Álbum", "Artista/Banda", "Popularidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        myMusicTable.setName("myMusicTable"); // NOI18N
        myMusicTable.setRowHeight(25);
        myMusicTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(myMusicTable);

        javax.swing.GroupLayout myMusicPanelLayout = new javax.swing.GroupLayout(myMusicPanel);
        myMusicPanel.setLayout(myMusicPanelLayout);
        myMusicPanelLayout.setHorizontalGroup(
            myMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myMusicSearchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        myMusicPanelLayout.setVerticalGroup(
            myMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myMusicPanelLayout.createSequentialGroup()
                .addComponent(myMusicSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Minha Lista", myMusicPanel);

        jPanel3.setName("jPanel3"); // NOI18N

        greetingsLabel.setFont(resourceMap.getFont("greetingsLabel.font")); // NOI18N
        greetingsLabel.setText(resourceMap.getString("greetingsLabel.text")); // NOI18N
        greetingsLabel.setName("greetingsLabel"); // NOI18N

        creditosLabel.setFont(resourceMap.getFont("creditosLabel.font")); // NOI18N
        creditosLabel.setForeground(resourceMap.getColor("creditosLabel.foreground")); // NOI18N
        creditosLabel.setText(resourceMap.getString("creditosLabel.text")); // NOI18N
        creditosLabel.setName("creditosLabel"); // NOI18N

        acquireCreditsButton.setText(resourceMap.getString("acquireCreditsButton.text")); // NOI18N
        acquireCreditsButton.setName("acquireCreditsButton"); // NOI18N
        acquireCreditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquireCreditsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(greetingsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(creditosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                .addComponent(acquireCreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acquireCreditsButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(greetingsLabel)
                        .addComponent(creditosLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        systemMenu.setText(resourceMap.getString("systemMenu.text")); // NOI18N
        systemMenu.setName("systemMenu"); // NOI18N

        acquireCreditsMenuItem.setText(resourceMap.getString("acquireCreditsMenuItem.text")); // NOI18N
        acquireCreditsMenuItem.setName("acquireCreditsMenuItem"); // NOI18N
        acquireCreditsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acquireCreditsButtonActionPerformed(evt);
            }
        });
        systemMenu.add(acquireCreditsMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        systemMenu.add(jSeparator2);

        changePasswordMenuItem.setText(resourceMap.getString("changePasswordMenuItem.text")); // NOI18N
        changePasswordMenuItem.setName("changePasswordMenuItem"); // NOI18N
        changePasswordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordMenuItemActionPerformed(evt);
            }
        });
        systemMenu.add(changePasswordMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        systemMenu.add(jSeparator1);

        logoutMenuItem.setText(resourceMap.getString("logoutMenuItem.text")); // NOI18N
        logoutMenuItem.setName("logoutMenuItem"); // NOI18N
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        systemMenu.add(logoutMenuItem);

        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        systemMenu.add(exitMenuItem);

        jMenuBar1.add(systemMenu);

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
        session.logout().showDialog();
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void acquireCreditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquireCreditsButtonActionPerformed
        session.showAcquireCreditsDialog();
    }//GEN-LAST:event_acquireCreditsButtonActionPerformed

    private void changePasswordMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordMenuItemActionPerformed
        session.showChangePasswordDialog();
    }//GEN-LAST:event_changePasswordMenuItemActionPerformed

    private void catalogSearchOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalogSearchOKButtonActionPerformed
        session.filterCatalog(catalogFieldComboBox.getSelectedItem().toString(), catalogSearchField.getText());
    }//GEN-LAST:event_catalogSearchOKButtonActionPerformed

    private void myMusicSearchOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myMusicSearchOKButtonActionPerformed
        session.filterMyMusic(myMusicFieldComboBox.getSelectedItem().toString(), myMusicSearchField.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_myMusicSearchOKButtonActionPerformed

    public javax.swing.JTable getMyMusicTable() {
        return myMusicTable;
    }
    
    public javax.swing.JTable getCatalogTable() {
        return catalogTable;
    }
    
    public javax.swing.JLabel getGreetings() {
        return greetingsLabel;
    }
    
    public javax.swing.JLabel getCreditsLabel() {
        return creditosLabel;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acquireCreditsButton;
    private javax.swing.JMenuItem acquireCreditsMenuItem;
    private javax.swing.JComboBox catalogFieldComboBox;
    private javax.swing.JPanel catalogPanel;
    private javax.swing.JTextField catalogSearchField;
    private javax.swing.JLabel catalogSearchLabel;
    private javax.swing.JButton catalogSearchOKButton;
    private javax.swing.JPanel catalogSearchPanel;
    private javax.swing.JTable catalogTable;
    private javax.swing.JMenuItem changePasswordMenuItem;
    private javax.swing.JLabel creditosLabel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel greetingsLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JComboBox myMusicFieldComboBox;
    private javax.swing.JPanel myMusicPanel;
    private javax.swing.JTextField myMusicSearchField;
    private javax.swing.JLabel myMusicSearchLabel;
    private javax.swing.JButton myMusicSearchOKButton;
    private javax.swing.JPanel myMusicSearchPanel;
    private javax.swing.JTable myMusicTable;
    private javax.swing.JMenu systemMenu;
    // End of variables declaration//GEN-END:variables
}