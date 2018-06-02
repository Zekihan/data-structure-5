package footballsimulation;


import java.util.Iterator;

import collections.ArrayMinHeap;
import collections.ArraySet;
import collections.Dictionary;
import collections.MinHeap;
import collections.Set;
import utils.ComparablePair;

public class AutomatedFootballManager implements FootballManager {
	//Warning: In this class, do not use console for input/output.

	public Set<Player> decideStartingLineUp(FootballClub ownClub, FootballClub opponent) {
		
		Player[] squadArr = ownClub.getSquad().toArray();
		Set<Player> startingSquad = new ArraySet<Player>();
		
		int GkCount = 0; int DlCount = 0; int DcCount = 0; int DrCount = 0; int MlCount = 0; int McCount = 0; int MrCount = 0; int FcCount = 0;
		for(Player player : squadArr)
		{
			if(GkCount != 1)
			{
				if (player.getPosition() == Position.GK)
				{
					startingSquad.add(player);
					GkCount++;
					continue;
				}
			}
			if(DlCount != 1)
			{
				if (player.getPosition() == Position.DL)
				{
					startingSquad.add(player);
					DlCount++;
					continue;
				}
			}
			if(DcCount != 2)
			{
				if (player.getPosition() == Position.DC)
				{
					startingSquad.add(player);
					DcCount++;
					continue;
				}
			}
			if(DrCount != 1)
			{
				if (player.getPosition() == Position.DR)
				{
					startingSquad.add(player);
					DrCount++;
					continue;
				}
			}
			if(MlCount != 1)
			{
				if (player.getPosition() == Position.ML)
				{
					startingSquad.add(player);
					MlCount++;
					continue;
				}
			}
			if(McCount != 2)
			{
				if (player.getPosition() == Position.MC)
				{
					startingSquad.add(player);
					McCount++;
					continue;
				}
			}
			if(MrCount != 1)
			{
				if (player.getPosition() == Position.MR)
				{
					startingSquad.add(player);
					MrCount++;
					continue;
				}
			}
			if(FcCount != 2)
			{
				if (player.getPosition() == Position.FC)
				{
					startingSquad.add(player);
					FcCount++;
					continue;
				}
			}
		}
		// TODO: Fix this method.
		// This method should select an appropriate line up for the match.
		// This is a simple automated football manager and thus it ignores the opponent.
		// The rules are explained in FootballManager.java
		return startingSquad; // Remove this line.
	}

	public Set<Player> decideSubstitutePlayers(FootballClub ownClub, FootballClub opponent, Set<Player> startinglineUp) {
		
		Player[] startingArr = startinglineUp.toArray();
		Set<Player> substitutes = new ArraySet<Player>((ArraySet<Player>) ownClub.getSquad());
		
		for (Player player:startingArr)
		{
			substitutes.remove(player);
		}
		while (substitutes.size() != 7)
		{
			substitutes.remove();
		}
		
		
		// TODO: Fix this method.
		// This method should return a set of appropriate substitute players.
		// This is a simple automated football manager and thus it ignores the opponent.
		// The rules are explained in FootballManager.java
		return substitutes; // Remove this line.
	}

	public Set<Player> makeSubstitutions(FootballClub ownClub, FootballMatch footballMatch) {

		if (ownClub.equals(footballMatch.getHomeClub()))
		{
			return makeSubstitutions(footballMatch.getHomeAchievements(),footballMatch.getHomeStartingLineUp(),footballMatch.getHomeSubstitutePlayers());
		}
		else 
		{
			return makeSubstitutions(footballMatch.getAwayAchievements(),footballMatch.getAwayStartingLineUp(),footballMatch.getAwaySubstitutePlayers());
		}

		
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

	}
	private Set<Player> makeSubstitutions(Dictionary<Integer,Integer> achievements, Set<Player> oldLineup, Set<Player> substitutePlayers)
	{
		Iterator<Integer> KeyIt = achievements.getKeyIterator();
		Iterator<Integer> ValIt = achievements.getValueIterator();
		
		MinHeap<ComparablePair<Integer,Integer>> minHeap = new ArrayMinHeap<ComparablePair<Integer,Integer>>(11);
		while(KeyIt.hasNext())
		{
			ComparablePair<Integer,Integer> pair = new ComparablePair<Integer,Integer>(KeyIt.next(),ValIt.next());
			minHeap.add(pair);
		}
		
		int substitutions = 0;
		Player[] oldLineupArr = oldLineup.toArray();
		Player[] substitutePlayersArr = substitutePlayers.toArray();
		Set<Player> newLineup = new ArraySet<Player>((ArraySet<Player>) oldLineup);
		
		while(substitutions < 3 && !minHeap.isEmpty()) {
			
			ComparablePair<Integer,Integer> worstPlayerPair = minHeap.removeMin();
			Player worstPlayer = null;
			for(Player player:oldLineupArr)
			{
				if(player.hashCode() == worstPlayerPair.getKey())
				{
					worstPlayer = player;
					break;
				}
			}
			
			for(Player player:substitutePlayersArr)
			{
				if(player.getPosition() == worstPlayer.getPosition())
				{
					newLineup.remove(worstPlayer);
					newLineup.add(player);
					substitutePlayers.remove(player);
					substitutions++;
					break;
				}
				
			}
		}
	
	return newLineup;
	}

}