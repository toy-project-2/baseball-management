package stadium;

import java.sql.SQLException;
import java.util.List;

public class StadiumService {
    private StadiumDAO stadiumDAO;

    public StadiumService(StadiumDAO stadiumDAO) {
        this.stadiumDAO = stadiumDAO;
    }

    public Stadium insertStadium(int stadiumId, String stadiumName, String stadiumLocation) throws SQLException {
        if (stadiumName == null || stadiumLocation == null) {
            System.out.println("null 값을 입력할 수 없습니다.");
        }

        return this.stadiumDAO.insert(stadiumId, stadiumName, stadiumLocation);
    }

    public List<Stadium> selectStadiums() throws SQLException {
        List<Stadium> stadiumList = this.stadiumDAO.select();
        return stadiumList;
    }

    public Stadium selectStadium(String name) throws SQLException {
        Stadium stadium = this.stadiumDAO.selectByName(name);
        return stadium;
    }

    public Stadium updateStadium(int stadiumId, String updateStadiumName) throws SQLException {
        Stadium updatedStadium = this.stadiumDAO.updateByName(stadiumId, updateStadiumName);
        return updatedStadium;
    }

    public void deleteStadium(int stadiumId) throws SQLException {
        int result = this.stadiumDAO.delete(stadiumId);
        if (result >= 1) {
            System.out.println("삭제에 성공하였습니다.");
        }

    }
}