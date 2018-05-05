package com.tfg.bussiness.transaction;

import java.util.logging.Level;

import com.tfg.entities.Blockchain;
import com.tfg.entities.Transaction;
import com.tfg.services.AccountService;
import com.tfg.services.BlockService;
import com.tfg.services.Services;
import com.tfg.services.TransactionService;
import com.tfg.utils.IOTools;
import com.tfg.utils.LogManager;

public class NewTransaction {

	AccountService a = Services.getAccountService();
	TransactionService ts = Services.getTransactionService();
	Blockchain bk = Services.getBlockchainService().getInstance();
	BlockService b = Services.getBlockService();

	/**
	 * Crea una nueva transacción y comprueba si es necesario crear un nuevo bloque
	 * tras añadirla a la cadena
	 * @param sender
	 * @param receiver
	 * @param amount
	 * @param fee
	 */
	public void create(String sender, String receiver, double amount, double fee) {
		Transaction t = new Transaction(sender, receiver, amount, fee);
		if (ts.validateTransaction(t)) {
			bk.getCurrentTransactions().add(t);
			IOTools.secureSerialize(bk);
			if (bk.getCurrentTransactions().size() >= bk.getLimitOfPendingTransactions()) {
				b.newBlock();
			}
		}
		LogManager.write(Level.INFO, Services.getBlockchainService().getInstance().toString());
	}

}
