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
    private Timestamp teamCreatedAt;
    private String stadiumName;

    @Builder
    public TeamRespDTO(int teamId, int stadiumId, String teamName, Timestamp teamCreatedAt, String stadiumName) {
        this.teamId = teamId;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
        this.stadiumName = stadiumName;
    }
}
