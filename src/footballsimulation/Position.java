package footballsimulation;

public enum Position {
	
		FC,
		
	ML,	MC,	MR,
	
	DL,	DC,	DR,
	
		GK
		
}

/*

Example usage of this enum:

	Position myFavoritePosition = Position.DL;
	
	Position[] allPositions = Position.values();
	int randomIndex = (int)(Math.random() * allPositions.length);
	Position randomPosition = allPositions[randomIndex];

	if(myFavoritePosition == randomPosition) {
		System.out.println("Two positions are the same");
	}
	 
*/