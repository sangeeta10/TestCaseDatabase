import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sangeshi on 2/3/2015.
 */
@WebServlet(name = "ServletDisplay")
public class ServletDisplay extends HttpServlet {
    Connection con = null;
    java.sql.Statement stmt;
    ResultSet rs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public Boolean connection(String username, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", username, password);
             } catch (ClassNotFoundException e1) {

                    e1.printStackTrace();
                    return false;
                }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        if(con!=null)
        {
            return true;
        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            connection("root","admin");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM testtable");
            String docType = "<!doctype html public \\\\\\\"-//w3c//dtd html 4.0 \"+\n" +
                    "\"transitional//en\\\"><html><head><title>Page2</title><style>\n" +
                    "table, th , td  {\n" +
                    "  border: 1px solid grey;\n" +
                    "  border-collapse: collapse;\n" +
                    "  padding: 5px;\n" +
                    "}\n" +
                    "table tr:nth-child(odd)\t{\n" +
                    "  background-color: #f1f1f1;\n" +
                    "}\n" +
                    "table tr:nth-child(even) {\n" +
                    "  background-color: #ffffff;\n" +
                    "}\n" +
                    ".divider{\n" +
                    "    width:20em;\n" +
                    "    height:auto;\n" +
                    "    display:inline-block;\n" +
                    "}</style>"+
                    "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n" +
                    "<script>jQuery(function(){\n" +
                    "$(\"#search\").autocomplete(\"getlist.jsp\");\n" +
                    "});" +
                    " </script>"+
                    "</head><body bgcolor=\"#f0f8ff\">" +
                    "<h2 style=\"text-align:center\">TEST CASE DATABASE</h2>"+
            "<div width=\"30%\" style=\"float:left\"><form align=\"center\">" +
                    "<input style=\"width:20em; height:4em;\" type=\"text\" name=\"search\" id=\"search\"></form></div>"+
                    "<div width=\"70%\" style=\"float:none\" test-align:center;\">"+
            "<form action=\"DeleteServlet\" method=\"get\" align=\"center\"><table border=\"1px\" align=\"center\" cellpadding=\"10\">";
            out.println(docType + "<tr><td></td><td>Test ID</td><td>Test Name</td><td>Test Description</td><td>Tags</td><td>Test Result</td><td>tExpected Result</td><td>Date</td></tr>");

            while (rs.next()) {
                out.println("<tr><td><input type=\"radio\" name=\"del\" value=\""+ rs.getInt(1)+"\"></td>");
                out.println("<td>" + rs.getInt(1) + "</td><td> " + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>"
                        + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td><td>" + rs.getDate(7) + "</td>");
                out.println("</tr>");
            }
            out.println("</table><br><br><input align=\"center\" style=\"width:10em; height:4em;\" type=\"submit\" value=\"DELETE\"></form>" + "<h3 style=\"text-align:center\">");
            //out.println("<br>");

            out.println("<form name=\"insert\" action=\"form.html\"\">" +
                    "<input style=\"width:10em; height:4em;\" type=\"submit\" value=\"INSERT\"></form>");
            out.println("<div class=\"divider\">");

            out.println("</div></div>");

            rs.close();
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
