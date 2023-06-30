package player;

import dto.PositionRespDTO;

import java.sql.*;
import java.util.ArrayList;
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
    public int insert(Integer teamId, String playerName, String position) {
        String query = "insert into player_tb (team_id, name, position) values (?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);
            statement.setString(2, playerName);
            statement.setString(3, position);

            return statement.executeUpdate();

        } catch (NullPointerException e) {
            StringBuilder sb = new StringBuilder();
            if(teamId == null) {
                sb.append("teamId 는 필수 입니다.").append("\n");
            }
            if(playerName == null) {
                sb.append("name 은 필수 입니다.").append("\n");
            }
            if(position == null) {
                sb.append("position 은 필수 입니다.").append("\n");
            }
            System.out.println(sb);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;

    }

    /**
     * 선수목록 (select)
     */
    public List<Player> select(Integer teamId) {
        List<Player> players = new ArrayList<>();
        String query = "select * from player_tb where team_id = ?";
        try {
            PreparedStatement Statement = connection.prepareStatement(query);
            Statement.setInt(1, teamId);

            ResultSet rs = Statement.executeQuery();

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
                players.add(player);
            }
            return players;

        } catch (NullPointerException e) {
            StringBuilder sb = new StringBuilder();
            if(teamId == null) {
                sb.append("teamId 는 필수 입니다.").append("\n");
            }
            System.out.println(sb);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return players;
    }

    /**
     * 선수퇴출 (update)
     */
    public int update(int playerId) {
        String query = "update player_tb set team_id = ? where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNull(1, Types.INTEGER);
            statement.setInt(2, playerId);

            int result = statement.executeUpdate();
            return result;

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * 포지션별목록 (select 피벗테이블)
     */
    public List<PositionRespDTO> selectPivot() {
        List<PositionRespDTO> playersGroupByPosition = new ArrayList<>();
        String query = "SELECT\n" +
                "  position,\n" +
                "  MAX(CASE WHEN team_id = '1' THEN name END) AS 빙하타고내려온팀,\n" +
                "  MAX(CASE WHEN team_id = '2' THEN name END) AS 진실은언제나한팀,\n" +
                "  MAX(CASE WHEN team_id = '3' THEN name END) AS 나는공주다팀\n" +
                "FROM player_tb\n" +
                "GROUP BY position;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                PositionRespDTO positionRespDTO = PositionRespDTO.builder()
                        .position(rs.getString("position"))
                        .team1(rs.getString("빙하타고내려온팀"))
                        .team2(rs.getString("진실은언제나한팀"))
                        .team3(rs.getString("나는공주다팀"))
                        .build();
                playersGroupByPosition.add(positionRespDTO);
            }
            return playersGroupByPosition;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playersGroupByPosition;
    }


}
