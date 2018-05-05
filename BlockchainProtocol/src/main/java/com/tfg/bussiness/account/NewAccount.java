package com.tfg.bussiness.account;

import java.util.logging.Level;

import com.tfg.entities.Blockchain;
import com.tfg.entities.User;
import com.tfg.services.AccountService;
import com.tfg.services.Services;
import com.tfg.services.TransactionService;
import com.tfg.utils.IOTools;
import com.tfg.utils.LogManager;

public class NewAccount {

	AccountService a = Services.getAccountService();
	TransactionService t = Services.getTransactionService();
	Blockchain b = Services.getBlockchainService().getInstance();
	
	/**
	 * Crea una cuenta a partir de tokens autogenerados en la cadena
	 * @param nickname - Nombre del propietario de la cuenta
	 * @param amount - Saldo requerido
	 */
	public void create(String nickname, double amount) {
		if (checkRequirements(amount, nickname)) {
			User user = new User(0.0, nickname);
			b.getCurrentUsers().add(user);
			LogManager.write(Level.INFO, "Created account for " + nickname + " with address -> " + user.getAddress()
			+ " and amount -> " + amount);
			IOTools.secureSerialize(b);
			t.newTransaction(b.getOwner().getAddress(), user.getNickname(), amount - 1.0, 1.0);
		}
	}

	
	/**
	 * Comprueba si los datos para crear una cuenta son válidos
	 * @param amount
	 * @param nickname
	 * @return true si los datos son válidos, falso si no
	 */
	private boolean checkRequirements(double amount, String nickname) {
		if (amount < 1) {
			LogManager.write(Level.SEVERE, "Error during creating account: The amount can not be negative"
					+ "(The fee for this operation are 1 tokens)");
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
