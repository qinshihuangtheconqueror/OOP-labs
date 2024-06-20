package logic.board;

public class CitizenSquare extends BoardSquare {

    public CitizenSquare(int BoardSquare_ID, int numberOfCitizens) {
        super(BoardSquare_ID, numberOfCitizens);
    }
    
    public CitizenSquare(CitizenSquare cs) {
    	super(cs.getboardSquareID(), cs.getNumberOfCitizens());
    }
    
    public boolean isEmpty() {
    	if(this.getNumberOfCitizens()==0) return true;
    	else return false;
    }
}
