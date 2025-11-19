package dao;

import entity.Student;

import java.sql.*;
import java.util.*;

public class Select {

    public static String url = "jdbc:mysql://192.168.56.132:3306/docker_work4?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    public static String user = "root";
    public static String password = "mysql_docker";
    public static List<Student> selectStudent() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = "select * from student";
        List<Student> students = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}

