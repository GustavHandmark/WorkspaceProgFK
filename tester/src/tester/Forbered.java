package tester;
import java.util.*;

public class Forbered {
	public static void main(String[] args){
		//List<Integer> nbrs = new ArrayList<Integer>();
		Set<Integer> nbrs = new HashSet<Integer>();
		Set<Integer> nbrs2 = new TreeSet<Integer>();

		for (int i = 0; i < 100; i += 10){
			nbrs.add(i);
			nbrs.add(i);
			
		}
		
		int sumln=0;
		for(int a: nbrs){
			System.out.println(a);
			sumln=sumln+1;
		}
		System.out.println(": " + sumln);
	}
}