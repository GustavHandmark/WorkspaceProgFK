package textproc;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		List<String> holgersson = new ArrayList<String>();

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		while (s.hasNext()) {
			holgersson.add(s.next().toLowerCase());
		}
		s.close();

		// Uppgift D4
		List<TextProcessor> textprocessors = new ArrayList<TextProcessor>();
		textprocessors.add(new SingleWordCounter("nils"));
		textprocessors.add(new SingleWordCounter("norge"));
		// java 8 foreach,lambda expression
		holgersson.forEach(word -> {
			for (TextProcessor p : textprocessors) {
				p.process(word);
			}
		});

		System.out.println("\n" + "Uppgift D4");
		for (TextProcessor p : textprocessors) {
			p.report();
		}

		// Uppgift D5/6
		long t0 = System.nanoTime();
		TextProcessor multiwc = new MultiWordCounter(REGIONS);
		holgersson.forEach(word -> multiwc.process(word));
		System.out.println("\n" + "Uppgift D5/6");
		multiwc.report();
		long t1 = System.nanoTime();

		// Uppgift D8
		long t2 = System.nanoTime();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next().toLowerCase());
		}
		scan.close();

		TextProcessor r = new GeneralWordCounter(stopwords);
		holgersson.forEach(word -> r.process(word));
		
		
		System.out.println("\n" + "Uppgift D8");
		// r.report();

		// Uppgift D9/D11/D12
		System.out.println("\n" + "Uppgift D9");
		r.report();
		long t3 = System.nanoTime();

		// D13
		System.out.println("Tid, MultiWordCounter: " + (t1 - t0) / 1000000.0 + "ms");
		System.out.println("Tid, GeneralWordCounter: " + (t3 - t2) / 1000000.0 + "ms");
		System.out.println(holgersson.size());
		
		//30.696, 37.37, 36.84 -> 36.84 (HashMap), MultiWordCounter
		//135.68, 125.866, 145.712 -> 135.68 (HashMap), GeneralWordCounter
		
		//26.7091575, 38.371747, 36.17403 -> 36.17403(TreeMap), MultiWordCounter
		//159.45, 180.979, 150.560548 -> 159.45 -> (TreeMap), GeneralWordCounter
		
		// Programmet fungerar fortfarande, Ordningen påverkas ej, Exekveringstiden påverkas något för GWC.
		// D14
		// Map är ett interface, HashMap implementerar interfacet Map.
		/* HashMap är uppbyggt genom att man skapar ett antal "buckets" i vilka Entry(k,v) objekt sedan kan lagras, i vilken av dessa "buckets"
		 * som Key-value paret ska läggas avgörs utifrån nyckelns hashvalue (d.v.s. mata in nyckeln genom en hashfunktion) och sedan används
		 * modulus n för att avgöra vilken bucket som key-value paret hamnar(där n är antalet buckets, bucketsen ligger i en array, Entry []).
		 * Skulle kollisioner uppstå, d.v.s. att två nycklar har samma hashvärde, skapas en variant av en linkedlist
		 * (linkedlistan kan komma att dynamiskt göras om till en treemap om antal Entryobjekt blir för många), där den första nyckeln pekar på
		 * nästan plats, är denna null läggs sedan den nya, kolliderande, nyckel-värde paret in på denna plats, osv. När Loadcapaciteten för hashmappen
		 * blir för hög (utilization>0.75) dubbleras antalet buckets.
		 * Tiderna för put/get/contains etc är O(1) (givet en bra hashfunktion), annars O(n) i det värsta exemplet där alla key-value par hamnar i 
		 * samma bucket (Osannolikt), här kommer dock linkedlistan göras om till en TreeMap -> O(log(n)) (binärsökning).
		 * 
		 * 
		 * TreeMap är en s.k. Red-Black NavigableMap som sorterar key-value par genom Red-Black tree algoritmen (ett självbalanserande sätt att 
		 * ordna data genom att ge en nod antingen röd eller svart färg (0,1), varje child är motsatsen till denna färg). Vid insättning balanseras trädet
		 * om så att ordningen (balansen) upprätthålls. Därefter fungerar det som ett binärt sökträd. D.v.s. att nycklarna är ordnade i någon ordning.
		 * Antingen genom en standard comparator eller en man själv definierar.
		 * 
		 * Ja, en fördel är att kunna göra generella deklarationer istället för specifika, vilket innebär att det är enkelt att ändra sub-datatyper i efterhand
		 *Comparator är ett interface som kan användas i ex. sort() och används för att avgöra hur två objekt förhåller sig till varandra.
		 *
		 * 
		 */ 
		

	}
}