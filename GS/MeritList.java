//package seatallotment;
package GS;
import common.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MeritList {
	private Map<String,Float> rankMap;                                            
	private int category;//constructor can b changed no need to store cat
	private float last;
	
//	private class CombinedRank{                                         
//		private String cat;
//		private Integer rank;
//		public CombinedRank(String cat,Integer rank){
//			this.cat=cat;
//			this.rank=rank;
//		}
//		
//		public String getCat(){
//			return cat;
//		}
//		
//		public Integer getRank(){
//			return rank;
//		}
//			
//	}
	
	public void addCand(String id,float rank){
		rankMap.put(id, rank);
	}
	
	public MeritList(int cat){                                          ////with argument as cat
		rankMap=new HashMap<String,Float>();			//destructor
		category=cat;
	}
	//@PALAK where's getMAp req.
//	public Map<String,Float> getMap(){                       //returns hashmap datamember of meritlist class
//		return this.rankMap;
//	}
			
	
	public boolean check(Candidate c) {
		if(rankMap.containsKey(c.getId())){return true;}
		else {return false;}	
	}
	public void setLastRank(float i) {
		last=i;
	}
	
	public float getLast() {
		return last;
	}
	
	
	
	
	//merge isBetter and compareRank
	public boolean isBetter(Candidate a,Candidate b){  //true if a's rank is larger than b's rank
		if(rankMap.get(a.getId())<rankMap.get(b.getId())) {return true;}
		else {return false;}
	}
	
	public boolean compareRank(Candidate a,Candidate b){ //assumption:a is always waitlisted last candidate and b is in applist 
		//dont think u need to check PALAK ,i already checkd earlier in ml, remove one of d checks
		if(!this.check(b)) {return false; }  //return true if eligible to come in waitlist
		if(isBetter(a,b)) {return true;}
		else {return false;}
	}

	
	
//	public MeritList combinedRankList(int cat,MeritList meritlist[]){ //creates 'combined' hashmap which caters to seat deservation
//		if(cat==0 || cat==2 || cat==3){                           //y here not in ml @Palak
//		return meritlist[cat];
//	}
//	else if(cat==1 || cat==4 || cat==5){
//		meritlist[0].updateRank(meritlist[cat].size());
//		meritlist[cat].addMap(meritlist[0]);
//		return meritlist[cat];
//	}
//	
//	
//	else if(cat==6 || cat==7){
//		meritlist[cat-4].updateRank(meritlist[cat].size());
//		meritlist[cat].addMap(meritlist[cat-4]);
//		return meritlist[cat];
//	}
//}
	
	public void displayMl(){
		System.out.println(" meritlist " +category);
		for(Map.Entry<String,Float> entry:rankMap.entrySet()){
			System.out.println(entry.getKey()+ " "+entry.getValue());
		}
	}
	
	public void displayRank(String id){
		System.out.println(id+ " "+ rankMap.get(id));
	}

	public int getCategory() {
		return category;
	}
	
	public void combineRank(MeritList ml){
		Set<String> set=ml.rankMap.keySet();
		Map<String,Float> tmp=new HashMap<String,Float>();
		for(String s: set){
			tmp.put(s,ml.rankMap.get(s)+last);
		}
		tmp.putAll(rankMap);
		rankMap=tmp;
	}
//	public void updateRank(int val){                //increases values(here rank) by a given val(given as parameter) 
//		Set<String> set=rankMap.keySet();
//		for(String s: set) rankMap.put(s,rankMap.get(s)+val);
//	}

	

}