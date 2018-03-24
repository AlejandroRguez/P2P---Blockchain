package com.tfg.bussiness.classes.blockchain;

import java.util.Random;
import java.util.stream.Collectors;

import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.entities.User;

public class SelectMiner {
	
	BlockchainService bk = Services.getBlockchainService();

	public User select() {
		return bk.getInstance().getCurrentUsers().get(generateMinerNumber());
	}

	private int generateMinerNumber() {
		return new Random().nextInt((bk.getInstance().getCurrentUsers().size()));
	}
	
	@SuppressWarnings("unused")
	private User proofOfStake() {
		return bk.getInstance().getCurrentUsers()
		.stream().sorted(User.usersComparator).collect(Collectors.toList()).get(0);
	}
}
