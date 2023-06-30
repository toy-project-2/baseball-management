package team;

import db.DBConnection;
import dto.TeamRespDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private static final Connection connection = DBConnection.getInstance();
    private static TeamDAO teamDAO;

    public static TeamDAO getInstance() {
        if (teamDAO == null) {
            teamDAO = new TeamDAO();
        }
        return teamDAO;
    }

    /**
     * 팀 등록
     */
    public int insert(Integer stadiumId, String teamName) {
        String query = "INSERT INTO team_tb (stadium_id, name) VALUE (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, teamName);
            return statement.executeUpdate();
        } catch (NullPointerException e) {
            StringBuilder sb = new StringBuilder();
            if(stadiumId == null) {
                sb.append("stadiumId 는 필수 입니다.").append("\n");
            }
            if(teamName == null) {
                sb.append("teamName 은 필수 입니다.").append("\n");
            }
            System.out.println(sb);
        } catch (SQLException e) {
            System.out.println("존재하지 않는 stadiumId 입니다.");
        }
        return -1;
    }

    /**
     * 모든 팀 조회
     */
    public List<TeamRespDTO> select() {
        List<TeamRespDTO> teams = new ArrayList<>();
        String query = "SELECT t.id teamId, t.name teamName, t.created_at teamCreatedAt, s.id stadiumId , s.name stadiumName FROM team_tb t LEFT OUTER JOIN stadium_tb s ON t.stadium_id = s.id";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    TeamRespDTO teamRespDTO = TeamRespDTO.builder()
                            .teamId(resultSet.getInt("teamId"))
                            .teamName(resultSet.getString("teamName"))
                            .teamCreatedAt(resultSet.getTimestamp("teamCreatedAt"))
                            .stadiumId(resultSet.getInt("stadiumId"))
                            .stadiumName(resultSet.getString("stadiumName"))
                            .build();
                    teams.add(teamRespDTO);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    /**
     * 전체 팀 데이터 삭제 (테스트 용도)
     */
    public void deleteAll() {
        String query = "DELETE FROM team_tb";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
