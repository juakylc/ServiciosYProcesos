package serviciosyprocesos;

public class TIC extends Thread{
	  final Object semaforo;
	  
	  public TIC(String nombre, Object semaforo) {
	    super(nombre);
	    this.semaforo = semaforo;
	  }
	  
	  @Override
	  public void run() {
	    while (true) {
	        try {
	              System.out.println("TIC...");
	              Thread.sleep(1000);            
	              synchronized(semaforo) {
	                semaforo.notify();
	                semaforo.wait();
	              }
	        } catch (InterruptedException e) { 
	                System.out.println("Hilo TIC interrumpido");
	                return;
	        }      
	    } 
	  }     
	}