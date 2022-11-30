import java.util.Scanner;

public class Shop {

	public void showShopItems(Scanner input, Player myPlayer) {
		System.out.println("Shop Items: ");
		System.out.println("1) Health Potion ");
		System.out.println("2) Strength Potion ");
		System.out.println("3) Defense Potion");
		int playerChoice = input.nextInt();
		System.out.println();
		
		switch (playerChoice) {
		case 1:
			System.out.println("Regaining health");
			myPlayer.health = myPlayer.level * 10;
			System.out.println("Your health is: " + myPlayer.health);
			break;
		case 2:
			System.out.println("Increasing Attack");
			myPlayer.cPoint = true;
			System.out.println("Charge point is: " + myPlayer.cPoint);
			break;
		case 3:
			System.out.println("Increasing Defense");
			myPlayer.dPoint = true;
			System.out.println("Defense point is: " + myPlayer.dPoint);
			break;
		default:
			break;

		}
	}
}
