import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class VirtualProgramme {

	private int category;	//
	private String prog;
	private int quota;
	private List<Candidate> waitList;
	private List<Candidate> appList;
	private List<Candidate> foreignCand;
	//private List<Integer> dsCand;  //I think we don't need this
	/** candidate whose index is equalto quota */
	private ListIterator<Candidate> candQuota; //
	private Candidate lc;
	
	public VirtualProgramme(int cat, int quota, String prog){
		this.setCategory(cat);
		//this.pd=pd;
		this.quota=quota;
		waitList = new LinkedList<Candidate>() ;
		appList = new LinkedList<Candidate> ();
		this.foreignCand = new LinkedList<Candidate> ();
		candQuota=waitList.listIterator();
		//this.dsCand = new LinkedList<Candidate> ();
		this.prog=prog;
	}
	
	public VirtualProgramme(){
		this.category=8;
		this.quota=2;
		waitList = new LinkedList<Candidate> ();
		appList = new LinkedList<Candidate> ();
	}
	
	public void displayList(LinkedList<Integer> list){
		ListIterator<Integer> it=list.listIterator();
		int i=1;
		while(it.hasNext()){
			System.out.println(i +" "+it.next());
			i++;
		}
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
		 if(!ml.check(element)){
			System.out.println(element.getId()+" not in meritlist "+prog );
			 return false;
			 }
		 //use sort built in
		 ListIterator<Candidate> itr = waitList.listIterator(); //try starting frm reverse decreasingIterator
	        int count=0;
	        while(count<quota) {			//check limits
	        	//if(itr==candQuota) change candQuota
	            if (itr.hasNext() == false) {
	                itr.add(element);
	                //System.out.println("Adding "+element.id);
	                //change candQuota
	                return true;
	            }
	            Candidate elementInList = itr.next();
	            /**element in list is less or equal to element to be added, insert it before elmInList */
	            if (!ml.compareRank(elementInList,element)) {	//imp to have not
	                itr.previous();
	                itr.add(element);
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
        	 //String id=candQuota.next().getId();
        		 /**last ranks are not equal,then remove last */				//mlMap hashmap of ml, shud pass d argumetn instead
        	//Candidate c=candQuota.next();	
        	 while((ml.compareRank(lc,last) )){
 	                //System.out.println("Removing "+last.getId());
 	                rejectedList.add(last.getIndex());
        			last=((LinkedList<Candidate>) waitList).removeLast();			//removes last candidate frm list
        			 //last=((LinkedList<Candidate>) waitList).getLast();
        		 }
        	 //c=candQuota.previous();
        	 }
         	
         
         return rejectedList;
	 }
	 
//	 public void updateCurrProg(Vector<Candidate> candidateList){
//		ListIterator<Integer> it=waitList.listIterator();
//		while(it.hasNext()){
//			int index=it.next();
//			candidateList[it].currProg=
//		}

	// public void 
	 
	/** @param meritlist of corresponding category */
	public LinkedList<Integer> filterApp(MeritList ml){
		LinkedList<Integer> rejectedList=new LinkedList<Integer>();  
		ListIterator<Candidate> itr=appList.listIterator();
		displayAppList();
		while(itr.hasNext()){
			Candidate element=itr.next();
			/** if not added to waitlist add candidate to rejected list*/
			if(orderedAdd(element,ml)){
				if(waitList.size()>=quota){
//					if(candQuota.hasPrevious()){candQuota.previous();}
//					else { candQuota=waitList.listIterator(quota-1);}
					lc=waitList.get(quota-1);
				}
			}
			else{
				rejectedList.add(element.getIndex());
			}
		}
		rejectedList.addAll(removeExtra(ml));
		
		//foreign candidates create a separate fn
//				if(waitList.size()<quota){}
//				else {
//					ListIterator<Candidate> forItr=foreignCand.listIterator();
//					while(itr.hasNext()){	//change to for loop
//						Candidate curr=itr.next();
//						Candidate last=waitList.get(waitList.size()-1);
//						if(ml.compareRank(last,curr)){//not sure if this should be true or false @charmi
//							foreignCand.remove(curr);				//if last better than curr
//							rejectedList.add(curr.getIndex());							//
//						}
//					}
//				}
		/**make applist empty */
		appList.clear();
		displayList(rejectedList);
		displayWaitList();
		return rejectedList;
	}

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
	
	

}
//hashmap for v.p.