package com.tfg.bussiness;

import java.util.List;

import com.tfg.bussiness.classes.block.FindByMiner;
import com.tfg.bussiness.classes.block.NewBlock;
import com.tfg.entities.Block;

public class BlockService {
	
	
	public void newBlock() {
		new NewBlock().create();
		
	}

	
	public List<Block> getByMiner(String miner) {
		return new FindByMiner().get(miner);
	}
	


}
