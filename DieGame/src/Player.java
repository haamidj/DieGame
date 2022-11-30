import java.security.SecureRandom;
import java.util.Scanner;

public class Player {
	int level = 10;
	double health = level * 10; // health is 10 times the level
	int magicPoints;
	int position;
	int healthPotions;// inventory item
	boolean cPoint; // charge point
	boolean dPoint; // defense point
	int wins = 0;

	public int move(SecureRandom die) { // players position is randomize everytime the die is rolled
		position += die.nextInt(3);

		return position;
	}

	public double attack(Player myPlayer, Enemy myEnemy) { // attack() decreases the enemy's health
		double attackDamage = 0; // attack damage is created and set to zero
		if (myPlayer.cPoint) { // checks if player has charged their attack
			attackDamage = level * 2; // if they have their attack damage if doubled
			myPlayer.cPoint = false; // charge point is set back to zero
		} else { // if the player has not charge attack will work as normal
			attackDamage = level; //attack damage is the same as player level
		}
		if (myEnemy.dPoint) { //checks if eenmy has defended
			attackDamage *= .1; //if enemy has defended the attack does 0.1 of its orignal value FIXME
			myEnemy.dPoint = false; // resets enemys defense
		}
		return attackDamage; //return the attack damage
	}

	public void defend(Player myPlayer, Enemy myEnemy) {
		myPlayer.dPoint = true;

	}

	public void special(Player myPlayer) {
		myPlayer.cPoint = true;
	}

//	public void checkInventory(Player myPlayer, Scanner input, Board myBoard) {
//		System.out.println("0> Close Inventory");
//		System.out.println("1> Health Potions: " + myPlayer.healthPotions);
//		System.out.println();
//		System.out.println("Choose Item to Use");
//		int itemChoice = input.nextInt();
//			switch(itemChoice) {
//			case 0:
//				break;
//			case 1:
//				System.out.println("...Using Health Potion");
//				if(myPlayer.healthPotions > 0) {
//					myPlayer.health = health;
//					System.out.println("Health replenished");
//					myPlayer.healthPotions--;
//					myBoard.turnCount++;
//				}else {
//					System.out.println("You have no health potions");
//				}
//				break;
//			default:
//				break;
//			}
//	}

}
