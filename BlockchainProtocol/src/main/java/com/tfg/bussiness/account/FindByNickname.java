package com.tfg.bussiness.account;

import com.tfg.entities.Account;
import com.tfg.services.Services;

public class FindByNickname {

	/**
	 * @param nickname - Nombre del propietario de la cuenta
	 * @return Devuelve los datos de la cuenta 
	 */
	public Account get(String nickname) {
		
		if(nickname.equals("Owner")){
			return Services.getBlockchainService().getInstance().getOwner();
		}
		else {
			return Services.getBlockchainService().getInstance().getCurrentUsers().stream().parallel()
					.filter(u -> u.getNickname().toLowerCase().equals(nickname.toLowerCase())).findFirst().orElse(null);

		}
				
	}

}
