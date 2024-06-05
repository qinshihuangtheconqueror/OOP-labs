import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import controls.Game;
import controls.board.Board;
import controls.board.BoardSquare;
import controls.board.CitizenSquare;
import controls.board.MandarinSquare;
import controls.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javax.swing.*;
import java.awt.*;
import gui.*;
import javafx.util.Duration;


public class Menu extends Application{
    public Stage stage;
    public Scene scene1, scene2;
    public Parent layout1, layout2;
    static JFrame frame ;
    public ArrayList<ImageView> ImageHolder0 = new ArrayList<ImageView>();
    public ArrayList<Integer> NumberofStones =  new ArrayList<Integer>();
    public ArrayList<BoardSquare> squares;
    public ArrayList<viewStones> ImageHolder = new ArrayList<viewStones>();
    public Game MainGame;
    public boolean startGame = false;
    private GraphicsContext gc;
    @Override
    public void start(Stage primarystage) throws Exception {
        layout1= createStartMenu();
        scene1 =  new Scene(layout1);
        stage =primarystage;

        Canvas canvas = new Canvas(1080, 960);
        gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        stage.setScene(scene1);
        setup();
        stage.show();

        //layout2 = RenderUI();
        Image bgImage = new Image(getClass().getResource("gui/asset/mandarin_background.png").toExternalForm());
        ImageView img_view =  new ImageView(bgImage);
        img_view.setY(0);
        img_view.setX(0);
        HBox Squares_row1 = new HBox(
                new CitizenSquareUI("Square 1",()->ChoosePivot(1),110,120,1),
                new CitizenSquareUI("Square 2",()->ChoosePivot(2),110,120,2),
                new CitizenSquareUI("Square 3",()->ChoosePivot(3),105,120,3),
                new CitizenSquareUI("Square 4",()->ChoosePivot(4),110,120,4),
                new CitizenSquareUI("Square 5",()->ChoosePivot(5),110,120,5)
        );

        Squares_row1.setTranslateX(210);
        Squares_row1.setTranslateY(265);
        HBox Squares_row2 = new HBox(
                new CitizenSquareUI("Square 11",()->ChoosePivot(11),110,120,11),
                new CitizenSquareUI("Square 10",()->ChoosePivot(10),110,120,10),
                new CitizenSquareUI("Square 9",()->ChoosePivot(9),105,120,9),
                new CitizenSquareUI("Square 8",()->ChoosePivot(8),110,120,8),
                new CitizenSquareUI("Square 7",()->ChoosePivot(7),110,120,7)
        );
        Squares_row2.setTranslateX(210);
        Squares_row2.setTranslateY(395);
        HBox Mandarin_Square1 = new HBox(
                new CitizenSquareUI("Mandarin Square 6",()->{},110,240,6)
        );
        Mandarin_Square1.setTranslateX(755);
        Mandarin_Square1.setTranslateY(265);
        HBox Mandarin_Square2 = new HBox(
                new CitizenSquareUI("Mandarin Square 0",()->{},110,240,0)
        );
        Mandarin_Square2.setTranslateX(100);
        Mandarin_Square2.setTranslateY(265);
        scene2 = new Scene(new Pane(img_view,canvas,Squares_row1,Squares_row2,Mandarin_Square1,Mandarin_Square2));


        System.out.println("Running...");


    }
    private void run(GraphicsContext gc){

        Image man_stone = new Image(getClass().getResource("gui/asset/bigstone1.png").toExternalForm());
        Image ciz_stone =  new Image(getClass().getResource("gui/asset/stone1.png").toExternalForm());

        for (int i=0;i<ImageHolder.size();i++){
            viewStones stone = ImageHolder.get(i);
            if (stone.type==1) {

                gc.drawImage(man_stone,stone.coordX,stone.coordY,32,32);
            }
            else gc.drawImage(ciz_stone,stone.coordX,stone.coordY,16,16);
        }


    }
    public class viewStones{
        public int square_id;
        public int coordX;
        public int coordY;
        public int type;
        public viewStones( int square_id, int coordX, int coordY,int type){
            this.square_id= square_id;
            this.coordX= coordX;
            this.coordY= coordY;
            this.type= type;
        }
    }
    public void setup(){
        Board MainBoard =  new Board(squares);
        Player player1 =  new Player(1,0);
        Player player2 = new Player(2,0);
        MainGame =  new Game(MainBoard,player1,player2, true);
        CitizenSquare CS1 = new CitizenSquare(1, 5);
        CitizenSquare CS2 = new CitizenSquare(2, 5);
        CitizenSquare CS3 = new CitizenSquare(3, 5);
        CitizenSquare CS4 = new CitizenSquare(4, 5);
        CitizenSquare CS5 = new CitizenSquare(5, 5);
        CitizenSquare CS7 = new CitizenSquare(7, 5);
        CitizenSquare CS8= new CitizenSquare(8, 5);
        CitizenSquare CS9 = new CitizenSquare(9, 5);
        CitizenSquare CS10 = new CitizenSquare(10, 5);
        CitizenSquare CS11 = new CitizenSquare(11, 5);

        MandarinSquare MQ0=  new MandarinSquare(0, 0,true);
        MandarinSquare MQ6=  new MandarinSquare(6, 0,true);
        squares = new ArrayList<BoardSquare>();
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
        int step_x= 100;
        for (int i=0;i<squares.size();i++){
            if (i==0) {
                MandarinSquare new_square = (MandarinSquare) squares.get(i);
                if (new_square.isContainMandarin()) {
                    int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                    ImageHolder.add(new viewStones(i,randomNumX,randomNumY,1));

                }
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,385-16);
                    ImageHolder.add(new viewStones(i,randomNumX,randomNumY,2));
                }
            }
            else if (i<6){
                step_x=step_x+110;
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-16);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,320-16);
                    ImageHolder.add(new viewStones(i,randomNumX,randomNumY,2));
                }
            }
            else if (i==6) {
                step_x=step_x+110;
                MandarinSquare new_square = (MandarinSquare) squares.get(i);
                if (new_square.isContainMandarin()) {
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                    ImageHolder.add(new viewStones(i,randomNumX,randomNumY,1));

                }
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,385-16);
                    ImageHolder.add(new viewStones(i,randomNumX,randomNumY,2));
                }
            }
            else {
                step_x=step_x-110;
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-16);
                    int randomNumY = ThreadLocalRandom.current().nextInt(395,445-16);
                    ImageHolder.add(new viewStones(i,randomNumX,randomNumY,2));
                }
            }
        }


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
    }



    public void ChoosePivot(int id){
        int direction =0;
        // phần này code logic của lượt chơi người chơi 1 và 2
        boolean player1turn = MainGame.isP1Turn();
        if (player1turn) {
            if (id <7){
                JPanel dialog_panel = new JPanel();
                JLabel label1 =  new JLabel("Player 1 can only pick the squares in the row below");
                JButton button =  new JButton("OK");
                frame = new JFrame("Dialog");

                dialog_panel.add(label1);
                dialog_panel.add(button);
                frame.add(dialog_panel);
                frame.setSize(300,100);
                frame.setLocationRelativeTo(null);
               frame.setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource()==button){
                            frame.setVisible(false);
                        }
                    }
                });
            }
            else{
                JPanel direction_panel = new JPanel();
                JLabel label1 =  new JLabel("What directions do you want to choose?");
                JButton buttonLeft =  new JButton("Left");
                JButton buttonRight =  new JButton("Right");

                frame = new JFrame("Chooose directions");
                direction_panel.add(label1);
                direction_panel.add(buttonLeft);
                direction_panel.add(buttonRight);
                frame.add(direction_panel);
                frame.setSize(300,100);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                buttonLeft.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource()==buttonLeft){

                            frame.setVisible(false);
                        }
                    }
                });

                buttonRight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource()==buttonRight){

                            frame.setVisible(false);
                        }
                    }
                });
            }

        }

    }
    private class CitizenSquareUI extends StackPane{
        CitizenSquareUI(String name, Runnable action,int width, int height, int id) {
            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.web("black", 0.75)),
                    new Stop(1.0, Color.web("black", 0.15))
            );

            Rectangle bg = new Rectangle(width, height);
            Text text = new Text("Current citizen: " + Integer.toString(squares.get(id).getNumberOfCitizens()));
            text.setFont(Font.font(10.0));
            text.fillProperty().bind(
              Bindings.when(bg.hoverProperty()).then(Color.BLUE).otherwise(Color.TRANSPARENT)
            );
            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.GRAY).otherwise(Color.TRANSPARENT)
            );
            setOnMouseClicked(e -> action.run());

            getChildren().addAll(bg, text);
        }

    }
    private Pane createStartMenu() {
        Pane root = new Pane();
        root.setPrefSize(1280,720);
        Image bgImage =  new Image(getClass().getResource("gui/asset/menu_bg.jpg").toExternalForm());
        VBox box =  new VBox(
                10,
                new MenuItem("LET'S PLAT MANDARIN SQUARE",()->{}),
                new MenuItem("START NEW GAME", ()-> stage.setScene(scene2)),
                new MenuItem("SETTING",()->{}),
                new MenuItem("QUIT",() -> Platform.exit())
        );
        box.setBackground(new Background(new BackgroundFill(Color.web("black", 0.6),null,null)));
        box.setTranslateX(500);
        box.setTranslateY(250) ;
        root.getChildren().addAll(
                new ImageView(bgImage),
                box

        );
        return root;
    }
    public void setStartGame(boolean status){
        this.startGame=status;
        if (!status){
            stage.setScene(scene1);
        }
        else{
            stage.setScene(scene2);
        }
    }
    private static class MenuItem extends StackPane {
        MenuItem(String name, Runnable action){
            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.web("black",0.75)),
                    new Stop(1.0, Color.web("black",0.15))
            );

            Rectangle bg = new Rectangle(250,30,gradient);
            Text text  = new Text(name);
            text.setFont(Font.font(22.0));
            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY)
            );

            setOnMouseClicked(e->action.run());
            getChildren().addAll(bg, text);

        }
    }

    public static void main(String[] args){


        launch(args);
    }
}
