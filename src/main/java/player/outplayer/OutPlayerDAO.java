package player.outplayer;

import dto.OutPlayerRespDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    private static Connection connection;
    private static OutPlayerDAO outPlayerDAO;

    private OutPlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public static OutPlayerDAO getInstance(Connection connection) {
        if (outPlayerDAO == null)
            outPlayerDAO = new OutPlayerDAO(connection);
        return outPlayerDAO;
    }

    /**
     * 퇴출등록 (insert)
     */
    public int insertOutPlayerByQuery (int playerId, String reason) {
        String query = "insert into out_player_tb (player_id, reason, created_at) values (?, ?, now())";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            statement.setString(2, reason);

            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 퇴출목록 (select (outer join))
     */
    public List<OutPlayerRespDTO> selectOutPlayerByQuery() {
        List<OutPlayerRespDTO> outPlayers = new ArrayList<>();
        String query = "select player_tb.player_id, player_tb.player_name, player_tb.position, out_player_tb.reason, out_player_tb.created_at\n" +
                "from out_player_tb left outer join player_tb on out_player_tb.player_id = player_tb.player_id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                OutPlayerRespDTO outPlayerRespDTO = OutPlayerRespDTO.builder()
                        .playerId(rs.getInt("player_id"))
                        .playerName(rs.getString("player_name"))
                        .position(rs.getString("position"))
                        .reason(rs.getString("reason"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .build();
                outPlayers.add(outPlayerRespDTO);
            }
            return outPlayers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outPlayers;
    }

}
