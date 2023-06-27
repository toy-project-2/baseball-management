package player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public void createPlayer (int teamId, String playerName, String position) {
        String query = "insert into player_tb (team_id, player_name, position, created_at) values(?, ?, ?, now())";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);
            statement.setString(2, playerName);
            statement.setString(3, position);

            int result = statement.executeUpdate();
            System.out.println("result = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getAllPlayer() {
        List<Player> players = new ArrayList<>();
        String query = "select * from player_tb";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

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

    public void deletePlayer(int playerId) {
        String query = "delete from player_tb where player_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emitPlayer(int playerId) {
        String query = "update player_tb set team_id = ? where player_Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNull(1, Types.INTEGER);
            statement.setInt(2, playerId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
