package dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
@EqualsAndHashCode
public class TeamRespDTO {

    private int teamId;
    private int stadiumId;
    private String teamName;
    private double winningRate;
    private Timestamp teamCreatedAt;
    private String stadiumName;
    private String location;

    @Builder
    public TeamRespDTO(int teamId, int stadiumId, String teamName, double winningRate, Timestamp teamCreatedAt, String stadiumName, String location) {
        this.teamId = teamId;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.winningRate = winningRate;
        this.teamCreatedAt = teamCreatedAt;
        this.stadiumName = stadiumName;
        this.location = location;
    }
}
