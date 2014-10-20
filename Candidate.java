import java.util.Vector;


public class Candidate {

	/**
	 * @param args
	 */
	private String id,category;
	private boolean nat,pd,ds;   /** nat 0 implies indian, 1 implies foreign */
	private Vector<String> preference;
	private int nextPref;
	private String currProg;
	/** pointer to next candidate */
	private Candidate next;			//protected
	private Candidate prv;
	
	
	public Candidate(String id,String cat,String pd){
		this.id=id;
		this.category=cat;
		if(cat=="F"){this.nat=true;}
		if(cat=="DS"){this.ds=true;}
		
		if(pd=="Y"){this.pd=true;}
		else this.pd=false;
		nextPref=0;
		setCurrProg("NONE");
		preference=new Vector<String> (0);
	}
	
	public void addPreference(String prefList){
		//currPref=0;			//complete
	}
		
	

	public String getCategory() {
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
	
	/** sets next candidate in original list ,used to display later*/
	public void setNext(Candidate next){ this.next=next;}

	public String getCurrProg() {
		return currProg;
	}

	public void setCurrProg(String currProg) {
		this.currProg = currProg;
	}

	public int getNextPref() {
		return nextPref;
	}

	public void setNextPref(int i) {
		if(i<preference.size()){nextPref=i;}
		else {i=-1;}
	}
	
	

	

}

	