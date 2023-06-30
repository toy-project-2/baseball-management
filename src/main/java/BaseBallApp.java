import db.DBConnection;
import player.PlayerDAO;
import player.PlayerService;
import player.outplayer.OutPlayerDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;



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

            while(true) {
                System.out.print("어떤 기능을 요청하시겠습니까? : ");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();

                if(s.equals("exit")){
                    System.out.println("시스템 종료");
                    break;
                }

                UrlUtil util = new UrlUtil(s);
                util.printAllParameter();

                if (util.getPath().equals("선수등록")) {
                    ps.insertPlayer(
                            Integer.parseInt(util.getParameter("teamId")),
                            util.getParameter("name"),
                            util.getParameter("position")
                    );

                } else if (util.getPath().equals("선수목록")) {
                    ps.getAllPlayers(
                            Integer.parseInt(util.getParameter("teamId"))
                    );

                } else if (util.getPath().equals("퇴출등록")) {
                    ps.emitPlayer(
                            Integer.parseInt(util.getParameter("playerId")),
                            util.getParameter("reason")
                    );

                } else if (util.getPath().equals("퇴출목록")) {
                    ps.getAllOutPlayers();

                } else if (util.getPath().equals("포지션별목록")) {
                    ps.getAllPlayersGroupByPosition();
                }
            }

        }

    }





