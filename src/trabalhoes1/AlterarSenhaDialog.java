/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastroFrame.java
 *
 * Created on 28/03/2012, 21:33:16
 */
package trabalhoes1;

/**
 *
 * @author Joao
 */
public class AlterarSenhaDialog extends javax.swing.JDialog {

    /** Creates new form CadastroFrame */
    public AlterarSenhaDialog(java.awt.Frame parent) {
        super(parent);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        antigaLabel = new javax.swing.JLabel();
        antigaField = new javax.swing.JPasswordField();
        senhaLabel = new javax.swing.JLabel();
        senhaField = new javax.swing.JPasswordField();
        repetirLabel = new javax.swing.JLabel();
        repetirField = new javax.swing.JPasswordField();
        alterarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(trabalhoes1.TrabalhoES1App.class).getContext().getResourceMap(AlterarSenhaDialog.class);
        antigaLabel.setText(resourceMap.getString("antigaLabel.text")); // NOI18N
        antigaLabel.setName("antigaLabel"); // NOI18N

        antigaField.setName("antigaField"); // NOI18N

        senhaLabel.setText(resourceMap.getString("senhaLabel.text")); // NOI18N
        senhaLabel.setName("senhaLabel"); // NOI18N

        senhaField.setText(resourceMap.getString("senhaField.text")); // NOI18N
        senhaField.setName("senhaField"); // NOI18N

        repetirLabel.setText(resourceMap.getString("repetirLabel.text")); // NOI18N
        repetirLabel.setName("repetirLabel"); // NOI18N

        repetirField.setText(resourceMap.getString("repetirField.text")); // NOI18N
        repetirField.setName("repetirField"); // NOI18N

        alterarButton.setText(resourceMap.getString("alterarButton.text")); // NOI18N
        alterarButton.setName("alterarButton"); // NOI18N

        cancelarButton.setText(resourceMap.getString("cancelarButton.text")); // NOI18N
        cancelarButton.setName("cancelarButton"); // NOI18N
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(antigaLabel)
                        .addGap(27, 27, 27)
                        .addComponent(antigaField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(repetirLabel)
                            .addComponent(senhaLabel))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(senhaField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(repetirField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(alterarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(antigaLabel)
                    .addComponent(antigaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaLabel)
                    .addComponent(senhaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repetirLabel)
                    .addComponent(repetirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelarButton)
                    .addComponent(alterarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarButton;
    private javax.swing.JTextField antigaField;
    private javax.swing.JLabel antigaLabel;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField repetirField;
    private javax.swing.JLabel repetirLabel;
    private javax.swing.JTextField senhaField;
    private javax.swing.JLabel senhaLabel;
    // End of variables declaration//GEN-END:variables
}