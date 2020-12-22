package View;

import Bean.Ambiente;
import Bean.Leitura;
import Dao.AmbienteDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class CadastroAmbiente extends javax.swing.JFrame {
    public CadastroAmbiente() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloCadastro = new javax.swing.JLabel();
        bloco = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        txtBloco = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(796, 480));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        tituloCadastro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloCadastro.setText("Cadastro de Salas");
        getContentPane().add(tituloCadastro);
        tituloCadastro.setBounds(330, 80, 140, 22);

        bloco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bloco.setText("Bloco :");
        getContentPane().add(bloco);
        bloco.setBounds(160, 190, 55, 22);

        numero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        numero.setText("Numero :");
        getContentPane().add(numero);
        numero.setBounds(160, 230, 74, 22);

        tipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tipo.setText("Tipo :");
        getContentPane().add(tipo);
        tipo.setBounds(160, 270, 47, 22);

        txtBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBlocoActionPerformed(evt);
            }
        });
        getContentPane().add(txtBloco);
        txtBloco.setBounds(240, 190, 350, 20);
        getContentPane().add(txtNumero);
        txtNumero.setBounds(240, 230, 350, 20);

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(300, 360, 80, 40);

        btnVoltar.setText("CANCELAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar);
        btnVoltar.setBounds(440, 360, 90, 40);

        jLabel1.setText(TelaLogin.userNome);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(630, 20, 103, 14);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teórica" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cbTipo);
        cbTipo.setBounds(240, 270, 350, 20);

        setSize(new java.awt.Dimension(796, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBlocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBlocoActionPerformed
    
    //Evento para salvar o cadastro do ambiente
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        AmbienteDAO ambienteDao = new AmbienteDAO();
        //Verificação se foi selecionado um bloco e uma sala
        if(txtBloco.getText().equals("")||txtNumero.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Não há informações inseridas no cadastro do ambiente");
        }else{
            //Verificação se o bloco e a sala não estão cadastrados
            if((ambienteDao.buscarSalaNum(txtBloco.getText(), txtNumero.getText())).equals("0")){
                try{
                    Ambiente ambi = new Ambiente();
                    AmbienteDAO ambiDao = new AmbienteDAO();
                    //Pegando bloco do campo text e colocando na classe
                    ambi.setBloco(txtBloco.getText());
                    //Pegando sala do campo text e colocando na classe
                    ambi.setSalaNum(txtNumero.getText());
                    //Pegando tipo da sala do campo combo Box e colocando na classe
                    ambi.setTipoSala(cbTipo.getSelectedItem().toString());
                    //chamando função para cadastrar ambiente
                    ambiDao.cadastrarAmbiente(ambi);
                    
                    JOptionPane.showMessageDialog(null, "Ambiente Cadastrado com Sucesso!");
                    //Chamando tela anterior
                    new PrincipalAmbiente().show();
                    //Fechando tela atual
                    dispose();
                }catch(Exception erro){
                    //Lançando tela de erro
                    JOptionPane.showMessageDialog(null, "Erro ao Cadastrar:"+erro);
                }
            }else{
                //Lançando tela de erro
                JOptionPane.showMessageDialog(null, "Ambiente já cadastrado");
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed
    
    //Evento botão voltar
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // Voltar para tela pricipal e fechar a atual
        new PrincipalAmbiente().show();
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbTipoActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroAmbiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bloco;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel numero;
    private javax.swing.JLabel tipo;
    private javax.swing.JLabel tituloCadastro;
    private javax.swing.JTextField txtBloco;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
