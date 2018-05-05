package com.tfg.services;

import java.util.List;

import com.tfg.bussiness.block.FindByIndex;
import com.tfg.bussiness.block.FindByMiner;
import com.tfg.bussiness.block.NewBlock;
import com.tfg.entities.Block;

/**
 * Clase intermedia entre la logica de negocio 
 * y el modelo de dominio
 *
 */
public class BlockService {
	
	
	/**
	 * Llamada al método que crea un nuevo bloque
	 */
	public void newBlock() {
		new NewBlock().create();
	}

	
	/**
	 * @param miner - Usuario del que se quieren obtener sus bloques minados
	 * @return Lista de bloques minados por un usuario dado
	 */
	public List<Block> getByMiner(String miner) {
		return new FindByMiner().get(miner);
	}
	
	/**
	 * @param index Indice del bloque
	 * @return El bloque en una posición dada
	 */
	public Block getByIndex(int index) {
		return new FindByIndex().get(index);
	}
	
	

}
