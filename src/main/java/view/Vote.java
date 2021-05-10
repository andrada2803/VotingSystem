package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Vote extends Controller {

    @FXML
    private Button president;

    @FXML
    private Button parlamentary;

    @FXML
    private Button europarlamentary;

    @FXML
    private Label votedPres;

    @FXML
    private Label votedParl;

    @FXML
    private Label votedEuro;

    @FXML
    private void initialize() {
        System.out.println(currentVoter.getFirstName());
    }

    @FXML
    private void presidentialElections(ActionEvent event) {
        if (!votingSystemDatabase.verifyVoterPresident(currentVoter.getId())) {
            votedPres.setText("You can't vote twice!");
        } else {
            try {
                changeScene(event, "/PresidentialElections.fxml");
            } catch (Exception e) {
                System.out.println("Can't load the new page. Sorry!");
            }
        }
    }

    @FXML
    private void parlamentaryElections(ActionEvent event) {
        if (!votingSystemDatabase.verifyVoterParliamentary(currentVoter.getId())) {
            votedParl.setText("You can't vote twice");
        } else {
            try {
                changeScene(event, "/ParliamentaryElections.fxml");
            } catch (Exception e) {
                System.out.println("Can't load the new page. Sorry!");
            }
        }

    }

    @FXML
    private void euroParlamentaryElections(ActionEvent event) {
        if(!votingSystemDatabase.verifyEuroVoterParliamentary(currentVoter.getId())){
            votedEuro.setText("You can't vote twice");
        } else {
            try {
                changeScene(event, "/EuroparliamentaryElections.fxml");
            } catch (Exception e) {
                System.out.println("Can't load the new page. Sorry!");
            }
        }
    }
}
