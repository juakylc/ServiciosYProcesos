package serviciosyprocesos;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonaAtomica {
	String nombre;
	AtomicInteger numeroSaludosRecibidos;
	
	public PersonaAtomica() {
		this.nombre = "Tellez";
		this.numeroSaludosRecibidos = new AtomicInteger(0);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AtomicInteger getNumeroSaludosRecibidos() {
		return numeroSaludosRecibidos;
	}

	public void setNumeroSaludosRecibidos(AtomicInteger numeroSaludosRecibidos) {
		this.numeroSaludosRecibidos = numeroSaludosRecibidos;
	}
	
}
