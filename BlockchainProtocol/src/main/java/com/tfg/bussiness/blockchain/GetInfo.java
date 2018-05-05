package com.tfg.bussiness.blockchain;

import com.tfg.entities.BlockchainPresentationInfo;
import com.tfg.services.Services;

public class GetInfo {
	/**
	 * @return El objeto que sintetiza la información de 
	 * la cadena para su posterior visualización
	 */
	public BlockchainPresentationInfo get() {
		return new BlockchainPresentationInfo(Services.getBlockchainService().getInstance());
	}
}
