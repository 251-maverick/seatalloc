import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class VirtualProgramme {

	private String category;
	private boolean pd;
	private int quota;
	private List<String> waitList;
	private List<String> appList;
	private List<String> foreignCand;
	//private List<Integer> dsCand;  //I think we don't need this
	/** candidate whose index is equalto quota */
	private String candQuota; //
	
	public VirtualProgramme(String cat, boolean pd, int quota){
		this.category=cat;
		this.pd=pd;
		this.quota=quota;
		waitList = new LinkedList<String>(0) ;
		appList = new LinkedList<String> (0);
		this.foreignCand = new LinkedList<Integer> (0);
		this.dsCand = new LinkedList<Integer> (0);
	}
	
	public void receiveApp (Candidate candidate){
		if(if candidate.nat==true){
			foreignCand.add(candidate.id);
		}
		else{
			appList.add(candidate.id);
		}
		
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
		//foreign candidates?? @PALAK
	}

}
//hashmap for v.p.