package business;

import model.Block;
import model.Transaction;
import model.User;

public interface BlockchainService {
	
	void notifyUsers();
	void addTransaction (Transaction transaction);
	void addBlock(Block block);
	void addAccount(User user);
	void updateInfo();
	User selectMiner();
//	Blockchain get();

}
