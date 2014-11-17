

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NumberOfOperationsToGetToN {
	
	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfLinesRead = 0;
			int numOfInputs = 0;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				numOfLinesRead++;
				if(numOfLinesRead == 1) {
					numOfInputs = Integer.parseInt(input);
				} else {
					int num = Integer.parseInt(input);
					findNumOfOperations(num, 0);
					if(numOfLinesRead == numOfInputs+1)
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void findNumOfOperations(int num, int numOfOps) {
		if(num == 0)
			log(numOfOps);
		else if(num%2 == 0){
			numOfOps++;
			findNumOfOperations(num/2, numOfOps);
		}
		else{
			numOfOps++;
			num -= 1;
			if(num != 0){
				numOfOps++;
				findNumOfOperations(num/2, numOfOps);
			}
		}
	}

	static void log(Object o) {
		System.out.println(o.toString());
	}
}
