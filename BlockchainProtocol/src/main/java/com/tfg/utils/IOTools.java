package com.tfg.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import com.tfg.entities.Blockchain;

/**
 * Clase auxiliar encargada de la gestión de la lectura y escritura del sistema en disco
 *
 */
public class IOTools {
	
	/**
	 * Guarda el objeto blockchain en disco
	 * @param blockchain El objeto a serializar
	 * @throws IOException
	 */
	public static void serialize(Blockchain blockchain) 
			throws IOException {
	    ObjectOutputStream oos = null;
	    try {
	        oos = new ObjectOutputStream(new FileOutputStream("./instance/blockchain.dat"));
	        oos.writeObject(blockchain);
	    } finally {
	        if (oos != null) oos.close();
	    }
	}
	
	/**
	 * @return El objeto blockchain existente en el disco
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Blockchain deserialize() 
			throws IOException, ClassNotFoundException {
	    ObjectInputStream oos = null;
	    try {
	        oos = new ObjectInputStream(new FileInputStream("./instance/blockchain.dat"));
	        Object o = oos.readObject();
	        return (Blockchain) o;
	    } finally {
	        if (oos != null) oos.close();
	    }
	}
	
	/**
	 * Llamada al método deserialize.
	 * Metodo auxiliar para escribir los mensajes de excepción en el log de la aplicación
	 * @return El objeto blockchain existente en el disco
	 */
	public static Blockchain secureDeserialize() {
		try {
			return deserialize();
		}catch (Exception e) {
			LogManager.write(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * Llamada al método serialize.
	 * Metodo auxiliar para escribir los mensajes de excepción en el log de la aplicación
	 * @param blockchain El objeto a serializar
	 */
	public static void secureSerialize(Blockchain blockchain) {
		try {
			serialize(blockchain);
		}catch (Exception e) {
			LogManager.write(Level.SEVERE, e.getMessage());
		}
	}

}
