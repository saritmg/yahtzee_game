package game;
import java.util.Random;

public class Die {
	private int value, sides;
	private Random rng;
	private boolean held;
	
	public Die(int num) {
		
		sides = num;
		rng = new Random();
		value = roll();
		held = false;
	}
	public int roll() {
		value = rng.nextInt(sides) + 1;
		return value;
	}
	public int getValue() {
		return value;
	}
	
	public boolean held() {
		return held;
	}
	
	public void toogleHeld() {
		held = !held;
	}
}
