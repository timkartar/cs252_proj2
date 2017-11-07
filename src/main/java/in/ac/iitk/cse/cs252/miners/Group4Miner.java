package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class Group4Miner extends BaseMiner implements Miner {

	
	protected Group4Miner(String id, int hashRate, int connectivity) {
		super(id, hashRate, connectivity);
	}
	private Block currenthead;
	private NetworkStatistics stats;
	
	@Override
	public Block currentlyMiningAt() {
		// TODO Auto-generated method stub
		return currenthead;
	}

	@Override
	public Block currentHead() {
		// TODO Auto-generated method stub
		return currenthead;
	}

	@Override
	public void blockMined(Block block, boolean isMinerMe) {
		// TODO Auto-generated method stub
		if(isMinerMe) {
			//do something
			if (block.getHeight() > currenthead.getHeight()) {
                this.currenthead = block;
			}
		}
		else {
//			//do something
//			String miner = block.getMinedBy();
//			double value = block.getBlockValue();
			 if (currenthead == null) {
	                currenthead = block;
	            } else if (block != null && block.getHeight() > currenthead.getHeight()) {
	            	//whenever we have more than half mining capacity reject blocks mined by others
	            	
	            	if(!((float)this.getHashRate()/this.stats.getTotalHashRate() > 0.5 || block.getBlockValue() > 20)) {
	            		this.currenthead = block;
	            	}
	                
	            }
		}
		
	}

	@Override
	public void networkUpdate(NetworkStatistics statistics) {
		// TODO Auto-generated method stub
		//this.totalhashrate = statistics.getTotalHashRate();
		this.stats = statistics;
	}

	@Override
	public void initialize(Block genesis, NetworkStatistics statistics) {
		// TODO Auto-generated method stub
		this.currenthead = genesis;
		this.stats = statistics;
		
	}

}
