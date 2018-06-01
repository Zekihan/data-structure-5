package footballsimulation;

import collections.ArraySet;
import collections.Set;


public class FootballClub {

	private FootballManager footballManager;
	
	private String clubName;
	
	private Set<Player> squad;
	
	@SuppressWarnings("unchecked")
	public FootballClub(FootballManager footballManager, String clubName, Set<Player> squad) {
		
		footballManager = footballManager;
		clubName = clubName;
		squad = new ArraySet<Player>((ArraySet<Player>) squad);
		
		
		// TODO: Assign values to attributes.
		// Note that squad is a mutable object.
		// Do not assign the squad directly to the attribute.
		// Instead, create a copy.
		
		// The squad should contain
		// at least 1 possible starting line up (e.g. at least 1 GK, at least 2 FCs, etc.)
		// and at least 18 players (11 for starting line up and 7 for substitutes),
		// All the players should have a shirt number between 1 and 99.
		// These numbers should be unique.
		
	}
	
	public FootballManager getManager()
	{
		return footballManager;
	}
	public String getClubName()
	{
		return clubName;
	}
	public Set<Player> getSquad()
	{
		Set<Player> squadCopy = new ArraySet<Player>((ArraySet<Player>) squad);
		return squadCopy;
	}
	// TODO: Define getter methods only.
	// Do not return the squad directly.
	// Instead, return a copy.
	
}
