
package Dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Bean.Reserva;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaDAO {
    //Atribuindo variavel conectar da classe Connection
    public Connection conectar;
    
    public ReservaDAO(){
        //recebendo os padrões da classe conectar
        this.conectar = new ConnectionFactory().conectar();
    } 
    
    //Função para cadastrar cliente
    public void cadastrarCliente(String nome, String doc, String contato) {

        PreparedStatement inserir = null;
        try {
            //Peguando Query para inserir valores no banco
            inserir = conectar.prepareStatement("INSERT INTO Cliente VALUES ('0', ?, ?, ?)");
            //Passando parametro para inserção
            inserir.setString(1, nome);
            //Passando parametro para inserção
            inserir.setString(2, doc);
            //Passando parametro para inserção
            inserir.setString(3, contato);
     
            //Iniciando execução da query
            inserir.execute();
            //Encerrando execução da query
            inserir.close();


        } catch (SQLException erro) {
            //Lançando exceções
            throw new RuntimeException(erro);
        }

    }
    
    
    //Função para cadastrar as reservas
    public void cadastrarReserva(Reserva res) {

        PreparedStatement inserir = null;

        try {
            //Peguando Query para inserir valores no banco
            inserir = conectar.prepareStatement("INSERT INTO Reserva (id_res,contribuicao_res,dataInicio_res,periodoReservado_res,observacao_res,servidor_res,cliente_res,ambiente_res) VALUES('0',?,?,?,?, (SELECT id_serv FROM servidor WHERE nome_serv = ? ),?, (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?))");
            //verificando oque está inserido no atributo de contribuição
            if(res.getContribuicao() == true){
                //passando valor para Inserir no banco
                inserir.setInt(1, 1);
            }else{
                //passando valor para Inserir no banco
                inserir.setInt(1, 0);
            }
            //Passando parametro para inserção
            inserir.setString(2, res.getDataInicio());
            //Passando parametro para inserção
            inserir.setString(3, res.getPeriodoReservado());
            //Passando parametro para inserção
            inserir.setString(4, res.getObservacao());
            //Passando parametro para inserção
            inserir.setString(5, res.getServ().getNome());
            //Passando parametro para inserção
            inserir.setInt(6, res.getId());
            //Passando parametro para inserção
            inserir.setString(7, res.getAmbi().getBloco());
            //Passando parametro para inserção
            inserir.setString(8, res.getAmbi().getSalaNum());
            
            //Executando query para realizar cadastro
            inserir.execute();
            //Encerrando execução
            inserir.close();

        } catch (SQLException erro) {
            //Lançando exceções
            throw new RuntimeException(erro);
        }

    }
    
    //Função para buscar id do cliente apartir do documento, nome e contato do cliente
    public String buscarIdCli(String doc, String nome, String contato){
        
        //ResultSet result = null;
        PreparedStatement validar = null;
        String cont = " ";
        
        try {
            //Query para selecionar o id do cliente apartir das informações passadas
            String selectSql = ("SELECT id_cli FROM cliente WHERE documento_cli = '" + doc + "' AND nome_cli = '" + nome + "' AND contato_Alter_serv = '" + contato + "'");
            
            //preparando query para execução
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);

            ResultSet rs = Stmt.executeQuery();
            
            //Armazenando id do cliente
            rs.next();
            cont=(rs.getString(1));
            
            //encerrando execução
            Stmt.close();
            //rs = null;
        } catch (Exception e) {
            //Lançando exceções
            throw new RuntimeException(e);
        }
        //Retornando o id
        return cont;
    }
    
    //Função para cadastrar a reserva e o cliente
    public void cadastrarReservaCli(Reserva res) {

        PreparedStatement inserir = null;

        try {
            //Preparando conexão pela query
            inserir = conectar.prepareStatement("INSERT INTO Reserva (id_res,contribuicao_res,dataInicio_res,periodoReservado_res,observacao_res,servidor_res,cliente_res,ambiente_res) VALUES('0',?,?,?,?, (SELECT id_serv FROM servidor WHERE nome_serv = ? ),(SELECT id_cli FROM cliente WHERE nome_cli = ? AND documento_cli = ? AND contato_Alter_serv = ? ), (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?))");
            //Verificação do que está inserido no atributo da conexão
            if(res.getContribuicao() == true){
                inserir.setInt(1, 1);
            }else{
                inserir.setInt(1, 0);
            }
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(2, res.getDataInicio());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(3, res.getPeriodoReservado());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(4, res.getObservacao());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(5, res.getServ().getNome());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(6, res.getClienteNome());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(7, res.getClienteDocumento());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(8, res.getClienteContato());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(9, res.getAmbi().getBloco());
            //Pegando valor do atributo e colocando como parâmetro para a inserção no banco
            inserir.setString(10, res.getAmbi().getSalaNum());
            
            //Iniciando execução da query
            inserir.execute();
            //Finalizando execução da query
            inserir.close();

        } catch (SQLException erro) {
            //Lançando erro
            throw new RuntimeException(erro);
        }

    }
    
    //Função para buscar se o periodo esta reservado em um dia especifico para aquela sala e bloco
    public String validarReserva(String bloco, String sala, String periodo, String data){
        
        //ResultSet result = null;
        PreparedStatement validar = null;
        String cont = " ";
        
        try {
            //Query para pesquisa
            String selectSql = ("SELECT COUNT(*) FROM reserva WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = '" + bloco + "' AND salaNum_ambi = '" + sala + "') AND periodoReservado_res = '" + periodo + "' AND dataInicio_res = '" + data + "'");
            
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);

            //Inicia a execução da query
            ResultSet rs = Stmt.executeQuery();
            rs.next();
            //Pegando resultado do select se for == 0 não existe a reserva se for 1 existe
            cont=(rs.getString(1));
            //Finaliza a execução da query
            Stmt.close();
            //rs = null;
        } catch (Exception e) {
            //Lançando erro
            throw new RuntimeException(e);
        }
        //Retornando resposta
        return cont;
    }
    
    
    //Função para buscar se existe um cliente com o documento passado
    public String buscarCliente(String documento){
        
        String cont = " ";
        
        
        try {
            //Definindo query para a pesquisa 
            String selectSql = ("SELECT COUNT(*) FROM cliente WHERE documento_cli = '" + documento + "'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);
            //Executando a query
            ResultSet rs = Stmt.executeQuery();
            
            rs.next();
            //Pegando resposta
            cont=(rs.getString(1));
            
        } catch (SQLException ex) {
            //Lançando erro
            JOptionPane.showMessageDialog(null, "Erro no cadastro do cliente: " + ex);
            throw new RuntimeException(ex);
        }
        //Retornando resposta
        return cont;

    }
    
    //Funçção para listar clientes com as mesmas informações passadas
    public String listarCliente(String documento, String nome, String contato){
        
        String cont = " ";
        
        
        try {
            //Query para busca clientes iguais
            String selectSql = ("SELECT COUNT(*) FROM cliente WHERE documento_cli = '" + documento + "' AND nome_cli = '" + nome + "' AND contato_Alter_serv = '" + contato +"'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);
            ResultSet rs = Stmt.executeQuery();
            
            rs.next();
            //Pegnado a resposta
            cont=(rs.getString(1));
            
            
        } catch (SQLException ex) {
            //Lançando janela de erro
            JOptionPane.showMessageDialog(null, "Erro no cadastro do cliente: " + ex);
            //Lançando erro
            throw new RuntimeException(ex);
        }
        //Retornando a resposta
        return cont;

    }
    
    //Função para editar reserva
    public void editarReserva(Reserva res) throws ParseException{
        
        PreparedStatement editar = null;
        
        try {
            //Definindo query para atualizar a tabela do sql
            editar = conectar.prepareStatement("UPDATE reserva SET contribuicao_res = ?, dataInicio_res = ?, periodoReservado_res = ?, observacao_res = ?, cliente_res = (SELECT id_cli FROM cliente WHERE nome_cli = ? AND documento_cli = ? AND contato_Alter_serv = ?) ,servidor_res = (SELECT id_serv FROM servidor WHERE nome_serv = ?), ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) WHERE id_res = ?");
            //Verificando oque está inserido no atributo de contruibuição 
            if(res.getContribuicao() == true){
                //passando o parâmetro equivalente a true
                editar.setInt(1, 1);
            }else{
                //Passando o parâmetro equivalente a false
                editar.setInt(1, 0);
            }
            //Passando parâmetro de data
            editar.setString(2, res.getDataInicio());
            //Passando parâmetro de Periodo reservado
            editar.setString(3, res.getPeriodoReservado());
            //Passando parâmetro de Observações
            editar.setString(4, res.getObservacao());
            //Passando parâmetro de Nome do cliente
            editar.setString(5, res.getClienteNome());
            //Passando parâmetro de Documento do cliente
            editar.setString(6, res.getClienteDocumento());
            //Passando parâmetro de Contato do cliente
            editar.setString(7, res.getClienteContato());
            //Passando parâmetro de Nome do servidor
            editar.setString(8, res.getServ().getNome());
            //Passando parâmetro de Bloco do ambiente
            editar.setString(9, res.getAmbi().getBloco());
            //Passando parâmetro de Sala do ambiente
            editar.setString(10, res.getAmbi().getSalaNum());
            //Passando parâmetro de id da reserva a ser editada
            editar.setInt(11, res.getId());
            
            //Executando a query
            editar.execute();
            //Janela de messagem de reserva editada
            JOptionPane.showMessageDialog(null, "Reserva Editada com Sucesso!");
            //Finalizando a query
            editar.close();

            
        } catch (SQLException erro) {
            //Lançando a exceção
            throw new RuntimeException(erro);
        }

    }
    
    //Função para pegar todos os ids das reservas para a sala especifica
    public List<Reserva> buscandoIds(int id){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            //Query de seleção 
            stmt = conectar.prepareStatement("SELECT id_res FROM reserva WHERE ambiente_res = ?");    
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            //Laço de repetição para pegar todas as reservas cadastradas
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.setId(rs.getInt("id_res"));
                                
                //Adicionando as informações salvas
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //Retornando reservas salvas
        return reservas;

    }
    
    public List<Reserva> buscandoServidores(int id){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            //Query de seleção 
            stmt = conectar.prepareStatement("SELECT id_res FROM reserva WHERE servidor_res = ?");    
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            //Laço de repetição para pegar todas as reservas cadastradas
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.setId(rs.getInt("id_res"));
                                
                //Adicionando as informações salvas
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //Retornando reservas salvas
        return reservas;

    }
    
    //Função para pegar os ids
    public String listarAmbientes(int id){
        
        String cont = " ";
        try {
            //Query para buscar total de ambientes com o id passado
            String selectSql = ("SELECT COUNT(*) FROM reserva WHERE ambiente_res = '" + id + "'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);
            ResultSet rs = Stmt.executeQuery();

            rs.next();
            //Pegando a resposta
            cont=(rs.getString(1));


        } catch (SQLException ex) {
            //Lançando janela de erro
            JOptionPane.showMessageDialog(null, "Erro na busca do ambiente: " + ex);
            //Lançando erro
            throw new RuntimeException(ex);
        }        
        //Retornando a resposta
        return cont;

    }
    
    //Função para pegar os ids
    public String listarServidores(int id){
        
        String cont = " ";
        try {
            //Query para buscar total de ambientes com o id passado
            String selectSql = ("SELECT COUNT(*) FROM reserva WHERE servidor_res = '" + id + "'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);
            ResultSet rs = Stmt.executeQuery();

            rs.next();
            //Pegando a resposta
            cont=(rs.getString(1));


        } catch (SQLException ex) {
            //Lançando janela de erro
            JOptionPane.showMessageDialog(null, "Erro na busca do ambiente: " + ex);
            //Lançando erro
            throw new RuntimeException(ex);
        }        
        //Retornando a resposta
        return cont;

    }
    
    
    //Função de excluir a Reserva
    public void excluirReserva(Reserva res){
        
        PreparedStatement excluir = null;
        try {
            //Query para deletar a reserva
            excluir = conectar.prepareStatement("DELETE FROM Reserva WHERE id_res = ?");
            //Passando parâmetro do id da reserva
            excluir.setInt(1, res.getId());
            //Executar query
            excluir.execute();
            //Finalizar execução da query
            excluir.close();
            //Janela de menssagem de que excluiu com sucesso
            JOptionPane.showMessageDialog(null, "Reserva Excluida com Sucesso!");
        } catch (SQLException ex) {
            //Janela de menssagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao Excluir a Reserva: " + ex);
        }

    }
    
    //Listando todas as reservas
    public List<Reserva> read(){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            //Query de seleção 
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) ORDER BY dataInicio_res");            
            rs = stmt.executeQuery();
            
            //Laço de repetição para pegar todas as reservas cadastradas
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                //Armazenando sala da reserva
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                //Armazenando data da reserva
                res.setDataInicio(rs.getString("DATAS"));
                                
                //Adicionando as informações salvas
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //Retornando reservas salvas
        return reservas;

    }
    
    //Função para filtrar por bloco
    public List<Reserva> readForDescBloc(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            //Query para pesquisar por bloco
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? ORDER BY dataInicio_res");
            //Passando bloco por parâmetro
            stmt.setString(1,desc);
            //Iniciando execução
            rs = stmt.executeQuery();
            
            //Laço de repetição para pegar todos os blocos por bloco
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                //Armazenando sala da reserva
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                //Armazenando data da reserva
                res.setDataInicio(rs.getString("DATAS"));
                //adicionando reserva salva a lista
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Janela de menssagem com erro
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //Retornando lista de reservas
        return reservas;

    }
    
    
    //Função para pesquisar por data
    public List<Reserva> readForDescDate(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            //Query para buscar por data
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE dataInicio_res = ? ORDER BY dataInicio_res");
            //Passando parâmetro de data
            stmt.setString(1,desc);
            //Iniciando execução da query
            rs = stmt.executeQuery();
            
            //Laço repetição para adicionar todas as reservas selecionadas
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                //Armazenando sala da reserva
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                //Armazenando data da reserva
                res.setDataInicio(rs.getString("DATAS"));
                
                //Adicionando todas as reservas salva a lista
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //janela de menssagem com erro
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //Retornando lista de reservas
        return reservas;

    }
    
    //Função de filtro por sala
    public List<Reserva> readForDescSala(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            //Query para pesquisar por sala
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE salaNum_ambi = ? ORDER BY dataInicio_res");
            //Passando parâmetro da sala a ser perquisada
            stmt.setString(1,desc);
            //Iniciando execução da query
            rs = stmt.executeQuery();
            
            //Laço repetição para adicionar todas as reservas selecionadas
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                //Armazenando sala da reserva
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                //Armazenando data da reserva
                res.setDataInicio(rs.getString("DATAS"));
                //Adicionando reservas salvas a lista
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Janela de menssagem com erro
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            //Lançando erro
            throw new RuntimeException(ex);
        }
        //Retornando lista de reservas
        return reservas;

    }
    
    //Função para filtrar por bloco e sala
    public List<Reserva> readForDescBlocSala(String desc, String desc2){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            //Query para pesquisar por bloco e sala
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? ORDER BY dataInicio_res");
            //Passando parâmetro do bloco
            stmt.setString(1,desc);
            //Passando parâmetro da sala
            stmt.setString(2,desc2);
            //Iniciando execução da query
            rs = stmt.executeQuery();
            
            //Laço de repetição para adicionar todas as reservas buscadas
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                //Armazenando sala da reserva
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                //Armazenando data da reserva
                res.setDataInicio(rs.getString("DATAS"));
                //Adicionando reservas salvas a lista                
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Janela de menssagem com erro
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            //Lançando erro
            throw new RuntimeException(ex);
        }
        //retornando lista de reservas
        return reservas;

    }
    
    //Função para filtar por bloco, sala e data
    public List<Reserva> readForDescBlocSalaDate(String desc, String desc2, String desc3){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            //Query para buscar por bloco, sala e data
            stmt = conectar.prepareStatement("SELECT DISTINCT bloco_ambi as BLOCO, salaNum_ambi as SALA, dataInicio_res as DATAS FROM ambiente INNER JOIN reserva ON (ambiente.id_ambi = reserva.ambiente_res) WHERE bloco_ambi = ? AND salaNum_ambi = ? AND dataInicio_res = ? ORDER BY dataInicio_res");
            //Parâmetro do bloco
            stmt.setString(1,desc);
            //Parâmetro da sala
            stmt.setString(2,desc2);
            //Parâmetro da data
            stmt.setString(3,desc3);
            //Iniciando execução da query
            rs = stmt.executeQuery();
            
            //Laço de repetição para adicionar todas as reserva a lista
            while(rs.next()){
                Reserva res = new Reserva();
                //Armazenando bloco da reserva
                res.getAmbi().setBloco(rs.getString("BLOCO"));
                //Armazenando sala da reserva
                res.getAmbi().setSalaNum(rs.getString("SALA"));
                //Armazenando data da reserva
                res.setDataInicio(rs.getString("DATAS"));
                                
                //Adicionando reservas salvas a lista
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Janela de menssagem com erro
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
            //Lançando erro
            throw new RuntimeException(ex);
        }
        //Retornando lista de reservas
        return reservas;

    }    
}
