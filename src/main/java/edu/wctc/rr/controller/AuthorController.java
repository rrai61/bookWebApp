/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.controller;

import edu.wctc.rr.ejb.AuthorFacade;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author ritu
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
    public String destination = "/authorTablePage.jsp";
    private HttpSession session;
    
    @Inject
    private AuthorFacade authService;

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, Exception {
        
        response.setContentType("text/html;charset=UTF-8");

        try {

            String action = request.getParameter("action");
            String subAction = request.getParameter("submit");
            String[] results;
            
            switch(action) {
                case "view":
                    switch(subAction) {
                        case "Start Session":   
                            String name = request.getParameter("nameStr") +"'s";
                            session = request.getSession();
                            session.setAttribute("name", name);                   
                            break;
                        case "End Session":
                            session.invalidate();   
                            break;
                        case "Create":
                            // creating new author
                            Author author = new Author();
                            author.setAuthorName(request.getParameter("nameObj"));
                            author.setDateAdded(new Date());
                            authService.create(author);
                            break;
                        case "Save":
                            // updating edit to existing author
                            String dateString = request.getParameter("dateObj");
                            Date startDate= new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                            String idStr = request.getParameter("idObj");
                            Integer id = Integer.parseInt(idStr);
                            Author author2 = authService.find(id);
                            author2.setAuthorName(request.getParameter("nameObj"));
                            author2.setDateAdded(startDate);
                            authService.edit(author2);
                            break;
                        default:
                            this.refreshList(request, authService);
                            destination = "/authorTablePage.jsp";
                    }
                    results = null;
                    this.refreshList(request, authService);
                    destination = "/authorTablePage.jsp";
                    break;
                    
                case "manage":
                    switch(subAction) {
                        case "delete": 
                            results = request.getParameterValues("authorCheck");
                            String author_id = results[0];
                            Author author3 = authService.find(author_id);
                            authService.remove(author3);
                            
                            this.refreshList(request, authService);
                            destination = "/authorTablePage.jsp";
                            break;
                        case "add/edit":
                            results = request.getParameterValues("authorCheck");

                            if(results == null){
                                // add action
                                Author author = null;
                                request.setAttribute("author", author);
                            }
                            else {
                                // edit action
                                results = request.getParameterValues("authorCheck");
                                author_id = results[0];
                                Author author4 = authService.find(author_id);
                                request.setAttribute("author", author4);
                            }
                            destination = "/authorAddEdit.jsp";
                            break;
                    }
                    break;                    
            }
        }
        catch(Exception e) {
            request.setAttribute("errMsg", e.getMessage());
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }
    
    private void refreshList(HttpServletRequest request, AuthorFacade authService) throws ClassNotFoundException, SQLException, Exception {
        List<Author> authorList = authService.findAll();
        request.setAttribute("authorList", authorList);
    }
    
    @Override
    public void init() throws ServletException {

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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
