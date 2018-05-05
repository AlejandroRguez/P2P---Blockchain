package com.tfg.bussiness.block;

import com.tfg.entities.Block;
import com.tfg.entities.Blockchain;
import com.tfg.services.Services;

public class FindByIndex {
	
	Blockchain b = Services.getBlockchainService().getInstance();

	/**
	 * @param index Posición del bloque a buscar
	 * @return El bloque en esa posición de la cadena
	 */
	public Block get(int index) {
		Block block = b.getCurrent();
		int i = b.getCurrent().getIndex();
		while(i >= 0) {
			if (block.getIndex() == index) {return block;}
			
			else {
				block = block.getPointer();
				i --;
			}
		}
		return null;
	}

}
