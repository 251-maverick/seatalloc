import java.util.HashMap;

public class MeritList {

	private HashMap rankMap;
	//private Integer category;//might map to int
	private String category;
	boolean pd; 	//not required
	
	private class CombinedRank{
		private String cat;
		private Integer rank;
		public CombinedRank(String cat,Integer rank){
			this.cat=cat;
			this.rank=rank;
		}
		
		public String getCat(){
			return cat;
		}
		
		public Integer getRank(){
			return rank;
		}
			
	}
	public MeritList(String category){
		rankMap=new HashMap();			//destructor
		//pd=false;
		//this.category=category;
	}
	
	public void addCand(String id,Integer rank){
		if(rank!=0){
			rankMap.put(id,new CombinedRank(id,rank));
		}
	}
	
	public void completeMap(MeritList GE,MeritList SC,MeritList ST){                //think of a better method
		if (this.category=="GE-PD" || this.category=="OBC" || this.category=="OBC-PD"){
			
			//do this
//			for(Map.Entry e : a.entrySet())  
//				  if(!b.containsKey(e.getKey())
//				    b.put(e.getKey(), e.getValue());		
			}
	}
	
	public boolean isGreater(CombinedRank other){			//improve
		if(this.category=="OBC"){
			//do later
		}
	}
	
	
}
