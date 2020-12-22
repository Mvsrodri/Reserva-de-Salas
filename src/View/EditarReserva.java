package View;

import Bean.Ambiente;
import Bean.Reserva;
import Dao.AmbienteDAO;
import Dao.PeriodoReservadoDAO;
import Dao.ReservaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import nickyb.sqleonardo.querybuilder.syntax.SQLFormatter;


public class EditarReserva extends javax.swing.JFrame {

    //Criando váriaveis especificas para armazenar valores passados da tela anterior como periodo, data, bloco e sala
    private String periodo = "";
    private String data = "";
    private String bloco = "";
    private String sala = "";
    
    //Função para iniciar a reserva
    public EditarReserva(String data, String bloco, String sala, String periodo) throws ParseException {
        initComponents();
        
        //Recebendo valores passados da tela anterior
        this.periodo = periodo;
        this.data = data;
        this.bloco = bloco;
        this.sala = sala;
        
        //Função para popular o comboBox do bloco
        readBlocs(bloco);
        //Função para popular o comboBox da sala
        readJSala(bloco, sala);
        //Função para popular os texts e campos da tela
        readEdit(data, bloco, sala, periodo);
    }
    
    //Função para validar a edição  
    public void readValida(String bloco, String sala, String periodo, String data) throws ParseException{
        ReservaDAO rdao = new ReservaDAO();
        //Se os valores do periodo, data, bloco e sala não tiverem sido editados
        if((this.periodo.equals(periodo)) && (this.data.equals(data)) && (this.bloco.equals(bloco)) && (this.sala.equals(sala))){
            //Se os campor text nome cliente, text documento cliente e text contato cliente tiverem vazios
            if((txtNomeCli.getText().equals(""))||(txtDocumentoCli.getText().equals(""))||(txtContatoCli.getText().equals(""))){
                //Janela de menssagem com erro 
                JOptionPane.showConfirmDialog(null,"Os campor referentes ao clientes estão incompletos","Erro",JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
            }else{
                //Passando para a função que lista os clientes equivalentes ao cliente na edição e verificando se existe o mesmo 
                if(rdao.listarCliente(txtDocumentoCli.getText(),txtNomeCli.getText(),txtContatoCli.getText()).equals("1")){
                    {
                        //Confirmação de se deseja realmente editar com aquele cliente inserido
                        int confirma = JOptionPane.showConfirmDialog(null, "O cliente que está reservando é o\nNome: "+txtNomeCli.getText()+"\nDocumento: "+txtDocumentoCli.getText()+"\nDeseja editar com esse cliente?","Atenção",JOptionPane.YES_NO_OPTION);
                        if(confirma == JOptionPane.YES_OPTION){
                            //Chamando função para editar a reserva
                            editReserva();
                        }
                    }

                }else{ 
                    //Se o cliente não existe e o documento dele não está em uso
                    if(rdao.buscarCliente(txtDocumentoCli.getText()).equals("0")){
                        //Se algum dos campos do cliente forem vazio
                        if((txtNomeCli.getText().equals(""))||(txtDocumentoCli.getText().equals(""))||(txtContatoCli.getText().equals(""))){
                            //Janela de menssagem de erro
                            JOptionPane.showConfirmDialog(null,"O cliente não pode ser adicionado\nPois os campos referentes ao clientes estão incompletos","Erro",2); 
                        }else{
                            //Confirmação de se deseja realmente criar o cliente para a reservas
                            int confirma = JOptionPane.showConfirmDialog(null, "Deseja cadastrar o cliente\nNome: "+txtNomeCli.getText()+"\nDocumento: "+txtDocumentoCli.getText()+"\nPara esta reserva?","Atenção",JOptionPane.YES_NO_OPTION);
                            if(confirma == JOptionPane.YES_OPTION){
                                //Chamando função para criar o cliente
                                rdao.cadastrarCliente(txtNomeCli.getText(),txtDocumentoCli.getText(),txtContatoCli.getText());
                                //Chamando função para editar a reserva
                                editReserva();
                            }
                        }
                    }
                }
            }
        }
        //Se as informações do periodo, bloco, sala e data forem diferente verifica se as informações novas para a edição
        else if((rdao.validarReserva(bloco,sala,periodo,data)).equals("0")){
            //Janela de menssagem confirmalção de edição cadastrada
            JOptionPane.showConfirmDialog(null, "Informações válidas para edição"," ", JOptionPane.DEFAULT_OPTION, 2);
            //Chamando função de editar reserva
            editReserva();
        }else{
            //Janela de menssagem de erro
            JOptionPane.showConfirmDialog(null, "Esse Periodo já foi reservado","Erro: Repetição de cadastro", JOptionPane.DEFAULT_OPTION, 2);
        }
        
        
    }

    //Função para popular os campos da tela de edição
    public void readEdit(String data, String bloco, String sala, String periodo) throws ParseException{
    
        PeriodoReservadoDAO rdao = new PeriodoReservadoDAO();
        
        //Laço de repetição para popular os campos da tela
        for(Reserva r: rdao.readEdit(data,bloco,sala,periodo)){
            //Setando no comboBox o valor do perido da reserva
            cbxPeriodo.setSelectedItem(r.getPeriodoReservado());
            //Setando o nome do cliente no text
            txtNomeCli.setText(r.getClienteNome());
            //Setando o contato do cliente no text
            txtContatoCli.setText(r.getClienteContato());
            //Setando o documento do cliente no text
            txtDocumentoCli.setText(r.getClienteDocumento());
            //Verificando oquer está inserido no atributo da contribuição
            if(r.getContribuicao()==true){
                //Setando a contribuição do comboBox
                cbxContrib.setSelectedIndex(0);
            }else{
                //Setando a contribuição do comboBox
                cbxContrib.setSelectedIndex(1);
            }
            //Setando formatação com parâmetro igual ao modelo do sql
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            //Formatando data
            Date dataFormatada = formato.parse(r.getDataInicio());
            //Setando no jchooserDate a data da reserva
            dtcDate.setDate(dataFormatada);
            //Setando as observaçõs no text
            txtObservacao.setText(r.getObservacao());
            //Setando o nome do servidor do text
            txtNomeServ.setText(r.getServ().getNome());
            //Pegando id da reserva
            int teste = r.getId();
            //Setando o id da reserva
            txtId.setText(Integer.toString(teste));
        } 
        
        
    }
    
    //Função para editar reserva
    public void editReserva() throws ParseException{
        Reserva res = new Reserva();
        ReservaDAO resDao = new ReservaDAO();
        
        //Pegando valor do comboBox e passando para o atributo da classe
        res.getAmbi().setBloco(cbxBloco.getSelectedItem().toString());
        //Pegando valor do comboBox e passando para o atributo da classe
        res.getAmbi().setSalaNum(cbxSala.getSelectedItem().toString());
        //Pegando valor do comboBox e passando para o atributo da classe
        res.setPeriodoReservado(cbxPeriodo.getSelectedItem().toString());
        //Pegando valor do text e passando para o atributo da classe
        res.setClienteNome(txtNomeCli.getText());
        //Pegando valor do text e passando para o atributo da classe
        res.setClienteContato(txtContatoCli.getText());
        //Pegando valor do text e passando para o atributo da classe
        res.setClienteDocumento(txtDocumentoCli.getText());
        //Verificando oque esta selecionado no comboBox de contribuição
        if((cbxContrib.getSelectedItem().toString()).equals("True")){
            //Pegando valor true e passando para o atributo da classe
            res.setContribuicao(true);
        }else
            //Verificando oque esta selecionado no comboBox de contribuição
        if((cbxContrib.getSelectedItem().toString()).equals("False")){
            //Pegando valor false e passando para o atributo da classe
            res.setContribuicao(false);
        }
        //Criando variavel para formatação igual do banco de dados
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd"); 
        //Formatando data
        String dataf = s.format(dtcDate.getDate());
        //Pegando valor da data e passando para o atributo da classe
        res.setDataInicio(dataf);
        //Pegando valor do text e passando para o atributo da classe
        res.setObservacao(txtObservacao.getText());
        //Pegando valor do text e passando para o atributo da classe
        res.getServ().setNome(txtNomeServ.getText());
        //Pegando valor do text e passando para o atributo da classe
        res.setId(Integer.parseInt(txtId.getText()));
        //Chamando função para editar no banco e passando a classe reserva
        resDao.editarReserva(res);
    }
    
    //Função para popular comboBox do bloco    
    public void readBlocs(String bloco){
        //Removendo itens do bloco
        cbxBloco.removeAllItems();
        //Adicionando vazio
        cbxBloco.addItem(" ");
        
        AmbienteDAO rdao = new AmbienteDAO();
        
        //Laço de repetição para popular comboBox
        for(Ambiente r: rdao.listarBloco()){
            //Adicionado item no comboBox
            cbxBloco.addItem(r.getBloco());
        } 
        int op = 0;
        //Laço de repetição para selecionar bloco referente ao selecionado na reserva
        while (op == 0){
            if(cbxBloco.getSelectedItem().toString().equals(bloco)){
                op = 1;
            }else{
                cbxBloco.setSelectedIndex(cbxBloco.getSelectedIndex()+1);
            }
        }
    }
    
    //Função para popular o comboBox da sala
    public void readJSala(String bloco, String sala){
        //Removento todos os itens do comboBox
        cbxSala.removeAllItems();
        //Adicionando vazio
        cbxSala.addItem(" ");
        
        AmbienteDAO ramb = new AmbienteDAO();
        
        //Laço de repetição para popular comboBox
        for(Ambiente r: ramb.listarSala(bloco)){
            //Adicionando item ao comboBox
            cbxSala.addItem(r.getSalaNum());
        } 
        int op = 0;
        //Laço de repetição para deixar selecionado no comboBox da sala referente a reserva
        while (op == 0){
            if(cbxSala.getSelectedItem().toString().equals(sala)){
                op = 1;
            }else{
                cbxSala.setSelectedIndex(cbxSala.getSelectedIndex()+1);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNomeCli = new javax.swing.JTextField();
        txtContatoCli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDocumentoCli = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        cbxSala = new javax.swing.JComboBox<>();
        lblBloco = new javax.swing.JLabel();
        cbxBloco = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtNomeServ = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        cbxContrib = new javax.swing.JComboBox<>();
        dtcDate = new com.toedter.calendar.JDateChooser();
        cbxPeriodo = new javax.swing.JComboBox<>();
        lblTexto = new javax.swing.JLabel();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Sala");

        jLabel2.setText("Nome Cliente");

        jLabel3.setText("Contato");

        jLabel4.setText("Documento");

        jLabel5.setText("Contribuição");

        jLabel6.setText("Data Inicio");

        jLabel9.setText("Observação");

        jLabel11.setText("Período Reservado");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Criar Reserva");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel6))
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField6)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel7.setText("Sala");

        jLabel12.setText("Período Reservado");

        jLabel8.setText("Nome do Cliente");

        jLabel10.setText("Contato do CLiente ");

        jLabel13.setText("Documento do cliente");

        jLabel14.setText("Contribuição");

        jLabel15.setText("Data Inicio");

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane2.setViewportView(txtObservacao);

        jLabel16.setText("Observação");

        lblBloco.setText("Bloco");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel17.setText("Nome Servidor");

        txtNomeServ.setEditable(false);

        lblId.setText("ID Reserva");

        txtId.setEditable(false);

        cbxContrib.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "False", "True" }));

        cbxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M1", "M2", "M3", "M4", "M5", "T1", "T2", "T3", "T4", "T5", "T6", "N1", "N2", "N3", "N4", "N5" }));

        lblTexto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTexto.setText("EDITAR RESERVA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btnSalvar)
                                .addGap(55, 55, 55)
                                .addComponent(btnCancelar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel16))
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel12)
                                        .addComponent(lblBloco))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                                    .addComponent(lblId)
                                    .addGap(26, 26, 26)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtContatoCli)
                                .addComponent(txtNomeCli)
                                .addComponent(cbxPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 51, Short.MAX_VALUE)
                                .addComponent(cbxSala, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxContrib, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeServ))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtDocumentoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTexto)
                .addGap(146, 146, 146))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTexto)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBloco)
                        .addComponent(cbxBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumentoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtContatoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbxContrib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(dtcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNomeServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    //Evento do botão salvar
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //Se o jchooserdate for diferente de vazio ou nulo
        if(dtcDate.getDate()!=null){
            //cria variável de formatação
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd"); 
            //formata data do campo jchooserdate
            String dataf = s.format(dtcDate.getDate());
            try {
                //Chama váriavel de validação do periodo, data, sala e bloco da reserva
                readValida(cbxBloco.getSelectedItem().toString(), cbxSala.getSelectedItem().toString(), cbxPeriodo.getSelectedItem().toString(), dataf);
                //Setando a tela como invisiveç
                this.setVisible(false);
                //Chamando tela de reserva por periodo
                new TelaPeriodosReservadorView(cbxBloco.getSelectedItem().toString(), cbxSala.getSelectedItem().toString(), dataf).show();
                //fechando tela de reserva
                dispose();
            } catch (ParseException ex) {
                //Lançando erro
                Logger.getLogger(EditarReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    //Evento do botão cancelar
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Seta a tela como invisivel
        this.setVisible(false);
        //Cria variável de formatar data
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd"); 
        //Formata data e passa para string
        String dataf = s.format(dtcDate.getDate());
        //chama a tela de périodo reservado
        new TelaPeriodosReservadorView(cbxBloco.getSelectedItem().toString(), cbxSala.getSelectedItem().toString(), dataf).show();
        //E fecha tela atual
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(EditarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new EditarReserva(String, String, String, String).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxBloco;
    private javax.swing.JComboBox<String> cbxContrib;
    private javax.swing.JComboBox<String> cbxPeriodo;
    private javax.swing.JComboBox<String> cbxSala;
    private com.toedter.calendar.JDateChooser dtcDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTexto;
    private javax.swing.JTextField txtContatoCli;
    private javax.swing.JTextField txtDocumentoCli;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNomeCli;
    private javax.swing.JTextField txtNomeServ;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables
}
