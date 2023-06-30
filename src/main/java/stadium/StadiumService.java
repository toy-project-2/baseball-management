package stadium;

import java.util.List;

public class StadiumService {

    private static final StadiumDAO stadiumDAO = StadiumDAO.getInstance();
    private static StadiumService stadiumService;

    public static StadiumService getInstance() {
        if (stadiumService == null) {
            stadiumService = new StadiumService();
        }
        return stadiumService;
    }

    public void insert(String stadiumName) {
        int result = stadiumDAO.insert(stadiumName);
        if (result > 0) {
            System.out.println("등록에 성공하였습니다.");
        } else {
            System.out.println("등록에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    public void select() {
        List<Stadium> stadiumList = stadiumDAO.select();
        if (stadiumList.isEmpty()) {
            System.out.println("야구장이 비어 있습니다.");
        } else {
            for (Stadium stadium : stadiumList) {
                System.out.println(stadium);
            }
        }
    }

}