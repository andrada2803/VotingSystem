package model;

public class ParliamentaryElections {
    private Integer id;
    private Integer idVoter;
    private Integer idParty;

    public ParliamentaryElections(Integer id, Integer idVoter, Integer idParty) {
        this.id = id;
        this.idVoter = idVoter;
        this.idParty = idParty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVoter() {
        return idVoter;
    }

    public void setIdVoter(Integer idVoter) {
        this.idVoter = idVoter;
    }

    public Integer getIdParty() {
        return idParty;
    }

    public void setIdParty(Integer idParty) {
        this.idParty = idParty;
    }
}
