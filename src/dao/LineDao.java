package dao;

import pojo.LineInfo;
import pojo.StationInfo;

/**
 * @Author 吕译辰
 * @Date 2022/6/27 - 11:32
 */
public interface LineDao {
    int insertLine(Object[] objects);
    //根据线路名 查询一条线路
    LineInfo viewLine(Object[] objects);

    //所有线路
    LineInfo[] allLine();
    //根据线路名  查询这个线路的站点！ 返回一个 站点数组
    StationInfo[] stations(Object[] objects);
    // 所有线路的数量！
    int lineCount();

    int deleteOneLineID(Object[] objects);

    int deleteOneLineName(Object[] objects);

}
