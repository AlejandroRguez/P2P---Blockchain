package com.tfg.bussiness.blockchain;

import java.util.Random;
import java.util.stream.Collectors;

import com.tfg.entities.Blockchain;
import com.tfg.entities.User;
import com.tfg.services.Services;

public class SelectMiner {
	
	Blockchain bk = Services.getBlockchainService().getInstance();

	/**
	 * @return El minero del próximo bloque
	 */
	public User select() {
		return bk.getCurrentUsers().get(generateMinerNumber());
	}

	/**
	 * @return Número aleatorio que designará al minero del bloque
	 */
	private int generateMinerNumber() {
		return new Random().nextInt((bk.getCurrentUsers().size()));
	}
	
	/**
	 * Implementa el algoritmo de prueba de participación
	 * Actualmente no se usa
	 * @return El usuario con más tokens
	 */
	@SuppressWarnings("unused")
	private User proofOfStake() {
		return bk.getCurrentUsers()
		.stream().parallel().sorted(User.usersComparator).collect(Collectors.toList()).get(0);
	}
}
