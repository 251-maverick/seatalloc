import java.util.HashMap;

public class MeritList {

	private HashMap rankmap;                                            
	private int category;
	
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
	public MeritList(int cat){                                          ////with argument as cat
		rankmap=new HashMap<String,int>();			//destructor
		category=cat;
	}
	public HashMap getMap(){                       //returns hashmap datamember of meritlist class
		return this.rankmap;
	}
	public int size(){                             //return size of hashmap datamember of meritlist class
		return rankmap.size();
	}
	public void updateRank(int val){                //increases values(here rank) by a given val(given as parameter) 
		Set<String> set=rankmap.keySet();
		for(String s: set) rankmap.put(s,rankmap.get(s)+val);
	}
	public void addMap(MeritList m){                //adds another hashmap to the hashmap of given meritllist class
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
	
	

