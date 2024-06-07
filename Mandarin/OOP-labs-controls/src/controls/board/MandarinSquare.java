package controls.board;

public class MandarinSquare extends BoardSquare {
    public boolean IsContainMandarin;

    public MandarinSquare(int BoardSquare_ID, int numberOfCitizens, boolean isContainMandarin) {
        super(BoardSquare_ID, numberOfCitizens);
        this.IsContainMandarin = isContainMandarin;
    }
    
    
    
    public boolean isContainMandarin() {
		return IsContainMandarin;
	}

	public void setContainMandarin(boolean isContainMandarin) {
		this.IsContainMandarin = isContainMandarin;
	}



	public boolean isEmpty() {
    	if(IsContainMandarin==false && this.getNumberOfCitizens()==0) return true;
    	else return false;
    }

    @Override
    void operation() {
        // Implementation for MandarinSquare specific operation
        System.out.println("MandarinSquare operation");
    }
}
