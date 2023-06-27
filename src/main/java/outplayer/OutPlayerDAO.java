package outplayer;

import player.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    private Connection connection;

    public OutPlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<OutPlayer> getAllOutPlayer() {
        List<OutPlayer> outPlayers = new ArrayList<>();
        String query = "select * from out_player_tb";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                OutPlayer outPlayer = new OutPlayer(
                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );
                outPlayers.add(outPlayer);
            }
            return outPlayers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outPlayers;
    }

}
