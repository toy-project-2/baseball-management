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

}
