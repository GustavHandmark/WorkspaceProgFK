package test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args){
		Map<String,Integer> map = new HashMap<>();
		System.out.println(map.get("A") + "" + map.size());
		map.put("A", 43);
		System.out.println(map.get("A") + "" + map.size());

		map.put("A", 42);
		System.out.println(map.get("A") + "" + map.size());
		
	}
}
