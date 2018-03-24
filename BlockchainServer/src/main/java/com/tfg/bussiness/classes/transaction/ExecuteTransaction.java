package com.tfg.bussiness.classes.transaction;

import com.tfg.entities.Transaction;

public class ExecuteTransaction {

	public void execute(Transaction transaction) {
		transaction.getReceiver().setAmount(transaction.getReceiver().getAmount() 
				+ transaction.getAmount());
		
		transaction.getSender().setAmount(transaction.getSender().getAmount() 
				- transaction.getAmount() - transaction.getFee());
		
	}

}
