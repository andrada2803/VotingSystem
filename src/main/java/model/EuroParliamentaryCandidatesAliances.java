package model;

public class EuroParliamentaryCandidatesAliances {
    private Integer id;
    private String nameOfTheGroup;

    public EuroParliamentaryCandidatesAliances(Integer id, String nameOfTheGroup) {
        this.id = id;
        this.nameOfTheGroup = nameOfTheGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfTheGroup() {
        return nameOfTheGroup;
    }

    public void setNameOfTheGroup(String nameOfTheGroup) {
        this.nameOfTheGroup = nameOfTheGroup;
    }
}
