package controls.board;

class CitizenSquare extends BoardSquare {

    public CitizenSquare(int BoardSquare_ID, int numberOfCitizens) {
        super(BoardSquare_ID, numberOfCitizens);
    }

    @Override
    void operation() {
        // Implementation for CitizenSquare specific operation
        System.out.println("CitizenSquare operation");
    }
}
