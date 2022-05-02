package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class HumanVGoblin extends Application {

    public static void main(String[] args) {
    //main.Game game = new main.Game();
    //game.playGame();
    launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //scene.setFill(Color.BLACK);
        FXMLLoader fxmlLoader = new FXMLLoader(HumanVGoblin.class.getResource("HumanVGoblinTitle.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),750,500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
