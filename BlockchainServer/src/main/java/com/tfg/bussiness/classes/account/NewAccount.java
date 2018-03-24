package com.tfg.bussiness.classes.account;

import java.util.logging.Level;

import com.tfg.bussiness.AccountService;
import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.bussiness.TransactionService;
import com.tfg.entities.Blockchain;
import com.tfg.entities.User;
import com.tfg.utils.LogManager;

public class NewAccount {

	AccountService a = Services.getAccountService();
	TransactionService t = Services.getTransactionService();
	BlockchainService b = Services.getBlockchainService();
	
	public Blockchain create(User account) {
		if (!checkRequirements(account.getAmount(), account.getNickname())) {return b.getInstance();}
		User user = new User(0.0, account.getNickname());
		b.getInstance().getCurrentUsers().add(user);
		return t.newTransaction(b.getInstance().getOwner().getAddress(), user.getNickname(), account.getAmount()-1.0, 1.0);
		
	}

	
	private boolean checkRequirements(double amount, String nickname) {
		if (amount < 0) {
			LogManager.write(Level.SEVERE, "Error during creating account: The amount can not be negative");
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
		return true;
	}
}
