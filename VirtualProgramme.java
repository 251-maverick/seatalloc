import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class VirtualProgramme {

	private int category;	//
	private boolean pd;
	private int quota;
	private List<Candidate> waitList;
	private List<Candidate> appList;
	private List<Candidate> foreignCand;
	//private List<Integer> dsCand;  //I think we don't need this
	/** candidate whose index is equalto quota */
	private ListIterator<Candidate> candQuota; //
	
	public VirtualProgramme(int cat, boolean pd, int quota){
		this.setCategory(cat);
		this.pd=pd;
		this.quota=quota;
		waitList = new LinkedList<Candidate>() ;
		appList = new LinkedList<Candidate> ();
		this.foreignCand = new LinkedList<Candidate> ();
		candQuota=waitList.listIterator();
		//this.dsCand = new LinkedList<Candidate> ();
	}
	
	public VirtualProgramme(){
		this.category=8;
		this.quota=2;
		waitList = new LinkedList<String> ();
		appList = new LinkedList<String> ();
	}
	
	public void receiveApp (Candidate candidate){
		if(candidate.getNat()==true){
			foreignCand.add(candidate);
		}
		else{
			appList.add(candidate);
		}
		
	}
	
	 public boolean orderedAdd(Candidate element,MeritList ml) {      
	     //LinkedList<Integer> rejectedList=new LinkedList<Integer>();    
		 if(!ml.check(element))return false;
		 
		 ListIterator<Candidate> itr = waitList.listIterator(); //try starting frm reverse decreasingIterator
	        int count=0;
	        while(count<quota) {			//check limits
	        	//if(itr==candQuota) change candQuota
	            if (itr.hasNext() == false) {
	                waitList.add(element);
	                //System.out.println("Adding "+element.id);
	                //change candQuota
	                return true;
	            }
	            Candidate elementInList = itr.next();
	            /**element in list is less or equal to element to be added, insert it before elmInList */
	            if (!ml.compareRank(elementInList.getId(),element.getId())) {
	                itr.previous();
	                waitList.add(element);
	                //System.out.println("Adding "+element.id);
	                //change candQuota
	                return true;
	            }
	            count++;
	        }
	        //rejectedList.add(element);
	        return false;
	    }
	 
	 /**supernumary quota */
	 public LinkedList<Integer> removeExtra(MeritList ml){
		 /** last but one element strictly greater than last ,for supernumary */
	        /** number by which waitlist exceeds quota  */
		 LinkedList<Integer> rejectedList=new LinkedList<Integer>();  
	     int extra=waitList.size()-quota;
         if(extra>0){ 
        	 Candidate last=((LinkedList<Candidate>) waitList).getLast();
        	 // if(candQuota==""){candQuota=waitList.get(quota-1);} 
        	 //else { 
        	 String id=candQuota.next().getId();
        		 /**last ranks are not equal,then remove last */				//mlMap hashmap of ml, shud pass d argumetn instead
        		 while((ml.compareRank(id.,last.getId()) )){
 	                //System.out.println("Removing "+last.getId());
 	                rejectedList.add(last.getIndex());
        			last=((LinkedList<Candidate>) waitList).removeLast();			//removes last candidate frm list
        			 //last=((LinkedList<Candidate>) waitList).getLast();
        		 }
        	 }
         	
         
         return rejectedList;
	 }
	 
//	 public void updateCurrProg(Vector<Candidate> candidateList){
//		ListIterator<Integer> it=waitList.listIterator();
//		while(it.hasNext()){
//			int index=it.next();
//			candidateList[it].currProg=
//		}

	 
	/** @param meritlist of corresponding category */
	public LinkedList<Integer> filterApp(MeritList ml){
		LinkedList<Integer> rejectedList=new LinkedList<Integer>();  
		ListIterator<Candidate> itr=appList.listIterator();
		while(itr.hasNext()){
			Candidate element=itr.next();
			/** if not added to waitlist add candidate to rejected list*/
			if(orderedAdd(element,ml)){
				if(waitList.size()>=quota){
					if(candQuota.hasPrevious()){candQuota.previous();}
					else { candQuota=waitList.listIterator(quota-1);}
				}
			}
			else{
				rejectedList.add(element.getIndex());
			}
		}
		rejectedList.addAll(removeExtra(ml));
		
		//foreign candidates?? @PALAK
		/** update currProg */
		/**make applist empty */
		appList.clear();
		return rejectedList;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
//hashmap for v.p.