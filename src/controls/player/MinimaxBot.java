package controls.player;

import java.util.ArrayList;

import controls.Game;
import controls.board.Board;
import controls.board.BoardSquare;
import controls.board.CitizenSquare;
import controls.board.MandarinSquare;

public class MinimaxBot extends Player {
	public MinimaxBot(int playerID, int point) {
		super(playerID, point);
		// TODO Auto-generated constructor stub
	}
	
	static private int currentSquareID;
	static private int citizens = 0;
	
	private void distribute(Board b, boolean isMoveLeft) {
		if(isMoveLeft) {
			if(currentSquareID==11) currentSquareID=0;
			else currentSquareID+=1;
			citizens-=1;
			b.getListOfSquare().get(currentSquareID).setNumberOfCitizens(b.getListOfSquare().get(currentSquareID).getNumberOfCitizens()+1);
		}else {
			if(currentSquareID==0) currentSquareID=11;
			else currentSquareID-=1;
			citizens-=1;
			b.getListOfSquare().get(currentSquareID).setNumberOfCitizens(b.getListOfSquare().get(currentSquareID).getNumberOfCitizens()+1);
		}
	}
	
	public int capture(Board b, boolean isMoveLeft) {
		int score = 0;
		while(b.getListOfSquare().get(currentSquareID).isEmpty()==false) {
			score+=b.getListOfSquare().get(currentSquareID).getNumberOfCitizens();
			if(b.getListOfSquare().get(currentSquareID) instanceof MandarinSquare) {
				MandarinSquare square = (MandarinSquare) b.getListOfSquare().get(currentSquareID);
				if(square.IsContainMandarin) {
					score+=5;
					square.setContainMandarin(false);
				}
				b.getListOfSquare().set(currentSquareID, square);
			}
			b.getListOfSquare().get(currentSquareID).setNumberOfCitizens(0);
			
			currentSquareID = nextSquare(isMoveLeft);
			if(b.getListOfSquare().get(currentSquareID).isEmpty()) currentSquareID=nextSquare(isMoveLeft);
			else break;
		}
		return score;
	}
	
	private int nextSquare(boolean isMoveLeft) {
		if(isMoveLeft) {
			if(currentSquareID==11) currentSquareID=0;
			else currentSquareID+=1;
		}else {
			if(currentSquareID==0) currentSquareID=11;
			else currentSquareID-=1;
		}
		return currentSquareID;
	}
	
	public void display(Board b) {
		ArrayList<BoardSquare> listOfSquares = b.getListOfSquare();
        for(BoardSquare i : listOfSquares) {
            if(i.getboardSquareID()==0) {
                MandarinSquare ms0 = (MandarinSquare) i;
                System.out.print(" ( " + ms0.getNumberOfCitizens() + " " + Boolean.toString(ms0.isContainMandarin()) + " (" + ms0.getboardSquareID() + ") | ");
            }else if(i.getboardSquareID()==6) {
                MandarinSquare ms6 = (MandarinSquare) i;
                System.out.println("" + ms6.getNumberOfCitizens() + " " + Boolean.toString(ms6.isContainMandarin()) + " (" + ms6.getboardSquareID() + ") ) ");
                System.out.print("\t\t");
            }else if(i.getboardSquareID()>=1 && i.getboardSquareID() <= 5) {
                System.out.print(i.getNumberOfCitizens() + " (" + i.getboardSquareID() + ") | ");
            }else break;
        }
        for(int i = 11; i >= 7; i--) {
            BoardSquare cb = b.getListOfSquare().get(i);
            System.out.print(cb.getNumberOfCitizens() + " (" + cb.getboardSquareID() + ") | ");
        }
        System.out.println("\n");
	}

	private int score(Board b, int chosenSquareID, boolean isMoveLeft) {
		int score = 0;
		citizens = b.getListOfSquare().get(chosenSquareID).getNumberOfCitizens();
		b.getListOfSquare().get(chosenSquareID).setNumberOfCitizens(0);
		currentSquareID = chosenSquareID;
		// first move
		while(citizens>0) {
			distribute(b, isMoveLeft);
			if(citizens==0) {
				currentSquareID = nextSquare(isMoveLeft);
				if(b.getListOfSquare().get(currentSquareID) instanceof CitizenSquare) {
					if(b.getListOfSquare().get(currentSquareID).isEmpty()==false) {
						citizens = b.getListOfSquare().get(currentSquareID).getNumberOfCitizens();
						b.getListOfSquare().get(currentSquareID).setNumberOfCitizens(0);
					}else {
						currentSquareID = nextSquare(isMoveLeft);
						score += capture(b, isMoveLeft);
					}
				}else {
					if(b.getListOfSquare().get(currentSquareID).isEmpty()) {
						currentSquareID = nextSquare(isMoveLeft);
						score += capture(b, isMoveLeft);
					}
				}
			}
		}
		return score;
	}

	
	private int minimax(Board b, int depth, int h, boolean isMaximizing, int chosenSquare, boolean isMoveLeft) {
		// Stop condition
		Board board = new Board(b);
//		display(b);
		int score = score(board, chosenSquare, isMoveLeft);
//		display(board);
//		String dir;
//		if(isMoveLeft) dir = "left";
//		else dir = "right";
//		System.out.print(depth + " " + chosenSquare + " " + dir + " " + score);
//		System.out.println();
		if(depth==h || (board.getListOfSquare().get(0).isEmpty() && board.getListOfSquare().get(6).isEmpty())) {
			return score;
		}
		// recursively maximize and minimize
		if(!isMaximizing) {
			int value = Integer.MIN_VALUE;
			for(int i = 1; i <= 5; i++) {
				value = Math.max(value, minimax(board, depth+1, h, !isMaximizing, i, isMoveLeft));
				value = Math.max(value, minimax(board, depth+1, h, !isMaximizing, i, !isMoveLeft));
			}
			return value;
		}else {
			int value = Integer.MAX_VALUE;
			for(int i = 7; i <= 11; i++) {
				value = Math.min(value, minimax(board, depth+1, h, !isMaximizing, i, isMoveLeft));
				value = Math.min(value, minimax(board, depth+1, h, !isMaximizing, i, !isMoveLeft));
			}
			return value;
		}
	}
	
	
	
	public void makeMove(Board boards) {
		final Board b = new Board(boards);
		boolean isMoveLeft = true;
		int bestScore = Integer.MIN_VALUE;
		int bestSquareID = 0;
		boolean bestMove = true;
		display(b);
//		System.out.println(minimax(b, 0, 2, true, 5, isMoveLeft));
//		display(b);
		for(int i = 1; i <= 5; i++) {
			if(!b.getListOfSquare().get(i).isEmpty()) {
				int squareID = i;
				int value1 = minimax(b, 0, 1, true, squareID, isMoveLeft);
				int value2 = minimax(b, 0, 1, true, squareID, !isMoveLeft);
				if(bestScore<value1) {
					bestScore=value1;
					bestMove = isMoveLeft;
					bestSquareID = squareID;
				}
				if(bestScore<value2) {
					bestScore = value2;
					bestMove = isMoveLeft;
					bestSquareID = squareID;
				}
				System.out.println("Square ID: " + squareID);
				System.out.println("Value1: " + value1);
				System.out.println("Value2: " + value2);
			}
		}
		System.out.println();
		System.out.println("Best score: " + bestScore);
		System.out.println("Best Square ID" + bestSquareID);
		String dir = "";
		if(bestMove) {
			dir = "left";
		}else {
			dir = "right";
		}
		System.out.println("Best Move: " + dir);
	}
}
