package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author 吕译辰
 * @Date 2022/3/21 - 20:40
 * 用数据库连接池来获取连接...
 */
public class Database {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    /**
     * 传统方式获取数据库连接
     *
     */
    public Database() {
        try {
           /* String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/bus_select_system?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
            String uname = "root";
            String password = "root";*/

            /*FileInputStream fileInputStream = new FileInputStream("src/db.properties"); //java项目写法！
            Properties properties = new Properties();
            properties.load(fileInputStream);*/

            //找到资源
            String path = Database.class.getResource("/").toURI().getPath() + "db.properties";
            //创建流对象
            FileInputStream fin = new FileInputStream(path) ;
            //创建Properties对象
            Properties properties = new Properties() ;
            //将properties与流对象进行关联。
            properties.load(fin);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String uname = properties.getProperty("uname");
            String password = properties.getProperty("password");
            Class.forName(driver);//加载驱动！
            con = DriverManager.getConnection(url, uname, password);
        } catch (SQLException | ClassNotFoundException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String sql, Object[] obj) {
        try {
            ps = con.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0 ; i < obj.length; i++) {
                    ps.setObject((i+1),obj[i]);
                }
            }
           rs =  ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql,Object obj[]) {
        int num = 0;
        try {
            ps = con.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0 ; i < obj.length; i++) {
                    ps.setObject((i+1),obj[i]);
                }
            }
           num =  ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return num;
    }
    public void closeAll() {
        //释放资源
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
