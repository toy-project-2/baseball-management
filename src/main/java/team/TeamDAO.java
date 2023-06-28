package team;

import dto.TeamRespDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 팀 등록
     */
    public Team insert(int stadiumId, String teamName, double winningRate) {
        String query = "INSERT INTO team_tb (stadium_id, team_name, winning_rate, created_at) VALUE(?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, teamName);
            statement.setDouble(3, winningRate);
            int rowCount = statement.executeUpdate();
            if (rowCount > 0)
                return selectByName(teamName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 모든 팀 조회
     */
    public List<TeamRespDTO> selectTeams() {
        List<TeamRespDTO> teams = new ArrayList<>();
        String query = "SELECT * FROM team_tb LEFT OUTER JOIN stadium_tb ON team_tb.stadium_id = stadium_tb.stadium_id";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    TeamRespDTO teamRespDTO = TeamRespDTO.builder()
                            .teamId(resultSet.getInt("team_id"))
                            .teamName(resultSet.getString("team_name"))
                            .winningRate(resultSet.getDouble("winning_rate"))
                            .teamCreatedAt(resultSet.getTimestamp("created_at"))
                            .stadiumId(resultSet.getInt("stadium_id"))
                            .stadiumName(resultSet.getString("stadium_name"))
                            .location(resultSet.getString("stadium_location"))
                            .build();
                    teams.add(teamRespDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    /**
     * 팀 상세 조회 (팀 id로)
     */
    public Team selectById(int teamId) throws SQLException {
        String query = "SELECT * FROM team_tb WHERE team_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, teamId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return Team.builder()
                            .teamId(resultSet.getInt("team_id"))
                            .stadiumId(resultSet.getInt("stadium_id"))
                            .teamName(resultSet.getString("team_name"))
                            .winningRate(resultSet.getDouble("winning_rate"))
                            .teamCreatedAt(resultSet.getTimestamp("created_at"))
                            .build();
            }
        }
        return null;
    }

    /**
     * 팀 상세 조회 (팀 이름으로)
     */
    public Team selectByName(String teamName) throws SQLException {
        String query = "SELECT * FROM team_tb WHERE team_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teamName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return Team.builder()
                            .teamId(resultSet.getInt("team_id"))
                            .stadiumId(resultSet.getInt("stadium_id"))
                            .teamName(resultSet.getString("team_name"))
                            .winningRate(resultSet.getDouble("winning_rate"))
                            .teamCreatedAt(resultSet.getTimestamp("created_at"))
                            .build();
            }
        }
        return null;
    }

    /**
     * 팀 이름 수정
     */
    public Team update(int teamId, String teamName) {
        String query = "UPDATE team_tb SET team_name = ? where team_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teamName);
            statement.setInt(2, teamId);
            int rowCount = statement.executeUpdate();
            if (rowCount > 0)
                return selectByName(teamName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 팀 삭제
     */
    public int delete(int teamId) {
        String query = "DELETE FROM team_tb where team_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            int rowCount = statement.executeUpdate();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 전체 팀 데이터 삭제 (테스트 용도)
     */
    public void deleteAll() {
        String query = "DELETE FROM team_tb";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
