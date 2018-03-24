package com.tfg.bussiness.classes.transaction;

import com.tfg.bussiness.AccountService;
import com.tfg.bussiness.BlockService;
import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.bussiness.TransactionService;
import com.tfg.entities.Account;
import com.tfg.entities.Blockchain;
import com.tfg.entities.Transaction;

public class NewTransaction {

	AccountService a = Services.getAccountService();
	TransactionService ts = Services.getTransactionService();
	BlockchainService bk = Services.getBlockchainService();
	BlockService b = Services.getBlockService();

	public Blockchain create(String sender, String receiver, double amount, double fee) {
		Account send = a.getByNickname(sender);
		Account receipt = a.getByNickname(receiver);
		Transaction t = new Transaction(send, receipt, amount, fee);
		if (ts.validateTransaction(t)) {
			bk.getInstance().getCurrentTransactions().add(t);
			if (bk.getInstance().getCurrentTransactions().size() == bk.getInstance().getLimitOfPendingTransactions()) {
				b.newBlock();
			}

		}
		
		return bk.getInstance();

	}
}
