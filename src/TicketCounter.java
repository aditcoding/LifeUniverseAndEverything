

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TicketCounter {

	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfLinesRead = 0;
			int numOfWindows = 0;
			int numOfTicketsSold  = 0;
			List<Integer> initParams = new ArrayList<Integer>();
			String [] strArray = null;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				numOfLinesRead++;
				if(numOfLinesRead == 1) {
					strArray = input.split(" ");
					numOfWindows = Integer.parseInt(strArray[0]);
					numOfTicketsSold = Integer.parseInt(strArray[1]);
				} else {
					strArray = input.split(" ");
					for(int i = 0; i < numOfWindows; i++){
						initParams.add(Integer.parseInt(strArray[i]));
					}
				}
			}
			findMaxAmount(initParams, numOfWindows, numOfTicketsSold);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void findMaxAmount(List<Integer> initParams, int numOfWindows, int numOfTicketsSold) {
		int maxAmount = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(numOfWindows, Collections.reverseOrder());
		pq.addAll(initParams);
		while(numOfTicketsSold != 0){
			numOfTicketsSold--;
			int a = pq.poll();
			maxAmount += a;
			a -= 1;
			pq.offer(a);
		}
		log(maxAmount);
	}
	
	static void log(Object o) {
		System.out.println(o.toString());
	}

}
