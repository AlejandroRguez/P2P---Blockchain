package business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import business.impl.classes.block.CreateBlock;
import business.impl.classes.block.FindByMiner;
import model.Block;
import model.Transaction;

@Stateless
@WebService(name="BlockService")
public class EjbBlockService implements LocalBlockService, RemoteBlockService{

	@Override
	public void createBlock(Long creationDate, String previousBlockHash, List<Transaction> transactions, String hash,
			int index, Block pointer, String miner) {
		new CreateBlock().create(creationDate, previousBlockHash, transactions,  hash, index, pointer, miner);
		
	}

	@Override
	public List<Block> getByMiner(String miner) {
		return new FindByMiner().get();
	}
	

}
