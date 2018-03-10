package business.impl.classes.account;

import model.Blockchain;
import model.User;

public class UpdateInfo {

	public void update(User user ,Blockchain blockchain) {
		user.updateInfo(blockchain);
		
	}

}
