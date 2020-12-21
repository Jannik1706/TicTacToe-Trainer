package tictactoe;

import java.util.Random;

public class Control {
	
	private View view;
	private Model model;

	public static void main(String[] args) {
		new Control();
	}
	
	/**
	 * Hier werden beim ausfueren der Control ebenfalls das Model und die View und auch drei Methoden ausgefuehrt.
	 */
	public Control() {
		model = new Model();
		view = new View(this, model);
		
		whoBegins();
		player();
		view.showBeginner();
		new PopUp3(this, model, view);
	}
	
	/**
	 * In dieser Methode werden die Spieler gewechselt.
	 */
	public void changePlayers() {
		if(!model.isTrainer()) {
			if(model.getCurrentPlayer() == 1) {
				model.setCurrentPlayer(2);
			}else if(model.getCurrentPlayer() == 2) {
				model.setCurrentPlayer(1);
			}
		}
		else{
			if(model.getCurrentPlayer()==1) {
				model.setCurrentPlayer(3);
			}else if(model.getCurrentPlayer()==3) {
				model.setCurrentPlayer(1);
			}
		}
	}

	/**
	 * Diese Methode liefert den derzeitigen Spieler, der an der Reihe ist.
	 * @return int derzeitiger Spieler
	 */
	public int player() {
		return model.getCurrentPlayer();
	}
	
	/**
	 * Diese Methode ermittelt durch Zufall den Beginner des Spiels.
	 */
	public void whoBegins() {
		Random begin = new Random();
		int beg;
		beg = 1+ begin.nextInt(2);
		model.setBeginner(beg);
		model.setCurrentPlayer(beg);
		if(model.isTrainer() || model.isTrainer2() || model.isTrainer3()) {
			model.setCurrentPlayer(1);
		}
	}
	
	/**
	 * 
	 */
	public int playerPC() {
		Random rand = new Random();
		int f_val;
		int field;
		for (int x=0; x<100; x++) {
			field = rand.nextInt(9);
			f_val = model.getField()[field/3][field%3];
			
			if (f_val==0) {
				return field;
			}
		}
		return -1;
	}
	
	/**
	 * Diese Methode ermittelt, in dem sie jede Stelle des Spielfelds abfragt, ob es einen Gewinner gibt und liefert, wenn es einen Gewinner gibt, den entsprechenden Wert zurueck.
	 * @return int 1 fuer Spieler 1 und int 2 fuer Spieler 2
	 */
	public int findWinner() {
		
		int[] res = new int[8];
		res[0] = model.getField()[0][0] +model.getField()[0][1] +model.getField()[0][2];
		res[1] = model.getField()[1][0] +model.getField()[1][1] +model.getField()[1][2];
		res[2] = model.getField()[2][0] +model.getField()[2][1] +model.getField()[2][2];
		res[3] = model.getField()[0][0] +model.getField()[1][0] +model.getField()[2][0];
		res[4] = model.getField()[0][1] +model.getField()[1][1] +model.getField()[2][1];
		res[5] = model.getField()[0][2] +model.getField()[1][2] +model.getField()[2][2];
		res[6] = model.getField()[0][0] +model.getField()[1][1] +model.getField()[2][2];
		res[7] = model.getField()[0][2] +model.getField()[1][1] +model.getField()[2][0];
		
		for(int i=0; i<=7; i++) {
			if(res[i]==3) {
				model.setWinner(1);
				return model.getWinner();
			}
			else if(res[i]==12) {
				model.setWinner(2);
				return model.getWinner();
			}
		}
		return 0;
	}
	
	public int posWinner() {
		int[] res = new int[24];
		res[0] = model.getField()[0][0] +model.getField()[0][1];
		res[1] = model.getField()[0][1] +model.getField()[0][2];
		res[2] = model.getField()[0][0] +model.getField()[0][2];
		
		res[3] = model.getField()[1][0] +model.getField()[1][1];
		res[4] = model.getField()[1][1] +model.getField()[1][2];
		res[5] = model.getField()[1][0] +model.getField()[1][2];
		
		res[6] = model.getField()[2][0] +model.getField()[2][1];
		res[7] = model.getField()[2][1] +model.getField()[2][2];
		res[8] = model.getField()[2][0] +model.getField()[2][2];
		
		res[9] = model.getField()[0][0] +model.getField()[1][0];
		res[10] = model.getField()[1][0] +model.getField()[2][0];		
		res[11] = model.getField()[0][0] +model.getField()[2][0];

		res[12] = model.getField()[0][1] +model.getField()[1][1];
		res[13] = model.getField()[1][1] +model.getField()[1][2];
		res[14] = model.getField()[0][1] +model.getField()[1][2];
		
		res[15] = model.getField()[2][0] +model.getField()[2][1];
		res[16] = model.getField()[2][1] +model.getField()[2][2];
		res[17] = model.getField()[2][0] +model.getField()[2][2];
		
		res[18] = model.getField()[0][0] +model.getField()[1][1];
		res[19] = model.getField()[1][1] +model.getField()[2][2];
		res[20] = model.getField()[0][0] +model.getField()[2][2];
		
		res[21] = model.getField()[0][2] +model.getField()[1][1];
		res[22] = model.getField()[1][1] +model.getField()[2][0];
		res[23] = model.getField()[0][2] +model.getField()[2][0];
		
		for(int i=0; i<=23; i++) {
			if(res[i]==2) {
//				System.out.println("Attention: Player 1 can win with his next step!"+res[i]);
				return res[i];
			}
		}
		return 0;
	}
	
	public boolean fField(int x, int y) {
		if(model.getField()[x][y] == 0) {
			return true;
		}
		else return false;
	}
	/*
	 * Diese Methode prueft, ob am Ende des Spiels ein Gleichstand vorliegt.
	 * @return boolean
	 */
	public boolean checkEquivalence() {
		if (findWinner() == 0 && model.getCount() >= 9) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * Diese Methode fuert die Methode restart() aus der View aus.
	 */
	public void restart() {
		view.restart();
	}
}
