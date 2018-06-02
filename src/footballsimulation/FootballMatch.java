package footballsimulation;

import java.util.Iterator;

import collections.ArrayDictionary;
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
		this.homeManager = homeClub.getManager();
		this.homeScore = 0;
		Player[] team = homeClub.getSquad().toArray();
		Dictionary<Integer, Integer> tempHome = new ArrayDictionary<Integer, Integer>();
		for (int i = 0; i < team.length; i++) {
			tempHome.add(team[i].getShirtName(),0);
		}
		this.homeAchievements = tempHome;
		this.awayClub = awayClub;
		this.awayManager = awayClub.getManager();
		this.awayScore = 0;
		Dictionary<Integer, Integer> tempAway = new ArrayDictionary<Integer, Integer>();
		for (int i = 0; i < team.length; i++) {
			tempAway.add(team[i].getShirtName(),0);
		}
		this.awayAchievements = tempAway;
	}

	public MatchResult simulateMatch() {
		
		homeStartingLineUp = homeManager.decideStartingLineUp(homeClub, awayClub);
		homeSubstitutePlayers = homeManager.decideSubstitutePlayers(homeClub, awayClub, homeStartingLineUp);
		
		awayStartingLineUp = awayManager.decideStartingLineUp(awayClub, homeClub);
		awaySubstitutePlayers = awayManager.decideSubstitutePlayers(awayClub, homeClub, awayStartingLineUp);
		// TODO: Assign values to awayStartingLineUp, homeSubstitutePlayers and awaySubstitutePlayers similarly.
		// Write those 3 lines of code very carefully!!
		boolean violates = false;
		MatchResult result;
		if (checkStartingTeam(homeStartingLineUp)||checkSubstituteTeam(homeSubstitutePlayers)) {
			awayScore = 3;
			violates = true;
		}
		if (checkStartingTeam(awayStartingLineUp)||checkSubstituteTeam(awaySubstitutePlayers)) {
			homeScore = 3;
			violates = true;
		}
		if ((homeScore == 3)&&(awayScore == 3)) {
			homeScore = 0;
			awayScore = 0;
		}
		result = new MatchResult(homeScore, awayScore, null);
		// TODO: Check for those 4 attributes. 
		// If a manager violates a rule, the other club will be the 
		// "winner by default" with a score of 3 against 0.
		// If both managers violates a rule, it is a draw (The score is 0-0).
		if (!violates) {
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
				randomEvent(homeStartingLineUp, awayStartingLineUp);
				
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
				randomEvent(secondHalfLineUpHome, secondHalfLineUpAway);
			}
			
			// We are keeping it simple: no extra time, no substitutions during the match, no injuries, etc. 
			
			// TODO: Create and return the appropriate MatchResult object.
			Integer[] home = findMax(homeAchievements);
			Integer[] away = findMax(awayAchievements);
			
			Player playerOfTheMatch ;
			
			Integer maxKey = home[0];
			if (home[1] < away[1]) {
				maxKey = away[1];
				playerOfTheMatch = getPlayer(homeClub.getSquad(), maxKey);
			}
			else {
				playerOfTheMatch = getPlayer(awayClub.getSquad(), maxKey);
			}
			
			result = new MatchResult(homeScore, awayScore, playerOfTheMatch);
		}
		return result; // Remove this line.
	}
	private boolean checkStartingTeam(Set<Player> startingTeam) {
		boolean rule = false;
		Player[] team = startingTeam.toArray();
		if (team.length != 12) {
			rule = true;
		}
		int gk = 0, dl = 0, dc = 0, dr = 0, ml = 0, mc = 0, mr = 0, fc = 0;
		for (int i = 0; i < team.length; i++) {
			Player player = team[i];
			if (player.getPosition() == Position.GK) {
				gk += 1;
			}
			if (player.getPosition() == Position.DL) {
				dl += 1;
			}
			if (player.getPosition() == Position.DC) {
				dc += 1;
			}
			if (player.getPosition() == Position.DR) {
				dr += 1;
			}
			if (player.getPosition() == Position.MC) {
				mc += 1;
			}
			if (player.getPosition() == Position.ML) {
				ml += 1;
			}
			if (player.getPosition() == Position.MR) {
				mr += 1;
			}
			if (player.getPosition() == Position.FC) {
				fc += 1;
			}
		}
		if ((gk != 1)&&(dl != 1)&&(dc != 2)&&(dr != 1)&&(mc != 2)&&(ml != 1)&&(mr != 1)&&(fc != 2)) {
			rule = true;
		}
		return rule;
	}
	private boolean checkSubstituteTeam(Set<Player> startingTeam) {
		boolean rule = false;
		Player[] team = startingTeam.toArray();
		if (team.length != 12) {
			rule = true;
		}
		return rule;
	}
	private Integer[] findMax(Dictionary<Integer, Integer> achievement) {
		Iterator<Integer> achievementValue = achievement.getValueIterator();
		Integer achievementMaxValue = 0, achievementKeyMax = 0, tempAchievementKey = 0, tempAchievementValue = 0;
		while (achievementValue.hasNext()) {
			tempAchievementValue = achievement.getValueIterator().next();
			tempAchievementKey = achievement.getKeyIterator().next();
			if (tempAchievementValue > achievementMaxValue) {
				achievementMaxValue = tempAchievementValue;
				achievementKeyMax = tempAchievementKey;
			}
		}
		Integer result[] = new Integer[2];
		result[0] = achievementKeyMax;
		result[1] = achievementMaxValue;
		return result;
	}
	private void randomEvent(Set<Player>homeTeam, Set<Player>awayTeam) {
		double rand = Math.random();
		if (rand <= 0.5) {
			
			Player player = randomPlayer(homeTeam);
			if (Math.random() < 0.06) {
				if (randomGoal(player)) {
					homeScore++;
					Integer tempvalue = homeAchievements.remove(player.getShirtName()) ;
					tempvalue += 20;
					homeAchievements.add(player.getShirtName(), tempvalue);	
				}
				
			}
			//assist
			if (Math.random() < 0.06) {
				if (randomAssist(player)) {
					Integer tempvalue = homeAchievements.remove(player.getShirtName()) ;
					tempvalue += 10;
					homeAchievements.add(player.getShirtName(), tempvalue);	
				}
			}
			//tackle
			if (Math.random() < 0.15) {
				if (randomTackle(player)) {
					Integer tempvalue = homeAchievements.remove(player.getShirtName()) ;
					tempvalue += 2;
					homeAchievements.add(player.getShirtName(), tempvalue);	
				}
			}
			//pass
			if (Math.random() < 0.5) {
				if (randomPass(player)) {
					Integer tempvalue = homeAchievements.remove(player.getShirtName()) ;
					tempvalue += 1;
					homeAchievements.add(player.getShirtName(), tempvalue);	
				}
			}
		}
		//away
		if (rand > 0.5) {
			
			Player player = randomPlayer(awayTeam);
			if (Math.random() < 0.06) {
				if (randomGoal(player)) {
					awayScore++;
					Integer tempvalue = awayAchievements.remove(player.getShirtName()) ;
					tempvalue += 20;
					awayAchievements.add(player.getShirtName(), tempvalue);	
				}
				
			}
			//assist
			if (Math.random() < 0.06) {
				if (randomAssist(player)) {
					Integer tempvalue = awayAchievements.remove(player.getShirtName()) ;
					tempvalue += 10;
					awayAchievements.add(player.getShirtName(), tempvalue);	
				}
			}
			//tackle
			if (Math.random() < 0.15) {
				if (randomTackle(player)) {
					Integer tempvalue = awayAchievements.remove(player.getShirtName()) ;
					tempvalue += 2;
					awayAchievements.add(player.getShirtName(), tempvalue);	
				}
			}
			//pass
			if (Math.random() < 0.5) {
				if (randomPass(player)) {
					Integer tempvalue = awayAchievements.remove(player.getShirtName()) ;
					tempvalue += 1;
					awayAchievements.add(player.getShirtName(), tempvalue);	
				}
			}
		}
		
	}
	private Player randomPlayer(Set<Player> team) {
			int rand = (int) ((Math.random()*11)+1);
			Player[] arrayTeam = team.toArray();
			Player player = arrayTeam[rand];
		return player;
	}
	private Player getPlayer(Set<Player> team,Integer key) {
		Player[] arrayTeam = team.toArray();
		Player player = null;
		for (int i = 0; i < arrayTeam.length; i++) {
			if (arrayTeam[i].hashCode() == key) {
				player = arrayTeam[i];
			}
		}
		return player;
	}
	private boolean randomGoal(Player player) {
		boolean goal = false;
		int rand = (int) Math.random();
		if (player.getPosition() == Position.GK) {
			if (rand <= 0.0005) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.DL) {
			if (rand <= 0.004) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.DC) {
			if (rand <= 0.005) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.DR) {
			if (rand <= 0.004) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.MC) {
			if (rand <= 0.06) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.ML) {
			if (rand <= 0.05) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.MR) {
			if (rand <= 0.06) {
				goal = true;
			}
		}
		if (player.getPosition() == Position.FC) {
			if (rand <= 0.5) {
				goal = true;
			}
		}
		return goal;
	}
	private boolean randomAssist(Player player) {
		boolean assist = false;
		int rand = (int) Math.random();
		if (player.getPosition() == Position.GK) {
			if (rand <= 0.005) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.DL) {
			if (rand <= 0.05) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.DC) {
			if (rand <= 0.05) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.DR) {
			if (rand <= 0.05) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.MC) {
			if (rand <= 0.5) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.ML) {
			if (rand <= 0.5) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.MR) {
			if (rand <= 0.5) {
				assist = true;
			}
		}
		if (player.getPosition() == Position.FC) {
			if (rand <= 0.5) {
				assist = true;
			}
		}
		return assist;
	}
	private boolean randomTackle(Player player) {
		boolean tackle = false;
		int rand = (int) Math.random();
		if (player.getPosition() == Position.GK) {
			if (rand <= 0.005) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.DL) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.DC) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.DR) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.MC) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.ML) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.MR) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		if (player.getPosition() == Position.FC) {
			if (rand <= 0.5) {
				tackle = true;
			}
		}
		return tackle;
	}
	private boolean randomPass(Player player) {
		boolean pass = false;
		int rand = (int) Math.random();
		if (player.getPosition() == Position.GK) {
			if (rand <= 0.005) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.DL) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.DC) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.DR) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.MC) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.ML) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.MR) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		if (player.getPosition() == Position.FC) {
			if (rand <= 0.5) {
				pass = true;
			}
		}
		return pass;
	}
	
				
			
	// Note: It is strongly recommended to define private helper methods.

}
