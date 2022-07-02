package dao.Impl;

import dao.LineDao;
import pojo.LineInfo;
import pojo.StationInfo;
import util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author 吕译辰
 * @Date 2022/6/27 - 11:33
 */
public class LineDaoImpl extends Database implements LineDao {
    @Override
    public int insertLine(Object[] objects) {
        String sql = "insert into line_info (line_name,cost,line_info,departure_time,collection_time,departure_interval) values " +
                "(?,?,?,?,?,?)";
        return super.executeUpdate(sql, objects);
    }

    @Override
    public LineInfo viewLine(Object[] objects) {
        String sql = "select * from line_info where line_name = ?";
        ResultSet resultSet = super.executeQuery(sql, objects);
        LineInfo lineInfo = null;
        try {
            if (resultSet.next()) {
                lineInfo = new LineInfo();
                lineInfo.setLineId(resultSet.getInt("line_id"));
                lineInfo.setLineName(resultSet.getString("line_name"));
                lineInfo.setCost(resultSet.getString("cost"));
                lineInfo.setLineInfo(resultSet.getString("line_info"));
                lineInfo.setDepartureTime(resultSet.getString("departure_time"));
                lineInfo.setCollectionTime(resultSet.getString("collection_time"));
                lineInfo.setDepartureInterval(resultSet.getInt("departure_interval"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return lineInfo;
    }

    @Override
    public int lineCount() {
        ResultSet rs = super.executeQuery("select COUNT(line_info.line_id) num from line_info", null);
        int num = 0;
        try {
            if (rs.next()) {
                num = rs.getInt("num");
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return num;
    }

    @Override
    public LineInfo[] allLine() {
        String sql = "select * from line_info";
        //查询有多少条公交线路
        ResultSet rs = super.executeQuery(sql, null);
        //创建公交线路数组
        LineInfo[] lineInfos = new LineInfo[lineCount()];
        int i = 0;
        try {
            while (true) {
                if (!rs.next()) break;
                lineInfos[i] = new LineInfo();
                lineInfos[i].setLineId(rs.getInt("line_id"));
                lineInfos[i].setLineName(rs.getString("line_name"));
                lineInfos[i].setCost(rs.getString("cost"));
                lineInfos[i].setLineInfo(rs.getString("line_info"));
                lineInfos[i].setDepartureTime(rs.getString("departure_time"));
                lineInfos[i].setCollectionTime(rs.getString("collection_time"));
                lineInfos[i].setDepartureInterval(rs.getInt("departure_interval"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return lineInfos;
    }

    /**
     * 一条公交线路 所经过的站点
     *
     * @param objects
     * @return 站点对象 数组！
     */
    @Override
    public StationInfo[] stations(Object[] objects) {
        // 按照线路名查询多个站点  要有站点的排序！  这个功能过一会记得实现！！
        String sql = "select c.station_id, c.station_name from line_info  a  ,line_and_station  b,station_info c " +
                "where  a.line_id = b.line_id and b.station_id=c.station_id and  a.line_name = ? ";
        ResultSet rs = super.executeQuery("select COUNT(c.station_id) count from line_info  a  ,line_and_station  b,station_info c " +
                "where  a.line_id = b.line_id and b.station_id=c.station_id and  a.line_name = ?", objects);
        int count = 0;
        try {
            if (rs.next()) {
                count = rs.getInt("count");
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StationInfo[] stationInfos = new StationInfo[count];
        rs = super.executeQuery(sql, objects);
        int i = 0;
        try {
            while (true) {
                if (!rs.next()) break;
                stationInfos[i] = new StationInfo();
                stationInfos[i].setStationId(rs.getInt("station_id"));
                stationInfos[i].setStationName(rs.getString("station_name"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return stationInfos;
    }

    /**
     * 删除一个线路  数据库用了触发器！
     * @param objects
     * @return 0 or 1
     */
    @Override
    public int deleteOneLineID(Object[] objects) {
        String sql = "delete from line_info where line_id = ?";
        return super.executeUpdate(sql, objects);
    }

    @Override
    public int deleteOneLineName(Object[] objects) {
        String sql = "delete from line_info where line_name = ?";
        return super.executeUpdate(sql, objects);
    }

    public static void main(String[] args) {
        Object[] objects = {"bt1"};
        StationInfo[] stations = new LineDaoImpl().stations(objects);
        for (StationInfo station : stations) {
            System.out.println(station);
        }
    }
}
