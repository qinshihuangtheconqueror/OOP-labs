package controls.player;
import java.util.ArrayList;

import controls.Game;
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

	// Whether the chosen square is in valid board square of the player
	public boolean isValidMove(Board b, int choosenSquareID) {
		for(BoardSquare i : this.validBoardSquare) {
			if(i.getboardSquareID()==choosenSquareID && i.getNumberOfCitizens()>0) return true;
		}
		return false;
	}

	public void dispatchCitizens(Board b) {
		boolean flag = false;
		int currentSquareID;
		BoardSquare currentSquare;
		for(BoardSquare i : this.validBoardSquare) {
			currentSquareID = i.getboardSquareID();
			if(b.getListOfSquare().get(currentSquareID).isEmpty() == false) {
				flag = true;
			}
		}

		// Dispatch the previous-won citizens when there are not any non-empty citizen squares
		ArrayList<BoardSquare> listOfSquare = b.getListOfSquare();
		if(flag==false) {
			if(this.point>5) {
				this.point -= 5;
				for(BoardSquare i: this.validBoardSquare) {
					currentSquareID = i.getboardSquareID();
					currentSquare = listOfSquare.get(currentSquareID);
					currentSquare.setNumberOfCitizens(1);
					listOfSquare.add(currentSquareID, currentSquare);
				}
			}
			b.setListOfSquare(listOfSquare);
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
			this.point += targetMandarinSquare.getNumberOfCitizens();
			targetMandarinSquare.setNumberOfCitizens(0);
			if(targetMandarinSquare.isContainMandarin()==true) {
				this.point += 10;
				targetMandarinSquare.setContainMandarin(false);
			}
			// update the target square (Mandarin square)
			listOfSquare.set(targetSquareID, targetMandarinSquare);
		}else {
			if(targetSquare.isEmpty()==false) {
				this.point += targetSquare.getNumberOfCitizens();
				targetSquare.setNumberOfCitizens(0);
			}
			// update the target square (Citizen Square)
			listOfSquare.set(targetSquareID, targetSquare);
		}
		// Update the list of Square in the board
		bss = listOfSquare;
	}


	// Player makes move (choose citizen square to distribute the citizens in this square)
//	public void makeMove(Board b, int choosenSquareID, boolean isLeftMove) {
//		if(isValidMove(b, choosenSquareID, isLeftMove)==true) {
//			// dispatch the citizen before making move (if there is not any non-empty square)
//			dispatchCitizens(b);
//			// Start to make move
//			ArrayList<BoardSquare> bss = b.getListOfSquare();
//			BoardSquare choosenSquare = b.getListOfSquare().get(choosenSquareID);
//			int citizens = choosenSquare.getNumberOfCitizens();
//			int currentSquareID = choosenSquareID;
//			choosenSquare.setNumberOfCitizens(0);
//			bss.set(currentSquareID, choosenSquare);
//
//			while(citizens>0) {
//				citizens--;
//				if(isLeftMove == true) {
//					if(currentSquareID == 11) currentSquareID = 0;
//					else currentSquareID++;
//					bss.get(currentSquareID).setNumberOfCitizens(bss.get(currentSquareID).getNumberOfCitizens()+1);
//				}else {
//					if(currentSquareID == 0) currentSquareID = 11;
//					else currentSquareID--;
//					bss.get(currentSquareID).setNumberOfCitizens(bss.get(currentSquareID).getNumberOfCitizens()+1);
//				}
//
//				// decide if the turn is continued or stopped (get point or not)
//				if(citizens==0) {
//					if(isLeftMove == true) {
//						if(currentSquareID == 11) currentSquareID = 0;
//						else currentSquareID++;
//						// Capture or continue the turn
//						if(bss.get(currentSquareID) instanceof MandarinSquare) {
//							MandarinSquare currentSquare = (MandarinSquare) bss.get(currentSquareID);
//							if(currentSquare.isEmpty()==true) {
//								captureSquare(bss,currentSquareID, isLeftMove);
//							}
//						}else {
//							BoardSquare currentSquare = (CitizenSquare) bss.get(currentSquareID);
//							if(currentSquare.isEmpty()==true) {
//								captureSquare(bss, currentSquareID, isLeftMove);
//							}else {
//								citizens = currentSquare.getNumberOfCitizens();
//								currentSquare.setNumberOfCitizens(0);
//								bss.set(currentSquareID, currentSquare);
//							}
//						}
//					}else {
//						if(currentSquareID == 0) currentSquareID = 11;
//						else currentSquareID--;
//						// Capture or continue the turn
//						if(bss.get(currentSquareID) instanceof MandarinSquare) {
//							MandarinSquare currentSquare = (MandarinSquare) bss.get(currentSquareID);
//							if(currentSquare.isEmpty()==true) {
//								captureSquare(bss,currentSquareID, isLeftMove);
//							}
//						}else {
//							BoardSquare currentSquare = (CitizenSquare) bss.get(currentSquareID);
//							if(currentSquare.isEmpty()==true) {
//								captureSquare(bss, currentSquareID, isLeftMove);
//							}else {
//								citizens = currentSquare.getNumberOfCitizens();
//								currentSquare.setNumberOfCitizens(0);
//								bss.set(currentSquareID, currentSquare);
//							}
//						}
//					}
//				}
//				System.out.print(currentSquareID + " ");
//
//				ArrayList<BoardSquare> listOfSquares = bss;
//				for(BoardSquare i : listOfSquares) {
//					if(i.getboardSquareID()==0) {
//						MandarinSquare ms0 = (MandarinSquare) i;
//						System.out.print(" ( " + ms0.getNumberOfCitizens() + " " + Boolean.toString(ms0.isContainMandarin()) + " (" + ms0.getboardSquareID() + ") | ");
//					}else if(i.getboardSquareID()==6) {
//						MandarinSquare ms6 = (MandarinSquare) i;
//						System.out.println("" + ms6.getNumberOfCitizens() + " " + Boolean.toString(ms6.isContainMandarin()) + " (" + ms6.getboardSquareID() + ") ) ");
//						System.out.print("\t\t");
//					}else if(i.getboardSquareID()>=1 && i.getboardSquareID() <= 5) {
//						System.out.print(i.getNumberOfCitizens() + " (" + i.getboardSquareID() + ") | ");
//					}else break;
//				}
//				for(int i = 11; i >= 7; i--) {
//					BoardSquare cb = bss.get(i);
//					System.out.print(cb.getNumberOfCitizens() + " (" + cb.getboardSquareID() + ") | ");
//				}
//				System.out.println("\n");
//
//			}
//			System.out.println();
//
//			// update the board square
//			b.setListOfSquare(bss);
//		}
//	}
}