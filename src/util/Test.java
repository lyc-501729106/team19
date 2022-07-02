package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author 吕译辰
 * @Date 2022/6/27 - 11:01
 */
public class Test {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("db.properties");
            System.out.println(fileInputStream.toString());
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String uname = properties.getProperty("uname");
            String password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
