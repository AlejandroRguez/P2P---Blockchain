package com.tfg.entities;


import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private static final double COINBASE_AMOUNT = 10.0;
	private static final int TRANSACTIONS_PER_BLOCK = 4;
	private static final int LIMIT_OF_PENDING_TRANSACTIONS = 6;
	
	private static Blockchain instance;
	private static Owner owner;
	
	private Block current;
	private List<User> users = new ArrayList<User>();
	private List<Transaction> currentTransactions = new ArrayList<Transaction>();
	private List<Double> prices = new ArrayList<Double>();
	private double currentPrice = 30;
	private int numberOfTransactions;
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

	public Owner getOwner() {
		return owner;
	}
	
	public void setTokens() {
		getOwner().setAmount(getOwner().getAmount() + 1000);
	}
	
	public double getTokens() {
		return getOwner().getAmount();
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
		prices.add(currentPrice);
	}

	public int getNumberOfBlocks() {
		return current.getIndex()+1;
	}

	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public double getTotalTokens() {
		 return getCurrentUsers().stream().mapToDouble(a -> a.getAmount()).sum() + getTokens();
	}
	
	public double getCoinbaseAmount() {
		return COINBASE_AMOUNT;
	}

	public int getTransactionsPerBlock() {
		return TRANSACTIONS_PER_BLOCK;
	}
	
	public int getLimitOfPendingTransactions() {
		return LIMIT_OF_PENDING_TRANSACTIONS;
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
	
	public int getNumberOfAddedTransactions() {
		return current.getIndex() * TRANSACTIONS_PER_BLOCK;
	}
	
	public int getNumberOfTotalTransactions() {
		return getNumberOfAddedTransactions() + getNumberOfPendingTransactions();
	}
	
	public int getNumberOfPendingTransactions() {
		return getCurrentTransactions().size();
	}
	
	public List<Double> getPrices(){
		return prices;
	}
	
	
	public double getMarketCapitalization() {
		return getCurrentPrice() * getTotalTokens();
	}
	



}
