import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Search extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //lay thong tin tu yeu cau tim kiem
        String id = request.getParameter("id");
        String name =request.getParameter("name");
        String dob = request.getParameter("dob");
        String department = request.getParameter("department");
        
        //luu thong tin tim kiem vao session
//        HttpSession session = request.getSession();
//        session.setAttribute("id", id);
//        session.setAttribute("name", name);
//        session.setAttribute("dob", dob);
//        session.setAttribute("department", department);
        
        //ket noi den csdl
        String jdbcUrl = "jdbc:mysql://localhost:3306/hocsinh";
        String username = "root";
        String password = "heichi1891";
        try{
            Class.forName("com.mysql.jdbc.Drive");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            
            //xay dung cau lenh truy van SQL
            String sql = "SELECT * FROM student WHERE id LIKE ? AND name LIKE ? AND dob LIKE ? AND department LIKE ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + id + "%");
            statement.setString(2, "%" + name + "%");
            statement.setString(3, "%" + dob + "%");
            statement.setString(4, "%" + department + "%");
            
            //Thuc thi cau truy van
            ResultSet resultSet = statement.executeQuery();
            
            //Tao trang html hien thi ket qua
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Kết quả tìm kiếm</title></head><body>");
            out.println("<h1>Kết quả tìm kiếm</h1>");
            out.println("<table><tr><th>ID</th><th>Tên</th><th>Ngày sinh</th><th>Khoa</th><th>Thao tác</th></tr>");
            
            //hien thi ket qua
            while (resultSet.next()) {
                String studentId = resultSet.getString("id");
                String studentName = resultSet.getString("name");
                String studentDob = resultSet.getString("dob");
                String studentDepartment = resultSet.getString("department");
                int selected = resultSet.getInt("selected");

                out.println("<tr>");
                out.println("<td>" + studentId + "</td>");
                out.println("<td>" + studentName + "</td>");
                out.println("<td>" + studentDob + "</td>");
                out.println("<td>" + studentDepartment + "</td>");
                if (selected == 0) {
                    out.println("<td><a href=\"select?id=" + studentId + "\">Select</a></td>");
                } 
                else{
                    out.println("<td>Selected</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
            
            //dong ket noi va giai phong tai nguyen
            statement.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
}