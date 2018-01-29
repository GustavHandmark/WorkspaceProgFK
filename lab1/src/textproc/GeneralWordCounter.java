package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> wordlist = new HashMap<String, Integer>();
//	private Map<String, Integer> wordlist = new TreeMap<String, Integer>();

	private Set<String> excluded;

	public GeneralWordCounter(Set<String> s) {
		excluded = s;
	}

	public void process(String w) {
		if (!excluded.contains(w)) {
			if (wordlist.containsKey(w)) {
				wordlist.compute(w, (k,v) -> v + 1);
			} else {
				wordlist.put(w, 1);
			}
		}
	}
//	public void report(){
//		wordlist.forEach((k, v) -> {
//			if (v >= 200) {
//				System.out.println(k + " : " + v);
//			}
//		});
//	}
	public void report() {
		
		Set<Map.Entry<String, Integer>> wordSet = wordlist.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i =0; i<50;i++){
			System.out.println(wordList.get(i).getKey()+" : "+wordList.get(i).getValue());
		}
	}
	public Set<Map.Entry<String, Integer>> getWords(){
		return wordlist.entrySet();
	}
}
