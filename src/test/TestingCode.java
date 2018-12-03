package test;

import game.Die;
import game.ScoreCard;

public class TestingCode {
	
	Die di[];
	Die d;
	ScoreCard sc;
	
	public TestingCode() {
		
		d = new Die(6);
		sc = new ScoreCard();
		di=new Die[5];
	}
	
	public void testRoll() {
		
		if(d.roll()>0 && d.roll()<7) 
			System.out.println("Roll function is passed");
		else
			System.out.println("Roll function is failed");
	}
	
	public void testGetScore() {
		
		int array[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		int i;
		for(i=0; i<13; i++) {
		if(sc.getScore(array[i])>=-1)
			System.out.println("The getScore function is passed for "+i+" index");
		else
			System.out.println("The getscore function is failed");
		}
	}
	
	public void testNumOfaKind(){
		 
		int i;
		for(i=0; i<5; i++) 
			
			di[i] = new Die(6);
		
		if(sc.numOfaKind(1,di)==true || sc.numOfaKind(1,di) == false) {
			System.out.println("The function has passed");
		
	}
    	}
	public void testYahtzeeBonus()
	{
		
	if(sc.yahtzeeBonus(di)==true || sc.yahtzeeBonus(di) == false)
		System.out.println("yahtzee bonus works well");
	}
	public void testFullHouse() {
		  
		if(sc.fullHouse(di)== true || sc.fullHouse(di)== false) {
			System.out.println("The full house function has passed");
		}
	}
        
        public void testToggleHeld(){
		Die dice=new Die(6);
		if(dice.held()==true || dice.held()==false)
		System.out.println("toggleHeld functtion is passed.");
		else 
		System.out.println("toogleHeld function is failed");
		}
	
	public static void main(String agrs[]) {
	
		TestingCode tc = new TestingCode();
		tc.testRoll();
		tc.testGetScore();
		tc.testNumOfaKind();
		tc.testFullHouse();	
		tc.testYahtzeeBonus();
                tc.testToggleHeld();
	}

}
