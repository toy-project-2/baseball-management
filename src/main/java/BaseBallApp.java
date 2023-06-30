import db.DBConnection;
import player.PlayerService;
import stadium.StadiumService;
import team.TeamService;
import util.UrlUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseBallApp {

    /**
     * 야구장등록?name=잠실야구장
     * 야구장목록
     * 팀등록?stadiumId=1&name=NC
     * 팀목록
     * 선수등록?teamId=1&name=이대호&position=1루수
     * 선수목록?teamId=1
     * 퇴출등록?playerId=1&reason=도박
     * 퇴출목록
     * 포지션별목록
     */

    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getInstance();

        PlayerDAO pd = PlayerDAO.getInstance(connection);
        OutPlayerDAO od = OutPlayerDAO.getInstance(connection);
        PlayerService ps = PlayerService.getInstance(connection);
        StadiumService ss = StadiumService.getInstance();
        TeamService ts = TeamService.getInstance();

        while (true) {
            UrlUtil util = UrlUtil.run();

            if (util.getPath().equals("선수등록")) {
                ps.insertPlayer(
                        util.getIntegerParameter("teamId"),
                        util.getStringParameter("name"),
                        util.getStringParameter("position")
                );
            } else if (util.getPath().equals("선수목록")) {
                ps.getAllPlayers(util.getIntegerParameter("teamId"));
            } else if (util.getPath().equals("퇴출등록")) {
                ps.emitPlayer(util.getIntegerParameter("playerId"), util.getStringParameter("reason"));
            } else if (util.getPath().equals("퇴출목록")) {
                ps.getAllEmittedPlayers();
            } else if (util.getPath().equals("포지션별목록")) {
                ps.getAllPlayersGroupByPosition();
            } else if (util.getPath().equals("야구장등록")) {
                ss.insert(util.getStringParameter("name"));
            } else if (util.getPath().equals("야구장목록")) {
                ss.select();
            } else if (util.getPath().equals("팀등록")) {
                ts.insertTeam(util.getIntegerParameter("stadiumId"), util.getStringParameter("name"));
            } else if (util.getPath().equals("팀목록")) {
                ts.selectTeams();
            } else {
                System.out.println("⊙﹏⊙ 잘못된 형식입니다. 다시 입력해주세요!! ⊙﹏⊙\n");
            }
        }
    }
}
