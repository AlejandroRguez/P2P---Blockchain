package com.tfg.utils.console;

import java.io.PrintStream;

/**
 * Métodos de utilidad para escribir cosas en pantalla de forma controlada.
 * Aqu� irian todas las decoraciones pertinentes
 * 
 * @author alb
 */
public class Printer {
	private static PrintStream con = System.out;
	
	public static void printHeading(String string) {
		con.println(string);
	}

	/**
	 * Avisa de error l�gico en la ejecuci�n, muy probablemente por 
	 * equivocacion del usuario o por circunstancias que han cambiado 
	 * durante el "think time" del usuario (control optimista y eso...)
	 * 
	 * @param e
	 */
	public static void printBusinessException(Exception e) {
		//TODO: Hacer esto un poco m�s curioso seg�n lo pida la interfaz
		
		con.println("A problem has ocurred");
		con.println("\t- " + e.getLocalizedMessage());
	}

	/**
	 * Avisa de error irrecuperable en la interfaz del usuario
	 * @param string
	 * @param e
	 */
	public static void printRuntimeException(RuntimeException e) {
		con.println("Fatal error");

		e.printStackTrace();
	}

	public static void print(String msg) {
		con.println(msg);
	}

	public static void printException(String string, Exception e) {
		con.println(string);
		con.println("\t- " + e.getLocalizedMessage());
	}
}
