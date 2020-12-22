package Dao;

import Connection.ConnectionFactory;
import Bean.Administrador;
import Bean.Ambiente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AdministradorDAO {

    public Connection conectar;
    
    public AdministradorDAO() {
        this.conectar = new ConnectionFactory().conectar();
    }
    //Cadastrar

    //Cadastrar
    public void cadastrarServidor(Administrador admi) {
        PreparedStatement inserir = null;

        try {
            //Preparando query
            inserir = conectar.prepareStatement("INSERT INTO Servidor (id_serv,nome_serv,email_serv,ramal_serv,permissao_serv,contato_Pessoal_serv,login_serv,senha_serv)VALUES(?,?,?,?,?,?,?,?)");
            //Pegando siape como parâmetro
            inserir.setInt(1, admi.getSiape());
            //Pegando nome como parâmetro
            inserir.setString(2, admi.getNome());
            //Pegando email como parâmetro
            inserir.setString(3, admi.getEmail());
            //Pegando ramal como parâmetro
            inserir.setInt(4, admi.getRamal());
            //Pegando permissao como parâmetro
            inserir.setString(5, admi.getPermissao());
            //Pegando contato como parâmetro
            inserir.setString(6, admi.getConPessoal());
            //Pegando login como parâmetro
            inserir.setString(7, admi.getLogin());
            //Pegando senha como parâmetro
            inserir.setString(8, admi.getSenha());
            //Iniciando execução da query
            inserir.execute();
            //Fechando execução da query
            inserir.close();

            
        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }

    }

    //Editar servidor
    public void editarServidor(Administrador admi) {

        PreparedStatement editar = null;

        try {
            //Pegando query de atualização
            editar = conectar.prepareStatement("UPDATE Servidor SET  nome_serv = ?,email_serv = ?,ramal_serv = ? ,permissao_serv = ?,contato_Pessoal_serv = ?,login_serv = ?,senha_serv = ? WHERE id_serv = ?");
            //Pegando nome como parâmetro
            editar.setString(1, admi.getNome());
            //Pegando email como parâmetro
            editar.setString(2, admi.getEmail());
            //Pegando ramal como parâmetro
            editar.setInt(3, admi.getRamal());
            //Pegando permissao como parâmetro
            editar.setString(4, admi.getPermissao());
            //Pegando contato como parâmetro
            editar.setString(5, admi.getConPessoal());
            //Pegando login como parâmetro
            editar.setString(6, admi.getLogin());
            //Pegando senha como parâmetro
            editar.setString(7, admi.getSenha());
            //Pegando siape como parâmetro
            editar.setInt(8, admi.getSiape());
            //Iniciando execução
            editar.execute();
            //Encerrando execução
            editar.close();

        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }

    }

    //Excluir servidor
    public void excluirServidor(Administrador admi) {

        PreparedStatement excluir = null;

        try {
            //Query para deletar servidor
            excluir = conectar.prepareStatement("DELETE FROM Servidor WHERE id_serv = ?");
            //Pegando siape como parâmetro
            excluir.setInt(1, admi.getSiape());
            //Iniciando execução
            excluir.execute();
            //Encerrando execução
            excluir.close();

            
        } catch (SQLException ex) {
            //Lançando exceção
            JOptionPane.showMessageDialog(null, "Erro ao Excluir o Cadastro: " + ex);
        }

    }
    //Verificar se existe um servidor com as informações passadas
    public String buscarInfos(String email, String contato, String login, String senha){
        
        String cont = " ";
        
        try {
            //Definindo query para busca 
            String selectSql = ("SELECT COUNT(*) FROM servidor WHERE email_serv = '" + email+ "' OR contato_Pessoal_serv = '" + contato + "' OR login_serv = '"+ login +"' OR senha_serv = '" + senha +"'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);
            //Executando a query
            ResultSet rs = Stmt.executeQuery();
            
            rs.next();
            //Pegando resposta
            cont=(rs.getString(1));
            
        } catch (SQLException ex) {
            //Lançando erro
            JOptionPane.showMessageDialog(null, "Erro na busca do servidor: " + ex);
            throw new RuntimeException(ex);
        }
        //Retornando resposta
        return cont;

    }
    
    //Buscar siape
    public String buscarSiape(String siape){
        
        String cont = " ";
        
        try {
            //Definindo query para a pesquisa 
            String selectSql = ("SELECT COUNT(*) FROM servidor WHERE id_serv = '" + siape+ "'");
            PreparedStatement Stmt = conectar.prepareStatement(selectSql);
            //Executando a query
            ResultSet rs = Stmt.executeQuery();
            
            rs.next();
            //Pegando resposta
            cont=(rs.getString(1));
            
        } catch (SQLException ex) {
            //Lançando erro
            JOptionPane.showMessageDialog(null, "Erro na busca do servidor: " + ex);
            throw new RuntimeException(ex);
        }
        //Retornando resposta
        return cont;

    }

    //Consultar servidores com o mesmo id
    public List<Administrador> consultarServidor(int siape) {

        PreparedStatement consulta = null;
        ResultSet resultado = null;

        List<Administrador> admi = new ArrayList<>();

        try {
            //Query para encontrar servidores com ids iguais
            consulta = conectar.prepareStatement("SELECT * FROM Servidor WHERE id_serv LIKE ?");
            //Pegando siape como parâmetro
            consulta.setInt(1, siape);
            //Executando query
            resultado = consulta.executeQuery();
            //Enquanto ouver pegar informações
            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setSiape(resultado.getInt("id_serv"));
                admin.setNome(resultado.getString("nome_serv"));
                admin.setEmail(resultado.getString("email_serv"));
                admin.setRamal(resultado.getInt("ramal_serv"));
                admin.setEmail(resultado.getString("email_serv"));
                admin.setPermissao(resultado.getString("permissao_serv"));
                admin.setConPessoal(resultado.getString("contato_Pessoal_serv"));
                admin.setLogin(resultado.getString("login_serv"));
                admin.setSenha(resultado.getString("senha_serv"));
                //Salvando na lista
                admi.add(admin);
            }

        } catch (SQLException erro) {
            //Lançando exceção
            throw new RuntimeException(erro);
        }
        //Retornando lista de servidor
        return admi;

    }
    
    //Listar todos os servidores
    public List<Administrador> listarServidor() {

        PreparedStatement listar = null;
        ResultSet resultado = null;

        List<Administrador> Servidor = new ArrayList<>();

        try {
            //Query para buscar todos os servidores
            listar = conectar.prepareStatement("SELECT * FROM Servidor");
            //Executando query
            resultado = listar.executeQuery();
            //Enquanto ouver pegar informações
            while (resultado.next()) {

                Administrador admin = new Administrador();

                admin.setSiape(resultado.getInt("id_serv"));
                admin.setNome(resultado.getString("nome_serv"));
                admin.setEmail(resultado.getString("email_serv"));
                admin.setRamal(resultado.getInt("ramal_serv"));
                admin.setPermissao(resultado.getString("permissao_serv"));
                admin.setConPessoal(resultado.getString("contato_Pessoal_serv"));
                admin.setLogin(resultado.getString("login_serv"));
                admin.setSenha(resultado.getString("senha_serv"));
                Servidor.add(admin);

            }
            return Servidor;

        } catch (SQLException erro) {
            //Lançando exceções
            throw new RuntimeException(erro);
        }

    }

}
