package player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@ToString
public class Player {

    private int playerId;
    private int teamId;
    private String playerName;
    private String position;
    private Timestamp createdAt;

}
