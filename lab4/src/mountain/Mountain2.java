package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain2 extends Fractal {
	private Point a, b, c;
	private double d;
	private HashMap<Side, Point> smap = new HashMap<Side, Point>();

	public Mountain2(Point a, Point b, Point c, double d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;

	}

	@Override
	public String getTitle() {
		return "Mountain sides Real";
	}

	// D5
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c, d);
	}

	public void fractalTriangle(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double d) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.penDown(); // is set as true when creating a turtle, just for
								// backup.
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			// change the points to the new values, i.e. the middle of where
			// point a meets point b
			// cast randfunc in randomutilities to int. (Point only accepts
			// integers)
			// Draw new triangles (4)
			Point AmB = cngPoint(p1,p2,d);
			Point BmC = cngPoint(p2,p3,d);
			Point CmA = cngPoint(p3,p1,d);

			fractalTriangle(turtle, order - 1, p1, AmB, CmA, d / 2);
			fractalTriangle(turtle, order - 1, AmB, BmC, CmA, d / 2);
			fractalTriangle(turtle, order - 1, AmB, p2, BmC, d / 2);
			fractalTriangle(turtle, order - 1, BmC,p3,CmA, d / 2);
		}
	}

	private Point middle(Point p1, Point p2, double d) {
		return new Point((p1.getX() + p2.getX())/2,
				((p1.getY() + p2.getY()) / 2 + (int) RandomUtilities.randFunc(d)));
	}
	// Check if point in hashmap, if it is - return it, else create new point.
	private Point cngPoint(Point p1, Point p2, double d) {
		Side s = new Side(p1, p2);
		Point mapPoint = smap.get(s);

		if (mapPoint != null) {
			return smap.remove(s);
		} else {
			mapPoint = middle(p1, p2, d);
			smap.put(s, mapPoint);
		}
		return mapPoint;
	}

}
