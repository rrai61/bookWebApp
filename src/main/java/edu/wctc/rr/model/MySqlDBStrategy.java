/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Database Access Strategy Object (low-level)
 * @author ritu
 */
public class MySqlDBStrategy implements DBStrategy {
    private Connection conn;
    
    @Override
    public void openConnection(String driverClass, String url, 
            String userName, String password) 
            throws ClassNotFoundException, SQLException {
        
        Class.forName (driverClass);
	conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException {
        
        conn.close();
    }
    
    @Override
    public final List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;        
        Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);      
        List<Map<String,Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        
	while(rs.next()) {
            Map<String,Object> record = new LinkedHashMap<>();
            for(int i=0; i < colCount; i++){
                String colName = rsmd.getColumnName(i+1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);
        }
        
        return records;
    }
    
    //DELETE method to delete records from table
    @Override
    public final void deleteRecord(String tableName, String primaryKey, 
            Object primaryValue) throws SQLException {

        PreparedStatement stmt = buildDeleteStatement(tableName, primaryKey, primaryValue);      
        stmt.executeUpdate();   
    }
    
    private PreparedStatement buildDeleteStatement(String tableName, String primaryKey, 
            Object primaryValue) throws SQLException {
        
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);   
        stmt.setObject(1, primaryValue);
        
        return stmt;
    }
    
    //CREATE method to create/insert record in table
    @Override
    public final void createRecord(String tableName, List<String> colNames, 
            List<Object> colValues) throws SQLException {
        
        String sql = "INSERT INTO " + tableName;
        StringJoiner sj = new StringJoiner(", ", " (",")");
        for(String colName : colNames){
            sj.add(colName);
        }
        sql += sj.toString();
        sql += " VALUES ";
        
        StringJoiner sj2 = new StringJoiner(", ", " (",")");
        for(Object colVal : colValues){
            sj2.add("?");
        }
        sql += sj2.toString();
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        for(int i=0; i<colValues.size(); i++){
            stmt.setObject(i+1, colValues.get(i));
        }
        
        stmt.executeUpdate();
    }
    
    
    public static void main(String[] args) throws Exception {
        DBStrategy db = new MySqlDBStrategy();
        
        db.openConnection("com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", "root", "admin");
        // delete a record
//        db.deleteRecord("author", "author_id", "3");
        
        // create a record
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        List<Object> colValues = new ArrayList<>();
//        colValues.add("John Harding");
//        db.createRecord("author", colNames, colValues);
        
        // find all records
        List<Map<String,Object>> records = db.findAllRecords("author", 500);
        System.out.println(records);
        db.closeConnection();
    }
    
}
