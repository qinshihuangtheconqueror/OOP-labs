package controls.board;

import java.util.ArrayList;

public class Board {
	private ArrayList<BoardSquare> listOfSquare;
	public Board(ArrayList<BoardSquare> listOfSquare) {
		super();
		this.listOfSquare = listOfSquare;
	}
	
	
	
	public ArrayList<BoardSquare> getListOfSquare() {
		return listOfSquare;
	}
	

	public void setListOfSquare(ArrayList<BoardSquare> listOfSquare) {
		this.listOfSquare = listOfSquare;
	}



	public void reset() {
		for(BoardSquare i : this.listOfSquare) {
			if(i instanceof MandarinSquare) {
				((MandarinSquare) i).setContainMandarin(true);
				i.setNumberOfCitizens(0);
			}else {
				i.setNumberOfCitizens(5);
			}
		}
	}
	
//	public boolean checkGameEnd() {
//		int flag = 0;
//		for(BoardSquare i : this.listOfSquare) {
//			if(i instanceof MandarinSquare) {
//				if(i.isEmpty() == true) flag++;
//			}
//		}
//		if(flag<2) return false;
//		return true;
//	}
}