package test;

import java.util.ArrayList;
import java.util.Scanner;

import controls.Game;
import controls.board.Board;
import controls.board.BoardSquare;
import controls.board.CitizenSquare;
import controls.board.MandarinSquare;
import controls.player.MinimaxBot;
import controls.player.Player;

public class TestBot {
	public static void main(String[] args) {
		System.out.println("Start");
		CitizenSquare CS1 = new CitizenSquare(1, 0);
        CitizenSquare CS2 = new CitizenSquare(2, 5);
        CitizenSquare CS3 = new CitizenSquare(3, 0);
        CitizenSquare CS4 = new CitizenSquare(4, 5);
        CitizenSquare CS5 = new CitizenSquare(5, 1);
        CitizenSquare CS7 = new CitizenSquare(7, 0);
        CitizenSquare CS8= new CitizenSquare(8, 0);
        CitizenSquare CS9 = new CitizenSquare(9, 0);
        CitizenSquare CS10 = new CitizenSquare(10, 0);
        CitizenSquare CS11 = new CitizenSquare(11, 0);

        MandarinSquare MQ0=  new MandarinSquare(0, 0, true);
        MandarinSquare MQ6=  new MandarinSquare(6, 0, true);
        ArrayList<BoardSquare> squares = new ArrayList<BoardSquare>();
        squares.add(MQ0);
        squares.add(CS1);
        squares.add(CS2);
        squares.add(CS3);
        squares.add(CS4);
        squares.add(CS5);
        squares.add(MQ6);
        squares.add(CS7);
        squares.add(CS8);
        squares.add(CS9);
        squares.add(CS10);
        squares.add(CS11);
        Board MainBoard =  new Board(squares);
        Player player1 =  new Player(1,0);
        Player player2 = new MinimaxBot(2,0);
        
        ArrayList<BoardSquare> validsquare1 = new ArrayList<BoardSquare>();
        validsquare1.add(CS11);
        validsquare1.add(CS10);
        validsquare1.add(CS9);
        validsquare1.add(CS8);
        validsquare1.add(CS7);
        player1.setValidBoardSquare(validsquare1);

        ArrayList<BoardSquare> validsquare2 = new ArrayList<BoardSquare>();
        validsquare2.add(CS1);
        validsquare2.add(CS2);
        validsquare2.add(CS3);
        validsquare2.add(CS4);
        validsquare2.add(CS5);
        player2.setValidBoardSquare(validsquare2);
        
        Game mainGame = new Game(MainBoard, player1, player2, false);
        
        
        MinimaxBot bot = (MinimaxBot) player2;
//        bot.display(MainBoard);
//        bot.dispatch(MainBoard, 5);
//        bot.display(MainBoard);
        bot.makeBotMove(mainGame.getMyBoard());
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Choose square ID for bot: ");
//        int chosenSquareID = sc.nextInt();
//        System.out.println("Move left? ");
//        int choice = sc.nextInt();
//        boolean isMoveLeft;
//        if(choice==1) isMoveLeft = true;
//        else isMoveLeft = false;
//        bot.makeMove(mainGame.getMyBoard(), chosenSquareID, isMoveLeft);
	}
}
