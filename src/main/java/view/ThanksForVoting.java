package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ThanksForVoting extends Controller {

    @FXML
    private Button thanksBack;

    @FXML
    private void thanksBackButton(ActionEvent event){
        try {
            changeScene(event, "/LogIn.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the new page. Sorry!");
        }
    }
}
