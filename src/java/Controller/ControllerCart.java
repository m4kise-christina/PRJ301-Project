/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAOCustomers;
import Model.DAOOrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *
 * @author Sy
 */
public class ControllerCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            HttpSession session = request.getSession();
            // Get cart K-V == ProductID - Quantity
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            // TEST CODE. REMOVE WHEN DONE
            // -------------
            if (service == null) {
                service = "displayAll";
            }

            switch (service) {
                case "displayAll":
//                    request.setAttribute("activePage", "cart");
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    break;
                case "add":
                    int productID = Integer.valueOf(request.getParameter("id"));
                    // specific handle for quantity from product_details
                    String quant = request.getParameter("quantity");
                    int addNumber;
                    if(quant == null) { // add service sent from category
                        addNumber = 1;
                    } else {
                        addNumber = Integer.valueOf(quant);
                    }
                    
                    int quantity = 0;
                    // Get quantity of product if it already in cart
                    if (cart.get(productID) != null) {
                        quantity = cart.get(productID);
                    }
                    cart.put(productID, quantity + addNumber);//increment value of key by 1(quantity).
                    response.sendRedirect("ControllerShop?service=displayAll");
                    break;
                case "remove":
                    int pID = Integer.valueOf(request.getParameter("id"));
                    cart.remove(pID);
                    response.sendRedirect("ControllerCart?service=displayAll");
                    break;
                case "removeAll":
                    cart.clear();
                    response.sendRedirect("ControllerCart?service=displayAll");
                case "update": // update the cart (quantity)
                    int pId = Integer.valueOf(request.getParameter("pId"));
                    int quan = Integer.valueOf((request.getParameter("quantity")));
                    System.out.println(pId);
                    System.out.println(quan);
                    cart.put(pId, quan);
                    response.sendRedirect("cart?service=displayAll");
                    break;

                case "checkout":
                    DAOCustomers daoC=new DAOCustomers();
                    DAOOrderDetail daoOD=new DAOOrderDetail();
                    // Check user login
                    String userName = (String)session.getAttribute("username");
                    if (userName == null) { // If user not yet logged it
                        response.sendRedirect("LoginServlet");
                        return;
                    }
//                     Get user object that are currently logged in
                    int cid = daoC.getIDbyUsername((String) session.getAttribute("username"));
//                     User do the checkout
                    daoOD.checkout(cart, cid);
                    response.sendRedirect("ControllerCart?service=displayAll");
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
