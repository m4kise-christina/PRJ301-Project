/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAOAdmin;
import Model.DAOCustomers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Sy
 */
public class LoginServlet extends HttpServlet {

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
            HttpSession session = request.getSession();

            if (session.getAttribute("username") != null) { //neu da co session cua username
                response.sendRedirect("index.jsp");
            } else {
                if (request.getParameter("submit") == null) {//
                    request.setAttribute("fail", false);
                    request.getRequestDispatcher("loginview.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");//get parameter(name) from login.jsp
                    String password = request.getParameter("password");
                    DAOCustomers dao = new DAOCustomers();
                    DAOAdmin daoAdmin = new DAOAdmin();
                    boolean checkLog = dao.checkLogin2(username, password);
                    boolean checkLogAdmin = daoAdmin.checkLoginAdmin(username, password);
                    if (checkLog) {
                        session.setAttribute("username", username);
                        response.sendRedirect("index.jsp");
                    } else {
                        if (checkLogAdmin) {
                            session.setAttribute("username", "admin");
                            response.sendRedirect("index.jsp");
                        } else {
                            request.setAttribute("fail", true);
                            request.getRequestDispatcher("loginview.jsp").forward(request, response);
                        }
                    }
                }

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
