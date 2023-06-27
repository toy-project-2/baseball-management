package stadium;

import lombok.Getter;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class StadiumDAO {

    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 야구장 등록
     */
    public Stadium insert(int stadiumId, String stadiumName, String stadiumLocation) throws SQLException {
        String query = "INSERT INTO stadium_tb VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, stadiumName);
            statement.setString(3, stadiumLocation);
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            int rowCount = statement.executeUpdate();
            if (rowCount > 0)
                return selectByName(stadiumName);
        }
        return null;
    }


    /**
     * 야구장 전체 조회
     */
    public List<Stadium> select() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium_tb";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Stadium stadium = buildStadiumFromResultSet(resultSet);
                    stadiums.add(stadium);
                }
            }
        }
        return stadiums;
    }

    /**
     * 야구장 상세 조회 id로
     */
    public Stadium selectById(int stadiumId) throws SQLException {
        String query = "SELECT * FROM stadium_tb WHERE stadium_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return buildStadiumFromResultSet(resultSet);
            }
        }
        return null;
    }

    /**
     * 야구장 상세 조회 이름으로
     */
    public Stadium selectByName(String stadiumName) throws SQLException {
        String query = "SELECT * FROM stadium_tb WHERE stadium_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, stadiumName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return buildStadiumFromResultSet(resultSet);
            }
        }
        return null;
    }

    /**
     * 야구장 이름 수정
     */
    public Stadium updateByName(int stadium_id, String updateStadiumName) throws SQLException {
        String query = "UPDATE stadium_tb SET stadium_name = ? WHERE stadium_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updateStadiumName);
            statement.setInt(2, stadium_id);
            int rowCount = statement.executeUpdate();
            if (rowCount > 0)
                return selectByName(updateStadiumName);
        }
        return null;
    }

    /**
     * 야구장 삭제
     */
    public int delete(int stadiumId) throws SQLException {
        String query = "DELETE FROM stadium_tb WHERE stadium_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            int rowCount = statement.executeUpdate();
            return rowCount;
        }
    }


    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException {
        int stadiumId = resultSet.getInt("stadium_id");
        String stadiumName = resultSet.getString("stadium_name");
        String stadiumLocation = resultSet.getString("stadium_location");
        Timestamp stadiumCreatedAt = resultSet.getTimestamp("stadium_created_at");

        return Stadium.builder()
                .stadiumId(stadiumId)
                .stadiumName(stadiumName)
                .stadiumLocation(stadiumLocation)
                .stadiumCreatedAt(stadiumCreatedAt)
                .build();
    }
}
