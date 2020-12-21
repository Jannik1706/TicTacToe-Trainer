package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PopUp2 extends JFrame implements ActionListener{
	private Control control;
	private Model model;
	private View view;
	private JPanel jpMain = new JPanel();
	private JPanel jpText = new JPanel();
	private JPanel jpButtons = new JPanel();
	private JTextField jtfText = new JTextField("");
	private JButton jbtnRestart = new JButton("Next Game");
	private JButton jbtnMenu = new JButton("Menu");
	private JButton jbtnEnd = new JButton("Close Game");
	
	/**
	 * Hier wird beim ausfuehren des PopUps die Startoberflaeche des selben erstellt
	 * @param c Bindet die Control ein
	 * @param m Bindet das Model ein
	 * @param v Bindet die View ein
	 */
	public PopUp2(Control c, Model m, View v) {
		super("Tic-Tac-Toe");
		control = c;
		model = m;
		view = v;
		this.setSize(300,180);
		this.setLocation(500,400);
		actualScore();
		
		jpMain.setPreferredSize(new Dimension(300,150));
		jpText.setPreferredSize(new Dimension(300,60));
		jpButtons.setPreferredSize(new Dimension(300,90));
		
		jpMain.add(jpText);
		jpMain.add(jpButtons, BorderLayout.SOUTH);
		
		jpText.add(jtfText);
		
		jpButtons.add(jbtnRestart);
		jpButtons.add(jbtnMenu);
		jpButtons.add(jbtnEnd);
		
		jtfText.setEditable(false);
		jbtnRestart.addActionListener(this);
		jbtnMenu.addActionListener(this);
		jbtnEnd.addActionListener(this);
		
		this.getContentPane().add(jpMain);
		this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
	}
	
	/**
	 * In dieser Methode werden die Actionlistener der Buttons abgefragt und jeweilige Funktionen ausgefuehrt.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object eS = e.getSource();
		if(eS == jbtnRestart) {
			control.restart();
			this.setVisible(false);
		}
		else if(eS == jbtnMenu) {
			this.setVisible(false);
			model.setMenu(true);
			new PopUp3(control, model, view);
		}
		else if(eS == jbtnEnd) {
			this.setVisible(false);
			view.setVisible(false);
		}
	}

	/**
	 * Diese Methode prueft den aktuellen Spielstand und fuehrt daf�r die jeweiligen Funktionen aus.
	 */
	public void actualScore() {
		if(model.getWinner() == 1) {
			jtfText.setText("Well done, Player 1!");
			model.setWinsX(model.getWinsX()+1);
		}
		else if(model.getWinner() == 2) {
			jtfText.setText("Well done, Player 2!");
			model.setWinsO(model.getWinsO()+1);
		}
		else if(model.getCount() >= 9) {
			jtfText.setText("Draw, no winner!");
		}
		else if(model.getWinsX() == 5 || model.getWinsO() == 5) {
			String pl = "";
			if(model.getWinsX() == 5) {
				pl = "Player 1";
				model.setWinsX(0);
				model.setWinsO(0);
			}
			else if(model.getWinsO() == 5) {
				pl = "Player 2";
				model.setWinsX(0);
				model.setWinsO(0);
			}
			jtfText.setText("Player "+pl+" has won five games!");
		}
	}

}
