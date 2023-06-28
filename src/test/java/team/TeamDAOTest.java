package team;

import db.DBConnection;
import dto.TeamRespDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stadium.StadiumDAO;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TeamDAOTest {

    Connection connection = DBConnection.getInstance();
    TeamDAO teamDAO = new TeamDAO(connection);
    StadiumDAO stadiumDAO = new StadiumDAO(connection);

    @BeforeEach
    private void insertStadiums() {
        stadiumDAO.insert("인천SSG랜더스필드", "인천");
        stadiumDAO.insert("잠실야구장", "서울");
        stadiumDAO.insert("사직야구장", "부산");
    }

    @AfterEach
    private void deleteAll() {
        teamDAO.deleteAll();
        stadiumDAO.deleteAll();
    }

    @DisplayName("팀 등록 테스트")
    @Test
    public void selectAllBaseballTeams() {
        // given
        final int stadiumId = 1;
        final String teamName = "SSG 랜더스";
        final double winningRate = 0.632;

        // when
        Team team = teamDAO.insert(stadiumId, teamName, winningRate);

        // then
        assertThat(team.getTeamName()).isEqualTo(teamName);
        assertThat(team.getStadiumId()).isEqualTo(stadiumId);
        assertThat(team.getWinningRate()).isEqualTo(winningRate);

    }

    @DisplayName("전체 팀 목록 조회 테스트")
    @Test
    public void insertBaseballTeam() {
        // given
        final int stadiumId1 = 1;
        final String teamName1 = "SSG 랜더스";
        final double winningRate1 = 0.632;

        final int stadiumId2 = 2;
        final String teamName2 = "LG 트윈스";
        final double winningRate2 = 0.623;

        final int stadiumId3 = 3;
        final String teamName3 = "롯데 자이언츠";
        final double winningRate3 = 0.500;

        teamDAO.insert(stadiumId1, teamName1, winningRate1);
        teamDAO.insert(stadiumId2, teamName2, winningRate2);
        teamDAO.insert(stadiumId3, teamName3, winningRate3);

        // when
        List<TeamRespDTO> teams = teamDAO.selectTeams();

        for (TeamRespDTO team : teams) {
            System.out.println("team = " + team);
        }

        // then
        assertThat(teams).isNotEmpty();
        assertThat(teams.size()).isEqualTo(3);
        assertThat(teams).extracting("teamName")
                .contains(teamName1, teamName2, teamName3);
        assertThat(teams).extracting("winningRate")
                .contains(winningRate1, winningRate2, winningRate3);

    }


}