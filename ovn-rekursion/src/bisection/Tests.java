package bisection;

public class Tests {

	public static boolean isPalindrome(String s) {
		return recPalindrome(s, 0, s.length() - 1);
	}

	private static boolean recPalindrome(String s, int start, int end) {
		if (end <= start) {
			return true;
		} else if (s.charAt(start) != s.charAt(end)) {
			return false;
		} else {
			return recPalindrome(s, start + 1, end - 1);
		}
	}

	public static double computeCapital(double capital, int years, double interestRate) {
		if (years == 0) {
			return capital;
		} else {
			return computeCapital(capital*(1+interestRate/100), years-1,interestRate);
		}

	}
	
	
	public static void main(String[] args){
		System.out.println(computeCapital(1000,3,10));
		
	}
}