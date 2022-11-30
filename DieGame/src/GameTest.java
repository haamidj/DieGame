import java.util.Arrays;
import java.util.Scanner;
import java.security.SecureRandom;
public class GameTest extends Player {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SecureRandom die = new SecureRandom();
		Player myPlayer = new Player();
		Board myBoard = new Board();
		Game myGame = new Game();
		
		System.out.println("Begin? ");
		String begin = input.next();
		myBoard.turnCount++;
		
		myGame.positionCheck(myBoard, myPlayer, input, die);
		
	}// end of main()
}// end of class
