package model;

public class PresidentialElections{
    private Integer id;
    private Integer idVoter;
    private Integer idPresidentCandidate;

    public PresidentialElections(Integer id, Integer idVoter, Integer idPresidentCandidate) {
        this.id = id;
        this.idVoter = idVoter;
        this.idPresidentCandidate = idPresidentCandidate;
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

    public Integer getIdPresidentCandidate() {
        return idPresidentCandidate;
    }

    public void setIdPresidentCandidate(Integer idPresidentCandidate) {
        this.idPresidentCandidate = idPresidentCandidate;
    }
}
