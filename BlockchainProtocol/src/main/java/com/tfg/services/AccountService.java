package com.tfg.services;

import com.tfg.bussiness.account.CreateBuyAccount;
import com.tfg.bussiness.account.FindByNickname;
import com.tfg.bussiness.account.NewAccount;
import com.tfg.entities.Account;


/**
 * Clase intermedia entre la logica de negocio 
 * y el modelo de dominio
 *
 */
public class AccountService {
	
	
	/**
	 * Llamada al metodo que crea una nueva cuenta
	 * @param nickname El nombre de usuario
	 * @param amount El importe solicitado
	 */
	public void newAccount(String nickname, double amount) {
		new NewAccount().create(nickname, amount);
		
	}

	
	/**
	 * Llamada al metodo que crea una cuenta a partir 
	 * de tokens de otro usuario
	 * @param seller El nik del vendedor
	 * @param amount El importe requerido
	 * @param nickname El nik del comprador
	 */
	public void createBuyAccount(String seller, double amount, String nickname) {
		new CreateBuyAccount().create(seller, amount, nickname);
		
	}

	
	/**
	 * Llamada al método que devuelve una cuenta a partir de su nik
	 * @param nickname Nik del usuario
	 * @return Cuenta cuyo nik se recibe como parámetro
	 */
	public Account getByNickname(String nickname) {
		return new FindByNickname().get(nickname);
	}

}
