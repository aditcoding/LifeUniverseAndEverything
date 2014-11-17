

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TheEnd42 {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			while ((input = br.readLine()) != null && input.trim().length() != 0) {
				Object output = checkInput(input);
				System.out.println(output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Object checkInput(String input) {
		try{
			int x = Integer.parseInt(input);
			if(x > 99 || x < -99){
				System.out.println("Encountered a big number. Continuing...");
				return "";
			} else if (x == 42){
				System.out.println("Encountered input 42. Terminating program");
				System.exit(0);
			} else {
				return x;
			}
		} catch (NumberFormatException nfe){
			System.out.println("Invalid input. Terminating program");
			System.exit(0);
		}
		return "";
	}

}
