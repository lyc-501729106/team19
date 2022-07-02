package dao.Impl;

import dao.LineAndStationDao;
import util.Database;

/**
 * @Author 吕译辰
 * @Date 2022/6/27 - 14:48
 */
public class LineAndStationDaoImpl extends Database implements LineAndStationDao {
    @Override
    public int insertLineAndStation(Object[] objects) {
        String sql = "insert into line_and_station(line_id,station_id) values(?,?)";
        return super.executeUpdate(sql,objects);
    }
}
