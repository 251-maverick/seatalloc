import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class GaleShapleyAdmission{
	private HashMap virtualProgramme; 
	private Vector<VirtualProgramme> program;
	public GaleShapleyAdmission() throws FileNotFoundException{ 
		virtualProgramme=new HashMap<String,VirtualProgramme>();   //virtualProgramme is hashmap of (code_of_program+'-category_code',corresponding virtual program)
		Scanner scan=new Scanner(new File("./programs.csv"));
		boolean firstline=true;
		String s;
		while(scan.hasNextLine()){                              //reads from program.csv and creates virtualProgramme hashmap
			if(firstline) continue;
			s=scan.NextLine();
			String[] fields=s.split(",");
			for(int i=3;i<11;i++){
			VirtualProgramme p;
			p=new VirtualProgramme(i-3,fields[i]);               //see if vp object can be created without any import or smthing
			virtualProgramme.put(fields[1]+"-"+(i-3),p);
			}
			firstline=false;
			}
			}
	
		}
		
	public MeritList combinedRankList(int cat,MeritList meritlist[]){ //creates 'combined' hashmap which caters to seat deservation
			if(cat==0 || cat==2 || cat==3){                           
			return meritlist[cat];
		}
		else if(cat==1 || cat==4 || cat==5){
			meritlist[0].updateRank(meritList[cat].size());
			meritlist[cat].addMap(meritlist[0]);
			return meritlist[cat];
		}
		
		
		else if(cat==6 || cat==7){
			meritlist[cat-4].updateRank(meritList[cat].size());
			meritlist[cat].addMap(meritlist[cat-4]);
			return meritlist[cat];
		}
	}
	
	public 
		
			
			
		
