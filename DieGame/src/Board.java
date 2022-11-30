
public class Board {
	int totalBoardPieces = 15;
	int[] enemyPosition = {21,23,25,29,212,215};
	int[] bossPosition = {15,150,150,150,150,150}; 
	//adding padding to array to prevent it from iterating through elements that dont exist
	int[] friendlyPosition = {4,6,8,11,13,14};
	int turnCount = 0;
}
