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
            String table_data = "table_data = [";
            while(rs.next())
            {
                table_data+="{id: '"+rs.getInt(1)+"' ,name: '"+rs.getString(2)+"' ,description: '"+
                        rs.getString(3)+"' ,tag: '"+rs.getString(4)+"' ,result: '"+rs.getString(5)+
                        "' ,expect: '"+rs.getString(6)+"', time: '"+rs.getDate(7)+"'},";

            }
            String new_table = table_data.substring(0,table_data.length()-1)+']';
            String docType = "<!doctype html public \\\\\\\"-//w3c//dtd html 4.0 \"+\n" +
                    "\"transitional//en\\\"><html><head><title>Test Case Database</title><style>\n" +
                    "#tab { height:400px;overflow-y: scroll;}"+
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
                    "}</style></head><script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js\"></script>";
            out.println(docType);
            out.println("<body bgcolor=\"#f0f8ff\">" +
                    "<div ng-app=\"\" ng-init=\""+new_table+"\">"+
                    "<h2 style=\"text-align:center\">TEST CASE DATABASE</h2>"+
            "<div width=\"20em\" style=\"float:left\">" +
                    "<b><center>SEARCH</center></b><form align=\"center\">" +
                    "<input style=\"width:20em; height:4ex\" type=\"text\" name=\"search\" id=\"search\" ng-model=\"searchText\"></form></div>"+
                    "<div width=\"70%\" style=\"float:none test-align:center;\">" +
            "<form action=\"DeleteServlet\" method=\"get\" align=\"center\"><div id=\"tab\"><table class=\"tableSection\" border=\"1px\" align=\"center\" cellpadding=\"10\">");
            out.println(docType + "<thead><tr><td></td><td>Test ID</td><td>Test Name</td><td>Test Description</td><td>Tags</td><td>Test Result</td><td>Expected Result</td><td>Date</td></tr></thead>");

            out.println("<tbody><tr ng-repeat=\"row in table_data | filter:searchText\">");
            out.println("<td><input type=\"radio\" value=\"{{row.id}}\" name=\"del\"></td>");
            out.println("<td>{{row.id}}</td>");
            out.println("<td>{{row.name}}</td>");
            out.println("<td>{{row.description}}</td>");
            out.println("<td>{{row.tag}}</td>");
            out.println("<td>{{row.result}}</td>");
            out.println("<td>{{row.expect}}</td>");
            out.println("<td>{{row.time}}</td>");
            out.println("</tr></tbody>");
            out.println("</table></div><br><br><input align=\"center\" style=\"width:10em; height:4em;\" type=\"submit\" value=\"DELETE\"></form>" + "<h3 style=\"text-align:center\">");
            //out.println("<br>");

            out.println("<form name=\"insert\" action=\"form.html\"\">" +
                    "<input style=\"width:10em; height:4em;\" type=\"submit\" value=\"INSERT\"></form>");
            out.println("<div class=\"divider\">");

            out.println("</div></div></div>");

            rs.close();
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
