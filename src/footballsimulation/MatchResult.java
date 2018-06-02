package footballsimulation;

public class MatchResult {

	private int scoreOfHomeTeam;
	private int scoreOfAwayTeam;
	private Player playerOfTheMatch; // The player with the highest personal score.
	
	public MatchResult(int scoreOfHomeTeam, int scoreOfAwayTeam, Player playerOfTheMatch) {

		// TODO: Fill the attributes.
		this.scoreOfHomeTeam = scoreOfHomeTeam;
		this.scoreOfAwayTeam = scoreOfAwayTeam;
		this.playerOfTheMatch = playerOfTheMatch;
		
	}
	
	// TODO: Define getters.
	public int getScoreOfHomeTeam() {
		return scoreOfHomeTeam;
	}
	public int getScoreOfAwayTeam() {
		return scoreOfAwayTeam;
	}
	public Player getPlayerOfTheMatch() {
		return playerOfTheMatch;
	}
}
