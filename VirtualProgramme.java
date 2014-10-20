package seatallocation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class VirtualProgramme {

	private int category;
	private boolean pd;
	private int quota;
	private List<String> waitList;
	private List<String> appList;
	private List<Integer> foreignCand;
	private List<Integer> dsCand;                  //no need remove it
	/** candidate whose index is equalto quota */
	private String candQuota; //
	
	public VirtualProgramme(int cat,int quota){
		this.category=cat;
		this.quota=quota;
		waitList = new LinkedList<String> ();
		appList = new LinkedList<String> ();
		this.foreignCand = new LinkedList<Integer> ();
		//this.dsCand = new LinkedList<Integer> ();
	}
	public VirtualProgramme(){
		this.category=8;
		this.quota=2;
		waitList = new LinkedList<String> ();
		appList = new LinkedList<String> ();
	}
	
	public void receiveApp (Candidate candidate){
		//sort
		
	}
	
	
	 public void orderedAdd(String element,MeritList ml) {      
	        ListIterator<String> itr = waitList.listIterator(); //try starting frm reverse decreasingIterator
	        int count=0;
	        while(count<quota) {			//check limits
	        	//if(itr==candQuota) change candQuota
	            if (itr.hasNext() == false) {
	                waitList.add(element);
	                System.out.println("Adding "+element);
	                //change candQuota
	                return;
	            }
	            String elementInList = itr.next();
	            /**element in list is less or equal to element to be added, insert it before elmInList */
	            if (!ml.compareRank(elementInList,element)) {
	                itr.previous();
	                waitList.add(element);
	                System.out.println("Adding "+element);
	                //change candQuota
	                return;
	            }
	            count++;
	        }
	        return;
	    }
	 /**supernumary quota */
	 public void removeExtra(MeritList ml){
		 /** last but one element strictly greater than last ,for supernumary */
	        /** number by which waitlist exceeds quota  */
	        int extra=waitList.size()-quota;
         if(extra>0){ 
        	 String last=((LinkedList<String>) waitList).getLast();
        	 if(candQuota==""){candQuota=waitList.get(quota-1);} 
        	 else { 
        		 /**last ranks are not equal,then remove last */				//mlMap hashmap of ml, shud pass d argumetn instead
        		 while((ml.compareRank(candQuota,last) )){
 	                System.out.println("Removing "+last);
        			 last=((LinkedList<String>) waitList).removeLast();
        			 //last=((LinkedList<String>) waitList).getLast();
        		 }
        	 }
         	
         } 
	 }
	
	public void filterApp(MeritList ml){
		ListIterator<String> itr=appList.listIterator();
		while(itr.hasNext()){
			String element=itr.next();
			orderedAdd(element,ml);
		}
		removeExtra(ml);
		//for rest currProg="-1"
		//foreign candidates?? @PALAK Done @CHARMI
		
		ListIterator<String> Foreignitr=this.foreignCand.listIterator();
		while(Foreignitr.hasNext()){
			Candidate current=Foreignitr.next();
			if(ml[current.getId]>ml[waitList.get(waitList.size()-1)]){
				rejectedList.add(current);
				this.foreignCand.remove(current);
			}
	}

	public int getCategory() {
		// TODO Auto-generated method stub
		return category;
	}

}
//hashmap for v.p.

