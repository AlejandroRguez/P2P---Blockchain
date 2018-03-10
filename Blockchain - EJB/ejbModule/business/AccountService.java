package business;

import model.Account;
import model.Blockchain;
import model.User;

public interface AccountService {
	
	void createAccount (double amount, String nickname);
	void createBuyAccount(String seller, double amount, String nickname);
	User getByNickname(String nickname);
	Account findByAddress(String address);
	void sendTransaction(String receiver, double amount, double fee);
	void mineBlock();
	void updateInfo(User user , Blockchain blockchain);

}
