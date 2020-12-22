package Bean;

public class Administrador extends Servidor{
    private Ambiente ambi;

    public Administrador(){
        ambi = new Ambiente();
    }

    public Administrador(Ambiente ambi){
        this.ambi = ambi;
    }

    public Ambiente getAmbi(){
        return ambi;
    }

    public void setAmbi(Ambiente ambi){
        this.ambi = ambi;
    }

    public void cadastrarPermissao(){

    }
}
