
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Select extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // Lấy thông tin sinh viên được chọn
        String studentId = request.getParameter("id");

        // Cập nhật trạng thái đã chọn của sinh viên trong cơ sở dữ liệu
        String jdbcUrl = "jdbc:mysql://localhost:3306/hocsinh";
        String username = "root";
        String password = "heichi1891";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            String sql = "UPDATE Student SET selected = 1 WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.executeUpdate();

            // Đóng kết nối và giải phóng tài nguyên
            statement.close();
            conn.close();

            // Quay trở lại trang giao diện ban đầu
            response.sendRedirect("index.html");
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } 
    }
}
