import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import controls.Game;
import controls.board.Board;
import controls.board.BoardSquare;
import controls.board.CitizenSquare;
import controls.board.MandarinSquare;
import controls.player.MinimaxBot;
import controls.player.Player;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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


import java.io.File;

import javafx.util.Duration;


public class Menu extends Application{
    public String choice = "BOT";
    public Stage stage;
    public Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9;
    public ArrayList<Scene> scenes = new ArrayList<Scene>();
    public Parent layout1, layout2;
    static JFrame frame;
    public ArrayList<BoardSquare> squares;
    public ArrayList<ViewStone> ImageHolder = new ArrayList<ViewStone>();
    public Game MainGame;
    public boolean startGame = false;
    private GraphicsContext gc;
    public ArrayList<Image> take_animation= new ArrayList<Image>();
    public ArrayList<Image> drop_animation= new ArrayList<Image>();
    public int take_animation_slide=-1;
    public int drop_animation_slide=-1;
    public int takeID;
    public int dropID;
    public ArrayList<Image> ciz_stones;
    public ArrayList<String> stoneImages;
    public Timeline mainTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));;
    public Utils MPlayer;

    @Override
    public void start(Stage primarystage) throws Exception {
        layout1= createStartMenu();
        scene1 =  new Scene(layout1);
        stage = primarystage;
        mainTimeline.setCycleCount(Timeline.INDEFINITE);
        mainTimeline.play();
        stage.setScene(scene1);
        mainTimeline.stop();
        setup();
        Canvas canvas = new Canvas(1080, 960);
        gc = canvas.getGraphicsContext2D();

        stage.show();
        //layout2 = RenderUI();
//        Image bgImage = new Image(getClass().getResource("gui/asset/mandarin_background.png").toExternalForm());
//        ImageView img_view =  new ImageView(bgImage);
//        img_view.setY(0);
//        img_view.setX(0);
        ImageView img_view = imageView("gui/asset/mandarin_background.png", 0, 0);

//        Image inGameBackground = new Image(getClass().getResource("gui/asset/IngameMenu.png").toExternalForm());
//        ImageView inGameBackgroundView = new ImageView(inGameBackground);
//        inGameBackgroundView.setX(960);
//        inGameBackgroundView.setY(0);
        ImageView inGameBackgroundView = imageView("gui/asset/IngameMenu.png", 960, 0);

//        Image homeImage = new Image(getClass().getResource("gui/asset/HomeButton.png").toExternalForm());
//        ImageView homeImageView = new ImageView(homeImage);
//        Image homeImage2 = new Image(getClass().getResource("gui/asset/HomeButton2.png").toExternalForm());
//        ImageView homeImageView2 = new ImageView(homeImage2);
//
//        homeImageView.setFitHeight(280);
//        homeImageView.setFitWidth(210);
//        homeImageView2.setFitHeight(275);
//        homeImageView2.setFitWidth(210);
        ImageView homeImageView = buttonImageView("gui/asset/HomeButton.png", 280, 210);
        ImageView homeImageView2 = buttonImageView("gui/asset/HomeButton2.png", 275, 210);

//        Button homeButton = new Button();
//        homeButton.setTranslateX(967);
//        homeButton.setTranslateY(205);
//        homeButton.setPrefSize(80, 80);
//        homeButton.setStyle("-fx-background-color: #ffffff00;");
//        homeButton.setGraphic(homeImageView);
        Button homeButton = button(967, 205, homeImageView);


        homeButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                homeButton.setGraphic(homeImageView2);
            } else {
                homeButton.setGraphic(homeImageView);
            }
        });
        homeButton.setOnAction(event -> {
        	MainGame.restart();
            ImageHolder.clear();
            loadImageHolder();
            this.MPlayer.mediaPlayer.stop();
            stage.setScene(scene1);
            mainTimeline.stop();
        });

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

        Image sound_button_image = new Image(getClass().getResource("gui/asset/unmute.png").toExternalForm());
        ImageView sound_button_view = new ImageView(sound_button_image);
        Image mute_button_image = new Image(getClass().getResource("gui/asset/mute.png").toExternalForm());
        ImageView mute_button_view = new ImageView(mute_button_image);

//        Button soundButton =  new Button();
//        soundButton.setTranslateX(1000);
//        soundButton.setTranslateY(600);
//        soundButton.setPrefSize(80, 80);
//        soundButton.setStyle("-fx-background-color: #ffffff00;");
//        soundButton.setGraphic(sound_button_view);
        Button soundButton = button(1000, 600, sound_button_view);


        soundButton.setOnAction(event -> {
            if (soundButton.getGraphic() == sound_button_view){
                soundButton.setGraphic(mute_button_view);
                this.MPlayer.mediaPlayer.pause();
            }
            else {
                soundButton.setGraphic(sound_button_view);
                this.MPlayer.mediaPlayer.play();
            }
        });

        scene2 = new Scene(new Pane(img_view,canvas,Squares_row1,Squares_row2,Mandarin_Square1,Mandarin_Square2, inGameBackgroundView, homeButton,soundButton));


        System.out.println("Running...");

        // arraylist of rule boards's path
        ArrayList<String> rulePaths = new ArrayList<String>();
        rulePaths.add("gui/asset/Setup.png");
        rulePaths.add("gui/asset/Scattering.png");
        rulePaths.add("gui/asset/Scattering2.png");
        rulePaths.add("gui/asset/Capturing.png");
        rulePaths.add("gui/asset/Passing.png");
        rulePaths.add("gui/asset/Dispatching.png");
        rulePaths.add("gui/asset/Winning.png");


        // help scene - scene3
        ImageView helpView = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView setupRuleView = imageView("gui/asset/Setup.png", 0, -500);
        ImageView rightViewSetup = buttonImageView("gui/asset/RIGHT.png", 80, 80);
        ImageView rightViewSetup2 = buttonImageView("gui/asset/RIGHT2.png", 80, 80);
        Button rightButtonSetup = button(740, -415, rightViewSetup);
        setActionRightButton(rightButtonSetup, rightViewSetup2, rightViewSetup, 3,
        setupRuleView, rightButtonSetup, null);
        ImageView backView = buttonImageView("gui/asset/BACk.png", 80, 80);
        ImageView backView2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButton = button(0, 0, backView);
        setActionBackButton(backButton, backView, backView2, 3,
        setupRuleView, rightButtonSetup, null);

        scene3 = new Scene(new Pane(helpView, setupRuleView, rightButtonSetup, backButton));


        // help scene - scene4
        ImageView helpView1 = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView scatteringRuleView = imageView("gui/asset/Scattering.png", 0, -500);
        ImageView rightViewScattering = buttonImageView("gui/asset/RIGHT.png", 80, 80);
        ImageView rightViewScattering2 = buttonImageView("gui/asset/RIGHT2.png", 80, 80);
        Button rightButtonScattering = button(725, -410, rightViewScattering);
        ImageView leftViewScattering = buttonImageView("gui/asset/LEFT.png", 80, 80);
        ImageView leftViewScattering2 = buttonImageView("gui/asset/LEFT2.png", 80, 80);
        Button leftButtonScattering= button(240, -410, leftViewScattering);
        setActionRightButton(rightButtonScattering, rightViewScattering2, rightViewScattering, 4,
        scatteringRuleView, rightButtonScattering, leftButtonScattering);
        setActionLeftButton(leftButtonScattering, leftViewScattering2, leftViewScattering, 4,
        scatteringRuleView, rightButtonScattering, leftButtonScattering);
        ImageView backViewScattering = buttonImageView("gui/asset/BACK.png", 80, 80);
        ImageView backViewScattering2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButtonScattering = button(0, 0, backViewScattering);
        setActionBackButton(backButtonScattering, backViewScattering, backViewScattering2, 4,
        scatteringRuleView, rightButtonScattering, leftButtonScattering);

        scene4 = new Scene(new Pane(helpView1, scatteringRuleView, rightButtonScattering, leftButtonScattering, backButtonScattering));


        // 2th scattering scene - scene5
        ImageView helpView2 = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView scatteringRuleViewSecond = imageView("gui/asset/Scattering2.png", 0, -500);
        ImageView rightViewScatteringSecond = buttonImageView("gui/asset/RIGHT.png", 80, 80);
        ImageView rightViewScatteringSecond2 = buttonImageView("gui/asset/RIGHT2.png", 80, 80);
        Button rightButtonScatteringSecond = button(725, -410, rightViewScatteringSecond);
        ImageView leftViewScatteringSecond = buttonImageView("gui/asset/LEFT.png", 80, 80);
        ImageView leftViewScatteringSecond2 = buttonImageView("gui/asset/LEFT2.png", 80, 80);
        Button leftButtonScatteringSecond = button(240, -410, leftViewScatteringSecond);
        setActionRightButton(rightButtonScatteringSecond, rightViewScatteringSecond2, rightViewScatteringSecond, 5,
        scatteringRuleViewSecond, rightButtonScatteringSecond, leftButtonScatteringSecond);
        setActionLeftButton(leftButtonScatteringSecond, leftViewScatteringSecond2, leftViewScatteringSecond, 5,
        scatteringRuleViewSecond, rightButtonScatteringSecond, leftButtonScatteringSecond);
        ImageView backViewScatteringSecond = buttonImageView("gui/asset/BACK.png", 80, 80);
        ImageView backViewScatteringSecond2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButtonScatteringSecond = button(0, 0, backViewScatteringSecond);
        setActionBackButton(backButtonScatteringSecond, backViewScatteringSecond, backViewScatteringSecond2, 5,
        scatteringRuleViewSecond, rightButtonScatteringSecond, leftButtonScatteringSecond);

        scene5 = new Scene(new Pane(helpView2, scatteringRuleViewSecond, rightButtonScatteringSecond,
                leftButtonScatteringSecond, backButtonScatteringSecond));

        // capturing scene - scene6
        ImageView helpView3 = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView capturingRuleView = imageView("gui/asset/Capturing.png", 0, -500);
        ImageView rightViewCapturing = buttonImageView("gui/asset/RIGHT.png", 80, 80);
        ImageView rightViewCapturing2 = buttonImageView("gui/asset/RIGHT2.png", 80, 80);
        Button rightButtonCapturing = button(725, -410, rightViewCapturing);
        ImageView leftViewCapturing = buttonImageView("gui/asset/LEFT.png", 80, 80);
        ImageView leftViewCapturing2 = buttonImageView("gui/asset/LEFT2.png", 80, 80);
        Button leftButtonCapturing = button(240, -410, leftViewCapturing);
        setActionRightButton(rightButtonCapturing, rightViewCapturing2, rightViewCapturing, 6,
        capturingRuleView, rightButtonCapturing, leftButtonCapturing);
        setActionLeftButton(leftButtonCapturing, leftViewCapturing2, leftViewCapturing, 6,
        capturingRuleView, rightButtonCapturing, leftButtonCapturing);
        ImageView backViewCapturing = buttonImageView("gui/asset/BACK.png", 80, 80);
        ImageView backViewCapturing2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButtonCapturing = button(0, 0, backViewCapturing);
        setActionBackButton(backButtonCapturing, backViewCapturing, backViewCapturing2, 6,
        capturingRuleView, rightButtonCapturing, leftButtonCapturing);

        scene6 = new Scene(new Pane(helpView3, capturingRuleView, rightButtonCapturing,
                leftButtonCapturing, backButtonCapturing));

        // passing scene - scene7
        ImageView helpView4 = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView passingRuleView = imageView("gui/asset/Passing.png", 0, -500);
        ImageView rightViewPassing = buttonImageView("gui/asset/RIGHT.png", 80, 80);
        ImageView rightViewPassing2 = buttonImageView("gui/asset/RIGHT2.png", 80, 80);
        Button rightButtonPassing = button(725, -410, rightViewPassing);
        ImageView leftViewPassing = buttonImageView("gui/asset/LEFT.png", 80, 80);
        ImageView leftViewPassing2 = buttonImageView("gui/asset/LEFT2.png", 80, 80);
        Button leftButtonPassing = button(240, -410, leftViewPassing);
        setActionRightButton(rightButtonPassing, rightViewPassing2, rightViewPassing, 7,
        passingRuleView, rightButtonPassing, leftButtonPassing);
        setActionLeftButton(leftButtonPassing, leftViewPassing2, leftViewPassing, 7,
        passingRuleView, rightButtonPassing, leftButtonPassing);
        ImageView backViewPassing = buttonImageView("gui/asset/BACK.png", 80, 80);
        ImageView backViewPassing2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButtonPassing = button(0, 0, backViewPassing);
        setActionBackButton(backButtonPassing, backViewPassing, backViewPassing2, 7,
                passingRuleView, rightButtonPassing, leftButtonPassing);

        scene7 = new Scene(new Pane(helpView4, passingRuleView, rightButtonPassing,
                leftButtonPassing, backButtonPassing));

        /////////////
        // dispatching scene - scene8
        ImageView helpView5 = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView dispatchingRuleView = imageView("gui/asset/Dispatching.png", 0, -500);
        ImageView rightViewDispatching = buttonImageView("gui/asset/RIGHT.png", 80, 80);
        ImageView rightViewDispatching2 = buttonImageView("gui/asset/RIGHT2.png", 80, 80);
        Button rightButtonDispatching = button(725, -410, rightViewDispatching);
        ImageView leftViewDispatching = buttonImageView("gui/asset/LEFT.png", 80, 80);
        ImageView leftViewDispatching2 = buttonImageView("gui/asset/LEFT2.png", 80, 80);
        Button leftButtonDispatching = button(240, -410, leftViewDispatching);
        setActionRightButton(rightButtonDispatching, rightViewDispatching2, rightViewDispatching, 8,
        dispatchingRuleView, rightButtonDispatching, leftButtonDispatching);
        setActionLeftButton(leftButtonDispatching, leftViewDispatching2, leftViewDispatching, 8,
        dispatchingRuleView, rightButtonDispatching, leftButtonDispatching);
        ImageView backViewDispatching = buttonImageView("gui/asset/BACK.png", 80, 80);
        ImageView backViewDispatching2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButtonDispatching = button(0,0,backViewDispatching);
        setActionBackButton(backButtonDispatching, backViewDispatching, backViewDispatching2, 8,
                dispatchingRuleView, rightButtonDispatching, leftButtonDispatching);

        scene8 = new Scene(new Pane(helpView5, dispatchingRuleView, rightButtonDispatching,
                leftButtonDispatching, backButtonDispatching));

        ///////////////////////
        // winning scene - scene9
        ImageView helpView6 = imageView("gui/asset/MenuScene.gif", 0, 0);
        ImageView winningRuleView = imageView("gui/asset/Winning.png", 0, -500);
        ImageView leftViewWinning = buttonImageView("gui/asset/LEFT.png", 80, 80);
        ImageView leftViewWinning2 = buttonImageView("gui/asset/LEFT2.png", 80, 80);
        Button leftButtonWinning = button(240, -410, leftViewWinning);
        setActionLeftButton(leftButtonWinning, leftViewWinning2, leftViewWinning, 9,
        winningRuleView, null, leftButtonWinning);
        ImageView backViewWinning = buttonImageView("gui/asset/BACK.png", 80, 80);
        ImageView backViewWinning2 = buttonImageView("gui/asset/BACK2.png", 80, 80);
        Button backButtonWinning = button(0, 0, backViewWinning);
        setActionBackButton(backButtonWinning, backViewWinning, backViewWinning2, 9,
                winningRuleView, null, leftButtonWinning);

        scene9 = new Scene(new Pane(helpView6, winningRuleView,
                leftButtonWinning, backButtonWinning));

        scenes.add(scene3);
        scenes.add(scene4);
        scenes.add(scene5);
        scenes.add(scene6);
        scenes.add(scene7);
        scenes.add(scene8);
        scenes.add(scene9);
    }


    // create ImageView
    public ImageView imageView(String path, int X, int Y) {
        Image image = new Image(getClass().getResource(path).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(X);
        imageView.setTranslateY(Y);
        return imageView;
    }

    // create button image view
    public ImageView buttonImageView(String path, int width, int height) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    // create button
    public Button button(int X, int Y, ImageView imageView) {
        Button button = new Button();
        button.setTranslateX(X);
        button.setTranslateY(Y);
        button.setPrefSize(80, 80);
        button.setStyle("-fx-background-color: #ffffff00");
        button.setGraphic(imageView);
        return button;
    }

    // set action for button
    public void setActionRightButton(Button button, ImageView imageView2, ImageView imageView, int currentScene,
                                     ImageView currentBoard, Button currentRightButton, Button currentLeftButton) {
        if (currentScene != 9) {
            button.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
                if(show) {
                    button.setGraphic(imageView2);
                } else {
                    button.setGraphic(imageView);
                }
            });

            button.setOnAction(event -> {
                stage.setScene(scenes.get(currentScene - 2));
                mainTimeline.stop();
                currentBoard.setTranslateY(-500);
                currentRightButton.setTranslateY(-410);
                if (currentLeftButton != null) {
                    currentLeftButton.setTranslateY(-410);
                }

                TranslateTransition nextBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 2).getRoot()).getChildren().get(1));
                nextBoardTransition.setByY(500);
                nextBoardTransition.setInterpolator(Interpolator.EASE_OUT);
                nextBoardTransition.play();
                if (currentScene != 8) {
                    TranslateTransition nextRightButtonTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 2).getRoot()).getChildren().get(2));
                    nextRightButtonTransition.setByY(500);
                    nextRightButtonTransition.setInterpolator(Interpolator.EASE_OUT);
                    nextRightButtonTransition.play();

                    TranslateTransition nextLeftButtonTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 2).getRoot()).getChildren().get(3));
                    nextLeftButtonTransition.setByY(500);
                    nextLeftButtonTransition.setInterpolator(Interpolator.EASE_OUT);
                    nextLeftButtonTransition.play();
                } else {
                    TranslateTransition nextLeftButtonTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 2).getRoot()).getChildren().get(2));
                    nextLeftButtonTransition.setByY(500);
                    nextLeftButtonTransition.setInterpolator(Interpolator.EASE_OUT);
                    nextLeftButtonTransition.play();
                }

            });
        }

    }

    // set action for left button
    public void setActionLeftButton(Button button, ImageView imageView2, ImageView imageView, int currentScene,
                               ImageView currentBoard, Button currentRightButton, Button currentLeftButton) {
        if (currentScene != 3) {
            button.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
                if (show) {
                    button.setGraphic(imageView2);
                } else {
                    button.setGraphic(imageView);
                }
            });

            button.setOnAction(event -> {
                stage.setScene(scenes.get(currentScene - 4));
                mainTimeline.stop();
                currentBoard.setTranslateY(-500);
                if (currentRightButton != null) {
                    currentRightButton.setTranslateY(-410);
                }
                currentLeftButton.setTranslateY(-410);

                TranslateTransition previousBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 4).getRoot()).getChildren().get(1));
                previousBoardTransition.setByY(500);
                previousBoardTransition.setInterpolator(Interpolator.EASE_OUT);
                previousBoardTransition.play();

                if (currentScene != 4) {
                    TranslateTransition previousRigthButtonTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 4).getRoot()).getChildren().get(2));
                    previousRigthButtonTransition.setByY(500);
                    previousRigthButtonTransition.setInterpolator(Interpolator.EASE_OUT);
                    previousRigthButtonTransition.play();

                    TranslateTransition previousLeftButtonTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 4).getRoot()).getChildren().get(3));
                    previousLeftButtonTransition.setByY(500);
                    previousLeftButtonTransition.setInterpolator(Interpolator.EASE_OUT);
                    previousLeftButtonTransition.play();
                } else {
                    TranslateTransition previousRightButtonTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scenes.get(currentScene - 4).getRoot()).getChildren().get(2));
                    previousRightButtonTransition.setByY(500);
                    previousRightButtonTransition.setInterpolator(Interpolator.EASE_OUT);
                    previousRightButtonTransition.play();
                }
            });
        }
    }

    // set action for back button
    public void setActionBackButton(Button button, ImageView imageView, ImageView imageView2, int currentScene,
                                    ImageView currentBoard, Button currentRightButton, Button currentLeftButton) {
        button.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                button.setGraphic(imageView2);
            } else {
                button.setGraphic(imageView);
            }
        });

        button.setOnAction(event -> {
            stage.setScene(scene1);
            mainTimeline.stop();
            currentBoard.setTranslateY(-500);
            if (currentRightButton != null) {
                currentRightButton.setTranslateY(-410);
            }
            if (currentLeftButton != null) {
                currentLeftButton.setTranslateY(-410);
            }
        });
    }


    public void setScene(Scene scene) {
        Canvas canvas = new Canvas(1080, 960);
        gc = canvas.getGraphicsContext2D();
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

        scene = new Scene(new Pane(img_view,canvas,Squares_row1,Squares_row2,Mandarin_Square1,Mandarin_Square2));
    }

    private void run(GraphicsContext gc){
    	if(MainGame.isP1Turn()) {
    		Image p1TurnImage = new Image(getClass().getResource("gui/asset/P1turn.png").toExternalForm());
            gc.clearRect(180, 100, 171, 31);
            gc.drawImage(p1TurnImage, 180, 100, 171, 31);
    	}else {
    		Image p2TurnImage = new Image(getClass().getResource("gui/asset/P2turn.png").toExternalForm());
    		gc.clearRect(180, 100, 171, 31);
            gc.drawImage(p2TurnImage, 180, 100, 171, 31);
    	}
        Image man_stone = new Image(getClass().getResource("gui/asset/ManStone.png").toExternalForm());
        gc.clearRect(100, 265,770,320);
        for (ViewStone stone : ImageHolder) {
            if (stone.type == 1) {
                gc.drawImage(man_stone, stone.coordX, stone.coordY, 32, 32);
            } else {
                gc.drawImage(stone.image, stone.coordX, stone.coordY, 16, 16);
            }
        }
        if (this.take_animation_slide==0) this.take_animation_slide=-1;
        else if (this.take_animation_slide!=-1){
            take(this.takeID);
        }
        if (this.drop_animation_slide==6) this.drop_animation_slide=-1;
        else if (this.drop_animation_slide!=-1){
            drop(this.dropID);
        }
        int player1Point = MainGame.getPlayer1().getPoint();
        int player2Point = MainGame.getPlayer2().getPoint();
        int xPoint2 = 750;
        int yPoint2 = 130;
        int xPoint1 = 280;
        int yPoint1 = 600;
        gc.clearRect(xPoint1-50, yPoint1, 64, 57);
        gc.clearRect(xPoint1-100, yPoint1, 64, 57);
        gc.clearRect(xPoint2-50, yPoint2, 64, 57);
        gc.clearRect(xPoint2-100, yPoint2, 64, 57);
        
        while(player1Point>=0) {
        	xPoint1 -= 50;
//        	gc.clearRect(xPoint1, yPoint1, 64, 57);
        	int printNum = player1Point % 10;
        	player1Point/=10;
        	if(printNum==1) {
        		Image number1 = new Image(getClass().getResource("gui/asset/1_resize.png").toExternalForm());
        		gc.drawImage(number1, xPoint1, yPoint1);
        	}else if(printNum==2) {
        		Image number2 = new Image(getClass().getResource("gui/asset/2_resize.png").toExternalForm());
        		gc.drawImage(number2, xPoint1, yPoint1);
        	}else if(printNum==3) {
        		Image number3 = new Image(getClass().getResource("gui/asset/3_resize.png").toExternalForm());
        		gc.drawImage(number3, xPoint1, yPoint1);
        	}else if(printNum==4) {
        		Image number4 = new Image(getClass().getResource("gui/asset/4_resize.png").toExternalForm());
        		gc.drawImage(number4, xPoint1, yPoint1);
        	}else if(printNum==5) {
        		Image number5 = new Image(getClass().getResource("gui/asset/5_resize.png").toExternalForm());
        		gc.drawImage(number5, xPoint1, yPoint1);
        	}else if(printNum==6) {
        		Image number6 = new Image(getClass().getResource("gui/asset/6_resize.png").toExternalForm());
        		gc.drawImage(number6, xPoint1, yPoint1);
        	}else if(printNum==7) {
        		Image number7 = new Image(getClass().getResource("gui/asset/7_resize.png").toExternalForm());
        		gc.drawImage(number7, xPoint1, yPoint1);
        	}else if(printNum==8) {
        		Image number8 = new Image(getClass().getResource("gui/asset/8_resize.png").toExternalForm());
        		gc.drawImage(number8, xPoint1, yPoint1);
        	}else if(printNum==9) {
        		Image number9 = new Image(getClass().getResource("gui/asset/9_resize.png").toExternalForm());
        		gc.drawImage(number9, xPoint1, yPoint1);
        	}else if(printNum==0) {
        		Image number0 = new Image(getClass().getResource("gui/asset/0_resize.png").toExternalForm());
        		gc.drawImage(number0, xPoint1, yPoint1);
        	}
        	if(player1Point==0) player1Point-=1;
        }
        
        while(player2Point>=0) {
        	xPoint2 -= 50;
//        	gc.clearRect(xPoint2, yPoint2, 64, 57);
        	int printNum = player2Point % 10;
        	player2Point/=10;
        	if(printNum==1) {
        		Image number1 = new Image(getClass().getResource("gui/asset/1_resize.png").toExternalForm());
        		gc.drawImage(number1, xPoint2, yPoint2);
        	}else if(printNum==2) {
        		Image number2 = new Image(getClass().getResource("gui/asset/2_resize.png").toExternalForm());
        		gc.drawImage(number2, xPoint2, yPoint2);
        	}else if(printNum==3) {
        		Image number3 = new Image(getClass().getResource("gui/asset/3_resize.png").toExternalForm());
        		gc.drawImage(number3, xPoint2, yPoint2);
        	}else if(printNum==4) {
        		Image number4 = new Image(getClass().getResource("gui/asset/4_resize.png").toExternalForm());
        		gc.drawImage(number4, xPoint2, yPoint2);
        	}else if(printNum==5) {
        		Image number5 = new Image(getClass().getResource("gui/asset/5_resize.png").toExternalForm());
        		gc.drawImage(number5, xPoint2, yPoint2);
        	}else if(printNum==6) {
        		Image number6 = new Image(getClass().getResource("gui/asset/6_resize.png").toExternalForm());
        		gc.drawImage(number6, xPoint2, yPoint2);
        	}else if(printNum==7) {
        		Image number7 = new Image(getClass().getResource("gui/asset/7_resize.png").toExternalForm());
        		gc.drawImage(number7, xPoint2, yPoint2);
        	}else if(printNum==8) {
        		Image number8 = new Image(getClass().getResource("gui/asset/8_resize.png").toExternalForm());
        		gc.drawImage(number8, xPoint2, yPoint2);
        	}else if(printNum==9) {
        		Image number9 = new Image(getClass().getResource("gui/asset/9_resize.png").toExternalForm());
        		gc.drawImage(number9, xPoint2, yPoint2);
        	}else if(printNum==0) {
        		Image number0 = new Image(getClass().getResource("gui/asset/0_resize.png").toExternalForm());
        		gc.drawImage(number0, xPoint2, yPoint2);
        	}
        	if(player2Point==0) player2Point-=1;
        }
        
        int numCiz11 = MainGame.getMyBoard().getListOfSquare().get(11).getNumberOfCitizens();
        int numCiz10 = MainGame.getMyBoard().getListOfSquare().get(10).getNumberOfCitizens();
        int numCiz9 = MainGame.getMyBoard().getListOfSquare().get(9).getNumberOfCitizens();
        int numCiz8 = MainGame.getMyBoard().getListOfSquare().get(8).getNumberOfCitizens();
        int numCiz7 = MainGame.getMyBoard().getListOfSquare().get(7).getNumberOfCitizens();
        int numCiz6 = MainGame.getMyBoard().getListOfSquare().get(6).getNumberOfCitizens();
        int numCiz5 = MainGame.getMyBoard().getListOfSquare().get(5).getNumberOfCitizens();
        int numCiz4 = MainGame.getMyBoard().getListOfSquare().get(4).getNumberOfCitizens();
        int numCiz3 = MainGame.getMyBoard().getListOfSquare().get(3).getNumberOfCitizens();
        int numCiz2 = MainGame.getMyBoard().getListOfSquare().get(2).getNumberOfCitizens();
        int numCiz1 = MainGame.getMyBoard().getListOfSquare().get(1).getNumberOfCitizens();
        int numCiz0 = MainGame.getMyBoard().getListOfSquare().get(0).getNumberOfCitizens();
        int ySquare1 = 530;
        int xSquare11 = 290;        
        displayNumberOfCitizen(numCiz11, xSquare11, ySquare1);
        int xSquare10 = xSquare11+110;
        displayNumberOfCitizen(numCiz10, xSquare10, ySquare1);
        int xSquare9 = xSquare10+110;
        displayNumberOfCitizen(numCiz9, xSquare9, ySquare1);
        int xSquare8 = xSquare9+110;
        displayNumberOfCitizen(numCiz8, xSquare8, ySquare1);
        int xSquare7 = xSquare8+110;
        displayNumberOfCitizen(numCiz7, xSquare7, ySquare1);
        
        int ySquare2 = 230;
        int xSquare1 = 290;
        displayNumberOfCitizen(numCiz1, xSquare1, ySquare2);
        int xSquare2 = xSquare1+110;
        displayNumberOfCitizen(numCiz2, xSquare2, ySquare2);
        int xSquare3 = xSquare2+110;
        displayNumberOfCitizen(numCiz3, xSquare3, ySquare2);
        int xSquare4 = xSquare3+110;
        displayNumberOfCitizen(numCiz4, xSquare4, ySquare2);
        int xSquare5 = xSquare4+110;
        displayNumberOfCitizen(numCiz5, xSquare5, ySquare2);
        
        
        int yMandarinSquare = 380;
        int xMandarinSquare0 = 95;
        displayNumberOfCitizen(numCiz0, xMandarinSquare0, yMandarinSquare);
        int xMandarinSquare6 = xMandarinSquare0+810;
        displayNumberOfCitizen(numCiz6, xMandarinSquare6, yMandarinSquare);
        

    }
    
    void displayNumberOfCitizen(int numberOfCitizen, int xPoint, int yPoint) {
    	int x = xPoint;
    	int y = yPoint;
    	gc.clearRect(x-20, y, 34, 30);
    	gc.clearRect(x-40, y, 34, 30);
    	int nc = numberOfCitizen;
    	while(nc>=0) {
    		x-=20;
    		int printNum = nc%10;
    		nc/=10;
    		if(printNum==0) {
    			Image image = new Image(getClass().getResource("gui/asset/0_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==1) {
    			Image image = new Image(getClass().getResource("gui/asset/1_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==2) {
    			Image image = new Image(getClass().getResource("gui/asset/2_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==3) {
    			Image image = new Image(getClass().getResource("gui/asset/3_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==4) {
    			Image image = new Image(getClass().getResource("gui/asset/4_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==5) {
    			Image image = new Image(getClass().getResource("gui/asset/5_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==6) {
    			Image image = new Image(getClass().getResource("gui/asset/6_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==7) {
    			Image image = new Image(getClass().getResource("gui/asset/7_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==8) {
    			Image image = new Image(getClass().getResource("gui/asset/8_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}else if(printNum==9) {
    			Image image = new Image(getClass().getResource("gui/asset/9_citizen.png").toExternalForm());
    			gc.drawImage(image, x, y);
    		}
    		if(nc==0) nc-=1;
    	}
    }
    
    public static class ViewStone{
        public int square_id;
        public int coordX;
        public int coordY;
        public int type;
        public Image image;
        public ViewStone( int square_id, int coordX, int coordY,int type, Image image){
            this.square_id= square_id;
            this.coordX= coordX;
            this.coordY= coordY;
            this.type= type;
            this.image= image;
        }
    }

    public void loadImageHolder() {
        int step_x= 100;
        for (int i=0;i<squares.size();i++){
            if (i==0) {
                MandarinSquare new_square = (MandarinSquare) squares.get(i);
                if (new_square.isContainMandarin()) {
                    int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                    Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
                    ImageHolder.add(new ViewStone(i, randomNumX, randomNumY, 1, ciz_stone));
                }
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,385-16);
                    Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
                    ImageHolder.add(new ViewStone(i, randomNumX, randomNumY, 2, ciz_stone));
                }
            }
            else if (i<6){
                step_x=step_x+110;
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-16);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,320-16);
                    Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
                    ImageHolder.add(new ViewStone(i, randomNumX, randomNumY, 2, ciz_stone));
                }
            }
            else if (i==6) {
                step_x=step_x+110;
                MandarinSquare new_square = (MandarinSquare) squares.get(i);
                if (new_square.isContainMandarin()) {
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                    Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
                    ImageHolder.add(new ViewStone(i, randomNumX, randomNumY, 1, ciz_stone));
                }
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,385-16);
                    Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
                    ImageHolder.add(new ViewStone(i, randomNumX, randomNumY, 2, ciz_stone));
                }
            }
            else {
                step_x=step_x-110;
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-16);
                    int randomNumY = ThreadLocalRandom.current().nextInt(395,445-16);
                    Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
                    ImageHolder.add(new ViewStone(i, randomNumX, randomNumY, 2, ciz_stone));
                }
            }
        }

    }

    public void setup(){
        this.ciz_stones = new ArrayList<>();
        this.stoneImages = new ArrayList<>();
        this.stoneImages.add("gui/asset/CizStone1.png");
        this.stoneImages.add("gui/asset/CizStone2.png");
        this.stoneImages.add("gui/asset/CizStone3.png");
        this.stoneImages.add("gui/asset/CizStone4.png");
        this.stoneImages.add("gui/asset/CizStone5.png");
        this.stoneImages.add("gui/asset/CizStone6.png");
        this.stoneImages.add("gui/asset/CizStone7.png");
        this.stoneImages.add("gui/asset/CizStone8.png");
        this.stoneImages.add("gui/asset/CizStone9.png");
        this.stoneImages.add("gui/asset/CizStone10.png");

        for (String image : this.stoneImages) {
            Image stone = new Image(getClass().getResource(image).toExternalForm());
            this.ciz_stones.add(stone);
        }

        this.MPlayer =  new Utils(new File("src/gui/asset/soundtrack.mp3").toURI().toString());
        CitizenSquare CS1 = new CitizenSquare(1, 0);
        CitizenSquare CS2 = new CitizenSquare(2, 0);
        CitizenSquare CS3 = new CitizenSquare(3, 0);
        CitizenSquare CS4 = new CitizenSquare(4, 0);
        CitizenSquare CS5 = new CitizenSquare(5, 0);
        CitizenSquare CS7 = new CitizenSquare(7, 0);
        CitizenSquare CS8= new CitizenSquare(8, 0);
        CitizenSquare CS9 = new CitizenSquare(9, 1);
        CitizenSquare CS10 = new CitizenSquare(10, 0);
        CitizenSquare CS11 = new CitizenSquare(11, 0);

        MandarinSquare MQ0=  new MandarinSquare(0, 0, false);
        MandarinSquare MQ6=  new MandarinSquare(6, 0, true);
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
        Board MainBoard =  new Board(squares);
        Player player1 =  new Player(1,0);
        Player player2 = new Player(2,0);
        Player botPlayer = new MinimaxBot(2, 0);
        MainGame =  new Game(MainBoard,player1,botPlayer, true);

        loadImageHolder();

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
        botPlayer.setValidBoardSquare(validsquare2);

        Image img = new Image(getClass().getResource("gui/asset/anim1.png").toExternalForm());
        this.take_animation.add(img);
        this.drop_animation.add(img);
        img = new Image(getClass().getResource("gui/asset/anim1.png").toExternalForm());
        this.take_animation.add(img);
        img = new Image(getClass().getResource("gui/asset/anim6.png").toExternalForm());
        this.take_animation.add(img);
        this.drop_animation.add(img);
        img = new Image(getClass().getResource("gui/asset/anim4.png").toExternalForm());
        this.take_animation.add(img);
        this.drop_animation.add(img);
        img = new Image(getClass().getResource("gui/asset/anim5.png").toExternalForm());
        this.take_animation.add(img);
        this.drop_animation.add(img);
        img = new Image(getClass().getResource("gui/asset/anim3.png").toExternalForm());
        this.take_animation.add(img);
        this.drop_animation.add(img);
        this.drop_animation.add(img);
    }



    public void ShowInvalidMove(int player_id) {
        JOptionPane.showMessageDialog(null, "Please choose another square", "Invalid chosen square", JOptionPane.ERROR_MESSAGE);
    }

    public void take(int squareID) {
        this.takeID =  squareID;
       int coordX;
       int coordY;
       if (squareID==0){
           coordX = 200;
           coordY = 400;
       }
       else if (squareID ==6){
           coordX =  850;
           coordY = 400;
       }
       else if(squareID>0 && squareID <6){
           coordX =  280 + (squareID - 1) * 110;
           coordY =  385;
       }
       else {
           coordX =  280 + (11 - squareID)*110;
           coordY =  485;
       }
       this.take_animation_slide--;
       Image img =  this.take_animation.get(this.take_animation_slide);
       gc.drawImage(img,coordX-img.getWidth(), coordY-img.getHeight(),img.getWidth(),img.getHeight());
    }

    public void drop(int squareID) {
        this.dropID =  squareID;
        int coordX;
        int coordY;
        if (squareID==0){
            coordX = 200;
            coordY = 400;
        }
        else if (squareID ==6){
            coordX =  850;
            coordY = 400;
        }
        else if(squareID>0 && squareID <6){
            coordX =  280 + (squareID -1)*110;
            coordY =  385;
        }
        else {
            coordX =  280 + (11 - squareID)*110;
            coordY =  485;
        }
        Image img =  this.drop_animation.get(this.drop_animation_slide);
        this.drop_animation_slide++;
        gc.drawImage(img,coordX- img.getWidth(), coordY-img.getHeight(),img.getWidth(),img.getHeight());
    }

    public void chooseDirection(Player player, int id) {
        JPanel direction_panel = new JPanel();
        JLabel label1 = new JLabel("What directions do you want to choose?");
        JButton buttonLeft = new JButton("Left");
        JButton buttonRight = new JButton("Right");

        frame = new JFrame("Choose directions");
        direction_panel.add(label1);
        direction_panel.add(buttonLeft);
        direction_panel.add(buttonRight);
        frame.add(direction_panel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        if(player.getPlayerID()==1) {
            buttonLeft.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == buttonLeft) {
                        frame.setVisible(false);
                        makeMove(MainGame.getMyBoard(), id, true, player);
                    }
                }
            });
            buttonRight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == buttonRight) {
                        frame.setVisible(false);
                        makeMove(MainGame.getMyBoard(), id, false, player);

                    }
                }
            });
        }else {
            buttonLeft.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == buttonLeft) {
                        frame.setVisible(false);
                        makeMove(MainGame.getMyBoard(), id, false, player);
                    }
                }
            });
            buttonRight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == buttonRight) {
                        frame.setVisible(false);
                        makeMove(MainGame.getMyBoard(), id, true, player);
                    }
                }
            });
        }
    }

    public void ChoosePivot(int id) {
        if (MainGame.isP1Turn()) {
            if (MainGame.getPlayer1().isValidMove(MainGame.getMyBoard(), id)) {
                chooseDirection(MainGame.getPlayer1(), id);
            } else {
                System.out.println("Invalid Move player 1");
                ShowInvalidMove(1);
            }
        } else {
            if(MainGame.getPlayer2() instanceof MinimaxBot) {
            	MinimaxBot bot = (MinimaxBot) MainGame.getPlayer2();
                mainTimeline.stop();
                System.out.println("Interrupted");
            	int chosenMovement = bot.makeHardBotMove(MainGame.getMyBoard());
                mainTimeline.play();
            	if(chosenMovement%2==0) {
            		int chosenSquareID = chosenMovement/2;
            		Task task = new Task<Void>(){
                        @Override
                        protected Void call() throws Exception{
                            makeMove(MainGame.getMyBoard(), chosenSquareID, false, MainGame.getPlayer2());
                        return null;
                        }
                    };
                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
            	}else {
            		int chosenSquareID = chosenMovement/2;
            		Task task = new Task<Void>(){
                        @Override
                        protected Void call() throws Exception{
                            makeMove(MainGame.getMyBoard(), chosenSquareID, true, MainGame.getPlayer2());
                        return null;
                        }
                    };
                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
            	}
            }else {
            	if (MainGame.getPlayer2().isValidMove(MainGame.getMyBoard(), id)) {
                    chooseDirection(MainGame.getPlayer2(), id);            }
                else {
                    System.out.println("Invalid Move player 2");
                    ShowInvalidMove(2);
                }
            }
        }
    }
    public void collectCitizen(int squareID){
        for ( int i= ImageHolder.size()-1;i>=0;i--){
            if (ImageHolder.get(i).square_id== squareID){
                if (ImageHolder.get(i).type==2) gc.clearRect(ImageHolder.get(i).coordX, ImageHolder.get(i).coordY, 16,16);
                else gc.clearRect(ImageHolder.get(i).coordX, ImageHolder.get(i).coordY, 32,32);
                ImageHolder.remove(i);
            }
        }
        this.take_animation_slide=6;
        take(squareID);
    }
    public void distributeCitizen(int squareID, boolean onDispatch){
        if (squareID==0) {
            int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
            int randomNumY = ThreadLocalRandom.current().nextInt(350,400-32);
            Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
            ImageHolder.add(new ViewStone(squareID, randomNumX, randomNumY, 2, ciz_stone));
        }
        else if (squareID<6){
            int randomNumX = ThreadLocalRandom.current().nextInt(150+ squareID* 110, 210+ squareID*110-32);
            int randomNumY = ThreadLocalRandom.current().nextInt(265, 400-32);
            Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
            ImageHolder.add(new ViewStone(squareID, randomNumX, randomNumY, 2, ciz_stone));
        }
        else if (squareID==6) {
            int randomNumX = ThreadLocalRandom.current().nextInt(150 + squareID * 110, 150 + squareID * 110 + 50 - 32);
            int randomNumY = ThreadLocalRandom.current().nextInt(350, 400 - 32);
            Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
            ImageHolder.add(new ViewStone(squareID, randomNumX, randomNumY, 2, ciz_stone));
        }
        else {
            int randomNumX = ThreadLocalRandom.current().nextInt(150+ (12-squareID)*110,150+(12-squareID)*110+50-16);
            int randomNumY = ThreadLocalRandom.current().nextInt(395,445-16);
            Image ciz_stone = this.ciz_stones.get(ThreadLocalRandom.current().nextInt(0, 9));
            ImageHolder.add(new ViewStone(squareID, randomNumX, randomNumY, 2, ciz_stone));
        }
        if (!onDispatch){
        this.drop_animation_slide =0;
        drop(squareID);}
    }

    public void dispatchCitizens(Board b, Player player) {
        boolean flag = false;
        int currentSquareID;
        BoardSquare currentSquare;
        for (BoardSquare i : player.getValidBoardSquare()) {
            currentSquareID = i.getboardSquareID();
            if (b.getListOfSquare().get(currentSquareID).isEmpty() == false) {
                flag = true;
            }
        }

        if(flag==false && player.getPoint()<5) {
        	endGameDialog();
        }
        
        // Dispatch the previous-won citizens when there are not any non-empty citizen squares
        ArrayList<BoardSquare> listOfSquare = b.getListOfSquare();
        if (flag == false) {
            player.setPoint(player.getPoint()-5);
            for (BoardSquare i : player.getValidBoardSquare()) {
                currentSquareID = i.getboardSquareID();
                currentSquare = listOfSquare.get(currentSquareID);
                currentSquare.setNumberOfCitizens(1);
                listOfSquare.set(currentSquareID, currentSquare);
                distributeCitizen(currentSquareID,true);
            }
            b.setListOfSquare(listOfSquare);
        }
        System.out.println("Dispatch citizen of player " + player.getPlayerID());
    }
    public void makeMove(Board b, int choosenSquareID, boolean isLeftMove, Player player) {
        // Start to make move
        ArrayList<BoardSquare> bss = b.getListOfSquare();
        BoardSquare choosenSquare = b.getListOfSquare().get(choosenSquareID);
        int citizens = choosenSquare.getNumberOfCitizens();
        int currentSquareID = choosenSquareID;
        choosenSquare.setNumberOfCitizens(0);
        collectCitizen(currentSquareID);
        try{
            Thread.sleep(600);}
        catch(InterruptedException e) {
            System.out.println("Error@##");
        }
        bss.set(currentSquareID, choosenSquare);

        while(citizens>0) {
            System.out.println(citizens);
            try{
                Thread.sleep(600);}
            catch(InterruptedException e) {
                System.out.println("Error@##");
            }

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
            distributeCitizen(currentSquareID,false);

            // decide if the turn is continued or stopped (get point or not)
            if(citizens==0) {
                if(isLeftMove == true) {
                    if(currentSquareID == 11) currentSquareID = 0;
                    else currentSquareID++;
                    // Capture or continue the turn

                    boolean flag = true;

                    while(bss.get(currentSquareID).isEmpty()) {
                        System.out.println("Capture square");
                        int targetSquareID;
                        if(currentSquareID == 11) targetSquareID = 0;
                        else targetSquareID = currentSquareID+1;
                        if(bss.get(targetSquareID).isEmpty()==false) {
                            player.captureSquare(bss, currentSquareID, isLeftMove);
                            try{
                                Thread.sleep(600);}
                            catch(InterruptedException e) {
                                System.out.println("Error@##");
                            }
                            collectCitizen(targetSquareID);
                            if(currentSquareID==11) currentSquareID = 1;
                            else if(currentSquareID == 10) currentSquareID = 0;
                            else currentSquareID += 2;
                        }else break;
                        flag = false;

                    }
                    if(flag == false) break;

                    if(bss.get(currentSquareID) instanceof CitizenSquare) {
                        System.out.println("Continue");
                        BoardSquare currentSquare = (CitizenSquare) bss.get(currentSquareID);
                        citizens = currentSquare.getNumberOfCitizens();
                        try{
                            Thread.sleep(600);}
                        catch(InterruptedException e) {
                            System.out.println("Error@##");
                        }
                        if (!bss.get(currentSquareID).isEmpty())
                            collectCitizen(currentSquareID);
                        currentSquare.setNumberOfCitizens(0);
                        bss.set(currentSquareID, currentSquare);
                    }
                }else {
                    if(currentSquareID == 0) currentSquareID = 11;
                    else currentSquareID--;
                    // Capture or continue the turn

                    boolean flag = true;
                    while(bss.get(currentSquareID).isEmpty()) {
                        System.out.println("Capture square");
                        int targetSquareID;
                        if(currentSquareID == 0) targetSquareID = 11;
                        else targetSquareID = currentSquareID-1;
                        if(bss.get(targetSquareID).isEmpty()==false) {
                            player.captureSquare(bss, currentSquareID, isLeftMove);
                            try{
                                Thread.sleep(600);}
                            catch(InterruptedException e) {
                                System.out.println("Error@##");
                            }
                            collectCitizen(targetSquareID);
                            if(currentSquareID==1) currentSquareID = 11;
                            else if(currentSquareID == 0) currentSquareID = 10;
                            else currentSquareID -= 2;
                        }else break;
                        flag = false;
                    }
                    if(flag == false) break;

                    if(bss.get(currentSquareID) instanceof CitizenSquare) {
                        System.out.println("Continue");
                        BoardSquare currentSquare = (CitizenSquare) bss.get(currentSquareID);
                        citizens = currentSquare.getNumberOfCitizens();
                        try{
                            Thread.sleep(600);}
                        catch(InterruptedException e) {
                            System.out.println("Error@##");
                        }
                        if (!bss.get(currentSquareID).isEmpty())
                            collectCitizen(currentSquareID);
                        currentSquare.setNumberOfCitizens(0);
                        bss.set(currentSquareID, currentSquare);
                    }
                }

            }
            System.out.print(currentSquareID + " ");

            ArrayList<BoardSquare> listOfSquares = bss;
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
                BoardSquare cb = bss.get(i);
                System.out.print(cb.getNumberOfCitizens() + " (" + cb.getboardSquareID() + ") | ");
            }
            System.out.println("\n");

        }
        System.out.println();

        b.setListOfSquare(bss);

        if(MainGame.isEndGame()) {
            endGameDialog();
            return;
        }

        if(MainGame.getPlayer1().equals(player)) {
            MainGame.setP1Turn(false);
            dispatchCitizens(b, MainGame.getPlayer2());
        }else {
            MainGame.setP1Turn(true);
            dispatchCitizens(b, MainGame.getPlayer1());
        }

        System.out.println("End make move");
    }

    public void endGameDialog() {
    	for(int i = 1; i <= 5; i++) {
        	if(!MainGame.getMyBoard().getListOfSquare().get(i).isEmpty()) {
        		MainGame.getPlayer2().setPoint(MainGame.getPlayer2().getPoint()
        				+ MainGame.getMyBoard().getListOfSquare().get(i).getNumberOfCitizens());
        		MainGame.getMyBoard().getListOfSquare().get(i).setNumberOfCitizens(0);
        		collectCitizen(i);
        	}
        }
        for(int i = 7; i <= 11; i++) {
        	if(!MainGame.getMyBoard().getListOfSquare().get(i).isEmpty()) {
        		MainGame.getPlayer1().setPoint(MainGame.getPlayer1().getPoint()
        				+ MainGame.getMyBoard().getListOfSquare().get(i).getNumberOfCitizens());
        		MainGame.getMyBoard().getListOfSquare().get(i).setNumberOfCitizens(0);
        		collectCitizen(i);
        	}
        }
    	
        JPanel dialog_panel = new JPanel();
        Player player = MainGame.winningPlayer();
        JLabel label1;
        if(player!=null) {
            label1 = new JLabel("Player " + player.getPlayerID() + " has won");
        }else {
            label1 = new JLabel("*********** Draw **********");

        }

        JPanel buttonPanel = new JPanel();
        JButton buttonLeft = new JButton("Restart");
        JButton buttonRight = new JButton("Quit game");
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonRight);
        frame = new JFrame("End Game");

        dialog_panel.add(label1);
        dialog_panel.add(buttonPanel);
        frame.add(dialog_panel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonLeft.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(e.getSource()==buttonLeft) {
                    frame.setVisible(false);
                    MainGame.restart();
                    ImageHolder.clear();
                    loadImageHolder();
                    

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // do your GUI stuff here
                            stage.setScene(scene2);
                            mainTimeline.play();
                        }
                    });
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(e.getSource()==buttonRight) {
                    frame.setVisible(false);
                    MainGame.restart();
                    ImageHolder.clear();
                    loadImageHolder();
                    MPlayer.mediaPlayer.stop();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // do your GUI stuff here
                            stage.setScene(scene1);
                            mainTimeline.stop();
                        }
                    });
                }
            }
        });
    }
    private class CitizenSquareUI extends StackPane{
        CitizenSquareUI(String name, Runnable action,int width, int height, int id) {
            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.web("black", 0.75)),
                    new Stop(1.0, Color.web("black", 0.15))
            );

            Rectangle bg = new Rectangle(width, height);
            Text text = new Text("Square number: "+ id);
            text.setFont(Font.font(12.0));
            text.fillProperty().bind(
                    Bindings.when(bg.hoverProperty()).then(Color.GRAY).otherwise(Color.TRANSPARENT)
            );
            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.TRANSPARENT).otherwise(Color.TRANSPARENT)
            );
            setOnMouseClicked(e -> action.run());

            getChildren().addAll(bg, text);
        }

    }
    private Pane createStartMenu() {
        Pane root = new Pane();
        root.setPrefSize(1080,720);
        Image bgImage =  new Image(getClass().getResource("gui/asset/MenuScene.gif").toExternalForm());


        ImageView startView = buttonImageView("gui/asset/START.png", 180, 110);
        ImageView startView2 = buttonImageView("gui/asset/START2.png", 180, 110);
        Button startButton = button(550, 120, startView);

        startButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                startButton.setGraphic(startView2);
            } else {
                startButton.setGraphic(startView);
            }
        });
        startButton.setOnAction(event-> {stage.setScene(scene2); mainTimeline.play(); this.MPlayer.mediaPlayer.play();});


        ImageView helpView = buttonImageView("gui/asset/HELP.png", 180, 110);
        ImageView helpView2 = buttonImageView("gui/asset/HELP2.png", 180, 110);
        Button helpButton = button(550, 220, helpView);

        helpButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                helpButton.setGraphic(helpView2);
            } else {
                helpButton.setGraphic(helpView);
            }
        });
        helpButton.setOnAction(event-> {
            stage.setScene(scene3);
            mainTimeline.stop();
            // transition of rule board
            TranslateTransition boardTransition = new TranslateTransition(Duration.seconds(1), ((Pane)scene3.getRoot()).getChildren().get(1));
            boardTransition.setByY(500);
            boardTransition.setInterpolator(Interpolator.EASE_OUT);
            boardTransition.play();

            // move to next rule
            TranslateTransition rightTransition = new TranslateTransition(Duration.seconds(1), ((Pane)scene3.getRoot()).getChildren().get(2));
            rightTransition.setInterpolator(Interpolator.EASE_OUT);
            rightTransition.setByY(500);
            rightTransition.play();
        });


        ImageView easyView = buttonImageView("gui/asset/EASY.png", 180, 110);
        ImageView easyView2 = buttonImageView("gui/asset/EASY2.png", 180, 110);
        Button easyButton = button(750, 280, easyView);
        ChangeListener<Boolean> easyHoverListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) {
                if (show) {
                    easyButton.setGraphic(easyView2);
                } else {
                    easyButton.setGraphic(easyView);
                }
            }
        };
        easyButton.hoverProperty().addListener(easyHoverListener);
        easyButton.setOnAction(event-> {


        });


        ImageView hardView = buttonImageView("gui/asset/HARD.png", 180, 110);
        ImageView hardView2 = buttonImageView("gui/asset/HARD2.png", 180, 110);
        Button hardButton = button(750, 380, hardView);
        ChangeListener<Boolean> hardHoverListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) {
                if (show) {
                    hardButton.setGraphic(hardView2);
                } else {
                    hardButton.setGraphic(hardView);
                }
            }
        };
        hardButton.hoverProperty().addListener(hardHoverListener);
        hardButton.setOnAction(event-> {


        });


        ImageView botView = buttonImageView("gui/asset/BOT.png", 180, 110);
        ImageView botView2 = buttonImageView("gui/asset/BOT2.png", 180, 110);
//        ImageView botView3 = buttonImageView("gui/asset/BOT3.png", 180, 110);
        Button botButton = button(550, 320, botView);

        ImageView playerView = buttonImageView("gui/asset/PLAYER.png", 180, 110);
        ImageView playerView2 = buttonImageView("gui/asset/PLAYER2.png", 180, 110);
//        ImageView playerView3 = buttonImageView("gui/asset/PLAYER3.png", 180, 110);
        Button playerButton = button(550, 420, playerView);

        botButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                botButton.setGraphic(botView2);
            } else {
                botButton.setGraphic(botView);
            }
        });
        botButton.setOnAction(event-> {
            if (choice == "PLAYER") {
                choice = "BOT";
//                playerButton.setVisible(false);
//                playerView3.setVisible(true);
//                botButton.setVisible(true);
                easyButton.hoverProperty().addListener(easyHoverListener);
                hardButton.hoverProperty().addListener(hardHoverListener);

                TranslateTransition easyTransition = new TranslateTransition(Duration.millis(500), ((Pane) scene1.getRoot()).getChildren().get(6));
                easyTransition.setByX(-400);
                easyTransition.setInterpolator(Interpolator.EASE_OUT);
                easyTransition.play();

                TranslateTransition hardTransition = new TranslateTransition(Duration.millis(500), ((Pane) scene1.getRoot()).getChildren().get(7));
                hardTransition.setByX(-400);
                hardTransition.setInterpolator(Interpolator.EASE_OUT);
                hardTransition.play();
            }
        });

        playerButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                playerButton.setGraphic(playerView2);
            } else {
                playerButton.setGraphic(playerView);
            }
        });
        playerButton.setOnAction(event-> {
            if (choice == "BOT") {
                choice = "PLAYER";
//                botButton.setVisible(false);
//                botView3.setVisible(true);
//                playerButton.setVisible(true);
                easyButton.hoverProperty().removeListener(easyHoverListener);
                hardButton.hoverProperty().removeListener(hardHoverListener);

                TranslateTransition easyTransition = new TranslateTransition(Duration.millis(500), ((Pane) scene1.getRoot()).getChildren().get(6));
                easyTransition.setByX(400);
                easyTransition.setInterpolator(Interpolator.EASE_IN);
                easyTransition.play();

                TranslateTransition hardTransition = new TranslateTransition(Duration.millis(500), ((Pane) scene1.getRoot()).getChildren().get(7));
                hardTransition.setByX(400);
                hardTransition.setInterpolator(Interpolator.EASE_IN);
                hardTransition.play();
            }
        });


        ImageView exitView = buttonImageView("gui/asset/EXIT.png", 180, 110);
        ImageView exitView2 = buttonImageView("gui/asset/EXIT2.png", 180, 110);
        Button exitButton = button(550, 520, exitView);

        exitButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                exitButton.setGraphic(exitView2);
            } else {
                exitButton.setGraphic(exitView);
            }
        });
        exitButton.setOnAction(event -> {Platform.exit();});
        root.getChildren().addAll(
                new ImageView(bgImage),
                startButton,
                helpButton,
                exitButton,
                playerButton,
                botButton,
                easyButton,
                hardButton
        );
        return root;
    }
    public void setStartGame(boolean status){
        this.startGame=status;
        if (!status){
            stage.setScene(scene1);
            mainTimeline.stop();
        }
        else{
            stage.setScene(scene2);
            mainTimeline.play();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}

