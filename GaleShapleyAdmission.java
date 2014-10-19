import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

public class GaleShapleyAdmission{
	//put datamembers
	private Vector<VirtualProgramme> program;
	private LinkedList<Candidate> candidateList=new LinkedList<Candidate>();
	private HashMap<String,VirtualProgramme> vpMap=new HashMap<String,VirtualProgramme>();
	
	public GaleShapleyAdmission() throws FileNotFoundException{ 
		Scanner scan=new Scanner(new File("./programs.csv"));
		boolean firstline=true;
		String s;
		while(scan.hasNextLine()){
			if(firstline) continue;
			s=scan.nextLine();
			String[] fields=s.split(",");
			for(int i=3;i<11;i++){
			VirtualProgramme p(i-3,fields[i]);
			program.addElement(p);
			}
			firstline=false;
			}
	}
	
	public void inputCandidate() throws FileNotFoundException{
		
		Scanner scan=new Scanner(new File("./choices.csv"));
		scan.nextLine();
		String s;
		while(scan.hasNextLine()){
			s=scan.nextLine();
			
			/** taking each candidates input */
			String[] field=s.split(",");
			for(int i=0;i<3;i++){
				Candidate c=new Candidate(field[0],field[1],field[2]);
				c.addPreference(field[3]);			//put nxt and prv pointers
				candidateList.add(c);				//set initial next pref also in this
			}
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
//		Iterator it= vpMap.entrySet().iterator();
//		while(it.hasNext()){
//			//Map.Entry<String,VirtualProgramme> vp = it.next();
//			
//		}
		for (Map.Entry<String, VirtualProgramme> entry : vpMap.entrySet()) {
			
		}
	}
	
	
	
	
}

		
