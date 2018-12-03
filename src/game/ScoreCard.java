package game;

public class ScoreCard {
	
	private int[] scores;
	
	public ScoreCard() {
		
		scores = new int[15];
		
		for(int i=0; i<15; i++) {
			scores[i] = -1;
		}
	}
	public void setScore(int n, int score) {
		scores[n] = score;
	}
	
	public int getScore(int n) {
		return scores[n];
	}
	
	public int upperScore() {
		int upperscore = 0;
		for(int i = 0; i<6; i++) 
			if(scores[i] != -1)
				upperscore +=scores[i];
			return upperscore;
		}
		public int bonusScore() {
			if(bonus())
				return 35;
			return 0;
		}
		
		public int totalUpperscore() {
			return upperScore() + bonusScore();
		}
		public int lowerScore() {
			
			int lowerscore = 0;
			for(int i=0; i<scores.length; i++)
				if(scores[i] != -1) 
					lowerscore += scores[i];
					
			return lowerscore;
				}
			public int grandTotal() {
				return upperScore() + lowerScore();
			}
			public boolean bonus() {
				return upperScore() >= 63;
			}
			public boolean filled() {
				boolean ans = true;
				for(int i=0; i<13; i++) {
					if(scores[i] == -1)
						ans = false;
				}
				return ans;
			}
			public boolean num(int n, Die[] dice) {
				boolean ans = false;
				for(int i=0; i<5; i++)
					if(dice[i].getValue()==n)
						ans = true;
						return ans;
			}
			public boolean numOfaKind(int n, Die[] dice) {
				boolean ans = false;
				int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
				
				for(int i=0; i<5; i++) {
					if(dice[i].getValue()==1)
						ones++;
					if(dice[i].getValue()==2)
						twos++;
					if(dice[i].getValue()==3)
						threes++;
					if(dice[i].getValue()==4)
						fours++;
					if(dice[i].getValue()==5)
						fives++;
					if(dice[i].getValue()==6)
						sixes++;
				}
				ans = (ones>=n || (twos>=n) || (threes>=n) || (fours>=n) || (fours>=n) || (fives>=n) || 
                                        (sixes>=n));
				return ans;
			}
			public boolean fullHouse(Die[] dice) {
				boolean ans = false;
				int ones=0,twos=0, threes=0, fours=0, fives=0, sixes=0;
				for(int i=0; i<5; i++) {
					if(dice[i].getValue()==1)
						ones++;
					if(dice[i].getValue()==2)
						twos++;
					if(dice[i].getValue()==3)
						threes++;
					if(dice[i].getValue()==4)
						fours++;
					if(dice[i].getValue()==5)
						fives++;
					if(dice[i].getValue()==6)
						sixes++;
				}
				ans = (ones == 3 && (twos == 2 || threes == 2 || fours == 2 ||
						fives == 2 || sixes == 2)) || (twos == 3 && (ones == 2 ||
						threes == 2 || fours == 2 || fives == 2 || sixes == 2)) ||
						(threes == 3 && (ones == 2 || twos == 2 || fours == 2 ||
						fives == 2 || sixes == 2)) || (fours == 3 && (ones == 2 ||
						threes == 2 || twos == 2 || fives == 2 || sixes ==2))
						|| (fives == 3 && (ones == 2 || threes == 2 || fours == 2 ||
						twos == 2 || sixes == 2)) || (sixes == 3 && (ones == 2 ||
						threes == 2 || fours == 2 || fives == 2 || twos == 2));
				return ans;
			}
			public boolean numStraight (int n, Die [] dice) {
				boolean ans = false;
				int ones=0,twos=0, threes=0, fours=0, fives=0, sixes=0;
				for(int i=0; i<5; i++) {
					if(dice[i].getValue()==1)
						ones++;
					if(dice[i].getValue()==2)
						twos++;
					if(dice[i].getValue()==3)
						threes++;
					if(dice[i].getValue()==4)
						fours++;
					if(dice[i].getValue()==5)
						fives++;
					if(dice[i].getValue()==6)
						sixes++;		
			} 
				if (n ==4) 
					ans = (ones >= 1 && twos >= 1 && threes >= 1 && fours >= 1) ||
					(twos >= 1 && threes >= 1 && fours >= 1 && fives >=1) ||
					(threes >=1 && fours >= 1 && fives >= 1 && sixes >= 1);
				
				if(n == 5) 
					ans = (ones >= 1 && twos >= 1 && threes >= 1 && fours >= 1 &&
					fives >= 1) || (twos >= 1 && threes >= 1 && fours >= 1 && fives >= 1 && sixes >= 1);
				return ans;
			  }
			public boolean chance() {
				return scores[12] == -1;
			}
			public boolean yahtzeeBonus(Die [] dice) {
				return (numOfaKind(5, dice) && scores[11] == 50);
			}
		}