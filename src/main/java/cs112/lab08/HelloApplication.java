package cs112.lab08;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HelloApplication extends Application {

    //CONSTANTS
    private static final int SCENE_WIDTH = 350;
    private static final int SCENE_HEIGHT = 500;


    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    // Vars
    ImageView cardImageView;
    Image image;
    Label messageLabel;
    Button drawCardButton;


    @Override
    public void start(Stage stage) throws IOException {
        Label titleLabel = new Label("Loteria Card");
        VBox layout = new VBox();
        layout.setAlignment(javafx.geometry.Pos.TOP_CENTER); // Add this line

        // Defining GUI elements
        image = LOTERIA_CARDS[0].getImage();
        cardImageView = new ImageView(image);
        drawCardButton = new Button("Draw Card");

        // Changing imageView properties
        cardImageView.setPreserveRatio(true);
        cardImageView.setFitHeight(250);


        // EVENT HANDLING
        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Get a random card
                int cardNum = ThreadLocalRandom.current().nextInt(4);

                // update image and message
                image = LOTERIA_CARDS[cardNum].getImage();
                messageLabel.setText(LOTERIA_CARDS[cardNum].getCardName());
                cardImageView.setImage(image);
            }
        });
        drawCardButton.setTooltip(new Tooltip("Click this button to draw a new card"));

        // Style editing
		titleLabel.setStyle("-fx-font-size: 15px;");
        messageLabel = new Label(LOTERIA_CARDS[0].getCardName());
		messageLabel.setStyle("-fx-font-size: 16px;");

        drawCardButton.setStyle("-fx-text-fill: rgb(255,255,255);");
		drawCardButton.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 10; -fx-font-size: 16px; -fx-background-color: #008cff");


        // Add padding for each element
        VBox.setMargin(titleLabel, new Insets(15, 00, 30, 0));
        VBox.setMargin(cardImageView, new Insets(0, 0, 60, 0));
        VBox.setMargin(messageLabel, new Insets(0, 0, 10, 0));
        VBox.setMargin(drawCardButton, new Insets(0, 0, 10, 0));

        // Draw elements
        layout.getChildren().add(titleLabel);
        layout.getChildren().add(cardImageView);
        layout.getChildren().add(messageLabel);
        layout.getChildren().add(drawCardButton);

        // SETUP SCENE
        Scene scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Loteria");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}