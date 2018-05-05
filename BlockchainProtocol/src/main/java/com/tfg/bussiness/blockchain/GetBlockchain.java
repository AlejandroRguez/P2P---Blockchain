package com.tfg.bussiness.blockchain;

import com.tfg.entities.Blockchain;
import com.tfg.utils.IOTools;

public class GetBlockchain {

	/**
	 * @return La cadena en su estado actual
	 */
	public Blockchain get() {
		return IOTools.secureDeserialize();
	}

}
