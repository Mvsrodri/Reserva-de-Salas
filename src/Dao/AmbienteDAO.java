package Dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Bean.Ambiente;

public class AmbienteDAO {
    public Connection conectar;
    
    public AmbienteDAO(){
        this.conectar = new ConnectionFactory().conectar();
    }
    
    //Cadastrar Ambiente
    public void cadastrarAmbiente(Ambiente ambi){
   
        PreparedStatement inserir = null;
        
         try {
            //Query para inserção do novo ambiente
            inserir = conectar.prepareStatement("INSERT INTO Ambiente (bloco_ambi,salaNum_ambi,tipoSala_ambi)VALUES(?,?,?)");
            //Passando bloco como parâmetro
            inserir.setString(1, ambi.getBloco());
            //Passando numero da sala como parâmetro
            inserir.setString(2, ambi.getSalaNum());
            //Passando tipo da sala como parâmetro
            inserir.setString(3, ambi.getTipoSala());
            //Iniciando execução da query
            inserir.execute();
            //Encerrando execução
            inserir.close();   
        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        } 
    }
    
    //Editar ambiente
    public void editarAmbiente(Ambiente ambi){
        
        PreparedStatement editar = null;
        
        try {
            //Query para atualizar o ambiente
            editar = conectar.prepareStatement("UPDATE Ambiente SET bloco_ambi = ? ,salaNum_ambi = ?,tipoSala_ambi = ? WHERE id_ambi = ?");
            //Passando bloco como parâmetro
            editar.setString(1, ambi.getBloco());
            //Passando numero da sala como parâmetro
            editar.setString(2, ambi.getSalaNum());
            //Passando tipo da sala como parâmetro
            editar.setString(3, ambi.getTipoSala());
            //Passando id como parâmetro
            editar.setInt(4, ambi.getId());
            //Iniciando execução
            editar.execute();
            //Finalizando execução
            editar.close();            
        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }
    }
    
    //Excluindo ambiente
    public void excluirAmbiente(Ambiente ambi){
        
        PreparedStatement excluir = null;
        
        try {
            //Query para deletar o ambiente selecionado
            excluir = conectar.prepareStatement("DELETE FROM Ambiente WHERE id_ambi = ?");
            //Passando id como parâmetro
            excluir.setInt(1, ambi.getId());
            //Iniciando execução da query
            excluir.execute();
            //Finalizando execução da query
            excluir.close();

            
        } catch (SQLException ex) {
            //Lançando exceção
            JOptionPane.showMessageDialog(null, "Erro ao Excluir o Ambiente: " + ex);
        }

    }
    
    //Buscar numero da sala
    public String buscarSalaNum(String bloco, String sala){
        
        String cont = " ";
        
        try {
            //Definindo query para a pesquisa 
            String selectSql = ("SELECT COUNT(*) FROM ambiente WHERE bloco_ambi = '" + bloco+ "' AND salaNum_ambi = '"+ sala +"'");
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
    
    //Listando todos os bloco, ordenando pelo bloco
    public List<Ambiente> listarAmbientes(){
        
        PreparedStatement listar = null;
        ResultSet resultado = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            //Query para buscar todos os ambientes ordenando por bloco
            listar  = conectar.prepareStatement("SELECT * FROM Ambiente ORDER BY bloco_ambi desc");
            //Executando query
            resultado = listar.executeQuery();
            //Laço de repetição para armazenar as informações do ambiente
            while (resultado.next()) {

                Ambiente ambiente = new Ambiente();

                ambiente.setId(resultado.getInt("id_ambi"));
                ambiente.setBloco(resultado.getString("bloco_ambi"));
                ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                ambientes.add(ambiente);
                
            }
            //retornando ambiente
            return ambientes;

        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }

    }
    
    //Listar todos os blocos
    public List<Ambiente> listarBloco(){
        
        PreparedStatement listarB = null;
        ResultSet resultadoB = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            //Query de seleção dos blocos
            listarB  = conectar.prepareStatement("SELECT DISTINCT bloco_ambi FROM Ambiente");
            //executando query
            resultadoB = listarB.executeQuery();
            //Laço de repetição para pegar os blocos buscados
            while (resultadoB.next()) {
                Ambiente ambiente = new Ambiente();
                ambiente.setBloco(resultadoB.getString("bloco_ambi"));
                ambientes.add(ambiente);
            }
            //Retornando ambientes
            return ambientes;

        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }

    }
    
    //Listando todas as salas referentes aos blocos
    public List<Ambiente> listarSala(String bloco){
        
        PreparedStatement listarS = null;
        ResultSet resultadoS = null;

        List<Ambiente> ambientes = new ArrayList<>();

        try {
            //Query para buscar as salas
            listarS  = conectar.prepareStatement("SELECT salaNum_ambi FROM ambiente WHERE bloco_ambi = ?");
            //Passando bloco como parâmetro
            listarS.setString(1, bloco);
            //Executando query para buscar as salas
            resultadoS = listarS.executeQuery();
            
            //Laço de repetição para pegar os numeros das salas
            while (resultadoS.next()) {
                Ambiente ambiente = new Ambiente();
                ambiente.setSalaNum(resultadoS.getString("salaNum_ambi"));
                ambientes.add(ambiente);
            }
            //Retornando ambientes
            return ambientes;

        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }

    }
    
    //Consultando ambiente por bloco e sala
    public List<Ambiente> consultarAmbiente(String bloco,String salaNum ) {
      
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        List<Ambiente> ambientes = new ArrayList<>();
        
        //Verificação se é uma busca só por sala
        if(bloco.equals(" ")){
            try {
                //Query de seleção
                consulta = conectar.prepareStatement("SELECT * FROM Ambiente WHERE salaNum_ambi = ? ORDER BY bloco_ambi");
                //Passando sala como parâmetro
                consulta.setString(1, salaNum);
                //Iniciando execução da query
                resultado = consulta.executeQuery();
                //Laço de repetição para armazenar informações
                while (resultado.next()) {

                    Ambiente ambiente = new Ambiente();

                    ambiente.setId(resultado.getInt("id_ambi"));
                    ambiente.setBloco(resultado.getString("bloco_ambi"));
                    ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                    ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                    ambientes.add(ambiente);
                }

            } catch (SQLException erro) {
                //Lançando exceção
                throw new RuntimeException(erro);
            } 
        //Verificando se a pesquisa é por bloco
        }else if(salaNum.equals(" ")){
            try {
                //Query de pesquisa pelo bloco
                consulta = conectar.prepareStatement("SELECT * FROM Ambiente WHERE bloco_ambi = ? ORDER BY salaNum_ambi");
                //Passando bloco como parâmetro
                consulta.setString(1, bloco);
                //Iniciando execução da query
                resultado = consulta.executeQuery();
                //Laço de repetição para pegar informações do ambiente
                while (resultado.next()) {

                    Ambiente ambiente = new Ambiente();

                    ambiente.setId(resultado.getInt("id_ambi"));
                    ambiente.setBloco(resultado.getString("bloco_ambi"));
                    ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                    ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                    ambientes.add(ambiente);
                }

            } catch (SQLException erro) {
                //Lançando exceção
                throw new RuntimeException(erro);
            } 
        //Verificano se não foi passada informação para a pesquisa
        }else if(bloco.equals(" ") && salaNum.equals(" ")){
            JOptionPane.showConfirmDialog(null, "Não foi selecionado nenhuma informação para pesquisa");
        }else{
            try {
                //Pegando query de seleção para busca ambiente por bloco e sala
                consulta = conectar.prepareStatement("SELECT * FROM Ambiente WHERE bloco_ambi = ? AND salaNum_ambi = ? ");
                //Passando bloco como parâmetro
                consulta.setString(1, bloco);
                //Passando salaNum como parâmetro
                consulta.setString(2, salaNum);
                //Executando query
                resultado = consulta.executeQuery();
                //Laço de repetição para popular a classe ambiente
                while (resultado.next()) {

                    Ambiente ambiente = new Ambiente();

                    ambiente.setId(resultado.getInt("id_ambi"));
                    ambiente.setBloco(resultado.getString("bloco_ambi"));
                    ambiente.setSalaNum(resultado.getString("salaNum_ambi"));
                    ambiente.setTipoSala(resultado.getString("tipoSala_ambi"));
                    ambientes.add(ambiente);
                }

            } catch (SQLException erro) {
                //Lançando exceção
                throw new RuntimeException(erro);
            } 
        }
        //Retornando ambiente
        return ambientes;
    }
}

