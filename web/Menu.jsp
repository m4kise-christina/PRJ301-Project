<%-- 
    Document   : category
    Created on : Jul 4, 2023, 10:53:06 AM
    Author     : Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Model.DAOProduct"%>
<%@page import="java.util.Vector"%>
<%@page import="Entity.Product"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="tablecss.css"/>
    </head>
    <body>

        <%
            ResultSet cRs=(ResultSet)request.getAttribute("cRs");
            Vector<Product> cVector=(Vector)request.getAttribute("cVector");
        %>
        <ul>
            <li><a href="ControllerProduct?service=displayAll">Home</a></li>
                <%
                    while(cRs.next()){
                %>
            <li class="tablelist">
                <a href="ControllerProduct?service=displayProduct&id=<%=cRs.getString(1).trim()%>"><%=cRs.getString(1).trim()%></a>
            </li>
            <%}%>
        </ul>
        <table class="styled-table" border="1">
            <caption>Product List</caption>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Model Year</th>
                    <th>Brand Name</th>
                    <th>Category Name</th>

                </tr>
            </thead>
            <tbody>
                <%for(Product pro : cVector){%>
                <tr>
                    <td><%=pro.getProductID()%></td>
                    <td><%=pro.getProductName()%></td>
                    <td><%=pro.getModelYear()%></td>
                    <td><%=pro.getPrice()%></td>
                    <td><%=pro.getBrandName()%></td>
                    <td><%=pro.getCategory()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
