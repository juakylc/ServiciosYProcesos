package serviciosyprocesos;

public class HiloBienHecho implements Runnable{

	Persona p;
	int j;
	
	public HiloBienHecho(Persona p, int j) {
		this.p = p;
		this.j = j;
	}
	
	@Override
	public void run() {
		synchronized(p) {
			for (int i=j; i<500001; i = i + 2) {
				p.setNumeroSaludosRecibidos(p.getNumeroSaludosRecibidos() + 1);
				System.out.println("Hola Tellez por " + p.getNumeroSaludosRecibidos() + "º vez");
			}
		}
		
	}

}
