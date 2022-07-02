package service;

import pojo.StationInfo;

import java.util.List;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 23:00
 */
public interface StationService {
    int insertStation(StationInfo stationInfo);
    List<StationInfo> allStation();//无条件查询所有的站点
    int updateStation(Object[] objects);
    int delStation(Object[] objects);
    StationInfo oneStation(Object[] objects);
}
