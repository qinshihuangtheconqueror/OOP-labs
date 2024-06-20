package logic.board;

public abstract class BoardSquare {
    private int boardSquareID;
    private int numberOfCitizens;


    public BoardSquare(int BoardSquare_ID, int numberOfCitizens) {
        this.boardSquareID = BoardSquare_ID;
        this.numberOfCitizens = numberOfCitizens;
    }



    public int getboardSquareID() {
        return boardSquareID;
    }


    public int getNumberOfCitizens() {
        return this.numberOfCitizens;
    }

    public void setNumberOfCitizens(int i) {
        this.numberOfCitizens = i;
    }
    
    public void setBoardSquareID(int boardSquareID) {
		this.boardSquareID = boardSquareID;
	}

    abstract public boolean isEmpty();
}