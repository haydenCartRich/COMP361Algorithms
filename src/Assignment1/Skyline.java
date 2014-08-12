package Assignment1;

import java.util.List;
import java.util.ArrayList;

public class Skyline {

	public List<Building> input;
	public List<XHTuple> output;

	public Skyline(List<Building> input){
		this.input = input;
		this.output = toSkyline(input);

	}

	private ArrayList<XHTuple> toSkyline(List<Building> input) {
		if(input.size() == 0){
			return new ArrayList<XHTuple>();
		}
		else if(input.size() == 1){
			ArrayList<XHTuple> toReturn =  new ArrayList<XHTuple>();
			toReturn.add(new XHTuple(input.get(0).l,input.get(0).h));
			toReturn.add(new XHTuple(input.get(0).r,0));
			return toReturn;
		}

		int middle = input.size()/2;
		ArrayList<XHTuple> left = toSkyline(input.subList(0, middle));
		ArrayList<XHTuple> right = toSkyline(input.subList(middle, input.size()));

		return mergeSkylines(left, right);

	}

	private ArrayList<XHTuple> mergeSkylines(ArrayList<XHTuple> left, ArrayList<XHTuple> right) {
		ArrayList<XHTuple> mergedOutput= new ArrayList<XHTuple>();

		float leftMax = 0, rightMax = 0;

		while(!left.isEmpty() || !right.isEmpty()) {
			if(left.isEmpty()){
				mergedOutput.add(right.remove(0));
			}
			else if(right.isEmpty()){
				mergedOutput.add(left.remove(0));
			}
			else {
				float x = 0;
				if(left.get(0).x == right.get(0).x){
					x = left.get(0).x;
					leftMax = left.remove(0).h;
					rightMax = right.remove(0).h;
				}
				else if(left.get(0).x < right.get(0).x){
					x = left.get(0).x;
					if(left.get(0).x > right.get(0).x){
						x = right.get(0).x;
						rightMax = right.remove(0).h;
					}
					leftMax = left.remove(0).h;
				}
				else {
					x = right.get(0).x;
					rightMax = right.remove(0).h;
				}
				mergedOutput.add(new XHTuple(x, Math.max(leftMax, rightMax)));
			}
		}
		return mergedOutput;
	}

	public static void main(String args[]){
		ArrayList<Building> input = new ArrayList<Building>();
		input.add(new Building(0, 2, 1));
		input.add(new Building(1, 4, 2));
		input.add(new Building(2, 3, 3));
		input.add(new Building(5, 6, 2));
		Skyline s = new Skyline(input);

		System.out.print("{");
		for(XHTuple t : s.output){
			System.out.print("("+t.x+","+t.h+")");
		}
		System.out.print("}");
	}
}
