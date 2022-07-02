package service.Impl;

import dao.Impl.LineAndStationDaoImpl;
import dao.LineAndStationDao;
import service.LineAndStationService;

/**
 * @Author 吕译辰
 * @Date 2022/6/27 - 14:47
 */
public class LineAndStationServiceImpl implements LineAndStationService {
    LineAndStationDao las = new LineAndStationDaoImpl();
    @Override
    public int insertLineAndStation(Object[] objects) {
        return las.insertLineAndStation(objects);
    }
}
