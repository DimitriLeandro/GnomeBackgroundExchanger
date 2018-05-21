package frames;

import classes.ImagensOnline;
import classes.MainExchanger;
import classes.ManipuladorTxt;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author dimitri leandro
 */
public class MainFrame extends javax.swing.JFrame {

    //DECLARANDO OBJETOS E VARIÁVEIS DA CLASSE
    private MainExchanger objMainExchanger;
    private ImagensOnline objImagensOnline;
    private byte tab;
    private ManipuladorTxt objManipuladorTxt;
    private PanelOnline objPanelOnline;
    private PanelOffline objPanelOffline;

    public MainFrame() {
        //PADRÃO
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icon.png")));

        //INICIANDO OBJETOS E VARIÁVEIS
        tab = 0;
        objImagensOnline = null;
        objMainExchanger = new MainExchanger();
        objManipuladorTxt = new ManipuladorTxt();
        objPanelOnline = new PanelOnline();
        objPanelOffline = new PanelOffline(objManipuladorTxt); //no construtor já vai um obj instânciado aqui no MainFrame mesmo, assim não preciso gastar mais memória criando dois objetos iguaizinhos da classe ManipuladroTxt

        //ADICIONANDO OS PAINEIS NO JTABBEDPANE E O EVENTO DE TROCAR A ABA
        tbbPainelAbas.add("Offline", objPanelOffline);
        tbbPainelAbas.add("Online", objPanelOnline);
        tbbPainelAbas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                tab = (byte) tbbPainelAbas.getSelectedIndex();
            }
        });

        //DESTRUINDO OBJETOS OBJPANELOFFLINE
        //esse eu ja posso destruir, já o objPanelOnline eu vou precisar pra pegar o tema escolhido pelo usuário
        objPanelOffline = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        tbbPainelAbas = new javax.swing.JTabbedPane();
        cmbTempo = new javax.swing.JComboBox<>();
        btnComecar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        cmbInterface = new javax.swing.JComboBox<>();
        btnEsconder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("GnomeBackgroundExchanger"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${name}"), this, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        tbbPainelAbas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbbPainelAbas.setPreferredSize(new java.awt.Dimension(572, 125));

        cmbTempo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trocar a cada 10 segundos", "Trocar a cada 30 segundos", "Trocar a cada 1 minuto", "Trocar a cada 5 minutos", "Trocar a cada 10 minutos", "Trocar a cada 1 hora", "Trocar a cada 5 horas", "Trocar a cada 10 horas" }));

        btnComecar.setText("Começar");
        btnComecar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComecarActionPerformed(evt);
            }
        });

        btnParar.setText("Parar");
        btnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPararActionPerformed(evt);
            }
        });

        cmbInterface.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gnome", "Mate", "Cinnamon" }));

        btnEsconder.setText("Esconder");
        btnEsconder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsconderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnComecar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnParar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEsconder, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbbPainelAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTempo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tbbPainelAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComecar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEsconder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComecarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComecarActionPerformed
        objMainExchanger.limparArrayImagens();
        //CASO O USUÁRIO ESTEJA NA TAB 0 -> OFFLINE
        //CASO TAB 1 -> ONLINE
        switch (tab) {
            case 0:                
                objMainExchanger.setArrayImagens(objManipuladorTxt.lerTxt());
                objMainExchanger.iniciarThread(cmbTempo.getSelectedItem().toString(), cmbInterface.getSelectedItem().toString());
                break;
            case 1:
                try {
                    objImagensOnline = new ImagensOnline(objMainExchanger, objPanelOnline.getTxtTema()); //no construtor vai o objMainExchanger por que lá no objImagensOnline vai ser preciso mecher no arrayImagens do objMainExchanger, e tem que ser o mesmo objeto
                    objImagensOnline.iniciarThreadBaixarImagens();
                    objMainExchanger.iniciarThread(cmbTempo.getSelectedItem().toString(), cmbInterface.getSelectedItem().toString());
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                System.out.println("Algo deu muito errado, reinicie a aplicação!");
                break;
        }
    }//GEN-LAST:event_btnComecarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        objMainExchanger.pararThread();
        if(objImagensOnline != null){
            objImagensOnline.pararThreadBaixarImagens();
        }
    }//GEN-LAST:event_btnPararActionPerformed

    private void btnEsconderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsconderActionPerformed
        //JÁ QUE A APLICAÇÃO VAI SER ESCONDIDA, O USUÁRIO NÃO VAI MAIS CONSEGUIR ABRIR, ENTÃO JÁ POSSO MATAR TODOS OS OBJETOS, MENOS O MAINEXCHANGER
        this.objManipuladorTxt = null;
        this.objPanelOffline = null;
        this.objPanelOnline = null;

        //SUMIU
        this.setVisible(false);
    }//GEN-LAST:event_btnEsconderActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComecar;
    private javax.swing.JButton btnEsconder;
    private javax.swing.JButton btnParar;
    private javax.swing.JComboBox<String> cmbInterface;
    private javax.swing.JComboBox<String> cmbTempo;
    private javax.swing.JTabbedPane tbbPainelAbas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
