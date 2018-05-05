package com.tfg.bussiness.block;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.tfg.entities.Block;
import com.tfg.entities.Blockchain;
import com.tfg.entities.Transaction;
import com.tfg.services.BlockService;
import com.tfg.services.BlockchainService;
import com.tfg.services.Services;
import com.tfg.services.TransactionService;
import com.tfg.utils.Crypto;
import com.tfg.utils.IOTools;
import com.tfg.utils.LogManager;

public class NewBlock {
	
	BlockService b = Services.getBlockService();
	TransactionService t = Services.getTransactionService();
	BlockchainService bc = Services.getBlockchainService();
	Blockchain chain = bc.getInstance();
	int transactions = chain.getTransactionsPerBlock();
	Long timestamp = System.currentTimeMillis();
	private int attempt = 1;

	/**
	 * Crea un nuevo bloque, lo añade a la cadena y guarda una copia actualizada de la misma
	 */
	public void create() {		
		Block block = new Block(timestamp , chain.getCurrent().getHash(), 
				selectTransactions() , proofOfWork(), chain.getCurrent().getIndex() + 1, chain.getCurrent(), bc.selectMiner());
		
		executeTransactions();
		
		double amount =  chain.getCoinbaseAmount() + block.getTransactions().stream().parallel().mapToDouble(m -> m.getFee()).sum();
		
		chain.getCurrentUsers().stream().parallel().filter(u -> u.getNickname().
				equals(block.getMiner().getNickname())).findFirst().orElse(null).
				setAmount(chain.getCurrentUsers().stream().parallel().filter(u -> u.getNickname().
						equals(block.getMiner().getNickname())).findFirst().orElse(null).getAmount() + amount);
		
		chain.setCurrent(block);
		IOTools.secureSerialize(chain);
		bc.updateInfo();
	}
	
		
	/**
	 * Ejecuta todas las transacciones contenidas en el bloque
	 */
	private void executeTransactions(){
		chain.getCurrentTransactions()
				.stream().parallel().sorted(Transaction.transactionsComparator).
				collect(Collectors.toList()).subList(0, 4).forEach(tr -> t.executeTransaction(tr));
		chain = bc.getInstance();
	}
	
	/**
	 * Selecciona las transacciones con más fee
	 * @return Lista de transacciones a añadir en el próximo bloque
	 */
	private List<Transaction> selectTransactions(){
		List<Transaction> aux = new ArrayList<Transaction>();
		
		chain.getCurrentTransactions()
		.stream().parallel().sorted(Transaction.transactionsComparator).
		collect(Collectors.toList()).subList(0, 4).forEach(t -> aux.add(t));
		
		return aux;
	}
		
	/**
	 * @return Numero aleatorio que participa en el cálculo del hash del bloque
	 */
	private Double getNonce() {
		return Math.random();
	}
	
	/**
	 * @return El nonce en formato String
	 */
	private String getNonceAsString() {
		return getNonce().toString();
	}
	
	/**
	 * @return La raíz del árbol de Merkle de las transacciones contenidas en el bloque
	 */
	private String getMerkleTreeRoot() {
		return Crypto.getStringHash(selectTransactions().stream().parallel().
				map(t -> t.getHash().toString()).reduce("",String::concat));
	}
	
	
	/**
	 * @param hash
	 * @return true si el hash es válido, false si no
	 */
	private boolean validHash(String hash){
		if(hash.contains(chain.getProofOfWork())) {
			return true;
		}
		return false;
	}
			
	/**
	 * Genera hash aleatorios hasta dar con uno válido
	 * @return El hash del bloque
	 */
	private String proofOfWork() {
		String hash = Crypto.getStringHash(
				getMerkleTreeRoot()
				.concat(chain.getCurrent().getHash())
				.concat(timestamp.toString())
				.concat(getNonceAsString()));
		 
		if(validHash(hash)) {
			LogManager.write(Level.INFO, "Correct hash generated. Attempt -> " + attempt +  " A new block will be added");
			attempt = 1;
			return hash;}		
		else {
			attempt ++;
			return proofOfWork();
		}
	}
	

}
