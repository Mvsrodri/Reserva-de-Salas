
package Bean;

public class Ambiente {
    private int id; 
    private String bloco;
    private String salaNum;
    private String tipoSala;
    
    
    public Ambiente(){
        id = 0;
        bloco = "";
        salaNum = "";
        tipoSala = "";
        
    }
    
    public Ambiente(int id,String bloco, String salaNum, String tipoSala){
        this.id = id;
        this.bloco = bloco;
        this.salaNum = salaNum;
        this.tipoSala = tipoSala;
      
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public String getBloco(){
        return bloco;
    }
    
    public void setBloco(String bloco){
        this.bloco = bloco;
    }
    
    public String getSalaNum(){
        return salaNum;
    }
    
    public void setSalaNum(String salaNum){
        this.salaNum = salaNum;
    }
    
    public String getTipoSala(){
        return tipoSala;
    }
    
    public void setTipoSala(String tipoSala){
        this.tipoSala = tipoSala;
    }
    
    
}
