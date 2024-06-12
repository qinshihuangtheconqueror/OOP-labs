package controls.board;

import java.util.ArrayList;

public class Board {
	private ArrayList<BoardSquare> listOfSquare;
	public Board(ArrayList<BoardSquare> listOfSquare) {
		super();
		this.listOfSquare = listOfSquare;
	}
	
	public Board() {
		listOfSquare = new ArrayList<BoardSquare>();
	}
	
	public Board(Board b) {
		ArrayList<BoardSquare> bss = new ArrayList<BoardSquare>();
		for(BoardSquare i : b.getListOfSquare()) {
			if(i instanceof MandarinSquare) {
				MandarinSquare sq = new MandarinSquare((MandarinSquare) i);
				bss.add(sq);
			}else {
				CitizenSquare sq = new CitizenSquare((CitizenSquare) i);
				bss.add(sq);
			}
		}
		this.listOfSquare = bss;
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
}