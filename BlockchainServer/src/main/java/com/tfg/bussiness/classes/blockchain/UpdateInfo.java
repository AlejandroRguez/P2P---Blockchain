package com.tfg.bussiness.classes.blockchain;

import java.util.Random;

import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;

public class UpdateInfo {
	
	BlockchainService bk = Services.getBlockchainService();

	public void update() {
		if(bk.getInstance().getNumberOfBlocks() % 10 == 0) {
			bk.getInstance().setProofOfWork("000");
			bk.getInstance().setTokens();
			bk.getInstance().setCurrentPrice(generatePrice());
		}
		else if(bk.getInstance().getNumberOfBlocks() % 5 == 0) {
			bk.getInstance().setProofOfWork("09");
		}
		
	}
	
	private double generatePrice() {
		return new Random().nextDouble()*(1.5-0.5) + 0.5;
	}

}
