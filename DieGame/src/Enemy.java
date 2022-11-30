
public class Enemy {
	private int level = 10;
	private double health = level * 10;
	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	boolean cPoint;
	boolean dPoint; // defends condition

	public double attack(Enemy myEnemy, Player myPlayer) {
		double attackDamage = 0;
		if (myEnemy.cPoint) {
			attackDamage = myEnemy.getLevel() * 2;
			myEnemy.cPoint = false;
		} else {
			attackDamage = myEnemy.getLevel();
		}
		if(myPlayer.dPoint) {
			attackDamage *= .1;
			myPlayer.dPoint = false;
		}
	return attackDamage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void defend(Enemy myEnemy) {
		myEnemy.dPoint = true;
	}
	
	public void special(Enemy myEnemy) {
		myEnemy.cPoint = true;
	}
	

}
