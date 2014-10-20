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
	private Vector<MeritList> mlMap;
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
		VirtualProgramme p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17;
		p0=new Virtualprogramme();p1=new Virtualprogramme();p2=new Virtualprogramme();
		p3=new Virtualprogramme();p4=new Virtualprogramme();p5=new Virtualprogramme();
		p6=new Virtualprogramme();p7=new Virtualprogramme();p8=new Virtualprogramme();
		p9=new Virtualprogramme();p10=new Virtualprogramme();p11=new Virtualprogramme();
		p12=new Virtualprogramme();p13=new Virtualprogramme();p14=new Virtualprogramme();
		p15=new Virtualprogramme();p16=new Virtualprogramme();p17=new Virtualprogramme();
		vpMap.put("B",p0);vpMap.put("D",p1);vpMap.put("K",p2);vpMap.put("M",p3);vpMap.put("G",p4);
		vpMap.put("R",p5);vpMap.put("W",p6);vpMap.put("A",p7);vpMap.put("C",p8);vpMap.put("E",p9);
		vpMap.put("H",p10);vpMap.put("J",p11);vpMap.put("N",p12);vpMap.put("P",p13);vpMap.put("S",p14);
		vpMap.put("U",p15);vpMap.put("V",p16);vpMap.put("S",p17);
		
	}

	public void ranklistInput() throws FileNotFoundException{
		for(int i=0;i<8;i++){ mlMap.add(i,new MeritList(i));} //initialize the list
		Scanner scan=new Scanner(new File("./ranklist.csv"));
		scan.nextLine();
		int lastRank[];
		while(scan.hasNextLine()){
			String s=scan.nextLine();
			String[] fields=s.split(",");
			for(int i=3;i<11;i++){
				Integer rank=Integer.parseInt(fields[i]);
				if(rank!=0){
					if(rank>=lastRank){lastRank[i-3]=rank;}
					mlMap.get(i-3).addCand(fields[0],rank);
				}
			}
		}
		set
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
	
	public MeritList combinedRankList(int cat,MeritList meritlist[]){ //creates 'combined' hashmap which caters to seat deservation
		if(cat==0 || cat==2 || cat==3){                           
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
	else if(cat==8){
		return meritlist[0];
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
	    	if((c.getNextProg()).endsWith("ds")){                    ////this if bracket added by r
	    		String s=(c.getNextProg()).substring(0,0);
	    		System.out.println("Adding app to ds program "+s);
	    		vpMap.get(s).receiveApp(c);
	    		c.setCurrProg(c.getNextPref());
		    	c.setNextPref(c.getNextPref()+1);
	    	}
	    	else {
	    		vpMap.get(c.get).receiveApp(c);
	    	    c.setCurrProg(c.getNextPref());
	    	    c.setNextPref(c.getNextPref()+1);
	    }
//	    	vpMap.get(c.getNextPref()).receiveApp(c);
//	    	c.setCurrProg(c.getNextPref());
//	    	c.setNextPref(c.getNextPref()+1);
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
		rejectedList=vp.filterApp(mlMap.get(vp.getCategory()));  
		//vp category shud b int
		updateCurrProg(rejectedList);
		currCand.addAll(rejectedList);
	}
	for (VirtualProgramme vp : vpMap.values()) {
		rejectedList=vp.filterApp(mlMap.get(vp.getCategory()));  //vp category shud b int
		updateCurrProg(rejectedList);
		currCand.addAll(rejectedList);

	}
}



public void output(){
	for(int i=0;i<candidateList.size();i++){
		System.out.println(candidateList.get(i).getId()+" "+candidateList.get(i).getCurrProg().substring(0, 4));
	}
}

public void gsAlgo(){
		while(candidateApply()){
			createWaitlist();
		}
		output();
}

}
