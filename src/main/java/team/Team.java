package team;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Team {

    private Integer teamId;
    private String teamName;
    private Integer stadiumId;
    private Timestamp teamCreatedAt;

    @Builder
    public Team(Integer teamId, Integer stadiumId, String teamName, Timestamp teamCreatedAt) {
        this.teamId = teamId;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }
}
