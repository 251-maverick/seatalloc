package seatallotment;

import java.util.HashMap;
import java.util.Set;

public class MeritList {
	private HashMap<String,Integer> rankMap;                                            
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
		public int getRank(String id){
		return rankmap.get(id);
	}
	
			
	}
	
	public MeritList(int cat){                                          ////with argument as cat
		rankMap=new HashMap<String,Integer>();			//destructor
		category=cat;
	}
	//@PALAK where's getMAp req.
	public HashMap<String,Integer> getMap(){                       //returns hashmap datamember of meritlist class
		return this.rankMap;
	}
	public int size(){                             //return size of hashmap datamember of meritlist class
		return rankMap.size();
	}
	public void updateRank(int val){                //increases values(here rank) by a given val(given as parameter) 
		Set<String> set=rankMap.keySet();
		for(String s: set) rankMap.put(s,rankMap.get(s)+val);
	}
	public void addMap(MeritList m){                //adds another hashmap to the hashmap of given meritllist class
		this.rankMap.putAll(m.getMap());
	}
	
	//public void addCand(String id,Integer rank){    
		//if(rank!=0){
			//rankMap.put(id,new CombinedRank(id,rank)); //kya kiya h?
		//}
	
	public boolean isGreater(Candidate a,Candidate b){  //true if a's rank is larger than b's rank
		if(this.getRank(a.getId())>this.getRank(b.getId())) return true;
		return false;
	}
	
	public boolean compareRank(Candidate a,Candidate b){ //assumption:a is always waitlisted last candidate and b is in applist 
		if(!this.check(b)) return false;    //return true if eligible to come in waitlist
		if(isGreater(a,b)) return true;
		else if(isGreater(b,a)) return false;
		else return true;
	}
	
	
}
	public boolean check(Candidate c) {
		if(rankMap.containsKey(c.getId())){return true;}
		else {return false;}	
	}

}
