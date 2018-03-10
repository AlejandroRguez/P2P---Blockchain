package business.impl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import business.impl.classes.account.CreateAccount;
import business.impl.classes.account.CreateBuyAccount;
import business.impl.classes.account.FindByAddress;
import business.impl.classes.account.FindByNickname;
import business.impl.classes.account.MineBlock;
import business.impl.classes.account.SendTransaction;
import business.impl.classes.account.UpdateInfo;
import model.Account;
import model.Blockchain;
import model.User;

@Stateless
@WebService(name="AccountService")
public class EjbAccountService implements LocalAccountService, RemoteAccountService{

	@Override
	public void createAccount(double amount, String nickname) {
		new CreateAccount().create(amount, nickname);
		
	}

	@Override
	public void createBuyAccount(String seller, double amount, String nickname) {
		new CreateBuyAccount().create(seller, amount, nickname);
		
	}

	@Override
	public User getByNickname(String nickname) {
		return new FindByNickname().get(nickname);
	}

	@Override
	public void sendTransaction(String receiver, double amount, double fee) {
		new SendTransaction().send(receiver, amount, fee);
		
	}

	@Override
	public void mineBlock() {
		new MineBlock().mine();
		
	}

	@Override
	public void updateInfo(User user, Blockchain blockchain) {
		new UpdateInfo().update(user , blockchain);
		
	}

	@Override
	public Account findByAddress(String address) {
		return new FindByAddress().find(address);
	}
	
	

}
