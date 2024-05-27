package controls.board;

abstract class BoardSquare {
    int BoardSquare_ID;
    int numberOfCitizens;

    public BoardSquare(int BoardSquare_ID, int numberOfCitizens) {
        this.BoardSquare_ID = BoardSquare_ID;
        this.numberOfCitizens = numberOfCitizens;
    }

    public int getNumberOfStones() {
        return numberOfCitizens;
    }

    abstract void operation();
}