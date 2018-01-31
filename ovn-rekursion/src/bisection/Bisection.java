package bisection;

public class Bisection {

	/**
	 * Beräkna nollstället för funktionen f i intervallet low..high med
	 * precisionen eps. Förutsätter att det finns ett nollställe i intervallet.
	 */
	public static double getZero(double low, double high, double eps, Function f) {
		double m = (high + low) / 2;
		if (Math.abs(high - low) < eps) {
			return m;
		} else {
			if (Math.signum(f.evaluate(m)) == Math.signum(f.evaluate(high))) {
				return getZero(low, m, eps, f);
			} else {

				return getZero(m, high, eps, f);
			}
		}
	}
}
