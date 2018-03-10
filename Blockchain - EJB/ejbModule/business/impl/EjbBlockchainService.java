package business.impl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import business.impl.classes.blockchain.AddAccount;
import business.impl.classes.blockchain.AddBlock;
import business.impl.classes.blockchain.AddTransaction;
import business.impl.classes.blockchain.NotifyUsers;
import business.impl.classes.blockchain.SelectMiner;
import business.impl.classes.blockchain.UpdateInfo;
import model.Block;
import model.Transaction;
import model.User;

@Stateless
@WebService(name="BlockchainService")
public class EjbBlockchainService implements LocalBlockchainService, RemoteBlockchainService {

	@Override
	public void notifyUsers() {
		new NotifyUsers().notify();
		
	}

	@Override
	public void addTransaction(Transaction transaction) {
		new AddTransaction().add(transaction);
		
	}

	@Override
	public void addBlock(Block block) {
		new AddBlock().add(block);
		
	}

	@Override
	public void updateInfo() {
		new UpdateInfo().update();
		
	}

	@Override
	public User selectMiner() {
		return new SelectMiner().select();
	}

	@Override
	public void addAccount(User user) {
		new AddAccount().add(user);
		
	}
	
	

//	@Override
//	public Blockchain get() {
//		return new GetBlockchain().get();
//	}

}
