package com.tfg.bussiness.transaction;

import java.util.logging.Level;

import com.tfg.entities.Account;
import com.tfg.entities.Transaction;
import com.tfg.services.Services;
import com.tfg.utils.LogManager;

public class ValidateTransaction {

	/**
	 * Comprueba si los datos de la transacción son válidos
	 * @param transaction
	 * @return true si son válidos, false si no
	 */
	public boolean isValid(Transaction transaction) {
		
		Account sender = Services.getAccountService().getByNickname(transaction.getSender());
		Account receiver = Services.getAccountService().getByNickname(transaction.getReceiver());
		
		if (sender == null) {
			LogManager.write(Level.SEVERE, "Error during create transaction: Invalid sender");
			return false;
		}
		if (receiver == null) {
			LogManager.write(Level.SEVERE, "Error during create transaction: Invalid receiver");
			return false;
		}
		if (sender.getAmount() < (transaction.getAmount() + transaction.getFee()) 
				|| transaction.getAmount() <= 0 || transaction.getFee() <= 0) {
			LogManager.write(Level.SEVERE, "Error during create transaction: Invalid amount");
			return false;
		}
		if(sender.equals(receiver)) {
			LogManager.write(Level.WARNING, "You are trying send tokens to the same wallet");
		}
		return true;
	}

}
