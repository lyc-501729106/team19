package dao;

import pojo.StationInfo;

import java.util.List;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 23:02
 */
public interface StationDao {
    int insertStation(StationInfo stationInfo);

    List<StationInfo> allStation();

    int updateStation(Object[] objects);
    int delStation(Object[] objects);

    StationInfo oneStation(Object[] objects);
}
