import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import controls.Game;
import controls.board.Board;
import controls.board.BoardSquare;
import controls.board.CitizenSquare;
import controls.board.MandarinSquare;
import controls.player.Player;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import javafx.util.Duration;



public class Menu extends Application {
    public Stage stage;
    public Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9;
    public Parent layout1, layout2;
    static JFrame frame;
    public ArrayList<BoardSquare> squares;
    public ArrayList<ViewStone> ImageHolder = new ArrayList<ViewStone>();
    public Game MainGame;
    public boolean startGame = false;
    private GraphicsContext gc, gc1;

    @Override
    public void start(Stage primarystage) throws Exception {
        layout1 = createStartMenu();
        scene1 = new Scene(layout1);
        stage = primarystage;
        stage.setScene(scene1);
        setup();
        Canvas canvas = new Canvas(1080, 960);
        gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        stage.show();


        //layout2 = RenderUI();
        Image bgImage = new Image(getClass().getResource("gui/asset/mandarin_background.png").toExternalForm());
        ImageView img_view = new ImageView(bgImage);
        img_view.setY(0);
        img_view.setX(0);
        HBox Squares_row1 = new HBox(
                new CitizenSquareUI("Square 1", () -> ChoosePivot(1), 110, 120, 1),
                new CitizenSquareUI("Square 2", () -> ChoosePivot(2), 110, 120, 2),
                new CitizenSquareUI("Square 3", () -> ChoosePivot(3), 105, 120, 3),
                new CitizenSquareUI("Square 4", () -> ChoosePivot(4), 110, 120, 4),
                new CitizenSquareUI("Square 5", () -> ChoosePivot(5), 110, 120, 5)
        );

        Squares_row1.setTranslateX(210);
        Squares_row1.setTranslateY(265);
        HBox Squares_row2 = new HBox(
                new CitizenSquareUI("Square 11", () -> ChoosePivot(11), 110, 120, 11),
                new CitizenSquareUI("Square 10", () -> ChoosePivot(10), 110, 120, 10),
                new CitizenSquareUI("Square 9", () -> ChoosePivot(9), 105, 120, 9),
                new CitizenSquareUI("Square 8", () -> ChoosePivot(8), 110, 120, 8),
                new CitizenSquareUI("Square 7", () -> ChoosePivot(7), 110, 120, 7)
        );
        Squares_row2.setTranslateX(210);
        Squares_row2.setTranslateY(395);
        HBox Mandarin_Square1 = new HBox(
                new CitizenSquareUI("Mandarin Square 6", () -> {
                }, 110, 240, 6)
        );
        Mandarin_Square1.setTranslateX(755);
        Mandarin_Square1.setTranslateY(265);
        HBox Mandarin_Square2 = new HBox(
                new CitizenSquareUI("Mandarin Square 0", () -> {
                }, 110, 240, 0)
        );
        Mandarin_Square2.setTranslateX(100);
        Mandarin_Square2.setTranslateY(265);
        scene2 = new Scene(new Pane(img_view, canvas, Squares_row1, Squares_row2, Mandarin_Square1, Mandarin_Square2));

        System.out.println("Running...");


        // help scene - scene3
        Image helpMenuImage = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView = new ImageView(helpMenuImage);
        helpView.setY(0);
        helpView.setX(0);

        // rule board
        Image setupRule = new Image("gui/asset/Setup.png");
        ImageView setupRuleView = new ImageView(setupRule);
        setupRuleView.setTranslateX(0);
        setupRuleView.setTranslateY(-500);

        // right button
        Image rightImageSetup = new Image("gui/asset/RIGHT.png");
        ImageView rightViewSetup = new ImageView(rightImageSetup);
        Image rightImageSetup2 = new Image("gui/asset/RIGHT2.png");
        ImageView rightViewSetup2 = new ImageView(rightImageSetup2);

        rightViewSetup.setFitHeight(80);
        rightViewSetup.setFitWidth(80);
        rightViewSetup2.setFitHeight(80);
        rightViewSetup2.setFitWidth(80);

        Button rightButtonSetup = new Button();
        rightButtonSetup.setTranslateX(740);
        rightButtonSetup.setTranslateY(-415);
        rightButtonSetup.setPrefSize(80, 80);
        rightButtonSetup.setStyle("-fx-background-color: #ffffff00;");
        rightButtonSetup.setGraphic(rightViewSetup);

        rightButtonSetup.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                rightButtonSetup.setGraphic(rightViewSetup2);
            } else {
                rightButtonSetup.setGraphic(rightViewSetup);
            }
        });
        rightButtonSetup.setOnAction(event -> {
            stage.setScene(scene4);
            setupRuleView.setTranslateY(-500);
            rightButtonSetup.setTranslateY(-415);

            TranslateTransition scatteringBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene4.getRoot()).getChildren().get(1));
            scatteringBoardTransition.setByY(500);
            scatteringBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            scatteringBoardTransition.play();

            TranslateTransition rightTransitionScattering = new TranslateTransition(Duration.seconds(1), ((Pane) scene4.getRoot()).getChildren().get(2));
            rightTransitionScattering.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionScattering.setByY(500);
            rightTransitionScattering.play();

            TranslateTransition leftTransitionScattering = new TranslateTransition(Duration.seconds(1), ((Pane) scene4.getRoot()).getChildren().get(3));
            leftTransitionScattering.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionScattering.setByY(500);
            leftTransitionScattering.play();
        });

        // back to menu button
        Image backImage = new Image("gui/asset/BACK.png");
        ImageView backView = new ImageView(backImage);
        Image backImage2 = new Image("gui/asset/BACK2.png");
        ImageView backView2 = new ImageView(backImage2);

        backView.setFitHeight(80);
        backView.setFitWidth(80);
        backView2.setFitHeight(80);
        backView2.setFitWidth(80);

        Button backButton = new Button();
        backButton.setTranslateX(0);
        backButton.setTranslateY(0);
        backButton.setPrefSize(80, 80);
        backButton.setStyle("-fx-background-color: #ffffff00;");
        backButton.setGraphic(backView);

        backButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButton.setGraphic(backView2);
            } else {
                backButton.setGraphic(backView);
            }
        });
        backButton.setOnAction(event -> {
            stage.setScene(scene1);
            setupRuleView.setTranslateY(-500);
            rightButtonSetup.setTranslateY(-415);

//            setupRuleView.setTranslateY(-500);
//            rightButtonSetup.setTranslateX(740);
//            rightButtonSetup.setTranslateY(-415);
        });

        scene3 = new Scene(new Pane(helpView, setupRuleView, rightButtonSetup, backButton));


        // help scene - scene4
        Image helpMenuImage1 = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView1 = new ImageView(helpMenuImage1);
        helpView.setY(0);
        helpView.setX(0);

        // rule board
        Image scatteringRule = new Image("gui/asset/Scattering.png");
        ImageView scatteringRuleView = new ImageView(scatteringRule);
        scatteringRuleView.setTranslateX(0);
        scatteringRuleView.setTranslateY(-500);

//        // right button
        Image rightImageScattering = new Image("gui/asset/RIGHT.png");
        ImageView rightViewScattering = new ImageView(rightImageScattering);
        Image rightImageScattering2 = new Image("gui/asset/RIGHT2.png");
        ImageView rightViewScattering2 = new ImageView(rightImageScattering2);

        rightViewScattering.setFitHeight(80);
        rightViewScattering.setFitWidth(80);
        rightViewScattering2.setFitHeight(80);
        rightViewScattering2.setFitWidth(80);

        Button rightButtonScattering = new Button();
        rightButtonScattering.setTranslateX(725);
        rightButtonScattering.setTranslateY(-410);
        rightButtonScattering.setPrefSize(80, 80);
        rightButtonScattering.setStyle("-fx-background-color: #ffffff00;");
        rightButtonScattering.setGraphic(rightViewScattering);

        // left button scattering
        Image leftImageScattering = new Image("gui/asset/LEFT.png");
        ImageView leftViewScattering = new ImageView(leftImageScattering);
        Image leftImageScattering2 = new Image("gui/asset/LEFT2.png");
        ImageView leftViewScattering2 = new ImageView(leftImageScattering2);

        leftViewScattering.setFitHeight(80);
        leftViewScattering.setFitWidth(80);
        leftViewScattering2.setFitHeight(80);
        leftViewScattering2.setFitWidth(80);

        Button leftButtonScattering = new Button();
        leftButtonScattering.setTranslateX(240);
        leftButtonScattering.setTranslateY(-410);
        leftButtonScattering.setPrefSize(80, 80);
        leftButtonScattering.setStyle("-fx-background-color: #ffffff00;");
        leftButtonScattering.setGraphic(leftViewScattering);

        rightButtonScattering.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                rightButtonScattering.setGraphic(rightViewScattering2);
            } else {
                rightButtonScattering.setGraphic(rightViewScattering);
            }
        });
        rightButtonScattering.setOnAction(event -> {
            stage.setScene(scene5);
            scatteringRuleView.setTranslateY(-500);
            rightButtonScattering.setTranslateY(-410);
            leftButtonScattering.setTranslateY(-410);

            TranslateTransition scatteringBoardTransitionSecond = new TranslateTransition(Duration.seconds(1), ((Pane) scene5.getRoot()).getChildren().get(1));
            scatteringBoardTransitionSecond.setByY(500);
            scatteringBoardTransitionSecond.setInterpolator(Interpolator.EASE_OUT);
            scatteringBoardTransitionSecond.play();

            TranslateTransition rightTransitionScatteringSecond = new TranslateTransition(Duration.seconds(1), ((Pane) scene5.getRoot()).getChildren().get(2));
            rightTransitionScatteringSecond.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionScatteringSecond.setByY(500);
            rightTransitionScatteringSecond.play();

            TranslateTransition leftTransitionScatteringSecond = new TranslateTransition(Duration.seconds(1), ((Pane) scene5.getRoot()).getChildren().get(3));
            leftTransitionScatteringSecond.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionScatteringSecond.setByY(500);
            leftTransitionScatteringSecond.play();
        });

        leftButtonScattering.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                leftButtonScattering.setGraphic(leftViewScattering2);
            } else {
                leftButtonScattering.setGraphic(leftViewScattering);
            }
        });
        leftButtonScattering.setOnAction(event -> {
            stage.setScene(scene3);
            scatteringRuleView.setTranslateY(-500);
            rightButtonScattering.setTranslateY(-410);
            leftButtonScattering.setTranslateY(-410);

            TranslateTransition setupBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene3.getRoot()).getChildren().get(1));
            setupBoardTransition.setByY(500);
            setupBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            setupBoardTransition.play();

            TranslateTransition rightTransitionSetup = new TranslateTransition(Duration.seconds(1), ((Pane) scene3.getRoot()).getChildren().get(2));
            rightTransitionSetup.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionSetup.setByY(500);
            rightTransitionSetup.play();
        });

        // back to menu button
        Image backImageScattering = new Image("gui/asset/BACK.png");
        ImageView backViewScattering = new ImageView(backImageScattering);
        Image backImageScattering2 = new Image("gui/asset/BACK2.png");
        ImageView backViewScattering2 = new ImageView(backImageScattering2);

        backViewScattering.setFitHeight(80);
        backViewScattering.setFitWidth(80);
        backViewScattering2.setFitHeight(80);
        backViewScattering2.setFitWidth(80);

        Button backButtonScattering = new Button();
        backButtonScattering.setTranslateX(0);
        backButtonScattering.setTranslateY(0);
        backButtonScattering.setPrefSize(80, 80);
        backButtonScattering.setStyle("-fx-background-color: #ffffff00;");
        backButtonScattering.setGraphic(backViewScattering);

        backButtonScattering.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButtonScattering.setGraphic(backViewScattering2);
            } else {
                backButtonScattering.setGraphic(backViewScattering);
            }
        });
        backButtonScattering.setOnAction(event -> {
            stage.setScene(scene1);
            scatteringRuleView.setTranslateY(-500);
            rightButtonScattering.setTranslateY(-410);
            leftButtonScattering.setTranslateY(-410);
        });

        scene4 = new Scene(new Pane(helpView1, scatteringRuleView, rightButtonScattering, leftButtonScattering, backButtonScattering));

        // 2th scattering scene - scene5
        Image helpMenuImage2 = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView2 = new ImageView(helpMenuImage2);
        helpView2.setY(0);
        helpView2.setX(0);

        // rule board
        Image scatteringRuleSecond = new Image("gui/asset/Scattering2.png");
        ImageView scatteringRuleViewSecond = new ImageView(scatteringRuleSecond);
        scatteringRuleViewSecond.setTranslateX(0);
        scatteringRuleViewSecond.setTranslateY(-500);

//        // right button
        Image rightImageScatteringSecond = new Image("gui/asset/RIGHT.png");
        ImageView rightViewScatteringSecond = new ImageView(rightImageScatteringSecond);
        Image rightImageScatteringSecond2 = new Image("gui/asset/RIGHT2.png");
        ImageView rightViewScatteringSecond2 = new ImageView(rightImageScatteringSecond2);

        rightViewScatteringSecond.setFitHeight(80);
        rightViewScatteringSecond.setFitWidth(80);
        rightViewScatteringSecond2.setFitHeight(80);
        rightViewScatteringSecond2.setFitWidth(80);

        Button rightButtonScatteringSecond = new Button();
        rightButtonScatteringSecond.setTranslateX(725);
        rightButtonScatteringSecond.setTranslateY(-410);
        rightButtonScatteringSecond.setPrefSize(80, 80);
        rightButtonScatteringSecond.setStyle("-fx-background-color: #ffffff00;");
        rightButtonScatteringSecond.setGraphic(rightViewScatteringSecond);

        // left button scattering
        Image leftImageScatteringSecond = new Image("gui/asset/LEFT.png");
        ImageView leftViewScatteringSecond = new ImageView(leftImageScatteringSecond);
        Image leftImageScatteringSecond2 = new Image("gui/asset/LEFT2.png");
        ImageView leftViewScatteringSecond2 = new ImageView(leftImageScatteringSecond2);

        leftViewScatteringSecond.setFitHeight(80);
        leftViewScatteringSecond.setFitWidth(80);
        leftViewScatteringSecond2.setFitHeight(80);
        leftViewScatteringSecond2.setFitWidth(80);

        Button leftButtonScatteringSecond = new Button();
        leftButtonScatteringSecond.setTranslateX(240);
        leftButtonScatteringSecond.setTranslateY(-410);
        leftButtonScatteringSecond.setPrefSize(80, 80);
        leftButtonScatteringSecond.setStyle("-fx-background-color: #ffffff00;");
        leftButtonScatteringSecond.setGraphic(leftViewScatteringSecond);

        rightButtonScatteringSecond.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                rightButtonScatteringSecond.setGraphic(rightViewScatteringSecond2);
            } else {
                rightButtonScatteringSecond.setGraphic(rightViewScatteringSecond);
            }
        });
        rightButtonScatteringSecond.setOnAction(event -> {
            stage.setScene(scene6);
            scatteringRuleViewSecond.setTranslateY(-500);
            rightButtonScatteringSecond.setTranslateY(-410);
            leftButtonScatteringSecond.setTranslateY(-410);

            TranslateTransition capturingBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene6.getRoot()).getChildren().get(1));
            capturingBoardTransition.setByY(500);
            capturingBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            capturingBoardTransition.play();

            TranslateTransition rightTransitionCapturing = new TranslateTransition(Duration.seconds(1), ((Pane) scene6.getRoot()).getChildren().get(2));
            rightTransitionCapturing.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionCapturing.setByY(500);
            rightTransitionCapturing.play();

            TranslateTransition leftTransitionCapturing = new TranslateTransition(Duration.seconds(1), ((Pane) scene6.getRoot()).getChildren().get(3));
            leftTransitionCapturing.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionCapturing.setByY(500);
            leftTransitionCapturing.play();
        });

        leftButtonScatteringSecond.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                leftButtonScatteringSecond.setGraphic(leftViewScatteringSecond2);
            } else {
                leftButtonScatteringSecond.setGraphic(leftViewScatteringSecond);
            }
        });
        leftButtonScatteringSecond.setOnAction(event -> {
            stage.setScene(scene4);
            scatteringRuleViewSecond.setTranslateY(-500);
            rightButtonScatteringSecond.setTranslateY(-410);
            leftButtonScatteringSecond.setTranslateY(-410);

            TranslateTransition scatteringBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene4.getRoot()).getChildren().get(1));
            scatteringBoardTransition.setByY(500);
            scatteringBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            scatteringBoardTransition.play();

            TranslateTransition rightTransitionScattering = new TranslateTransition(Duration.seconds(1), ((Pane) scene4.getRoot()).getChildren().get(2));
            rightTransitionScattering.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionScattering.setByY(500);
            rightTransitionScattering.play();

            TranslateTransition leftTransitionScattering = new TranslateTransition(Duration.seconds(1), ((Pane) scene4.getRoot()).getChildren().get(3));
            leftTransitionScattering.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionScattering.setByY(500);
            leftTransitionScattering.play();
        });

        // back to menu button
        Image backImageScatteringSecond = new Image("gui/asset/BACK.png");
        ImageView backViewScatteringSecond = new ImageView(backImageScatteringSecond);
        Image backImageScatteringSecond2 = new Image("gui/asset/BACK2.png");
        ImageView backViewScatteringSecond2 = new ImageView(backImageScatteringSecond2);

        backViewScatteringSecond.setFitHeight(80);
        backViewScatteringSecond.setFitWidth(80);
        backViewScatteringSecond2.setFitHeight(80);
        backViewScatteringSecond2.setFitWidth(80);

        Button backButtonScatteringSecond = new Button();
        backButtonScatteringSecond.setTranslateX(0);
        backButtonScatteringSecond.setTranslateY(0);
        backButtonScatteringSecond.setPrefSize(80, 80);
        backButtonScatteringSecond.setStyle("-fx-background-color: #ffffff00;");
        backButtonScatteringSecond.setGraphic(backViewScatteringSecond);

        backButtonScatteringSecond.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButtonScatteringSecond.setGraphic(backViewScatteringSecond2);
            } else {
                backButtonScatteringSecond.setGraphic(backViewScatteringSecond);
            }
        });
        backButtonScatteringSecond.setOnAction(event -> {
            stage.setScene(scene1);
            scatteringRuleViewSecond.setTranslateY(-500);
            rightButtonScatteringSecond.setTranslateY(-410);
            leftButtonScatteringSecond.setTranslateY(-410);
        });

        scene5 = new Scene(new Pane(helpView2, scatteringRuleViewSecond, rightButtonScatteringSecond,
                leftButtonScatteringSecond, backButtonScatteringSecond));


        // capturing scene - scene6
        Image helpMenuImage3 = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView3 = new ImageView(helpMenuImage3);
        helpView3.setY(0);
        helpView3.setX(0);

        // rule board
        Image capturingRule = new Image("gui/asset/Capturing.png");
        ImageView capturingRuleView = new ImageView(capturingRule);
        capturingRuleView.setTranslateX(0);
        capturingRuleView.setTranslateY(-500);

//        // right button
        Image rightImageCapturing = new Image("gui/asset/RIGHT.png");
        ImageView rightViewCapturing = new ImageView(rightImageCapturing);
        Image rightImageCapturing2 = new Image("gui/asset/RIGHT2.png");
        ImageView rightViewCapturing2 = new ImageView(rightImageCapturing2);

        rightViewCapturing.setFitHeight(80);
        rightViewCapturing.setFitWidth(80);
        rightViewCapturing2.setFitHeight(80);
        rightViewCapturing2.setFitWidth(80);

        Button rightButtonCapturing = new Button();
        rightButtonCapturing.setTranslateX(725);
        rightButtonCapturing.setTranslateY(-410);
        rightButtonCapturing.setPrefSize(80, 80);
        rightButtonCapturing.setStyle("-fx-background-color: #ffffff00;");
        rightButtonCapturing.setGraphic(rightViewCapturing);

        // left button scattering
        Image leftImageCapturing = new Image("gui/asset/LEFT.png");
        ImageView leftViewCapturing = new ImageView(leftImageCapturing);
        Image leftImageCapturing2 = new Image("gui/asset/LEFT2.png");
        ImageView leftViewCapturing2 = new ImageView(leftImageCapturing2);

        leftViewCapturing.setFitHeight(80);
        leftViewCapturing.setFitWidth(80);
        leftViewCapturing2.setFitHeight(80);
        leftViewCapturing2.setFitWidth(80);

        Button leftButtonCapturing = new Button();
        leftButtonCapturing.setTranslateX(240);
        leftButtonCapturing.setTranslateY(-410);
        leftButtonCapturing.setPrefSize(80, 80);
        leftButtonCapturing.setStyle("-fx-background-color: #ffffff00;");
        leftButtonCapturing.setGraphic(leftViewCapturing);

        rightButtonCapturing.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                rightButtonCapturing.setGraphic(rightViewCapturing2);
            } else {
                rightButtonCapturing.setGraphic(rightViewCapturing);
            }
        });
        rightButtonCapturing.setOnAction(event -> {
            stage.setScene(scene7);
            capturingRuleView.setTranslateY(-500);
            rightButtonCapturing.setTranslateY(-410);
            leftButtonCapturing.setTranslateY(-410);

            TranslateTransition passingBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene7.getRoot()).getChildren().get(1));
            passingBoardTransition.setByY(500);
            passingBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            passingBoardTransition.play();

            TranslateTransition rightTransitionPassing = new TranslateTransition(Duration.seconds(1), ((Pane) scene7.getRoot()).getChildren().get(2));
            rightTransitionPassing.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionPassing.setByY(500);
            rightTransitionPassing.play();

            TranslateTransition leftTransitionPassing = new TranslateTransition(Duration.seconds(1), ((Pane) scene7.getRoot()).getChildren().get(3));
            leftTransitionPassing.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionPassing.setByY(500);
            leftTransitionPassing.play();

        });

        leftButtonCapturing.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                leftButtonCapturing.setGraphic(leftViewCapturing2);
            } else {
                leftButtonCapturing.setGraphic(leftViewCapturing);
            }
        });
        leftButtonCapturing.setOnAction(event -> {
            stage.setScene(scene5);
            capturingRuleView.setTranslateY(-500);
            rightButtonCapturing.setTranslateY(-410);
            leftButtonCapturing.setTranslateY(-410);

            TranslateTransition scatteringBoardTransitionSecond = new TranslateTransition(Duration.seconds(1), ((Pane) scene5.getRoot()).getChildren().get(1));
            scatteringBoardTransitionSecond.setByY(500);
            scatteringBoardTransitionSecond.setInterpolator(Interpolator.EASE_OUT);
            scatteringBoardTransitionSecond.play();

            TranslateTransition rightTransitionScatteringSecond = new TranslateTransition(Duration.seconds(1), ((Pane) scene5.getRoot()).getChildren().get(2));
            rightTransitionScatteringSecond.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionScatteringSecond.setByY(500);
            rightTransitionScatteringSecond.play();

            TranslateTransition leftTransitionScatteringSecond = new TranslateTransition(Duration.seconds(1), ((Pane) scene5.getRoot()).getChildren().get(3));
            leftTransitionScatteringSecond.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionScatteringSecond.setByY(500);
            leftTransitionScatteringSecond.play();
        });

        // back to menu button
        Image backImageCapturing = new Image("gui/asset/BACK.png");
        ImageView backViewCapturing = new ImageView(backImageCapturing);
        Image backImageCapturing2 = new Image("gui/asset/BACK2.png");
        ImageView backViewCapturing2 = new ImageView(backImageCapturing2);

        backViewCapturing.setFitHeight(80);
        backViewCapturing.setFitWidth(80);
        backViewCapturing2.setFitHeight(80);
        backViewCapturing2.setFitWidth(80);

        Button backButtonCapturing = new Button();
        backButtonCapturing.setTranslateX(0);
        backButtonCapturing.setTranslateY(0);
        backButtonCapturing.setPrefSize(80, 80);
        backButtonCapturing.setStyle("-fx-background-color: #ffffff00;");
        backButtonCapturing.setGraphic(backViewCapturing);

        backButtonCapturing.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButtonCapturing.setGraphic(backViewCapturing2);
            } else {
                backButtonCapturing.setGraphic(backViewCapturing);
            }
        });
        backButtonCapturing.setOnAction(event -> {
            stage.setScene(scene1);
            capturingRuleView.setTranslateY(-500);
            rightButtonCapturing.setTranslateY(-410);
            leftButtonCapturing.setTranslateY(-410);
        });

        scene6 = new Scene(new Pane(helpView3, capturingRuleView, rightButtonCapturing,
                leftButtonCapturing, backButtonCapturing));

        // passing scene - scene7
        Image helpMenuImage4 = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView4 = new ImageView(helpMenuImage4);
        helpView4.setY(0);
        helpView4.setX(0);

        // rule board
        Image passingRule = new Image("gui/asset/Passing.png");
        ImageView passingRuleView = new ImageView(passingRule);
        passingRuleView.setTranslateX(0);
        passingRuleView.setTranslateY(-500);

//        // right button
        Image rightImagePassing = new Image("gui/asset/RIGHT.png");
        ImageView rightViewPassing = new ImageView(rightImagePassing);
        Image rightImagePassing2 = new Image("gui/asset/RIGHT2.png");
        ImageView rightViewPassing2 = new ImageView(rightImagePassing2);

        rightViewPassing.setFitHeight(80);
        rightViewPassing.setFitWidth(80);
        rightViewPassing2.setFitHeight(80);
        rightViewPassing2.setFitWidth(80);

        Button rightButtonPassing = new Button();
        rightButtonPassing.setTranslateX(725);
        rightButtonPassing.setTranslateY(-410);
        rightButtonPassing.setPrefSize(80, 80);
        rightButtonPassing.setStyle("-fx-background-color: #ffffff00;");
        rightButtonPassing.setGraphic(rightViewPassing);

        // left button scattering
        Image leftImagePassing = new Image("gui/asset/LEFT.png");
        ImageView leftViewPassing = new ImageView(leftImagePassing);
        Image leftImagePassing2 = new Image("gui/asset/LEFT2.png");
        ImageView leftViewPassing2 = new ImageView(leftImagePassing2);

        leftViewPassing.setFitHeight(80);
        leftViewPassing.setFitWidth(80);
        leftViewPassing2.setFitHeight(80);
        leftViewPassing2.setFitWidth(80);

        Button leftButtonPassing = new Button();
        leftButtonPassing.setTranslateX(240);
        leftButtonPassing.setTranslateY(-410);
        leftButtonPassing.setPrefSize(80, 80);
        leftButtonPassing.setStyle("-fx-background-color: #ffffff00;");
        leftButtonPassing.setGraphic(leftViewPassing);

        rightButtonPassing.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                rightButtonPassing.setGraphic(rightViewPassing2);
            } else {
                rightButtonPassing.setGraphic(rightViewPassing);
            }
        });
        rightButtonPassing.setOnAction(event -> {
            stage.setScene(scene8);
            passingRuleView.setTranslateY(-500);
            rightButtonPassing.setTranslateY(-410);
            leftButtonPassing.setTranslateY(-410);

            TranslateTransition dispatchingBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene8.getRoot()).getChildren().get(1));
            dispatchingBoardTransition.setByY(500);
            dispatchingBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            dispatchingBoardTransition.play();

            TranslateTransition rightTransitionDispatching = new TranslateTransition(Duration.seconds(1), ((Pane) scene8.getRoot()).getChildren().get(2));
            rightTransitionDispatching.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionDispatching.setByY(500);
            rightTransitionDispatching.play();

            TranslateTransition leftTransitionDispatching = new TranslateTransition(Duration.seconds(1), ((Pane) scene8.getRoot()).getChildren().get(3));
            leftTransitionDispatching.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionDispatching.setByY(500);
            leftTransitionDispatching.play();
        });

        leftButtonPassing.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                leftButtonPassing.setGraphic(leftViewPassing2);
            } else {
                leftButtonPassing.setGraphic(leftViewPassing);
            }
        });
        leftButtonPassing.setOnAction(event -> {
            stage.setScene(scene6);
            passingRuleView.setTranslateY(-500);
            rightButtonPassing.setTranslateY(-410);
            leftButtonPassing.setTranslateY(-410);

            TranslateTransition capturingBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene6.getRoot()).getChildren().get(1));
            capturingBoardTransition.setByY(500);
            capturingBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            capturingBoardTransition.play();

            TranslateTransition rightTransitionCapturing = new TranslateTransition(Duration.seconds(1), ((Pane) scene6.getRoot()).getChildren().get(2));
            rightTransitionCapturing.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionCapturing.setByY(500);
            rightTransitionCapturing.play();

            TranslateTransition leftTransitionCapturing = new TranslateTransition(Duration.seconds(1), ((Pane) scene6.getRoot()).getChildren().get(3));
            leftTransitionCapturing.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionCapturing.setByY(500);
            leftTransitionCapturing.play();
        });

        // back to menu button
        Image backImagePassing = new Image("gui/asset/BACK.png");
        ImageView backViewPassing = new ImageView(backImagePassing);
        Image backImagePassing2 = new Image("gui/asset/BACK2.png");
        ImageView backViewPassing2 = new ImageView(backImagePassing2);

        backViewPassing.setFitHeight(80);
        backViewPassing.setFitWidth(80);
        backViewPassing2.setFitHeight(80);
        backViewPassing2.setFitWidth(80);

        Button backButtonPassing = new Button();
        backButtonPassing.setTranslateX(0);
        backButtonPassing.setTranslateY(0);
        backButtonPassing.setPrefSize(80, 80);
        backButtonPassing.setStyle("-fx-background-color: #ffffff00;");
        backButtonPassing.setGraphic(backViewPassing);

        backButtonPassing.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButtonPassing.setGraphic(backViewPassing2);
            } else {
                backButtonPassing.setGraphic(backViewPassing);
            }
        });
        backButtonPassing.setOnAction(event -> {
            stage.setScene(scene1);
            passingRuleView.setTranslateY(-500);
            rightButtonPassing.setTranslateY(-410);
            leftButtonPassing.setTranslateY(-410);
        });

        scene7 = new Scene(new Pane(helpView4, passingRuleView, rightButtonPassing,
                leftButtonPassing, backButtonPassing));


        // dispatching scene - scene8
        Image helpMenuImage5 = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView5 = new ImageView(helpMenuImage5);
        helpView5.setY(0);
        helpView5.setX(0);

        // rule board
        Image dispatchingRule = new Image("gui/asset/Dispatching.png");
        ImageView dispatchingRuleView = new ImageView(dispatchingRule);
        dispatchingRuleView.setTranslateX(0);
        dispatchingRuleView.setTranslateY(-500);

//        // right button
        Image rightImageDispatching = new Image("gui/asset/RIGHT.png");
        ImageView rightViewDispatching = new ImageView(rightImageDispatching);
        Image rightImageDispatching2 = new Image("gui/asset/RIGHT2.png");
        ImageView rightViewDispatching2 = new ImageView(rightImageDispatching2);

        rightViewDispatching.setFitHeight(80);
        rightViewDispatching.setFitWidth(80);
        rightViewDispatching2.setFitHeight(80);
        rightViewDispatching2.setFitWidth(80);

        Button rightButtonDispatching = new Button();
        rightButtonDispatching.setTranslateX(725);
        rightButtonDispatching.setTranslateY(-410);
        rightButtonDispatching.setPrefSize(80, 80);
        rightButtonDispatching.setStyle("-fx-background-color: #ffffff00;");
        rightButtonDispatching.setGraphic(rightViewDispatching);

        // left button scattering
        Image leftImageDispatching = new Image("gui/asset/LEFT.png");
        ImageView leftViewDispatching = new ImageView(leftImageDispatching);
        Image leftImageDispatching2 = new Image("gui/asset/LEFT2.png");
        ImageView leftViewDispatching2 = new ImageView(leftImageDispatching2);

        leftViewDispatching.setFitHeight(80);
        leftViewDispatching.setFitWidth(80);
        leftViewDispatching2.setFitHeight(80);
        leftViewDispatching2.setFitWidth(80);

        Button leftButtonDispatching = new Button();
        leftButtonDispatching.setTranslateX(240);
        leftButtonDispatching.setTranslateY(-410);
        leftButtonDispatching.setPrefSize(80, 80);
        leftButtonDispatching.setStyle("-fx-background-color: #ffffff00;");
        leftButtonDispatching.setGraphic(leftViewDispatching);

        rightButtonDispatching.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                rightButtonDispatching.setGraphic(rightViewDispatching2);
            } else {
                rightButtonDispatching.setGraphic(rightViewDispatching);
            }
        });
        rightButtonDispatching.setOnAction(event -> {
            stage.setScene(scene9);
            dispatchingRuleView.setTranslateY(-500);
            rightButtonDispatching.setTranslateY(-410);
            leftButtonDispatching.setTranslateY(-410);

            TranslateTransition winningBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene9.getRoot()).getChildren().get(1));
            winningBoardTransition.setByY(500);
            winningBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            winningBoardTransition.play();

            TranslateTransition rightTransitionWinning = new TranslateTransition(Duration.seconds(1), ((Pane) scene9.getRoot()).getChildren().get(2));
            rightTransitionWinning.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionWinning.setByY(500);
            rightTransitionWinning.play();
        });

        leftButtonDispatching.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                leftButtonDispatching.setGraphic(leftViewDispatching2);
            } else {
                leftButtonDispatching.setGraphic(leftViewDispatching);
            }
        });
        leftButtonDispatching.setOnAction(event -> {
            stage.setScene(scene7);
            dispatchingRuleView.setTranslateY(-500);
            rightButtonDispatching.setTranslateY(-410);
            leftButtonDispatching.setTranslateY(-410);

            TranslateTransition passingBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene7.getRoot()).getChildren().get(1));
            passingBoardTransition.setByY(500);
            passingBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            passingBoardTransition.play();

            TranslateTransition rightTransitionPassing = new TranslateTransition(Duration.seconds(1), ((Pane) scene7.getRoot()).getChildren().get(2));
            rightTransitionPassing.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionPassing.setByY(500);
            rightTransitionPassing.play();

            TranslateTransition leftTransitionPassing = new TranslateTransition(Duration.seconds(1), ((Pane) scene7.getRoot()).getChildren().get(3));
            leftTransitionPassing.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionPassing.setByY(500);
            leftTransitionPassing.play();
        });

        // back to menu button
        Image backImageDispatching = new Image("gui/asset/BACK.png");
        ImageView backViewDispatching = new ImageView(backImageDispatching);
        Image backImageDispatching2 = new Image("gui/asset/BACK2.png");
        ImageView backViewDispatching2 = new ImageView(backImageDispatching2);

        backViewDispatching.setFitHeight(80);
        backViewDispatching.setFitWidth(80);
        backViewDispatching2.setFitHeight(80);
        backViewDispatching2.setFitWidth(80);

        Button backButtonDispatching = new Button();
        backButtonDispatching.setTranslateX(0);
        backButtonDispatching.setTranslateY(0);
        backButtonDispatching.setPrefSize(80, 80);
        backButtonDispatching.setStyle("-fx-background-color: #ffffff00;");
        backButtonDispatching.setGraphic(backViewDispatching);

        backButtonDispatching.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButtonDispatching.setGraphic(backViewDispatching2);
            } else {
                backButtonDispatching.setGraphic(backViewDispatching);
            }
        });
        backButtonDispatching.setOnAction(event -> {
            stage.setScene(scene1);
            dispatchingRuleView.setTranslateY(-500);
            rightButtonDispatching.setTranslateY(-410);
            leftButtonDispatching.setTranslateY(-410);
        });

        scene8 = new Scene(new Pane(helpView5, dispatchingRuleView, rightButtonDispatching,
                leftButtonDispatching, backButtonDispatching));


        // winning scene - scene9
        Image helpMenuImage6 = new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());
        ImageView helpView6 = new ImageView(helpMenuImage6);
        helpView6.setY(0);
        helpView6.setX(0);

        // rule board
        Image winningRule = new Image("gui/asset/Winning.png");
        ImageView winningRuleView = new ImageView(winningRule);
        winningRuleView.setTranslateX(0);
        winningRuleView.setTranslateY(-500);

        // left button scattering
        Image leftImageWinning = new Image("gui/asset/LEFT.png");
        ImageView leftViewWinning = new ImageView(leftImageWinning);
        Image leftImageWinning2 = new Image("gui/asset/LEFT2.png");
        ImageView leftViewWinning2 = new ImageView(leftImageWinning2);

        leftViewWinning.setFitHeight(80);
        leftViewWinning.setFitWidth(80);
        leftViewWinning2.setFitHeight(80);
        leftViewWinning2.setFitWidth(80);

        Button leftButtonWinning = new Button();
        leftButtonWinning.setTranslateX(240);
        leftButtonWinning.setTranslateY(-410);
        leftButtonWinning.setPrefSize(80, 80);
        leftButtonWinning.setStyle("-fx-background-color: #ffffff00;");
        leftButtonWinning.setGraphic(leftViewWinning);

        leftButtonWinning.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                leftButtonWinning.setGraphic(leftViewWinning2);
            } else {
                leftButtonWinning.setGraphic(leftViewWinning);
            }
        });
        leftButtonWinning.setOnAction(event -> {
            stage.setScene(scene8);
            winningRuleView.setTranslateY(-500);
            leftButtonWinning.setTranslateY(-410);

            TranslateTransition dispatchingBoardTransition = new TranslateTransition(Duration.seconds(1), ((Pane) scene8.getRoot()).getChildren().get(1));
            dispatchingBoardTransition.setByY(500);
            dispatchingBoardTransition.setInterpolator(Interpolator.EASE_OUT);
            dispatchingBoardTransition.play();

            TranslateTransition rightTransitionDispatching = new TranslateTransition(Duration.seconds(1), ((Pane) scene8.getRoot()).getChildren().get(2));
            rightTransitionDispatching.setInterpolator(Interpolator.EASE_OUT);
            rightTransitionDispatching.setByY(500);
            rightTransitionDispatching.play();

            TranslateTransition leftTransitionDispatching = new TranslateTransition(Duration.seconds(1), ((Pane) scene8.getRoot()).getChildren().get(3));
            leftTransitionDispatching.setInterpolator(Interpolator.EASE_OUT);
            leftTransitionDispatching.setByY(500);
            leftTransitionDispatching.play();
        });

        // back to menu button
        Image backImageWinning = new Image("gui/asset/BACK.png");
        ImageView backViewWinning = new ImageView(backImageDispatching);
        Image backImageWinning2 = new Image("gui/asset/BACK2.png");
        ImageView backViewWinning2 = new ImageView(backImageWinning2);

        backViewWinning.setFitHeight(80);
        backViewWinning.setFitWidth(80);
        backViewWinning2.setFitHeight(80);
        backViewWinning2.setFitWidth(80);

        Button backButtonWinning = new Button();
        backButtonWinning.setTranslateX(0);
        backButtonWinning.setTranslateY(0);
        backButtonWinning.setPrefSize(80, 80);
        backButtonWinning.setStyle("-fx-background-color: #ffffff00;");
        backButtonWinning.setGraphic(backViewWinning);

        backButtonWinning.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                backButtonWinning.setGraphic(backViewWinning2);
            } else {
                backButtonWinning.setGraphic(backViewWinning);
            }
        });
        backButtonWinning.setOnAction(event -> {
            stage.setScene(scene1);
            winningRuleView.setTranslateY(-500);
            leftButtonWinning.setTranslateY(-410);
        });

        scene9 = new Scene(new Pane(helpView6, winningRuleView,
                leftButtonWinning, backButtonWinning));

    }

    private void run(GraphicsContext gc){
        if (MainGame.isEndGame()){
            JPanel dialog_panel = new JPanel();
            JLabel label1 = new JLabel("The Game has ended");
            JButton button = new JButton("OK");
            frame = new JFrame("End Game");

            dialog_panel.add(label1);
            dialog_panel.add(button);
            frame.add(dialog_panel);
            frame.setSize(300, 100);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == button) {
                        stage.setScene(scene1);
                        frame.setVisible(false);
                    }
                }
            });
        }
        else {
            Image man_stone = new Image(getClass().getResource("gui/asset/bigstone1.png").toExternalForm());
            Image ciz_stone = new Image(getClass().getResource("gui/asset/stone1.png").toExternalForm());

            for (int i = 0; i < ImageHolder.size(); i++) {
                ViewStone stone = ImageHolder.get(i);
                if (stone.type == 1) {
                    gc.drawImage(man_stone, stone.coordX, stone.coordY, 32, 32);
                } else {
                    gc.drawImage(ciz_stone, stone.coordX, stone.coordY, 16, 16);
                }
            }
        }

    }
    public class ViewStone{
        public int square_id;
        public int coordX;
        public int coordY;
        public int type;
        public ViewStone( int square_id, int coordX, int coordY,int type){
            this.square_id= square_id;
            this.coordX= coordX;
            this.coordY= coordY;
            this.type= type;
        }
    }
    public void setup(){
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
        Board MainBoard =  new Board(squares);
        Player player1 =  new Player(1,0);
        Player player2 = new Player(2,0);
        MainGame =  new Game(MainBoard,player1,player2, true);
        int step_x= 100;
        for (int i=0;i<squares.size();i++){
            if (i==0) {
                MandarinSquare new_square = (MandarinSquare) squares.get(i);
                if (new_square.isContainMandarin()) {
                    int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                    ImageHolder.add(new ViewStone(i,randomNumX,randomNumY,1));
                }
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,385-16);
                    ImageHolder.add(new ViewStone(i,randomNumX,randomNumY,2));
                }
            }
            else if (i<6){
                step_x=step_x+110;
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-16);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,320-16);
                    ImageHolder.add(new ViewStone(i,randomNumX,randomNumY,2));
                }
            }
            else if (i==6) {
                step_x=step_x+110;
                MandarinSquare new_square = (MandarinSquare) squares.get(i);
                if (new_square.isContainMandarin()) {
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                    ImageHolder.add(new ViewStone(i,randomNumX,randomNumY,1));

                }
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-32);
                    int randomNumY = ThreadLocalRandom.current().nextInt(265,385-16);
                    ImageHolder.add(new ViewStone(i,randomNumX,randomNumY,2));
                }
            }
            else {
                step_x=step_x-110;
                for (int j =1;j<= squares.get(i).getNumberOfCitizens();j++){
                    int randomNumX = ThreadLocalRandom.current().nextInt(step_x,step_x+50-16);
                    int randomNumY = ThreadLocalRandom.current().nextInt(395,445-16);
                    ImageHolder.add(new ViewStone(i,randomNumX,randomNumY,2));
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



    public void ShowInvalidMove(int player_id) {
        JPanel dialog_panel = new JPanel();
        JLabel label1 = new JLabel("Player" +player_id +" can only pick the squares in the row below");
        JButton button = new JButton("OK");
        frame = new JFrame("Dialog");

        dialog_panel.add(label1);
        dialog_panel.add(button);
        frame.add(dialog_panel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
                    frame.setVisible(false);
                }
            }
        });
    }

    public void ChoosePivot(int id) {
        if (MainGame.isP1Turn()) {
            if (MainGame.getPlayer1().isValidMove(MainGame.getMyBoard(), id)) {
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
                buttonLeft.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buttonLeft) {
                            frame.setVisible(false);
                            makeMove(MainGame.getMyBoard(), id, true, MainGame.getPlayer1());
                        }
                    }
                });
                buttonRight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buttonRight) {
                            frame.setVisible(false);
                            makeMove(MainGame.getMyBoard(), id, false, MainGame.getPlayer1());

                        }
                    }
                });
            } else {
                ShowInvalidMove(1);
            }
        } else {
            if (MainGame.getPlayer2().isValidMove(MainGame.getMyBoard(), id)) {
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
                buttonLeft.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buttonLeft) {
                            frame.setVisible(false);
                            makeMove(MainGame.getMyBoard(), id, true, MainGame.getPlayer2());
                        }
                    }
                });
                buttonRight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buttonRight) {
                            frame.setVisible(false);
                            makeMove(MainGame.getMyBoard(), id, false, MainGame.getPlayer2());
                        }
                    }
                });
            }
            else {
                ShowInvalidMove(2);
            }
        }
    }
//            else{
//                JPanel direction_panel = new JPanel();
//                JLabel label1 =  new JLabel("What directions do you want to choose?");
//                JButton buttonLeft =  new JButton("Left");
//                JButton buttonRight =  new JButton("Right");
//
//                frame = new JFrame("Chooose directions");
//                direction_panel.add(label1);
//                direction_panel.add(buttonLeft);
//                direction_panel.add(buttonRight);
//                frame.add(direction_panel);
//                frame.setSize(300,100);
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//                buttonLeft.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource()==buttonLeft){
//
//                            frame.setVisible(false);
//                        }
//                    }
//                });
//
//                buttonRight.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource()==buttonRight){
//
//                            frame.setVisible(false);
//                        }
//                    }
//                });
//            }
//
//        }
//
//
    public void collectCitizen(int squareID){
        for ( int i= ImageHolder.size()-1;i>=0;i--){
            if (ImageHolder.get(i).square_id == squareID){
                gc.clearRect(ImageHolder.get(i).coordX, ImageHolder.get(i).coordY, 16,16);
                ImageHolder.remove(i);
                System.out.println(ImageHolder.size());
            }
        }
    }
    public void distributeCitizen(int squareID){
        if (squareID==0) {
                int randomNumX = ThreadLocalRandom.current().nextInt(150,210-32);
                int randomNumY = ThreadLocalRandom.current().nextInt(265,400-32);
                ImageHolder.add(new ViewStone(squareID,randomNumX,randomNumY,2));
            }
        else if (squareID<6){
            int randomNumX = ThreadLocalRandom.current().nextInt(150+ squareID* 110, 210+ squareID*110-32);
            int randomNumY = ThreadLocalRandom.current().nextInt(265, 400-32);
            ImageHolder.add(new ViewStone(squareID,randomNumX,randomNumY,2));
        }
        else if (squareID==6) {
            int randomNumX = ThreadLocalRandom.current().nextInt(150 + squareID * 110, 150 + squareID * 110 + 50 - 32);
            int randomNumY = ThreadLocalRandom.current().nextInt(265, 400 - 32);
            ImageHolder.add(new ViewStone(squareID, randomNumX, randomNumY, 2));
        }
        else {
            int randomNumX = ThreadLocalRandom.current().nextInt(150+ (12-squareID)*110,150+(12-squareID)*110+50-16);
            int randomNumY = ThreadLocalRandom.current().nextInt(395,445-16);
            ImageHolder.add(new ViewStone(squareID,randomNumX,randomNumY,2));
        }
    }
    public void makeMove(Board b, int choosenSquareID, boolean isLeftMove, Player player) {
            player.dispatchCitizens(b);
            // Start to make move
            ArrayList<BoardSquare> bss = b.getListOfSquare();
            BoardSquare choosenSquare = b.getListOfSquare().get(choosenSquareID);
            int citizens = choosenSquare.getNumberOfCitizens();
            int currentSquareID = choosenSquareID;
            choosenSquare.setNumberOfCitizens(0);
            collectCitizen(currentSquareID);
            bss.set(currentSquareID, choosenSquare);

            while(citizens>0) {
                try{
                Thread.sleep(500);}
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
                distributeCitizen(currentSquareID);

                // decide if the turn is continued or stopped (get point or not)
                if(citizens==0) {
                    if(isLeftMove == true) {
                        if(currentSquareID == 11) currentSquareID = 0;
                        else currentSquareID++;
                        // Capture or continue the turn
                        if(bss.get(currentSquareID) instanceof MandarinSquare) {
                            MandarinSquare currentSquare = (MandarinSquare) bss.get(currentSquareID);
                            if(currentSquare.isEmpty()==true) {
                                player.captureSquare(bss,currentSquareID, isLeftMove);
                            }
                        }else {
                            BoardSquare currentSquare = (CitizenSquare) bss.get(currentSquareID);
                            if(currentSquare.isEmpty()==true) {
                                player.captureSquare(bss, currentSquareID, isLeftMove);
                            }else {
                                citizens = currentSquare.getNumberOfCitizens();
                                collectCitizen(currentSquareID);
                                currentSquare.setNumberOfCitizens(0);
                                bss.set(currentSquareID, currentSquare);
                            }
                        }
                    }else {
                        if(currentSquareID == 0) currentSquareID = 11;
                        else currentSquareID--;
                        // Capture or continue the turn
                        if(bss.get(currentSquareID) instanceof MandarinSquare) {
                            MandarinSquare currentSquare = (MandarinSquare) bss.get(currentSquareID);
                            if(currentSquare.isEmpty()==true) {
                                player.captureSquare(bss,currentSquareID, isLeftMove);
                            }
                        }else {
                            BoardSquare currentSquare = (CitizenSquare) bss.get(currentSquareID);
                            if(currentSquare.isEmpty()==true) {
                                player.captureSquare(bss, currentSquareID, isLeftMove);
                            }else {
                                citizens = currentSquare.getNumberOfCitizens();
                                currentSquare.setNumberOfCitizens(0);
                                bss.set(currentSquareID, currentSquare);
                            }
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

            // update the board square
            b.setListOfSquare(bss);
    }
    private class CitizenSquareUI extends StackPane{
        CitizenSquareUI(String name, Runnable action,int width, int height, int id) {
            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.web("black", 0.75)),
                    new Stop(1.0, Color.web("black", 0.15))
            );

            Rectangle bg = new Rectangle(width, height);
            Text text = new Text("Square number: "+ id +" \nCurrent Citizen: "+ Integer.toString(squares.get(id).getNumberOfCitizens()));
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
        root.setPrefSize(1080,720);
        Image bgImage =  new Image(getClass().getResource("gui/asset/MenuScene.png").toExternalForm());

//         START button
        Image startImage = new Image("gui/asset/START.png");
        ImageView startView = new ImageView(startImage);
        Image startImage2 = new Image("gui/asset/START2.png");
        ImageView startView2 = new ImageView(startImage2);

        startView.setFitHeight(80);
        startView.setFitWidth(200);
        startView2.setFitHeight(80);
        startView2.setFitWidth(200);

        Button startButton = new Button();
        startButton.setTranslateX(645);
        startButton.setTranslateY(200);
        startButton.setPrefSize(80,80);
        startButton.setStyle("-fx-background-color: #ffffff00;");
        startButton.setGraphic(startView);

        startButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                startButton.setGraphic(startView2);
            } else {
                startButton.setGraphic(startView);
            }
        });
        startButton.setOnAction(event-> stage.setScene(scene2));


        Image helpImage = new Image("gui/asset/HELP.png");
        ImageView helpView = new ImageView(helpImage);
        Image helpImage2 = new Image("gui/asset/HELP2.png");
        ImageView helpView2 = new ImageView(helpImage2);

        helpView.setFitHeight(80);
        helpView.setFitWidth(200);
        helpView2.setFitHeight(80);
        helpView2.setFitWidth(200);

        Button helpButton = new Button();
        helpButton.setTranslateX(650);
        helpButton.setTranslateY(300);
        helpButton.setPrefSize(80,80);
        helpButton.setStyle("-fx-background-color: #ffffff00;");
        helpButton.setGraphic(helpView);

        helpButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                helpButton.setGraphic(helpView2);
            } else {
                helpButton.setGraphic(helpView);
            }
        });
        helpButton.setOnAction(event-> {
            stage.setScene(scene3);

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


        Image exitImage = new Image("gui/asset/EXIT.png");
        ImageView exitView = new ImageView(exitImage);
        Image exitImage2 = new Image("gui/asset/EXIT2.png");
        ImageView exitView2 = new ImageView(exitImage2);

        exitView.setFitHeight(80);
        exitView.setFitWidth(200);
        exitView2.setFitHeight(80);
        exitView2.setFitWidth(200);

        Button exitButton = new Button();
        exitButton.setTranslateX(650);
        exitButton.setTranslateY(400);
        exitButton.setPrefSize(80,80);
        exitButton.setStyle("-fx-background-color: #ffffff00;");
        exitButton.setGraphic(exitView);

        exitButton.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
            if (show) {
                exitButton.setGraphic(exitView2);
            } else {
                exitButton.setGraphic(exitView);
            }
        });
        exitButton.setOnAction(event -> {Platform.exit();});


//        box.setBackground(new Background(new BackgroundFill(Color.web("black", 0.6),null,null)));
//        box.setTranslateX(350);
//        box.setTranslateY(250) ;
        root.getChildren().addAll(
                new ImageView(bgImage),
                startButton,
                helpButton,
                exitButton
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
//    private static class MenuItem extends StackPane {
//        MenuItem(String name, Runnable action){
//            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
//                    new Stop(0.1, Color.web("black",0.75)),
//                    new Stop(1.0, Color.web("black",0.15))
//            );
//
//            Rectangle bg = new Rectangle(250,30,gradient);
//            Text text  = new Text(name);
//            text.setFont(Font.font(22.0));
//            text.fillProperty().bind(
//                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY)
//            );
//
//            setOnMouseClicked(e->action.run());
//            getChildren().addAll(bg, text);
//
//        }
//    }

    public static void main(String[] args){
        launch(args);
    }
}

