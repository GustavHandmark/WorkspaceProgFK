package bisection;

public class MyFunction implements Function{
	public double evaluate(double x){
		return Math.exp(-x) - 1 + Math.cos(x);
	}
	public static void main(String[] args){
		double zero = Bisection.getZero(0, 1.5, 0.0001, new MyFunction());
		System.out.println(zero);
	}

}
