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
    private Timestamp stadiumCreatedAt;

    @Builder
    public Stadium(Integer stadiumId, String stadiumName, Timestamp stadiumCreatedAt) {
        this.stadiumId = stadiumId;
        this.stadiumName = stadiumName;
        this.stadiumCreatedAt = stadiumCreatedAt;
    }
}
