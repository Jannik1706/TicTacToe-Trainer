package tictactoe;

public class Model {

	private int winsX;
	private int winsO;
	private int games;
	private int stepX;
	private int stepY;
	private int[][] field = new int [3][3];
	private int currentPlayer;
	private int beginner;
	private int count;
	private int winner;
	private boolean trainer;
	private boolean trainer2;
	private boolean trainer3;
	private boolean posWinner;
	private boolean menu;
	
	/**
	 * Hier werden die Variablen alle auf den Startwert gesetzt.
	 */
	public Model() {
		winsX = 0;
		winsO = 0;
		games = 0;
		stepX = 1;
		stepY = 1;
		count = 0;
		winner = 0;
		setTrainer(false);
		setTrainer2(false);
		setTrainer3(false);
		setPosWinner(false);
		setMenu(false);
		currentPlayer = 0;
		field [0][0] = 0;
		field [0][1] = 0;
		field [0][2] = 0;
		field [1][0] = 0;
		field [1][1] = 0;
		field [1][2] = 0;
		field [2][0] = 0;
		field [2][1] = 0;
		field [2][2] = 0;
	}
	
	/**
	 * Hier koennen die Felder in einem Array gesetzt und ausgegeben werden.
	 */
	public int[][] getField() {
		return field;
	}
	
	public void setField(int[][] field) {
		this.field = field;
	}
	
	public void setFieldVal(int val, int row, int col) {
		this.field[row][col] = val;
	}
	
	
	
	/**
	 * Hier koennen die gewonnenen Spiele von Spieler 1 gesetzt und ausgegeben werden.
	 */
	public int getWinsX() {
		return winsX;
	}

	public void setWinsX(int winsX) {
		this.winsX = winsX;
	}

	/**
	 * Hier koennen die gewonnenen Spiele von Spieler 2 gesetzt und ausgegeben werden.
	 */
	public int getWinsO() {
		return winsO;
	}

	public void setWinsO(int winsO) {
		this.winsO = winsO;
	}

	/**
	 * Hier kann die Spielanzahl gesetzt und ausgegeben werden.
	 */
	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}
	
	/**
	 * Hier kann jeder Schritt der einzelnen Spieler gesetzt und ausgegeben werden.
	 */
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Hier wird fuer den Gewinner einer Partie ein jeweiliger int gesetzt und ausgegeben.
	 */
	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * Hier koennen die Schritte von Spieler 1 gesetzt und ausgegeben werden.
	 */
	public int getStepX() {
		return stepX;
	}

	public void setStepX(int stepX) {
		this.stepX = stepX;
	}

	/**
	 * Hier koennen die Schritte von Spieler 2 gesetzt und ausgegeben werden.
	 */
	public int getStepY() {
		return stepY;
	}

	public void setStepY(int stepY) {
		this.stepY = stepY;
	}

	/**
	 * Hier kann der Beginner der Partie gesetzt und ausgegeben werden.
	 */
	public int getBeginner() {
		return beginner;
	}

	public void setBeginner(int beginner) {
		this.beginner = beginner;
	}

	/**
	 * Hier kann der aktuelle Spieler gesetzt und ausgegeben werden.
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isTrainer() {
		return trainer;
	}

	public void setTrainer(boolean trainer) {
		this.trainer = trainer;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public boolean isTrainer2() {
		return trainer2;
	}

	public void setTrainer2(boolean trainer2) {
		this.trainer2 = trainer2;
	}

	public boolean isTrainer3() {
		return trainer3;
	}

	public void setTrainer3(boolean trainer3) {
		this.trainer3 = trainer3;
	}

	public boolean isPosWinner() {
		return posWinner;
	}

	public void setPosWinner(boolean posWinner) {
		this.posWinner = posWinner;
	}
}

