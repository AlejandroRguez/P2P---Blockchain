package com.tfg.entities;

import java.util.List;

public class Block {

	Long creationDate;
	String previousBlockHash;
	List<Transaction> transactions;
	String hash;
	int index;
	Block pointer;
	Account miner;
	
	public Block(Long creationDate, String previousBlockHash, List<Transaction> transactions, String hash, int index,
			Block pointer, Account miner) {
		this.creationDate = creationDate;
		this.previousBlockHash = previousBlockHash;
		this.transactions = transactions;
		this.hash = hash;
		this.index = index;
		this.pointer = pointer;
		this.miner = miner;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public String getPreviousBlockHash() {
		return previousBlockHash;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public String getHash() {
		return hash;
	}

	public int getIndex() {
		return index;
	}

	public Block getPointer() {
		return pointer;
	}

	public Account getMiner() {
		return miner;
	}
	
	
	
	
}
