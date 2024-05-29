package controls.board;

public class CitizenSquare extends BoardSquare {

    public CitizenSquare(int BoardSquare_ID, int numberOfCitizens) {
        super(BoardSquare_ID, numberOfCitizens);
    }
    
    public boolean isEmpty() {
    	if(this.getNumberOfCitizens()==0) return true;
    	else return false;
    }

    @Override
    void operation() {
        // Implementation for CitizenSquare specific operation
        System.out.println("CitizenSquare operation");
    }
}
