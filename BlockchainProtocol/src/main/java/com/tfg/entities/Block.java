package com.tfg.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Block implements Serializable {
	

	private static final long serialVersionUID = 1L;

	Long creationDate;
	public String previousBlockHash;
	public List<Transaction> transactions;
	public String hash;
	public int index;
	Block pointer;
	public Account miner;
	public Date creation;
	public int numberOfTransactions;
	public double totalAmount;
	public double totalFee;
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	/**
	 * Constructor
	 * @param creationDate
	 * @param previousBlockHash
	 * @param transactions
	 * @param hash
	 * @param index
	 * @param pointer
	 * @param miner
	 */
	public Block(Long creationDate, String previousBlockHash, List<Transaction> transactions, String hash, int index,
			Block pointer, Account miner) {
		this.creationDate = creationDate;
		this.previousBlockHash = previousBlockHash;
		this.transactions = transactions;
		this.hash = hash;
		this.index = index;
		this.pointer = pointer;
		this.miner = miner;
		this.creation = new Date();
	}

	/**
	 * @return La fecha de creación del bloque
	 */
	public Long getCreationDate() {
		return creationDate;
	}

	/**
	 * @return El hash del bloque anterior
	 */
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}

	/**
	 * @return La lista de transacciones obtenidas en el bloque
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @return El hash del bloque
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @return El índice del bloque
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return El bloque anterior en la cadena
	 */
	public Block getPointer() {
		return pointer;
	}

	/**
	 * @return El minero del bloque
	 */
	public Account getMiner() {
		return miner;
	}
	
	public String getDate() {
		return DATE_FORMAT.format(creation);
	}
	
	/**
	 * @return Número de transacciones añadidas al bloque
	 */
	public int getNumberOfTransactions() {
		return getTransactions().size();
	}

	/**
	 * @return Importe total de todas las transacciones contenidas en el bloque
	 */
	public double getTotalAmount() {
		return getTransactions().stream().parallel().mapToDouble(m -> m.getAmount()).sum();
	}

	/**
	 * @return Comisión total de todas las transacciones contenidas en el bloque
	 */
	public double getTotalFee() {
		return getTransactions().stream().parallel().mapToDouble(m -> m.getFee()).sum();
	}
	
}
