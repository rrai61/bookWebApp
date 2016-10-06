/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.rr.model.Author;
import edu.wctc.rr.model.AuthorDao;
import edu.wctc.rr.model.AuthorDaoStrategy;
import edu.wctc.rr.model.AuthorService;
import edu.wctc.rr.model.MySqlDBStrategy;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 *
 * @author ritu
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
    public String destination = "/authorTablePage.jsp";
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    @Inject
    private AuthorService authService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            configDbConnection();

            String action = request.getParameter("action");
            String subAction = request.getParameter("submit");
            String[] results;
        
            switch(action) {
                case "view":
                    this.refreshList(request, authService);
                    break;
                case "manage":
                    switch(subAction) {
                        case "delete": 
                            results = request.getParameterValues("authorCheck");
                            for(int i=0; i < results.length; i++) {
                                authService.deleteAuthor("author_id", results[i]);
                            }
                            this.refreshList(request, authService);
                            break;
                        case "add/edit":
                            results = request.getParameterValues("authorCheck");
                            List<Author> editList = new ArrayList();
                            if(results == null){
                                // add action
                                Author author = new Author();
                                editList.add(author);
                            }
                            else {
                                // edit action
                                for(int i=0; i < results.length; i++) {
                                    Author author1 = authService.getAuthorById("author_id", results[i]);
                                    editList.add(author1);
                                }
                            }
                            request.setAttribute("editList", editList);
                            destination = "/authorAddEdit.jsp";
                            break;
                    }
                    break;                    
            }
        }
        catch(Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }
    
    private void refreshList(HttpServletRequest request, AuthorService authService) throws ClassNotFoundException, SQLException, Exception {
        List<Author> authorList = authService.getAuthorList();
        request.setAttribute("authorList", authorList);
    }
    
    @Override
    public void init() throws ServletException {
        driverClass = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/book";
        userName = "root";
        password = "admin";
    }
    
    private void configDbConnection() {
        authService.getDao().initDao(driverClass, url, userName, password);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
