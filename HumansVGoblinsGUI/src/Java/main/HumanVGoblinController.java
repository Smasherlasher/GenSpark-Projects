package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HumanVGoblinController {


    @FXML
    Button enterButton;
    @FXML
    TextField name;

    @FXML
    private void sendData()throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(HumanVGoblin.class.getResource("HumanVGoblinGame.fxml"));
        Stage window = (Stage) enterButton.getScene().getWindow();
        Game gameController = new Game(name.getText());
        fxmlLoader.setController(gameController);
        window.setScene(new Scene(fxmlLoader.load(),750,900));
        gameController.displayImage();
        //gameController.playGame();
        //gameController.name = name.getText();
        //gameController.receiveData();
    }

}