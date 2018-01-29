package textproc;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class WordCountComparator2 implements Comparator<Map.Entry<String, Integer>> {

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if (Character.isDigit(o1.getKey().charAt(0)) && !Character.isDigit(o2.getKey().charAt(0))) {
			return 1;
		}
		else if (Character.isDigit(o2.getKey().charAt(0)) && !Character.isDigit(o1.getKey().charAt(0))) {
			return -1;
		} else {
			return o1.getKey().compareTo(o2.getKey());
		}
	}

}
