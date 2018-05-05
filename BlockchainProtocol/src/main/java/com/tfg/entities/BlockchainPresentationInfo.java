package com.tfg.entities;

import java.util.ArrayList;
import java.util.List;

public class BlockchainPresentationInfo {
	
	public List<Block> blocks;
	public List<Block> mainBlocks;
	public List<Double> prices = new ArrayList<Double>();
	public double currentPrice;
	public double marketCapitalization;
	public int transactions;
	
	private Blockchain chain;
	
	/**
	 * Constructor
	 * Construye el objeto reducido a partir de la blockchain original
	 * @param blockchain
	 */
	public BlockchainPresentationInfo(Blockchain blockchain) {
		this.chain = blockchain;
		this.mainBlocks = getBlocksAsList(chain.getCurrent().getIndex()).subList(0, 5);
		this.blocks = getBlocksAsList(chain.getCurrent().getIndex());
		this.prices = chain.getPrices();
		this.currentPrice = chain.getCurrentPrice();
		this.marketCapitalization = chain.getMarketCapitalization();
		this.transactions = chain.getNumberOfTotalTransactions();
	}
	
	/**
	 * @return Lista de los 5 últimos bloques
	 */
	public List<Block> getMainBlocks() {
		return mainBlocks;
	}
	
	/**
	 * @return Lista de bloques totales
	 */
	public List<Block> getBlocks() {
		return blocks;
	}

	/**
	 * @return Lista de precios
	 */
	public List<Double> getPrices() {
		return prices;
	}

	/**
	 * @return Precio actual del token
	 */
	public double getCurrentPrice() {
		return (double)Math.round(currentPrice * 100d) / 100d;
	}

	/**
	 * @return Capitalización de mercado actual
	 */
	public double getMarketCapitalization() {
		return (double)Math.round(marketCapitalization * 100d) / 100d;
	}

	/**
	 * @return Numero de transacciones registradas
	 */
	public int getTransactions() {
		return transactions;
	}

	/**
	 * @param index
	 * @return Los bloques existentes en la cadena en forma de lista
	 */
	private List<Block> getBlocksAsList(int index){
		List<Block> blocks = new ArrayList<Block>();
		Block block = chain.getCurrent();
		while(index >= 0) {
			blocks.add(block);
			block = block.getPointer();
			index --;
		}
		return blocks;
	}

}
