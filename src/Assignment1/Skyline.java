package Assignment1;

import java.util.List;
import java.util.ArrayList;

public class Skyline {

	public List<Building> input;
	public List<XHTuple> output;
	
	public int divideCount = 0;	
	public int mergeCount = 0;

	public Skyline(List<Building> input){
		this.input = input;
		this.output = toSkyline(input);

	}

	private ArrayList<XHTuple> toSkyline(List<Building> input) {
		divideCount++;
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
			mergeCount++;
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
					leftMax = left.remove(0).h;
				}
				else {
					x = right.get(0).x;
					rightMax = right.remove(0).h;
				}
				float maxH = Math.max(leftMax, rightMax);
				if(mergedOutput.size() > 0){
					if(maxH != mergedOutput.get(mergedOutput.size()-1).h){
						mergedOutput.add(new XHTuple(x, maxH));
					}
				}
				else
					mergedOutput.add(new XHTuple(x, maxH));		
			}
		}
		return mergedOutput;
	}
}
