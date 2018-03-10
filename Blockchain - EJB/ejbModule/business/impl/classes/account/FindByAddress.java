package business.impl.classes.account;

import model.Account;
import model.Blockchain;

public class FindByAddress {

	public Account find(String address) {
		if (Blockchain.getOwner().getAddress().equals(address)) {return Blockchain.getOwner();}
		else return Blockchain.getInstance().getCurrentUsers().stream().filter(u -> u.getAddress().equals(address)).findFirst().get();
	}

}
