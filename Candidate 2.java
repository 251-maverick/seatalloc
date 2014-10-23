
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
	private Integer index;
	
	public Candidate(String id,String cat,String pd,Integer i){
		this.setId(id);
		ds=false;
		if(cat.equals("F")){this.nat=true;}
		else {this.nat=false;}
		if(pd.equals("Y")){this.pd=true;}
		else {this.pd=false;}
		
		if(cat.equals("GE")){category=0;}
		else if(cat.equals("OBC")){category=1;}
		else if(cat.equals("SC")){category=2;}
		else if(cat.equals("ST")){category=3;}
		else if(cat.equals("DS")){category=0;ds=true;}
		else if(cat.equals("DS-OBC")){category=1;ds=true;}
		else if(cat.equals("DS-SC")){category=2;ds=true;}
		else if(cat.equals("DS-ST")){category=3;ds=true;}
		
		if(this.pd){this.category=this.category+4;}
		nextPref=0;
		currProg="-1";
		preference=new Vector<String> (0);
		this.setIndex(i);
	}
	
	public void addPreference(String prefList){                    //function to create a preference list for the candidate
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
				if(ds){ preference.addElement(pref+"-ds");}
				preference.addElement(pref+"-0");           //if pref is B4110 then B4110-0 is added to preference list
				preference.addElement(pref+"-1");       //0..7 represent categories as given in table of lab10 statement..top to down
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
			}
			else if(category==1){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
			}
			else if(category==2){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-2");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
				preference.addElement(pref+"-6");
			}
			else if(category==3){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-3");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-5");
				preference.addElement(pref+"-7");
			}
			else if(category==4){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
			else if(category==5){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
			else if(category==6){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-2");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-6");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
			else if(category==7){
				if(ds) preference.addElement(pref+"-ds");
				preference.addElement(pref+"-0");
				preference.addElement(pref+"-3");
				preference.addElement(pref+"-4");
				preference.addElement(pref+"-7");
				preference.addElement(pref+"-1");
				preference.addElement(pref+"-5");
			}
		} while(k!=-1);
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
		else {i=-1;}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public void displayCand(){
		System.out.println(id+ " cat "+category+ " pd "+pd +" curr "+currProg+ " nextProg "+ getNextProg());
	}
	
	public void displayPref(){
		System.out.println("preflist: "+id);
		for(int i=0;i<preference.size();i++){
			System.out.print(preference.get(i)+ " ");
		}
		System.out.print('\n');
	}

	

}

	