
package Bean;


public class Reserva {
    private int id;
    private String clienteNome;
    private String clienteContato;
    private String clienteDocumento;
    private String periodoReservado;
    private String dataInicio;
    private String observacao;
    private boolean contribuicaoReserva;
    private int periodoId;/*Chamando a classe Periodo */
    private Ambiente ambi;/*Chamando a classe Ambiente*/
    private int ambienteId;/*Chamando a classe Ambiente*/
    private int funcionarioId;//Chamando a classe Funcionario
    private Servidor serv;/*Chamando a classe Servidor*/
    
    public Reserva(){
        id = 0;
        clienteNome = "";
        clienteContato = "";
        clienteDocumento = "";
        periodoReservado = "";
        dataInicio = "";
        observacao = "";
        contribuicaoReserva = false;
        periodoId = 0;
        ambienteId = 0;
        funcionarioId = 0;
        serv = new Servidor() {};
        ambi = new Ambiente();
    }
    
    public Reserva( String observacao, boolean contribuicaoReserva, int periodoId,int ambienteId, String dataInicio, String periodoReservado,int funcionarioId, String clienteNome, String clienteContato, String clienteDocumento){
        this.periodoReservado = periodoReservado;
        this.dataInicio = dataInicio;
        this.observacao = observacao;
        this.contribuicaoReserva = contribuicaoReserva;
        this.periodoId = periodoId;
        this.ambienteId = ambienteId;
        this.funcionarioId = funcionarioId;
        this.clienteNome = clienteNome;
        this.clienteContato = clienteContato;
        this.clienteDocumento = clienteDocumento;
        this.serv = serv;
        this.ambi = ambi;
    }
    
   
    
    public String getClienteDocumento(){
        return clienteDocumento;
    }
    
    public void setClienteDocumento(String clienteDocumento){
        this.clienteDocumento = clienteDocumento;
    }
    
    public String getClienteNome(){
        return clienteNome;
    }
    
    public void setClienteNome(String clienteNome){
        this.clienteNome = clienteNome;
    }
    
    public String getClienteContato(){
        return clienteContato;
    }
    
    public void setClienteContato(String clienteContato){
         this.clienteContato = clienteContato;
    }
    
    public String getPeriodoReservado(){
        return periodoReservado;
    }
    
    public void setPeriodoReservado(String periodoReservado){
        this.periodoReservado = periodoReservado ;
    }
    
    public String getDataInicio(){
        return dataInicio;
    }
    
    public void setDataInicio(String dataInicio){
        this.dataInicio = dataInicio;
    }
    
    public String getObservacao(){
        return observacao;
    }
    
    public void setObservacao(String observacao){
        this.observacao = observacao;
    }
    
    public boolean getContribuicao(){
        return contribuicaoReserva;
    }
    
    public void setContribuicao(boolean contribuicaoReserva){
        this.contribuicaoReserva = contribuicaoReserva;
    }
    
    public int getAmbiente(){
        return ambienteId;
    }
    
    public void setAmbiente(Ambiente ambi){
        this.ambienteId = ambienteId;
    }
    
    public int getFuncionario(){
        return funcionarioId;
    }
    
    public void setFuncionario(Funcionario fun){
        this.funcionarioId = funcionarioId;
    }
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Servidor getServ(){
        return serv;
    }
    
    public void setServ(Servidor serv){
        this.serv = serv;
    }
    
    public Ambiente getAmbi(){
        return ambi;
    }
    
    public void setAmbi(Ambiente ambi){
        this.ambi = ambi;
    }
}