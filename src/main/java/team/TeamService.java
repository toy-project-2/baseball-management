package team;

import dto.TeamRespDTO;

import java.util.List;

public class TeamService {

    private TeamDAO teamDAO;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public Team insertTeam(int stadiumId, String teamName, double winningRate) {
        Team team = teamDAO.insert(stadiumId, teamName, winningRate);
        return team;
    }

    public List<TeamRespDTO> selectTeams() {
        List<TeamRespDTO> teamRespDTOS = teamDAO.selectTeams();
        return teamRespDTOS;
    }




}
