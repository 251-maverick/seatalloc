package MeritOrder;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.LinkedList.*;
import common.*;

/**
 * This class stores an array of Candidates sorted on their ranks (GE appended by OBC and so on) and also an array of DS candidates sorted on 
 * GE rank
 * @author rishabhraj
 *
 */
public class MeritList{
	private Candidate[] meritList;
	private Candidate[] dsList;
	private Candidate[] foreignList;
	MeritList(Vector<Candidate> candList,String filepath ) throws FileNotFoundException{    
		LinkedList<Candidate> geList=new LinkedList<Candidate>();
		LinkedList<Candidate> obcList=new LinkedList<Candidate>();
		LinkedList<Candidate> scList=new LinkedList<Candidate>();
		LinkedList<Candidate> stList=new LinkedList<Candidate>();
		LinkedList<Candidate> gepdList=new LinkedList<Candidate>();
		LinkedList<Candidate> obcpdList=new LinkedList<Candidate>();
		LinkedList<Candidate> scpdList=new LinkedList<Candidate>();
		LinkedList<Candidate> stpdList=new LinkedList<Candidate>();
		ArrayList<LinkedList<Candidate>> array=new ArrayList<LinkedList<Candidate>>(); //'array' is an array of linked-list of candidates
		array.add(geList);array.add(obcList);array.add(scList);array.add(stList);array.add(gepdList);array.add(obcpdList);
		array.add(scpdList);array.add(stpdList);
		LinkedList<Candidate> dslist=new LinkedList<Candidate>();
		LinkedList<Candidate> foreignlist=new LinkedList<Candidate>();
		for(int i=0;i<candList.size();i++){
			if(candList.get(i).getDs()){
				dslist.add(candList.get(i));
			}
			if(candList.get(i).getNat()){
					foreignlist.add(candList.get(i));
				}
			
			for(int j=0;j<8;j++){
				float rank=(candList.get(i)).getRank()[j];
				if(rank!=0) (array.get(j)).add(candList.get(i));      //appending candidate to appropriate lists depending on their ranks in
																	  //different categories
			}	
			
		}
		Comparator<Candidate> cmp0=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[0]<c2.getRank()[0]) return -1;
				else if(c1.getRank()[0]>c2.getRank()[0]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(0),cmp0);                     //sorting list of candidates with a GE rank on their ranks using comparator cmp0
		Collections.sort(dslist,cmp0);
		Collections.sort(foreignlist,cmp0);
		Comparator<Candidate> cmp1=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[1]<c2.getRank()[1]) return -1;
				else if(c1.getRank()[1]>c2.getRank()[1]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(1),cmp1);
		Comparator<Candidate> cmp2=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[2]<c2.getRank()[2]) return -1;
				else if(c1.getRank()[0]>c2.getRank()[0]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(2),cmp2);
		Comparator<Candidate> cmp3=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[3]<c2.getRank()[3]) return -1;
				else if(c1.getRank()[3]>c2.getRank()[3]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(3),cmp3);
		Comparator<Candidate> cmp4=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[4]<c2.getRank()[4]) return -1;
				else if(c1.getRank()[4]>c2.getRank()[4]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(4),cmp4);
		Comparator<Candidate> cmp5=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[5]<c2.getRank()[5]) return -1;
				else if(c1.getRank()[5]>c2.getRank()[5]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(5),cmp5);
		Comparator<Candidate> cmp6=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[6]<c2.getRank()[6]) return -1;
				else if(c1.getRank()[6]>c2.getRank()[6]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(6),cmp6);
		Comparator<Candidate> cmp7=new Comparator<Candidate>(){
			public int compare(Candidate c1,Candidate c2){
				if(c1.getRank()[7]<c2.getRank()[7]) return -1;
				else if(c1.getRank()[7]>c2.getRank()[7]) return 1;
				else return 0;
				}
		};
		Collections.sort(array.get(7),cmp7);
		
		for(int j=1;j<8;j++) array.get(0).addAll(array.get(j));        //appending OBC,SC..(and so on) into GE meritlist
		meritList=array.get(0).toArray(new Candidate[array.get(0).size()]); //converting lists to array for efficient traversal
		dsList=dslist.toArray(new Candidate[dslist.size()]);
		foreignList=foreignlist.toArray(new Candidate[foreignlist.size()]);
	}
	public Candidate[] getDSList(){
		return dsList;              
	}
	public Candidate[] getMeritList(){
		return meritList;
	}
	public Candidate[] getForeignList(){
		return foreignList;
	}
		
		
	
}