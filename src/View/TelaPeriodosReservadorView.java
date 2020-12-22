package View;

import Bean.Reserva;
import Dao.PeriodoReservadoDAO;
import Dao.ReservaDAO;
import java.sql.Connection;
import Connection.ConnectionFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;

public class TelaPeriodosReservadorView extends javax.swing.JFrame {

    Connection conexao = null;
    
    public TelaPeriodosReservadorView(String data, String bloco, String sala){
        initComponents();
        
        //Inserindo a data das reservas em um label
        lblInfoBloco.setText(data);
        
        //Inserindo o bloco das reservas em um label
        lblInfoSala.setText(bloco);
        
        //Inserindo a sala das reservas em um label
        lblInfoData.setText(sala);
                
        //Chamando Função para popular a tabela com as informações da reserva
        readJTable(data, bloco, sala);
                    
        conexao = ConnectionFactory.conectar();
    }

    //Função para popular a tabela com as reservas realizadas
    public void readJTable(String data, String bloco, String sala){
        DefaultTableModel model = (DefaultTableModel)tablePeriodo.getModel();
        model.setRowCount(0);
        PeriodoReservadoDAO rdao = new PeriodoReservadoDAO();
        
        //Laço de repetição para adicionar as reservas na tabela
        for(Reserva r: rdao.read(data, bloco, sala)){
            
            model.addRow(new Object[]{
                r.getPeriodoReservado(),
                r.getClienteNome(),
                r.getServ().getNome(),
                r.getContribuicao(),
                r.getObservacao()
            });
        }
       
    }
    
    //Função para realizar a exclusão da reserva 
    public void excluirReserva(){
        PeriodoReservadoDAO rdao = new PeriodoReservadoDAO();
        ReservaDAO rsdao = new ReservaDAO();
        //Pegando a linha selecionada para exclusão para a exclusão
        int pesquisa =(tablePeriodo.getSelectedRow());
        //Verificando se uma linha foi selecionada
        if(pesquisa >= 0){
            String periodo = (tablePeriodo.getValueAt(pesquisa,0)).toString();
            //Laço de repetição para pegar o id que será excluido
            for(Reserva id: rdao.buscarId(lblInfoData.getText(), lblInfoBloco.getText(), lblInfoSala.getText(), periodo)){
                //Executando função para excluir uma reserva
                rsdao.excluirReserva(id);
                //Iniciando Tela de reserva view
                new TelaReservaView().show();
                //Fechando tela atual
                dispose();
            }
            
        }else{
            //Lançando Janela de menssagem com erro
            JOptionPane.showConfirmDialog(null, "Não foi selecionada uma reserva para ser excluida");
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVoltar = new javax.swing.JButton();
        lblNomeFunc = new javax.swing.JLabel();
        lblBloco = new javax.swing.JLabel();
        lblSala = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePeriodo = new javax.swing.JTable();
        lblData = new javax.swing.JLabel();
        lblPeriodos = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblInfoBloco = new javax.swing.JLabel();
        lblInfoSala = new javax.swing.JLabel();
        lblInfoData = new javax.swing.JLabel();
        btnImprime = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(700, 570));

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblNomeFunc.setText(TelaLogin.userNome);

        lblBloco.setText("BLOCO:");

        lblSala.setText("SALA:");

        tablePeriodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Periodo", "Cliente", "Servidor", "Contribuição", "Observações"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
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
        tablePeriodo.setColumnSelectionAllowed(true);
        tablePeriodo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablePeriodo);
        tablePeriodo.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablePeriodo.getColumnModel().getColumnCount() > 0) {
            tablePeriodo.getColumnModel().getColumn(0).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(0).setPreferredWidth(4);
            tablePeriodo.getColumnModel().getColumn(1).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablePeriodo.getColumnModel().getColumn(2).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablePeriodo.getColumnModel().getColumn(3).setResizable(false);
            tablePeriodo.getColumnModel().getColumn(4).setResizable(false);
        }

        lblData.setText("DATA:");

        lblPeriodos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPeriodos.setText("PERIODOS RESERVADOS");

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnImprime.setText("IMPRIMIR");
        btnImprime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPeriodos)
                                .addGap(76, 76, 76)
                                .addComponent(lblNomeFunc))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBloco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblInfoBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblSala)
                                .addGap(9, 9, 9)
                                .addComponent(lblInfoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblInfoData, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnImprime)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(btnEditar)
                                .addGap(30, 30, 30)
                                .addComponent(btnExcluir)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeFunc)
                    .addComponent(lblPeriodos)
                    .addComponent(btnVoltar))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSala, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblInfoBloco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBloco))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblInfoSala, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblData, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(lblInfoData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnImprime))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Ação do botão voltar
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        //Setanto valor da label para o default
        lblBloco.setText("BLOCO:");
        //Setanto valor da label para o default
        lblSala.setText("SALA:");
        //Setanto valor da label para o default
        lblData.setText("DATA:");
        
        //Fechando tela e abrindo a anterior
        this.setVisible(false);
        
        new TelaReservaView().show();
        dispose();
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    //Ação do botão de editar
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //pega a reserva selecionada na tabela
        int pesquisa =(tablePeriodo.getSelectedRow());
        //System.out.println(pesquisa);
        
        if(pesquisa >= 0){
            String periodo = (tablePeriodo.getValueAt(pesquisa,0)).toString();
            try {
                //Passa as informações selecionadas para a tela de edição da reserva
                new EditarReserva(lblInfoData.getText(), lblInfoBloco.getText(), lblInfoSala.getText(), periodo).show();
            } catch (ParseException ex) {
                //Lançando erro
                Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Fechando tela atual
            dispose();
        }else{
            JOptionPane.showConfirmDialog(null, "Não foi selecionada a informação na tabela");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    //Ação do botão de excluir reserva 
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        //Chamando função para excluir reserva
        excluirReserva();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnImprimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimeActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?","Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            //Imprimindo relatório com o framework JasperReports
            try {
                //Usando a classe HashMap para criar um filtro
                HashMap filtro = new HashMap();
                //filtro.put("nome","Pedro");
                filtro.put("sala",lblInfoSala.getText());
                filtro.put("bloco",lblInfoBloco.getText());
                filtro.put("data",lblInfoData.getText());
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("C:/reports/ReservaDetalhada.jasper",filtro,conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnImprimeActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPeriodosReservadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new TelaPeriodosReservadorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprime;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblInfoBloco;
    private javax.swing.JLabel lblInfoData;
    private javax.swing.JLabel lblInfoSala;
    private javax.swing.JLabel lblNomeFunc;
    private javax.swing.JLabel lblPeriodos;
    private javax.swing.JLabel lblSala;
    private javax.swing.JTable tablePeriodo;
    // End of variables declaration//GEN-END:variables
}
