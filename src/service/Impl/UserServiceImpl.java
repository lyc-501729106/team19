package service.Impl;

import dao.Impl.UserDaoImpl;
import dao.UserDao;
import pojo.AdminInfo;
import service.UserService;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 15:47
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public AdminInfo Login(Object[] objects) {
        return userDao.Login(objects);
    }
}
