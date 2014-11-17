

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class InversionCount {
	
	static long merge(int[] arr, int[] left, int[] right) {
	    int i = 0, j = 0, count = 0;
	    while (i < left.length || j < right.length) {
	        if (i == left.length) {
	            arr[i+j] = right[j];
	            j++;
	        } else if (j == right.length) {
	            arr[i+j] = left[i];
	            i++;
	        } else if (left[i] <= right[j]) {
	            arr[i+j] = left[i];
	            i++;                
	        } else {
	            arr[i+j] = right[j];
	            count += left.length-i;
	            j++;
	        }
	    }
	    return count;
	}

	static long invCount(int[] arr) {
	    if (arr.length < 2)
	        return 0;

	    int m = (arr.length + 1) / 2;
	    int left[] = Arrays.copyOfRange(arr, 0, m);
	    int right[] = Arrays.copyOfRange(arr, m, arr.length);

	    return invCount(left) + invCount(right) + merge(arr, left, right);
	}

	public static void main(String[] args) {
		try {
			
			boolean start = true;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfTestCases = 0;
			int numOfLinesRead = 0;
			int lengthOfInput = 0;
			String [] strArray = null;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				long numOfSwaps = 0;
				numOfLinesRead += 1;
				if(start){
					numOfTestCases = Integer.parseInt(input);
					start = false;
				} else {
					if(numOfLinesRead % 2 != 0){
						int [] intArray = new int [lengthOfInput];
						strArray = input.split(" ");
						for(int i = 0; i < strArray.length; i++){
							int x = Integer.parseInt(strArray[i]);
							intArray[i] = x;
						}
						numOfSwaps = invCount(intArray);
						//numOfSwaps = invCount(setUp());
						System.out.println(numOfSwaps);
						numOfTestCases -= 1;
						if(numOfTestCases == 0)
							break;
					} else {
						lengthOfInput = Integer.parseInt(input);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	 public static int [] setUp() throws Exception {
		    int size = 7000000;
		    int [] numbers = new int [size];
		    Random generator = new Random();
		    for (int i = 0; i < size; i++) {
		      int random = generator.nextInt();
		      numbers[i] = random;
		      //System.out.println(random);
		    }
		    return numbers;
		  }
}
