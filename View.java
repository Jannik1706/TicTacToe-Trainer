package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame implements ActionListener{
	
	private Control control;
	private Model model;
	private JPanel jpMain = new JPanel();
	private JPanel jpScore = new JPanel();
	private JTextField[] jtfScore = {	new JTextField(""),
			new JTextField("Steps Player 1:		Wins Player 1:"),
			new JTextField("Steps Player 2:		Wins Player 2:")};
	private JPanel jpFields = new JPanel();
	private JButton [][] jbtnFields = new JButton[3][3];
	private ImageIcon x = new ImageIcon("x.png");
	private ImageIcon circle = new ImageIcon("circle.png");
	
	/**
	 * Hier wird beim ausfuehren der View die Startoberflaeche des Spiels erstellt
	 * @param c Bindet die Control ein
	 * @param m Bindet das Model ein
	 */
	public View(Control c, Model m) {
		super ("Tic-Tac-Toe");
		control = c;
		model = m;
		this.setSize(600,700);
		this.setLocation(350,40);
		
		jpMain.setPreferredSize(new Dimension(600,700));
		jpScore.setPreferredSize(new Dimension(600,80));
		jpFields.setPreferredSize(new Dimension(600,600));
		
		jpMain.add(jpScore, BorderLayout.NORTH);
		jpMain.add(jpFields, BorderLayout.CENTER);
		
		
		for(int i = 0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				jbtnFields[i][j]=new JButton("");
			}
		}
		
		jpScore.setLayout(new GridLayout(3,1));
		jpScore.add(jtfScore[0]);
		jpScore.add(jtfScore[1]);
		jpScore.add(jtfScore[2]);
		jtfScore[0].setEditable(false);
		jtfScore[1].setEditable(false);
		jtfScore[2].setEditable(false);
		jtfScore[0].setText("Player "+control.player()+" begins.");
		
		
		jpFields.setLayout(new GridLayout(3,3));
		jpFields.add(jbtnFields[0][0]);
		
		for(int i = 0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				jbtnFields[i][j].addActionListener(this);
				jpFields.add(jbtnFields[i][j]);
			}
		}
		
		this.getContentPane().add(jpMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
	}
	
	/**
	 * Beim Ausfueren dieser Methode wird in ein Textfeld hineingeschrieben, welcher Spieler die Partie beginnt.
	 */
	public void showBeginner() {
		jtfScore[0].setText("Player "+control.player()+" begins.");
	}
	
	/**
	 * Beim Ausfueren dieser Methode wird ein Neustart an der Spieloberflaeche durchgefuehrt.
	 */
	public void restart() {
		int array [][] = new int[3][3];
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				jbtnFields[i][j].removeActionListener(this);
				jbtnFields[i][j].setIcon(null);
				jbtnFields[i][j].addActionListener(this);
				array[i][j] = 0;
			}
		}
		model.setField(array);
		control.whoBegins();
		control.player();
		model.setStepX(1);
		model.setStepY(1);
		model.setCount(0);
		model.setWinner(0);
		jtfScore[0].setText("Player "+control.player()+" begins.");
		jtfScore[1].setText("Steps Player 1:		Wins Player 1: "+model.getWinsX()+"");
		jtfScore[2].setText("Steps Player 2:		Wins Player 2: "+model.getWinsO()+"");
		model.setGames(model.getGames()+1);
	}
	
	/**
	 * Hier werden die Actionlistener der verschiedenen Buttons abgefragt und jeweilige Funktionen ausgefuehrt.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object eS = e.getSource();
		int plyr = 0;
		
		int[][] array = new int[3][3];
		Icon icon = null;
		array = model.getField();
		
		/*
		 * In dieser Verzweigung werden jedem Spieler Funktionen zugeordnet.
		 */
		if(!model.isTrainer() && !model.isTrainer2() && !model.isTrainer3()) {
			if(control.player() == 1) {
				plyr = 1;
				icon = x;
				model.setStepX(model.getStepX()+1);
				jtfScore[1].setText("Steps Player 1: "+(model.getStepX()-1)+"	Wins Player 1: "+model.getWinsX()+"");
			}
			else if(control.player() == 2) {
				plyr = 4;
				icon = circle;
				model.setStepY(model.getStepY()+1);
				jtfScore[2].setText("Steps Player 2: "+(model.getStepY()-1)+"	Wins Player 2: "+model.getWinsO()+"");			
			}
			control.changePlayers();
		}
		else if(model.isTrainer() || model.isTrainer2() || model.isTrainer3()){
			if(control.player() == 1) {
				plyr = 1;
				icon = x;
				model.setStepX(model.getStepX()+1);
				jtfScore[1].setText("Steps Player 1: "+(model.getStepX()-1)+"	Wins Player 1: "+model.getWinsX()+"");
				for(int i=0; i<=2; i++) {
					for(int j=0; j<=2; j++) {
						if(eS == jbtnFields[i][j]) {
							array[i][j]=plyr;
							jbtnFields[i][j].setIcon(icon);
							jbtnFields[i][j].removeActionListener(this);
						}
					}
				}
				model.setField(array);
				
				control.posWinner();
				
				if(model.isTrainer() && model.getStepX()<=5 && model.getWinner()==0) {
					playPc();
				}
				else if(model.isTrainer2() && model.getStepX()<=5 && model.getWinner()==0) {
					playPc2();
				}
				else if(model.isTrainer3() && model.getStepX()<=5 && model.getWinner()==0) {
					playPc3();
				}
			}
		}
		
		if(!model.isTrainer()) {
			for(int i=0; i<=2; i++) {
				for(int j=0; j<=2; j++) {
					if(eS == jbtnFields[i][j]) {
						array[i][j]=plyr;
						jbtnFields[i][j].setIcon(icon);
						jbtnFields[i][j].removeActionListener(this);
					}
				}
			}
			model.setField(array);
			model.setCount(model.getCount()+1);
		}
		
		/*
		 * In dieser Verzweigung wird das PopUp ausgefuehrt, wenn Spieler 1 oder 2 gewonnen haben oder wenn es mit Gleichstand endet.
		 */
		if(control.findWinner() == 1) {
			removeAllActionListeners();
			new PopUp2(control, model, this);
		}
		else if (control.findWinner() == 2) {
			removeAllActionListeners();
			new PopUp2(control, model, this);
		}
		else if(control.checkEquivalence()) {
			removeAllActionListeners();
			new PopUp2(control, model, this);
		}
		else if(model.getWinsX() == 5 || model.getWinsO() == 5) {
			removeAllActionListeners();
			new PopUp2(control, model, this);
		}
	}
	
	/**
	 * Diese Methode beinhaltet den Algorithmus des einfachsten Computer-Gegners (Setzung per Zufall)
	 */
	public void playPc() {
		int freeF = 0;
		
		model.setStepY(model.getStepY()+1);
		jtfScore[2].setText("Steps Player 2: "+(model.getStepY()-1)+"	Wins Player 2: "+model.getWinsO()+"");

		freeF = control.playerPC();
		if(freeF != -1) {
			model.setFieldVal(4, freeF/3, freeF%3);
			jbtnFields[freeF/3][freeF%3].setIcon(circle);
			jbtnFields[freeF/3][freeF%3].removeActionListener(this);
			model.setCount(model.getCount()+1);
		}
		else System.out.println("Error, no free field could be found!");
	}
	
	/**
	 * Diese Methode beinhaltet den Algorithmus des mittleren Computer-Gegners (Verhinderung eines Gewinns)
	 */
	public void playPc2() {
		
		int[][] array = new int[3][3];
		int plyr = 4;
		
		if(model.getField()[0][0]==1 && model.getField()[0][1]==1 && control.playerPC() != -1 && control.fField(0,2)) {
			model.setFieldVal(4, 0, 2);
			array[0][2]=plyr;
			jbtnFields[0][2].setIcon(circle);
			jbtnFields[0][2].removeActionListener(this);
		}
		else if(model.getField()[0][1]==1 && model.getField()[0][2]==1 && control.playerPC() != -1 && control.fField(0,0)) {
			model.setFieldVal(4, 0, 0);
			array[0][0]=plyr;
			jbtnFields[0][0].setIcon(circle);
			jbtnFields[0][0].removeActionListener(this);
		}
		else if(model.getField()[0][0]==1 && model.getField()[0][2]==1 && control.playerPC() != -1 && control.fField(0,1)) {
			model.setFieldVal(4, 0, 1);
			array[0][1]=plyr;
			jbtnFields[0][1].setIcon(circle);
			jbtnFields[0][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[1][0]==1 && model.getField()[1][1]==1 && control.playerPC() != -1 && control.fField(1,2)) {
			model.setFieldVal(4, 1, 2);
			array[1][2]=plyr;
			jbtnFields[1][2].setIcon(circle);
			jbtnFields[1][2].removeActionListener(this);
		}
		else if(model.getField()[1][1]==1 && model.getField()[1][2]==1 && control.playerPC() != -1 && control.fField(1,0)) {
			model.setFieldVal(4, 1, 0);
			array[1][0]=plyr;
			jbtnFields[1][0].setIcon(circle);
			jbtnFields[1][0].removeActionListener(this);
		}
		else if(model.getField()[1][0]==1 && model.getField()[1][2]==1 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[2][0]==1 && model.getField()[2][1]==1 && control.playerPC() != -1 && control.fField(2,2)) {
			model.setFieldVal(4, 2, 2);
			array[2][2]=plyr;
			jbtnFields[2][2].setIcon(circle);
			jbtnFields[2][2].removeActionListener(this);
		}
		else if(model.getField()[2][1]==1 && model.getField()[2][2]==1 && control.playerPC() != -1 && control.fField(2,0)) {
			model.setFieldVal(4, 2, 0);
			array[2][0]=plyr;
			jbtnFields[2][0].setIcon(circle);
			jbtnFields[2][0].removeActionListener(this);
		}
		else if(model.getField()[2][0]==1 && model.getField()[2][2]==1 && control.playerPC() != -1 && control.fField(2,1)) {
			model.setFieldVal(4, 2, 1);
			array[2][1]=plyr;
			jbtnFields[2][1].setIcon(circle);
			jbtnFields[2][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][0]==1 && model.getField()[1][0]==1 && control.playerPC() != -1 && control.fField(2,0)) {
			model.setFieldVal(4, 2, 0);
			array[2][0]=plyr;
			jbtnFields[2][0].setIcon(circle);
			jbtnFields[2][0].removeActionListener(this);
		}
		else if(model.getField()[1][0]==1 && model.getField()[2][0]==1 && control.playerPC() != -1 && control.fField(0,0)) {
			model.setFieldVal(4, 0, 0);
			array[0][0]=plyr;
			jbtnFields[0][0].setIcon(circle);
			jbtnFields[0][0].removeActionListener(this);
		}
		else if(model.getField()[0][0]==1 && model.getField()[2][0]==1 && control.playerPC() != -1 && control.fField(1,0)) {
			model.setFieldVal(4, 1, 0);
			array[1][0]=plyr;
			jbtnFields[1][0].setIcon(circle);
			jbtnFields[1][0].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][1]==1 && model.getField()[1][1]==1 && control.playerPC() != -1 && control.fField(2,1)) {
			model.setFieldVal(4, 2, 1);
			array[2][1]=plyr;
			jbtnFields[2][1].setIcon(circle);
			jbtnFields[2][1].removeActionListener(this);
		}
		else if(model.getField()[1][1]==1 && model.getField()[2][1]==1 && control.playerPC() != -1 && control.fField(0,1)) {
			model.setFieldVal(4, 0, 1);
			array[0][1]=plyr;
			jbtnFields[0][1].setIcon(circle);
			jbtnFields[0][1].removeActionListener(this);
		}
		else if(model.getField()[0][1]==1 && model.getField()[2][1]==1 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][2]==1 && model.getField()[1][2]==1 && control.playerPC() != -1 && control.fField(2,2)) {
			model.setFieldVal(4, 2, 2);
			array[2][2]=plyr;
			jbtnFields[2][2].setIcon(circle);
			jbtnFields[2][2].removeActionListener(this);
		}
		else if(model.getField()[1][2]==1 && model.getField()[2][2]==1 && control.playerPC() != -1 && control.fField(0,2)) {
			model.setFieldVal(4, 0, 2);
			array[0][2]=plyr;
			jbtnFields[0][2].setIcon(circle);
			jbtnFields[0][2].removeActionListener(this);
		}
		else if(model.getField()[0][2]==1 && model.getField()[2][2]==1 && control.playerPC() != -1 && control.fField(1,2)) {
			model.setFieldVal(4, 1, 2);
			array[1][2]=plyr;
			jbtnFields[1][2].setIcon(circle);
			jbtnFields[1][2].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][0]==1 && model.getField()[1][1]==1 && control.playerPC() != -1 && control.fField(2,2)) {
			model.setFieldVal(4, 2, 2);
			array[2][2]=plyr;
			jbtnFields[2][2].setIcon(circle);
			jbtnFields[2][2].removeActionListener(this);
		}
		else if(model.getField()[1][1]==1 && model.getField()[2][2]==1 && control.playerPC() != -1 && control.fField(0,0)) {
			model.setFieldVal(4, 0, 0);
			array[0][0]=plyr;
			jbtnFields[0][0].setIcon(circle);
			jbtnFields[0][0].removeActionListener(this);
		}
		else if(model.getField()[0][0]==1 && model.getField()[2][2]==1 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][2]==1 && model.getField()[1][1]==1 && control.playerPC() != -1 && control.fField(2,0)) {
			model.setFieldVal(4, 2, 0);
			array[2][0]=plyr;
			jbtnFields[2][0].setIcon(circle);
			jbtnFields[2][0].removeActionListener(this);
		}
		else if(model.getField()[1][1]==1 && model.getField()[2][0]==1 && control.playerPC() != -1 && control.fField(0,2)) {
			model.setFieldVal(4, 0, 2);
			array[0][2]=plyr;
			jbtnFields[0][2].setIcon(circle);
			jbtnFields[0][2].removeActionListener(this);
		}
		else if(model.getField()[0][2]==1 && model.getField()[2][0]==1 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		else {
			int freeF = 0;
			freeF = control.playerPC();
			if(freeF != -1) {
				model.setFieldVal(4, freeF/3, freeF%3);
				jbtnFields[freeF/3][freeF%3].setIcon(circle);
				jbtnFields[freeF/3][freeF%3].removeActionListener(this);
				array[freeF/3][freeF%3]=plyr;
			}
			else {
				System.out.println("Error, no free field could be found!");
			}
				
		}
		model.setStepY(model.getStepY()+1);
		model.setCount(model.getCount()+1);
		jtfScore[2].setText("Steps Player 2: "+(model.getStepY()-1)+"	Wins Player 2: "+model.getWinsO()+"");
		model.setField(array);
	}
	
	/**
	 * Diese Methode beinhaltet den Algorithmus des schwierigsten Computer-Gegners (Verhinderung eines Gewinns/selbst gewinnen)
	 */
	public void playPc3() {
		
		int[][] array = new int[3][3];
		int plyr = 4;
		
		if(model.getField()[0][0]==4 && model.getField()[0][1]==4 && control.playerPC() != -1 && control.fField(0,2)) {
			model.setFieldVal(4, 0, 2);
			array[0][2]=plyr;
			jbtnFields[0][2].setIcon(circle);
			jbtnFields[0][2].removeActionListener(this);
		}
		else if(model.getField()[0][1]==4 && model.getField()[0][2]==4 && control.playerPC() != -1 && control.fField(0,0)) {
			model.setFieldVal(4, 0, 0);
			array[0][0]=plyr;
			jbtnFields[0][0].setIcon(circle);
			jbtnFields[0][0].removeActionListener(this);
		}
		else if(model.getField()[0][0]==4 && model.getField()[0][2]==4 && control.playerPC() != -1 && control.fField(0,1)) {
			model.setFieldVal(4, 0, 1);
			array[0][1]=plyr;
			jbtnFields[0][1].setIcon(circle);
			jbtnFields[0][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[1][0]==4 && model.getField()[1][1]==4 && control.playerPC() != -1 && control.fField(1,2)) {
			model.setFieldVal(4, 1, 2);
			array[1][2]=plyr;
			jbtnFields[1][2].setIcon(circle);
			jbtnFields[1][2].removeActionListener(this);
		}
		else if(model.getField()[1][1]==4 && model.getField()[1][2]==4 && control.playerPC() != -1 && control.fField(1,0)) {
			model.setFieldVal(4, 1, 0);
			array[1][0]=plyr;
			jbtnFields[1][0].setIcon(circle);
			jbtnFields[1][0].removeActionListener(this);
		}
		else if(model.getField()[1][0]==4 && model.getField()[1][2]==4 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[2][0]==4 && model.getField()[2][1]==4 && control.playerPC() != -1 && control.fField(2,2)) {
			model.setFieldVal(4, 2, 2);
			array[2][2]=plyr;
			jbtnFields[2][2].setIcon(circle);
			jbtnFields[2][2].removeActionListener(this);
		}
		else if(model.getField()[2][1]==4 && model.getField()[2][2]==4 && control.playerPC() != -1 && control.fField(2,0)) {
			model.setFieldVal(4, 2, 0);
			array[2][0]=plyr;
			jbtnFields[2][0].setIcon(circle);
			jbtnFields[2][0].removeActionListener(this);
		}
		else if(model.getField()[2][0]==4 && model.getField()[2][2]==4 && control.playerPC() != -1 && control.fField(2,1)) {
			model.setFieldVal(4, 2, 1);
			array[2][1]=plyr;
			jbtnFields[2][1].setIcon(circle);
			jbtnFields[2][1].removeActionListener(this);
		}

		
		else if(model.getField()[0][0]==4 && model.getField()[1][0]==4 && control.playerPC() != -1 && control.fField(2,0)) {
			model.setFieldVal(4, 2, 0);
			array[2][0]=plyr;
			jbtnFields[2][0].setIcon(circle);
			jbtnFields[2][0].removeActionListener(this);
		}
		else if(model.getField()[1][0]==4 && model.getField()[2][0]==4 && control.playerPC() != -1 && control.fField(0,0)) {
			model.setFieldVal(4, 0, 0);
			array[0][0]=plyr;
			jbtnFields[0][0].setIcon(circle);
			jbtnFields[0][0].removeActionListener(this);
		}
		else if(model.getField()[0][0]==4 && model.getField()[2][0]==4 && control.playerPC() != -1 && control.fField(1,0)) {
			model.setFieldVal(4, 1, 0);
			array[1][0]=plyr;
			jbtnFields[1][0].setIcon(circle);
			jbtnFields[1][0].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][1]==4 && model.getField()[1][1]==4 && control.playerPC() != -1 && control.fField(2,1)) {
			model.setFieldVal(4, 2, 1);
			array[2][1]=plyr;
			jbtnFields[2][1].setIcon(circle);
			jbtnFields[2][1].removeActionListener(this);
		}
		else if(model.getField()[1][1]==4 && model.getField()[2][1]==4 && control.playerPC() != -1 && control.fField(0,1)) {
			model.setFieldVal(4, 0, 1);
			array[0][1]=plyr;
			jbtnFields[0][1].setIcon(circle);
			jbtnFields[0][1].removeActionListener(this);
		}
		else if(model.getField()[0][1]==4 && model.getField()[2][1]==4 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][0]==4 && model.getField()[1][1]==4 && control.playerPC() != -1 && control.fField(2,2)) {
			model.setFieldVal(4, 2, 2);
			array[2][2]=plyr;
			jbtnFields[2][2].setIcon(circle);
			jbtnFields[2][2].removeActionListener(this);
		}
		else if(model.getField()[1][1]==4 && model.getField()[2][2]==4 && control.playerPC() != -1 && control.fField(0,0)) {
			model.setFieldVal(4, 0, 0);
			array[0][0]=plyr;
			jbtnFields[0][0].setIcon(circle);
			jbtnFields[0][0].removeActionListener(this);
		}
		else if(model.getField()[0][0]==4 && model.getField()[2][2]==4 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		
		
		else if(model.getField()[0][2]==4 && model.getField()[1][1]==4 && control.playerPC() != -1 && control.fField(2,0)) {
			model.setFieldVal(4, 2, 0);
			array[2][0]=plyr;
			jbtnFields[2][0].setIcon(circle);
			jbtnFields[2][0].removeActionListener(this);
		}
		else if(model.getField()[1][1]==4 && model.getField()[2][0]==4 && control.playerPC() != -1 && control.fField(0,2)) {
			model.setFieldVal(4, 0, 2);
			array[0][2]=plyr;
			jbtnFields[0][2].setIcon(circle);
			jbtnFields[0][2].removeActionListener(this);
		}
		else if(model.getField()[0][2]==4 && model.getField()[2][0]==4 && control.playerPC() != -1 && control.fField(1,1)) {
			model.setFieldVal(4, 1, 1);
			array[1][1]=plyr;
			jbtnFields[1][1].setIcon(circle);
			jbtnFields[1][1].removeActionListener(this);
		}
		else playPc2();
	}
	
	/**
	 * Diese Methode entfernt alle Actionlistener von den Buttons.
	 */
	public void removeAllActionListeners() {
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				jbtnFields[i][j].removeActionListener(this);
			}
		}
	}
}
