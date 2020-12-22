package View;

import Bean.Ambiente;
import Bean.Reserva;
import Dao.AmbienteDAO;
import Dao.ReservaDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PrincipalAmbiente extends javax.swing.JFrame {
    /*Função para Listar Todos os Ambientes Cadastrados*/
    
    public void Listar(){
       try{
            AmbienteDAO dao = new AmbienteDAO();
            List<Ambiente> listaAmbientes = dao.listarAmbientes();
            DefaultTableModel model = (DefaultTableModel)tabela.getModel();
            model.setNumRows(0);
            
            for(Ambiente lista : listaAmbientes){
                model.addRow(new Object[]{
                    lista.getId(),
                    lista.getBloco(),
                    lista.getSalaNum(),
                    lista.getTipoSala()
                });
            }
            
       }catch(Exception e){
       
       }
    }
     /*Função para Listar Todos os Blocos Cadastrados*/
     public void readBlocs(){
        cbBloco.removeAllItems();
        cbBloco.addItem("<Selecione o Bloco>");
        cbNumero.removeAllItems();
        cbNumero.addItem("<Selecione a Sala>");
     
        AmbienteDAO adao = new AmbienteDAO();
        
        for(Ambiente a: adao.listarBloco()){
            cbBloco.addItem(a.getBloco());
        } 
    }
    /*Função para Listar Todas as Salas Cadastradas*/
    public void readSalas(String desc){
        cbNumero.removeAllItems();
        cbNumero.addItem("<Selecione a Sala>");
        
        AmbienteDAO adao = new AmbienteDAO();
        
        for(Ambiente a: adao.listarSala(desc)){
            cbNumero.addItem(a.getSalaNum());
        } 
    }
    public PrincipalAmbiente() {
        initComponents();
        
        if(TelaLogin.permissao.equals("Funcionário")){
            btnAdicionar.setEnabled(false);
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
        } 
    
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        tituloEditar = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbBloco = new javax.swing.JComboBox<>();
        cbNumero = new javax.swing.JComboBox<>();
        txtPesquisar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(796, 480));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(190, 370, 130, 40);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(490, 370, 130, 40);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(340, 370, 130, 40);

        tituloEditar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloEditar.setText("Ambientes");
        getContentPane().add(tituloEditar);
        tituloEditar.setBounds(350, 60, 82, 22);

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVoltar);
        btnVoltar.setBounds(30, 20, 80, 23);

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(540, 100, 79, 23);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Bloco", "Numero", "Tipo"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(160, 140, 500, 200);

        jLabel1.setText(TelaLogin.userNome);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(630, 20, 103, 14);

        cbBloco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione o Bloco>", "A", "I", "P", "K", "S" }));
        cbBloco.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbBlocoPopupMenuWillBecomeVisible(evt);
            }
        });
        cbBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBlocoActionPerformed(evt);
            }
        });
        getContentPane().add(cbBloco);
        cbBloco.setBounds(230, 100, 160, 20);

        cbNumero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione a Sala>", "001", "002", "003", "004", "005", "101", "102", "103", "104", "105", "201", "202", "203", "204", "205" }));
        cbNumero.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbNumeroPopupMenuWillBecomeVisible(evt);
            }
        });
        getContentPane().add(cbNumero);
        cbNumero.setBounds(400, 100, 118, 20);

        txtPesquisar.setEditable(false);
        getContentPane().add(txtPesquisar);
        txtPesquisar.setBounds(180, 90, 20, 30);

        setSize(new java.awt.Dimension(797, 479));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Botão Adicionar
        CadastroAmbiente telaCadastro = new CadastroAmbiente();
        
        telaCadastro.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // Botão Pesquisar
         try{
            String bloco = cbBloco.getSelectedItem().toString();
            String salaNum = cbNumero.getSelectedItem().toString();
            if(bloco != "<Selecione o Bloco>" && salaNum != "<Selecione a Sala>"){
                AmbienteDAO dao = new AmbienteDAO();
                List<Ambiente> consultarAmbientesId = dao.consultarAmbiente(bloco,salaNum);
                DefaultTableModel model = (DefaultTableModel)tabela.getModel();
                model.setNumRows(0);
                
                for(Ambiente consulta : consultarAmbientesId){
                    model.addRow(new Object[]{
                        consulta.getId(),
                        consulta.getBloco(),
                        consulta.getSalaNum(),
                        consulta.getTipoSala()
                    });
                }
                
            }else if(bloco != "<Selecione o Bloco>"){
                AmbienteDAO dao = new AmbienteDAO();
                List<Ambiente> consultarAmbientesId = dao.consultarAmbiente(bloco," ");
                DefaultTableModel model = (DefaultTableModel)tabela.getModel();
                model.setNumRows(0);
                
                for(Ambiente consulta : consultarAmbientesId){
                    model.addRow(new Object[]{
                        consulta.getId(),
                        consulta.getBloco(),
                        consulta.getSalaNum(),
                        consulta.getTipoSala()
                    });
                }
            
            }else if(salaNum != "<Selecione a Sala>"){
                AmbienteDAO dao = new AmbienteDAO();
                List<Ambiente> consultarAmbientesId = dao.consultarAmbiente(" ",salaNum);
                DefaultTableModel model = (DefaultTableModel)tabela.getModel();
                model.setNumRows(0);
                
                for(Ambiente consulta : consultarAmbientesId){
                    model.addRow(new Object[]{
                        consulta.getId(),
                        consulta.getBloco(),
                        consulta.getSalaNum(),
                        consulta.getTipoSala()
                    });
                }
            }
            else{
               Listar(); 
            }
       }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro:"+e);
       }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Listar Todos os Ambientes Cadastrados na Tabela
    
        Listar();
        
    }//GEN-LAST:event_formWindowActivated

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Botão Excluir
        try{
            Ambiente ambi = new Ambiente();
            AmbienteDAO ambiDao = new AmbienteDAO();
            ReservaDAO resDao = new ReservaDAO();
            
            ambi.setId(Integer.parseInt(txtPesquisar.getText()));
            
            int contId = Integer.parseInt(resDao.listarAmbientes(ambi.getId()));
            
            if(contId == 0){
                ambiDao.excluirAmbiente(ambi);
                JOptionPane.showMessageDialog(null, "Ambiente Excluido com Sucesso!");
            }else{
                int confirma = JOptionPane.showConfirmDialog(null, "O ambiente que deseja excluir está reservado, para exclui-lo é necessário excluir as reservas\nDeseja excluir as reservas para excluir o ambiente?","Atenção",JOptionPane.YES_NO_OPTION);
                if(confirma == JOptionPane.YES_OPTION){
                    for(Reserva r: resDao.buscandoIds(ambi.getId())){
                        resDao.excluirReserva(r);
                    }
                    ambiDao.excluirAmbiente(ambi);
                }
            }

            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Selecione um Ambiente Antes de Excluir");
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // Pegar o id do ambiente
        txtPesquisar.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tabelaMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // Pegar dados do Ambiente 
        try{
            Ambiente amb = new Ambiente();
            amb.setId(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
            amb.setBloco(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            amb.setSalaNum(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            amb.setTipoSala(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());

            //enviar Dados para outra tela
            EditarAmbiente telaEditar = new EditarAmbiente(amb);

            telaEditar.setVisible(true);
            dispose();
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Selecione um Ambiente Antes de Editar");
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // Voltar para Tela Principal
        new TelaPrincipal().show();
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBlocoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbBlocoActionPerformed

    private void cbNumeroPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbNumeroPopupMenuWillBecomeVisible
        // Exibir Numero das Salas
        readSalas(cbBloco.getSelectedItem().toString());
    }//GEN-LAST:event_cbNumeroPopupMenuWillBecomeVisible

    private void cbBlocoPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbBlocoPopupMenuWillBecomeVisible
        // Exibir Blocos
        readBlocs();
    }//GEN-LAST:event_cbBlocoPopupMenuWillBecomeVisible

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
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalAmbiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalAmbiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbBloco;
    private javax.swing.JComboBox<String> cbNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel tituloEditar;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
