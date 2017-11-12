package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class SelfishMiner extends BaseMiner implements Miner {
	private Block currenthead;
	private Block secrethead;
	//int secretlen = 0;
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

		 if(isMinerMe && block!=null) {
			 	//int delta = secrethead.getHeight() - currenthead.getHeight();
			 	if(this.secrethead ==null) this.secrethead = block;
			 	int delta = block.getHeight() - secrethead.getHeight();

			 	//secretlen +=1;
	            if (delta > 0 ) {
	                this.secrethead = block;
	                
	            }
	           // this.currenthead = secrethead;
	        }
	        else if(block != null){
			 	int delta = block.getHeight() - secrethead.getHeight();
			 	if(currenthead == null) this.currenthead = block;
			 	else if (delta > 0 ) {
	                this.secrethead = block;
	                this.currenthead = block;
	              //  secretlen = 0;
	            } 
			 	delta = block.getHeight() - secrethead.getHeight();
	            if( !(delta < -1) ){
	            	this.currenthead = secrethead;
	            	//secretlen = 0;
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
