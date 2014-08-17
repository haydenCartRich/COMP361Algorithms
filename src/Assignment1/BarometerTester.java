package Assignment1;

import java.util.ArrayList;

public class BarometerTester {
	public static void main(String args[]){

		for(int i = 1; i < 100; i+=5){
			ArrayList<Building> input = generateBuildings(i, 1000, 10, 100);
			Skyline s = new Skyline(input);
			ArrayList<Building> inputSame = generateSameBuildings(i, 1000, 10, 100);
			Skyline s2 = new Skyline(inputSame);
			System.out.println(i + "\t" + (s.divideCount + s.mergeCount) + "\t" + (s2.divideCount + s2.mergeCount));
		}
		/*
		System.out.print("input: {");
		for(Building b : s.input){
			System.out.print("("+b.l+","+b.r+","+b.h+")");
		}
		System.out.println("}");

		System.out.print("output: {");
		for(XHTuple t : s.output){
			System.out.print("("+t.x+","+t.h+")");
		}
		System.out.println("}");

		System.out.println("divideCount: " + s.divideCount);
		System.out.println("mergeCount: " + s.mergeCount);
		System.out.println("cost: "+ (s.mergeCount+s.divideCount));
		System.out.println("n: "+input.size()); //n
		System.out.println("n log(n): "+input.size() * Math.log(input.size())); //n log(n) 
		 */
	}

	private static ArrayList<Building> generateBuildings(int n, int l, int r, int h) {
		ArrayList<Building> buildingList = new ArrayList<Building>();
		for(int i = 0; i < n; i++ ){
			float ranL = (float) Math.random() * l;
			float ranR = (float) (ranL + Math.random() * r);
			float ranH = (float) Math.random() * h;
			buildingList.add(new Building(ranL, ranR, ranH));
		}
		return buildingList;
	}
	
	private static ArrayList<Building> generateSameBuildings(int n, int l, int r, int h) {
		ArrayList<Building> buildingList = new ArrayList<Building>();
		float ranL = (float) Math.random() * l;
		float ranR = (float) (ranL + Math.random() * r);
		float ranH = (float) Math.random() * h;
		for(int i = 0; i < n; i++ ){
			buildingList.add(new Building(ranL, ranR, ranH));
		}
		return buildingList;
	}
}
