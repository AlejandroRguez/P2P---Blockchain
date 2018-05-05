package com.tfg.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase estática encargada de la escritura del log
 *
 */
public class LogManager {
	
	//Intentar implementarlo para crear un log diario
	static Logger logger = Logger.getLogger("foodchain");
	static FileHandler fh;
	
	/**
	 * Escribe los mensajes en el log del sistema
	 * Crea un log diario
	 * @param logLevel Indica el nivel de gravedad del error
	 * @param message Mensaje asociado al error
	 */
	public static void write(Level logLevel, String message) {
		try {
			SimpleDateFormat jdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			fh = new FileHandler("./logs/blockchain" + jdf.format(d) + ".log", true);
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
			logger.log(logLevel, message);
			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
