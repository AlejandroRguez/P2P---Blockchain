package model;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private static final double COINBASE_AMOUNT = 10.0;
	private static final int TRANSACTIONS_PER_BLOCK = 4;
	
	private static Blockchain instance;
	private static Owner owner;
	
	private Block current;
	private List<User> users = new ArrayList<User>();
	private List<Transaction> currentTransactions = new ArrayList<Transaction>();
	private double tokens = 1000;
	private double currentPrice = 30;
	private int numberOfBlocks;
	private int numberOfTransactions;
	private int numberOfTokens;
	private String proofOfWork = "09";
	
	private Blockchain () {	
		Blockchain.owner = Owner.getInstance();
		this.current = new Block(System.currentTimeMillis(), "", new ArrayList<Transaction>(), "Genesis Block", 0, null, owner);
	}
	
	public static Blockchain getInstance() {
		if (instance == null) {instance = new Blockchain(); }
		return instance;
	}
	
	public List<User> getCurrentUsers(){
		return users;
	}
	
	public Block getCurrent() {
		return current;
	}
	
	public void setCurrent(Block current) {
		this.current = current;
	}

	public static Owner getOwner() {
		return owner;
	}


	public double getTokens() {
		return tokens;
	}
	
	public void setTokens() {
		this.tokens = getTokens() + 1000;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public int getNumberOfTokens() {
		return numberOfTokens;
	}

	public void setNumberOfTokens(int numberOfTokens) {
		this.numberOfTokens = numberOfTokens;
	}
	
	public double getCoinbaseAmount() {
		return COINBASE_AMOUNT;
	}

	public int getTransactionsPerBlock() {
		return TRANSACTIONS_PER_BLOCK;
	}
	
	public List<Transaction> getCurrentTransactions(){
		return currentTransactions;
	}
	
	public String getProofOfWork() {
		return proofOfWork;
	}
	
	public void setProofOfWork(String proofOfWork) {
		this.proofOfWork = proofOfWork;
	}
	


}
