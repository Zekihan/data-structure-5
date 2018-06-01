package footballsimulation;

import collections.ArrayDictionary;
import collections.Dictionary;

public class FootballManagerApp {

	public static void main(String... args) {
		
		// TODO: Create two football clubs and organize a single match between them.
		// Print the result and the "player of the match".
		Dictionary<Integer, Integer> achievements = new ArrayDictionary<Integer, Integer>();
		achievements.add(1, 1);
		achievements.add(2, 2);
		achievements.add(3, 3);
		System.out.println(achievements.getValueIterator());
	}
	
	
	
}
