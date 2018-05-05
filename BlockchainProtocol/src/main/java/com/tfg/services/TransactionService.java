package com.tfg.services;

import com.tfg.bussiness.transaction.ExecuteTransaction;
import com.tfg.bussiness.transaction.NewTransaction;
import com.tfg.bussiness.transaction.ValidateTransaction;
import com.tfg.entities.Transaction;

/**
 * Clase intermedia entre la logica de negocio 
 * y el modelo de dominio
 *
 */
public class TransactionService {
	
	
	/**
	 * Llamada al método que crea una transacción
	 * @param sender El emisor de la transacción
	 * @param receiver El receptor de la transacción
	 * @param amount El importe a transmitir
	 * @param fee La comisión para el minero
	 */
	public void newTransaction(String sender, String receiver, double amount, double fee) {
		new NewTransaction().create(sender, receiver, amount, fee);
		
	}

	
	/**
	 * @param transaction Transacción a validar
	 * @return true si la transacción es válida, false si no
	 */
	public boolean validateTransaction(Transaction transaction) {
		return new ValidateTransaction().isValid(transaction);
	}

	
	/**
	 * Llamada al método encargado de ejecutar una transacción
	 * @param transaction Transacción a ejecutar
	 */
	public void executeTransaction(Transaction transaction) {
		new ExecuteTransaction().execute(transaction);
		
	}


}
