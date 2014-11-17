import java.util.PriorityQueue;

public class MinimumMovesForStringConversion {
	
	static int [][] dp;
	static String src;
	static String dest;
	
	public static void main(String[] args) {
		src = args[0];
		dest = args[1];
		int n = src.length();
		dp = new int [n][n];
		for(int m=0;m<n;m++){
			for(int i=0;i<n-m;i++){
				if(m==0){
					if(src.charAt(i) != dest.charAt(i))
						dp[i][i] = 1;
				} else {
					int j = i+m;
					dp[i][j] = rec(i, j, dest.charAt(i));
				}
			}
			
		}
		log(dp[0][n-1]);
	}

	private static int rec(int i, int j, char c) {
		PriorityQueue<Integer> sums;
		if(i==j){
			return dp[i][i];
		} else{
			sums = new PriorityQueue<Integer>();
			for(int k=i;k<j;k++){
				sums.add(rec(i,k, dest.charAt(i)) + rec(k+1,j, dest.charAt(k+1)));
			}
		}
		return Math.min(sums.peek(), 
				1 + rec(i+1,j,dest.charAt(i)));
	}

	static void log(Object o) {
		System.out.println(o.toString());
	}
}
