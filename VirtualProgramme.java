import java.util.*;
public class VirtualProgramme {

	private int category;
	private int quota;
	private LinkedList<Integer> waitList;
	private LinkedList<String> appList;
	private LinkedList<Integer> foreignCand;
	private LinkedList<Integer> dsCand;
	
	public VirtualProgramme(int cat,int quota){
		this.category=cat;
		this.quota=quota;
		waitList = new LinkedList();
		appList = new LinkedList();
		this.foreignCand = new LinkedList();
		this.dsCand = new LinkedList();
	}
	
	//public void receiveApp (Candidate candidate){
		//sort
		
	//}
	
	/*public void filterApp(){
		Collections.sort(applist, Comparator<String>)
		 ListIterator<String> pointer = appList.listIterator();
	        while (pointer.hasNext()) {
	        	//merge
	        }

	}*/
	

};
//hashmap for v.p.
