/**
 * @author Dhyey Patel
 *
 *BoardGame is a class used to set up the backend of the game and handle the HashDictionary 
 *
 */

public class BoardGame {
	private int emptyPos, maxLvl, boardSize;
	private char[][] gameBoard;
	
	// The constructor for this class is used to create the 2D array of the given size 
	// It also makes every value of the 2D array equal to 'g'
	public BoardGame(int board_size, int empty_positions, int max_levels) {
		boardSize = board_size;
		gameBoard = new char[board_size][board_size];
		for (int i=0; i<boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				gameBoard[i][j] = 'g'; 
			}
		}
		emptyPos = empty_positions;
		maxLvl = max_levels;
	}
	
	// This is a public method that creates the dictionary of size 10000 
	public HashDictionary makeDictionary() {
		HashDictionary table = new HashDictionary(7919);
		return table;
	}
	
	// This is a public method that checks if the configuration is in the given HashDictionary
	// If it is located in the HashDictionary it returns the score otherwise it return -1
	public int isRepeatedConfig(HashDictionary dict) {
		String stringConfig = createConfig();
		return dict.getScore(stringConfig);
	}
	
	//This is a public method that puts the current configuration into the given HashDictionary with the given score
	public void putConfig(HashDictionary dict, int score) {
		String stringConfig = createConfig();
		Configuration temp = new Configuration(stringConfig, score);
		dict.put(temp);		
	}
	
	// This is a public method that is used to record the move by putting the symbol in the given position
	public void savePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	// This is a public method to see if a spot is empty
	public boolean positionIsEmpty(int row, int col) {
		return (gameBoard[row][col] == 'g');
	}
	
	// This is a public method that is used to see if a computer tile is occupying the tile in the given position
	public boolean tileOfComputer (int row, int col) {
		return (gameBoard[row][col] == 'o');
	}
	
	// This is a public method that is used to see if a human tile is occupying the tile in the given position
	public boolean tileOfHuman (int row, int col) {
		return (gameBoard[row][col] == 'b');
	}
	
	// This is a public method that is used to see if the given symbol wins the game or not
	public boolean wins (char symbol) {
		boolean check;
		int j;
		// First the algorithm goes through all the rows and sees if all the tiles in any given row are of the given symbol
		for (int i=0; i<boardSize; i++) {
			check = true;
			j=0;
			while(j<boardSize && check) {
				if (gameBoard[i][j] != symbol) {
					check = false;
				}
				j++;
			}
			if (check) {
				return true;
			}
		}
		// Then the the algorithm goes through all the columns and sees if all the tiles in any given column are of the given symbol
		for (int i=0; i<boardSize; i++) {
			check = true;
			j=0;
			while(j<boardSize && check) {
				if (gameBoard[j][i] != symbol) {
					check = false;
				}
				j++;
			}
			if (check) {
				return true;
			}
		}
		// After checking all the rows and columns we need to check for the two diagonal possibilities
		// This part of the algorithm check the top right to bottom left diagonal 
		j=0;
		check = true;
		while(j<boardSize && check) {
			if (gameBoard[j][j] != symbol) {
				check = false;
			}
			j++;
		}
		if (check) {
			return true;
		}
		// This part checks the bottom right to top left diagonal 
		j=1;
		check = true;
		while(j<=boardSize && check) {
			if (gameBoard[boardSize-j][j-1] != symbol) {
				check = false;
			}
			j++;
		}
		if (check) {
			return true;
		}
		// If there are no matches found then it returns true
		return false;
	}
	
	// This is a public method that is used to determine if the game is a draw given the symbols and empty positions 
	public 	boolean isDraw(char symbol, int empty_positions) {
		String stringConfig = createConfig();
		int counter=0;
		boolean check;
		// First it will calculate the number of open tiles
		for (int i=0; i<stringConfig.length(); i++) {
			if (stringConfig.charAt(i) == 'g') {
				counter++;
			}
		}
		// If there are more open tiles than empty positions than it cannot be a draw, and returns false
		if (counter>empty_positions) {
			return false;
		}
		// If there are no empty tiles, and no one is a winner than it has to be a draw
		else if (counter == 0) {
			if (wins('o') || wins('b')) {
				return false;
			}
			return true;
		}
		// Now the algorithm must locate all the empty tiles and see if the given player can make a move
		else {
			for (int i=0; i<boardSize; i++) {
				for (int j=0; j<boardSize; j++) {
					if (gameBoard[i][j]=='g') {
						// For each of the empty tiles, we need to check all 8 positions around it 
						check = false;
						for (int newi=i-1; newi<i+2; newi++) {
							for (int newj=j-1; newj<j+2; newj++) {
								// Check the position as long as it is not out of bounds 
								if (newi>=0 && newi<boardSize && newj>=0 && newj<boardSize) {
									// If the player can move than change check to true
									if (gameBoard[newi][newj] == symbol) {
										check = true;
									}
								}
							}
						}
						// If a player can move than return false
						if (check) {
							return false;
						}
					}
				}
			}
		}
		// If the given player cannot move than it has to be a draw
		return true;	
	}
	
	// This is a public method to assign a score to the position given the next turn and the number of empty positions
	public int evalBoard(char symbol, int empty_positions) {
		// If the computer wins than return 3
		if (wins('o')) {
			return 3;
		}
		// If the human wins than return 0
		else if (wins('b')) {
			return 0;
		}
		// If it is a draw than return 2
		else if (isDraw(symbol, empty_positions)) {
			return 2;
		}
		// If the outcome of the game is undecided return 1
		else {
			return 1;
		}
	}
	
	// This is a private method used to convert the configuration of the board into a string
	private String createConfig() {
		String stringConfig = "";
		for (int i=0; i<boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				stringConfig = stringConfig + gameBoard[j][i]; 
			}
		}
		return stringConfig;
	}
	
	
}
