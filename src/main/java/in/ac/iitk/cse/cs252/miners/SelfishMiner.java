package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class SelfishMiner extends BaseMiner implements Miner {
	private Block currenthead;
	private Block secrethead;
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
			return currenthead;
	}
	
	@Override
	public void blockMined(Block block, boolean isMinerMe) {
//		// TODO Auto-generated method stub
//		int count = 0;
//		if(isMinerMe) {
//		  if (block.getHeight() > currenthead.getHeight()) {
//              //this.currenthead = block;
//			  if(this.secrethead.getMinedBy().equals(this.getId())) {
//				  this.secrethead = block;
//				  count++;
//			  }
//			  else {
//				  count = 1;
//				  this.secrethead = block;
//			  }
//          }
//		}
//		else{
//          if (currenthead == null) {
//              currenthead = block;
//          } else if (block != null  && block.getHeight() > currenthead.getHeight()) {
//              if(count > 1) {this.currenthead = secrethead; this.notify();}
//              else if(this.getConnectivity()/stats.getTotalConnectivity() > 0.5) {
//            	  this.currenthead = secrethead;
//            	  //this.notifyAll();
//              }
//              else this.secrethead = block;
//              this.currenthead = block;
//          			
//
//          }
//      }
		 if(isMinerMe) {
	            if (block.getHeight() > currenthead.getHeight()) {
	                this.secrethead = block;
	                this.currenthead = block;
	            }
	        }
	        else{
	            if (currenthead == null) {
	                currenthead = block;
	            } else if (block != null && block.getHeight() > currenthead.getHeight()) {
	                this.currenthead = block;

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
