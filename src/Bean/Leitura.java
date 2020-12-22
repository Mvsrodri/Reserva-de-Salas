
package Bean;


import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
//Classe Leitura;
public class Leitura{
    public String  inData(String rotulo){
        System.out.println(rotulo);

        InputStreamReader tec = new InputStreamReader(System.in);
        BufferedReader buff = new BufferedReader(tec);
        String ret = "";
        try{
            ret = buff.readLine();
        }
        catch(IOException ioe){
            System.out.println("\nErro no Sistema");
        }
        return ret;
    }
}
