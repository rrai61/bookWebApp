/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object (low-level)
 * @author ritu
 */
public class AuthorDao implements AuthorDaoStrategy {
    private DBStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDao(DBStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }
    
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException{
        
        db.openConnection(driverClass, url, userName, password);
        
        List<Map<String,Object>> records = db.findAllRecords("author", 500);
        List<Author> authors = new ArrayList<>();
        
        for(Map<String,Object> rec : records){
            Author author = new Author();
            
            Integer id = Integer.parseInt(rec.get("author_id").toString());
            author.setAuthorId(id);
            
            String name = rec.get("author_name").toString();
            author.setAuthorName(name != null ? name: "");
            
            Date date = (Date)rec.get("date_added");
            author.setDateAdded(date);
            
            authors.add(author);
        }
      
        db.closeConnection();
        
        return authors;
    }
    
    @Override
    public void deleteAuthor(String columnValue) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        
        Integer primaryKey = Integer.parseInt(columnValue);
        db.deleteRecord("author", "author_id", primaryKey);
      
        db.closeConnection();
    }
    
    @Override
    public void createAuthor(List<String> columnNames, List<Object> columnValues) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        
        db.createRecord("author", columnNames, columnValues);
      
        db.closeConnection();
    }
    
    public static void main(String[] args) throws Exception {
        AuthorDaoStrategy dao = new AuthorDao( new MySqlDBStrategy(), 
                "com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", 
                "root", "admin");
        
//        dao.deleteAuthor("2", "author_id");
       
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("J.K. Rowling");
//        dao.createAuthor(colNames, colValues);
        
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
    }
    
}
