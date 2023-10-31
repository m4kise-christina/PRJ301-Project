/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Customers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.DAOCustomers;
import java.util.Vector;

/**
 *
 * @author Sy
 */
public class ControllerCustomers extends HttpServlet {

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

        HttpSession session = request.getSession();
        DAOCustomers dao = new DAOCustomers();
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");

            if (service.equals("add")) {
                int id = dao.generateID();
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String zip_code = request.getParameter("zip_code");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Customers cus = new Customers(id, name, phone, email, address, zip_code, username, password);
                int n = dao.addCustomers(cus);
                request.setAttribute("custom", cus);
                response.sendRedirect("RegisterSuccessful.jsp");
            }
            
            if (service.equals("listAll")) {
                Vector<Customers> vector = dao.getAll();
                display(vector, out);
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

    public void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
        //view
        disp.forward(request, response);
    }

    public void display(Vector<Customers> vector, PrintWriter out) {
        out.print("<table border=1>\n"
                + "	<caption>List of Customers</caption>\n"
                + "	<tr>\n"
                + "		<th>CustomerID</th>\n"
                + "		<th>CustomerName</th>\n"
                + "		<th>Phone</th>\n"
                + "		<th>Email</th>\n"
                + "		<th>Address</th>\n"
                + "		<th>Zip_code</th>\n"
                + "		<th>Username</th>\n"
                + "		<th>Password</th>\n"
                + "	</tr>");
        for (Customers cus : vector) {
            out.print("<tr>\n"
                    + "		<td>" + cus.getCustomer_id() + "</td>\n"
                    + "		<td>" + cus.getCustomer_name() + "</td>\n"
                    + "		<td>" + cus.getPhone() + "</td>\n"
                    + "		<td>" + cus.getEmail() + "</td>\n"
                    + "		<td>" + cus.getAddress() + "</td>\n"
                    + "		<td>" + cus.getZip_code() + "</td>\n"
                    + "		<td>" + cus.getUsername() + "</td>\n"
                    + "		<td>" + cus.getPassword() + "</td>\n"
                    + "	</tr>");
        }
        out.print("</table>");
    }
}
