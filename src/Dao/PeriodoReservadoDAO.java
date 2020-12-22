package Dao;

import Bean.Reserva;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class PeriodoReservadoDAO {
    //Recriando metodo de criar conexão da classe ConnectionFactory
    public Connection conectar;
    
    public PeriodoReservadoDAO(){
        //conectar da classe recebendo parâmetrodos da classe ConnectionFactory
        this.conectar = new ConnectionFactory().conectar();
    }
   
    //Função para ler as reservas cadastradas das informações passadas
    public List<Reserva> read(String data, String bloco, String sala){
              
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            //Query para realizar a busca das informações
            stmt = conectar.prepareStatement("SELECT periodoReservado_res as PERIODO, nome_cli as CLIENTE, nome_serv as SERVIDOR, contribuicao_res as CONTRIBUICAO, observacao_res as OBSERVACOES FROM reserva INNER JOIN cliente ON (reserva.cliente_res = cliente.id_cli) INNER JOIN servidor ON (reserva.servidor_res = servidor.id_serv) WHERE dataInicio_res = ? AND ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) ORDER BY PERIODO");
            //Passando parâmetro da sala
            stmt.setString(1, sala);
            //Passando parâmetro da data
            stmt.setString(2, data);
            //Passando parâmetro do bloco
            stmt.setString(3, bloco);
            //Executando query
            rs = stmt.executeQuery();
            //Laço de repetição para adicionar todas as reservas a lista
            while(rs.next()){
                Reserva res = new Reserva();
                
                //Salvando valor pego do periodo
                res.setPeriodoReservado(rs.getString("PERIODO"));
                //Salvando valor pego do nome cliente
                res.setClienteNome(rs.getString("CLIENTE"));
                //Salvando valor pego do nome servidor
                res.getServ().setNome(rs.getString("SERVIDOR"));
                //Verificação do que está inserido na contribuição
                if(rs.getInt("CONTRIBUICAO")==1){
                    //Salvando valor pego da contribuição
                    res.setContribuicao(true);
                }else{
                    //Salvando valor pego da contribuição
                    res.setContribuicao(false);
                }
                //Salvando valor pego das observações
                res.setObservacao(rs.getString("OBSERVACOES"));
                //adicionando a reserva salva a lista de reservas
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //retornando lista de reservas
        return reservas;

    }
    
    //Leitura da reserva que será editado
    public List<Reserva> readEdit(String data, String bloco, String sala, String periodo){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            //Query para busca da reserva que será editada
            stmt = conectar.prepareStatement("SELECT id_res as ID, periodoReservado_res as PERIODO, nome_cli as NOME, contato_Alter_serv as CONTATO, documento_cli AS DOCUMENTO, contribuicao_res as CONTRIBUICAO, dataInicio_res as DATAS, observacao_res as OBSERVACAO, nome_serv as SERVIDOR FROM reserva INNER JOIN cliente ON (reserva.cliente_res = cliente.id_cli) INNER JOIN servidor ON (reserva.servidor_res = servidor.id_serv) WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) AND periodoReservado_res = ? AND dataInicio_res = ?");            
            //Passando parâmetro do bloco
            stmt.setString(1,bloco);
            //Passando parâmetro da sala
            stmt.setString(2,sala);
            //Passando parâmetro do periodo
            stmt.setString(3,periodo);
            //Passando parâmetro da data
            stmt.setString(4,data);
            //Executando query
            rs = stmt.executeQuery();
            
            //Laço de repetição para acionar a reserva encontrada
            while(rs.next()){
                Reserva res = new Reserva();
                //Salvando valor pego do periodo
                res.setPeriodoReservado(rs.getString("PERIODO"));
                //Salvando valor pego do nome cliente
                res.setClienteNome(rs.getString("NOME"));
                //Salvando valor pego do contato cliente
                res.setClienteContato(rs.getString("CONTATO"));
                //Salvando valor pego do documento cliente
                res.setClienteDocumento(rs.getString("DOCUMENTO"));
                //Verificação do valor que foi pego da contribuição
                if((Integer.parseInt(rs.getString("CONTRIBUICAO")))==0){
                    //Salvando valor pego da contribuição
                    res.setContribuicao(true);
                }else{
                    //Salvando valor pego da contribuição
                    res.setContribuicao(false);
                }
                //Salvando valor pego da data
                res.setDataInicio(rs.getString("DATAS"));
                //Salvando valor pego das observações
                res.setObservacao(rs.getString("OBSERVACAO"));
                //Salvando valor pego do nome servidor
                res.getServ().setNome(rs.getString("SERVIDOR"));
                //Salvando valor pego do id da reserva
                res.setId(rs.getInt("ID"));
                                
                //Adicionando reserva a lista de reserva
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Lançando exceção
            throw new RuntimeException(ex);
        }
        //Retornando lista de reservas
        return reservas;

    }
    
    //Função para Buscar id da reserva
    public List<Reserva> buscarId(String data, String bloco, String sala, String periodo){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        
        try {
            //Query para pegar id da reserva
            stmt = conectar.prepareStatement("SELECT id_res as ID, periodoReservado_res as PERIODO, nome_cli as NOME, contato_Alter_serv as CONTATO, documento_cli AS DOCUMENTO, contribuicao_res as CONTRIBUICAO, dataInicio_res as DATAS, observacao_res as OBSERVACAO, nome_serv as SERVIDOR FROM reserva INNER JOIN cliente ON (reserva.cliente_res = cliente.id_cli) INNER JOIN servidor ON (reserva.servidor_res = servidor.id_serv) WHERE ambiente_res = (SELECT id_ambi FROM ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ?) AND periodoReservado_res = ? AND dataInicio_res = ?");            
            //Passando parâmetro do bloco
            stmt.setString(1,bloco);
            //Passando parâmetro da sala
            stmt.setString(2,sala);
            //Passando parâmetro do periodo
            stmt.setString(3,periodo);
            //Passando parâmetro da ddata
            stmt.setString(4,data);
            //Iniciando execução da query
            rs = stmt.executeQuery();
            
            //Laço de repetição para adicionar reservas a lista
            while(rs.next()){
                Reserva res = new Reserva();
                //Salvando valor pego do periodo
                res.setPeriodoReservado(rs.getString("PERIODO"));
                //Salvando valor pego do nome cliente
                res.setClienteNome(rs.getString("NOME"));
                //Salvando valor pego do contato cliente
                res.setClienteContato(rs.getString("CONTATO"));
                //Salvando valor pego do documento cliente
                res.setClienteDocumento(rs.getString("DOCUMENTO"));
                //Verificação do que está inserido na contribuição
                if((Integer.parseInt(rs.getString("CONTRIBUICAO")))==0){
                    //Salvando valor pego da contribuição
                    res.setContribuicao(true);
                }else{
                    //Salvando valor pego da contribuição
                    res.setContribuicao(false);
                }
                //Salvando valor pego da data
                res.setDataInicio(rs.getString("DATAS"));
                //Salvando valor pego das observações
                res.setObservacao(rs.getString("OBSERVACAO"));
                //Salvando valor pego do nome servidor
                res.getServ().setNome(rs.getString("SERVIDOR"));
                //Salvando valor pego do id da reserva
                res.setId(rs.getInt("ID"));
                //Adicionando reserva a lista de reservas
                reservas.add(res);
            }
        } catch (SQLException ex) {
            //Lançando erro
            throw new RuntimeException(ex);
        }
        //Retornando lista de reserva
        return reservas;

    }
    
    //Realizando filto de pesquisa por data
    public List<Reserva> readForDescData(String desc){
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reserva> reservas = new ArrayList<>(); 
        try {
            //Query de busca de reservas por data
            stmt = conectar.prepareStatement("SELECT * FROM reserva WHERE dataInicio_res = ?");
            //Passando parâmetro da data
            stmt.setString(1,desc);
            //Iniciando execução da query
            rs = stmt.executeQuery();
            //Laço de repetição para salvas as reservas
            while(rs.next()){
                Reserva res = new Reserva();
                //Salvando valor pego da data
                res.setDataInicio(rs.getString("dataInicio_res"));
                //Salvando valor pego do periodo
                res.setPeriodoReservado(rs.getString("periodoReservado_res"));
                //Salvando valor pego das observações
                res.setObservacao(rs.getString("observacao_res"));
                //Adicionando reserva salva a lista de reservas
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
