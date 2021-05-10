package service;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class VotingSystemDatabase {
    private ArrayList<Voter> voters;
    private ArrayList<PresidentialCandidate> presidentialCandidates;
    private ArrayList<ParliamentaryCandidates> parliamentaryCandidates;
    private ArrayList<EuroParliamentaryCandidatesAliances> euroParliamentaryCandidatesAliances;
    private ArrayList<Party> parties;

    public ArrayList<Party> getParties() {
        return parties;
    }

    public ArrayList<PresidentialCandidate> getPresidentialCandidates() {
        return presidentialCandidates;
    }

    public ArrayList<ParliamentaryCandidates> getParliamentaryCandidates() {
        return parliamentaryCandidates;
    }

    public ArrayList<EuroParliamentaryCandidatesAliances> getEuroParliamentaryCandidatesAliances() {
        return euroParliamentaryCandidatesAliances;
    }

    public ArrayList<Voter> getVoters() {
        return voters;
    }

    public VotingSystemDatabase() {
        this.fetchDatabase();
    }

    public void fetchDatabase() {

        this.voters = new ArrayList<>();
        this.presidentialCandidates = new ArrayList<>();
        this.parliamentaryCandidates = new ArrayList<>();
        this.euroParliamentaryCandidatesAliances = new ArrayList<>();
        this.parties = new ArrayList<>();

        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, firstName, lastName, CNP, phoneNumber, dateOfBirth, gender FROM Voter");

            while (resultSet.next()) {
                voters.add(new Voter(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("CNP"),
                        resultSet.getString("phoneNumber"), resultSet.getString("gender"), (resultSet.getDate("dateOfBirth")).toLocalDate()));
            }
        } catch (SQLException sqlException) {
            System.out.println("ERROR Voter");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, firstName, lastName, id_party, age FROM PresidentialCandidate");

            while (resultSet.next()) {
                presidentialCandidates.add(new PresidentialCandidate(resultSet.getInt("id"), resultSet.getString("firstName"),
                        resultSet.getString("lastName"), resultSet.getInt("age"), resultSet.getInt("id_party")));
            }
        } catch (SQLException sqlException) {
            System.out.println("ERROR Pres");
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        for (PresidentialCandidate presidentialCandidate : presidentialCandidates) {
            System.out.println(presidentialCandidate.getFirstName());
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, id_party, ideology, nameOfTheLeader, governance FROM ParlamentaryCandidateParties");

            while (resultSet.next()) {
                parliamentaryCandidates.add(new ParliamentaryCandidates(resultSet.getInt("id"), resultSet.getInt("id_party"), resultSet.getString("nameOfTheLeader"), resultSet.getString("ideology"), resultSet.getString("governance")));
            }
        } catch (SQLException sqlException) {
            System.out.println("ERROR PARL");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        for (ParliamentaryCandidates parliamentaryCandidate : parliamentaryCandidates) {
            System.out.println(parliamentaryCandidate.getIdeology());
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nameOfTheGroup FROM EuroParlamentaryCandidatesParties");

            while (resultSet.next()) {
                euroParliamentaryCandidatesAliances.add(new EuroParliamentaryCandidatesAliances(resultSet.getInt("id"), resultSet.getString("nameOfTheGroup")));
            }
        } catch (SQLException sqlException) {
            System.out.println("ERROR EUR");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, numberOfMembers, numberOfMandates, id_group FROM Parties");

            while (resultSet.next()) {
                parties.add(new Party(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("numberOfMembers"), resultSet.getInt("numberOfMandates"),
                        resultSet.getInt("id_group")));
            }
        } catch (SQLException sqlException) {
            System.out.println("ERROR Parties");
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public void insertVoters(Voter voter) {

        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into Voter values(?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, this.voters.size() + 1);
            preparedStatement.setString(2, voter.getFirstName());
            preparedStatement.setString(3, voter.getLastName());
            preparedStatement.setString(4, voter.getCNP());
            preparedStatement.setString(5, voter.getPhoneNumber());
            preparedStatement.setDate(6, java.sql.Date.valueOf(voter.getDateOfBirth()));
            preparedStatement.setString(7, voter.getGender());
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println("ERROR VOTER");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public Integer getEurolVoteId(Connection connection) throws SQLException {
        Integer idVote = 0;

        String query = "SELECT id FROM EuroParlamentaryElectionsVote";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            idVote = resultSet.getInt("id");
        }

        return idVote;
    }

    public void insertEuroElection(Integer idAliance, Integer idVoter) {

        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into EuroParlamentaryElectionsVote values(?, ?, ?)");
            preparedStatement.setInt(1, getEurolVoteId(connection) + 1);
            preparedStatement.setInt(2, idVoter);
            preparedStatement.setInt(3, idAliance);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public Integer getPresidentialVoteId(Connection connection) throws SQLException {
        Integer idVote = 0;

        String query = "SELECT id FROM PresidentialElectionsVote";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            idVote = resultSet.getInt("id");
        }

        return idVote;
    }

    public void insertPresidentialElection(Integer idCandidate, Integer idVoter) {

        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into PresidentialElectionsVote values(?, ?, ?)");
            preparedStatement.setInt(1, getPresidentialVoteId(connection) + 1);
            preparedStatement.setInt(2, idVoter);
            preparedStatement.setInt(3, idCandidate);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println("ERROR PRESIDENT");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public Integer getParliamentaryVoteId(Connection connection) throws SQLException {
        Integer idVote = 0;

        String query = "SELECT id FROM ParlamentaryElectionsVote";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            idVote = resultSet.getInt("id");
        }

        return idVote;
    }

    public void insertParliamentaryElection(Integer idParty, Integer idVoter) {

        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into ParlamentaryElectionsVote values(?, ?, ?)");
            preparedStatement.setInt(1, getParliamentaryVoteId(connection) + 1);
            preparedStatement.setInt(2, idVoter);
            preparedStatement.setInt(3, idParty);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println("ERROR PARL");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public Integer getIdVoterPresident(Connection connection, Integer currentVoterId) throws SQLException {

        Integer idVoter;
        Integer canVote = 1;

        String query = "SELECT id_voter FROM PresidentialElectionsVote";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            idVoter = resultSet.getInt("id_voter");
            if (idVoter.equals(currentVoterId)) {
                canVote = 0;
            }
        }
        return canVote;
    }

    public Boolean verifyVoterPresident(Integer idVoter) {
        System.out.println(idVoter);
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            if (getIdVoterPresident(connection, idVoter) == 1) {
                System.out.println("No other member");
                return true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("ERROR VERIFY VOTER PRESIDENT");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return false;
    }


    public Integer getIdVoterParliamentary(Connection connection, Integer currentVoterId) throws SQLException {

        Integer idVoter = null;
        Integer canVote = 1;

        String query = "SELECT id_voter FROM ParlamentaryElectionsVote";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            idVoter = resultSet.getInt("id_voter");
            if (idVoter.equals(currentVoterId)) {
                canVote = 0;
            }
        }

        return canVote;
    }

    public Boolean verifyVoterParliamentary(Integer idVoter) {
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            if (getIdVoterParliamentary(connection, idVoter) == 1) {
                System.out.println("No other member");
                return true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("ERROR VERIFY VOTER PARLAMENTARY");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return false;
    }

    public Integer getIdVoterEuroParliamentary(Connection connection, Integer currentVoterId) throws SQLException {

        Integer idVoter = null;
        Integer canVote = 1;

        String query = "SELECT id_voter FROM EuroParlamentaryElectionsVote";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            idVoter = resultSet.getInt("id_voter");
            if (idVoter.equals(currentVoterId)) {
                canVote = 0;
            }
        }

        return canVote;
    }

    public Boolean verifyEuroVoterParliamentary(Integer idVoter) {
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            if (getIdVoterEuroParliamentary(connection, idVoter) == 1) {
                System.out.println("No other member");
                return true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("ERROR VERIFY VOTER EUROPEAN PARLIAMENT");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    public Integer countVotesPresidential(Integer idCandidate) {
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;
        Integer number = 0;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS NumberOfVotes FROM PresidentialElectionsVote WHERE id_candidate = ?");
            preparedStatement.setInt(1, idCandidate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt("NumberOfVotes");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return number;
    }

    public Integer countVotesParliamentary(Integer idParty) {
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;
        Integer number = 0;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS NumberOfVotes FROM ParlamentaryElectionsVote WHERE id_candidateParties = ?");
            preparedStatement.setInt(1, idParty);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt("NumberOfVotes");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return number;
    }

    public Integer countVotesEuroParliamentary(Integer idAliance) {
        String url = "jdbc:sqlserver://DESKTOP-JU8VUJQ\\localhost:1433;databaseName=VotingSystem;";
        String userName = "andradaAdmin";
        String password = "adminpassword";

        Connection connection = null;
        Integer number = 0;

        try {
            connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS NumberOfVotes FROM EuroParlamentaryElectionsVote WHERE id_aliances = ?");
            preparedStatement.setInt(1, idAliance);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt("NumberOfVotes");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return number;
    }
}