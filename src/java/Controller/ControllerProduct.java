/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Product;
import Model.DAOProduct;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;


/**
 *
 * @author Sy
 */
public class ControllerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            DAOProduct dao = new DAOProduct();
            ResultSet rs = dao.getAllCategories();
            request.setAttribute("cRs", rs);
            if (service.equals("search")) {
                String submit=request.getParameter("submit");
                if(submit!=null){
                String pname = request.getParameter("pname");
                Vector<Product> searched = dao.findByName(pname);
                request.setAttribute("searchbyname", searched);
                request.getRequestDispatcher("single-product.jsp");
                }
            }

            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) { //chuan bi du lieu call DAO
                Vector<Product> vt = dao.getAll();
                request.setAttribute("cVector", vt);
                RequestDispatcher dis = request.getRequestDispatcher("viewforcategory.jsp");
                dis.forward(request, response);
            }
            if (service.equals("adminDisplay")) {
                Vector<Product> vt = dao.getAll();
                request.setAttribute("allProduct", vt);
                request.setAttribute("cVector", vt);

                RequestDispatcher dis = request.getRequestDispatcher("DisplayProduct.jsp");
                dis.forward(request, response);
            }
            if (service.equals("displayProduct")) {
                String id = (String) request.getParameter("id");
                System.out.println(id);
                Vector<Product> vector = dao.findByCate(id);
                request.setAttribute("cVector", vector);
                RequestDispatcher dis = request.getRequestDispatcher("viewforcategory.jsp");
                dis.forward(request, response);
            }
            if (service.equals("addProduct")) {
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String pname = request.getParameter("pname");
                    String Price = request.getParameter("price");
                    String modelyear = request.getParameter("modelyear");
                    String brand = request.getParameter("brand");
                    String category = request.getParameter("category");
                    int pid = dao.generateID();
                    double price = Double.parseDouble(Price);
                    int modelYear = Integer.parseInt(modelyear);
                    Product pro = new Product(pid, pname, price, modelYear, brand, category);
                    int n = 0;
                    n = dao.addProduct(pro);
                    if (n != 0) //                response.sendRedirect("index.jsp");
                    {
                        response.sendRedirect("index.jsp");
                    }
                } else {
                    request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                }
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    Product pro = dao.findByID(id);
                    request.setAttribute("pro", pro);
                    dispatch(request, response, "/updateProduct.jsp");
                } else {// da submit --> update
                    String id = request.getParameter("pid");
                    String name = request.getParameter("pname");
                    String Price = request.getParameter("price");
                    String modelYear = request.getParameter("modelyear");
                    String brandName = request.getParameter("brand");
                    String category = request.getParameter("category");
                    //check value
                    out.print(id + "  " + name + "  " + Price + "  " + modelYear + "  " + brandName + "  " + category);
                    //convert
                    int pid = Integer.parseInt(id);
                    int modelyear = Integer.parseInt(modelYear);
                    double price = Double.parseDouble(Price);
                    out.print("213");
                    out.println(id);
                    Product pro = new Product(pid, name, price, modelyear, brandName, category);
                    dao.updateProduct(pro);
                    // disp(request, response,"ProductController");
                    response.sendRedirect("index.jsp");
                }
            }
            if(service.equals("delete")){
                int id=Integer.parseInt(request.getParameter("id"));
                int check=0;
                check=dao.removeProduct(id);
                if(check!=0){
                    response.sendRedirect("index.jsp");
                }
                else{
                    request.setAttribute("message", "There's one or more constraints remain in the Database.");
                    request.getRequestDispatcher("ControllerProduct?service=adminDisplay").forward(request, response);
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

    public void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
        //view
        disp.forward(request, response);
    }
}
