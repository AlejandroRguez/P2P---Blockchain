package business;

import model.Transaction;

public interface TransactionService {
	
	Transaction createTransaction(String sender, String receiver, double amount, double fee);
	boolean validateTransaction(Transaction transaction);
	void executeTransaction(Transaction transaction);

}
