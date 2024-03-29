package mountain;

public class Side {
	private Point p1;
	private Point p2;
	
	
	
	public Side(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	
	public boolean equals(Object obj){
		if (obj instanceof Side){
			Side s = (Side) obj;
			return (p1 == s.p1 && p2 == s.p2) || (p2 == s.p1 && p1 == s.p2);
		} else {
			return false;
		}
		
	}
	
	public int hashCode(){
		return p1.hashCode() +p2.hashCode();
	}
	
}
