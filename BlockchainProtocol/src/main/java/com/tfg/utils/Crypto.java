package com.tfg.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 * Clase encarcaga de la generación de pares de claves y de hashes
 *
 */
public class Crypto {
	
	/**
	 * @return Instancia de la clase necesaria para calcular el hash de una cadena
	 */
	private static MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			LogManager.write(Level.SEVERE, "Couldn�t get MessageDigest Instance");
			return null;
		}
	}

	/**
	 * @return Tupla de claves pública y privada
	 */
	public static KeyPair getKeyPair() {
		try {
			return KeyPairGenerator.getInstance("RSA").generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			LogManager.write(Level.SEVERE, "Couldn�t get Key Pair Instance");
			return null;
		}
	}
	
	/**
	 * @param text El texto del que se debe calcular el hash
	 * @return El hash del texto en el formato previamente seleccionado
	 */
	public static String getStringHash(String text) {
		String s = "";
	    byte[] digest = getMessageDigest().digest(text.getBytes());
	    for (byte b : digest) {
	    	String a = Integer.toHexString(0xFF & b);
	        s = s.concat(a.toString());
	    }
	    return s;
	}

}
