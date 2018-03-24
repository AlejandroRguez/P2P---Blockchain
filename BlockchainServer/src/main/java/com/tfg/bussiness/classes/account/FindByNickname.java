package com.tfg.bussiness.classes.account;

import com.tfg.bussiness.Services;
import com.tfg.entities.Account;

public class FindByNickname {

	public Account get(String nickname) {
		
		if(nickname.equals("Owner")){
			return Services.getBlockchainService().getInstance().getOwner();
		}
		else {
			return Services.getBlockchainService().getInstance().getCurrentUsers().stream()
					.filter(u -> u.getNickname().equals(nickname)).findFirst().orElse(null);

		}
				
	}

}
