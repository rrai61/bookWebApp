/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Data Access Object (low-level)
 * @author ritu
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy, Serializable {
    @Inject
    private DBStrategy db;
    
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    public AuthorDao(){}
    
    @Override
    public void initDao(String driverClass, String url, String userName, String password){
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPwd(password);
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return password;
    }

    public void setPwd(String password) {
        this.password = password;
    }
    
    
    @Override
    public List<Author> getAuthorList() throws Exception{
        
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
    public Author findAuthorById(String columnName, Object columnValue) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        
        Map<String,Object> rec = db.findRecordById("author", columnName, columnValue);
        
        Author author = new Author();
            
        Integer id = Integer.parseInt(rec.get("author_id").toString());
        author.setAuthorId(id);
            
        String name = rec.get("author_name").toString();
        author.setAuthorName(name != null ? name: "");
            
        Date date = (Date)rec.get("date_added");
        author.setDateAdded(date);
      
        db.closeConnection();
        
        return author;
    }
    
    @Override
    public void deleteAuthor(String columnName, Object columnValue) throws Exception {
        
        db.openConnection(driverClass, url, userName, password);
        db.deleteRecord("author", columnName, columnValue);
        db.closeConnection();
    }
    
    @Override
    public void createAuthor(List<String> columnNames, List<Object> columnValues) throws Exception {
        
        db.openConnection(driverClass, url, userName, password);
        db.createRecord("author", columnNames, columnValues);
        db.closeConnection();
    }
    
    @Override
    public void updateAuthor(String columnName, Object columnValue, 
            List<String> columnNames, List<Object> columnValues) throws Exception {

        db.openConnection(driverClass, url, userName, password);
        db.updateRecord("author", columnName, columnValue, columnNames, columnValues);
        db.closeConnection();
    }
    
}
    
//    public static void main(String[] args) throws Exception {
//        AuthorDaoStrategy dao = new AuthorDao( new MySqlDBStrategy(), 
//                "com.mysql.jdbc.Driver", 
//                "jdbc:mysql://localhost:3306/book", 
//                "root", "admin");
        
       
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("J.K. Rowling");
//        dao.createAuthor(colNames, colValues);

//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("John Hardy");
//        dao.updateAuthor("author_id", "6", colNames, colValues);
        
//        dao.deleteAuthor("author_id", "6");
        
//        List<Author> authors = dao.getAuthorList();
//        System.out.println(authors);
//    }
    
