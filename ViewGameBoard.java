package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Presents the user with the game graphical user interface
 */
public class ViewGameBoard extends Application
{
    private Canvas gameCanvas;
    private ControllerGameBoard controller;
    private GameBoardLabel fishRemaining;
    private GameBoardLabel guessesRemaining;
    private GameBoardLabel message;

    public static void main(String[] args)
    {
        // TODO: launch the app
        launch();
    }

    public void updateHeader() {

        //TODO update labels
        fishRemaining.setText ("Fish: " + controller.modelGameBoard.getFishRemaining());
        //"Fish: " + controller.modelGameBoard.getFishRemaining()
        guessesRemaining.setText ("Bait: " + controller.modelGameBoard.getGuessesRemaining());
        //"Bait: " + controller.modelGameBoard.getGuessesRemaining()
        if(controller.fishWin()) {
            message.setText("Fishes win!");
            //"Fishes win!"
        } else if(controller.playerWins()) {
            message.setText("You win!");
            //"Fishes win!"
        } else {
            message.setText("Find the fish!");
            //"Find the fish!"
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        controller = new ControllerGameBoard();
        // TODO initialize gameCanvas
        Canvas gameCanvas = new Canvas();
        fishRemaining = new GameBoardLabel();
        guessesRemaining = new GameBoardLabel();
        message = new GameBoardLabel();

        // TODO display game there are infinite ways to do this, I used BorderPane with HBox and VBox.
        BorderPane root = new BorderPane();

        updateHeader();

        VBox buttonColumns = new VBox(10);
        buttonColumns.setAlignment(Pos.BOTTOM_CENTER);

        for (int row=0; row < ModelGameBoard.DIMENSION; row++) {
            // TODO: create row container
            HBox buttonRows = new HBox(10);

            for (int col=0; col < ModelGameBoard.DIMENSION; col++) {
                GameBoardButton button = new GameBoardButton(row, col, controller.modelGameBoard.fishAt(row,col));
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> {
                    controller.makeGuess(finalRow, finalCol);
                    if(!controller.isGameOver()) {
                        button.handleClick();
                        updateHeader();
                    }
                });
                // TODO: add button to row
                buttonRows.getChildren().add(button);
            }
            // TODO: add row to column
            buttonColumns.getChildren().add(row, buttonRows);
        }
        // TODO: create scene, stage, set title, and show

        HBox Label = new HBox(60, fishRemaining, guessesRemaining, message);
        root.setCenter(Label);
        Label.setAlignment(Pos.CENTER);
        root.setBottom(buttonColumns);
        BorderPane.setMargin(Label, new Insets(25.0, 25.0, 25.0, 25.0));
        BorderPane.setMargin(buttonColumns, new Insets(0.0, 45.0, 15.0, 45.0));
        Scene scene = new Scene(root, 450, 300);
        stage.setScene(scene);
        stage.setTitle("Gone Fishing");
        stage.show();
    }
}