package com.tfg.bussiness.classes.block;

import java.util.ArrayList;
import java.util.List;

import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.entities.Block;

public class FindByMiner {
	
	BlockchainService b = Services.getBlockchainService();

	public List<Block> get(String miner) {
		int i = b.getInstance().getCurrent().getIndex();
		List<Block> blocks = new ArrayList<Block>();
		Block block = b.getInstance().getCurrent();
		while(i >= 0) {
			if (block.getMiner().getNickname().equals(miner)) {blocks.add(block);}
			block = block.getPointer();
			i --;
		}
		return blocks;
	}

}
