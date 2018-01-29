package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor {

//	private Map<String, Integer> countlist = new HashMap<String, Integer>();
	private Map<String, Integer> countlist = new TreeMap<String, Integer>();

	public MultiWordCounter(String[] words) {
		for (String s : words) {
			countlist.put(s, 0);
		}

	}

	@Override
	public void process(String w) {
//		Java 8 ->
		countlist.computeIfPresent(w, (k,v) -> v + 1);
		

//		2.) Probably safest approach,
//		if(countlist.containsKey(w)){
//			countlist.put(w, map.get(w)+1);
//		}
//		Alternative 3)
//		countlist.forEach((k, v) -> {
//			if (w.equals(k)) {
//				v++;
//			}
//		});


	}

	@Override
	public void report() {
		countlist.forEach((k, v) -> System.out.println(k + " : " + v));

	}
}
