package view;


import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.EuroParliamentaryCandidatesAliances;
import model.ParliamentaryCandidates;
import model.PresidentialCandidate;

public class AdministratorSPage extends Controller {
    @FXML
    private Button resultPresident;

    @FXML
    private Button resultParliamentary;

    @FXML
    private Button resultEuro;

    @FXML
    private Button adminBack;

    @FXML
    private ListView<Pane> listViewResult;

    @FXML
    private void resultPres(ActionEvent event) {
        ObservableList<Pane> observableList = FXCollections.observableArrayList();
        System.out.println(votingSystemDatabase.getPresidentialCandidates().size());

        for (PresidentialCandidate presidentialCandidate : votingSystemDatabase.getPresidentialCandidates()) {
            Text text = new Text(presidentialCandidate.getFirstName() + " " + presidentialCandidate.getLastName());

            Text text2 = new Text(String.valueOf(votingSystemDatabase.countVotesPresidential(presidentialCandidate.getId())));

            text.setLayoutX(20);
            text.setLayoutY(20);
            text.setFont(Font.font("Bell MT", 20));
            text.setFill(Color.web("#3498DB"));

            text2.setLayoutX(600);
            text2.setLayoutY(20);
            text2.setFont(Font.font("Bell MT", 20));
            text2.setFill(Color.RED);
            Pane currentPane = new Pane();

            currentPane.getChildren().add(text);
            currentPane.getChildren().add(text2);
            observableList.add(currentPane);
        }
        listViewResult.setItems(observableList);
    }

    @FXML
    private void resultParl(ActionEvent event) {
        ObservableList<Pane> observableList = FXCollections.observableArrayList();
        System.out.println(votingSystemDatabase.getParliamentaryCandidates().size());

        for (ParliamentaryCandidates parliamentaryCandidate : votingSystemDatabase.getParliamentaryCandidates()) {
            Text text = new Text(votingSystemDatabase.getParties().get(parliamentaryCandidate.getId_party() - 1).getNameOfTheParty());

            Text text2 = new Text(String.valueOf(votingSystemDatabase.countVotesParliamentary(parliamentaryCandidate.getId())));

            text.setLayoutX(20);
            text.setLayoutY(20);
            text.setFont(Font.font("Bell MT", 20));
            text.setFill(Color.web("#3498DB"));

            text2.setLayoutX(600);
            text2.setLayoutY(20);
            text2.setFont(Font.font("Bell MT", 20));
            text2.setFill(Color.RED);
            Pane currentPane = new Pane();

            currentPane.getChildren().add(text);
            currentPane.getChildren().add(text2);
            observableList.add(currentPane);
        }
        listViewResult.setItems(observableList);

    }

    @FXML
    private void resultEuroParl(ActionEvent event) {
        ObservableList<Pane> observableList = FXCollections.observableArrayList();
        System.out.println(votingSystemDatabase.getEuroParliamentaryCandidatesAliances().size());

        for (EuroParliamentaryCandidatesAliances euroParliamentaryCandidatesAliance : votingSystemDatabase.getEuroParliamentaryCandidatesAliances()) {
            Text text = new Text(euroParliamentaryCandidatesAliance.getNameOfTheGroup());

            Text text2 = new Text(String.valueOf(votingSystemDatabase.countVotesEuroParliamentary(euroParliamentaryCandidatesAliance.getId())));
            text.setLayoutX(20);
            text.setLayoutY(20);
            text.setFont(Font.font("Bell MT", 20));
            text.setFill(Color.web("#3498DB"));

            text2.setLayoutX(600);
            text2.setLayoutY(20);
            text2.setFont(Font.font("Bell MT", 20));
            text2.setFill(Color.RED);
            Pane currentPane = new Pane();

            currentPane.getChildren().add(text);
            currentPane.getChildren().add(text2);
            observableList.add(currentPane);
        }
        listViewResult.setItems(observableList);
    }

    @FXML
    public void goBackToLogIn(ActionEvent event){
        try {
            changeScene(event, "/LogIn.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the new page. Sorry!");
        }
    }
}
