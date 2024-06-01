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
	
	// Whether the choosen square is in valid board square of the player
	public boolean isValidMove(Board b, int choosenSquareID, boolean isLeftMove) {
		for(BoardSquare i : this.validBoardSquare) {
			if(i.getboardSquareID()==choosenSquareID && i.getNumberOfCitizens()>0) return true;
		}
		return false;
	}
	
	public void dispatchCitizens() {
		boolean flag = false;
		for(BoardSquare i : this.validBoardSquare) {
			if(i.isEmpty()==false) {
				flag = true;
				break;
			}
		}
		
		// Dispatch the previous-won citizens when there are not any non-empty citizen squares
		if(flag==false) {
			if(this.point>5) {
				this.point -= 5;
				for(BoardSquare i: this.validBoardSquare) {
					i.setNumberOfCitizens(1);
				}
			}
		}
		
	}
	
	public void captureSquare(ArrayList<BoardSquare> bss, int currentSquareID, boolean isLeftMove) {
		ArrayList<BoardSquare> listOfSquare = bss;
		
		// Get the target square and the current square
		BoardSquare currentSquare = listOfSquare.get(currentSquareID);
		int targetSquareID;
		BoardSquare targetSquare;
		if(isLeftMove==true) {
			if(currentSquareID == 11) {
				targetSquareID = 0;
				targetSquare = listOfSquare.get(targetSquareID);
			}else {
				targetSquareID = currentSquareID + 1;
				targetSquare = listOfSquare.get(targetSquareID);
			}
		}else {
			if(currentSquareID == 0) {
				targetSquareID = 11;
				targetSquare = listOfSquare.get(targetSquareID);
			}else {
				targetSquareID = currentSquareID - 1;
				targetSquare = listOfSquare.get(targetSquareID);
			}
		}
		
		// Win the target square
		if(targetSquare instanceof MandarinSquare) {
			MandarinSquare targetMandarinSquare = (MandarinSquare) targetSquare;
			// Only allow to capture the Mandarin square when its number of citizens > 5
			if(targetMandarinSquare.getNumberOfCitizens()>5) {
				this.point += targetMandarinSquare.getNumberOfCitizens();
				targetMandarinSquare.setNumberOfCitizens(0);
				if(targetMandarinSquare.isContainMandarin()==true) {
					this.point += 10;
					targetMandarinSquare.setContainMandarin(false);
				}
				// update the target square (Mandarin square)
				listOfSquare.add(targetSquareID, targetMandarinSquare);
			}else {
				if(targetSquare.isEmpty()==false) {
					this.point += targetSquare.getNumberOfCitizens();
					targetSquare.setNumberOfCitizens(0);
				}
				// update the target square (Citizen Square)
				listOfSquare.add(targetSquareID, targetSquare);
			}
		}
		// Update the list of Square in the board
		bss = listOfSquare;
	}


	// Player makes move (choose citizen square to distribute the citizens in this square)
	public void makeMove(Board b, int choosenSquareID, boolean isLeftMove) {		
    	if(isValidMove(b, choosenSquareID, isLeftMove)==true) {
    		// dispatch the citizen before making move (if there is not any non-emqty square)
    		dispatchCitizens();
    		// Start to make move
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
    						captureSquare(bss, currentSquareID, isLeftMove);
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
    						captureSquare(bss, currentSquareID, isLeftMove);    					}
    				}
    			}
    		}
    		
    		// update the board square
    		b.setListOfSquare(bss);
    	}
    }    
}


