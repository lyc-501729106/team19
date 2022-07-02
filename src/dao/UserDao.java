package dao;

import pojo.AdminInfo;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 15:48
 */
public interface UserDao {
    AdminInfo Login(Object[] objects);
}
