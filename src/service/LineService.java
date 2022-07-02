package service;

import pojo.LineInfo;

import java.util.List;


public interface LineService {
    int insertLine(Object[] objects);
    // 以线路名查询一条线路 不包括站点信息
    LineInfo viewLine(Object[] objects);

    /**
     * 获取 多条线路
     * @return 多条线路  包括站点 存在集合数组中  一个集合就是一条线路
     */
    List[] oneOrAllLine(Object[] objects);

    int deleteOneLineID(Object[] objects);//删除一条线路 数据库用了触发器来删除另一个表中的相关信息！
    int deleteOneLineName(Object[] objects);
}
