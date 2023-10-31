<%-- 
    Document   : shop
    Created on : Jul 1, 2023, 3:18:31 PM
    Author     : Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.DAOProduct"%>
<%@page import="java.util.Vector"%>
<%@page import="Entity.Product"%>
<%@page import="Model.DAOInventory"%>
<%@page import="Model.DAODiscount"%>
<!DOCTYPE html>
<!--
        ustora by freshdesignweb.com
        Twitter: https://twitter.com/freshdesignweb
        URL: https://www.freshdesignweb.com/ustora/
-->
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Shop Page- Ustora Demo</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/responsive.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <div class="header-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="user-menu">
                            <ul>
                                <li><h5 style="font-weight: bold">HE176623_Truong Tien Sy</h5></li>
                                <li><a href="#"><i class="fa fa-user"></i> My Account</a></li>
                                    <%
                                            if(session.getAttribute("username") == null) {
                                    %>
                                <li><a href="loginview.jsp"><i class="fa fa-user"></i> Login</a></li>
                                    <%}
                                        else {
                                    %>
                                <li>Welcome <%=session.getAttribute("username")%>!</li>
                                <li><form method="link" action="logout.jsp">
                                        <input type="submit" value="Logout"/>
                                    </form></li>            
                                    <%}%>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div> <!-- End header area -->

        <div class="site-branding-area">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="logo">
                            <h1><a href="./"><img src="img/logo.png"></a></h1>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="shopping-item">
                            <a href="ControllerCart?service=displayAll">Cart - <span class="cart-amunt">$0</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">0</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End site branding area -->

        <div class="mainmenu-area">
            <div class="container">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div> 
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="index.jsp">Home</a></li>
                            <li class="active"><a href="ControllerShop?service=displayAll">Shop page</a></li>
                            <li><a href="single-product.jsp">Single product</a></li>
                            <li><a href="ControllerCart?service=displayAll">Cart</a></li>
                            <li><a href="ControllerProduct?service=displayAll">Category</a></li>
                        </ul>
                    </div>  
                </div>
            </div>
        </div> <!-- End mainmenu area -->

        <div class="product-big-title-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-bit-title text-center">
                            <h2>Shop</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <%
                Vector<Product> allProduct=(Vector)request.getAttribute("allProduct");
                %>


                <div class="row">
                    <%
                        DAODiscount daoD=new DAODiscount();
                        DAOInventory daoI=new DAOInventory();
                        for(Product pro : allProduct){
                        double initial_price=pro.getPrice();
                        double discount=(double)daoD.getDiscount(pro.getProductID())/100;
                        double afterDiscount=initial_price*(1-discount);
                        double displayPrice=(int)(afterDiscount*100)/100;
                        int initial_quantity=daoI.getQuantityByID(pro.getProductID());
                    %>

                    <div class="col-md-3 col-sm-6">
                        <div class="single-shop-product">
                            <div class="product-upper">
                                <img src="img/<%=pro.getProductID()%>.png" alt="error">
                            </div>
                            <h2><a href=""><%=pro.getProductName()%></a></h2>
                            <div style="display: flex;" class="product-carousel-price">
                                <ins>$<%=displayPrice%></ins> <del>$<%=pro.getPrice()%></del>
                                <p style="font-size: smaller; font-style: italic">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Inventory: <%=initial_quantity%></p>
                            </div>  

                            <div class="product-option-shop">
                                <%if(initial_quantity!=0){%>
                                <a class="add_to_cart_button" rel="nofollow" href="ControllerCart?service=add&id=<%=pro.getProductID()%>">Add to cart</a>
                                <%}else{%>
                                <a style="background-color: red" class="add_to_cart_button" rel="nofollow" href="#"><del>Add to cart</del></a>
                                <%}%>
                                <a class="add_to_cart_button" rel="nofollow" href="SingleProduct?service=productDetail&id=<%=pro.getProductID()%>">See details</a>
                            </div>                       
                        </div>
                    </div>
                    <%}%>
                    <!--                                    <div class="col-md-3 col-sm-6">
                                                            <div class="single-shop-product">
                                                                <div class="product-upper">
                                                                    <img src="img/2.png" alt="">
                                                                </div>
                                                                <h2><a href="">Apple new mac book 2015 March :P</a></h2>
                                                                <div class="product-carousel-price">
                                                                    <ins>$899.00</ins> <del>$999.00</del>
                                                                </div>  
                                                                
                                                                <div class="product-option-shop">
                                                                    <a class="add_to_cart_button" data-quantity="1" data-product_sku="" data-product_id="70" rel="nofollow" href="/canvas/shop/?add-to-cart=70">Add to cart</a>
                                                                </div>                       
                                                            </div>
                                                        </div>-->

                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="product-pagination text-center">
                            <nav>
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>                        
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="footer-top-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="footer-about-us">
                            <h2>u<span>Stora</span></h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis sunt id doloribus vero quam laborum quas alias dolores blanditiis iusto consequatur, modi aliquid eveniet eligendi iure eaque ipsam iste, pariatur omnis sint! Suscipit, debitis, quisquam. Laborum commodi veritatis magni at?</p>
                            <div class="footer-social">
                                <a href="#" target="_blank"><i class="fa fa-facebook"></i></a>
                                <a href="#" target="_blank"><i class="fa fa-twitter"></i></a>
                                <a href="#" target="_blank"><i class="fa fa-youtube"></i></a>
                                <a href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-menu">
                            <h2 class="footer-wid-title">User Navigation </h2>
                            <ul>
                                <li><a href="">My account</a></li>
                                <li><a href="">Order history</a></li>
                                <li><a href="">Wishlist</a></li>
                                <li><a href="">Vendor contact</a></li>
                                <li><a href="">Front page</a></li>
                            </ul>                        
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-menu">
                            <h2 class="footer-wid-title">Categories</h2>
                            <ul>
                                <li><a href="">Mobile Phone</a></li>
                                <li><a href="">Home accesseries</a></li>
                                <li><a href="">LED TV</a></li>
                                <li><a href="">Computer</a></li>
                                <li><a href="">Gadets</a></li>
                            </ul>                        
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-newsletter">
                            <h2 class="footer-wid-title">Newsletter</h2>
                            <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your inbox!</p>
                            <div class="newsletter-form">
                                <input type="email" placeholder="Type your email">
                                <input type="submit" value="Subscribe">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-bottom-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="copyright">
                            <p>&copy; 2015 uCommerce. All Rights Reserved. <a href="http://www.freshdesignweb.com" target="_blank">freshDesignweb.com</a></p>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="footer-card-icon">
                            <i class="fa fa-cc-discover"></i>
                            <i class="fa fa-cc-mastercard"></i>
                            <i class="fa fa-cc-paypal"></i>
                            <i class="fa fa-cc-visa"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Latest jQuery form server -->
        <script src="https://code.jquery.com/jquery.min.js"></script>

        <!-- Bootstrap JS form CDN -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <!-- jQuery sticky menu -->
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>

        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.min.js"></script>

        <!-- Main Script -->
        <script src="js/main.js"></script>
    </body>
</html>