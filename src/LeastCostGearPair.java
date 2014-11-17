

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeastCostGearPair {

	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfLinesRead = 0;
			int numOfGears = 0;
			int distance = 0;
			List<Integer> radii = null;
			List<Integer> costs = null;
			String [] strArray = null;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				numOfLinesRead++;
				if(numOfLinesRead == 1) {
					strArray = input.split(" ");
					numOfGears = Integer.parseInt(strArray[0]);
					distance = Integer.parseInt(strArray[1]);
					radii = new ArrayList<Integer>();
					costs = new ArrayList<Integer>();
				} else if(numOfLinesRead == 2){
					strArray = input.split("");
					for(int i=0;i<numOfGears;i++)
						radii.add(Integer.parseInt(strArray[i]));
				} 
				else {
					strArray = input.split("");
					for(int i=0;i<numOfGears;i++)
						costs.add(Integer.parseInt(strArray[i]));
					break;
				}
			}
			findLeastCostPair(radii,costs,distance,numOfGears);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void findLeastCostPair(List<Integer> radii, List<Integer> costs,
			int distance, int numOfGears) {
		for(int i=0;i<numOfGears;i++){
			int [] pairCost = new int [numOfGears];
			int radius1 = radii.get(i);
			for(int j=0;j<numOfGears;j++){
				int radius2 = radii.get(j);
				int cost2 = costs.get(j);
				if(radius1 + radius2 < distance){
					continue;
				} else {
					pairCost[j] = cost2;
				}
			}
			Arrays.sort(pairCost);
			int index = filterDuplicates(pairCost, radii, costs);
			log(index);
		}
	}

	private static int filterDuplicates(int[] pairCost, List<Integer> radii, List<Integer> costs) {
		int retIndex = -1;
		List<Integer> matchedCostRadii = new ArrayList<Integer>();
		int minCost = pairCost[0];
		if(minCost != pairCost[1])
			retIndex = costs.indexOf(minCost);
		else{
			for(int i=1;i<pairCost.length;i++){
				if(pairCost[i] == minCost){
					matchedCostRadii.add(radii.get(i));
				} else
					break;
			}
			Collections.sort(matchedCostRadii, Collections.reverseOrder());
			int maxRadius = matchedCostRadii.get(0);
			retIndex = radii.indexOf(maxRadius);
		}
		return retIndex;
	}

	static void log(Object o) {
		System.out.print(o.toString() + " ");
	}
}
