package business;

public interface ServicesFactory {
	
	TransactionService getTransactionService();
	BlockService getBlockService();
	BlockchainService getBlockchainService();
	AccountService getAccountService();

}
