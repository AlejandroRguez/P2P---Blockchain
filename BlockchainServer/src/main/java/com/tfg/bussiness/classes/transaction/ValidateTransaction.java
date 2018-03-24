package com.tfg.bussiness.classes.transaction;

import java.util.logging.Level;

import com.tfg.entities.Transaction;
import com.tfg.utils.LogManager;

public class ValidateTransaction {

	public boolean isValid(Transaction transaction) {
		
		if (transaction.getSender() == null) {
			LogManager.write(Level.SEVERE, "Error during create transaction: Invalid sender");
			return false;
		}
		if (transaction.getReceiver() == null) {
			LogManager.write(Level.SEVERE, "Error during create transaction: Invalid receiver");
			return false;
		}
		if (transaction.getSender().getAmount() < (transaction.getAmount() + transaction.getFee()) 
				|| transaction.getAmount() <= 0 || transaction.getFee() <= 0) {
			LogManager.write(Level.SEVERE, "Error during create transaction: Invalid amount");
			return false;
		}
		if(transaction.getSender().equals(transaction.getReceiver())) {
			LogManager.write(Level.WARNING, "You are trying send tokens to the same wallet");
		}
		return true;
	}

}
