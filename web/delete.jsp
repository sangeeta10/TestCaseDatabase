<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.beans.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    </head>
<body bgcolor="#f0f8ff">
<div align="center">

</div>
<% ResultSet resultset = null;
    Connection con = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testCases", "root", "admin");
        java.sql.Statement statement = con.createStatement();
        resultset = statement.executeQuery("select * from testtable");
%>
<div align="center">
<table align="center" border="2px; solid" cellpadding="2px">
    <tr>
        <td>TEST ID</td>
        <td>NAME</td>
        <td>DESCRIPTION</td>
        <td>TAGS</td>
        <td>OUTPUT</td>
        <td>EXPECTED OUTPUT</td>
        <td>TIME STAMP</td>
    </tr>
    <%while(resultset.next()){%>
    <tr><td><%=resultset.getInt(1)%></td>
        <td><%=resultset.getString(2)%></td>
        <td><%=resultset.getString(3)%></td>
        <td><%=resultset.getString(4)%></td>
        <td><%=resultset.getString(5)%></td>
        <td><%=resultset.getString(6)%></td>
        <td><%=resultset.getDate(7)%></td>

    </tr>
    <%} resultset = statement.executeQuery("select * from testtable");%>

</table>
<h2>SELECT THE ID TO DELETE</h2>
    <form name="del">
<select name = "id">
    <%! String n=null; %>
    <% while (resultset.next()) {%>
    <option value="<%Integer op =resultset.getInt(1); out.print(op);%>"><%=resultset.getInt(1)%></option>
    <% }
    %>
</select>
        <input type="submit" name="ok" value="DELETE"/>
        <% String id= request.getParameter("id");
            String st = "delete from testtable where testID="+id+";";
            int i=statement.executeUpdate(st);
            if(i!=0)
            {
                out.println("row deleted successfully");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            con.close();
        }%>

</form>
    <br>
    <form name = "back" action = "index.html">
        <input type="submit" value="BACK TO MAIN">
    </form>
</div>
</body>
</html>