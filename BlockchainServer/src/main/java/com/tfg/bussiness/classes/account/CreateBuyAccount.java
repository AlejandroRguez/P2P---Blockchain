package com.tfg.bussiness.classes.account;

import java.util.logging.Level;

import com.tfg.bussiness.AccountService;
import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.bussiness.TransactionService;
import com.tfg.entities.Blockchain;
import com.tfg.entities.User;
import com.tfg.utils.LogManager;

public class CreateBuyAccount {
	
	AccountService a = Services.getAccountService();
	TransactionService t = Services.getTransactionService();
	BlockchainService b = Services.getBlockchainService();

	public Blockchain create(String seller, double amount, String nickname) {
		if (!checkRequirements(amount, nickname, seller)) return b.getInstance();
		
		User user = new User(0, nickname);
		b.getInstance().getCurrentUsers().add(user);
		return t.newTransaction(seller, nickname, amount-2.0, 2.0);
		
	}
	
	private boolean checkRequirements(double amount, String nickname, String seller) {
		if (amount < 0) {
			LogManager.write(Level.SEVERE, "Error during creating account: The amount can�t be negative");
			return false;
		}
		if (nickname.isEmpty()) {
			LogManager.write(Level.SEVERE, "Error during creating account: The nickname is required");
			return false;
		}
		
		if(a.getByNickname(nickname) != null) {
			LogManager.write(Level.SEVERE, "Error during creating account: The nickname is already used");
			return false;
		}
		
		if(a.getByNickname(seller) == null) {
			LogManager.write(Level.SEVERE, "Error during creating account: The seller does not exist");
			return false;
		}
		if(a.getByNickname(seller).getAmount() < amount) {
			LogManager.write(Level.SEVERE, "Error during creating account: The seller hasn't amount enough");
			return false;
		}
		return true;
	}

}
