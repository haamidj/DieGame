
public class SuperEnemy extends Enemy {

	private int level = 20;
	private double health = level * 10; // change health to double

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double attack(Enemy myEnemy, Player myPlayer) {
		double attackDamage = 0;
		if (myEnemy.cPoint) {
			attackDamage = myEnemy.getLevel() * 2;
			myEnemy.cPoint = false;
		} else {
			attackDamage = myEnemy.getLevel();
		}
		if (myPlayer.dPoint) {
			attackDamage *= .1;
			myPlayer.dPoint = false;
		}
	return attackDamage;
	}
}
