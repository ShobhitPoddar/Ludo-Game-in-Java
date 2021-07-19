import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class LudoMainBoard extends JFrame {
	static int boardWidth=500;
	static int boardHeight=500;
	int coinWidth=30,coinHeight=30;
	ImageIcon [] blueCoin,redCoin,yellowCoin,greenCoin;
	int blueCurPos[]= {-1,-1,-1,-1},yellowCurPos[]={-1,-1,-1,-1},greenCurPos[]= {-1,-1,-1,-1},redCurPos[]= {-1,-1,-1,-1};
	Point[] blueInitPos,yellowInitPos,greenInitPos,redInitPos;
	int curPlayer=1,randomNumber,curNumber,greenCnt=0,redCnt=0,blueCnt=0,yellowCnt=0,winCnt=0,playerCount;
	JButton [] blueButton,redButton,yellowButton,greenButton;
    boolean six,six3,six2,one,two,three,four,lastNumber,real;
	MyJPanel board;
	JLabel lblDice,lblAnimated;
	JButton btnGreenTurn,btnRedTurn,btnBlueTurn,btnYellowTurn;
	ImageIcon diceImage,diceAnimated;
	Thread t1,t2,t3,t4;
	JTextField tf1;
	
	int greenx[]={35,68,101,134,170,201,201,201,201,201,201,234,267,267,267,267,267,267,301,334,367,401,434,466,466,466,433,400,366,334,300,268,268,268,268,268,268,234,200,200,200,200,200,200,168,135,101,68,35,2,2,35,67,101,135,169,204};
    int greeny[]={200,200,200,200,200,167,135,101,68,35,2,2,2,34,68,102,134,167,201,201,201,201,201,201,233,266,266,266,266,266,266,301,332,367,399,433,466,466,466,431,400,366,331,299,266,266,266,266,266,266,234,234,234,234,234,234,234};
	int redx[]= {267,267,267,267,267,300,334,366,400,434,466,466,466,433,400,366,334,300,268,268,268,267,267,267,234,200,200,200,200,200,200,168,135,101,68,35,2,2,2,35,68,101,134,170,201,201,201,201,201,201,234,234,234,234,234,234,234};
	int redy[]= {34,68,102,134,167,201,201,201,201,201,201,234,267,266,266,266,266,266,301,332,367,399,433,466,466,466,431,400,366,331,299,266,266,266,266,266,266,234,200,200,200,200,200,200,167,135,101,68,35,2,2,33,68,101,133,167,198};
	int bluex[]= {433,400,366,334,300,268,268,268,267,267,267,234,200,200,200,200,200,200,168,135,101,68,35,2,2,2,35,68,101,134,170,201,201,201,201,201,201,234,267,267,267,267,267,267,301,334,367,401,434,466,466,434,400,366,334,301,271};
	int bluey[]= {266,266,266,266,266,301,332,367,399,433,466,466,466,431,400,366,331,299,266,266,266,266,266,266,234,200,200,200,200,200,200,167,135,101,68,35,2,2,2,34,68,102,134,167,201,201,201,201,201,201,234,234,234,234,234,234,234};
	int yellowx[]= {200,200,200,200,200,168,135,101,68,35,2,2,2,35,68,101,134,170,201,201,201,201,201,201,234,267,267,267,267,267,267,301,334,367,401,434,466,466,433,400,366,334,300,268,268,268,267,267,267,234,234,234,234,234,234,234};
	int yellowy[]= {431,400,366,331,299,266,266,266,266,266,266,234,200,200,200,200,200,200,167,135,101,68,35,2,2,2,34,68,102,134,167,201,201,201,201,201,201,234,266,266,266,266,266,301,332,367,399,433,466,466,434,399,366,333,301,271};

	int greenUniqueNumber[]= {-1,1,2,3,4,5,6,7,-1,9,10,11,12,-1,14,15,16,17,18,19,20,-1,22,23,24,25,-1,27,28,29,30,31,32,33,-1,35,36,37,38,-1,40,41,42,43,44,45,46,-1,48,49,50,-1,-1,-1,-1,-1,-1};
    int redUniqueNumber[]= {-1,14,15,16,17,18,19,20,-1,22,23,24,25,-1,27,28,29,30,31,32,33,-1,35,36,37,38,-1,40,41,42,43,44,45,46,-1,48,49,50,51,-1,1,2,3,4,5,6,7,-1,9,10,11,-1,-1,-1,-1,-1,-1};
    int blueUniqueNumber[]= {-1,27,28,29,30,31,32,33,-1,35,36,37,38,-1,40,41,42,43,44,45,46,-1,48,49,50,51,-1,1,2,3,4,5,6,7,-1,9,10,11,12,-1,14,15,16,17,18,19,20,-1,22,23,24,-1,-1,-1,-1,-1,-1};
    int yellowUniqueNumber[]= {-1,40,41,42,43,44,45,46,-1,48,49,50,51,-1,1,2,3,4,5,6,7,-1,9,10,11,12,-1,14,15,16,17,18,19,20,-1,22,23,24,25,-1,27,28,29,30,31,32,33,-1,35,36,37,-1,-1,-1,-1,-1,-1};
	public LudoMainBoard(int playerCount,boolean real) {
		this.playerCount=playerCount;
		this.real=real;
		board=new MyJPanel();
		board.setLayout(null);
		add(board);
	
		greenCoin=new ImageIcon[4];
		greenInitPos=new Point[4];
		greenButton=new JButton[4];
		greenInitPos[0]=new Point(51,85);
		greenInitPos[1]=new Point(85,118);
		greenInitPos[2]=new Point(118,85);
		greenInitPos[3]=new Point(85,51);
		for(int i=0;i<4;i++) {
            greenCoin[i]=new ImageIcon(new ImageIcon("images/green.png").getImage().getScaledInstance(coinWidth,coinHeight, Image.SCALE_DEFAULT));
            greenButton[i]=new JButton(greenCoin[i]);
        	greenButton[i].setBorder(BorderFactory.createEmptyBorder());
			greenButton[i].setContentAreaFilled(false);
			greenButton[i].addActionListener(new A());
            greenButton[i].setBounds(greenInitPos[i].x,greenInitPos[i].y,coinWidth,coinHeight);
			board.add(greenButton[i]);
		}
		
		redCoin=new ImageIcon[4];
		redInitPos=new Point[4];
		redButton=new JButton[4];
		redInitPos[0]=new Point(383,51);
		redInitPos[1]=new Point(350,85);
		redInitPos[2]=new Point(384,118);
		redInitPos[3]=new Point(417,84);
		for(int i=0;i<4;i++) {
			redCoin[i]=new ImageIcon(new ImageIcon("images/red.png").getImage().getScaledInstance(coinWidth,coinHeight, Image.SCALE_DEFAULT));
			redButton[i]=new JButton(redCoin[i]);
			redButton[i].setBorder(BorderFactory.createEmptyBorder());
			redButton[i].setContentAreaFilled(false);
			redButton[i].addActionListener(new A());
			redButton[i].setBounds(redInitPos[i].x,redInitPos[i].y,coinWidth,coinHeight);
			board.add(redButton[i]);
		}
		
		if(playerCount>=3) {
			blueCoin=new ImageIcon[4];
			blueInitPos=new Point[4];
			blueButton=new JButton[4];
			blueInitPos[0]=new Point(384,351);
			blueInitPos[1]=new Point(351,384);
			blueInitPos[2]=new Point(384,416);
			blueInitPos[3]=new Point(417,384);
			for(int i=0;i<4;i++) {
				blueCoin[i]=new ImageIcon(new ImageIcon("images/blue.png").getImage().getScaledInstance(coinWidth,coinHeight, Image.SCALE_DEFAULT));
				blueButton[i]=new JButton(blueCoin[i]);
				blueButton[i].setBorder(BorderFactory.createEmptyBorder());
				blueButton[i].setContentAreaFilled(false);
				blueButton[i].addActionListener(new A());
				blueButton[i].setBounds(blueInitPos[i].x,blueInitPos[i].y,coinWidth,coinHeight);
				board.add(blueButton[i]);
			}
		}
		
		if(playerCount>=4) {
			yellowCoin=new ImageIcon[4];
			yellowInitPos=new Point[4];
			yellowButton=new JButton[4];
			yellowInitPos[0]=new Point(85,351);
			yellowInitPos[1]=new Point(118,383);
			yellowInitPos[2]=new Point(84,417);
			yellowInitPos[3]=new Point(52,384);
			for(int i=0;i<4;i++) {
				yellowCoin[i]=new ImageIcon(new ImageIcon("images/yellow.png").getImage().getScaledInstance(coinWidth,coinHeight, Image.SCALE_DEFAULT));
				yellowButton[i]=new JButton(yellowCoin[i]);
				yellowButton[i].setBorder(BorderFactory.createEmptyBorder());
				yellowButton[i].setContentAreaFilled(false);
				yellowButton[i].addActionListener(new A());
				yellowButton[i].setBounds(yellowInitPos[i].x,yellowInitPos[i].y,coinWidth,coinHeight);
				board.add(yellowButton[i]);
			}
		}
		
		diceImage= new ImageIcon("images/1.png");
		lblDice=new JLabel(new ImageIcon(diceImage.getImage().getScaledInstance(50, 50 ,Image.SCALE_DEFAULT)));
		lblDice.setBounds(225, 225, 50, 50);
		board.add(lblDice);
		lblDice.setVisible(false);
		
		diceAnimated=new ImageIcon("images/animated_dice.gif");
		lblAnimated = new JLabel(new ImageIcon(diceAnimated.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		lblAnimated.setBounds(200, 200, 100, 100);
		lblAnimated.setVisible(false);
		board.add(lblAnimated);
				
		if(!real) {
			tf1=new JTextField();
			tf1.setBounds(0,0,50,50);
			board.add(tf1);
		}
		
		btnGreenTurn =new JButton(new ImageIcon(new ImageIcon("images/greenbutton.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT)));
		btnGreenTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dice(1);
			}
		});
		btnGreenTurn.setBounds(148, 152, 40, 40);
		btnGreenTurn.setBorder(BorderFactory.createEmptyBorder());
		btnGreenTurn.setContentAreaFilled(false);
		board.add(btnGreenTurn);
		
		btnRedTurn =new JButton(new ImageIcon(new ImageIcon("images/redbutton.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT)));
		btnRedTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dice(2);
			}
		});
		btnRedTurn.setBounds(450, 155, 40, 40);
		btnRedTurn.setBorder(BorderFactory.createEmptyBorder());
		btnRedTurn.setContentAreaFilled(false);
		btnRedTurn.setEnabled(false);
		board.add(btnRedTurn);

		if(playerCount>=3) {
			btnBlueTurn =new JButton(new ImageIcon(new ImageIcon("images/bluebutton.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT)));
			btnBlueTurn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					dice(2);
				}
			});
			btnBlueTurn.setBounds(450, 450, 40, 40);
			btnBlueTurn.setBorder(BorderFactory.createEmptyBorder());
			btnBlueTurn.setContentAreaFilled(false);
			btnBlueTurn.setEnabled(false);
			board.add(btnBlueTurn);
		}
		
		if(playerCount>=4) {
			btnYellowTurn =new JButton(new ImageIcon(new ImageIcon("images/yellowbutton.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT)));
			btnYellowTurn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					dice(2);
				}
			});
			btnYellowTurn.setBounds(150, 450, 40, 40);
			btnYellowTurn.setBorder(BorderFactory.createEmptyBorder());
			btnYellowTurn.setContentAreaFilled(false);
			btnYellowTurn.setEnabled(false);
			board.add(btnYellowTurn);
		}
				
		setUndecorated(false);
		setSize(boardWidth+14,boardHeight+37);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void dice(int player){
		curPlayer=player;
		lblDice.setVisible(false);
		lblAnimated.setVisible(true);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				lblAnimated.setVisible(false);
				if(real) {
					Random r1=new Random();
					randomNumber=r1.nextInt(6)+1;
				}
				else {
					randomNumber=Integer.parseInt(tf1.getText());
				}
				diceImage=new ImageIcon("images/"+randomNumber+".png");
				lblDice.setIcon(new ImageIcon(diceImage.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
				lblDice.setVisible(true);
				revalidate();
				if(randomNumber!=6) {
					if(six==false) {
						if(curPlayer==1 && greenCurPos[0]==-1 && greenCurPos[1]==-1 && greenCurPos[2]==-1 && greenCurPos[3]==-1) {
							JOptionPane.showMessageDialog(LudoMainBoard.this,"You need 6 to move");
							randomNumber=0;
							curNumber=0;
							adjustChance();
							return;
						}
						if(curPlayer==2 && redCurPos[0]==-1 && redCurPos[1]==-1 && redCurPos[2]==-1 && redCurPos[3]==-1) {
							JOptionPane.showMessageDialog(LudoMainBoard.this,"You need 6 to move");
							curNumber=0;
							randomNumber=0;
							adjustChance();
							return;
						}
						if(playerCount>=3)
							if(curPlayer==3 && blueCurPos[0]==-1 && blueCurPos[1]==-1 && blueCurPos[2]==-1 && blueCurPos[3]==-1) {
								JOptionPane.showMessageDialog(LudoMainBoard.this,"You need 6 to move");
								randomNumber=0;
								curNumber=0;
								adjustChance();
								return;
							}
						if(playerCount>=4)
							if(curPlayer==4 && yellowCurPos[0]==-1 && yellowCurPos[1]==-1 && yellowCurPos[2]==-1 && yellowCurPos[3]==-1) {
								JOptionPane.showMessageDialog(LudoMainBoard.this,"You need 6 to move");
								randomNumber=0;
								curNumber=0;
								adjustChance();
								return;
							}
					}
					lastNumber=true;
					prompt();
				}
				else{
					if(six2==true) {
						six3=true;
						randomNumber=0;
						JOptionPane.showMessageDialog(LudoMainBoard.this, "3 sixes so can't move");
						adjustChance();
						six=false;
						six2=false;
						six3=false;
					}
					else if(six==true)
						six2=true;
					else
						six=true;
				}						
			}
		}.start();
	}
	boolean overlapCheck(int n) {
		if(n==1 || n==2)
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(redCurPos[i]!=-1 && greenCurPos[j]!=-1 && redUniqueNumber[redCurPos[i]]!=-1 && greenUniqueNumber[greenCurPos[j]]!=-1 && redUniqueNumber[redCurPos[i]]==greenUniqueNumber[greenCurPos[j]]) {
					if(n==1) {//green coin overlapped red coin
						JOptionPane.showMessageDialog(this,"Overlap1");
						redButton[i].setLocation(redInitPos[i]);
						redCurPos[i]=-1;
					}
					else if(n==2){//red coin overlapped green coin
						JOptionPane.showMessageDialog(this,"Overlap2");
						greenButton[j].setLocation(greenInitPos[j]);
						greenCurPos[i]=-1;
					}
					return true;
				}
			}
		}
		if(n==3 || n==2)
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(redCurPos[i]!=-1 && blueCurPos[j]!=-1 && redUniqueNumber[redCurPos[i]]!=-1 && blueUniqueNumber[blueCurPos[j]]!=-1 && redUniqueNumber[redCurPos[i]]==blueUniqueNumber[blueCurPos[j]]) {
					if(n==3) {//blue coin overlapped red coin
						JOptionPane.showMessageDialog(this,"Overlap3");
						redButton[i].setLocation(redInitPos[i]);
						redCurPos[i]=-1;
					}
					else if(n==2){//red coin overlapped blue coin
						JOptionPane.showMessageDialog(this,"Overlap4");
						blueButton[j].setLocation(blueInitPos[j]);
						blueCurPos[i]=-1;
					}			
					return true;
				}
			}
		}
		if(n==4 || n==2)
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(redCurPos[i]!=-1 && yellowCurPos[j]!=-1 && redUniqueNumber[redCurPos[i]]!=-1 && yellowUniqueNumber[yellowCurPos[j]]!=-1 && redUniqueNumber[redCurPos[i]]==yellowUniqueNumber[yellowCurPos[j]]) {
					if(n==4) {//yellow coin overlapped red coin
						JOptionPane.showMessageDialog(this,"Overlap5");
						redButton[i].setLocation(redInitPos[i]);
						redCurPos[i]=-1;
					}
					else if(n==2){//red coin overlapped yellow coin
						JOptionPane.showMessageDialog(this,"Overlap6");
						yellowButton[j].setLocation(yellowInitPos[j]);
						yellowCurPos[i]=-1;
					}
					return true;
				}
			}
		}
		if(n==1 || n==3)
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(greenCurPos[i]!=-1 && blueCurPos[j]!=-1 && greenUniqueNumber[greenCurPos[i]]!=-1 && blueUniqueNumber[blueCurPos[j]]!=-1 && greenUniqueNumber[greenCurPos[i]]==blueUniqueNumber[blueCurPos[j]]) {
					if(n==1) {//green coin overlapped blue coin
						JOptionPane.showMessageDialog(this,"Overlap7");
						blueButton[j].setLocation(blueInitPos[j]);
						blueCurPos[i]=-1;
					}
					else if(n==3){//blue coin overlapped green coin
						JOptionPane.showMessageDialog(this,"Overlap8");
						greenButton[i].setLocation(greenInitPos[i]);
						greenCurPos[i]=-1;
					}
					return true;
				}
			}
		}
		if(n==1 || n==4)
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(greenCurPos[i]!=-1 && yellowCurPos[j]!=-1 && greenUniqueNumber[greenCurPos[i]]==yellowUniqueNumber[yellowCurPos[j]]) {
					if(n==1) {//green coin overlapped yellow coin
						JOptionPane.showMessageDialog(this,"Overlap9");
						yellowButton[j].setLocation(yellowInitPos[j]);
						yellowCurPos[i]=-1;
					}
					else if(n==4){//yellow coin overlapped green coin
						JOptionPane.showMessageDialog(this,"Overlap10");
						greenButton[i].setLocation(greenInitPos[i]);
						greenCurPos[i]=-1;
					}
					return true;
				}
			}
		}
		if(n==3 || n==4)
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(blueCurPos[i]!=-1 && yellowCurPos[j]!=-1 && blueUniqueNumber[blueCurPos[i]]!=-1 && yellowUniqueNumber[yellowCurPos[j]]!=-1 && blueUniqueNumber[blueCurPos[i]]==yellowUniqueNumber[yellowCurPos[j]]) {
					if(n==3) {//blue coin overlapped yellow coin
						JOptionPane.showMessageDialog(this,"Overlap11");
						yellowButton[j].setLocation(yellowInitPos[j]);
						yellowCurPos[i]=-1;
					}
					else if(n==4){//yellow coin overlapped blue coin
						JOptionPane.showMessageDialog(this,"Overlap12");
						blueButton[i].setLocation(blueInitPos[i]);
						blueCurPos[i]=-1;
					}			
					return true;
				}
			}
		}
		return false;
	}
	public void adjustChance() {
    	boolean b=overlapCheck(curPlayer);
    	if(b==true) {
    		playSound(2);
    		JOptionPane.showMessageDialog(this, "You got bonus chance");
    		return;
    	}
    	btnGreenTurn.setEnabled(false);
    	btnRedTurn.setEnabled(false);
    	if(playerCount>=3)
    		btnBlueTurn.setEnabled(false);
    	if(playerCount>=4)
    		btnYellowTurn.setEnabled(false);
		if(curPlayer==1) {
			curPlayer=2;
			btnRedTurn.setEnabled(true);
		}
		else if(curPlayer==2) {
			if(playerCount>=3) {
				curPlayer=3;
				btnBlueTurn.setEnabled(true);
			}
			else{
				curPlayer=1;
				btnGreenTurn.setEnabled(true);
			}
		}
		else if(curPlayer==3) {
			if(playerCount>=4) {
				curPlayer=4;
				btnYellowTurn.setEnabled(true);
			}
			else {
				curPlayer=1;
				btnGreenTurn.setEnabled(true);
			}
		}
		else if(curPlayer==4) {
			curPlayer=1;
			btnGreenTurn.setEnabled(true);
		}
    }
 	public void prompt() {
 		if(six2==true){
 			setTitle("You got 6 now Click on the coin to move");
    		six2=false;
    		curNumber=6;
    		return;
 		}
 		else if(six==true) {
 			setTitle("You got 6 now Click on the coin to move");
    		six=false;
    		curNumber=6;
    		return;
 		}
 		else {
 			setTitle("You got "+ randomNumber +" now Click on the coin to move");
    		lastNumber=false;
    		curNumber=randomNumber;
    		randomNumber=0;
    		return;
 		}
 	}
 	void callPrompt() {
 		if(lastNumber!=false)
			prompt();
    	else {
    		setTitle("");
    		adjustChance();
    	}
 	}
    public void moveCoin(int player,int coin,int n) {
    	if(player==1) {//green 
    		if(greenCurPos[coin]==-1) {
    			if(n==6){
    				greenButton[coin].setLocation(greenx[0], greeny[0]);
    				greenCurPos[coin]=0;
    			}
    			else {
    				JOptionPane.showMessageDialog(this, "You need 6 to move this coin");
    				lastNumber=true;
    	    		randomNumber=curNumber;
    			}
    			callPrompt();
    		}
    		else{
    			if(greenx.length-greenCurPos[coin]-1<n) {
    				JOptionPane.showMessageDialog(this, "you need" + (greenx.length-greenCurPos[coin]-1) + "to move this coin");
    			}
    			else {
    				new Thread() {
    					public void run() {
    						playSound(1);
    						for(int i=greenCurPos[coin]+1;i<=greenCurPos[coin]+n;i++) {
    							greenButton[coin].setLocation(greenx[i], greeny[i]);
    							delay(100);
    						}
    						greenCurPos[coin]+=n;
    						if(greenCurPos[coin]==greenx.length-1){//Reached Home
    							greenButton[coin].setEnabled(false);
    							JOptionPane.showMessageDialog(LudoMainBoard.this, "Reached Home");
    							greenCnt++;
    							if(greenCnt==4) {
    								JOptionPane.showMessageDialog(LudoMainBoard.this, "Player 1 got Rank "+ (++winCnt));
    							}
    						}
    						callPrompt();
    					}
    				}.start();
    			}
    		}
    	}
    	else if(player==2) {//red 
    		if(redCurPos[coin]==-1) {
    			if(n==6){
    				redButton[coin].setLocation(redx[0], redy[0]);
    				redCurPos[coin]=0;
    			}
    			else {
    				JOptionPane.showMessageDialog(this, "You need 6 to move this coin");
    				lastNumber=true;
    	    		randomNumber=curNumber;
    			}
    			callPrompt();
    		}
    		else{
    			if(redx.length-redCurPos[coin]-1<n) {
    				JOptionPane.showMessageDialog(this, "you need" + (redx.length-redCurPos[coin]-1) + "to move this coin");
    			}
    			else {
    				new Thread() {
    					public void run() {
    						playSound(1);
    						for(int i=redCurPos[coin]+1;i<=redCurPos[coin]+n;i++) {
    							redButton[coin].setLocation(redx[i], redy[i]);
    							delay(100);
    						}
    						redCurPos[coin]+=n;
    						if(redCurPos[coin]==redx.length-1){//Reached Home
    							redButton[coin].setEnabled(false);
    							JOptionPane.showMessageDialog(LudoMainBoard.this, "Reached Home");
    							redCnt++;
    							if(redCnt==4) {
    								JOptionPane.showMessageDialog(LudoMainBoard.this, "Player 2 got Rank "+ (++winCnt));
    							}
    						}
    						callPrompt();
    					}
    				}.start();
    			}
    		}
    	}
    	else if(player==3) {//blue 
    		if(blueCurPos[coin]==-1) {
    			if(n==6){
    				blueButton[coin].setLocation(bluex[0], bluey[0]);
    				blueCurPos[coin]=0;
    			}
    			else {
    				JOptionPane.showMessageDialog(this, "You need 6 to move this coin");
    				lastNumber=true;
    	    		randomNumber=curNumber;
    			}
    			callPrompt();
    		}
    		else{
    			if(bluex.length-blueCurPos[coin]-1<n) {
    				JOptionPane.showMessageDialog(this, "you need" + (bluex.length-blueCurPos[coin]-1) + "to move this coin");
    			}
    			else {
    				new Thread() {
    					public void run() {
    						playSound(1);
    						for(int i=blueCurPos[coin]+1;i<blueCurPos[coin]+n+1;i++) {
    							blueButton[coin].setLocation(bluex[i], bluey[i]);
    							delay(100);
    						}
    						blueCurPos[coin]+=n;
    						if(blueCurPos[coin]==bluex.length-1){//Reached Home
    							blueButton[coin].setEnabled(false);
    							JOptionPane.showMessageDialog(LudoMainBoard.this, "Reached Home");
    							blueCnt++;
    							if(blueCnt==4) {
    								JOptionPane.showMessageDialog(LudoMainBoard.this, "Player 4 got Rank "+ (++winCnt));
    							}
    						}
    						callPrompt();
    					}
    				}.start();
    			}  	
    		}
    	}	
    	else if(player==4) {//yellow 
    		if(yellowCurPos[coin]==-1) {
    			if(n==6){
    				yellowButton[coin].setLocation(yellowx[0], yellowy[0]);
    				yellowCurPos[coin]=0;
    			}
    			else {
    				JOptionPane.showMessageDialog(this, "You need 6 to move this coin");
    				lastNumber=true;
    	    		randomNumber=curNumber;
    			}
    			callPrompt();
    		}
    		else{
    			if(yellowx.length-yellowCurPos[coin]-1<n) {
    				JOptionPane.showMessageDialog(this, "you need" + (yellowx.length-yellowCurPos[coin]-1) + "to move this coin");
    			}
    			else {
    				new Thread() {
    					public void run() {
    						playSound(1);
    						for(int i=yellowCurPos[coin]+1;i<=yellowCurPos[coin]+n;i++) {
    							yellowButton[coin].setLocation(yellowx[i], yellowy[i]);
    							delay(100);
    						}
    						yellowCurPos[coin]+=n;
    						if(yellowCurPos[coin]==yellowx.length-1){//Reached Home
    							yellowButton[coin].setEnabled(false);
    							JOptionPane.showMessageDialog(LudoMainBoard.this, "Reached Home");
    							yellowCnt++;
    							if(yellowCnt==4) {
    								JOptionPane.showMessageDialog(LudoMainBoard.this, "Player 3 got Rank "+ (++winCnt));
    							}
    						}
    						callPrompt();
    					}
    				}.start();
    			}
    		}
    	}
    }
    public void delay(int n) {
    	try {
    		Thread.sleep(n);
    	}
    	catch(InterruptedException e) {
    		e.printStackTrace();
    	}
    }
	public void playSound(int code) {
	    try {
	    	String s1;
	    	if(code == 1) {
	    		s1="music/move.wav";
	    	}
	    	else {
	    		s1="music/cut.wav";
	    	}
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s1).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	class MyJPanel extends JPanel{ 
		public void paintComponent(Graphics g) {
			ImageIcon background;
			background=new ImageIcon("images/ludoboard.jpg");
			g.drawImage(background.getImage(), 0, 0, LudoMainBoard.boardWidth,LudoMainBoard.boardHeight,null);
		}
	}
	class A implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b=(JButton)e.getSource();
			if(greenButton[0]==b){
				if(curPlayer==1 && curNumber!=0)
					moveCoin(1,0,curNumber);
			}
			else if(greenButton[1]==b){
				if(curPlayer==1 && curNumber!=0)
					moveCoin(1,1,curNumber);
			}
			else if(greenButton[2]==b){
				if(curPlayer==1 && curNumber!=0)
					moveCoin(1,2,curNumber);
			}
			else if(greenButton[3]==b){
				if(curPlayer==1 && curNumber!=0)
					moveCoin(1,3,curNumber);
			}
			else if(redButton[0]==b){
				if(curPlayer==2 && curNumber!=0)
					moveCoin(2,0,curNumber);
			}
			else if(redButton[1]==b){
				if(curPlayer==2 && curNumber!=0)
					moveCoin(2,1,curNumber);
			}
			else if(redButton[2]==b){
				if(curPlayer==2 && curNumber!=0)
					moveCoin(2,2,curNumber);
			}
			else if(redButton[3]==b){
				if(curPlayer==2 && curNumber!=0)
					moveCoin(2,3,curNumber);
			}
			else if(blueButton[0]==b){
				if(curPlayer==3 && curNumber!=0)
					moveCoin(3,0,curNumber);
			}
			else if(blueButton[1]==b){
				if(curPlayer==3 && curNumber!=0)
					moveCoin(3,1,curNumber);
			}
			else if(blueButton[2]==b){
				if(curPlayer==3 && curNumber!=0)
					moveCoin(3,2,curNumber);
			}
			else if(blueButton[3]==b){
				if(curPlayer==3 && curNumber!=0)
					moveCoin(3,3,curNumber);
			}
			else if(yellowButton[0]==b){
				if(curPlayer==4 && curNumber!=0)
					moveCoin(4,0,curNumber);
			}
			else if(yellowButton[1]==b){
				if(curPlayer==4 && curNumber!=0)
					moveCoin(4,1,curNumber);
			}
			else if(yellowButton[2]==b){
				if(curPlayer==4 && curNumber!=0)
					moveCoin(4,2,curNumber);
			}
			else if(yellowButton[3]==b){
				if(curPlayer==4 && curNumber!=0)
					moveCoin(4,3,curNumber);
			}
		}
	}
	/*public static void main(String[] args) {
		new LudoMainBoard(4,false);
	}*/
}


