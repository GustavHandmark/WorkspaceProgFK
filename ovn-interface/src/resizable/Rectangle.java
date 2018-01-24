package resizable;

public class Rectangle implements Resizable {
	private double height;
	private double width;

	public Rectangle(double h, double w) {
		height = h;
		width = w;
	}
	
	public String toString() {
		return "Height: " + height + " width: " + width;
	}

	@Override
	public void downSize(int scaleFactor) {
		height = height / scaleFactor;
		width = width / scaleFactor;
		
	}

}
