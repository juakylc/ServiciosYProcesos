package serviciosyprocesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiciosYProcesos {
	
	public static Scanner sc = new Scanner(System.in);
	public static String[] personas = {"Tellez", "Joaquin", "Arboledas"};
	public static Persona tellez = new Persona();
	public static PersonaAtomica tellezAtomico = new PersonaAtomica();
	public static Object sem;

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("******************************************");
		System.out.println("*          SERVICIOS Y PROCESOS          *");	
		System.out.println("******************************************");	
		
		int n = 0;
		while (n!=8) {
			System.out.println("\nSelecciona una opci�n:");
			System.out.println("\t1 - Ejecutar comando dir.");
			System.out.println("\t2 - Abrir Chrome.");
			System.out.println("\t3 - Ejecutar clase java.");
			System.out.println("\t4 - TIC TAC.");
			System.out.println("\t5 - Hilos con semaforo.");
			System.out.println("\t6 - Hilos con objetos atomicos.");
			System.out.println("\t7 - Hilos con bloques sincronizados.");
			System.out.println("\t8 - Salir.");
			n = sc.nextInt();
			switch (n) {
				case 1: launchCommand("dir /A");
						break;
				case 2: launchApp("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
						break;
				case 3: launchJavaClass(personas);
						break;
				case 4: //Recuerda buscar en google: How does a thread support its own interruption
						sem = new Object();
						TIC tic = new TIC("TIC", sem);
	            		TAC tac = new TAC("TAC", sem);
	            		tic.start();
	            		Thread.sleep(1000);
	            		tac.start();
	            		Thread.sleep(60000);
	            		tic.interrupt();
	            		tac.interrupt();
	            		tic.join();
	            		tac.join();
						break;
				case 5: sem = new Object();
						tellez.setNumeroSaludosRecibidos(0);
						Thread hilo1 = new Thread(new HiloSemaforo(tellez, 1, sem));
						Thread hilo2 = new Thread(new HiloSemaforo(tellez, 2, sem));
						hilo1.start();
						hilo2.start();
						hilo1.join();
						hilo2.join();
						System.out.println(tellez.numeroSaludosRecibidos);
						break;
				case 6: tellezAtomico.setNumeroSaludosRecibidos(new AtomicInteger(0));
						Thread hilo3 = new Thread(new HiloAtomico(tellezAtomico, 1));
						Thread hilo4 = new Thread(new HiloAtomico(tellezAtomico, 2));
						hilo3.start();
						hilo4.start();
						hilo3.join();
						hilo4.join();
						System.out.println(tellezAtomico.numeroSaludosRecibidos);
						break;
				case 7: tellez.setNumeroSaludosRecibidos(0);
						Thread hilo5 = new Thread(new HiloBienHecho(tellez, 1));
						Thread hilo6 = new Thread(new HiloBienHecho(tellez, 2));
						hilo5.start();
						hilo6.start();
						hilo5.join();
						hilo6.join();
						System.out.println(tellez.numeroSaludosRecibidos);
						break;
				case 8: System.out.println("Gracias por usar este programa"); 
						break;
				default: System.out.println("\nError. Introduzca una opci�n v�lida"); break;
				
			}
		}
		
	}
	
    public static void launchCommand(String command) throws IOException, InterruptedException {
    	
        ProcessBuilder ps = new ProcessBuilder("cmd","/C", command);
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        
        pr.waitFor();
        
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }
	
	public static void launchApp(String ruta) throws IOException, InterruptedException {
        System.out.println("Abriendo programa...\n");
        
        //Instanciamos el comando que ejecutar� el programa
        ProcessBuilder ps = new ProcessBuilder(ruta);
        
        //Si diese error, lo redirigimos 
        ps.redirectErrorStream(true);
        
        //Ejecutamos el proceso
        Process pr = ps.start();
        
        //Instanciamos el BufferedReader para imprimir por pantalla la informaci�n que el proceso nos envie
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        
        //Esperamos a que el proceso acabe para seguir ejecutando el programa 
        pr.waitFor();
        
        //Imprimimos por pantalla la informacion
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line); 
        }
        
        //Finalizamos el BufferedReader
        in.close();
        
        System.out.println("\nPrograma ejecutado con exito!");
    }

	public static void launchJavaClass(String[] argumentos) throws IOException, InterruptedException {
        System.out.println("Ejecutando clase Java...\n");
        
        //Ruta de Hola Gente
        String dir = ".\\HolaGente\\bin";
        //Nombre del .class
        String className = "holagente.HolaGente";
        
        //Instanciamos el comando que ejecutar� el programa
        ProcessBuilder ps = new ProcessBuilder("java.exe","-cp",dir,className, argumentos[0], argumentos[1], argumentos[2]);
        
        //Si diese error, lo redirigimos 
        ps.redirectErrorStream(true);
        
        //Ejecutamos el proceso
        Process pr = ps.start();
        
        //Instanciamos el BufferedReader para imprimir por pantalla la informaci�n que el proceso nos envie
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        
        //Esperamos a que el proceso acabe para seguir ejecutando el programa 
        pr.waitFor();
        
        //Imprimimos por pantalla la informacion
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line); 
        }
        
        //Finalizamos el BufferedReader
        in.close();
        
        System.out.println("\nClase ejecutada con exito!");
    }

}
