package frames;

import classes.ManipuladorTxt;

/**
 *
 * @author dimi
 */
public class PanelOffline extends javax.swing.JPanel {

    private ManipuladorTxt objManipuladorTxt;
    
    public PanelOffline(ManipuladorTxt objManipuladorTxt) { //AQUI EU JA RECEBO O OBJETO INSTANCIADO DA CLASSE MAINFRAME PRA NÃO PRECISAR GASTAR MAIS MEMÓRIA COM DOIS OBJETOS IGUAIS
        initComponents();
        this.objManipuladorTxt = objManipuladorTxt;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(327, 100));

        btnAdd.setText("Selecionar Imagens");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 127, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        objManipuladorTxt.abrirTxt();
    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    // End of variables declaration//GEN-END:variables
}
