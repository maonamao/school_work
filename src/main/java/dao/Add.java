package dao;
import entity.Student;
import java.sql.*;
import java.util.*;

public class Add {
    public static String url = "jdbc:mysql://192.168.56.132:3306/docker_work4?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    public static String user = "root";
    public static String password = "mysql_docker";
    public static void addStudent(Student student) throws ClassNotFoundException {
        String sql = "insert into student(name, age, gender) values(?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getGender());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
