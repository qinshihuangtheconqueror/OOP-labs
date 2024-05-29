package controls.player;
import java.util.ArrayList;

import controls.board.*;

public class Player {
	private int playerID;
	private int point;
	private ArrayList<BoardSquare> validBoardSquare;

    public Player(int playerID, int point) {
		super();
		this.playerID = playerID;
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public void setValidBoardSquare(ArrayList<BoardSquare> validBoardSquare) {
		this.validBoardSquare = validBoardSquare;
	}


	public void makeMove(Board b, int choosenSquareID, boolean isLeftMove) {
    	// Whether the choosen square is in valid board square of the player
    	int flag = 0;
    	for(BoardSquare i : this.validBoardSquare) {
    		if(i.getboardSquareID()==choosenSquareID) {
    			flag += 1;
    			break;
    		}
    	}
    	if(b.getListOfSquare().get(choosenSquareID).getNumberOfCitizens()>0) {
    		flag += 1;
    	}
    	if(flag == 2) {
    		ArrayList<BoardSquare> bss = b.getListOfSquare();
    		BoardSquare choosenSquare = b.getListOfSquare().get(choosenSquareID);
    		int citizens = choosenSquare.getNumberOfCitizens();
    		int currentSquareID = choosenSquareID;
    		while(citizens>0) {
    			citizens--;
    			if(isLeftMove == true) {
    				if(currentSquareID == 11) currentSquareID = 0;
    				else currentSquareID++;
    				bss.get(currentSquareID).setNumberOfCitizens(bss.get(currentSquareID).getNumberOfCitizens()+1);
    			}else {
    				if(currentSquareID == 0) currentSquareID = 11;
    				else currentSquareID--;
    				bss.get(currentSquareID).setNumberOfCitizens(bss.get(currentSquareID).getNumberOfCitizens()+1);
    			}
    			
    			// decide if the turn is continued or stopped (get point or not)
    			if(citizens==0) {
    				if(isLeftMove == true) {
    					if(currentSquareID == 11) currentSquareID = 0;
        				else currentSquareID++;
    					if(bss.get(currentSquareID).getNumberOfCitizens()>0) {
    						// continue
    						citizens = bss.get(currentSquareID).getNumberOfCitizens();
    						bss.get(currentSquareID).setNumberOfCitizens(0);
    					}else if(bss.get(currentSquareID).getNumberOfCitizens()==0) {
    						// get point
    						if(currentSquareID == 11) currentSquareID = 0;
            				else currentSquareID++;
    						if(currentSquareID != 0 && currentSquareID != 6) {
    							if(bss.get(currentSquareID).getNumberOfCitizens()>0) {
        							this.point+=bss.get(currentSquareID).getNumberOfCitizens();
        							bss.get(currentSquareID).setNumberOfCitizens(0);
        						}
    						}else {
    							if(bss.get(currentSquareID).getNumberOfCitizens()>5) {
    								MandarinSquare ms = (MandarinSquare) bss.get(currentSquareID);
    								if(ms.isContainMandarin()==true) {
    									ms.setContainMandarin(false);
    									this.point+=10;
    								}
    								this.point+=bss.get(currentSquareID).getNumberOfCitizens();
        							ms.setNumberOfCitizens(0);
        							bss.add(currentSquareID, ms);
    							}
    						}
    					}
    				}else {
    					if(currentSquareID == 0) currentSquareID = 11;
        				else currentSquareID--;
    					if(bss.get(currentSquareID).getNumberOfCitizens()>0) {
    						// continue
    						citizens = bss.get(currentSquareID).getNumberOfCitizens();
    						bss.get(currentSquareID).setNumberOfCitizens(0);
    					}else if(bss.get(currentSquareID).getNumberOfCitizens()==0) {
    						// get point
    						if(currentSquareID == 0) currentSquareID = 11;
            				else currentSquareID--;
    						if(currentSquareID != 0 && currentSquareID != 6) {
    							if(bss.get(currentSquareID).getNumberOfCitizens()>0) {
        							this.point+=bss.get(currentSquareID).getNumberOfCitizens();
        							bss.get(currentSquareID).setNumberOfCitizens(0);
        						}
    						}else {
    							if(bss.get(currentSquareID).getNumberOfCitizens()>5) {
    								MandarinSquare ms = (MandarinSquare) bss.get(currentSquareID);
    								if(ms.isContainMandarin()==true) {
    									ms.setContainMandarin(false);
    									this.point+=10;
    								}
    								this.point+=bss.get(currentSquareID).getNumberOfCitizens();
        							ms.setNumberOfCitizens(0);
        							bss.add(currentSquareID, ms);
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    
}


