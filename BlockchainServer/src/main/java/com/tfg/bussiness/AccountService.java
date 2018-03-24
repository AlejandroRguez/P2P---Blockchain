package com.tfg.bussiness;

import com.tfg.bussiness.classes.account.CreateBuyAccount;
import com.tfg.bussiness.classes.account.FindByNickname;
import com.tfg.bussiness.classes.account.NewAccount;
import com.tfg.entities.Account;
import com.tfg.entities.Blockchain;
import com.tfg.entities.User;

public class AccountService {
	
	
	public Blockchain newAccount(User account) {
		return new NewAccount().create(account);
		
	}

	
	public Blockchain createBuyAccount(String seller, double amount, String nickname) {
		return new CreateBuyAccount().create(seller, amount, nickname);
		
	}

	
	public Account getByNickname(String nickname) {
		return new FindByNickname().get(nickname);
	}

}
