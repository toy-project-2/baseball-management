package stadium;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Stadium {

    private Integer stadiumId;
    private String stadiumName;
    private String stadiumLocation;
    private Timestamp stadiumCreatedAt;

    @Builder
    public Stadium(Integer stadiumId, String stadiumName, String stadiumLocation, Timestamp stadiumCreatedAt) {
        this.stadiumId = stadiumId;
        this.stadiumName = stadiumName;
        this.stadiumLocation = stadiumLocation;
        this.stadiumCreatedAt = stadiumCreatedAt;
    }
}
