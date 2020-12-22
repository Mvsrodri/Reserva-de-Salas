
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    
    public static Connection conectar(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_reserva?useTimezone=true&serverTimezone=UTC","root","3621");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}

