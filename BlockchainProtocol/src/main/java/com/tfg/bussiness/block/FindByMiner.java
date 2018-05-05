package com.tfg.bussiness.block;

import java.util.ArrayList;
import java.util.List;

import com.tfg.entities.Block;
import com.tfg.entities.Blockchain;
import com.tfg.services.Services;

public class FindByMiner {
	
	Blockchain b = Services.getBlockchainService().getInstance();

	/**
	 * @param miner Nik de un minero
	 * @return La lista de bloques minados por un minero
	 */
	public List<Block> get(String miner) {
		int i = b.getCurrent().getIndex();
		List<Block> blocks = new ArrayList<Block>();
		Block block = b.getCurrent();
		while(i >= 0) {
			if (block.getMiner().getNickname().toLowerCase().equals(miner.toLowerCase())) {blocks.add(block);}
			block = block.getPointer();
			i --;
		}
		return blocks;
	}

}
