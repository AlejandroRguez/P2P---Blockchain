package business.impl.classes.account;

import model.Blockchain;
import model.User;

public class FindByNickname {

	public User get(String nickname) {
		return Blockchain.getInstance().getCurrentUsers().stream()
				.filter(u -> u.getNickName().equals(nickname)).findFirst().get();
	}

}
