package player;

import player.outplayer.OutPlayerDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class PlayerService {

    private static Connection connection;
    private static PlayerService playerService;
    private final static PlayerDAO playerDAO = PlayerDAO.getInstance(connection);
    private final static OutPlayerDAO outPlayerDAO = OutPlayerDAO.getInstance(connection);

    private PlayerService(Connection connection) {
        this.connection = connection;
    }

    public static PlayerService getInstance(Connection connection) {
        if (playerService == null)
            playerService = new PlayerService(connection);
        return playerService;
    }

    /**
     * 선수등록 (insert() 호출)
     */
    public void insertPlayer(int teamId, String playerName, String position) {
        int result =  playerDAO.insert(teamId, playerName, position);
        if ( result == 1 )
            System.out.println("성공");
        else
            System.out.println("실패");
    }

    /**
     * 선수목록 (select() 호출)
     */
    public void getAllPlayers(int teamid) {
        System.out.println(playerDAO.select(teamid));
    }

    /**
     * 선수퇴출등록
     * (transaction 관리)
     */
    public void emitPlayer(int playerId, String reason) throws SQLException{
        try {
            connection.setAutoCommit(false);

            playerDAO.update(playerId);
            outPlayerDAO.insert(playerId, reason);

            connection.commit();
            System.out.println("성공");
        } catch (SQLException e) {
            connection.rollback();
//            e.printStackTrace();
            System.out.println("실패");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * 퇴출목록
     */
    public void getAllOutPlayers() {
        System.out.println(outPlayerDAO.select());
    }

    /**
     * 포지션별목록
     */
    public void getAllPlayersGroupByPosition() {
        System.out.println(playerDAO.selectPivot());
    }

}
