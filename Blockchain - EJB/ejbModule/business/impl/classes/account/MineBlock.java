package business.impl.classes.account;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;

import business.BlockService;
import business.BlockchainService;
import infraestructure.Factories;
import utils.Crypto;
import utils.LogManager;
import model.Blockchain;
import model.Transaction;

public class MineBlock {
	
	BlockService b = Factories.services.getBlockService();
	BlockchainService bc = Factories.services.getBlockchainService();
	Blockchain bk = Blockchain.getInstance();
	int transactions = bk.getTransactionsPerBlock() - 1;
	Long timestamp = System.currentTimeMillis();
	private int attempt = 1;

	public void mine() {
		
		b.createBlock(timestamp , bk.getCurrent().getHash(), 
				selectTransactions(), proofOfWork(), bk.getCurrent().getIndex() + 1, bk.getCurrent(), bc.selectMiner().getAddress());
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Transaction> selectTransactions(){
		return ((List<Transaction>) bk.getCurrentTransactions()
				.stream().sorted(Transaction.transactionsComparator)).subList(0, transactions);
	}
		
	private Double getNonce() {
		return Math.random();
	}
	
	private String getNonceAsString() {
		return getNonce().toString();
	}
	
	private String getMerkleTreeRoot() {
		return Crypto.getMessageDigest().digest(selectTransactions().stream().
				map(t -> t.getHash().toString()).reduce("",String::concat).getBytes()).toString();
	}
	
	
	private boolean validHash(String hash){
		if(hash.contains(bk.getProofOfWork())) {
			return true;
		}
		return false;
	}
			
	private String proofOfWork() {
		String hash = Crypto.getMessageDigest().digest(
				getMerkleTreeRoot()
				.concat(bk.getCurrent().getHash())
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
