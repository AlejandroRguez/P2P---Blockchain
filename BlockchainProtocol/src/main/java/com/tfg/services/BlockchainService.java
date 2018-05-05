package com.tfg.services;

import com.tfg.bussiness.blockchain.GetBlockchain;
import com.tfg.bussiness.blockchain.GetInfo;
import com.tfg.bussiness.blockchain.SelectMiner;
import com.tfg.bussiness.blockchain.UpdateInfo;
import com.tfg.entities.Blockchain;
import com.tfg.entities.BlockchainPresentationInfo;
import com.tfg.entities.User;

/**
 * Clase intermedia entre la logica de negocio 
 * y el modelo de dominio
 *
 */
public class BlockchainService {
	
	
	/**
	 * Metodo que actualiza los datos internos de la cadena
	 * Prueba de trabajo, tokens y precio por token
	 */
	public void updateInfo() {
		new UpdateInfo().update();
		
	}

	
	/**
	 * Selecciona aleatoriamente el minero del siguiente bloque
	 * @return El minero seleccionado
	 */
	public User selectMiner() {
		return new SelectMiner().select();
	}
	
	
	/**
	 * @return La cadena en su estado actual
	 */
	public Blockchain getInstance() {
		return new GetBlockchain().get();
	}
	
	/**
	 * @return Objeto blockchain simplificado para facilitar su tratamiento en las vistas 
	 */
	public BlockchainPresentationInfo getInfo() {
		return new GetInfo().get();
	}


}
