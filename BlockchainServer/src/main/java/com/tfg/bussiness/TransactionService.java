package com.tfg.bussiness;

import com.tfg.bussiness.classes.transaction.ExecuteTransaction;
import com.tfg.bussiness.classes.transaction.NewTransaction;
import com.tfg.bussiness.classes.transaction.ValidateTransaction;
import com.tfg.entities.Blockchain;
import com.tfg.entities.Transaction;

public class TransactionService {
	
	
	public Blockchain newTransaction(String sender, String receiver, double amount, double fee) {
		return new NewTransaction().create(sender, receiver, amount, fee);
		
	}

	
	public boolean validateTransaction(Transaction transaction) {
		return new ValidateTransaction().isValid(transaction);
	}

	
	public void executeTransaction(Transaction transaction) {
		new ExecuteTransaction().execute(transaction);
		
	}


}
