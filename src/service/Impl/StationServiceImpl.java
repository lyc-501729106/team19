package service.Impl;

import dao.Impl.StationDaoImpl;
import dao.StationDao;
import pojo.StationInfo;
import service.StationService;

import java.util.List;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 23:00
 */
public class StationServiceImpl implements StationService {
    StationDao stationDao = new StationDaoImpl();
    @Override
    public int insertStation(StationInfo stationInfo) {
        return stationDao.insertStation(stationInfo);
    }

    @Override
    public List<StationInfo> allStation() {
        return stationDao.allStation();
    }
    @Override
    public int updateStation(Object[] objects) {
        return stationDao.updateStation(objects);
    }

    @Override
    public int delStation(Object[] objects) {
        return stationDao.delStation(objects);
    }

    @Override
    public StationInfo oneStation(Object[] objects) {
        return stationDao.oneStation(objects);
    }
}
