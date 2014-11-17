

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MissingNumberAP {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfLinesRead = 0;
			int lengthOfProgression = 0;
			int [] initParams = null;
			String [] strArray = null;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				numOfLinesRead++;
				if(numOfLinesRead == 1) {
					lengthOfProgression = Integer.parseInt(input);
					initParams = new int[lengthOfProgression];
				} else {
					strArray = input.split(" ");
					for(int i = 0; i < lengthOfProgression; i++){
						initParams[i] = Integer.parseInt(strArray[i]);
					}
					findMissingNumber(initParams);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void findMissingNumber(int[] initParams) {
		int a = initParams[0];
		int b = initParams[1];
		int d = b-a;
		int length = initParams.length;
		for(int i=1;i<=length;i++){
			int k = a + (i-1)*d;
			if(initParams[i-1] != k){
				System.out.println(k);
			}
		}
	}
	
}
