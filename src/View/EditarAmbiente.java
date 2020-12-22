package View;

import Bean.Ambiente;
import Bean.Leitura;
import Dao.AmbienteDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class EditarAmbiente extends javax.swing.JFrame {
    //Variáveis globais para armazenar o bloco e sala a serem alterados
    String blocoS;
    String salaN;
    
    
    public EditarAmbiente(Ambiente amb) {
        initComponents();
        //Pegando informações do ambiente passado para ser editado
        txtId.setText(String.valueOf(amb.getId()));
        txtBloco.setText(String.valueOf(amb.getBloco()));
        txtNumero.setText(String.valueOf(amb.getSalaNum()));
        blocoS = amb.getBloco();
        salaN = amb.getSalaNum();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloEditar = new javax.swing.JLabel();
        bloco = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        txtBloco = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(796, 480));
        setResizable(false);
        getContentPane().setLayout(null);

        tituloEditar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloEditar.setText("Editar Salas");
        getContentPane().add(tituloEditar);
        tituloEditar.setBounds(350, 80, 91, 22);

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
        btnSalvar.setBounds(300, 370, 80, 40);

        btnVoltar.setText("CANCELAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar);
        btnVoltar.setBounds(420, 370, 90, 40);

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtId);
        txtId.setBounds(240, 150, 40, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 150, 31, 22);

        jLabel3.setText(TelaLogin.userNome);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(630, 20, 103, 14);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teórica" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cbTipo);
        cbTipo.setBounds(240, 270, 350, 20);

        setSize(new java.awt.Dimension(795, 509));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBlocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBlocoActionPerformed

    //Evento do botão salvar para armazenar edição
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //Verificação se o bloco e sala não foram alterados
        if(salaN.equals(txtNumero.getText())&&blocoS.equals(txtBloco.getText())){
            try{
                Ambiente ambi = new Ambiente();
                AmbienteDAO ambiDao = new AmbienteDAO();
                //Pegando informações
                ambi.setId(Integer.parseInt(txtId.getText()));
                ambi.setBloco(txtBloco.getText());
                ambi.setSalaNum(txtNumero.getText());
                ambi.setTipoSala(cbTipo.getSelectedItem().toString());
                //Chamando função para editar ambiente
                ambiDao.editarAmbiente(ambi);
                JOptionPane.showMessageDialog(null, "Ambiente Editado com Sucesso!");
                //Chamando tela anterior e fechando anterior
                new PrincipalAmbiente().show();
                dispose();

            }catch(Exception erro){
                //Lançando tela de erro
                JOptionPane.showMessageDialog(null, "Erro ao Cadastrar:"+erro);
            }
        }else{
            try{
                Ambiente ambi = new Ambiente();
                AmbienteDAO ambiDao = new AmbienteDAO();
                //Pegando informações da tela
                ambi.setId(Integer.parseInt(txtId.getText()));
                ambi.setBloco(txtBloco.getText());
                ambi.setSalaNum(txtNumero.getText());
                ambi.setTipoSala(cbTipo.getSelectedItem().toString());
                //Verificando se a sala ou bloco já estão cadastrados
                if((ambiDao.buscarSalaNum(ambi.getBloco(), ambi.getSalaNum())).equals("0")){
                    //Chamando função para alterar
                    ambiDao.editarAmbiente(ambi);
                    JOptionPane.showMessageDialog(null, "Ambiente Editado com Sucesso!");
                    //Abrindo tela anterior e fechando a atual
                    new PrincipalAmbiente().show();
                     dispose();
                }else{
                    //Lançando tela de erro
                    JOptionPane.showMessageDialog(null, "Já há esta sala para o bloco referente");
                }
            }catch(Exception erro){
                //Lançando tela de erro
                JOptionPane.showMessageDialog(null, "Erro ao Cadastrar:"+erro);
            }
        }
        

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    //Evento do botão volta para a tela anterior
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // Voltar para tela pricipal e fechar a atual
        new PrincipalAmbiente().show();
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbTipoActionPerformed

    
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
            java.util.logging.Logger.getLogger(EditarAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarAmbiente(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bloco;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel numero;
    private javax.swing.JLabel tipo;
    private javax.swing.JLabel tituloEditar;
    private javax.swing.JTextField txtBloco;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
