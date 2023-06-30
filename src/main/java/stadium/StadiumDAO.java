package stadium;

import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {

    private Connection connection = DBConnection.getInstance();
    private static StadiumDAO stadiumDAO;

    public static StadiumDAO getInstance() {
        if (stadiumDAO == null) {
            stadiumDAO = new StadiumDAO();
        }
        return stadiumDAO;
    }

    /**
     * 야구장 등록
     */
    public int insert(String stadiumName) {
        String query = "INSERT INTO stadium_tb(name) VALUE (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, stadiumName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }


    /**
     * 야구장 전체 조회
     */
    public List<Stadium> select() {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium_tb";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Stadium stadium = Stadium.builder()
                            .stadiumId(resultSet.getInt("id"))
                            .stadiumName(resultSet.getString("name"))
                            .stadiumCreatedAt(resultSet.getTimestamp("created_at"))
                            .build();
                    stadiums.add(stadium);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stadiums;
    }

}