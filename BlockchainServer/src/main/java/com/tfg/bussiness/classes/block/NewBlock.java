package com.tfg.bussiness.classes.block;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.tfg.bussiness.BlockService;
import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.entities.Block;
import com.tfg.entities.Transaction;
import com.tfg.utils.Crypto;
import com.tfg.utils.LogManager;

public class NewBlock {
	
	BlockService b = Services.getBlockService();
	BlockchainService bc = Services.getBlockchainService();
	int transactions = bc.getInstance().getTransactionsPerBlock();
	Long timestamp = System.currentTimeMillis();
	private int attempt = 1;
	List<Transaction> transactionsToMine = selectTransactions();

	public void create() {
		
		
		Block b = new Block(timestamp , bc.getInstance().getCurrent().getHash(), 
				transactionsToMine, proofOfWork(), bc.getInstance().getCurrent().getIndex() + 1, bc.getInstance().getCurrent(), bc.selectMiner());
		
		transactionsToMine.stream().forEach(t -> Services.getTransactionService().executeTransaction(t));
		b.getMiner().setAmount(b.getMiner().getAmount() 
				+ bc.getInstance().getCoinbaseAmount() 
				+ transactionsToMine.stream().mapToDouble(m -> m.getFee()).sum());
		
		bc.getInstance().setCurrent(b);
		bc.updateInfo();
			
	}
	
		
	private List<Transaction> orderTransactions(){
		return bc.getInstance().getCurrentTransactions()
				.stream().sorted(Transaction.transactionsComparator).collect(Collectors.toList());
	}
	
	private List<Transaction> selectTransactions(){
		List<Transaction> transactionsOrdered = orderTransactions();
		List<Transaction> transactionsToMine = new ArrayList<Transaction>();
		
		for(int i = 0; i<transactions; i++) {
			transactionsToMine.add(transactionsOrdered.get(i));
			bc.getInstance().getCurrentTransactions().remove(transactionsOrdered.get(i));
		}
		
		return transactionsToMine;
	}
		
	private Double getNonce() {
		return Math.random();
	}
	
	private String getNonceAsString() {
		return getNonce().toString();
	}
	
	private String getMerkleTreeRoot() {
		return Crypto.getMessageDigest().digest(transactionsToMine.stream().
				map(t -> t.getHash().toString()).reduce("",String::concat).getBytes()).toString();
	}
	
	
	private boolean validHash(String hash){
		if(hash.contains(bc.getInstance().getProofOfWork())) {
			return true;
		}
		return false;
	}
			
	private String proofOfWork() {
		String hash = Crypto.getMessageDigest().digest(
				getMerkleTreeRoot()
				.concat(bc.getInstance().getCurrent().getHash())
				.concat(timestamp.toString())
				.concat(getNonceAsString())
				.getBytes(StandardCharsets.UTF_8)).toString();
		 
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
