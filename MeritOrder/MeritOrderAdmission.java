package MeritOrder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import common.*;
/**
 * <h> Implementation of GaleShapleyAlgorithm </h> 
 * This class implements the entire algorithm
 * vpMap is a HashMap of Programme code to corresponding Virtual Programme
 * candidateList is a vector of candidates participating in this allocation 
 * meritList is the meritlist used in this algorithm 
 * @author maverick
 *
 */

public class MeritOrderAdmission {
	private String filepath;
	private HashMap<String,VirtualProgramme> vpMap; 
	private Vector<Candidate> candidateList;
	private MeritList meritList;
	
	public MeritOrderAdmission(String path) { 
		filepath=path;
		candidateList=new Vector<Candidate>();
		createVp();
		inputCandidate();
		rankListInput();
	}
	
	public void createVp() {
		vpMap=new HashMap<String,VirtualProgramme>();   //virtualProgramme is hashmap of (code_of_program+'-category_code',corresponding virtual program)
		try{
		Scanner scan=new Scanner(new File(filepath+"/programs.csv"));
		String s;
		scan.nextLine();
		while(scan.hasNextLine()){                              //reads from program.csv and creates virtualProgramme hashmap
			s=scan.nextLine();
			String[] field=s.split(",");
			for(int i=3;i<11;i++){
				int y = Integer.parseInt(field[i]);
				String prog=field[1]+"-"+(i-3);
				VirtualProgramme p=new VirtualProgramme(i-3,y,prog);  
				//System.out.println("prog "+prog);
				vpMap.put(prog,p);
			}
		 }
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		VirtualProgramme[] p=new VirtualProgramme[18];
		for(int i=0;i<18;i++){p[i]=new VirtualProgramme();}
		vpMap.put("B",p[0]);vpMap.put("D",p[1]);vpMap.put("K",p[2]);vpMap.put("M",p[3]);vpMap.put("G",p[4]);
		vpMap.put("R",p[5]);vpMap.put("W",p[6]);vpMap.put("A",p[7]);vpMap.put("C",p[8]);vpMap.put("E",p[9]);
		vpMap.put("H",p[10]);vpMap.put("J",p[11]);vpMap.put("N",p[12]);vpMap.put("P",p[13]);vpMap.put("S",p[14]);
		vpMap.put("U",p[15]);vpMap.put("V",p[16]);vpMap.put("S",p[17]);	
		
		//displayVpmap();
	}
	
	
	public void rankListInput() {
		try{meritList=new MeritList(candidateList,filepath);}
		catch(FileNotFoundException e){
			e.printStackTrace();}
		/*for(int i=0;i<8;i++){MeritList m=new MeritList(i); mlMap.add(m);} //initialize the list
		try{
		Scanner scan=new Scanner(new File(filepath+"/ranklist.csv"));
		scan.nextLine();
		float[] lastRank=new float[8];
		while(scan.hasNextLine()){
			String s=scan.nextLine();
			String[] fields=s.split(",");
			for(int i=3;i<7;i++){
				float rank=Float.parseFloat(fields[i]);
				if(rank!=0){
					if(rank>= lastRank[i-3]){lastRank[i-3]=rank;}
					mlMap.get(i-3).addCand(fields[0],rank);
				}
				float rank2=Float.parseFloat(fields[i+5]);
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
		*/
		
	}//end of RanklistInput
	
	/*public void createMl(){
		mlMap.get(1).combineRank(mlMap.get(0));
		mlMap.get(4).combineRank(mlMap.get(0));
		mlMap.get(5).combineRank(mlMap.get(0));
		mlMap.get(6).combineRank(mlMap.get(2));
		mlMap.get(7).combineRank(mlMap.get(3));

	}*/
	
	
	public void inputCandidate() {
		try{
		Scanner scan=new Scanner(new File(filepath+"/choices.csv"));
		scan.nextLine();
		String s;
			while(scan.hasNextLine()){
				s=scan.nextLine();
				/** taking each candidates input */
				String[] field=s.split(",");
				Candidate c;
				c=new Candidate(field[0],field[1],field[2],candidateList.size(),filepath);
				c.addPreference(field[3]);			
				 candidateList.addElement(c);
				//currCand.add(candidateList.size()-1);
				//c.displayCand();
			
			}
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
	}
	
public void dsAlloc(Candidate[] dsList){          //allots DS candidates seats
	for(int i=0;i<dsList.length;i++){
		Candidate c=dsList[i];
		Vector<String> pref=c.getPrefList();
		for(int j=0;j<pref.size();j++){
			c.setPreference(j);
			String s=c.getCurrProg();
			if(vpMap.get(s.substring(0,1)).getQuota()>vpMap.get(s.substring(0,1)).getCurrStr()){
				if(c.getRank()[0]>0){
					c.allotedseat();
					vpMap.get(s.substring(0,1)).increaseCurrStr();
					vpMap.get(s.substring(0,1)).setLastRank(c.getRank()[0]);
					break;
				}
				else continue;
			}
			else if(vpMap.get(s.substring(0,1)).getQuota()==vpMap.get(s.substring(0,1)).getCurrStr()){
				if(vpMap.get(s.substring(0,1)).getLastRank()==c.getRank()[0]){
					if(c.getRank()[0]>0){
						c.allotedseat();
						vpMap.get(s.substring(0,1)).increaseQuota();
						vpMap.get(s.substring(0,1)).increaseCurrStr();
						vpMap.get(s.substring(0,1)).setLastRank(c.getRank()[0]);
						break;
					}
					else continue;
				}
				else continue;
			}
			else continue;
			
		}
		if(!c.alloted()) c.resetPrefList();
		
	}
	
}
public void normalAlloc(){         //allots all candidates seats, including ones who didn't get a seat after DS allocationns are over
	for(int i=0;i<meritList.getMeritList().length;i++){
		Candidate c=meritList.getMeritList()[i];
		if(c.alloted()|| c.getNat()) continue;
		else{
			Vector<String> pref=c.getPrefList();
			for(int j=0;j<pref.size();j++){
				c.setPreference(j);
				String s=c.getCurrProg();
				int cat=Integer.parseInt(s.substring(s.length()-1,s.length()));
				if(vpMap.get(s).getQuota()>vpMap.get(s).getCurrStr() && c.getRank()[cat]>0){
						c.allotedseat();
						vpMap.get(s).increaseCurrStr();
						vpMap.get(s).setLastRank(c.getRank()[cat]);
						
						break;
					}
				else if(vpMap.get(s).getQuota()==vpMap.get(s).getCurrStr()){
					if(vpMap.get(s).getLastRank()==c.getRank()[cat]){
						c.allotedseat();
						vpMap.get(s).increaseQuota();
						vpMap.get(s).increaseCurrStr();
						break;
						
					}
				}
			}
		}
		if(!c.alloted()) c.resetPrefList();
			
		}
}

public void foreignAlloc(){
	for(int i=0;i<meritList.getForeignList().length;i++){
		Candidate c=meritList.getForeignList()[i];
		Vector<String> pref=c.getPrefList();
		for(int j=0;j<pref.size();j++){
			c.setPreference(j);
			String s=c.getCurrProg();
			if(vpMap.get(s).getQuota()>vpMap.get(s).getCurrStr() && c.getRank()[0]>0){
				c.allotedseat();
				break;
			}
			else if(vpMap.get(s).getQuota()==vpMap.get(s).getCurrStr()){
				if(vpMap.get(s).getLastRank()>=c.getRank()[0]){
					c.allotedseat();
					break;
				}
			}
		}
	}
}			
/**
 * Implements seat deservation after phase 1
 * @return boolean It is true if de-reservation occurs else if none of the seats are dereserved it returns false
 */
public boolean seatdereservation(){      
	boolean deresHappened=false;
	for(String key:vpMap.keySet()){
		String cat=key.substring(key.length()-1,key.length());
		//System.out.println(cat);
		if(cat.equals("0") || cat.equals("2") || cat.equals("3")) continue;
		else if(cat.equals("1") || cat.equals("4") || cat.equals("5")){
			VirtualProgramme p=vpMap.get(key);
			if(p.getQuota()>p.getCurrStr()){
				deresHappened=true;
				int deresSeat=p.getQuota()-p.getCurrStr();
				String prog=p.getProg().substring(0,p.getProg().length()-1);
				String geprog=prog+"0";
				vpMap.get(geprog).increaseQuota(deresSeat);    //not decreasing seats left unallocated in OBC cat vp as they woudn't be taken by anyone
				continue;
			}
			else continue;
		}
		else if(cat.equals("6")){
			VirtualProgramme p=vpMap.get(key);
			if(p.getQuota()>p.getCurrStr()){
				deresHappened=true;
				int deresSeat=p.getQuota()-p.getCurrStr();
				String prog=p.getProg().substring(0,p.getProg().length()-1);
				String scprog=prog+"2";
				vpMap.get(scprog).increaseQuota(deresSeat);
				continue;
			}
			else continue;
		}
		else if(cat.equals("7")){
			VirtualProgramme p=vpMap.get(key);
			if(p.getQuota()>p.getCurrStr()){
				deresHappened=true;
				int deresSeat=p.getQuota()-p.getCurrStr();
				String prog=p.getProg().substring(0,p.getProg().length()-1);
				String scprog=prog+"3";
				vpMap.get(scprog).increaseQuota(deresSeat);
				continue;
			}
			else continue;
		}
	}
	return deresHappened;
}
	

/*public boolean candidateApply(){
	boolean prefComplete=true;
	Integer index;
	if(currCand.size()==0){return false;}
	for (Iterator<Integer> it = currCand.iterator(); it.hasNext(); ){
        index = it.next(); 
		Candidate c=candidateList.get(index); 
	    if(c.getNextPref() !=-1){
	    	prefComplete=false;
	    	if((c.getNextProg()).endsWith("ds")){                    
	    		String s=(c.getNextProg()).substring(0,1);
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

	    }
	 
	}
	currCand.clear();
	if(prefComplete){return false;}
	else {return true;}
		
}*/

/*public void updateCurrProg(LinkedList<Integer> rej){
	ListIterator<Integer> it=rej.listIterator();
	while(it.hasNext()){
		int index=it.next();
		candidateList.get(index).setCurrProg(-1);
	}
		
}*/


/*public void createWaitlist(){
	Iterator<VirtualProgramme> it=vpMap.values().iterator();
	while(it.hasNext()){
		VirtualProgramme vp=(VirtualProgramme) it.next();
		LinkedList<Integer> rejectedList=new LinkedList<Integer>(vp.filterApp(mlMap.get(vp.getCategory())));
		updateCurrProg(rejectedList);
		currCand.addAll(rejectedList);
	}
}*/

/*public void displayVpmap(){
	System.out.println("vpmap ");
	for(Map.Entry<String,VirtualProgramme> m:vpMap.entrySet()){
		System.out.print(m.getKey()+" ");
		m.getValue().displayQuota();m.getValue().displayCurrStr();
	}
}*/

/*public void displayCurrCand(){
	System.out.println("Current cand ");
	ListIterator<Integer> it=currCand.listIterator();
	while(it.hasNext()){
		int n=it.next();
		System.out.println(n+" "+candidateList.get(n).getId());
		//candidateList.get(n).displayCand();
	}
}*/




	public void output(){

		try{
			PrintStream out = new PrintStream(new FileOutputStream("outputMeritOrderAdmission.csv"));
			System.setOut(out);
			for(int i=0;i<candidateList.size();i++){
				if(candidateList.get(i).alloted()){
					String prog=candidateList.get(i).getCurrProg();
					prog=prog.substring(0,prog.length()-2);
					out.print(candidateList.get(i).getId());out.print(",");out.println(prog);
				}
				else {out.print(candidateList.get(i).getId());out.println(",-1");}
				}
		}
		catch(IOException e1){
			
		}
	}
	/*System.out.println("output");
	for(int i=0;i<candidateList.size();i++){
		if(candidateList.get(i).getCurrProg().equals("-1")){ 
			System.out.println(candidateList.get(i).getId()+" -1");
		}
		else{ System.out.println(candidateList.get(i).getId()+" "+candidateList.get(i).getCurrProg().substring(0,2));} // @PALAK substring index
	}*/
	/*try{
	PrintStream console = System.out;

	File file = new File("./output.csv");          //change later
	FileOutputStream fos = new FileOutputStream(file);
	PrintStream ps = new PrintStream(fos);
	System.setOut(ps);
	System.out.println("CandidateUniqueId,ProgrammeID");
	for(int i=0;i<candidateList.size();i++){
		System.out.print(candidateList.get(i).getId());System.out.print(",");System.out.println(candidateList.get(i).getCurrProg());
	}
}*/
//catch(FileNotFoundException e){
	//e.printStackTrace();
//}

	



	public void allocAlgo(){
		//System.out.println("Enter the path to the current directory")
		dsAlloc(meritList.getDSList());
		normalAlloc();
		if(seatdereservation()) normalAlloc();
		foreignAlloc();
		output();
	}
	//////////////////////////////tmpfunchere////////////////////////////////
	
}