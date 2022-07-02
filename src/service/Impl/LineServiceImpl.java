package service.Impl;

import com.alibaba.fastjson.JSON;
import dao.Impl.LineDaoImpl;
import dao.LineDao;
import pojo.LineInfo;
import pojo.StationInfo;
import service.LineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 吕译辰
 * @Date 2022/6/27 - 11:30
 */
public class LineServiceImpl implements LineService {
    LineDao lineDao = new LineDaoImpl();

    @Override
    public int insertLine(Object[] objects) {
        return lineDao.insertLine(objects);
    }

    @Override
    public LineInfo viewLine(Object[] objects) {
        return lineDao.viewLine(objects);
    }

    @Override
    public List[] oneOrAllLine(Object[] objects) {
        List[] lists = null;
        if (objects == null) {//查询所有的线路 包含站点
            LineInfo[] lineInfos = lineDao.allLine();//获取所有的线路  信息  不包括站点
            lists = new ArrayList[lineDao.lineCount()];
            for (int i = 0; i < lineInfos.length; i++) {
                lists[i] = new ArrayList();
                lists[i].add(lineInfos[i]);
                Object[] objects1 = {lineInfos[i].getLineName()};
                StationInfo[] stations = lineDao.stations(objects1);//每一个线路的  所有站点
                for (StationInfo station : stations) {
                    lists[i].add(station.getStationName());
                }
            }
        } else {//条件查询线路  包含站点！
            LineInfo lineInfo = lineDao.viewLine(objects);
            if (lineInfo != null) {
                lists = new ArrayList[1];
                lists[0] = new ArrayList();
                lists[0].add(lineInfo); // 按线路名查询一个线路信息 加入集合中！
                Object[] objects1 = {lineInfo.getLineName()};
                StationInfo[] stations = lineDao.stations(objects1);
                for (StationInfo station : stations) {
                    lists[0].add(station.getStationName()); //将这条线路的站点名字 也插入到集合中；
                }
            }
        }
        return lists;
    }

    @Override
    public int deleteOneLineID(Object[] objects) {
        return lineDao.deleteOneLineID(objects);
    }

    @Override
    public int deleteOneLineName(Object[] objects) {
        return lineDao.deleteOneLineName(objects);
    }

    public static void main(String[] args) {
        Object[] obj = {"105"};
        List[] lists = new LineServiceImpl().oneOrAllLine(obj);
        for (List list : lists) {
            for (Object o : list) {
                System.out.println(o);
            }
        }
    }

}
