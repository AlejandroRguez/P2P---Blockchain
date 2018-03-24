package com.tfg.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class Crypto {
	
	public static MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			LogManager.write(Level.SEVERE, "Couldn�t get MessageDigest Instance");
			return null;
		}
	}

	public static KeyPair getKeyPair() {
		try {
			return KeyPairGenerator.getInstance("RSA").generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			LogManager.write(Level.SEVERE, "Couldn�t get Key Pair Instance");
			return null;
		}
	}

}
