<%-- 
    Document   : register
    Created on : Jun 28, 2023, 8:50:00 AM
    Author     : Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="loginstyle.css">
    </head>
    <body class="bgc">
        <form action="ControllerCustomers" method="post">
            <div id="login-form-wrap">
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>
                <label for="name"><b>Name</b></label>
                <input type="text" placeholder="Enter Name" name="name" id="name" required>

                <label for="phone"><b>Phone</b></label>
                <input type="text" placeholder="Enter Name" name="phone" maxlength="10" pattern="[0-9]{10}" id="phone" required>


                <label for="email"><b>Email</b></label>
                <input type="email" placeholder="Enter Email" name="email" id="email" required>

                <label for="address"><b>Address</b></label>
                <input type="text" placeholder="Enter Address" name="address" id="address" required>

                <label for="zip_code"><b>Zip code</b></label>
                <input type="text" placeholder="Enter Zip code" name="zip_code" maxlength="5" pattern="[0-9]{5}" id="zip_code" required>

                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" id="username" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="password" required>

                <!--    <label for="psw-repeat"><b>Repeat Password</b></label>
                    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>-->
                <hr>

                <input type="submit" value="Register" name="submit" class="registerbtn">
                <input type="reset" value="CLEAR">
                <input type="hidden" name="service" value="add">
            </div>

            <div class="signin">
                <p>Already have an account? <a href="loginview.jsp">Sign in</a></p>
            </div>
        </form>
    </body>
</html>
