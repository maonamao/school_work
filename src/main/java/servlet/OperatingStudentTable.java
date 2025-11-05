package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/OperatingStudentTable")
public class OperatingStudentTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        switch (operation) {
            case "Select" : {
                json = objectMapper.writeValueAsString(dao.Select.selectStudent());
                resp.getWriter().write(json);
                break;
            }
            case "Delete" : {
                dao.Delete.deleteStudent(req.getParameter(req.getParameter("name")));
                resp.getWriter().write("删除成功");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        String json =
    }
}
