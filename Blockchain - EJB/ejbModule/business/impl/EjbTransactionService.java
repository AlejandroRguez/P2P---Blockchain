package business.impl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import business.impl.classes.transaction.CreateTransaction;
import business.impl.classes.transaction.ExecuteTransaction;
import business.impl.classes.transaction.ValidateTransaction;
import model.Transaction;

@Stateless
@WebService(name="TransactionService")
public class EjbTransactionService implements LocalTransactionService, RemoteTransactionService{

	@Override
	public Transaction createTransaction(String sender, String receiver, double amount, double fee) {
		return new CreateTransaction().create(sender, receiver, amount, fee);
		
	}

	@Override
	public boolean validateTransaction(Transaction transaction) {
		return new ValidateTransaction().isValid(transaction);
	}

	@Override
	public void executeTransaction(Transaction transaction) {
		new ExecuteTransaction().execute(transaction);
		
	}

}
