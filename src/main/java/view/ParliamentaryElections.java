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
import model.ParliamentaryCandidates;


public class ParliamentaryElections extends Controller {

    Integer partyId = 0;

    @FXML
    private ListView<Pane> listView;

    @FXML
    private Button parlamentBack;

    @FXML
    private Button parliamentSubmit;

    @FXML
    private AnchorPane anchorParl;

    @FXML
    private Label warningSubmit;

    @FXML
    private void initialize() {
        ObservableList<Pane> paneObservableListParliamentary = FXCollections.observableArrayList();

        for (ParliamentaryCandidates parliamentaryCandidates : votingSystemDatabase.getParliamentaryCandidates()) {
            Text nameOfParty = new Text(votingSystemDatabase.getParties().get(parliamentaryCandidates.getId_party() - 1).getNameOfTheParty());
            nameOfParty.setLayoutX(50);
            nameOfParty.setLayoutY(50);
            nameOfParty.setFont(Font.font("Bell MT", 20));

            Text ideology = new Text(parliamentaryCandidates.getIdeology());
            ideology.setLayoutX(850);
            ideology.setLayoutY(50);
            ideology.setFont(Font.font("Bell MT", 20));

            Text leader = new Text(parliamentaryCandidates.getNameOfTheLeader());
            leader.setLayoutX(567);
            leader.setLayoutY(50);
            leader.setFont(Font.font("Bell MT", 20));

            Text governance = new Text(parliamentaryCandidates.getGovernance());
            governance.setLayoutX(1141);
            governance.setLayoutY(50);
            governance.setFont(Font.font("Bell MT", 20));

            Button voteParty = new Button("Vote");
            voteParty.setLayoutX(1400);
            voteParty.setLayoutY(20);
            voteParty.setFont(Font.font("Bell MT", 20));
            voteParty.setOnAction(e -> {
                for (Pane pane : paneObservableListParliamentary) {
                    pane.getChildren().get(4).setStyle(
                            "-fx-background-color: white;"
                    );
                }

                voteParty.setStyle(
                        "-fx-background-color: red;"
                );
                partyId = parliamentaryCandidates.getId();
                System.out.println(partyId);
            });

            Pane currentPane = new Pane();
            currentPane.getChildren().add(nameOfParty);
            currentPane.getChildren().add(ideology);
            currentPane.getChildren().add(leader);
            currentPane.getChildren().add(governance);
            currentPane.getChildren().add(voteParty);


            paneObservableListParliamentary.add(currentPane);
        }
        parliamentSubmit.setOnAction(e -> {
            if(partyId != 0){
                try {
                    votingSystemDatabase.insertParliamentaryElection(partyId, currentVoter.getId());
                    changeScene(e, "/ThanksForVoting.fxml");
                } catch (Exception exception) {
                    warningSubmit.setText("Can't load the new page. Sorry!");
                }
            }
            else{
                warningSubmit.setText("YOU HAVE TO VOTE FIRST!!");
            }
        });

        listView.setItems(paneObservableListParliamentary);
    }

    @FXML
    private void parlamentBackButton(ActionEvent event) {
        try {
            changeScene(event, "/Vote.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the new page. Sorry!");
        }
    }
}
