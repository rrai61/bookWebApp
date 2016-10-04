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
    
    public final List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorList();
    }
    
    public final void deleteAuthor(String columnName, Object columnValue) throws Exception {
        
        dao.deleteAuthor(columnName, columnValue);
    }
    
    public final void createAuthor(List<String> columnNames, List<Object> columnValues) throws Exception {
        
        dao.createAuthor(columnNames, columnValues);
    }
    
    public final void updateAuthor(String primaryColumnName, Object primaryColumnValue, 
            List<String> columnNames, List<Object> columnValues) throws Exception {
        
        dao.updateAuthor(primaryColumnName, primaryColumnValue, columnNames, columnValues);
    }
    
    public static void main(String[] args) throws Exception{
        
        AuthorDaoStrategy dao = new AuthorDao(new MySqlDBStrategy(), 
                "com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", 
                "root", "admin");
        AuthorService service = new AuthorService(dao);
        
        service.deleteAuthor("author_id", "8");
        
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("Willy Wonka");
//        service.updateAuthor("author_id", "8", colNames, colValues);
        
        List<Author> authors = service.getAuthorList();
        System.out.println(authors);
    }

}
