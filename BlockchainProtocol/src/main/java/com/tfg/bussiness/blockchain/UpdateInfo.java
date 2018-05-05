package com.tfg.bussiness.blockchain;

import java.util.Random;

import com.tfg.entities.Blockchain;
import com.tfg.services.Services;
import com.tfg.utils.IOTools;

public class UpdateInfo {
	
	Blockchain bk = Services.getBlockchainService().getInstance();

	/**
	 * Genera un nuevo precio para la cadena
	 * Si el nuevo bloque es múltiplo de 10 fija una nueva prueba de trabajo y genera 1000 tokens
	 * Si es múltiplo de 5 genera una prueba de trabajo distinta
	 * Guarda la cadena
	 */
	public void update() {
		
		bk.setCurrentPrice(generatePrice()*bk.getCurrentPrice());
		
		if(bk.getNumberOfBlocks() % 10 == 0) {
			bk.setProofOfWork("99");
			bk.setTokens();
		}
		else if(bk.getNumberOfBlocks() % 5 == 0) {
			bk.setProofOfWork("09");
		}
		
		IOTools.secureSerialize(bk);
		
	}
	
	/**
	 * @return El nuevo precio del token
	 */
	private double generatePrice() {
		double aux = new Random().nextDouble()*(1.5-0.5) + 0.5;
		return (double)Math.round(aux * 100d) / 100d;
	}
	
	
}
