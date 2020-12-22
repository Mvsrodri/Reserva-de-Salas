package Bean;


public class Permissao {
    private String tipo;
    
    public Permissao(){
        tipo = "";
    }
    
    public Permissao(String tipo){
        this.tipo = tipo;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}

