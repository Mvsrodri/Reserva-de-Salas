package View;

import Bean.Administrador;
import Bean.Reserva;
import Dao.AdministradorDAO;
import Dao.ReservaDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaServidorView extends javax.swing.JFrame {

    int Siape = 0;
    //Metodo de Listagem
    public void Listar() {
        try {
            //Execução do select
            AdministradorDAO admiDao = new AdministradorDAO();
            List<Administrador> listarServidor = admiDao.listarServidor();

            //Colacação do dados na tabela
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setNumRows(0);
            
            //Laço de repetição para popular tabela
            for (Administrador listar : listarServidor) {
                model.addRow(new Object[]{
                    listar.getSiape(),
                    listar.getNome(),
                    listar.getEmail(),
                    listar.getRamal(),
                    listar.getPermissao(),
                    listar.getConPessoal(),
                    listar.getLogin(),
                    listar.getSenha(),});
            }

        } catch (Exception e) {
        }
    }

    public TelaServidorView() {
        initComponents();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        bntEditar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRamal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtConPessoal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPermissao = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        BntCadastrar = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JTextField();
        bntNovo = new javax.swing.JButton();
        Nome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Siape");

        txtId.setEditable(false);

        tabela.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Siape", "Nome", "Email", "Ramal", "Permissâo", "Contato", "Login", "Senha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(1).setResizable(false);
            tabela.getColumnModel().getColumn(2).setResizable(false);
            tabela.getColumnModel().getColumn(3).setResizable(false);
            tabela.getColumnModel().getColumn(4).setResizable(false);
            tabela.getColumnModel().getColumn(6).setResizable(false);
        }

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        bntEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntEditar.setText("Editar");
        bntEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditarActionPerformed(evt);
            }
        });

        btnVoltar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Ramal");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Permissão");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Telefone");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Login");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Senha");

        txtPermissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Funcionário" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Controle de Servidores");

        BntCadastrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BntCadastrar.setText("Cadastrar");
        BntCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntCadastrarActionPerformed(evt);
            }
        });

        bntNovo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntNovo.setText("Limpar");
        bntNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNovoActionPerformed(evt);
            }
        });

        Nome.setText(TelaLogin.userNome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtNome)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRamal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConPessoal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(BntCadastrar)
                .addGap(72, 72, 72)
                .addComponent(bntNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(bntEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addGap(229, 229, 229)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Nome)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(jLabel9)
                    .addComponent(Nome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtRamal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtConPessoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BntCadastrar)
                    .addComponent(bntNovo)
                    .addComponent(bntEditar)
                    .addComponent(btnExcluir))
                .addGap(59, 59, 59))
        );

        setSize(new java.awt.Dimension(796, 480));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Listando Servidores Cadastrados
        //Listar();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //Listando Servidores Cadastrados
        Listar();
    }//GEN-LAST:event_formWindowActivated

    //Evento ao clickar em um campo da tabela
    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        //Pegando os codigos do Servidor 
        Siape = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        txtId.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        txtNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        txtEmail.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        txtRamal.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        txtPermissao.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
        txtConPessoal.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
        txtLogin.setText(tabela.getValueAt(tabela.getSelectedRow(), 6).toString());
        txtSenha.setText(tabela.getValueAt(tabela.getSelectedRow(), 7).toString());
    }//GEN-LAST:event_tabelaMouseClicked

    //Evento do botão voltar para a tela anterior
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        new TelaPrincipal().show();
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    //Evento do botão para abrir tela de cadastro
    private void BntCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntCadastrarActionPerformed
        new CadastroServidor().show();
        dispose();
    }//GEN-LAST:event_BntCadastrarActionPerformed
    
    //Evento do botão para editar informações de um servidor
    private void bntEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditarActionPerformed
        //Edição do Cadastro
        Administrador admi = new Administrador();
        AdministradorDAO admiDao = new AdministradorDAO();
        if(Siape != 0){
            //Verificação se o siape foi alterado
            if(Siape == Integer.parseInt(txtId.getText())){
                try {
                    //Se o siape não foi alterado permite a edição direta do servidor
                    admi.setSiape(Integer.parseInt(txtId.getText()));
                    admi.setNome(txtNome.getText());
                    admi.setEmail(txtEmail.getText());
                    admi.setRamal(Integer.parseInt(txtRamal.getText()));
                    admi.setPermissao(txtPermissao.getSelectedItem().toString());
                    admi.setConPessoal(txtConPessoal.getText());
                    admi.setLogin(txtLogin.getText());
                    admi.setSenha(txtSenha.getText());
                    //Chamando função para editar servidor 
                    admiDao.editarServidor(admi);

                    JOptionPane.showMessageDialog(null, "Dados Alterados");

                } catch (Exception e) {
                    //Lançando tela de erro
                    JOptionPane.showMessageDialog(null, "Erro Dados não Alterados" + e);
                }
            }else{
                //Verificando se o siape editado já existe
                if(admiDao.buscarSiape(txtId.getText()).equals("0")){
                    try {

                        admi.setSiape(Integer.parseInt(txtId.getText()));
                        admi.setNome(txtNome.getText());
                        admi.setEmail(txtEmail.getText());
                        admi.setRamal(Integer.parseInt(txtRamal.getText()));
                        admi.setPermissao(txtPermissao.getSelectedItem().toString());
                        admi.setConPessoal(txtConPessoal.getText());
                        admi.setLogin(txtLogin.getText());
                        admi.setSenha(txtSenha.getText());
                        //Chamando função para editar
                        admiDao.editarServidor(admi);

                        JOptionPane.showMessageDialog(null, "Dados Alterados");

                    } catch (Exception e) {
                        //Lançando tela de erro
                        JOptionPane.showMessageDialog(null, "Erro Dados não Alterados" + e);
                    }
                }else{
                    //Lançando tela de erro
                    JOptionPane.showMessageDialog(null, "Siape inserido já existe");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum Servidor selecionado na tabela");
        }
        
    }//GEN-LAST:event_bntEditarActionPerformed

    //Evento do botão excluir servidor
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        //Ecluir Servidor
        ReservaDAO resDao = new ReservaDAO();
        Administrador admi = new Administrador();
        AdministradorDAO admiDao = new AdministradorDAO();
        if(Siape != 0){
            admi.setSiape(Integer.parseInt(txtId.getText()));    
            int contId = Integer.parseInt(resDao.listarServidores(admi.getSiape()));

            //Verificando se o servidor realizou alguma reserva
            if(contId == 0){
                //Excluindo servidor
                admiDao.excluirServidor(admi);
                JOptionPane.showMessageDialog(null, "Servidor Excluido com Sucesso!");
            }else{
                int confirma = JOptionPane.showConfirmDialog(null, "O servidor que deseja excluir está reservando, para exclui-lo é necessário excluir as reservas\nDeseja excluir as reservas para excluir o servidor?","Atenção",JOptionPane.YES_NO_OPTION);
                if(confirma == JOptionPane.YES_OPTION){
                    for(Reserva r: resDao.buscandoServidores(admi.getSiape())){
                        resDao.excluirReserva(r);
                    }
                    admiDao.excluirServidor(admi);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum Servidor selecionado na tabela");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    //Evento do botão novo para limpar os campo já preenchidos
    private void bntNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNovoActionPerformed
        //Limpando a Tela
        txtId.setText(null);
        txtNome.setText(null);
        txtEmail.setText(null);
        txtRamal.setText(null);
        txtConPessoal.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
    }//GEN-LAST:event_bntNovoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaServidorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaServidorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BntCadastrar;
    private javax.swing.JLabel Nome;
    private javax.swing.JButton bntEditar;
    private javax.swing.JButton bntNovo;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtConPessoal;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JComboBox<String> txtPermissao;
    private javax.swing.JTextField txtRamal;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
