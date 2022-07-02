package dao.Impl;

import dao.StationDao;
import pojo.StationInfo;
import util.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 23:02
 */
public class StationDaoImpl extends Database implements StationDao {
    @Override
    public int insertStation(StationInfo stationInfo) {
        String sql = "insert into station_info(station_name) values (?)";
        Object[] objects = {stationInfo.getStationName()};
        return super.executeUpdate(sql, objects);
    }

    @Override
    public List<StationInfo> allStation() {
        String sql = "select * from station_info";
        ResultSet rs = super.executeQuery(sql, null);
        StationInfo stationInfo = null;
        List<StationInfo> list = new ArrayList<>();
        try {
            while (rs.next()) {
                stationInfo = new StationInfo();
                stationInfo.setStationId(rs.getInt("station_id"));
                stationInfo.setStationName(rs.getString("station_name"));
                list.add(stationInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int updateStation(Object[] objects) {
        String sql = "update station_info set station_name = ? where station_id = ?";
        return super.executeUpdate(sql, objects);
    }

    @Override
    public int delStation(Object[] objects) {
        String sql = "delete from station_info where station_id = ?";
        return super.executeUpdate(sql,objects);
    }

    @Override
    public StationInfo oneStation(Object[] objects) {
        String sql = "select * from station_info where station_name = ?";
        ResultSet resultSet = super.executeQuery(sql, objects);
        StationInfo stationInfo = null;
        try {
            if (resultSet.next()) {
                stationInfo = new StationInfo();
                stationInfo.setStationId(resultSet.getInt("station_id"));
                stationInfo.setStationName(resultSet.getString("station_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return stationInfo;
    }
}
