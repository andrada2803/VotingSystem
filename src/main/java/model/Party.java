package model;

public class Party {
    private Integer id;
    private String nameOfTheParty;
    private Integer numberOfMembers;
    private Integer numberOfMandates;
    private Integer idAliance;

    public Party(Integer id, String nameOfTheParty, Integer numberOfMembers, Integer numberOfMandates, Integer idAliance) {
        this.id = id;
        this.nameOfTheParty = nameOfTheParty;
        this.numberOfMembers = numberOfMembers;
        this.numberOfMandates = numberOfMandates;
        this.idAliance = idAliance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfTheParty() {
        return nameOfTheParty;
    }

    public void setNameOfTheParty(String nameOfTheParty) {
        this.nameOfTheParty = nameOfTheParty;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(Integer numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public Integer getNumberOfMandates() {
        return numberOfMandates;
    }

    public void setNumberOfMandates(Integer numberOfMandates) {
        this.numberOfMandates = numberOfMandates;
    }

    public Integer getIdAliance() {
        return idAliance;
    }

    public void setIdAliance(Integer idAliance) {
        this.idAliance = idAliance;
    }
}
