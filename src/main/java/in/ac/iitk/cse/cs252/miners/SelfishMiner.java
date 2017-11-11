package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class SelfishMiner extends BaseMiner implements Miner {
	private Block currenthead;
	private Block secrethead;
	int secretlen = 0;
	private NetworkStatistics stats;
	
	protected SelfishMiner(String id, int hashRate, int connectivity) {
		super(id, hashRate, connectivity);
	}

	@Override
	public Block currentlyMiningAt() {
		// TODO Auto-generated method stub
			return secrethead;
	}

	@Override
	public Block currentHead() {
		// TODO Auto-generated method stub
			return currenthead;
	}
	
	@Override
	public void blockMined(Block block, boolean isMinerMe) {
//		// TODO Auto-generated method stub

		 if(isMinerMe) {
			 	int delta = secrethead.getHeight() - currenthead.getHeight();
			 	this.secrethead = block;
			 	secretlen +=1;
	            if (delta ==0 && secretlen == 2) {
	                this.currenthead = block;
	                secretlen = 0;
	            }
	           // this.currenthead = secrethead;
	        }
	        else{
			 	int delta = secrethead.getHeight() - currenthead.getHeight();
			 	Block temphead = currenthead;
			 	this.currenthead = block;
	           
	            if (delta == 0 ) {
	                this.secrethead = currenthead;
	                secretlen = 0;
	            }else if(delta == 1){
	            	this.currenthead = secrethead;
	            	secretlen = 0;
	            }else if(delta == 2) {
	            	this.currenthead = secrethead;
	            	secretlen = 0;

	            }else {
	            	Block topublish = secrethead;
	            	while(topublish.getPreviousBlock()!=null && topublish.getPreviousBlock()!= temphead) {
	            		topublish = topublish.getPreviousBlock();
	            	}
	            	this.currenthead = topublish;
	            	secretlen -= 1;
	            }
	            	
	            }
	        }
		
	
	
	public void withhold(Block  block) {
		//if(currenthead.getPreviousBlock().getMinedBy().equals(this.getId())) currenthead = block;
		//System.out.println("working");
		//flag = 1; 
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
		this.secrethead = genesis;
	}

}
