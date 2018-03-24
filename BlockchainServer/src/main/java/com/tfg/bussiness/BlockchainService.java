package com.tfg.bussiness;

import com.tfg.bussiness.classes.blockchain.GetBlockchain;
import com.tfg.bussiness.classes.blockchain.SelectMiner;
import com.tfg.bussiness.classes.blockchain.UpdateInfo;
import com.tfg.entities.Blockchain;
import com.tfg.entities.User;

public class BlockchainService {
	
	
	public void updateInfo() {
		new UpdateInfo().update();
		
	}

	
	public User selectMiner() {
		return new SelectMiner().select();
	}
	
	
	public Blockchain getInstance() {
		return new GetBlockchain().get();
	}


}
