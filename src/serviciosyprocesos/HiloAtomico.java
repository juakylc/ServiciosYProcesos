package serviciosyprocesos;

import java.util.concurrent.atomic.AtomicInteger;

public class HiloAtomico implements Runnable{

	PersonaAtomica p;
	int j;
	
	public HiloAtomico(PersonaAtomica p, int j) {
		this.p = p;
		this.j = j;
	}
	
	@Override
	public void run() {
		for (int i=j; i<500001; i = i + 2) {
			p.getNumeroSaludosRecibidos().getAndIncrement();
			System.out.println("Hola Tellez por " + p.getNumeroSaludosRecibidos() + "º vez");
		}
		
	}

}
