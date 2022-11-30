import java.util.Scanner;
import java.security.SecureRandom;

public class Game {
	public void positionCheck(Board myBoard, Player myPlayer, Scanner input, SecureRandom die) {
		while (myBoard.turnCount > 0 && myPlayer.position <= myBoard.totalBoardPieces) { // while the game is in progress
			System.out.println("Turn: " + myBoard.turnCount); // print the turn
			myPlayer.position += myPlayer.move(die); // move the player and add it the playerPosition
			
			if(myPlayer.position >= myBoard.totalBoardPieces){// FIXME add a position lock to final board piece
				myPlayer.position = myBoard.totalBoardPieces; // prevents player from exceeding max position
			}
				
			System.out.println("Your Position: " + myPlayer.position); // Print the position
		
			for (int i = 0; i < myBoard.enemyPosition.length; i++) { // create a loop to check position
				if (myPlayer.position == myBoard.enemyPosition[i]) { // check if player position landed on enemy
					System.out.println("Enemy encountered on tile: " + myPlayer.position);
					System.out.println();
					System.out.flush();
					Enemy myEnemy = new Enemy(); // create an enemy
					System.out.println("Enemy level is: " + myEnemy.getLevel());
					if(!enemyEncounter(myPlayer, input, myBoard, myEnemy)) { // trigger enemy encounter method and check if player won
						myBoard.turnCount = -1;
					}
					break; // end loop check
				} else if (myPlayer.position == myBoard.friendlyPosition[i]) { // check if player landed friendly
					System.out.println("Friendly encountered on tile: " + myPlayer.position);
					Shop myShop = new Shop(); //creates a shop
					myShop.showShopItems(input, myPlayer); //shows the shops inventory
					System.out.println();
					System.out.flush();
					break;// if found end loop
				} else if (myPlayer.position == myBoard.bossPosition[i]) {
					System.out.println("Enemy Boss encountered on tile: " + myPlayer.position);
					System.out.println();
					System.out.flush();
					Enemy enemyBoss = new Enemy(); 
					enemyBoss.setLevel(20);
					enemyBoss.setHealth(enemyBoss.getLevel() * 10);
					System.out.println("Enemy Boss level is: " + enemyBoss.getLevel());
					
					if(!enemyEncounter(myPlayer, input, myBoard, enemyBoss)) { // trigger enemy encounter method and check if player won
						myBoard.turnCount = -1;
					}else {
						System.out.println("--------------------------------------------------------------");
						System.out.println("\t\tCongratulations! You Won the Game!");
						System.out.println("--------------------------------------------------------------");
						myBoard.turnCount = -1;
					}
					
				}//end of position checking
			} // end of for
			System.out.println();
			
			System.out.println("Continue? "); // ask player if they want to conitnue
			char cont = input.next().charAt(0); // take the first letter of yes or no
			System.out.flush();
			
			switch (cont) { // check if yes or no FIXME
			case 'y':
				myBoard.turnCount++; // increase turncount restart while loop
				break; 
			case 'n':
				System.out.println("Forfeit");// display forfiet
				myBoard.turnCount = -1;// end while loop
				break;
			}// end of switch
			System.out.println("--------------------------------------------------------------");
		} // end of while
		if(myBoard.turnCount == -1) {
			System.out.println("Game Over! ");
		} else if(myBoard.turnCount > myBoard.totalBoardPieces) {
			System.out.println("You won!");
		}
		
	}// end of position check

	public boolean enemyEncounter(Player myPlayer, Scanner input, Board myBoard, Enemy myEnemy) { 
		boolean battleVictory = false; // creates a victory condition
//		myEnemy.displayEnemy(myPlayer.position, myBoard); //Choose and display the enemy 
		System.out.println("\t\t\tBattle Start");
		boolean battleOngoing = true;
		
		while (battleOngoing) {
		System.out.println();
		System.out.println("Turn: " + myBoard.turnCount);
		System.out.println("\t1> Fight");
		System.out.println("\t2> Defend");
		System.out.println("\t3> Special");
//		System.out.println("\t4> Item");
		System.out.println();
		System.out.flush();
		System.out.println("Pick your move: "); // asks user what they will do
		int moveChoice = input.nextInt();
		System.out.println();
		System.out.flush();
		
			switch (moveChoice) { // do something in response to the user choice
			case 1:
				
				System.out.println("You Chose to Fight");
				enemyDoSomething(myEnemy, myPlayer, myBoard);
				
				double health = myEnemy.getHealth();
				health -= myPlayer.attack(myPlayer, myEnemy);
				myEnemy.setHealth(health);
				
				if(myEnemy.getHealth() > 0) {
					System.out.println();
					System.out.println("Enemy health is:" + myEnemy.getHealth());
				} else {
					System.out.println();
					System.out.println("Enemy health is: 0");
				}
				
				// enemy does something
				if (myEnemy.getHealth() <= 0 || myPlayer.health <= 0) { //if the enemy dies or player dies the battle is ended
					battleOngoing = false;
				}
				myBoard.turnCount++;
				break;
			case 2:
				
				System.out.println("You Chose to Defend");
				System.out.flush();
				
				myPlayer.defend(myPlayer, myEnemy);//call myPlayer defend method
				enemyDoSomething(myEnemy, myPlayer, myBoard);
				
				myBoard.turnCount++;
				
				break;
			case 3:
				System.out.println("Charging next attack!");
				System.out.flush();
				myPlayer.special(myPlayer);
				enemyDoSomething(myEnemy, myPlayer, myBoard);
				if (myEnemy.getHealth() <= 0 || myPlayer.health <= 0) { //if the enemy dies or player dies the battle is ended
					battleOngoing = false;
				}
				myBoard.turnCount++;
				break;
//			case 4:
//				System.out.println("Opening Inventory...");
//				System.out.flush();
//				myPlayer.checkInventory(myPlayer, input, myBoard);//call myPlsyer inventorycheck method
//				break;
//
			}// end of switch
		} // end of while battle is ongoing
		if (myEnemy.getHealth() <= 0 || myPlayer.health <= 0) { //if the enemy dies or player dies the battle is ended
			if(myPlayer.health <= 0){
				System.out.println("You Died! ");
				battleVictory = false;
			} else {
				myPlayer.level++;
				System.out.println("You leveled up to level: " + myPlayer.level);
				battleVictory = true;
			}
			battleOngoing = false;
		}
	return battleVictory;
	}// end of enemyEncounter
	
	

	public void enemyDoSomething(Enemy myEnemy, Player myPlayer, Board myBoard) { // method to decide enemy choice
		SecureRandom die = new SecureRandom();// randomize enemy choice
		
		int enemyChoice = die.nextInt(3) + 1;
		if(myEnemy.cPoint) { //guarentees enemy attacks if it has charged the previous turn
			enemyChoice = 1;
		}
		switch (enemyChoice) {
		case 1:
			System.out.println("Enemy attacking!");
			myPlayer.health -= myEnemy.attack(myEnemy, myPlayer);
			if(myPlayer.health > 0) {
				System.out.println();
				System.out.println("Your health is: " + myPlayer.health);
			}else {
				System.out.println();
				System.out.println("Your health is: 0");
			}
			break;
		case 2:
			System.out.println("Enemy defending!");
			myEnemy.defend(myEnemy);
			break;
		case 3:
			System.out.println("Enemy charging attack!");
			myEnemy.special(myEnemy);
			break;
		default:
			System.out.println("Enemy is dazed!");
			break;
		}
	}//end of enemyDoSomething()
	
	

}// end of class
