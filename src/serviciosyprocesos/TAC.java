package serviciosyprocesos;

public class TAC extends Thread{
	  final Object semaforo;
	  
	  public TAC(String nombre, Object semaforo) {
	    super(nombre);
	    this.semaforo = semaforo;
	  }
	  
	  @Override
	  public void run() {
	    while (true) {
	        try {
	              System.out.println("TAC...");
	              Thread.sleep(1000);            
	              synchronized(semaforo) { 
	                semaforo.notify();
	                semaforo.wait();
	              }
	        } catch (InterruptedException e) { 
	                System.out.println("Hilo TAC interrumpido");
	                return;
	        }      
	    } 
	  }
	}