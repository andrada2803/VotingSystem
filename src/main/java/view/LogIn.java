package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Voter;
import validator.LogInValidator;

import java.sql.*;
import java.time.LocalDate;

public class LogIn extends Controller {
    @FXML
    private Button logInButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField CNP;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private ChoiceBox<String> gender;
    ObservableList<String> genders = FXCollections.observableArrayList("F", "M");

    private LogInValidator logInValidator;

    @FXML
    private void initialize() {
        gender.setItems(genders);
        logInValidator = new LogInValidator();

    }

    private Integer age;

    @FXML
    private Label labelWarning1;

    @FXML
    private Label labelWarning2;

    @FXML
    private Label labelWarning3;

    @FXML
    private Label labelWarning4;

    @FXML
    private Label labelWarning5;

    @FXML
    private void logIn(ActionEvent event) {
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";
        Integer ok = 1;

        Connection connection = null;

        logInValidator.emptyLabels(labelWarning1, labelWarning2, labelWarning3, labelWarning4, labelWarning5);

        if (!logInValidator.verifyCNP(labelWarning1, this.CNP.getText(), this.gender.getValue())) {
            System.out.println("CNP NOT Good");
        } else if (!logInValidator.verifyPhoneNumber(labelWarning2, this.phoneNumber.getText())) {
            System.out.println("Phone NOT Good");
        } else if (!logInValidator.verifyFirstName(labelWarning3, this.firstName.getText())) {
            System.out.println("First Name not good");
        } else if (!logInValidator.verifyLastName(labelWarning4, this.lastName.getText())) {
            System.out.println("Last Name not good");
        } else if (!logInValidator.isAllowedToVote(labelWarning5, this.dateOfBirth.getText())) {
            System.out.println("NOT Allowe to VOTE");
        } else {
            currentVoter = new Voter(votingSystemDatabase.getVoters().size() + 1, firstName.getText(), lastName.getText(), CNP.getText(), phoneNumber.getText(), gender.getValue(), LocalDate.parse(dateOfBirth.getText()));
            for (Voter voter : votingSystemDatabase.getVoters()) {
                if (voter.getCNP().equals(CNP.getText()) && !currentVoter.equals(voter)) {
                    labelWarning1.setText("This CNP was already used. Please check your CNP.");
                    return;
                }
                else if(logInValidator.checkIfAdministrator(this.CNP.getText()) && currentVoter.equals(voter)){
                    try {
                        changeScene(event, "/Administrator'sPage.fxml");
                    } catch (Exception e) {
                        System.out.println("Can't load the new page. Sorry!");
                    }
                    return;
                }

                if (currentVoter.equals(voter)) {
                    currentVoter.setId(voter.getId());
                    try {
                        changeScene(event, "/Vote.fxml");
                    } catch (Exception e) {
                        System.out.println("Can't load the new page. Sorry!");
                    }
                    return;
                }
            }
            votingSystemDatabase.insertVoters(currentVoter);
            try {
                changeScene(event, "/Vote.fxml");
            } catch (Exception e) {
                System.out.println("Can't load the new page. Sorry!");
            }
        }
    }
}
