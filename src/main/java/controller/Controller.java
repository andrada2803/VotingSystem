package controller;

import service.VotingSystemDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Voter;

import java.io.IOException;

public class Controller {

    public static VotingSystemDatabase votingSystemDatabase = null;
    public static Voter currentVoter = null;

    public Controller(){
        if(votingSystemDatabase == null){
            votingSystemDatabase = new VotingSystemDatabase();
        }
        else{
            votingSystemDatabase.fetchDatabase();
        }
    }

    public void changeScene(ActionEvent event, String fxmlFilePath) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        Scene  scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}