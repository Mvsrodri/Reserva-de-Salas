package View;

import Bean.Administrador;
import Dao.AdministradorDAO;
import Dao.LoginDAO;
import java.sql.*;
import Connection.ConnectionFactory;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JOptionPane;


public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    
    public TelaPrincipal() {
        initComponents();
        //Verificação qual tipo de permissão possui o servidor
        if(TelaLogin.permissao.equals("Funcionário")){
            //Travando botão de gerenciar funcionários se a permissão for funcionário
            btnFuncionario.setEnabled(false);
        }  
        conexao = ConnectionFactory.conectar();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnBackup = new javax.swing.JButton();
        btnFuncionario = new javax.swing.JButton();
        btnReserva = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnAmbiente = new javax.swing.JButton();
        txtNome = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setName("FramePrincipal"); // NOI18N
        setUndecorated(true);

        lblImage.setToolTipText("");

        jPanel1.setEnabled(false);
        jPanel1.setLayout(null);

        btnBackup.setText("BACKUP");
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });
        jPanel1.add(btnBackup);
        btnBackup.setBounds(50, 20, 108, 23);

        btnFuncionario.setText("MANTER FUNCIONÁRIOS");
        btnFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnFuncionario);
        btnFuncionario.setBounds(50, 130, 340, 40);

        btnReserva.setText("REALIZAR RESERVA");
        btnReserva.setMaximumSize(new java.awt.Dimension(105, 23));
        btnReserva.setMinimumSize(new java.awt.Dimension(105, 23));
        btnReserva.setPreferredSize(new java.awt.Dimension(105, 23));
        btnReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaActionPerformed(evt);
            }
        });
        jPanel1.add(btnReserva);
        btnReserva.setBounds(50, 230, 340, 40);

        btnImprimir.setText("REALIZAR IMPRESSÃO");
        btnImprimir.setMaximumSize(new java.awt.Dimension(105, 23));
        btnImprimir.setMinimumSize(new java.awt.Dimension(105, 23));
        btnImprimir.setPreferredSize(new java.awt.Dimension(105, 23));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir);
        btnImprimir.setBounds(50, 280, 340, 40);

        btnAmbiente.setText("MANTER AMBIENTES");
        btnAmbiente.setMaximumSize(new java.awt.Dimension(105, 23));
        btnAmbiente.setMinimumSize(new java.awt.Dimension(105, 23));
        btnAmbiente.setPreferredSize(new java.awt.Dimension(105, 23));
        btnAmbiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnAmbiente);
        btnAmbiente.setBounds(50, 180, 340, 40);

        txtNome.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtNome.setText(TelaLogin.userNome);
        txtNome.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txtNomeComponentShown(evt);
            }
        });
        jPanel1.add(txtNome);
        txtNome.setBounds(260, 20, 133, 19);

        btnReturn.setText("TROCAR USÁRIO");
        btnReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReturn.setMaximumSize(new java.awt.Dimension(105, 23));
        btnReturn.setMinimumSize(new java.awt.Dimension(105, 23));
        btnReturn.setName("btnReturn"); // NOI18N
        btnReturn.setPreferredSize(new java.awt.Dimension(105, 23));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        jPanel1.add(btnReturn);
        btnReturn.setBounds(50, 330, 340, 40);
        btnReturn.getAccessibleContext().setAccessibleName("");
        btnReturn.getAccessibleContext().setAccessibleDescription("");

        btnFinalizar.setText("FINALIZAR APLICAÇÃO");
        btnFinalizar.setMaximumSize(new java.awt.Dimension(105, 23));
        btnFinalizar.setMinimumSize(new java.awt.Dimension(105, 23));
        btnFinalizar.setPreferredSize(new java.awt.Dimension(105, 23));
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnFinalizar);
        btnFinalizar.setBounds(50, 380, 340, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        setSize(new java.awt.Dimension(445, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Evento do botão imprimir
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?","Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            //Imprimindo relatório com o framework JasperReports
            try {
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("C:/reports/Reservas.jasper",null,conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    //Evento do botão para sair e trocar o servidor
    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        new TelaLogin().show();
        dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    //Evento do botão funcionário para gerenciar os funcionários
    private void btnFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionarioActionPerformed
        // TODO add your handling code here:
        new TelaServidorView().show();
        dispose();
    }//GEN-LAST:event_btnFuncionarioActionPerformed

    //Evento do botão ambiente para gerenciar ambientes
    private void btnAmbienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbienteActionPerformed
        // TODO add your handling code here:
        new PrincipalAmbiente().show();
        dispose();
    }//GEN-LAST:event_btnAmbienteActionPerformed

    //Evento do botão reservas para gerenciar reservas
    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
        // TODO add your handling code here:
        new TelaReservaView().show();
        dispose();
    }//GEN-LAST:event_btnReservaActionPerformed

    private void txtNomeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtNomeComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtNomeComponentShown

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        new TelaBackup().show();
    }//GEN-LAST:event_btnBackupActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        int confire = JOptionPane.showConfirmDialog(null, "Deseja realmente finalizar a aplicação?","Atenção",JOptionPane.YES_NO_OPTION);
        if(confire == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Aplicação encerrada");
            System.exit(0);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAmbiente;
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnFuncionario;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnReserva;
    private javax.swing.JButton btnReturn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel txtNome;
    // End of variables declaration//GEN-END:variables
}
