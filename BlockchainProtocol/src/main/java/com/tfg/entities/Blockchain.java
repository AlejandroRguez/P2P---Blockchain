package com.tfg.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tfg.utils.IOTools;

public class Blockchain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double COINBASE_AMOUNT = 10.0;
	private static final int TRANSACTIONS_PER_BLOCK = 4;
	private static final int LIMIT_OF_PENDING_TRANSACTIONS = 6;
	
	private static Blockchain instance;
	private static Owner owner = Owner.getInstance();
	
	private Block current;
	private List<User> users = new ArrayList<User>();
	private List<Transaction> currentTransactions = new ArrayList<Transaction>();
	private List<Double> prices = new ArrayList<Double>();
	private double currentPrice = 30;
	private String proofOfWork = "09";
	
	/**
	 * Constructor
	 * Crea el bloque génesis y guarda la primera copia de la cadena
	 */
	private Blockchain () {	
		this.current = new Block(System.currentTimeMillis(), "", new ArrayList<Transaction>(), "Genesis Block", 0, null, owner);
		IOTools.secureSerialize(this);
	}
	
	/**
	 * Patrón Singleton que garantiza que solo habrá una instancia de la cadena
	 * @return La cadena
	 */
	public static Blockchain getInstance() {
		if (instance == null) {instance = new Blockchain(); }
		instance = IOTools.secureDeserialize();
		return instance;
	}
	
	/**
	 * @return Lista de usuarios actuales existentes en la cadena
	 */
	public List<User> getCurrentUsers(){
		return users;
	}
	
	/**
	 * @return El último bloque añadido
	 */
	public Block getCurrent() {
		return current;
	}
	
	/**
	 * Actualiza el último bloque añadido en la cadena cada vez que se añade uno nuevo
	 * @param current
	 */
	public void setCurrent(Block current) {
		this.current = current;
	}

	/**
	 * @return La cuenta owner que simula un dueño de la cadena
	 */
	public Owner getOwner() {
		return owner;
	}
	
	/**
	 * Aumenta en 1000 el saldo de la cuenta Owner
	 */
	public void setTokens() {
		getOwner().setAmount(getOwner().getAmount() + 1000);
	}
	
	/**
	 * @return Los tokens autogenerados por la cadena
	 */
	public double getTokens() {
		return owner.getAmount();
	}

	/**
	 * @return Precio por token actual
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * Añade a la lista de precios el último precio calculado
	 * Se utiliza esta lista para la representación gráfica de evolución del precio del token
	 * @param currentPrice
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
		prices.add(currentPrice);
	}

	/**
	 * @return El número de bloques existentes en la cadena
	 */
	public int getNumberOfBlocks() {
		return current.getIndex()+1;
	}

	/**
	 * @return Número total de tokens en la cadena
	 */
	public double getTotalTokens() {
		 return getCurrentUsers().stream().mapToDouble(a -> a.getAmount()).sum() + getTokens();
	}
	
	/**
	 * @return Importe asociado a la transacción coinbase
	 */
	public double getCoinbaseAmount() {
		return COINBASE_AMOUNT;
	}

	/**
	 * @return Numero máximo de transacciones por bloque
	 */
	public int getTransactionsPerBlock() {
		return TRANSACTIONS_PER_BLOCK;
	}
	
	/**
	 * @return Numero máximo de transacciones pendientes
	 */
	public int getLimitOfPendingTransactions() {
		return LIMIT_OF_PENDING_TRANSACTIONS;
	}
	
	/**
	 * @return Lista de transacciones pendientes de añadir a la cadena
	 */
	public List<Transaction> getCurrentTransactions(){
		return currentTransactions;
	}
	
	/**
	 * @return La cadena que debe contener el hash de un bloque para considerarse válido
	 */
	public String getProofOfWork() {
		return proofOfWork;
	}
	
	/**
	 * Varía la dificultad de la prueba de trabajo
	 * @param proofOfWork
	 */
	public void setProofOfWork(String proofOfWork) {
		this.proofOfWork = proofOfWork;
	}
	
	/**
	 * @return Numero de transacciones añadidas a la cadena
	 */
	public int getNumberOfAddedTransactions() {
		return current.getIndex() * TRANSACTIONS_PER_BLOCK;
	}
	
	/**
	 * @return Numero de transacciones totales (añadidas + sin añadir)
	 */
	public int getNumberOfTotalTransactions() {
		return getNumberOfAddedTransactions() + getNumberOfPendingTransactions();
	}
	
	/**
	 * @return Numero de transacciones pendientes
	 */
	public int getNumberOfPendingTransactions() {
		return getCurrentTransactions().size();
	}
	
	/**
	 * @return La lista de precios por token de la cadena desde su construcción
	 */
	public List<Double> getPrices(){
		return prices;
	}
	
	
	/**
	 * @return La capitalización de mercado de la red
	 */
	public double getMarketCapitalization() {
		return getCurrentPrice() * getTotalTokens();
	}
	
	public String toString() {
		return "Chain status -> {Blocks: " + getNumberOfBlocks() + " | Transactions: " 
			+ getNumberOfTotalTransactions() + " | Users: " + getCurrentUsers().size() + " }";
    }



}
