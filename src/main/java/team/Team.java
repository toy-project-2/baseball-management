package team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Team {

    private Integer teamId;
    private String teamName;
    private Double winningRate;
    private Timestamp teamCreatedAt;

    @Builder
    public Team(Integer teamId, String teamName, Double winningRate, Timestamp teamCreatedAt) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.winningRate = winningRate;
        this.teamCreatedAt = teamCreatedAt;
    }
}
