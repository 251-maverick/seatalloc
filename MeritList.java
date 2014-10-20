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
		
		public Integer getRank(){
			return rank;
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
	
	
	
	public boolean compareRank(CombinedRank other){			//improve
		if(this.category=="OBC"){
		//do later
		}
	}
	
	public MeritList combinedRankList(int cat,MeritList meritlist[]){ //creates 'combined' hashmap which caters to seat deservation
		if(cat==0 || cat==2 || cat==3){                           //y here not in ml @Palak
		return meritlist[cat];
	}
	else if(cat==1 || cat==4 || cat==5){
		meritlist[0].updateRank(meritlist[cat].size());
		meritlist[cat].addMap(meritlist[0]);
		return meritlist[cat];
	}
	
	
	else if(cat==6 || cat==7){
		meritlist[cat-4].updateRank(meritlist[cat].size());
		meritlist[cat].addMap(meritlist[cat-4]);
		return meritlist[cat];
	}
}
	public boolean check(Candidate c) {
		if(rankMap.containsKey(c.getId())){return true;}
		else {return false;}	
	}

}
