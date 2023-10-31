<%-- 
    Document   : loginview.jsp
    Created on : Jun 27, 2023, 10:51:12 AM
    Author     : Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>HTML5 Login Form with validation Example</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="loginstyle.css">

</head>
<body>
    <!-- partial:index.partial.html -->
    <div id="login-form-wrap">
        <h2>Login</h2>
        <form id="login-form" method="post" action="LoginServlet">
            <p>
                <input type="text" id="username" name="username" placeholder="Username" required><i class="validation"><span></span><span></span></i>
            </p>
            <p>
                <input type="password" id="email" name="password" placeholder="Enter password" required><i class="validation"><span></span><span></span></i>
            </p>
            <p>
                <input type="submit" value="Login">
                <input type="hidden" name="submit" value="login">           
            </p>

        </form>
        <%
//            boolean fail=(boolean)request.getAttribute("fail");
//            if(fail){
//                out.println("Username or Password is incorrect");
//            }
        %>
        <div id="create-account-wrap">
            <p>Not a member? <a href="register.jsp">Register a new Account</a><p>
        </div><!--create-account-wrap-->
    </div><!--login-form-wrap-->
    <!-- partial -->

</body>
</html>
