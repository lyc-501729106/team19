package dao.Impl;

import util.Database;
import dao.UserDao;
import pojo.AdminInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 15:48
 */
public class UserDaoImpl extends Database implements UserDao {
    @Override
    public AdminInfo Login(Object[] objects) {
        String sql = "select * from admin_info where users = ? and pwd = ?";
        ResultSet rs = executeQuery(sql, objects);
        AdminInfo adminInfo = null;
        try {
            if (rs.next()) {
                adminInfo = new AdminInfo(
                        rs.getInt("admin_id"),
                        rs.getString("users"),
                        rs.getString("pwd"),
                        rs.getString("sex"),
                        rs.getString("phone")
                );
                /*adminInfo = new AdminInfo();
                adminInfo.setAdminId(rs.getInt("admin_id"));
                adminInfo.setUsers(rs.getString("users"));
                adminInfo.setPwd(rs.getString("pwd"));
                adminInfo.setSex(rs.getString("sex"));
                adminInfo.setPhone(rs.getString("phone"));*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return adminInfo;
    }
}
