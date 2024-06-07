import java.awt.DisplayMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import controls.Game;
import controls.board.Board;
import controls.board.BoardSquare;
import controls.board.CitizenSquare;
import controls.board.MandarinSquare;
import controls.player.Player;

//public class Main{
//	static public void displayBoard(Game game) {
//		if(game.isP1Turn()==true) System.out.println("Player 1 turn:");
//		else System.out.println("Player 2 turn");
//		Board board = game.getMyBoard();
//		ArrayList<BoardSquare> listOfSquares = new ArrayList<BoardSquare>();
//		listOfSquares = board.getListOfSquare();
//		for(BoardSquare i : listOfSquares) {
//			if(i.getboardSquareID()==0) {
//				MandarinSquare ms0 = (MandarinSquare) i;
//				System.out.print(" ( " + ms0.getNumberOfCitizens() + " " + Boolean.toString(ms0.isContainMandarin()) + " (" + ms0.getboardSquareID() + ") | ");
//			}else if(i.getboardSquareID()==6) {
//				MandarinSquare ms6 = (MandarinSquare) i;
//				System.out.println("" + ms6.getNumberOfCitizens() + " " + Boolean.toString(ms6.isContainMandarin()) + " (" + ms6.getboardSquareID() + ") ) ");
//				System.out.print("\t\t");
//			}else if(i.getboardSquareID()>=1 && i.getboardSquareID() <= 5) {
//				System.out.print(i.getNumberOfCitizens() + " (" + i.getboardSquareID() + ") | ");
//			}else break;
//		}
//		for(int i = 11; i >= 7; i--) {
//			BoardSquare cb = board.getListOfSquare().get(i);
//			System.out.print(cb.getNumberOfCitizens() + " (" + cb.getboardSquareID() + ") | ");
//		}
//		System.out.println("\n");
//		System.out.println("Player 1: " + game.getPlayer1().getPoint());
//		System.out.println("Player 2: " + game.getPlayer2().getPoint());
//	}
//
//	public static void main(String[] args) {
//		MandarinSquare ms0 = new MandarinSquare(0, 0, true);
//		MandarinSquare ms6 = new MandarinSquare(6, 0, true);
//		CitizenSquare cs1 = new CitizenSquare(1, 5);
//		CitizenSquare cs2 = new CitizenSquare(2, 5);
//		CitizenSquare cs3 = new CitizenSquare(3, 5);
//		CitizenSquare cs4 = new CitizenSquare(4, 5);
//		CitizenSquare cs5 = new CitizenSquare(5, 5);
//		CitizenSquare cs7 = new CitizenSquare(7, 5);
//		CitizenSquare cs8 = new CitizenSquare(8, 5);
//		CitizenSquare cs9 = new CitizenSquare(9, 5);
//		CitizenSquare cs10 = new CitizenSquare(10, 5);
//		CitizenSquare cs11 = new CitizenSquare(11, 5);
//
//		ArrayList<BoardSquare> listOfSquares = new ArrayList<BoardSquare>();
//		listOfSquares.add(ms0);
//		listOfSquares.add(cs1);
//		listOfSquares.add(cs2);
//		listOfSquares.add(cs3);
//		listOfSquares.add(cs4);
//		listOfSquares.add(cs5);
//		listOfSquares.add(ms6);
//		listOfSquares.add(cs7);
//		listOfSquares.add(cs8);
//		listOfSquares.add(cs9);
//		listOfSquares.add(cs10);
//		listOfSquares.add(cs11);
//		Board board = new Board(listOfSquares);
//
//		ArrayList<BoardSquare> vsp1 = new ArrayList<BoardSquare>();
//		vsp1.add(cs11);
//		vsp1.add(cs10);
//		vsp1.add(cs9);
//		vsp1.add(cs8);
//		vsp1.add(cs7);
//		ArrayList<BoardSquare> vsp2 = new ArrayList<BoardSquare>();
//		vsp2.add(cs1);
//		vsp2.add(cs2);
//		vsp2.add(cs3);
//		vsp2.add(cs4);
//		vsp2.add(cs5);
//		Player player1 = new Player(1, 0);
//		Player player2 = new Player(2, 0);
//		player1.setValidBoardSquare(vsp1);
//		player2.setValidBoardSquare(vsp2);
//
//		Game game = new Game(board, player1, player2, true);
////		displayBoard(game);
////		System.out.println();
//
//		Scanner sc = new Scanner(System.in);
//
//		int phase = 5;
//		while(phase>0) {
//			phase--;
//			displayBoard(game);
//			int chosenSquareID;
//			System.out.println("Choose the square ID: ");
//			chosenSquareID = sc.nextInt();
//
//			int isMoveLeftInt;
//			boolean isLeftMove;
//			System.out.print("Is move left? ");
//			isMoveLeftInt = sc.nextInt();
//			if(isMoveLeftInt==1) isLeftMove = true;
//			else isLeftMove = false;
//
//			player1.makeMove(board, chosenSquareID, isLeftMove);
//			game.setMyBoard(board);
//
//			game.processMove(chosenSquareID, isLeftMove);
//		}
//	}
//}