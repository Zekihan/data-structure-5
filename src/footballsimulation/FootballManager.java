package footballsimulation;

import collections.Set;

public interface FootballManager {
	
	// This method is only called once before the match.
	// It should return a set of 11 players with the following formation:
	// 1 × GK (Goalkeeper)
	// 1 × DL (Defender Left)
	// 2 × DC (Defender Centre)
	// 1 × DR (Defender Right)
	// 1 × ML (Midfielder Left)
	// 2 × MC (Midfielder Centre)
	// 1 × MR (Midfielder Right)
	// 2 × FC (Forward Centre) 
	// This set should be a subset of the squad (i.e. players should be selected from the squad).
	public Set<Player> decideStartingLineUp(FootballClub ownClub, FootballClub opponent);
	
	// This method is only called once before the match.
	// It should return a set of players
	// who appear in the squad but not in the starting line up.
	// Cardinality of this set should be 7.
	public Set<Player> decideSubstitutePlayers(FootballClub ownClub, FootballClub opponent, Set<Player> startinglineUp);
	
	// This method is only called once at halftime.
	// This method should return a new line up based on the current line up and the substitute players.
	// The maximum number of player substitutions is 3.
	public Set<Player> makeSubstitutions(FootballClub ownClub, FootballMatch footballMatch); 
	
	
	// Note: A football manager can manage multiple teams at the same time.
	// That is why we do not store "ownClub" as an attribute here.
	
}
