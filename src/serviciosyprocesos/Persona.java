package serviciosyprocesos;

public class Persona {
	String nombre;
	int numeroSaludosRecibidos;
	
	public Persona() {
		this.nombre = "Tellez";
		this.numeroSaludosRecibidos = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroSaludosRecibidos() {
		return numeroSaludosRecibidos;
	}

	public void setNumeroSaludosRecibidos(int numeroSaludosRecibidos) {
		this.numeroSaludosRecibidos = numeroSaludosRecibidos;
	}
	
	
}
