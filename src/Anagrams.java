

import java.util.Arrays;

public class Anagrams {

	public static void main(String[] args) {
		long n = 1246878;
		isAnagram(n);
		
	}

	private static void isAnagram(long n) {
		String s = String.valueOf(n);
		char [] c = s.toCharArray();
		Arrays.sort(c);
		for(int i=2;i<10;i++){
			long product = n*i;
			char [] c1 = String.valueOf(product).toCharArray();
			Arrays.sort(c1);
			if(Arrays.equals(c, c1)){
				log("Equal for i = " + i + " product is : " + product);
			}
		}
		
	}


	static void log(Object o) {
		System.out.println(o.toString());
	}
}
