package business.impl.classes.account;

import java.util.logging.Level;

import business.AccountService;
import business.BlockchainService;
import business.TransactionService;
import infraestructure.Factories;
import model.User;
import utils.LogManager;

public class CreateBuyAccount {
	
	AccountService a = Factories.services.getAccountService();
	TransactionService t = Factories.services.getTransactionService();
	BlockchainService b = Factories.services.getBlockchainService();

	public void create(String seller, double amount, String nickname) {
		if (!checkRequirements(amount, nickname, seller)) {return ;}
		User user = new User(0, nickname);
		b.addAccount(user);
		t.createTransaction(seller, nickname, amount-2, 2);
		
	}
	
	private boolean checkRequirements(double amount, String nickname, String seller) {
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
		
		if(a.getByNickname(seller) == null) {
			LogManager.write(Level.SEVERE, "Error during creating account: The seller does not exist");
			return false;
		}
		if(a.getByNickname(seller).getAmount() < amount) {
			LogManager.write(Level.SEVERE, "Error during creating account: The seller hasn't amount enough");
			return false;
		}
		return true;
	}

}
