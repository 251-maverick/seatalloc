package seatallotment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Vector;

public class GaleShapleyAdmission {
	private HashMap<String,VirtualProgramme> vpMap; 
	private Vector<HashMap<String,Integer> > mlMap;
	private Vector<Candidate> candidateList;
	private LinkedList<Integer> currCand;
	
	public GaleShapleyAdmission() { 
		candidateList=new Vector<Candidate>();
		currCand=new LinkedList<Integer>();
		this.createVP();
		this.inputCandidate();
		this.ranklistInput();
	}
	
	public void createVP() throws FileNotFoundException{
		vpMap=new HashMap<String,VirtualProgramme>();   //virtualProgramme is hashmap of (code_of_program+'-category_code',corresponding virtual program)
		Scanner scan=new Scanner(new File("./programs.csv"));
		boolean firstline=true;
		String s;
		while(scan.hasNextLine()){                              //reads from program.csv and creates virtualProgramme hashmap
			if(firstline){
				firstline=false;
				continue;
			}
			s=scan.nextLine();
			String[] field=s.split(",");
			for(int i=3;i<11;i++){
				VirtualProgramme p;
				int y = Integer.parseInt(field[i]);
				p=new VirtualProgramme(i-3,y);               //see if vp object can be created without any import or smthing
				vpMap.put(field[1]+"-"+(i-3),p);
			}
		}
	}

	public void ranklistInput() throws FileNotFoundException{
		for(int i=0;i<8;i++) mlMap.add(i,new HashMap<String,Integer()>); //initialize the list
		scan=new Scanner(new File("./ranklist.csv"));
		firstline=true;
		while(scan.hasNextLine()){
			if(firstline){
				firstline=false;
				continue;
			}
			s=scan.nextLine();
			String[] fields=s.split(",");
			for(int i=3;i<11;i++){
				Integer x=Integer.parseInt(fields[i])
				if(x!=0){
					mlMap[i-3].put(fields[0],x);
				}
			}
		}
	}//end of RanklistInput
	
	
	public void inputCandidate() throws FileNotFoundException{
		
		Scanner scan=new Scanner(new File("./choices.csv"));
		scan.nextLine();
		String s;
		while(scan.hasNextLine()){
			s=scan.nextLine();
			
			/** taking each candidates input */
			String[] field=s.split(",");
			for(int i=0;i<3;i++){
				Candidate c=new Candidate(field[0],field[1],field[2],candidateList.size());
				c.addPreference(field[3]);			//put nxt and prv pointers
				currCand.add(candidateList.size());
				 candidateList.add(c);		//set initial next pref also in this
				//currCand.add(candidateList.size()-1);
			}
		}

	}
	
	

public boolean candidateApply(){
	boolean allAssigned=true,prefComplete=true;
	//ListIterator<Integer> it=currCand.listIterator();
	Integer index;
	if(currCand.size()==0){return false;}
	for (Iterator<Integer> it = currCand.iterator(); it.hasNext(); ){
        index = it.next(); 
		Candidate c=candidateList.get(index); //hoe its reference
	    if(c.getNextPref() !=-1){
	    	prefComplete=false;
	    	vpMap.get(c.getNextPref()).receiveApp(c);
	    	c.setCurrProg(c.getNextPref());
	    	c.setNextPref(c.getNextPref()+1);
	    }
	 
	}
	currCand.clear();
	if(prefComplete){return false;}
	else return true;
		
}

//public boolean candidateApply(){
//	boolean allAssigned=true,prefComplete=true;
//	ListIterator<Integer> it=candidateList.listIterator();
//	while(itr.hasNext()){
//		Candidate c=itr.next(); 
//	    if(c.getCurrProg()=="-1" ){  //next pref mite nt b required later if using list of index
//	    	allAssigned=false;
//	    	if(c.getNextPref() !=-1){
//	    		prefComplete=false;
//	    		vpMap.get(c.getNextPref()).receiveApp(c);
//	    		c.setCurrProg(c.getNextPref());
//	    		c.setNextPref(c.getNextPref()+1);
//	    	}
//	    }
//	}
//	if(allAssigned || prefComplete){return false;}
//	else return true;
//		
//}

public void updateCurrProg(LinkedList<Integer> rej){
	ListIterator<Integer> it=rej.listIterator();
	while(it.hasNext()){
		int index=it.next();
		candidateList.get(index).setCurrProg(-1);
	}
		
}


public void createWaitlist(){
	LinkedList<Integer> rejectedList=new LinkedList<Integer>();
	for (VirtualProgramme vp : vpMap.values()) {
		rejectedList=vp.filterApp(mlMap.get(vp.getCategory()));  //vp category shud b int
		updateCurrProg(rejectedList);
		currCand.addAll(rejectedList);
	}
	for (VirtualProgramme vp : vpMap.values()) {
		rejectedList=vp.filterApp(mlMap.get(vp.getCategory()));  //vp category shud b int
		updateCurrProg(rejectedList);
		currCand.addAll(rejectedList);

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

public void output(){
	for(int i=0;i<candidateList.size();i++){
		System.out.println(candidateList.get(i).getId()+" "+candidateList.get(i).getCurrProg());
	}
}

public void gsAlgo(){
		while(candidateApply()){
			createWaitlist();
		}
		output();
}

}
