package serviciosyprocesos;

import java.util.Collection;

public class HiloSemaforo implements Runnable{

	Persona p;
	int j;
	Object sem;
	
	public HiloSemaforo(Persona p, int j, Object sem) {
		this.p = p;
		this.j = j;
		this.sem = sem;
	}
	
	@Override
	public void run() {
		synchronized(sem) {
			for (int i=j; i<500001; i = i + 2) {
				p.setNumeroSaludosRecibidos(p.getNumeroSaludosRecibidos() + 1);
				System.out.println("Hola Tellez por " + p.getNumeroSaludosRecibidos() + "º vez");
			}
		}
	}

}
