<%-- 
    Document   : updateProduct
    Created on : Jul 11, 2023, 11:15:18 AM
    Author     : Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.DAOProduct, Entity.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Product pro=(Product)request.getAttribute("pro");
        %>
        <form action="ControllerProduct" method="post">
            <table>
                <tr>
                <tr>
                    <th>Product ID</th><td><input type="text" name="pid" 
                                                  value="<%=pro.getProductID()%>"></td>
                </tr><tr>   <th>Product Name</th><td><input type="text" name="pname" 
                                                                value="<%=pro.getProductName()%>"></td>
                </tr><tr>     <th>Price</th><td><input type="text" name="price" 
                                                                value="<%=pro.getPrice()%>"></td>
                </tr><tr>     <th>Model Year</th><td><input type="number" name="modelyear"
                                                                value="<%=pro.getModelYear()%>"></td>
                </tr><tr>     <th>Brand Name</th><td><input type="text" name="brand" 
                                                                value="<%=pro.getBrandName()%>"></td>
                </tr><tr>    <th>Category</th><td><input type="text" name="category" 
                                                                  value="<%=pro.getCategory()%>"></td>
                </tr><tr>   <th><input type="submit" value="update" name="submit"></th>
                    <th><input type="reset" value="reset">
                        <input type="hidden" name="service" value="updateProduct">

                    </th>
                </tr>
                </tr>
            </table>
        </form>
    </body>
</html>
