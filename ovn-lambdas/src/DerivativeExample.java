import java.util.function.Function;

public class DerivativeExample {

	public interface Func {
		// här behövs mer (uppgift U8)
		double comp(double x);
	}
	
	public static double deriv(Func f, double x) {
		final double h = 1e-6;
		return (f.comp(x+h)-f.comp(x))/h; // använd derivatans definition här (uppgift U9)
	}
	
	public static double deriv1(Function<Double,Double> f,double x){
		final double h = 1e-6;
		return (f.apply(x+h)-f.apply(x))/h;
		
	}
	public static Func deriv2(Func f){
		final double h = 1e-6;
		return x ->(f.comp(x+h)-f.comp(x))/h;
	}
	
	public static void main(String[] args) {
		// avkommentera nedanstående

		 double yprime = deriv1(x -> Math.sin(x), 0.3);
		 System.out.println(yprime + " (förväntat: ca 0.9553)");
		 
		 Func f = x -> Math.sin(x); // f(x) = sin(x)
		 Func g = deriv2(f); // g(x) = f’(x)
		 double yp = g.comp(0.3); // yp = g(0.3) = f’(0.3)
		 System.out.println(yp);
		 
	}
}
