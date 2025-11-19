package dao;
import entity.Student;
import java.sql.*;
import java.util.*;
public class Update {
    public static String url = "jdbc:mysql://192.168.56.132:3306/docker_work4?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    public static String user = "root";
    public static String password = "mysql_docker";
    public static void updateStudent(Student student) throws ClassNotFoundException {
        String sql = "update student set age = ? where name = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getAge());
            ps.setString(2, student.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
