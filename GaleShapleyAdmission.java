package seatallotment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Vector;

public class GaleShapleyAdmission {
	private HashMap<String,VirtualProgramme> vpMap;//change String to class Preference ? 
	private Vector<HashMap<String,Integer> > mlMap;
	
	public GaleShapleyAdmission() throws FileNotFoundException{ 
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
		while(scan.HasNextLine()){
			if(firstline){
				firstline=false;
				continue;
			}
			s=scan.NextLine();
			String[] fields=s.split(",");
			for(int i=3;i<11;i++){
				Integer x=Integer.parseInt(fields[i])
				if(x!=0){
					mlMap[i-3].put(fields[0],x);
				}
			}
		}
	}//end of RanklistInput
	//I'm putting back the original inputCandidate and made the cat changes in candidate constructor
	/*public void inputCandidate() throws FileNotFoundException{
		
		Scanner scan=new Scanner(new File("./choices.csv"));
		scan.nextLine();
		String s;
		while(scan.hasNextLine()){
			s=scan.nextLine();
			
			/** taking each candidates input */
	/*		String[] field=s.split(",");
			if(field[1]=="GE" && field[2]=="N"){
				Candidate c=new Candidate(field[0],0);
				c.addPreference(field[3]);			//put nxt and prv pointers
				candidateList.add(c);				//set initial next pref also in this
			}
			if(field[1]=="OBC" && field[2]=="N"){
				Candidate c=new Candidate(field[0],1);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
			if(field[1]=="SC" && field[2]=="N"){
				Candidate c=new Candidate(field[0],2);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
			if(field[1]=="ST" && field[2]=="N"){
				Candidate c=new Candidate(field[0],3);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
			if(field[1]=="GE" && field[2]=="Y"){
				Candidate c=new Candidate(field[0],4);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
			if(field[1]=="OBC" && field[2]=="Y"){
				Candidate c=new Candidate(field[0],5);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
			if(field[1]=="SC" && field[2]=="Y"){
				Candidate c=new Candidate(field[0],6);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
			if(field[1]=="ST" && field[2]=="Y"){
				Candidate c=new Candidate(field[0],7);
				c.addPreference(field[3]);			
				candidateList.add(c);	
			}
		}
		ListIterator<Candidate> itr=candidateList.listIterator();
//		while(itr.hasNext()){
//			itr.setNext(itr.next());
//		}
	}*/
public void inputCandidate() throws FileNotFoundException{//added index i// no more changes required mostly
		
		Scanner scan=new Scanner(new File("./choices.csv"));
		scan.nextLine();
		String s;
		int i=0;
		while(scan.hasNextLine()){
			s=scan.nextLine();
			
			/** taking each candidates input */
			String[] field=s.split(",");
			for(int i=0;i<3;i++){
				Candidate c=new Candidate(field[0],field[1],field[2],i);
				c.addPreference(field[3]);			//put nxt and prv pointers
				candidateList.add(c);				//set initial next pref also in this
			}
			i++;
		}
		ListIterator<Candidate> itr=candidateList.listIterator();
//		while(itr.hasNext()){
//			itr.setNext(itr.next());
//		}
	}
	
public void candidateApply(){
	ListIterator<Candidate> itr=candidateList.listIterator();
	while(itr.hasNext()){
		Candidate c=itr.next(); 
	    if(c.getCurrProg()=="-1"){
	    	if(c.getNextPref() !=-1){
	    		vpMap.get(c.getNextPref()).receiveApp(c);
	    		c.setNextPref(c.getNextPref()+1);
	    	}
	    }
	}
}
public void createWaitlist(){
//	Iterator it= vpMap.entrySet().iterator();
//	while(it.hasNext()){
//		//Map.Entry<String,VirtualProgramme> vp = it.next();
//		
//	}
	for (Map.Entry<String, VirtualProgramme> entry : vpMap.entrySet()) {
		
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
	}

}
