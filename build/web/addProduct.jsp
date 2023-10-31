<%-- 
    Document   : addProduct
    Created on : Jul 11, 2023, 10:49:48 AM
    Author     : Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ControllerProduct" method="post">
		<table>
			<tr>
				<th>Product Name</th>
				<td><input type="text" name="pname"></td>
			</tr>
                        <tr>
				<th>Price</th>
				<td><input type="text" name="price" id=""></td>
			</tr>
			<tr>
				<th>Model Year</th>
				<td><input type="text" name="modelyear" id=""></td>
			</tr>
			
			<tr>
				<th>brand Name</th>
				<td><input type="text" name="brand" id=""></td>
			</tr>
			<tr>
				<th>category Name</th>
				<td><input type="text" name="category" id=""></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add Product" name="submit"></td>
				<td><input type="reset" value="Clear"></td>
			</tr>
			</tr>
		</table>
           <input type="hidden" name="service" value="addProduct">
	</form>
    </body>
</html>
