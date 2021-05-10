package validator;

import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.Period;

public class LogInValidator {

    /**
     * @return true if voter's CNP is valid and meets all of the requirements
     */
    public boolean verifyCNP(Label lblCNP, String CNP, String gender) {
        if (CNP.length() != 13) {
            lblCNP.setText("Your CNP must have 13 digits. Please check your CNP.");
            return false;
        }
        if (!CNP.matches("[0-9]+")) {
            lblCNP.setText("Your CNP must contain only digits.Please check your CNP.");
            return false;
        }
        if (((CNP.charAt(0) == '6' || CNP.charAt(0) == '2') && gender.equals("F")) || ((CNP.charAt(0) == '5' || CNP.charAt(0) == '1') && gender.equals("M"))) {
            return true;
        } else {
            lblCNP.setText("The first digit of your CNP does not match your gender. Please check your CNP or gender choice box.");
            return false;

        }
    }

    /**
     * @return voter's age, computed as a difference between the current date and the voter's date of birth
     */
    public Integer computeVoterAge(String dateOfBirth) {
        LocalDate now = LocalDate.now();
        Period difference = Period.between(LocalDate.parse(dateOfBirth), now);
        return difference.getYears();
    }

    /**
     * @return true if voter is over 18 and is Allowed to vote
     */
    public boolean isAllowedToVote(Label lblage, String dateOfBirth) {
        if (computeVoterAge(dateOfBirth) >= 18) {
            return true;
        } else {
            lblage.setText("To be able to vote you have to be 18 or older.");
            return false;
        }
    }

    /**
     * @return true if voter's first name is formed only of letters, spaces and hyphens
     */
    public boolean verifyFirstName(Label lblFirstName, String firstName) {
        if (firstName.matches("[a-zA-Z- ]+") && Character.isUpperCase(firstName.charAt(0))) {
            return true;
        } else {
            lblFirstName.setText("First name incorrect. It must contain only letters and start with an uppercase letter.");
            return false;
        }
    }

    /**
     * @return true if voter's last name is formed only of letters, spaces and hyphens
     */
    public boolean verifyLastName(Label lblLastName, String lastName) {
        if (lastName.matches("[a-zA-Z- ]+") && Character.isUpperCase(lastName.charAt(0))) {
            return true;
        } else {
            lblLastName.setText("Last name incorrect. It must contain only letters and start with an uppercase letter.");
            return false;
        }
    }

    /**
     * @return true if voter's phone number has 10 digits & contains only digits
     */
    public boolean verifyPhoneNumber(Label lblphoneNumber, String phoneNumber) {
        if (phoneNumber.length() == 10 && phoneNumber.matches("[0-9]+")) {
            return true;
        } else {
            lblphoneNumber.setText("The phone number is incorrect. Please check it again!");
            return false;
        }
    }

    /**
     * @return true if the voter is the administrator
     */
    public boolean checkIfAdministrator(String CNP) {
        if (CNP.equals("6000000000000")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * makes all the labels empty
     */
    public void emptyLabels(Label label1, Label label2, Label label3, Label label4, Label label5) {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
    }
}
