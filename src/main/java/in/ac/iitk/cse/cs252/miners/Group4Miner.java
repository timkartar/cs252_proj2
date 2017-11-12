package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class Group4Miner extends BaseMiner implements Miner {

	
	protected Group4Miner(String id, int hashRate, int connectivity) {
		super(id, hashRate, connectivity);
	}
	private Block currenthead;
	private Block secrethead;
	private NetworkStatistics stats;
	
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
		// TODO Auto-generated method stub
//		if(isMinerMe) {
//			//do something
//			if(block != null && block.getBlockValue() > 20 || (float)this.getHashRate()/this.stats.getTotalHashRate() > 0.5) {
//				if (block.getHeight() > currenthead.getHeight()) {
//					this.currenthead = block;
//				}
//			}
//		}
//		else {
////			//do something
////			String miner = block.getMinedBy();
////			double value = block.getBlockValue();
//			 if (currenthead == null) {
//	                currenthead = block;
//	            } else if (block != null && block.getHeight() > currenthead.getHeight()) {
//	            	//whenever we have more than half mining capacity reject blocks mined by others
//	            	
//	            	if(!((float)this.getHashRate()/this.stats.getTotalHashRate() > 0.5 || block.getBlockValue() > 20)) {
//	            		this.currenthead = block;
//	            	}
//	                
//	            }
//		}
		if(this.secrethead.equals(this.currenthead) && (float)this.getHashRate()/this.stats.getTotalHashRate() > 0.5 ) {
//    		this.currenthead = block;
			if(isMinerMe && block!= null) {
				//do something
				if (block.getHeight() > currenthead.getHeight()) {
	                this.currenthead = block;
	                this.secrethead = block;
				}
			}
			else if(block != null){
//				//do something
//				String miner = block.getMinedBy();
//				double value = block.getBlockValue();
				 if (currenthead == null) {
		                currenthead = block;
		                this.secrethead = block;
		            } else if (block != null && block.getHeight() > currenthead.getHeight()) {
		            	//whenever we have more than half mining capacity reject blocks mined by others
		                	if((float)this.getHashRate()/this.stats.getTotalHashRate() <= 0.5) {this.currenthead = block;this.secrethead = block;}   
		                
		            }
			}
    	}
		
		
		else if(this.secrethead.equals(this.currenthead) && block.getBlockValue() > 20) {
				if(isMinerMe && block!=null) {
		            if (block.getHeight() > currenthead.getHeight()) {
		                this.currenthead = block;
		            }
		        }
		        else if (block != null){
		            if (currenthead == null) {
		                currenthead = block;
		            } 
		        }    	
		}
		else {
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
		this.secrethead = genesis;
		this.stats = statistics;
		
	}

}
