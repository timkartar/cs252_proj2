package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;
import in.ac.iitk.cse.cs252.miners.BaseMiner;
import in.ac.iitk.cse.cs252.miners.Miner;

public class FeeSnipingMiner extends BaseMiner implements Miner {
	private Block currenthead;
	
	protected FeeSnipingMiner(String id, int hashRate, int connectivity) {
		super(id, hashRate, connectivity);
	}

	 @Override
	    public Block currentlyMiningAt() {
	        return currenthead;
	    }

	    @Override
	    public Block currentHead() {
	        return currenthead;
	    }

	    @Override
	    public void blockMined(Block block, boolean isMinerMe) {
	        if(isMinerMe && block!=null) {
	            if (block.getHeight() > currenthead.getHeight()) {
	                this.currenthead = block;
	            }
	        }
	        else if (block != null){
	            if (currenthead == null) {
	                currenthead = block;
	            } else if (block != null && block.getHeight() > currenthead.getHeight()) {
	            	//System.out.println(block.getBlockValue());
	            	if(block.getBlockValue() < 20) {    // took 20 as threshold value for 
	            		this.currenthead = block;
	            	}
//	            	else {
//	            		Block theftblock = new Block(currenthead,this.getId(),block.getBlockValue());
//	            	}
	            }
	        }
	    }


	    @Override
	    public void initialize(Block genesis, NetworkStatistics networkStatistics) {
	        this.currenthead = genesis;
	    }

	    @Override
	    public void networkUpdate(NetworkStatistics statistics) {

	    }

}
