<%-- 
    Document   : category
    Created on : Jul 4, 2023, 10:53:06 AM
    Author     : Sy
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            Vector<Product> cVector=(Vector)request.getAttribute("cVector");
        %>

        <table class="styled-table" border="3">
            <caption>Product List</caption>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Model Year</th>
                    <th>Brand Name</th>
                    <th>Category</th>
                    <th>Update</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%for(Product pro : cVector){%>
                <tr>
                    <td><%=pro.getProductID()%></td>
                    <td><%=pro.getProductName()%></td>
                    <td><%=pro.getPrice()%></td>
                    <td><%=pro.getModelYear()%></td>
                    <td><%=pro.getBrandName()%></td>
                    <td><%=pro.getCategory()%></td>
                    <td>
                        <a href="ControllerProduct?service=updateProduct&id=<%=pro.getProductID()%>">Update</a>
                    </td>
                    <td>
                        <a href="ControllerProduct?service=delete&id=<%=pro.getProductID()%>">Remove</a>
                    </td>
                    
                </tr>
                <%}%>
                <%String message=(String)request.getAttribute("message");
                    if(message!=null){
                    out.println(message);   
                        }
                    %>
            </tbody>
        </table>
    </body>
</html>