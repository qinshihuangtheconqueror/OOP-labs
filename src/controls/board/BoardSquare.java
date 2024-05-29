package controls.board;

abstract class BoardSquare {
    private int BoardSquare_ID;
    private int numberOfCitizens;

    public BoardSquare(int BoardSquare_ID, int numberOfCitizens) {
        this.BoardSquare_ID = BoardSquare_ID;
        this.numberOfCitizens = numberOfCitizens;
    }

    public int getNumberOfCitizens() {
        return this.numberOfCitizens;
    }
    
    public void setNumberOfCitizens(int i) {
    	this.setNumberOfCitizens(i);
    }
    
    abstract public boolean isEmpty();
    abstract void operation();
}