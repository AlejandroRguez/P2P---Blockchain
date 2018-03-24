package com.tfg.bussiness;

public class Services {
	
	public static BlockService getBlockService() {
		return new BlockService();
	}
	
	public static AccountService getAccountService() {
		return new AccountService();
	}
	
	public static BlockchainService getBlockchainService() {
		return new BlockchainService();
	}
	
	public static TransactionService getTransactionService() {
		return new TransactionService();
	}

}
