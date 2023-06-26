package outplayer;

import java.sql.Connection;

public class OutPlayerDAO {
    private Connection connection;

    public OutPlayerDAO(Connection connection) {
        this.connection = connection;
    }

}
