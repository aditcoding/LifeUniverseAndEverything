

public class PrimesLessThanN {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int numOfPrimes = 2;
		boolean isPrime = false;
		for(int i=3;i<=n;i++){
			if(i>6)
				if((i-1)%6 != 0 && (i+1)%6 != 0)
					continue;
			for(int j=2;j<=(i/2);j++){
				if(i%j != 0){
					isPrime = true;
					continue;
				} else {
					isPrime = false;
					break;
				}
			}
			if(isPrime)
				numOfPrimes++;
		}
		log(numOfPrimes);
	}
	
	static void log(Object o) {
		System.out.println(o.toString());
	}
}
