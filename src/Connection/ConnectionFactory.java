
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    
    public static Connection conectar(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Nome_do_Banco?useTimezone=true&serverTimezone=UTC","root","Senha_do_usuario");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}

