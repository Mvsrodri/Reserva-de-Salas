
package Bean;


public class Funcionario extends Servidor {
   private Reserva rese;
   
   public Funcionario(){
       rese = new Reserva();
   }
   
   public Funcionario(Reserva rese){
       this.rese = rese;
   }
   
   public Reserva getRese(){
       return rese;
   }
   
   public void setRese(Reserva rese){
       this.rese = rese;
   }
}
