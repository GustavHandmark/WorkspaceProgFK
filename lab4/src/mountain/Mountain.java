package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point pa;
	private Point pb;
	private Point pc;
	
	public Mountain(Point pa, Point pb, Point pc) {
		super();
		this.pa = pa;
		this.pb = pb;
		this.pc = pc;
	}

	@Override
	public String getTitle() {
		return "Mountain";
	}
	// D3

	// turtle.moveTo(turtle.getWidth() / 2.0 - length / 2.0,
	// turtle.getHeight() / 2.0 + Math.sqrt(3.0) * length / 4.0);

	// public void draw(TurtleGraphics turtle) {
	// fractalTriangle(turtle,order,pa,pb,pc);
	// }
	//
	// /*
	// * Recursive method: Draws a recursive triangle of the mountain.
	// */
	// private void fractalTriangle(TurtleGraphics turtle, int order, Point a,
	// Point b, Point c) {
	// if(order==0){
	// turtle.moveTo(a.getX(),a.getY());
	// turtle.penDown(); // set as true when creating a turtle, just for backup.
	// turtle.forwardTo(b.getX(),b.getY());
	// turtle.forwardTo(c.getX(),c.getY());
	// turtle.forwardTo(a.getX(),a.getY());
	// } else {
	// // change the points to the new values, i.e. the middle of where point a
	// meets point b
	// Point AmB = new Point((a.getX()+b.getX())/2,(a.getY()+b.getY())/2);
	// Point BmC = new Point((b.getX()+c.getX())/2,(b.getY()+c.getY())/2);
	// Point CmA = new Point((c.getX()+a.getX())/2,(c.getY()+a.getY())/2);
	// // Draw new triangles (4)
	// fractalTriangle(turtle,order - 1,AmB,BmC,CmA);
	// fractalTriangle(turtle,order - 1,AmB,CmA,a);
	// fractalTriangle(turtle,order - 1,AmB,b,BmC);
	// fractalTriangle(turtle,order - 1,BmC,c,CmA);
	// }
	//
	// }

	// D4
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle,order,pa,pb,pc,100.0);
	}

	/*
	 * Recursive method: Draws a recursive triangle of the mountain.
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c,double d) {
		if(order==0){
			turtle.moveTo(a.getX(),a.getY());
			turtle.penDown(); // set as true when creating a turtle, just for backup.
			turtle.forwardTo(b.getX(),b.getY());
			turtle.forwardTo(c.getX(),c.getY());
			turtle.forwardTo(a.getX(),a.getY());
		} else {
			// change the points to the new values, i.e. the middle of where point a meets point b
			// cast randfunc in randomutilities to int. (Point only accepts integers)
			Point AmB = new Point((a.getX()+b.getX())/2,(a.getY()+b.getY())/2+(int)RandomUtilities.randFunc(d));
			Point BmC = new Point((b.getX()+c.getX())/2,(b.getY()+c.getY())/2+(int)RandomUtilities.randFunc(d));
			Point CmA = new Point((c.getX()+a.getX())/2,(c.getY()+a.getY())/2+(int)RandomUtilities.randFunc(d));
			// Draw new triangles (4)
			fractalTriangle(turtle,order - 1,AmB,BmC,CmA,d/2);
			fractalTriangle(turtle,order - 1,AmB,CmA,a,d/2);
			fractalTriangle(turtle,order - 1,AmB,b,BmC,d/2);
			fractalTriangle(turtle,order - 1,BmC,c,CmA,d/2);
		}
		
	}
}