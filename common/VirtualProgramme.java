package common;
import GS.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
/**This class stores data related to a virtual programme(vp)
 * category is a value between 0 to 7 (inclusive) consistent with what was used in candidate class
 * currStr is the number of candidates wait-listed at any stage in the virtual programme and quota is the upper limit to it
 * lastrank is the rank of the latest candidate wait-listed to the virtual programme and if no candidate is wait-listed its value is -1
 * waitlist is the list of candidates waitlisted for this virtual programme (for galeshapley) 
 * applist is the list of candidates applied for this virtual programme in any iteration (for galeshapley)
 * @author maverick
 *
 */
public class VirtualProgramme {

	private int category;	//
	private String prog;
	private int quota;
	private int currStr;    //currentStrength
	private float lastrank; //stores rank of last candidate wait listed at any stage
	private List<Candidate> waitList;
	private List<Candidate> appList;
	private List<Candidate> foreignCand;
	private ListIterator<Candidate> candQuota; //
	private Candidate lc;
	
	public VirtualProgramme(int cat, int quota, String prog){
		this.setCategory(cat);
		waitList = new LinkedList<Candidate>() ;
		appList = new LinkedList<Candidate> ();
		this.foreignCand = new LinkedList<Candidate> ();
		candQuota=waitList.listIterator();
		this.quota=quota;
		currStr=0;
		lastrank=-1;
		this.prog=prog;
	}
	
	public VirtualProgramme(){ //constructor for vp for DS candidates
		this.category=0; 
		this.quota=2;
		currStr=0;
		lastrank=-1;
		waitList = new LinkedList<Candidate> ();
		appList = new LinkedList<Candidate> ();
		foreignCand = new LinkedList<Candidate> ();
		
		
	}
	/**Function to increase quota of vp
	 * 
	 * @param i value by which quota is to be increased
	 */
	public void increaseQuota(int i){
		quota+=i;
	}
	/**
	 * Function to receive application of a candidate to this virtual program 
	 * @param candidate Candidate to be inserted to the application list
	 */
	public void receiveApp (Candidate candidate){
		if(candidate.getNat()==true){
			foreignCand.add(candidate);
		}
		else{
			appList.add(candidate);
		}
		
	}
	/**
	 * This function inserts candidate to the waitlist such that waitlist remains in a sorted order
	 * @param element Candidate to be inserted
	 * @param ml mlMap to compare ranks
	 * @return True if successfully added else false
	 */
	public boolean orderedAdd(Candidate element,GS.MeritList ml) {      
	     //LinkedList<Integer> rejectedList=new LinkedList<Integer>();    
		 if(!ml.check(element)){
			//System.out.println(element.getId()+" not in meritlist "+prog );
			 return false;
			 }
		 //use sort built in
		 ListIterator<Candidate> itr = waitList.listIterator(); //try starting frm reverse decreasingIterator
	        int count=0;
	        while(count<quota) {			//check limits
	        	//if(itr==candQuota) change candQuota
	            if (itr.hasNext() == false) {
	                itr.add(element);
	                //System.out.println("Adding "+element.getId());
	                //change candQuota
	                return true;
	            }
	            Candidate elementInList = itr.next();
	            /**element in list is less or equal to element to be added, insert it before elmInList */
	            if (!ml.compareRank(elementInList,element)) {	//imp to have not
	                itr.previous();
	                itr.add(element);
	                //System.out.println("Adding "+element.getId());
	                //change candQuota
	                return true;
	            }
	            count++;
	        }
	        //rejectedList.add(element);
	        return false;
	    }
	/**
	 * This function removes candidates found in-eligible for this vp 
	 * @param ml Meritlist to compare ranks
	 * @return List of indices of rejected candidates from a this virtual program
	 */
	 public LinkedList<Integer> removeExtra(MeritList ml){
		 /** last but one element strictly greater than last ,for supernumary */
	        /** number by which waitlist exceeds quota  */
		 LinkedList<Integer> rejectedList=new LinkedList<Integer>();  
	     int extra=waitList.size()-quota;
         if(extra>0){ 
        	 Candidate last=((LinkedList<Candidate>) waitList).getLast();
        	 // if(candQuota==""){candQuota=waitList.get(quota-1);} 
        	 //else { 
        	 //String id=candQuota.next().getId();
        		 /**last ranks are not equal,then remove last */				//mlMap hashmap of ml, shud pass d argumetn instead
        	//Candidate c=candQuota.next();	
        	 while((ml.compareRank(lc,last) )){
 	                //System.out.println("Removing "+last.getId());
 	                rejectedList.add(last.getIndex());
        			((LinkedList<Candidate>) waitList).removeLast();			//removes last candidate frm list
        			 last=((LinkedList<Candidate>) waitList).getLast();
        		 }
        	 //c=candQuota.previous();
        	 }
         	
         
         return rejectedList;
	 }
	 /**
	  * This function gives the index of all those foreign candidates rejected by thi vp
	  * @param ml Meritlist for comparing ranks
	  * @return List of indices of rejected foreign candidates
	  */
	 public LinkedList<Integer> foreign(MeritList ml){
		 LinkedList<Integer> rejectedList=new LinkedList<Integer>();  
		 ListIterator<Candidate> itr=foreignCand.listIterator();
			while(itr.hasNext()){	//change to for loop
				Candidate curr=itr.next();
				Candidate last=((LinkedList<Candidate>) waitList).getLast();
				if((last.category!=0) ||(ml.compareRank(last,curr))){			//true if last is not gen or  if curr is  better than or = last
					//System.out.println("removing f: "+curr.getId());
					itr.remove();				
					rejectedList.add(curr.getIndex());							
				}
			}
			return rejectedList;
	 }
	 /** @param meritlist of corresponding category */
		public LinkedList<Integer> filterApp(MeritList ml){
			LinkedList<Integer> rejectedList=new LinkedList<Integer>();  
			ListIterator<Candidate> itr=appList.listIterator();
			//displayAppList();
			while(itr.hasNext()){
				Candidate element=itr.next();
				/** if not added to waitlist add candidate to rejected list*/
				if(orderedAdd(element,ml)){
					if(waitList.size()>=quota){
//						if(candQuota.hasPrevious()){candQuota.previous();}
//						else { candQuota=waitList.listIterator(quota-1);}
						lc=waitList.get(quota-1);			//change dis, it will take too long
					}
				}
				else{
					rejectedList.add(element.getIndex());
				}
			}
			rejectedList.addAll(removeExtra(ml));
			
			//foreign candidates create a separate fn
			if(quota==0){
				 ListIterator<Candidate> it=foreignCand.listIterator();
					while(it.hasNext()){	//change to for loop
						Candidate curr=it.next();
						//itr.remove();				
						rejectedList.add(curr.getIndex());							
					}
					foreignCand.clear();
			}
			else if(waitList.size()>=quota ){rejectedList.addAll(foreign(ml));}
					
			/**make applist empty */
			appList.clear();
			//displayList(rejectedList);
			//displayWaitList();
			return rejectedList;
		}
	public String getProg(){
		return prog;
	}
	public float getLastRank(){
		return lastrank;
	}
	public void setLastRank(float rank){
		lastrank=rank;
	}
	public int getQuota(){
		return quota;
	}
	public void increaseQuota(){
		quota++;
	}
	public int getCurrStr(){
		return currStr;
	}
	public void increaseCurrStr(){
		currStr++;
	}
	
	/*public void displayList(LinkedList<Integer> list){
		ListIterator<Integer> it=list.listIterator();
		int i=1;
		while(it.hasNext()){
			System.out.println(i +" "+it.next());
			i++;
		}
	}*/
	
	 
	 /**supernumary quota */
	 /*public LinkedList<Integer> removeExtra(MeritList ml){
		 /** last but one element strictly greater than last ,for supernumary */
	        /** number by which waitlist exceeds quota  */
		 

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public void displayWaitList(){
		System.out.println(prog+ " waitlist");
		ListIterator<Candidate> it=waitList.listIterator();
		int i=1;
		while(it.hasNext()){
			System.out.println(i +" "+it.next().getId());
			i++;
		}
	}
	
	public void displayAppList(){
		System.out.println(prog+" applist");
		ListIterator<Candidate> it=appList.listIterator();
		int i=1;
		while(it.hasNext()){
			System.out.println(i +" "+it.next().getId());
			i++;
		}
	}
	
/////////////////tmp///////////////////
public void displayQuota(){
		System.out.println(quota);
}
public void displayCurrStr(){
	System.out.println(currStr);
}
public void displayLastRank(){
	System.out.println(lastrank);
}
}
//hashmap for v.p.
