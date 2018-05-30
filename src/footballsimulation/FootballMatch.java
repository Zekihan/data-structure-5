package footballsimulation;

import collections.Dictionary;
import collections.Set;

public class FootballMatch {

	private FootballClub homeClub;
	private FootballManager homeManager;
	private Set<Player> homeStartingLineUp;
	private Set<Player> homeSubstitutePlayers;
	private int homeScore;
	private Dictionary<Integer, Integer> homeAchievements; 

	private FootballClub awayClub;
	private FootballManager awayManager;
	private Set<Player> awayStartingLineUp;
	private Set<Player> awaySubstitutePlayers;
	private int awayScore;
	private Dictionary<Integer, Integer> awayAchievements;
	
	public FootballClub getHomeClub() {
		return homeClub;
	}
	public FootballManager getHomeManager() {
		return homeManager;
	}
	public Set<Player> getHomeStartingLineUp() {
		return homeStartingLineUp;
	}
	public Set<Player> getHomeSubstitutePlayers() {
		return homeSubstitutePlayers;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public Dictionary<Integer, Integer> getHomeAchievements() {
		return homeAchievements;
	}
	public FootballClub getAwayClub() {
		return awayClub;
	}
	public FootballManager getAwayManager() {
		return awayManager;
	}
	public Set<Player> getAwayStartingLineUp() {
		return awayStartingLineUp;
	}
	public Set<Player> getAwaySubstitutePlayers() {
		return awaySubstitutePlayers;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public Dictionary<Integer, Integer> getAwayAchievements() {
		return awayAchievements;
	}
	
	public FootballMatch(FootballClub homeClub, FootballClub awayClub) {

		// TODO: Initialize all the attributes other than 
		// homeStartingLineUp, awayStartingLineUp, homeSubstitutePlayers and awaySubstitutePlayers.
		// Key-value pairs of the dictionaries should consist of 
		// shirt numbers and the corresponding personal scores.
		// All players should start with 0 points as achievements.
		this.homeClub = homeClub;
		this.homeManager = homeClub.getFootballManager;
		this.homeScore = 0;
		this.homeAchievements = homeAchievements;
		this.awayClub = awayClub;
		this.awayManager = awayClub.getFootballManager;
		this.awayScore = 0;
		this.awayAchievements = awayAchievements;
	}

	public MatchResult simulateMatch() {
		
		homeStartingLineUp = homeManager.decideStartingLineUp(homeClub, awayClub);
		homeSubstitutePlayers = homeManager.decideSubstitutePlayers(homeClub, awayClub, homeStartingLineUp);
		
		awayStartingLineUp = homeManager.decideStartingLineUp(awayClub, homeClub);
		awaySubstitutePlayers = homeManager.decideSubstitutePlayers(awayClub, homeClub, awayStartingLineUp);
		// TODO: Assign values to awayStartingLineUp, homeSubstitutePlayers and awaySubstitutePlayers similarly.
		// Write those 3 lines of code very carefully!!
		
		// TODO: Check for those 4 attributes. 
		// If a manager violates a rule, the other club will be the 
		// "winner by default" with a score of 3 against 0.
		// If both managers violates a rule, it is a draw (The score is 0-0).
		
		for(int minute = 0; minute < 45; minute++) {
			
			// TODO: Simulate random scores, assists, etc. for the first half of the match.
			// Achievements: 
			// + Scoring a goal: 20 points
			// + Assist: 10 points
			// + Tackling opponent: 3 points
			// + placed pass: 1 point
			// A random event can be simulated with the following snippet of code:
			// if(Math.random() < 0.3) {
			//    ... // This line of code will run with probability 0.3
			// }
			
			
		}
		
		// Half time simulation
		Set<Player> secondHalfLineUpHome = homeManager.makeSubstitutions(homeClub, this);
		Set<Player> secondHalfLineUpAway = awayManager.makeSubstitutions(awayClub, this);
		// TODO: Check for the rule violations. In case of any violation, apply the same penalties as above.
		
		// Second half
		for(int minutes = 45; minutes < 90; minutes++) {
			// TODO: Simulate the second half.
			// For the new players, you may want to add extra success (because they will have more energy).
			// It is smart to implement a private method and call it both in the first half and in the second half.
		}
		
		// We are keeping it simple: no extra time, no substitutions during the match, no injuries, etc. 
		
		// TODO: Create and return the appropriate MatchResult object.	
		return null; // Remove this line.
	}
	private int randomEvent() {
		if ((22*Math.random())+1< 10) {
			
		}
		if (Math.random() < 0.06) {
			
		}
		if (Math.random() < 0.06) {
			
		}
		if (Math.random() < 0.15) {
			
		}
		if (Math.random() < 0.5) {
			
		}
		
		return 0;
		
	}
	
	// Note: It is strongly recommended to define private helper methods.

}
