package model;

public class ParliamentaryCandidates {
    private Integer id;
    private Integer id_party;
    private String nameOfTheLeader;
    private String ideology;
    private String governance;

    public ParliamentaryCandidates(Integer id, Integer id_party, String nameOfTheLeader, String ideology, String governance) {
        this.id = id;
        this.id_party = id_party;
        this.nameOfTheLeader = nameOfTheLeader;
        this.ideology = ideology;
        this.governance = governance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_party() {
        return id_party;
    }

    public void setId_party(Integer id_party) {
        this.id_party = id_party;
    }

    public String getNameOfTheLeader() {
        return nameOfTheLeader;
    }

    public void setNameOfTheLeader(String nameOfTheLeader) {
        this.nameOfTheLeader = nameOfTheLeader;
    }

    public String getIdeology() {
        return ideology;
    }

    public void setIdeology(String ideology) {
        this.ideology = ideology;
    }

    public String getGovernance() {
        return governance;
    }

    public void setGovernance(String governance) {
        this.governance = governance;
    }
}
