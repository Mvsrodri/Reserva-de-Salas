package Bean;

import Bean.Permissao;

public abstract class Servidor {

    private String nome;
    private int siape;
    private String email;
    private int ramal;
    private String senha;
    private String permissao;
    private String conPessoal;
    private String login;

    public Servidor() {
        nome = "";
        siape = 0;
        email = "";
        ramal = 0;
        senha = "";
        permissao = "";
        conPessoal = "";
        login = "";
    }

    public Servidor(String nome, int siape, String email, int ramal, String senha, String permissao, String conPessoal, String login) {
        this.nome = nome;
        this.siape = siape;
        this.email = email;
        this.ramal = ramal;
        this.senha = senha;
        this.permissao = permissao;
        this.conPessoal = conPessoal;
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRamal() {
        return ramal;
    }

    public void setRamal(int ramal) {
        this.ramal = ramal;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getConPessoal() {
        return conPessoal;
    }

    public void setConPessoal(String conPessoal) {
        this.conPessoal = conPessoal;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
