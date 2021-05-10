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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.EuroParliamentaryCandidatesAliances;
import model.Party;

public class EuroparliamentaryElections extends Controller {

    private Integer alianceId = 0;

    @FXML
    private ListView<Pane> paneListViewEuro;
    @FXML
    private Button euroBack;

    @FXML
    private Button euroSubmit;

    @FXML
    private Label warningEuro;

    @FXML
    private AnchorPane anchorEuro;

    @FXML
    private void initialize() {
        ObservableList<Pane> paneObservableListEuro = FXCollections.observableArrayList();

        for (EuroParliamentaryCandidatesAliances euroParliamentaryCandidatesAliance : votingSystemDatabase.getEuroParliamentaryCandidatesAliances()) {
            Text nameOfGroup = new Text(euroParliamentaryCandidatesAliance.getNameOfTheGroup());
            nameOfGroup.setLayoutX(50);
            nameOfGroup.setLayoutY(50);
            nameOfGroup.setFont(Font.font("Bell MT", 20));

            Pane currentPane = new Pane();
            currentPane.getChildren().add(nameOfGroup);

            Integer index = 1;
            for (Party party : votingSystemDatabase.getParties()) {
                if (party.getIdAliance().equals(euroParliamentaryCandidatesAliance.getId())) {
                    Text alianceParties = new Text(party.getNameOfTheParty());
                    Text nbOfMandates = new Text(String.valueOf(party.getNumberOfMandates()));
                    alianceParties.setLayoutY(index * 40);
                    alianceParties.setFont(Font.font("Bell MT", 20));

                    nbOfMandates.setLayoutY(index * 40);
                    nbOfMandates.setFont(Font.font("Bell MT", 20));

                    index++;
                    alianceParties.setLayoutX(950);
                    nbOfMandates.setLayoutX(730);

                    currentPane.getChildren().add(alianceParties);
                    currentPane.getChildren().add(nbOfMandates);
                }
            }
            Button voteEuro = new Button("Vote");
            voteEuro.setLayoutX(1400);
            voteEuro.setLayoutY(50);
            voteEuro.setFont(Font.font("Bell MT", 20));

            voteEuro.setOnAction(e -> {
                for (Pane pane : paneObservableListEuro) {
                    pane.getChildren().get(pane.getChildren().size() - 1).setStyle(
                            "-fx-background-color: white;"
                    );
                }
                voteEuro.setStyle(
                        "-fx-background-color: red;"
                );
                alianceId = euroParliamentaryCandidatesAliance.getId();
            });

            currentPane.getChildren().add(voteEuro);
            paneObservableListEuro.add(currentPane);
        }

        euroSubmit.setOnAction(e -> {
            if (alianceId != 0) {
                try {
                    votingSystemDatabase.insertEuroElection(alianceId, currentVoter.getId());
                    changeScene(e, "/ThanksForVoting.fxml");
                } catch (Exception exception) {
                    warningEuro.setText("Can't load the new page. Sorry!");
                }
            } else {
                warningEuro.setText("YOU HAVE TO VOTE FIRST!");
            }
        });

        paneListViewEuro.setItems(paneObservableListEuro);
    }

    @FXML
    private void euroParlamentBackButton(ActionEvent event) {
        try {
            changeScene(event, "/Vote.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the new page. Sorry!");
        }
    }
}
