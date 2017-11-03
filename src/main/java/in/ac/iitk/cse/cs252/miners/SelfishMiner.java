package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class SelfishMiner extends BaseMiner implements Miner {
	private Block currenthead;
	private NetworkStatistics stats;
	protected SelfishMiner(String id, int hashRate, int connectivity) {
		super(id, hashRate, connectivity);
	}

	@Override
	public Block currentlyMiningAt() {
		// TODO Auto-generated method stub
		 return currenthead;
	}

	@Override
	public Block currentHead() {
		// TODO Auto-generated method stub
		/*if(currenthead.getPreviousBlock()!= null)	return currenthead.getPreviousBlock();
		else*/ return currenthead;
	}

	@Override
	public void blockMined(Block block, boolean isMinerMe) {
		// TODO Auto-generated method stub
		int flag = 0;
		 if(isMinerMe) {
	            if (block.getHeight() > currenthead.getHeight()) {
	                this.currenthead = block;
	                flag = 1;
	            }
	        }
	        else{ //DO SOMETHING

	            if (currenthead == null) {
	                currenthead = block;
	            } else if (block != null && block.getHeight() > currenthead.getHeight()) {
	                if(flag == 0) {
	                	this.currenthead = block;
	                }
	                flag = 1;

	            }
	        }
		
	}

	@Override
	public void networkUpdate(NetworkStatistics statistics) {
		// TODO Auto-generated method stub
		this.stats = statistics;
	}

	@Override
	public void initialize(Block genesis, NetworkStatistics statistics) {
		// TODO Auto-generated method stub
		this.stats = statistics;
		this.currenthead = genesis;
	}

}
