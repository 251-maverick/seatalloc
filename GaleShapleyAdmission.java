import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class GaleShapleyAdmission{
	//put datamembers
	private Vector<VirtualProgramme> program;
	public GaleShapleyAdmission() throws FileNotFoundException{ 
		Scanner scan=new Scanner(new File("./programs.csv"));
		boolean firstline=true;
		String s;
		while(scan.hasNextLine()){
			if(firstline) continue;
			s=scan.NextLine();
			String[] fields=s.split(",");
			for(int i=3;i<11;i++){
			VirtualProgramme p(i-3,fields[i]);
			program.addElement(p);
			}
			firstline=false;
			}
			}
	
		}
		
	public MeritList combinedRankList(int cat,MeritList meritlist[]){
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
		
			
			
		
