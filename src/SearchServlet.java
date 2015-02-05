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
 * Created by sangeshi on 2/4/2015.
 */
@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    Connection con = null;
    java.sql.Statement statement=null;
    ResultSet rs=null;

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
    protected String searchByID(String id) throws ClassNotFoundException {
        try {
            connection("root","admin");
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM testtable where testID="+id+";");
            String res="";
            while(rs.next())
            {
                res+=rs.getInt(1)+"#"+rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#"+
                        rs.getString(5)+"#"+rs.getString(6)+"#"+rs.getDate(7)+"&";
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected String searchByTags(String tag)throws ClassNotFoundException {
        try {
            connection("root","admin");
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM testtable where tags='"+tag+"';");
            String res="";
            while(rs.next())
            {
                res+=rs.getInt(1)+"#"+rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#"+
                        rs.getString(5)+"#"+rs.getString(6)+"#"+rs.getDate(7)+"&";
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected String searchByResult(String result)throws ClassNotFoundException {
        try {
            connection("root","admin");
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM testtable where expectedResult='"+result+"';");
            String res="";
            while(rs.next())
            {
                res+=rs.getInt(1)+"#"+rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#"+
                        rs.getString(5)+"#"+rs.getString(6)+"#"+rs.getDate(7)+"&";
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected String searchByDate(String date)throws ClassNotFoundException {
        try {
            connection("root","admin");
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM testtable where testTimestamp LIKE '"+date+"%';");
            String res="";
            while(rs.next())
            {
                res+=rs.getInt(1)+"#"+rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#"+
                        rs.getString(5)+"#"+rs.getString(6)+"#"+rs.getDate(7)+"&";
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ch = request.getParameter("name");
        //out.print(ch);
        String s = "";
        if (ch.equals("testID")) {
            try {
                s = searchByID(request.getParameter("select"));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (ch.equals("tags")) {
            try {
                s = searchByTags(request.getParameter("select"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (ch.equals("exRes")) {
            try {
                s = searchByResult(request.getParameter("select"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(ch.equals("time")){
            try {
                s = searchByDate(request.getParameter("select"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        String[] rarr = s.split("&");
        out.print("<html><body bgcolor=\"#f0f8ff\">");
        out.print("<h2 align=\"center\">SEARCH RESULT</h2>");
        out.print("<table cellpadding=\"1px\" border=\"1px solid\" align=\"center\">");
        out.print("<tr>");
        out.print("<td>TEST ID</td><td>NAME</td><td>DESCRIPTION</td><td>TAGS</td><td>OUTPUT</td>");
        out.print("<td>EXPECTED OUTPUT</td><td>DATE</td></tr>");

        for (int j = 0; j < rarr.length; j++) {
            out.print("<tr>");

            String[] res = rarr[j].split("#");
            Integer i = 0;


            while (i < res.length) {
                out.print("<td>" + res[i] + "</td>");
                i++;
            }
            out.print("</tr>");

        }
        out.print("</table><br><br>");
        out.print("<form align=\"center\" action=\"search.html\"><input style=\"width: 10em; height: 2em;\" type=\"submit\" value=\"MAIN PAGE\"></form>");
        out.print("</body></html>");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
