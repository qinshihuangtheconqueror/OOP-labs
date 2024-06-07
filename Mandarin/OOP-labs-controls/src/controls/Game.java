package controls;

import controls.board.Board;
import controls.board.MandarinSquare;
import controls.player.Player;

public class Game {
	private Board myBoard;
	private Player player1;
	private Player player2;
	private boolean isP1Turn;

	public Game(Board myBoard, Player player1, Player player2, boolean isP1Turn) {
		super();
		this.myBoard = myBoard;
		this.player1 = player1;
		this.player2 = player2;
		this.isP1Turn = isP1Turn;
	}

	public boolean isP1Turn() {
		return isP1Turn;
	}

	public void setP1Turn(boolean isP1Turn) {
		this.isP1Turn = isP1Turn;
	}

	public Board getMyBoard() {
		return myBoard;
	}

	public void setMyBoard(Board myBoard) {
		this.myBoard = myBoard;
	}


	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public void restart() {
		myBoard.reset();
	}

	public boolean isEndGame() {
		MandarinSquare ms1 = (MandarinSquare) this.getMyBoard().getListOfSquare().get(0);
		MandarinSquare ms2 = (MandarinSquare) this.getMyBoard().getListOfSquare().get(6);
		if (ms1.isContainMandarin() == false && ms2.isContainMandarin() == false)
			return true;
		else
			return false;
	}

	public Player winningPlayer() {
		if (player1.getPoint() > player2.getPoint())
			return player1;
		else if (player1.getPoint() < player2.getPoint())
			return player2;
		else
			return null;
	}


}