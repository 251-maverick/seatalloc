import java.util.Vector;


public class Candidate {

	/**
	 * @param args
	 */
	private String id;
	int category;
	private boolean nat,pd,ds;   /** nat 0 implies indian, 1 implies foreign */
	private Vector<String> preference;
	private int nextPref;
	private String currProg;
	/** pointer to next candidate */
	private Candidate next;			//protected
	private Candidate prv;
	
	
	public Candidate(String id,String cat,String pd){
		this.id=id;
		ds=false;
		if(cat=="F"){this.nat=true;}
		else this.nat=false;
		
		if(pd=="Y"){this.pd=true;}
		else this.pd=false;
		
		if(cat=="GE"){category=0;}
		else if(cat="OBC"){category=1;}
		else if(cat="SC"){category=2;}
		else if(cat="ST"){category=3;}
		else if(cat="DS"){category=0;ds=true;}
		else if(cat="DS-OBC"){category=1;ds=true;}
		else if(cat="DS-SC"){category=2;ds=true;}
		else if(cat="DS-ST"){category=3;ds=true;}
		if(this.pd==true){this.category=this.category+4;}
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

	