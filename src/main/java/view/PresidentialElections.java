package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.PresidentialCandidate;


public class PresidentialElections extends Controller {

    Integer candidateId = 0;

    @FXML
    ListView<Pane> paneListViewPresident;

    @FXML
    private Button presidentBack;

    @FXML
    private Button submitPresident;

    @FXML
    private AnchorPane anchorPres;

    @FXML
    private Label warningSubmit;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        ObservableList<Pane> paneObservableList = FXCollections.observableArrayList();

        for (PresidentialCandidate candidate : votingSystemDatabase.getPresidentialCandidates()) {
            Text name = new Text(candidate.getFirstName() + " " + candidate.getLastName());
            name.setLayoutX(50);
            name.setLayoutY(50);
            name.setFont(Font.font("Bell MT", 20));

            Text age = new Text(String.valueOf(candidate.getAge()));
            age.setLayoutX(1000);
            age.setLayoutY(50);
            age.setFont(Font.font("Bell MT", 20));

            Text party = new Text(votingSystemDatabase.getParties().get(candidate.getParty() - 1).getNameOfTheParty());
            party.setLayoutY(50);
            party.setLayoutX(500);
            party.setFont(Font.font("Bell MT", 20));

            Button votePresident = new Button("Vote");
            votePresident.setLayoutX(1400);
            votePresident.setLayoutY(20);
            votePresident.setFont(Font.font("Bell MT", 20));
            votePresident.setOnAction(e -> {
                for (Pane pane : paneObservableList) {
                    pane.getChildren().get(2).setStyle(
                            "-fx-background-color: white;"
                    );
                }
                votePresident.setStyle(
                        "-fx-background-color: red;"
                );
                candidateId = candidate.getId();
                System.out.println(candidateId);

            });

            Pane currentPane = new Pane();
            currentPane.getChildren().add(name);
            currentPane.getChildren().add(age);
            currentPane.getChildren().add(votePresident);
            currentPane.getChildren().add(party);

            paneObservableList.add(currentPane);
        }

        submitPresident.setOnAction(e -> {
            if(candidateId != 0){
                try {
                    votingSystemDatabase.insertPresidentialElection(candidateId, currentVoter.getId());
                    changeScene(e, "/ThanksForVoting.fxml");
                } catch (Exception exception) {
                    warningSubmit.setText("Can't load the new page. Sorry!");
                }
            }
            else{
                warningSubmit.setText("YOU HAVE TO VOTE FIRST!!");
            }
        });

        paneListViewPresident.setItems(paneObservableList);
    }


    @FXML
    private void backButtonPresident(ActionEvent event) {
        try {
            changeScene(event, "/Vote.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the new page. Sorry!");
        }
    }

}
