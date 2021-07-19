import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LudoMainMenu extends JFrame{
    JLabel lblMainMenu,lblPlayer,lblHelp,lblHelpText,lblGame;
    int player=1;
    boolean game;
    Font fontStyle;
    Color fontClr,fontClr2;
    JButton quit,btnStart,btnPlayer,btnHelp,btnBack,btnBack2,btnPlayer1,btnPlayer2,btnPlayer3,btnPlayer4,btnRealGame,btnDemoGame;
    public LudoMainMenu() {
        setLayout(null);
        setSize(700,500);
        setResizable(false);
        
        setContentPane(new JLabel((new ImageIcon(new ImageIcon("images/background.gif").getImage().getScaledInstance(700,500,Image.SCALE_DEFAULT)))));  // to add image in back ground
        
        lblMainMenu = new JLabel("MAIN MENU");
        lblMainMenu.setBounds(180,40,500,50);
        setStyleHead(lblMainMenu);
        add(lblMainMenu);
        
        btnStart= new JButton("Start");
        btnStart.setBounds(200,150,300,80);
        setStyleButton(btnStart);
        add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                showScreen(4);
            }
        });
        
        //Game
        lblGame = new JLabel("Game Type");
        lblGame.setBounds(180,40,500,50);
        setStyleHead(lblGame);
        add(lblGame);
        
        btnRealGame = new JButton("Real Game");
        btnRealGame.setBounds(200, 150, 320, 80);
        setStyleButton(btnRealGame);
        add(btnRealGame);
        btnRealGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                game = true;
                dispose();
                new LudoMainBoard(player,game);
            }
        });
        
        btnDemoGame = new JButton("Demo Game");
        btnDemoGame.setBounds(200, 250, 320, 80);
        setStyleButton(btnDemoGame);
        add(btnDemoGame);
        btnDemoGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                game = false;
                dispose();
                new LudoMainBoard(player,game);
            }
        });
        
        //Level
	    btnPlayer= new JButton("Player");
	    btnPlayer.setBounds(200,250,300,80);
	    setStyleButton(btnPlayer);
	    btnPlayer.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){	
	  	    	showScreen(2);
	  	    }
	  	});
	    add(btnPlayer);

		lblPlayer= new JLabel("Player");
    	lblPlayer.setBounds(250,40,500,50);
		setStyleHead(lblPlayer);
    	add(lblPlayer);

	    btnPlayer1= new JButton("Player 2");
	    btnPlayer1.setBounds(200,150,300,80);
	    setStyleButton(btnPlayer1);
        add(btnPlayer1);
	    btnPlayer1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		player=2;
        		toggleColorOfButton(btnPlayer1);
        	}
        });
		    
	    btnPlayer2= new JButton("Player 3");
        btnPlayer2.setBounds(200,250,300,80);
        setStyleButton(btnPlayer2);
        btnPlayer2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae){
        		player=3;
        		toggleColorOfButton(btnPlayer2);	
        	}
        });
        add(btnPlayer2);
        
        btnPlayer3= new JButton("Player 4");
        btnPlayer3.setBounds(200,350,300,80);
        setStyleButton(btnPlayer3);
        btnPlayer3.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent ae){
	       		player=4;
	       		toggleColorOfButton(btnPlayer3);	
	       	}
	    });
        add(btnPlayer3);
    
        //Help
	    btnHelp= new JButton("Help");
	    setStyleButton(btnHelp);
	    btnHelp.setBounds(200,350,300,80);	  	    
	    btnHelp.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){
	  	    	 showScreen(3);
	  	    }
	  	});
	    add(btnHelp);

	    lblHelp= new JLabel("Help");
	    lblHelp.setBounds(250,10,500,50);
		setStyleHead(lblHelp);
	    add(lblHelp);
			
		lblHelpText = new JLabel("<html>Press Space to throw arrow<br>or click on the character<br> alt+f4 to stop game any time</html>");
    	lblHelpText.setBounds(180,150,500,200);
    	setStyleOtherLabel(lblHelpText);
    	add(lblHelpText);

    	//quit
    	quit= new JButton("Quit");
    	quit.setBounds(0,350,200,100);    
    	setStyleOtherButton(quit);
    	quit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae){
    			System.exit(0);
	    	}
    	});
    	add(quit);

    	btnBack= new JButton("Back");
    	btnBack.setBounds(500,350,200,100);
    	setStyleOtherButton(btnBack);
    	btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
            	showScreen(1);
            }
    	});
    	add(btnBack);
    	setUndecorated(true);
    	showScreen(1);
    	toggleColorOfButton(btnPlayer1);
    	setVisible(true);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new LudoMainMenu();
    }
    void showScreen(int n){
    	lblMainMenu.setVisible(false);
    	btnStart.setVisible(false);
        lblGame.setVisible(false);
        btnRealGame.setVisible(false);
        btnDemoGame.setVisible(false);
        btnPlayer.setVisible(false);
        btnHelp.setVisible(false);
        lblPlayer.setVisible(false);
        btnPlayer1.setVisible(false);
        btnPlayer2.setVisible(false);
        btnPlayer3.setVisible(false);
        btnBack.setVisible(false);
        lblHelp.setVisible(false);
        lblHelpText.setVisible(false);
        if(n==1){
        	lblMainMenu.setVisible(true);
        	btnStart.setVisible(true);
        	btnPlayer.setVisible(true);
        	btnHelp.setVisible(true);
        }
        else if(n==2){
        	lblPlayer.setVisible(true);
        	btnPlayer1.setVisible(true);
        	btnPlayer2.setVisible(true);
        	btnPlayer3.setVisible(true);
        	btnBack.setVisible(true);
        }
        else if(n==3){
            lblHelp.setVisible(true);
            lblHelpText.setVisible(true);
            btnBack.setVisible(true);
        }
        else if(n==4){
            lblGame.setVisible(true);
            btnRealGame.setVisible(true);
            btnDemoGame.setVisible(true);
            btnBack.setVisible(true);
        }
    }
    void setStyleHead(JLabel cmp){
		fontStyle= new Font("Forte",Font.BOLD,50);
		cmp.setFont(fontStyle);
		fontClr= new Color(249, 2, 23);
		cmp.setForeground(fontClr);
    }
    void setStyleOtherLabel(JLabel cmp){
	    fontStyle= new Font("chiller",Font.BOLD,44);
	    cmp.setFont(fontStyle);
	    fontClr= new Color(196, 0, 78);
		cmp.setForeground(fontClr);
    }
    void setStyleButton(JButton cmp){
		fontStyle= new Font("Ravie",Font.BOLD,34);
	    cmp.setFont(fontStyle);
	    fontClr= new Color(219, 216, 212,255);  // 4th argument is the opacity
	    cmp.setForeground(fontClr); 
	    fontClr= new Color(112, 34, 37);
	    cmp.setBackground(fontClr);
	    cmp.setBorderPainted(false);
	    cmp.setFocusPainted(false);
    }
    void setStyleOtherButton(JButton cmp){
    	fontStyle= new Font("Forte",Font.BOLD,50);
    	cmp.setFont(fontStyle);
    	fontClr= new Color(249, 2, 23);
    	cmp.setForeground(fontClr); 
    	cmp.setContentAreaFilled(false);
    	cmp.setBorderPainted(false);
    	cmp.setFocusPainted(false);
    }
    void toggleColorOfButton(JButton btnPlayer){
		fontClr= new Color(3, 181, 9);
		fontClr2= new Color(112, 34, 37);
		if(btnPlayer==btnPlayer1)
		btnPlayer1.setBackground(fontClr);
		else
			btnPlayer1.setBackground(fontClr2);
		if(btnPlayer==btnPlayer2)
			btnPlayer2.setBackground(fontClr);
		else
			btnPlayer2.setBackground(fontClr2);
		if(btnPlayer==btnPlayer3)
			btnPlayer3.setBackground(fontClr);
		else
			btnPlayer3.setBackground(fontClr2);
		if(btnPlayer==btnRealGame)
			btnRealGame.setBackground(fontClr);
		else
			btnRealGame.setBackground(fontClr2);
		if(btnPlayer==btnDemoGame)
			btnDemoGame.setBackground(fontClr);
		else
			btnDemoGame.setBackground(fontClr2);
    }
}
