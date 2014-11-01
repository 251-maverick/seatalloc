package common;
//import GS.GaleShapleyAdmission;
//import MeritOrder.MeritOrderAdmission;
import GS.*;
import MeritOrder.*;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("me");
		// TODO Auto-generated method stub
				MeritOrderAdmission moa = new MeritOrderAdmission("/Users/Palak/Desktop/backup_project/lab10/test_cases/2");
		moa.allocAlgo();
		GaleShapleyAdmission gs = new GaleShapleyAdmission("/Users/Palak/Desktop/backup_project/lab10/test_cases/2");
		//GaleShapleyAdmission gs = new GaleShapleyAdmission(args[0]);		
		gs.gsAlgo();

		

	}

}
