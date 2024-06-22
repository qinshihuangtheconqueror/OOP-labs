package logic.player;
import java.util.ArrayList;

import logic.board.*;

public class Player {
	private int playerID;
	private int point;
	private ArrayList<BoardSquare> validBoardSquare;

	public Player(int playerID, int point) {
		super();
		this.playerID = playerID;
		this.point = point;
	}

	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public void setValidBoardSquare(ArrayList<BoardSquare> validBoardSquare) {
		this.validBoardSquare = validBoardSquare;
	}

	// Whether the chosen square is in valid board square of the player
	public boolean isValidMove(Board b, int choosenSquareID) {
		for (BoardSquare i : this.validBoardSquare) {
			if (i.getboardSquareID() == choosenSquareID && i.getNumberOfCitizens() > 0) return true;
		}
		return false;
	}
	
	public ArrayList<BoardSquare> getValidBoardSquare() {
		return validBoardSquare;
	}

	public void captureSquare(ArrayList<BoardSquare> bss, int currentSquareID, boolean isLeftMove) {
		ArrayList<BoardSquare> listOfSquare = bss;

		// Get the target square and the current square
		BoardSquare currentSquare = listOfSquare.get(currentSquareID);
		int targetSquareID;
		BoardSquare targetSquare;
		if (isLeftMove == true) {
			if (currentSquareID == 11) {
				targetSquareID = 0;
				targetSquare = listOfSquare.get(targetSquareID);
			} else {
				targetSquareID = currentSquareID + 1;
				targetSquare = listOfSquare.get(targetSquareID);
			}
		} else {
			if (currentSquareID == 0) {
				targetSquareID = 11;
				targetSquare = listOfSquare.get(targetSquareID);
			} else {
				targetSquareID = currentSquareID - 1;
				targetSquare = listOfSquare.get(targetSquareID);
			}
		}

		// Win the target square
		if (targetSquare instanceof MandarinSquare) {
			MandarinSquare targetMandarinSquare = (MandarinSquare) targetSquare;
			this.point += targetMandarinSquare.getNumberOfCitizens();
			targetMandarinSquare.setNumberOfCitizens(0);
			if (targetMandarinSquare.isContainMandarin() == true) {
				this.point += 5;
				targetMandarinSquare.setContainMandarin(false);
			}
			// update the target square (Mandarin square)
			listOfSquare.set(targetSquareID, targetMandarinSquare);
		} else {
			if (targetSquare.isEmpty() == false) {
				this.point += targetSquare.getNumberOfCitizens();
				targetSquare.setNumberOfCitizens(0);
			}
			// update the target square (Citizen Square)
			listOfSquare.set(targetSquareID, targetSquare);
		}
		// Update the list of Square in the board
		bss = listOfSquare;
	}
}