package controls.board;

public class MandarinSquare extends BoardSquare {
    private boolean isContainMandarin;

    public MandarinSquare(int BoardSquare_ID, int numberOfCitizens, boolean isContainMandarin) {
        super(BoardSquare_ID, numberOfCitizens);
        this.isContainMandarin = isContainMandarin;
    }
    
    
    
    public boolean isContainMandarin() {
		return isContainMandarin;
	}

	public void setContainMandarin(boolean isContainMandarin) {
		this.isContainMandarin = isContainMandarin;
	}



	public boolean isEmpty() {
    	if(isContainMandarin==false && this.getNumberOfCitizens()==0) return true;
    	else return false;
    }

    @Override
    void operation() {
        // Implementation for MandarinSquare specific operation
        System.out.println("MandarinSquare operation");
    }
}
