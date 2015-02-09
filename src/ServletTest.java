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
 * Created by sangeshi on 2/2/2015.
 */
@WebServlet(name = "ServletTest")
public class ServletTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //  Database credentials
        String USER = "root";
        String PASS = "admin";
        java.sql.Statement stmt;
        Connection con;
        ResultSet rs;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases","root","admin");
            stmt = con.createStatement();
            String query = "insert into testTable values ('"+request.getParameter("testID")+"','"+request.getParameter("testName")+"','"+request.getParameter("testDescription")+
                    "','"+request.getParameter("tags")+"','"+request.getParameter("testResult")+"','"+request.getParameter("expectedResult")+
                    "','"+request.getParameter("testTimestamp")+"');";
            int i = stmt.executeUpdate(query);
            String s = "";
            if (i != 0) {
                s="RECORD INSERTED SUCCESSFULLY!!";
            }
            else {
                s = "RECORD INSERTION FAILED!!";
            }
            out.println("<html><head><script>function dis(){ alert(\""+ s +"\"); window.location.assign(\"http://localhost:8578/display.html\"); }"+
            "</script></head><body onload=\"dis()\"></body></html>");
            //response.sendRedirect("/display.html");
            /*out.println("<html><head><title>Insert</title></head><body bgcolor=\"#f0f8ff\"><h2 align=\"center\">"+s+"</h2><div align=\"center\">"+
                    "<form name=\"more\" action=\"form.html\"><input style=\"width: 20em; size: 4em;\" type =\"submit\" value=\"INSERT MORE RECORDS\"></form>"
                    +"<form name=\"display\" action=\"display.html\"><input style=\"width: 20em; size: 4em;\" type=\"submit\" value=\"BACK\"></form></div>"+"</body></html>");*/

            /*rs = stmt.executeQuery("SELECT * FROM testtable");
            String docType = "<!doctype html public \\\\\\\"-//w3c//dtd html 4.0 \"+\n" +
                    "\"transitional//en\\\"><html><head><title>Page2</title></head><body>"+
                    "<h3 style=\"text-align:center\">"+s+"</h3><table border=\"1px\" align=\"center\" cellpadding=\"10\">";
            out.println(docType + "<tr><td>Test ID</td><td>Test Name</td><td>Test Description</td><td>Tags</td><td>Test Result</td><td>tExpected Result</td><td>Time Stamp</td></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt(1) + "</td><td> " + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>"
                + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td><td>" + rs.getDate(7)+ "</td>");
                out.println("</tr>");
            }
            out.println("</table>"+"<h3 style=\"text-align:center\"><form name=\"yesNo\" action=\"form.html\">"+
                    "<input type=\"submit\" value=\"Yes\"></form>"+"</body></html>");
            rs.close();*/
            stmt.close();


        } catch (ClassNotFoundException e) {
            out.println("<html><head><script>function dis(){ alert(\"RECORD INSERTION FAILED!!!\"); window.location.assign(\"http://localhost:8578/display.html\"); }" +
                    "</script></head><body onload=\"dis()\"></body></html>");
            response.sendRedirect("/display.html");
        } catch (SQLException e) {
            out.println("<html><head><script>function dis(){ alert(\"RECORD INSERTION FAILED!!!\"); window.location.assign(\"http://localhost:8578/display.html\"); }"+
                    "</script></head><body onload=\"dis()\"></body></html>");
            /*String s="RECORD INSERTION FAILED!!";
            out.println("<html><head><title>Insert</title></head><body bgcolor=\"#f0f8ff\"><h2 align=\"center\">"+s+"</h2>"+
                    "<div align=\"center\"><form name=\"more\" action=\"form.html\"><input style=\"width: 20em; size: 4em;\" type =\"submit\" value=\"INSERT MORE RECORDS\"></form>"
                    +"<form name=\"display\" action=\"display.html\"><input style=\"width: 20em; size: 4em;\" type=\"submit\" value=\"BACK\"></form>"+"</div></body></html>");*/
        }


        /*String docType = "<!doctype html public \\\"-//w3c//dtd html 4.0 "+
                "transitional//en\">\n";
        out.println(docType + "<html>\n<head><title>Page2</title></head><body>"+
        "<h2>Data Entered Successfully</h2><br><ul>"+
        "<li>Test Name:"+request.getParameter("testName")+"</li><li>Test Description:"+request.getParameter("testDescription")+
                "</li><li>Test Result:"+request.getParameter("testResult")+"</li></ul></body></html>");*/
    }
}
