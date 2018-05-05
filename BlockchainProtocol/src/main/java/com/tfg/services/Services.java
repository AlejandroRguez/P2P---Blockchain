package com.tfg.services;

/**
 * Clase contenedora. Encapsula las llamadas a todos los servicios
 *
 */
public class Services {
	
	/**
	 * @return Instancia de la clase BlockService
	 */
	public static BlockService getBlockService() {
		return new BlockService();
	}
	
	/**
	 * @return Instancia de la clase AccountService
	 */
	public static AccountService getAccountService() {
		return new AccountService();
	}
	
	/**
	 * @return Instancia de la clase BlockchainService
	 */
	public static BlockchainService getBlockchainService() {
		return new BlockchainService();
	}
	
	/**
	 * @return Instancia de la clase TransactionService
	 */
	public static TransactionService getTransactionService() {
		return new TransactionService();
	}

}
