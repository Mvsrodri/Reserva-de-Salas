package View;

import Bean.Ambiente;
import Bean.Reserva;
import Dao.AmbienteDAO;
import Dao.ReservaDAO;
import Connection.ConnectionFactory;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class TelaReservaView extends javax.swing.JFrame {
    //Recriando função para fazer a conexão
    Connection conexao = null;
    
    public TelaReservaView() {
        initComponents();
        //Pegando data atual
        java.util.Date data = new java.util.Date();
        
        //Realizando conexão com o banco
        conexao = ConnectionFactory.conectar();
        //Setando o label do id como invisivel
        lblId.setVisible(false);
        //Excecutando função para popular comboBox do bloco
        readBlocs();
        //Executando função para popular tabela com as informações das reservas
        readJTable();
        
    }
    
    //Função para leitura e para popular tabela com informções das reservas
    public void readJTable(){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        //Laço de repetição para popular tabela
        for(Reserva r: rdao.read()){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    //Função para popular tabela filtrando por bloco e sala
    public void readJTableBlocSala(String desc, String desc2){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        //Laço de repetição para popular tabela
        for(Reserva r: rdao.readForDescBlocSala(desc, desc2)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    //Função para popular tabela filtrando por bloco, sala e data
    public void readJTableBlocSalaDate(String desc, String desc2, String desc3){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        //Laço de repetição para popular a tabela
        for(Reserva r: rdao.readForDescBlocSalaDate(desc, desc2, desc3)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    //Função para popular tabela filtrando por bloco
    public void readJTableBloc(String desc){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        //Laço de repetição para popular tabela
        for(Reserva r: rdao.readForDescBloc(desc)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    //Função para filtar e popular tabela por salas
    public void readJTableSala(String desc){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        //Laço de repetição para popular tabela
        for(Reserva r: rdao.readForDescSala(desc)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    //Função para filtrar e popular tabela por datas
    public void readJTableDate(String desc){
        DefaultTableModel model = (DefaultTableModel)tabela.getModel();
        model.setRowCount(0);
        removeModel(model);
        ReservaDAO rdao = new ReservaDAO();
        
        //Laço de repetição para popular tabela
        for(Reserva r: rdao.readForDescDate(desc)){
            
            model.addRow(new Object[]{
                r.getAmbi().getBloco(),
                r.getAmbi().getSalaNum(),
                r.getDataInicio()
            });
        }
            
    }
    
    //Função para remover linha da tabela
    public void removeModel(DefaultTableModel model){
        int i = 0;
        //Laço de repetição para remover linha da tabela
        while(model.getRowCount()!=0){
            model.removeRow(i);
            i = i + 1;
        }
    }
    
    //Função para ler os blocos e colocar no comboBox
    public void readBlocs(){
        //Removento todos os itens no combobox
        cbxBloco.removeAllItems();
        //Adicionando linha vazia
        cbxBloco.addItem(" ");
        //Removento todos os itens no combobox
        cbxSala.removeAllItems();
        //Adicionando linha vazia
        cbxSala.addItem(" ");
        
        AmbienteDAO rdao = new AmbienteDAO();
        
        //Laço de repetição para popular o comboBox
        for(Ambiente r: rdao.listarBloco()){
            //adicionando item ao comboBox
            cbxBloco.addItem(r.getBloco());
        } 
    }
    
    //Função para popular o comboBox da sala
    public void readSalas(String desc){
        //Removento todos os itens no combobox
        cbxSala.removeAllItems();
        //Adicionando campo vazio
        cbxSala.addItem(" ");
        
        AmbienteDAO rdao = new AmbienteDAO();
        //Laço de repetição para popular o comboBox
        for(Ambiente r: rdao.listarSala(desc)){
            //Adicionando item ao comboBox
            cbxSala.addItem(r.getSalaNum());
        } 
    }
    
    public void encerrarTela(){
        new TelaPrincipal().show();
        dispose();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnVoltar = new javax.swing.JButton();
        lblNomeFunc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        lblBloco = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jdcData = new com.toedter.calendar.JDateChooser();
        btnProcura = new javax.swing.JButton();
        lblTexto = new javax.swing.JLabel();
        cbxBloco = new javax.swing.JComboBox<>();
        lblSala = new javax.swing.JLabel();
        cbxSala = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reservas");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(655, 460));
        setResizable(false);

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblNomeFunc.setText(TelaLogin.userNome);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BLOCO", "SALA", "DATA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setMaximumSize(new java.awt.Dimension(225, 225));
        tabela.setPreferredSize(new java.awt.Dimension(225, 225));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(1).setResizable(false);
            tabela.getColumnModel().getColumn(2).setResizable(false);
        }

        btnAdicionar.setText("CRIAR");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        lblBloco.setText("Bloco");

        lblData.setText("Data");

        btnProcura.setText("PROCURAR");
        btnProcura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcuraActionPerformed(evt);
            }
        });

        lblTexto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTexto.setText("BUSCAR RESERVAS");

        cbxBloco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "P", "G", "H", "I", "K", "A" }));
        cbxBloco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBlocoItemStateChanged(evt);
            }
        });
        cbxBloco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBlocoActionPerformed(evt);
            }
        });

        lblSala.setText("Sala");

        cbxSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "001", "002", "003", "101", "102", "103", " " }));
        cbxSala.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbxSalaPopupMenuWillBecomeVisible(evt);
            }
        });
        cbxSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxSalaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(194, 194, 194)
                            .addComponent(lblTexto))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNomeFunc))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBloco)
                                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSala)
                                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblData)
                                    .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btnAdicionar)
                        .addGap(88, 88, 88)
                        .addComponent(btnProcura)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeFunc)
                    .addComponent(lblTexto)
                    .addComponent(btnVoltar))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblData)
                                .addGap(6, 6, 6)
                                .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSala)
                                    .addComponent(lblBloco))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnProcura))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    //Evento do botão Procurar
    private void btnProcuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcuraActionPerformed
        //Pegando linha selecionada da tabela
        int pesquisa =(tabela.getSelectedRow());
        //Verificando se alguma linha foi selecionada
        if(pesquisa >= 0){
            try{
                //Armazenando data selecionada a uma string
                String data = (tabela.getValueAt(pesquisa,0)).toString();
                //Armazenando bloco selecioanado a uma string
                String bloco = (tabela.getValueAt(pesquisa,1)).toString();
                //Armazenando sala selecionada a uma string
                String sala = (tabela.getValueAt(pesquisa,2)).toString();

                //Chamando tela de reserva especificada e passando as variáveis criadas com as informações de data, bloco e sala
                TelaPeriodosReservadorView frame = new TelaPeriodosReservadorView(data, bloco, sala);

                frame.show();
                //Fechando tela atuaç
                dispose();
            }
            catch (Exception erro){
                //Janela de menssagem com erro
                JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                //Dando refresh na tela atual
                readJTable();
            }
        //Se nenhuma linha foi selecionada e nenhum campo de pesquisa esta populado da refresh na tela 
        }else if((cbxBloco.getSelectedItem().toString()==" ") && (cbxSala.getSelectedItem().toString()==" ") && (jdcData.getDate()==null)){
            readBlocs();
            readJTable();
        //Se nenhuma opção for verdadeira é feito um filtro das informações que estão inseridas
        }else{
            //Se o comboBox do bloco tiver informação selecionado
            if((cbxBloco.getSelectedItem().toString())!=" "){
                try{
                    //Passando para a função de filtro por bloco
                    readJTableBloc(cbxBloco.getSelectedItem().toString());
                }
                catch (Exception erro){
                    //janela de menssagem de erro
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            //Se o comboBox da sala tiver informação selecionada
            if((cbxSala.getSelectedItem().toString())!=" "){
                try{
                    //passando para a função de filtro por sala
                    readJTableSala(cbxSala.getSelectedItem().toString());
                }
                catch (Exception erro){
                    //janela de menssagem de erro
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            //Se o comboBox da data tiver informação selecionada
            if((jdcData.getDate())!=null){
                try{
                    //Formatando data para o formato do banco e colocando em string
                    SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd"); 
                    String data = s.format(jdcData.getDate());
                    //Passando para a função de filtro por data
                    readJTableDate(data);
                }
                catch (Exception erro){
                    //janela de menssagem de erro
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            //Se o comboBox do bloco e o comboBox da sala tiverem informações selecionadas 
            if((cbxBloco.getSelectedItem().toString())!=" "&&(cbxSala.getSelectedItem().toString())!=" "){
                try{
                    //Passa para a função de filtro por bloco e sala
                    readJTableBlocSala(cbxBloco.getSelectedItem().toString(),cbxSala.getSelectedItem().toString());
                }
                catch (Exception erro){
                    //janela de menssagem de erro
                    JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela","Erro: Falta de Informação", JOptionPane.DEFAULT_OPTION, 2);
                }
            }
            //Se o comboBox do bloco, comboBox da sala e o jchooserDate tiverem selecionados
            if((cbxBloco.getSelectedItem().toString())!=" "&&(cbxSala.getSelectedItem().toString())!=" " && (jdcData.getDate())!=null){
                //Formatendo data e passando para uma string
                SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd"); 
                String dataf = s.format(jdcData.getDate());
                //Passando para a função que filtra por bloco, sala e data
                readJTableBlocSalaDate(cbxBloco.getSelectedItem().toString(),cbxSala.getSelectedItem().toString(),dataf);
            }
        }
    }//GEN-LAST:event_btnProcuraActionPerformed

    //Evento do botão voltar
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        //Abrindo tela anterior
        new TelaPrincipal().show();
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void cbxBlocoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBlocoItemStateChanged
        
    }//GEN-LAST:event_cbxBlocoItemStateChanged

    private void cbxBlocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBlocoActionPerformed
        //readSalas(cbxBloco.getSelectedItem().toString());
    }//GEN-LAST:event_cbxBlocoActionPerformed

    //Evento ao clicar no comboBox da sala
    private void cbxSalaPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxSalaPopupMenuWillBecomeVisible
        //Popula o comboBox da sala
        readSalas(cbxBloco.getSelectedItem().toString());
    }//GEN-LAST:event_cbxSalaPopupMenuWillBecomeVisible

    private void cbxSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxSalaMouseClicked

    }//GEN-LAST:event_cbxSalaMouseClicked

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        
   
    }//GEN-LAST:event_tabelaMouseClicked

    //Evento do botão adicionar
    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //Abre a tela de cadastro para reserva
        new CadastroReserva().show();
        //Fechando tela atual
        dispose();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReservaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnProcura;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxBloco;
    private javax.swing.JComboBox<String> cbxSala;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcData;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNomeFunc;
    private javax.swing.JLabel lblSala;
    private javax.swing.JLabel lblTexto;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
