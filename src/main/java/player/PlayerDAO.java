package player;

import dto.PositionRespDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlayerDAO {
    private static Connection connection;
    private static PlayerDAO playerDAO;

    private PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public static PlayerDAO getInstance(Connection connection) {
        if (playerDAO == null)
            playerDAO = new PlayerDAO(connection);
        return playerDAO;
    }

    /**
     * 선수등록 (insert)
     */
    public int insertPlayerByQuery (int teamId, String playerName, String position) {
        String query = "insert into player_tb (team_id, player_name, position, created_at) values(?, ?, ?, now())";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);
            statement.setString(2, playerName);
            statement.setString(3, position);

            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 선수목록 (select)
     */
    public List<Player> selectPlayerByQuery(int teamId) {
        List<Player> players = new ArrayList<>();
        String query = "select * from player_tb where team_id = ?";
        try {
            PreparedStatement Statement = connection.prepareStatement(query);
            Statement.setInt(1, teamId);

            ResultSet rs = Statement.executeQuery();

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("player_id"),
                        rs.getInt("team_id"),
                        rs.getString("player_name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
                players.add(player);
            }
            return players;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * 선수퇴출 (update)
     */
    public int emitPlayerByQuery(int playerId) {
        String query = "update player_tb set team_id = ? where player_Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNull(1, Types.INTEGER);
            statement.setInt(2, playerId);

            int result = statement.executeUpdate();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 포지션별목록 (피벗테이블)
     */
    public List<PositionRespDTO> playerGroupByPosition() {
        List<PositionRespDTO> playersGroupByPosition = new ArrayList<>();
        String query = "SELECT\n" +
                "  position,\n" +
                "  MAX(CASE WHEN team_id = '1' THEN player_name END) AS team1,\n" +
                "  MAX(CASE WHEN team_id = '2' THEN player_name END) AS team2,\n" +
                "  MAX(CASE WHEN team_id = '3' THEN player_name END) AS team3\n" +
                "FROM player_tb\n" +
                "GROUP BY position";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                PositionRespDTO positionRespDTO = PositionRespDTO.builder()
                        .position(rs.getString("position"))
                        .team1(rs.getString("team1"))
                        .team2(rs.getString("team2"))
                        .team3(rs.getString("team3"))
                        .build();
                playersGroupByPosition.add(positionRespDTO);
            }
            return playersGroupByPosition;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playersGroupByPosition;
    }

    public void deletePlayerByQuery(int playerId) {
        String query = "delete from player_tb where player_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
