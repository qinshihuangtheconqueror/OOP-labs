package controls.board;

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

    abstract public boolean isEmpty();
    abstract void operation();
}