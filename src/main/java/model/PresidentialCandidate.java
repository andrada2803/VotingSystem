package model;

public class PresidentialCandidate {
    private Integer id;
    private String firstName;
    private  String lastName;
    private Integer age;
    private Integer idParty;

    public PresidentialCandidate(Integer id, String firstName, String lastName, Integer age, Integer idParty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.idParty = idParty;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getParty() {
        return idParty;
    }

    public void setParty(Integer idParty) {
        this.idParty = idParty;
    }
}
