package footballsimulation;

import collections.ArraySet;
import collections.Set;

public class AutomatedFootballManager implements FootballManager {

	//Warning: In this class, do not use console for input/output.

	public Set<Player> decideStartingLineUp(FootballClub ownClub, FootballClub opponent) {
		Set<Player> startingSquad = new ArraySet<Player>((ArraySet<Player>) ownClub.getSquad());
		
		// TODO: Fix this method.
		// This method should select an appropriate line up for the match.
		// This is a simple automated football manager and thus it ignores the opponent.
		// The rules are explained in FootballManager.java
		return null; // Remove this line.
	}

	public Set<Player> decideSubstitutePlayers(FootballClub ownClub, FootballClub opponent, Set<Player> startinglineUp) {
		// TODO: Fix this method.
		// This method should return a set of appropriate substitute players.
		// This is a simple automated football manager and thus it ignores the opponent.
		// The rules are explained in FootballManager.java
		return null; // Remove this line.
	}

	public Set<Player> makeSubstitutions(FootballClub ownClub, FootballMatch footballMatch) {
		// TODO: Fix this method.
		// This method should return a new line up for the second half of the match.
		// The rules are explained in FootballManager.java
		
		// In order to get starting line up and substitute players from footballMatch,
		// first determine whether "this" is the manager of home team or away team! 
		// (Assume that the names of the football clubs are unique.)
				
		// Start with the most unsuccessful player
		// and change as many players as possible (but not more than 3 players).
		// In an extreme case only 1 player is possible to change.
		// e.g. when all the substitute players can only play as GK.
		
		// Let the current line up be this:
		//    player1 (plays as DC) -- assume that this player has the less achievement (let's say it is the worst player)
		//    player2 (plays as GK) -- the second worst
		//    player3 (plays as MC) -- the third worst
		//    player4 (plays as DL) -- the fourth worst
		//    player5 (plays as DR) -- the fifth worst
		//    player6 (plays as DC) -- the sixth worst
		//    player7 (plays as ML) -- the seventh worst
		//    player8 (plays as MC) -- the eighth worst
		//    player9 (plays as MR) -- the ninth worst
		//    player10 (plays as FC) -- the tenth worst
		//    player11 (plays as FC) -- the eleventh worst (or the best, having the most achievement)
		// and assume that the substitute players are as below:
		//    player12 (plays as DC)
		//    player13 (plays as DR)
		//    player14 (plays as FC)
		//    player15 (plays as MC)
		//    player16 (plays as FC)
		//    player17 (plays as DC)
		//    player18 (plays as DL)
		
		// In this case, you should exchange player1 with either player12 or player17.
		// Note that there is no available player to exchange with player2, so skip him/her. 
		// Exchange player3 with player15.
		// Exchange player4 with player18.
		// Stop (because there can be at most 3 player substitution during the match).
		
		// It is crucial to NOT sort the players according to their achievements. 
		// Instead, create a local min heap here and use it.
		// That is more efficient!
		return null; // Remove this line.
	}

	
}