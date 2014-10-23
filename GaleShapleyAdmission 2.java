//package seatallotment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
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
		mlMap=new Vector<MeritList>();
		createVp();
		inputCandidate();
		rankListInput();
		createMl();
	}
	
	public void createVp() {
		vpMap=new HashMap<String,VirtualProgramme>();   //virtualProgramme is hashmap of (code_of_program+'-category_code',corresponding virtual program)
		try{
		Scanner scan=new Scanner(new File("/Users/Palak/Desktop/academics/sem3/cs251/lab10/src/programs.csv"));
		String s;
		scan.nextLine();
		while(scan.hasNextLine()){                              //reads from program.csv and creates virtualProgramme hashmap
			s=scan.nextLine();
			String[] field=s.split(",");
			for(int i=3;i<11;i++){
				int y = Integer.parseInt(field[i]);
				String prog=field[1]+"-"+(i-3);
				VirtualProgramme p=new VirtualProgramme(i-3,y,prog);  //see if vp object can be created without any import or smthing
				//System.out.println("prog "+prog);
				vpMap.put(prog,p);
			}
		 }
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
//		VirtualProgramme[] p=new VirtualProgramme[18];
//		vpMap.put("B",p[0]);vpMap.put("D",p[1]);vpMap.put("K",p[2]);vpMap.put("M",p[3]);vpMap.put("G",p[4]);
//		vpMap.put("R",p[5]);vpMap.put("W",p[6]);vpMap.put("A",p[7]);vpMap.put("C",p[8]);vpMap.put("E",p[9]);
//		vpMap.put("H",p[10]);vpMap.put("J",p[11]);vpMap.put("N",p[12]);vpMap.put("P",p[13]);vpMap.put("S",p[14]);
//		vpMap.put("U",p[15]);vpMap.put("V",p[16]);vpMap.put("S",p[17]);	
		
		//displayVpmap();
	}
	
	
	public void rankListInput() {
		
		for(int i=0;i<8;i++){MeritList m=new MeritList(i); mlMap.add(m);} //initialize the list
		try{
		Scanner scan=new Scanner(new File("/Users/Palak/Desktop/academics/sem3/cs251/lab10/src/rank1.csv"));
		scan.nextLine();
		int[] lastRank=new int[8];
		while(scan.hasNextLine()){
			String s=scan.nextLine();
			String[] fields=s.split(",");
			for(int i=3;i<7;i++){
				Integer rank=Integer.parseInt(fields[i]);
				if(rank!=0){
					if(rank>= lastRank[i-3]){lastRank[i-3]=rank;}
					mlMap.get(i-3).addCand(fields[0],rank);
				}
				Integer rank2=Integer.parseInt(fields[i+5]);
				if(rank2!=0){
					if(rank2>= lastRank[i+1]){lastRank[i+1]=rank2;}
					mlMap.get(i+1).addCand(fields[0],rank2);
				}
			}
			
		}
		for(int i=0;i<8;i++){
			mlMap.get(i).setLastRank(lastRank[i]);
			//mlMap.get(i).displayMl();
		}
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		
	}//end of RanklistInput
	
	public void createMl(){
		mlMap.get(1).combineRank(mlMap.get(0));
		mlMap.get(4).combineRank(mlMap.get(0));
		mlMap.get(5).combineRank(mlMap.get(0));
		mlMap.get(6).combineRank(mlMap.get(2));
		mlMap.get(7).combineRank(mlMap.get(3));

	}
	
	
	public void inputCandidate() {
		try{
		Scanner scan=new Scanner(new File("/Users/Palak/Desktop/academics/sem3/cs251/lab10/src/cand1.csv"));
		scan.nextLine();
		String s;
			while(scan.hasNextLine()){
				s=scan.nextLine();
				/** taking each candidates input */
				String[] field=s.split(",");
				Candidate c=new Candidate(field[0],field[1],field[2],candidateList.size());
				c.addPreference(field[3]);			//put nxt and prv pointers
				currCand.add(candidateList.size());
				 candidateList.add(c);		
				//currCand.add(candidateList.size()-1);
				c.displayCand();
			
			}
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
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
	    		vpMap.get(c.getNextProg()).receiveApp(c); //is dis correct
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
	else {return true;}
		
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
	//LinkedList<Integer> rejectedList=new LinkedList<Integer>();
	//for (VirtualProgramme vp : vpMap.values()) {
		//rejectedList.clear();
		//rejectedList.addAll(vp.filterApp(mlMap.get(vp.getCategory())));
	
		Iterator<VirtualProgramme> it=vpMap.values().iterator();
		while(it.hasNext()){
		VirtualProgramme vp=(VirtualProgramme) it.next();
		//System.out.print(vp.getCategory());
		LinkedList<Integer> rejectedList=new LinkedList<Integer>(vp.filterApp(mlMap.get(vp.getCategory())));
		updateCurrProg(rejectedList);
		currCand.addAll(rejectedList);
		}
	//}
	
}

public void displayVpmap(){
	System.out.println("vpmap ");
	for(Map.Entry<String,VirtualProgramme> m:vpMap.entrySet()){
		System.out.print(m.getKey()+" ");
		m.getValue().displayWaitList();
	}
}

public void displayCurrCand(){
	System.out.println("Current cand ");
	ListIterator<Integer> it=currCand.listIterator();
	while(it.hasNext()){
		int n=it.next();
		System.out.println(n+" "+candidateList.get(n).getId());
		//candidateList.get(n).displayCand();
	}
}



public void output(){
	for(int i=0;i<candidateList.size();i++){
		System.out.println(candidateList.get(i).getId()+" "+candidateList.get(i).getCurrProg().substring(0, 4));
	}
}

	public void gsAlgo(){

//		for(int i=0;i<8;i++){
//			mlMap.get(i).displayMl();
//		}	
	while(candidateApply()){
		createWaitlist();
		displayCurrCand();
		
	}
//		while(candidateApply()){
//			createWaitlist();
//		}
	output();
}

}
