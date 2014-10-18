import java.util.Vector;


public class Candidate {

	/**
	 * @param args
	 */
	private String id;
	private int category;
	private boolean nat,ds;   /** nat 0 implies indian, 1 implies foreign */
	private Vector<String> preference;
	private int nextPref;
	private int currPref;
	private String currProg;
	
	public Candidate(String id,int  cat,boolean nat,boolean ds){
		this.id=id;
		category=cat;
		this.nat=nat;
		this.ds=ds;
		currPref=-1;
		currProg="NONE";
		preference=new Vector<String> (0);
	}
	
	public void addPreference(String prefList){//currpref=0
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
				preference.addElement(pref+"-GE");           ////if pref is IITB-CSE then IITB-CSE-GE is added to preference list
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-OBC-PD");
			}
			else if(category==1){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-OBC-PD");
			}
			else if(category==2){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-SC");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-OBC-PD");
				preference.addElement(pref+"-SC-PD");
			}
			else if(category==3){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-ST");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-OBC-PD");
				preference.addElement(pref+"-ST-PD");
			}
			else if(category==4){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-OBC-PD");
			}
			else if(category==5){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-OBC-PD");
			}
			else if(category==6){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-SC");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-SC-PD");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-OBC-PD");
			}
			else if(category==7){
				preference.addElement(pref+"-GE");
				preference.addElement(pref+"-ST");
				preference.addElement(pref+"-GE-PD");
				preference.addElement(pref+"-ST-PD");
				preference.addElement(pref+"-OBC");
				preference.addElement(pref+"-OBC-PD");
			}
		} while(k!=-1);
			
	}
		
	

	
	}



	
