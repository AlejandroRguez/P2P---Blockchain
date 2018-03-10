package business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import business.AccountService;
import business.BlockService;
import business.BlockchainService;
import business.ServicesFactory;
import business.TransactionService;

public class LocalEjbServiceLocator implements ServicesFactory{
	
	private static final String TRANSACTION_SERVICE_JNDI_KEY = "Blockchain/Blockchain - EJB/EjbTransactionService!business.impl.LocalTransactionService";
	private static final String BLOCK_SERVICE_JNDI_KEY = "Blockchain/Blockchain - EJB/EjbBlockService!business.impl.LocalBlockService";
	private static final String BLOCKCHAIN_SERVICE_JNDI_KEY = "Blockchain/Blockchain - EJB/EjbBlockchainService!business.impl.LocalBlockchainService";
	private static final String ACCOUNT_SERVICE_JNDI_KEY = "Blockchain/Blockchain - EJB/EjbAccountService!business.impl.LocalAccountService";

	@Override
	public TransactionService getTransactionService() {
		try {
			Context ctx = new InitialContext();
			return (TransactionService) ctx.lookup(TRANSACTION_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public BlockService getBlockService() {
		try {
			Context ctx = new InitialContext();
			return (BlockService) ctx.lookup(BLOCK_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public BlockchainService getBlockchainService() {
		try {
			Context ctx = new InitialContext();
			return (BlockchainService) ctx.lookup(BLOCKCHAIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public AccountService getAccountService() {
		try {
			Context ctx = new InitialContext();
			return (AccountService) ctx.lookup(ACCOUNT_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
