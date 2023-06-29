package player.outplayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@ToString
public class OutPlayer {

    private int id;
    private int playerId;
    private String reason;
    private Timestamp createdAt;

}
