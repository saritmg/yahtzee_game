package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.apple.eawt.AppEvent;
import com.apple.eawt.Application;
import com.apple.eawt.PreferencesHandler;



public class Yahtzee {
	
	JFrame theWindow;
	Container thepane;
	MyPanel gamepanel;
	JPanel buttonpanel, leftpanel, dicepanel, scorepanel;
	MyPanel grid [];
	JLabel boxes [] [];
	
	JLabel curroll, highscore;
	JButton buttons[];
	JButton dicebuttons[];
	JButton newGame, roll, zero, quit;
	MyListener theListener;
	
	public static final int SIDES=6;
	
	Die dice[] = new Die[5];
	ScoreCard scorecard[] = new ScoreCard[6];
	
	int rollNum = 0, round = 1, tempScore = 0;
	boolean scored = false, zeroing = false, rolling = false;
	Timer timer;
	Clip yahtzeeClip, rollingClip;
	
	Application app;
	AppListener appListener;
	int delay = 60;
	JButton save, close;
	JFrame preferences;
	JSlider slider;
	JLabel currVal;
	SliderChangeListener sliderListener;
	
	public static void main(String args[]) {
		new Yahtzee();
	}
	
	public Yahtzee() {
		

		
		for(int i=0; i<5; i++)
			dice[i] = new Die(SIDES);
		for(int i=0; i<6; i++)
			scorecard[i] = new ScoreCard();
		setup();
		forceReRoll();
	}
        
	public void setup() {

		theWindow = new JFrame("Y A H T Z E E ");
		thepane = theWindow.getContentPane();
                
		//thepane.setLayout(new GridLayout(1,2));
		theWindow.setLocation(150, 150);
		
		gamepanel = new MyPanel(1000, 540, 1, 2);
		gamepanel.addKeyListener(new InputHandler());
                
		//gamepanel.setFocusable(true);
		
		leftpanel = new JPanel();
		leftpanel.setLayout(new BorderLayout());
		leftpanel.setPreferredSize(new Dimension(550, 540));
		
		JLabel yahtzee = new JLabel("YAHTZEE");
		yahtzee.setPreferredSize(new Dimension(400, 100));
		yahtzee.setFont(new Font("Helvetica", Font.BOLD, 24));
		yahtzee.setForeground(Color.BLACK);
		leftpanel.add(yahtzee, BorderLayout.NORTH);
		
		theListener = new MyListener();
		
		setupDicePanel();
		leftpanel.add(dicepanel, BorderLayout.CENTER);
		
		setupButtonPanel();
		leftpanel.add(buttonpanel, BorderLayout.SOUTH);
		gamepanel.add(leftpanel);
		
		setupScorePanel();
		gamepanel.add(scorepanel);
		
		thepane.add(gamepanel);
		theWindow.pack();
		theWindow.setVisible(true);
		
		timer = new Timer(delay, theListener);
		timer.start();
		timer.stop();
		
		app = Application.getApplication();
		appListener = new AppListener();
//		app.setPreferencesHandler(appListener);
		
	}
        
	public void setupDicePanel() {
		
		dicepanel = new JPanel();
		dicepanel.setLayout(new FlowLayout());
		
		dicebuttons = new JButton[5];
		
		for(int i = 0; i < 5; i++) {
			dicebuttons[i] = new JButton();
			dicebuttons[i].setFont(new Font("Helvetiva", Font.PLAIN, 50));
                        dicebuttons[i].setBackground(new Color(143, 188, 143));
			dicebuttons[i].addActionListener(theListener);
			dicepanel.add(dicebuttons[i]);
		}
		questionMarks();
	}
	
	public void setupButtonPanel() {
		
		buttonpanel = new JPanel();
		buttonpanel.setLayout(new FlowLayout());
		buttonpanel.setBackground(new Color(143, 188, 143));
		buttonpanel.setPreferredSize(new Dimension(550, 60));
		
		curroll = new JLabel(" Current Roll: " + rollNum);
		curroll.setFont(new Font("Helvetica", Font.PLAIN, 16));
		buttonpanel.add(curroll);
		
		
		newGame = new JButton("New Game [N]");
		newGame.addActionListener(theListener);
		buttonpanel.add(newGame);
                newGame.setBackground(new Color(211, 214, 115));
                
		roll = new JButton("Roll [R]");		
		roll.addActionListener(theListener);
		buttonpanel.add(roll);
                roll.setBackground(new Color(211, 214, 115));
                
		zero = new JButton("Zero [Z]");
		zero.addActionListener(theListener);
	 	buttonpanel.add(zero);
                zero.setBackground(new Color(211, 214, 115));
                
		quit = new JButton("Exit");
		quit.addActionListener(theListener);
		buttonpanel.add(quit);
		quit.setBackground(new Color(211, 214, 115));
	}
	public void setupScorePanel()
	{
		scorepanel = new MyPanel(400,540);
		grid = new MyPanel[7];
		boxes = new JLabel[22][7];
		JLabel l0;
		buttons = new JButton[14];
		
		grid[0] = new MyPanel(120, 540, 22, 1);
		for (int i = 1; i < 7; i++)
		{
			grid[i] = new MyPanel(40, 540, 22, 1);
			l0 = new JLabel("  #" + i);
			l0.setBorder(new MatteBorder(2, 2, 2, 2, (new Color(143, 188, 143))));
			boxes[0][i] = l0;
			for (int j = 1; j < 10; j++)
			{
				boxes[j][i] = new JLabel();
			boxes[j][i].setBorder(new MatteBorder(0, 1, 1, 1, (new Color(143, 188, 143))));
			}
			boxes[10][i] = new JLabel();
			boxes[10][i].setBorder(new MatteBorder(0, 0, 1, 0, (new Color(143, 188, 143))));
			
			for (int j = 11; j < 22; j++)
			{
				boxes[j][i] = new JLabel();
				boxes[j][i].setBorder(new MatteBorder(0, 1, 1, 1, (new Color(143, 188, 143))));
			}
			for (int j = 0; j < 22; j++)
				grid[i].add(boxes[j][i]);
		}
			        
		JLabel l1 = new JLabel("  Upper Section");
		l1.setFont(new Font("Helvetica", Font.BOLD, 14));
		l1.setBorder(new MatteBorder(2, 2, 2, 2, (new Color(143, 188, 143))));
		grid[0].add(l1);
		buttons[0] = new JButton("Aces   \t [1]");
                buttons[0].setBackground(new Color(211, 214, 115));
                
		buttons[1] = new JButton("Twos   \t [2]");
                buttons[1].setBackground(new Color(133, 178, 137));
                
		buttons[2] = new JButton("Threes \t[3]");
                buttons[2].setBackground(new Color(211, 214, 115));
                
		buttons[3] = new JButton("Fours  \t [4]");
                buttons[3].setBackground(new Color(133, 178, 137));
                
		buttons[4] = new JButton("Fives  \t [5]");
                buttons[4].setBackground(new Color(211, 214, 115));
                
		buttons[5] = new JButton("Sixes  \t [6]");
                buttons[5].setBackground(new Color(133, 178, 137));
                
		for (int i = 0; i < 6; i++)
		{
			grid[0].add(buttons[i]);
		}
		JLabel l2 = new JLabel("  TOTAL SCORE");
		l2.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l2.setBorder(new MatteBorder(2, 2, 1, 2, (new Color(143, 188, 143))));
		grid[0].add(l2);
		JLabel l3 = new JLabel("  BONUS");
		l3.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l3.setBorder(new MatteBorder(1, 2, 1, 2, (new Color(143, 188, 143))));
		grid[0].add(l3);
		JLabel l4 = new JLabel("  TOTAL");
		l4.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l4.setBorder(new MatteBorder(1, 2, 2, 2, (new Color(143, 188, 143))));
		grid[0].add(l4);
		JLabel l5 = new JLabel("LOWER SECTION");
		l5.setFont(new Font("Helvetica", Font.BOLD, 14));
		grid[0].add(l5);
		buttons[6] = new JButton("3 of a Kind");
                buttons[6].setBackground(new Color(211, 214, 115));
                
		buttons[7] = new JButton("4 of a Kind");
                buttons[7].setBackground(new Color(133, 178, 137));
                
		buttons[8] = new JButton("Full House");
                buttons[8].setBackground(new Color(211, 214, 115));
                
		buttons[9] = new JButton("Sm. Straight");
                buttons[9].setBackground(new Color(133, 178, 137));
                
		buttons[10] = new JButton("Lg Straight");
                buttons[10].setBackground(new Color(211, 214, 115));
                
		buttons[11] = new JButton("YAHTZEE");
                buttons[11].setBackground(new Color(133, 178, 137));
                
		buttons[12] = new JButton("Chance");
                buttons[12].setBackground(new Color(211, 214, 115));
                
		buttons[13] = new JButton("Yahtzee Bonus");
                buttons[13].setBackground(new Color(133, 178, 137));
		for (int i = 6; i < 14; i++)
			grid[0].add(buttons[i]);
			
		JLabel l6 = new JLabel("  Total Lower");
		l6.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l6.setBorder(new MatteBorder(2, 2, 1, 2, (new Color(143, 188, 143))));
		grid[0].add(l6);
		JLabel l7 = new JLabel("  Total Upper");
		l7.setFont(new Font("Helvetica", Font.PLAIN, 14));
		l7.setBorder(new MatteBorder(1, 2, 1, 2, (new Color(143, 188, 143))));
		grid[0].add(l7);
		JLabel l8 = new JLabel("  GRAND TOTAL");
		l8.setFont(new Font("Helvetica", Font.BOLD, 14));
		l8.setBorder(new MatteBorder(1, 2, 2, 2, (new Color(143, 188, 143))));
		grid[0].add(l8); 
		
		for (int i = 0; i < 7; i++) 
			scorepanel.add(grid[i]);	
		for (int i = 0; i < 14; i++)
			buttons[i].addActionListener(theListener);
			
	}
	public void enableButtons()
	{
		for (int i = 0; i < 6; i++)
		{
			buttons[i].setEnabled(scorecard[round-1].num(i+1, dice));
		}
		buttons[6].setEnabled(scorecard[round-1].numOfaKind(3, dice));
		buttons[7].setEnabled(scorecard[round-1].numOfaKind(4, dice));
		buttons[8].setEnabled(scorecard[round-1].fullHouse(dice));
		buttons[9].setEnabled(scorecard[round-1].numStraight(4, dice));
		buttons[10].setEnabled(scorecard[round-1].numStraight(5, dice));
		buttons[11].setEnabled(scorecard[round-1].numOfaKind(5, dice));
		buttons[12].setEnabled(scorecard[round-1].chance());
		buttons[13].setEnabled(scorecard[round-1].yahtzeeBonus(dice));
		
		for (int i = 0; i < 13; i++)
			if (scorecard[round-1].getScore(i) > -1)
				buttons[i].setEnabled(false);
		
		if (scorecard[round-1].numOfaKind(5, dice))
		{
			yahtzeeClip.stop();
			yahtzeeClip.setFramePosition(0);
			yahtzeeClip.start();
		}
		zero.setEnabled(true);
	}
	public void roll()
	{
		timer.restart();	
		rolling = true;	
                
	if (rollNum < 4)
	{
		rollingAnimation();
		rollNum = (rollNum + 1) % 4;
	}			
		if (rollNum == 4)
			roll.setEnabled(false);

		curroll.setText("  Current Roll: " + rollNum);
	}
	
	public void rollingAnimation()
	{
		for (int i = 0; i < 5; i++)
			if (!dice[i].held() || dicebuttons[i].getText() == "+")
			{
				if (dice[i].held())
					toggleDie(i);
				dice[i].roll();
				dicebuttons[i].setText(Integer.toString(dice[i].getValue()));
			}
	}
	
	public void zero()
	{
		zeroing = true;
		for (int i = 0; i < 13; i++)
			if (scorecard[round-1].getScore(i) == -1)
					buttons[i].setEnabled(true);
		roll.setEnabled(false);
		zero.setEnabled(false);
	}
	
	public void questionMarks()
	{
		for (int i = 0; i < 5; i++)
			dicebuttons[i].setText("+");
	}
	
	public void toggleDie(int n)
	{
		if (!dice[n].held())
			dicebuttons[n].setFont(new Font("Helvetica", Font.BOLD, 65));
		else
			dicebuttons[n].setFont(new Font("Helvetica", Font.PLAIN, 50));
		dice[n].toogleHeld();
	}
	
	public void score(int n, int score)
	{
		scorecard[round-1].setScore(n, score);		// backend - not GUI
		if (n > 5)
			n += 4;
		for (int i = 0; i < 5; i++)
			if (dice[i].held())
				toggleDie(i);
		zeroing = false;
		if (n != 17)
			scored = true;
		tempScore = 0;
		
		// score the one they just did
		boxes[n+1][round].setText("  " + Integer.toString(score));
		
		// Recalculate the totals and display them
		boxes[7][round].setText("  " +
				Integer.toString(scorecard[round-1].upperScore()));
		boxes[8][round].setText("  " +	
				Integer.toString(scorecard[round-1].bonusScore()));
		boxes[9][round].setText("  " +
				Integer.toString(scorecard[round-1].totalUpperscore()));
		boxes[19][round].setText("  " +	
				Integer.toString(scorecard[round-1].lowerScore()));
		boxes[20][round].setText("  " +	
				Integer.toString(scorecard[round-1].totalUpperscore()));
		boxes[21][round].setText("  " +	
				Integer.toString(scorecard[round-1].grandTotal()));	
		if (n != 17)				
			forceReRoll();
		scored = false;	
		questionMarks();	
	}
	
	public void forceReRoll()
	{
		for (int i = 0; i < 14; i++)	
			buttons[i].setEnabled(false);
		zero.setEnabled(false);
		roll.setEnabled(true);
		rollNum = 0;
		curroll.setText("  Current Roll: " + rollNum);
		scored = false;
		if (scorecard[round-1].filled())
			forceNewGame();
	}
	
	public void forceNewGame()
	{
		for (int i = 0; i < 14; i++)	
			buttons[i].setEnabled(false);
		zero.setEnabled(false);
		roll.setEnabled(false);
		
	}
	
	public int sum()
	{
		return (dice[0].getValue() + dice[1].getValue() + dice[2].getValue()
					+ dice[3].getValue() + dice[4].getValue());
	}
	private class InputHandler extends KeyAdapter
	{
	    public void keyPressed(KeyEvent e) 
		{
			if (rolling)
			{
				timer.stop();
				rolling = false;
				enableButtons();
			}
			else {
	        if (e.getKeyCode() == KeyEvent.VK_R && roll.isEnabled()) 
		        roll();
		    if (e.getKeyCode() == KeyEvent.VK_A && dicebuttons[0].isEnabled()) 
		        toggleDie(0);                  
		    if (e.getKeyCode() == KeyEvent.VK_S && dicebuttons[1].isEnabled()) 
		        toggleDie(1);                  
		    if (e.getKeyCode() == KeyEvent.VK_D && dicebuttons[2].isEnabled()) 
		        toggleDie(2);                 
		    if (e.getKeyCode() == KeyEvent.VK_F && dicebuttons[3].isEnabled()) 
		        toggleDie(3);                  
		    if (e.getKeyCode() == KeyEvent.VK_G && dicebuttons[4].isEnabled()) 
		        toggleDie(4);
			if (e.getKeyCode() == KeyEvent.VK_Z && zero.isEnabled())
				zero();
			if (e.getKeyCode() == KeyEvent.VK_N && newGame.isEnabled())
			{
				if (JOptionPane.showConfirmDialog(null, "Are you sure you"
				 	 + " want to start a new game?",	"New Game", 
						JOptionPane.YES_NO_OPTION) == 0)
				{	
					if (round < 6)
					{
						round++;
						questionMarks();
						forceReRoll();
					}
					else
						new Yahtzee();
				}
			}
			}
			
	    }
	}
	
	
	private class AppListener implements PreferencesHandler
	{
		public void handlePreferences(AppEvent.PreferencesEvent e)
		{
			preferences = new JFrame("Preferences");
			preferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			preferences.setLocation(350, 200);
			MyPanel prefPanel = new MyPanel(500, 200, 4, 1);
			
			slider = new JSlider();
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.setMaximum(500);
                        slider.setMinimum(0);
                        slider.setMajorTickSpacing(100);
                        slider.setMinorTickSpacing(25);
                        slider.setValue(delay);
			
			sliderListener = new SliderChangeListener();
			
	        slider.addChangeListener(sliderListener);
			prefPanel.add(slider);
			//prefPanel.add(temp);
			
			preferences.add(prefPanel);
	        preferences.pack();
	        preferences.setVisible(true);
		}
	}
	
	private class SliderChangeListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e) 
		{
            String adjust = "";
            if (slider.getValueIsAdjusting())
            	currVal.setText("Current value: " + slider.getValue());
			delay = slider.getValue();
			timer.setDelay(delay);
        }
    };
    
    private class MyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			gamepanel.grabFocus();
			if (rolling)
			{
				if (e.getSource() == timer)
					rollingAnimation();
				if (e.getSource()  != timer)
				{
					timer.stop();
					rolling = false;
					enableButtons();
				}
			}
			else
			if (zeroing)
			{
				for (int i = 0; i < 13; i++)					
					if (e.getSource() == buttons[i])
					{
						score(i, 0);
						zeroing = false;
						scored = true;	
						roll.setEnabled(true);
						forceReRoll();
					}
			}
			else if (!rolling)
			{	
				for (int i = 0; i < 5; i++)
					if (e.getSource() == dicebuttons[i])
						toggleDie(i);
				if (e.getSource() == newGame)
				{
					if (JOptionPane.showConfirmDialog(null, "Are you sure you"
					 	 + " want to start a new game?",	"New Game", 
							JOptionPane.YES_NO_OPTION) == 0)
					{	
						if (round < 6)
						{
							round++;
							questionMarks();
							forceReRoll();
						}
						else
							new Yahtzee();
					}
				}
					
				if (e.getSource() == roll)
					roll();
				if (e.getSource() == zero)
					zero();
				if (e.getSource() == quit)
					System.exit(0);
				for (int i = 0; i < 6; i++)
					if (e.getSource() == buttons[i])
					{
						for (int j = 0; j < 5; j++)
							if (dice[j].getValue() == i+1)
								tempScore += i+1;
						score(i, tempScore);
					}
				if (e.getSource() == buttons[6])
					score(6, sum());
				if (e.getSource() == buttons[7])
					score(7, sum());
				if (e.getSource() == buttons[8])
					score(8, 25);
				if (e.getSource() == buttons[9])
					score(9, 30);
				if (e.getSource() == buttons[10])
					score(10, 40);
				if (e.getSource() == buttons[11])
					score(11, 50);
				if (e.getSource() == buttons[12])
					score(12, sum());
				if (e.getSource() == buttons[13])
					score(13, 100);
				if (e.getSource() == close)
					preferences.setVisible(false);
			}
		}
	}

}
