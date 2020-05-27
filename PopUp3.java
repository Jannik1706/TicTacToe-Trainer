package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PopUp3 extends JFrame implements ActionListener{
	private Control control;
	private Model model;
	private View view;
	private JPanel jpMain = new JPanel();
	private JPanel jpText = new JPanel();
	private JPanel jpButtons = new JPanel();
	private JTextField [] jtfText = {new JTextField("Choose how you want to play."),new JTextField("Choose your level.")};
	private JButton [] jbtnOne = {new JButton("2 Players"),new JButton("Change to Normal")};
	private JButton [] jbtnTwo = {new JButton("Trainer"),new JButton("Change to Trainer"),
			new JButton("Beginner"),new JButton("Advanced"),new JButton("Professional"),new JButton("Change Level")};
	private JButton jbtnThree = new JButton("Continue");
	private JButton jbtnFour = new JButton("Close Game");
	private JButton [] jbtnFive = {new JButton("<<Back"),new JButton("<<Back")};
	
	/*
	 * Hier wird beim ausfuehren des PopUps die Startoberflaeche des selben erstellt
	 * @param c Bindet die Control ein
	 * @param m Bindet das Model ein
	 * @param v Bindet die View ein
	 */
	public PopUp3(Control c, Model m, View v) {
		super("Tic-Tac-Toe");
		control = c;
		model = m;
		view = v;
		this.setSize(300,150);
		this.setLocation(500,400);
		
		view.removeAllActionListeners();
		
		jpMain.setPreferredSize(new Dimension(300,150));
		jpText.setPreferredSize(new Dimension(300,60));
		jpButtons.setPreferredSize(new Dimension(300,90));
		
		jpMain.add(jpText);
		jpMain.add(jpButtons, BorderLayout.SOUTH);
		
		jpText.add(jtfText[0]);
		
		jpButtons.add(jbtnOne[0]);
		jpButtons.add(jbtnTwo[0]);
		
		
		if(model.isMenu()) {
			this.setSize(300,180);
			this.setLocation(500,400);
			
			view.removeAllActionListeners();
			
			jpMain.setPreferredSize(new Dimension(300,180));
			jpText.setPreferredSize(new Dimension(300,60));
			jpButtons.setPreferredSize(new Dimension(300,120));
			
			jbtnOne[0].setVisible(false);
			jbtnTwo[0].setVisible(false);
			
			if(model.isTrainer() || model.isTrainer2() || model.isTrainer3()) {
				jpButtons.add(jbtnOne[1]);
				jpButtons.add(jbtnTwo[5]);
			}
			else if(!model.isTrainer()) {
				jpButtons.add(jbtnTwo[1]);
			}
			jpButtons.add(jbtnThree);
			jpButtons.add(jbtnFive[0]);
			jpButtons.add(jbtnFour);
		}
		
		jtfText[0].setEditable(false);
		jtfText[1].setEditable(false);
		jbtnOne[0].addActionListener(this);
		jbtnOne[1].addActionListener(this);
		jbtnTwo[0].addActionListener(this);
		jbtnTwo[1].addActionListener(this);
		jbtnTwo[2].addActionListener(this);
		jbtnTwo[3].addActionListener(this);
		jbtnTwo[4].addActionListener(this);
		jbtnTwo[5].addActionListener(this);
		jbtnThree.addActionListener(this);
		jbtnFour.addActionListener(this);
		jbtnFive[0].addActionListener(this);
		jbtnFive[1].addActionListener(this);

		this.getContentPane().add(jpMain);
		this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		Object eS = e.getSource();
		if(eS == jbtnOne[0]) {
			model.setWinsX(0);
			model.setWinsO(0);
			model.setTrainer(false);
			model.setTrainer2(false);
			model.setTrainer3(false);
			control.restart();
			this.setVisible(false);
		}
		else if(eS == jbtnOne[1]) {
			model.setWinsX(0);
			model.setWinsO(0);
			model.setTrainer(false);
			model.setTrainer2(false);
			model.setTrainer3(false);
			control.restart();
			this.setVisible(false);
		}
		else if(eS == jbtnTwo[0]) {
			this.setSize(300,180);
			this.setLocation(500,400);
			
			jpMain.setPreferredSize(new Dimension(300,180));
			jpText.setPreferredSize(new Dimension(300,60));
			jpButtons.setPreferredSize(new Dimension(300,120));
			
			jbtnOne[0].setVisible(false);
			jbtnTwo[0].setVisible(false);
			jtfText[0].setVisible(false);
			
			jpText.add(jtfText[1]);
			jpButtons.add(jbtnTwo[2]);
			jpButtons.add(jbtnTwo[3]);
			jpButtons.add(jbtnTwo[4]);
			jpButtons.add(jbtnFive[1]);
		}
		else if(eS == jbtnTwo[1]) {
			this.setSize(300,180);
			this.setLocation(500,400);
			
			jpMain.setPreferredSize(new Dimension(300,180));
			jpText.setPreferredSize(new Dimension(300,60));
			jpButtons.setPreferredSize(new Dimension(300,120));
			
			jbtnTwo[1].setVisible(false);
			jbtnThree.setVisible(false);
			jbtnFour.setVisible(false);
			jbtnFive[0].setVisible(false);
			
			jtfText[0].setVisible(false);
			
			jpText.add(jtfText[1]);
			jpButtons.add(jbtnTwo[2]);
			jpButtons.add(jbtnTwo[3]);
			jpButtons.add(jbtnTwo[4]);
			jpButtons.add(jbtnFive[1]);
		}
		else if(eS == jbtnTwo[2]) {
			model.setWinsX(0);
			model.setWinsO(0);
			model.setTrainer(true);
			model.setTrainer2(false);
			model.setTrainer3(false);
			this.setVisible(false);
			control.restart();
		}
		else if(eS == jbtnTwo[3]) {
			model.setWinsX(0);
			model.setWinsO(0);
			model.setTrainer(false);
			model.setTrainer2(true);
			model.setTrainer3(false);
			this.setVisible(false);
			control.restart();
		}
		else if(eS == jbtnTwo[4]) {
			model.setWinsX(0);
			model.setWinsO(0);
			model.setTrainer(false);
			model.setTrainer2(false);
			model.setTrainer3(true);
			this.setVisible(false);
			control.restart();
		}
		else if(eS == jbtnTwo[5]) {
			this.setSize(300,180);
			this.setLocation(500,400);
			
			jpMain.setPreferredSize(new Dimension(300,180));
			jpText.setPreferredSize(new Dimension(300,60));
			jpButtons.setPreferredSize(new Dimension(300,120));
			
			jbtnTwo[1].setVisible(false);
			jbtnThree.setVisible(false);
			jbtnFour.setVisible(false);
			jbtnFive[0].setVisible(false);
			
			jtfText[0].setVisible(false);
			jbtnOne[1].setVisible(false);
			jbtnTwo[5].setVisible(false);
			
			jpText.add(jtfText[1]);
			jpButtons.add(jbtnTwo[2]);
			jpButtons.add(jbtnTwo[3]);
			jpButtons.add(jbtnTwo[4]);
			jpButtons.add(jbtnFive[1]);
		}
		else if(eS == jbtnThree) {
			control.restart();
			this.setVisible(false);
		}
		else if(eS == jbtnFour) {
			this.setVisible(false);
			view.setVisible(false);
		}
		else if(eS == jbtnFive[0]) {
			this.setVisible(false);
			new PopUp2(control, model, view);
		}
		else if(eS == jbtnFive[1]) {
			this.setVisible(false);
			new PopUp3(control, model, view);
		}
	}
}
