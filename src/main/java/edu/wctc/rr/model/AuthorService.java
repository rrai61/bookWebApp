/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author ritu
 */
@SessionScoped
public class AuthorService implements Serializable {
    
    @Inject
    private AuthorDaoStrategy dao;
    
    public AuthorService(){
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException, Exception {
        
        return dao.getAuthorList();
    }
    
    public Author getAuthorById(String columnName, Object columnValue) throws Exception{
        
        return dao.findAuthorById(columnName, columnValue);
    }
    
    public void deleteAuthor(String columnName, Object columnValue) throws Exception {
        
        dao.deleteAuthor(columnName, columnValue);
    }
    
    public void createAuthor(List<String> columnNames, List<Object> columnValues) throws Exception {
        
        dao.createAuthor(columnNames, columnValues);
    }
    
    public void updateAuthor(String primaryColumnName, Object primaryColumnValue, 
            List<String> columnNames, List<Object> columnValues) throws Exception {
        
        dao.updateAuthor(primaryColumnName, primaryColumnValue, columnNames, columnValues);
    }
    
//    public static void main(String[] args) throws Exception{
//        
//        AuthorDaoStrategy dao = new AuthorDao(new MySqlDBStrategy(), 
//                "com.mysql.jdbc.Driver", 
//                "jdbc:mysql://localhost:3306/book", 
//                "root", "admin");
//        AuthorService service = new AuthorService(dao);
//        
//        service.deleteAuthor("author_id", "8");
//        
////        List<String> colNames = new ArrayList<>();
////        colNames.add("author_name");
////        List<Object> colValues = new ArrayList<>();
////        colValues.add("Willy Wonka");
////        service.updateAuthor("author_id", "8", colNames, colValues);
//        
//        List<Author> authors = service.getAuthorList();
//        System.out.println(authors);
//    }
//
//}
}