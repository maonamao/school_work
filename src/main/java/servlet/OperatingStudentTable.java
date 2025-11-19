package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import static config.Cors.setCorsHeaders;

@WebServlet("/OperatingStudentTable")
public class OperatingStudentTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        ObjectMapper objectMapper = new ObjectMapper();
        setCorsHeaders(resp);
        String json;
        switch (operation) {
            case "Select" : {
                try {
                    json = objectMapper.writeValueAsString(dao.Select.selectStudent());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                resp.getWriter().write(json);
                break;
            }
            case "Delete" : {
                try {
                    dao.Delete.deleteStudent(req.getParameter("name"));
                    resp.getWriter().write("删除成功");
                } catch (ClassNotFoundException e) {
                    resp.getWriter().write("删除失败");
                    throw new RuntimeException(e);

                }

                break;
            }
        }
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 对OPTIONS预检请求设置CORS头
        setCorsHeaders(resp);
        // 必须返回200状态码，否则预检失败
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        ObjectMapper objectMapper = new ObjectMapper();
        setCorsHeaders(resp);
        try{
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            Student student = objectMapper.readValue(sb.toString(), Student.class);
            switch ( operation){
                case "Add" : {
                    dao.Add.addStudent(student);
                    resp.getWriter().write("添加成功");
                    break;
                }
                case "Update" : {
                    dao.Update.updateStudent(student);
                    resp.getWriter().write("更新成功");
                }
            }


        }
        catch (Exception e){
            resp.getWriter().write("操作失败");
            e.printStackTrace();
        }

    }
}
