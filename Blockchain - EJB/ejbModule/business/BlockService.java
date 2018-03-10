package business;

import java.util.List;

import model.Block;
import model.Transaction;

public interface BlockService {
	
	void createBlock(Long creationDate, String previousBlockHash, List<Transaction> transactions, String hash, int index,
			Block pointer, String miner);
	List<Block> getByMiner(String miner);
	

}
