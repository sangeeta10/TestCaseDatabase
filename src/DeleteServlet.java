import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sangeshi on 2/6/2015.
 */
@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    ResultSet resultset = null;
    Connection con = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
            java.sql.Statement statement = con.createStatement();
            String id= request.getParameter("del");
            String st = "delete from testtable where testID="+id+";";
            int i=statement.executeUpdate(st);

            response.sendRedirect("/display.html");

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

}
