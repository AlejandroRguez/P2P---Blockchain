package business.impl.classes.account;

import java.util.logging.Level;

import business.AccountService;
import business.BlockchainService;
import business.TransactionService;
import infraestructure.Factories;
import model.Blockchain;
import model.User;
import utils.LogManager;

public class CreateAccount {

	AccountService a = Factories.services.getAccountService();
	TransactionService t = Factories.services.getTransactionService();
	BlockchainService b = Factories.services.getBlockchainService();
	
	public void create(double amount, String nickname) {
		if (!checkRequirements(amount, nickname)) {return ;}
		User user = new User(0, nickname);
		b.addAccount(user);
		t.createTransaction(Blockchain.getOwner().getAddress(), nickname, amount-1, 1);
	}

	
	private boolean checkRequirements(double amount, String nickname) {
		if (amount < 0) {
			LogManager.write(Level.SEVERE, "Error during creating account: The amount can´t be negative");
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
