package dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PositionRespDTO {
    private String position;
    private String team1;
    private String team2;
    private String team3;

    @Builder
    public PositionRespDTO(String position, String team1, String team2, String team3) {
        this.position = position;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
    }

    @Override
    public String toString() {
        return "PositionRespDTO{" +
                "position='" + position + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", team3='" + team3 + '\'' +
                '}' + '\n';
    }
}
