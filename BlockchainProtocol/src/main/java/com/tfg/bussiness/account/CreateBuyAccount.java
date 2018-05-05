package com.tfg.bussiness.account;

import java.util.logging.Level;

import com.tfg.entities.Blockchain;
import com.tfg.entities.User;
import com.tfg.services.AccountService;
import com.tfg.services.Services;
import com.tfg.services.TransactionService;
import com.tfg.utils.IOTools;
import com.tfg.utils.LogManager;

public class CreateBuyAccount {
	
	AccountService a = Services.getAccountService();
	TransactionService t = Services.getTransactionService();
	Blockchain b = Services.getBlockchainService().getInstance();

	/**
	 * Crea una cuenta a partir del saldo existente
	 * en la cuenta de un usuario ya registrado en la cadena
	 * @param seller
	 * @param amount
	 * @param nickname
	 */
	public void create(String seller, double amount, String nickname) {
		if (checkRequirements(amount, nickname, seller)) { 
			User user = new User(0, nickname);
			b.getCurrentUsers().add(user);
			LogManager.write(Level.INFO, "Created account for " + nickname + " with address -> " + user.getAddress()
			+ " and amount -> " + amount);
			IOTools.secureSerialize(b);
			t.newTransaction(seller, nickname, amount-2.0, 2.0);
		}
		
	}
	
	/**
	 * Comprueba su los datos para la creación de la nueva cuenta
	 * son correctos
	 * @param amount
	 * @param nickname
	 * @param seller
	 * @return true si los datos son válidos, false si no.
	 */
	private boolean checkRequirements(double amount, String nickname, String seller) {
		if (amount < 2) {
			LogManager.write(Level.SEVERE, "Error during creating account: The amount can not be negative "
					+ "(The fee for this operation are 2 tokens)");
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
			LogManager.write(Level.SEVERE, "Error during creating account: The seller has not amount enough");
			return false;
		}
		return true;
	}

}
