import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CoinsGame {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfLinesRead = 0;
			int k=0,l=0,m=0;
			String [] strArray = null;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				numOfLinesRead++;
				if(numOfLinesRead == 1) {
					strArray = input.split(" ");
					k = Integer.parseInt(strArray[0]);
					l = Integer.parseInt(strArray[1]);
					m = Integer.parseInt(strArray[2]);
				} else {
					strArray = input.split(" ");
					int [] towers = new int [m];
					for(int i=0;i<m;i++){
						towers[i] = Integer.parseInt(strArray[i]);
					}
					printWinner(towers,k,l,m);
					break;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void printWinner(int[] towers, int k, int l, int m) {
		int MAX = 1000000;
		boolean [] bool = new boolean [MAX+1];
		for(int i=1;i<=MAX;i++){
			int tmp1 = i-k;
			int tmp2 = i-l;
			if(i==1 || i==k || i==l){
				bool[i]= true;
			} else if(!bool[i-1]){
				bool[i] = true;
			} else if(tmp1>=1 && !bool[tmp1]){
				bool[i] = true;
			} else if(tmp2>=1 && !bool[tmp2]){
				bool[i] = true;
			}
		}
		for(int j=0;j<m;j++){
			int n = towers[j];
			if(bool[n]){
				log("A");
			} else {
				log("B");
			}
		}
	}

	static void log(Object o) {
		System.out.print(o.toString());
	}
}
