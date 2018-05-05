package com.tfg.bussiness.transaction;

import com.tfg.entities.Account;
import com.tfg.entities.Blockchain;
import com.tfg.entities.Transaction;
import com.tfg.services.Services;
import com.tfg.utils.IOTools;

public class ExecuteTransaction {

	/**
	 * Ejecuta la transacción y guarda una copia de la cadena
	 * @param transaction
	 */
	public void execute(Transaction transaction) {
		
		Blockchain blockchain = Services.getBlockchainService().getInstance();
		
		Account receiver = null , sender = null;
		
		if(transaction.getReceiver().equals("Owner")) {
			receiver = blockchain.getOwner();
		}
		
		if(transaction.getSender().equals("Owner")) {
			sender = blockchain.getOwner();
		}
		
		if(receiver == null) {
			receiver = blockchain.getCurrentUsers().stream().parallel()
				.filter(u -> u.getNickname().equals(transaction.getReceiver())).findFirst().orElse(null);
		}
		
		if(sender == null) {
			sender = blockchain.getCurrentUsers().stream().parallel()
				.filter(u -> u.getNickname().equals(transaction.getSender())).findFirst().orElse(null);		
		}
		
		receiver.setAmount(receiver.getAmount() 
				+ transaction.getAmount());
		
		sender.setAmount(sender.getAmount() 
				- transaction.getAmount() - transaction.getFee());
		
		blockchain.getCurrentTransactions().remove
		(blockchain.getCurrentTransactions().stream().filter(t -> t.equals(transaction)).findFirst().get());		
		
		IOTools.secureSerialize(blockchain);
		
	}

}
