package model;

import java.time.LocalDate;

public class Voter {
    private String firstName;
    private String lastName;
    private String CNP;
    private String phoneNumber;
    private String gender;
    private LocalDate dateOfBirth;
    private Integer id;

    public Voter(Integer id, String firstName, String lastName, String CNP, String phoneNumber, String gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object voterEquals){
        Voter myVoter = (Voter) voterEquals;

        if(myVoter.getCNP().equals(this.CNP) && myVoter.getFirstName().equals(this.firstName) && myVoter.getLastName().equals(this.lastName)
        && myVoter.getDateOfBirth().equals(this.dateOfBirth) && myVoter.getGender().equals(this.gender) && myVoter.getPhoneNumber().equals(this.phoneNumber)){
            return  true;
        }
        else{
            return false;
        }
    }
}
