package controls.board;

class MandarinSquare extends BoardSquare {
    boolean isContainMandarin;

    public MandarinSquare(int BoardSquare_ID, int numberOfCitizens, boolean isContainMandarin) {
        super(BoardSquare_ID, numberOfCitizens);
        this.isContainMandarin = isContainMandarin;
    }

    @Override
    void operation() {
        // Implementation for MandarinSquare specific operation
        System.out.println("MandarinSquare operation");
    }
}
