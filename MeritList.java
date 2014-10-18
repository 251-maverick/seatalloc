import java.util.HashMap;

public class MeritList {

	private HashMap rankmap;                                            ////need a hashset
	private int category;
	//boolean pd; 	//not required
	
	private class CombinedRank{                                         ////would need only id and rank no cat
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
	public MeritList(int cat){                                          ////with argument as cat
		rankmap=new HashMap<String,int>();			//destructor
		category=cat;
	}
	public HashMap getMap(){
		return this.rankmap;
	}
	public void updateRank(int val){
		Set<String> set=rankmap.keySet();
		for(String s: set) rankmap.put(s,rankmap.get(s)+val);
	}
	public void addMap(MeritList m){
		this.rankmap.putAll(m.getMap());
	}
	
	public void addCand(String id,Integer rank){
		if(rank!=0){
			rankMap.put(id,new CombinedRank(id,rank));
		}
	}
	
	
			
			
			
			
			
			
			
			
			//do this
//			for(Map.Entry e : a.entrySet())  
//				  if(!b.containsKey(e.getKey())
//				    b.put(e.getKey(), e.getValue());		
			}
	}
	
	//public boolean isGreater(CombinedRank other){			//improve
		
		//}
	}
	
	

