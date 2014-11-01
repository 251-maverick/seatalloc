package common;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * This class stores data of a candidate who is participating in seat-allocation.
 * ID is candidate's roll number, category is an integer ranging from 0 to 7 
 * nat is true if candidate is 'foreign' 
 * preference stores the expanded preference list of candidate
 * nextPref is the index of next programme to which candidate will apply if rejected from currently applied programme
 * currProg is the programme-code of current program to which candidate applied
 * alloted is true if candidate is wait-listed for some programme
 * rank is an array storing different category ranks of the candidate
 * index stores the index of candidate in the candidateList vector
 * @author maverick
 *
 */
public class Candidate{
	private String id;                    
	int category;
	private boolean nat,pd,ds;       
	private Vector<String> preference;
	private int nextPref;
	private String currProg;
	private Integer index;
	private boolean alloted;   
	private float[] rank;
	/**
	 * Constructor for Candidate class
	 * @param id Roll number of candidate
	 * @param cat "GE" "OBC" and so on..
	 * @param pd  "Y" or "N"
	 * @param i    index of candidate in candidateList vector
	 * @param filepath filepath given by user as input
	 * @throws FileNotFoundException  If ranklist.csv is not found then this exception is thrown
	 */
	public Candidate(String id,String cat,String pd,Integer i,String filepath, int x) throws FileNotFoundException{ 
		this.setId(id);
		ds=false;
		rank=new float[8];
		if(cat.equals("F")){this.nat=true; category=0;}      //category of foreign is 0
		else this.nat=false;
		if(pd.equals("Y")){this.pd=true;}
		else this.pd=false;
		if(cat.equals("GE")){category=0;}
		else if(cat.equals("OBC")){category=1;}
		else if(cat.equals("SC")){category=2;}
		else if(cat.equals("ST")){category=3;}
//			Scanner scan=new Scanner(new File(filepath+"/ranklist.csv"));        
//			scan.nextLine();
//			String s;
//			boolean whilebreak;
//			while(scan.hasNext()){
//				whilebreak=false;
//				s=scan.nextLine();
//				String[] field=s.split(",");
//				if(field[0].equals(id)){
//					whilebreak=true;
//					if(cat.equals("DS")){
//					ds=true;
//					for(int j=11;j>2;j--){                 //assigning category to DS candidates by reading ranklist.csv
//						if(j==7) continue;
//						if(!field[j].equals("0")){
//							if(j>7){ category=j-4;break;}
//							else{ category=j-3;break;}
//						}
//					}
//				
//				}
//					for(int j=0;j<4;j++){                     //storing rank of candidate to rank array
//						float x=Float.parseFloat(field[j+3]);
//						rank[j]=x;
//					}
//					for(int k=4;k<8;k++){
//						float y=Float.parseFloat(field[k+4]);
//						rank[k]=y;
//					}
//						
//					}
//				if(whilebreak) break;
//			}
		
		if(cat.equals("DS")){
			ds=true;
			this.category=0;
		}
	
	    if(this.pd==true && (!ds)){this.category=this.category+4;}
		nextPref=0;
		setCurrProg(-1);
		preference=new Vector<String> (0);
		this.setIndex(i);
		alloted=false;
	}
	
	public Candidate(String id,String cat,String pd,Integer i,String filepath) throws FileNotFoundException{ 
		this.setId(id);
		ds=false;
		rank=new float[8];
		if(cat.equals("F")){this.nat=true; category=0;}      //category of foreign is 0
		else this.nat=false;
		if(pd.equals("Y")){this.pd=true;}
		else this.pd=false;
		if(cat.equals("GE")){category=0;}
		else if(cat.equals("OBC")){category=1;}
		else if(cat.equals("SC")){category=2;}
		else if(cat.equals("ST")){category=3;}
			Scanner scan=new Scanner(new File(filepath+"/ranklist.csv"));        
			scan.nextLine();
			String s;
			boolean whilebreak;
			while(scan.hasNext()){
				whilebreak=false;
				s=scan.nextLine();
				String[] field=s.split(",");
				if(field[0].equals(id)){
					whilebreak=true;
					if(cat.equals("DS")){
					ds=true;
					for(int j=11;j>2;j--){                 //assigning category to DS candidates by reading ranklist.csv
						if(j==7) continue;
						if(!field[j].equals("0")){
							if(j>7){ category=j-4;break;}
							else{ category=j-3;break;}
						}
					}
				
				}
					for(int j=0;j<4;j++){                     //storing rank of candidate to rank array
						float x=Float.parseFloat(field[j+3]);
						rank[j]=x;
					}
					for(int k=4;k<8;k++){
						float y=Float.parseFloat(field[k+4]);
						rank[k]=y;
					}
						
					}
				if(whilebreak) break;
			}
	
	    if(this.pd==true && (!ds)){this.category=this.category+4;}
		nextPref=0;
		setCurrProg(-1);
		preference=new Vector<String> (0);
		this.setIndex(i);
		alloted=false;
	}
	
	/**
	 * This function creates preference list of a candidate for gale shapley admission
	 * @param prefList
	 */
	public void addPreference1(String prefList){                    //GS function to create a preference list for the candidate
		String pref;
		if(prefList.indexOf('_')==-1) pref=prefList;               
		else pref=prefList.substring(0,prefList.indexOf('_'));
		int prev=-1,k=-1,last;
		last=prefList.lastIndexOf('_');
		do{
			prev=k;
			k=prefList.indexOf('_',k+1);
			if(k!=-1) pref=prefList.substring(prev+1,k);
			else pref=prefList.substring(last+1);
			
			if(ds){ 
			preference.addElement(pref+"-0");	//if pref is B4110 then B4110-0 is added to preference list
			preference.addElement(pref+"-1");
			preference.addElement(pref+"-2");	//if pref is B4110 then B4110-0 is added to preference list
			preference.addElement(pref+"-3");	//if pref is B4110 then B4110-0 is added to preference list
												//0..7 represent categories as given in table of lab10 statement..top to down
			preference.addElement(pref+"-4");
			preference.addElement(pref+"-5");
			preference.addElement(pref+"-6");	
			preference.addElement(pref+"-7");	
			}
			else if(category==0){
//				if(ds){ preference.addElement(pref+"-ds");}
				preference.addElement(pref+"-0");	//if pref is B4110 then B4110-0 is added to preference list
				preference.addElement(pref+"-1");
				
													//0..7 represent categories as given in table of lab10 statement..top to down
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
			}
			else if(category==1){
//				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
			}
			else if(category==2){
				//if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-2");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
				preference.addElement(pref+"-6");
			}
			else if(category==3){
			//	if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-3");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
				preference.addElement(pref+"-7");
			}
			else if(category==4){
				//if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
			else if(category==5){
				//if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
			else if(category==6){
				//if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-2");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-6");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
			else if(category==7){
				//if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-3");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-7");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
		} while(k!=-1);
	}

	/** This function creates preference list of a candidate for merit order admission
	 * 
	 * @param prefList It is the string B1_B2 and so on, obtained while reading choices.csv 
	 */
	public void addPreference(String prefList){                
		String pref;
		if(prefList.indexOf('_')==-1) pref=prefList;               
		else pref=prefList.substring(0,prefList.indexOf('_'));
		int prev=-1,k=-1,last;
		last=prefList.lastIndexOf('_');
		do{
			prev=k;
			k=prefList.indexOf('_',k+1);
			if(k!=-1) pref=prefList.substring(prev+1,k);
			else pref=prefList.substring(last+1);
			if(category==0){
				preference.addElement(pref+"-0");           //if pref is B4110 then B4110-0 is added to preference list
			}
			else if(category==1){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-1");
			}
			else if(category==2){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-2");
			}
			else if(category==3){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-3");
			}
			else if(category==4){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-4");
			}
			else if(category==5){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
			}
			else if(category==6){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-2");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-6");
			}
			else if(category==7){
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-3");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-7");
			}
		} while(k!=-1);
	}
	/*public int compareTo(Candidate c,int cat){         //check if it works with 2 arg
		if(this.rank[cat]<c.rank[cat]) return -1;
		if(this.rank[cat]>c.rank[cat]) return 1;
		else return 0;
	}*/
	public Vector<String> getPrefList(){
		return preference;
	}
	public void allotedseat(){
		alloted=true;
	}
	public boolean alloted(){
		return alloted;
	}
	public float[] getRank(){
		return rank;
	}
	/** 
	 * This function resets the preference list of the candidate 
	 */
	public void resetPrefList(){   //function to re-set preferences of a candidate     
		setCurrProg(0);
		setNextPref(1); //CHECK
	}
	public int getCategory() {
		return category;
	}

	public boolean getNat(){
		return nat;
	}
	
	public boolean getPd(){
		return pd;
	}
	
	public boolean getDs(){
		return ds;
	}
	
	public String getCurrProg() {
		return currProg;
	}
	public void setPreference(int i){   //function to set currProg to preference[i] and nextPref to i+1
			setCurrProg(i);
			setNextPref(i+1);
		
	}
	public void setCurrProg(int index) {
		if(index==-1){this.currProg="-1";}
		else{ this.currProg = preference.get(index);}
	}
	
	public String getNextProg(){
		return preference.get(nextPref);
	}

	public int getNextPref() {
		return nextPref;
	}

	public void setNextPref(int i) {
		if(i<preference.size()){nextPref=i;}
		else {nextPref=-1;}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public int getIndex(){
		return this.index;
	}
	
	
	
	
	/*public void displayCand(){
		System.out.println(id+ " cat "+category+ " pd "+pd +" curr "+currProg+ " nextProg "+ getNextProg());
	}*/
	//////////////////////tmp/////////////////////
	/*public void displayPref(){
		System.out.println("preflist: "+id);
		for(int i=0;i<preference.size();i++){
			System.out.print(preference.get(i)+ " ");
		}
		System.out.print('\n');
	}*/
	/*public void displayRank(){
		for(int i=0;i<8;i++) System.out.print(rank[i]+ " ");
	}*/

	

}

	