package team;

import dto.TeamRespDTO;

import java.util.List;

public class TeamService {

    private final TeamDAO teamDAO = TeamDAO.getInstance();
    private static TeamService teamService;

    public static TeamService getInstance() {
        if (teamService == null) {
            teamService = new TeamService();
        }
        return teamService;
    }

    public void insertTeam(Integer stadiumId, String teamName) {
        int result = teamDAO.insert(stadiumId, teamName);
        if (result > 0) {
            System.out.println("팀등록에 성공하였습니다.");
        } else {
            System.out.println("팀등록에 실패하였습니다.");
        }
    }

    public void selectTeams() {
        List<TeamRespDTO> teamRespDTOS = teamDAO.selectTeams();
        if (teamRespDTOS.isEmpty()) {
            System.out.println("팀이 비어있습니다.");
        } else {
            for (TeamRespDTO teamRespDTO : teamRespDTOS) {
                System.out.println(teamRespDTO);
            }
        }
    }


}
