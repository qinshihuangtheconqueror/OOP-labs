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
	
	public void dispatch(Board b, int chosenSquare) {
		boolean flag = true;
		if(chosenSquare<=5) {
			for(int i = 1; i <= 5; i++) {
				if(!b.getListOfSquare().get(i).isEmpty()) {
					flag = false;
					break;
				}
			}
			if(flag == true) {
				for(int i = 1; i <= 5; i++) {
					b.getListOfSquare().get(i).setNumberOfCitizens(1);
				}
			}
		}else {
			for(int i = 7; i <= 11; i++) {
				if(!b.getListOfSquare().get(i).isEmpty()) {
					flag = false;
					break;
				}
			}
			if(flag == true) {
				for(int i = 7; i <= 11; i++) {
					b.getListOfSquare().get(i).setNumberOfCitizens(1);
				}
			}
		}
	}

	private int score(Board b, int chosenSquareID, boolean isMoveLeft) {
		
		int score = 0;
//		dispatch(b, chosenSquareID);
		citizens = b.getListOfSquare().get(chosenSquareID).getNumberOfCitizens();
		b.getListOfSquare().get(chosenSquareID).setNumberOfCitizens(0);
		currentSquareID = chosenSquareID;
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

	
	private int minimax(Board b, int depth, int h, boolean isMaximizing, int chosenSquare, boolean isMoveLeft, int alpha, int beta) {
		Board board = new Board(b);
		String dir;
		if(isMoveLeft) {
			dir="left";
		}else {
			dir="right";
		}
		display(board);
		System.out.println("Depth: " + depth);
		System.out.println("Direction: " + dir);
		System.out.println("Chosen square: " + chosenSquare);
		System.out.println("Is Maximizing: " + isMaximizing);
		int score = score(board, chosenSquare, isMoveLeft);
		System.out.println("Score: " + score);
		display(board);
		System.out.println("-----------------------------");
		if(depth==h || (board.getListOfSquare().get(0).isEmpty() && board.getListOfSquare().get(6).isEmpty())) {
			return score;
		}
		if(isMaximizing==false) {
			int value = Integer.MIN_VALUE;
			dispatch(board, 1);
			for(int i = 1; i <= 5; i++) {
				if(board.getListOfSquare().get(i).isEmpty()==false) {
					value = Math.max(value, minimax(board, depth+1, h, !isMaximizing, i, isMoveLeft, alpha, beta)-score);
					value = Math.max(value, minimax(board, depth+1, h, !isMaximizing, i, !isMoveLeft, alpha, beta)-score);	
					alpha = Math.max(alpha, value);
					if(beta<=alpha) break;
				}
			}
			return value;
		}else {
			int value = Integer.MAX_VALUE;
			dispatch(board, 7);
			for(int i = 7; i <= 11; i++) {
				if(board.getListOfSquare().get(i).isEmpty()==false) {
					Board board1 = new Board(board);
					Board board2 = new Board(board);
					score(board1, i, isMoveLeft);
					score(board2, i, !isMoveLeft);
					if(depth+1==h) {
						value = Math.min(value, score-minimax(board, depth+1, h, !isMaximizing, i, isMoveLeft, alpha, beta));
						value = Math.min(value, score-minimax(board, depth+1, h, !isMaximizing, i, !isMoveLeft, alpha, beta));
					}else {
						if(board1.getListOfSquare().get(0).isEmpty()&&board1.getListOfSquare().get(6).isEmpty()) {
							value = Math.min(value, score-minimax(board, depth+1, h, !isMaximizing, i, isMoveLeft, alpha, beta));
						}else {
							value = Math.min(value, score+minimax(board, depth+1, h, !isMaximizing, i, isMoveLeft, alpha, beta));
						}
						if(board2.getListOfSquare().get(0).isEmpty()&&board2.getListOfSquare().get(6).isEmpty()) {
							value = Math.min(value, score-minimax(board, depth+1, h, !isMaximizing, i, !isMoveLeft, alpha, beta));
						}else {
							value = Math.min(value, score+minimax(board, depth+1, h, !isMaximizing, i, !isMoveLeft, alpha, beta));
						}
					}
					beta = Math.min(value, beta);
				}
			}
//			System.out.println("Square ID: " + chosenSquare);
			System.out.println("Value: " + value);
			return value;
		}
	}
	
	
	
	public int makeHardBotMove(Board boards) {
		int move = 0;
		final Board b = new Board(boards);
		boolean isMoveLeft = true;
		int bestScore = Integer.MIN_VALUE;
		int bestSquareID = 0;
		boolean bestMove = true;
		display(b);
		int height = 3;
		int depth = 0;
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		for(int i = 1; i <= 5; i++) {
			if(!b.getListOfSquare().get(i).isEmpty()) {
				int squareID = i;
				int value1 = minimax(b, depth, height, true, squareID, isMoveLeft, alpha, beta);
				int value2 = minimax(b, depth, height, true, squareID, !isMoveLeft, alpha, beta);
				if(bestScore<=value1) {
					bestScore=value1;
					bestMove = isMoveLeft;
					bestSquareID = squareID;
				}
				if(bestScore<=value2) {
					bestScore = value2;
					bestMove = !isMoveLeft;
					bestSquareID = squareID;
				}
				System.out.println("**Square ID: " + squareID);
				System.out.println("**Value 1: " + value1);
				System.out.println("**Value 2: " + value2);
				display(b);
			}
		}
		String dir;
		if(bestMove) {
			dir="left";
		}else {
			dir="right";
		}
		System.out.println("Best score: " + bestScore);
		System.out.println("Best choice: " + bestSquareID);
		System.out.println("Best direction: " + dir);
		if(bestMove) {
			move +=1;
			move += bestSquareID*2;
		}else {
			move += bestSquareID*2;
		}
		return move;
	}
	
	public int makeEasyBotMove(Board boards) {
		int move = 0;
		final Board b = new Board(boards);
		boolean isMoveLeft = true;
		int bestScore = Integer.MAX_VALUE;
		int bestSquareID = 0;
		boolean bestMove = true;
		display(b);
		int height = 0;
		int depth = 0;
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		for(int i = 1; i <= 5; i++) {
			if(!b.getListOfSquare().get(i).isEmpty()) {
				int squareID = i;
				int value1 = minimax(b, depth, height, false, squareID, isMoveLeft, alpha, beta);
				int value2 = minimax(b, depth, height, false, squareID, !isMoveLeft, alpha, beta);
				if(bestScore>=value1) {
					bestScore=value1;
					bestMove = isMoveLeft;
					bestSquareID = squareID;
				}
				if(bestScore>=value2) {
					bestScore = value2;
					bestMove = !isMoveLeft;
					bestSquareID = squareID;
				}
				System.out.println("**Square ID: " + squareID);
				System.out.println("**Value 1: " + value1);
				System.out.println("**Value 2: " + value2);
				display(b);
			}
		}
		String dir;
		if(bestMove) {
			dir="left";
		}else {
			dir="right";
		}
		System.out.println("Best choice: " + (bestSquareID)/2);
		System.out.println("Best direction: " + dir);
		if(bestMove) {
			move +=1;
			move += bestSquareID*2;
		}else {
			move += bestSquareID*2;
		}
		return move;
	}
}
