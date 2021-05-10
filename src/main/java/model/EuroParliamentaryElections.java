package model;

public class EuroParliamentaryElections {
    private Integer id;
    private Integer idVoter;
    private Integer idAliance;

    public EuroParliamentaryElections(Integer id, Integer idVoter, Integer idAliance) {
        this.id = id;
        this.idVoter = idVoter;
        this.idAliance = idAliance;
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

    public Integer getIdAliance() {
        return idAliance;
    }

    public void setIdAliance(Integer idAliance) {
        this.idAliance = idAliance;
    }
}
