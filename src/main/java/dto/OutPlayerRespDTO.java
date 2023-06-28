package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
public class OutPlayerRespDTO {

    private int playerId;
    private String playerName;
    private String position;
    private String reason;
    private Timestamp createdAt;

    @Builder
    public OutPlayerRespDTO(int playerId, String playerName, String position, String reason, Timestamp createdAt) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position = position;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OutPlayerRespDTO{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", position='" + position + '\'' +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                '}'+'\n';
    }
}
