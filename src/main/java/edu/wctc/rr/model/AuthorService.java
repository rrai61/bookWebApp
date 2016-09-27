/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author ritu
 */
public class AuthorService {
    private AuthorDaoStrategy dao;

    public AuthorService(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorList();
    }
    
    public void deleteAuthor(String columnValue) throws Exception {
        dao.deleteAuthor(columnValue);
    }
    
    public void createAuthor(List<String> columnNames, List<Object> columnValues) throws Exception {
        dao.createAuthor(columnNames, columnValues);
    }
    
    public static void main(String[] args) throws Exception{
        
        AuthorDaoStrategy dao = new AuthorDao(new MySqlDBStrategy(), 
                "com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", 
                "root", "admin");
        AuthorService service = new AuthorService(dao);
        
        //service.deleteAuthor("7");
        
        List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        List<Object> colValues = new ArrayList<>();
        colValues.add("Peter Holmes");
        service.createAuthor(colNames, colValues);
        
        List<Author> authors = service.getAuthorList();
        System.out.println(authors);
    }

}
