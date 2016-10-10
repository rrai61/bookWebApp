/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import javax.enterprise.context.Dependent;

/**
 * Database Access Strategy Object (low-level)
 * @author ritu
 */
@Dependent
public class MySqlDBStrategy implements DBStrategy, Serializable {

    private Connection conn;
    
    @Override
    public void openConnection(String driverClass, String url, 
            String userName, String password) throws ClassNotFoundException, SQLException {
        
        Class.forName (driverClass);
	conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException {
        
        conn.close();
    }
    
    @Override
    public List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        
        String sql ="";
        List<Map<String,Object>> records = new ArrayList<>();
        try {
            if(tableName.isEmpty()){ 
                throw new InvalidEntryException("Error: tableName cannot be empty");
            }
            if(maxRecords <= 0) {
                throw new InvalidEntryException("Error: maxRecords must be greater than 0");
            }
            else {
                sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;        
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
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
            }
        }
        catch(InvalidEntryException ex)
        {
            System.out.println(ex.getMessage());
        }     
        return records;
    }
    
    @Override
    public Map<String,Object> findRecordById(String tableName, String primaryKeyName, Object primaryValue) throws SQLException {

        Map<String,Object> record = new LinkedHashMap<>();
        try {
            if(primaryValue == null){ 
                throw new InvalidEntryException("Error: primaryValue cannot be null");
            }
            else {     
                PreparedStatement stmt = buildFindRecordByIdStatement(tableName, primaryKeyName);
                stmt.setObject(1, primaryValue);
                ResultSet rs2 = stmt.executeQuery(); 
                ResultSetMetaData rsmd2 = rs2.getMetaData(); 
                int colCount = rsmd2.getColumnCount(); 
                while(rs2.next()) {
                    for(int i=0; i < colCount; i++){
                        String colName = rsmd2.getColumnName(i+1);
                        Object colData = rs2.getObject(colName);
                        record.put(colName, colData);
                    }
                }
            }
        }
        catch (InvalidEntryException ex){
            System.out.println(ex.getMessage());
        }
        return record;
    }
    
    private PreparedStatement buildFindRecordByIdStatement(String tableName, String primaryKeyName) throws SQLException {
        
        String sql = "SELECT * FROM " + tableName + " WHERE " + primaryKeyName + " = ?";   
        PreparedStatement stmt = conn.prepareStatement(sql);   
        return stmt;
    }
    
    //DELETE method to delete records from table
    @Override
    public void deleteRecord(String tableName, String primaryKeyName, 
            Object primaryValue) throws SQLException {
        try {
            if(tableName.isEmpty()){ 
                throw new InvalidEntryException("Error: tableName cannot be empty");
            }
            if(primaryKeyName.isEmpty()) {
                throw new InvalidEntryException("Error: maxRecords must be greater than 0");
            }
            if(primaryValue == null) {
                throw new InvalidEntryException("Error: primaryValue cannot be null");
            }
            else {
                PreparedStatement stmt = buildDeleteStatement(tableName, primaryKeyName);
                stmt.setObject(1, primaryValue);
                stmt.executeUpdate(); 
            }
        }
        catch (InvalidEntryException ex){
            System.out.println(ex.getMessage());
        }  
    }
    
    private PreparedStatement buildDeleteStatement(String tableName, String primaryKeyName) throws SQLException {
        
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyName + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);   
        return stmt;
    }
    
    //CREATE method to create/insert record in table
    @Override
    public void createRecord(String tableName, List<String> colNames, 
            List<Object> colValues) throws SQLException {
        
        try {
            if(tableName.isEmpty()){ 
                throw new InvalidEntryException("Error: tableName cannot be empty");
            }
            if(colNames.isEmpty()) {
                throw new InvalidEntryException("Error: List of colNames cannot be empty");
            }
            if(colValues.isEmpty()) {
                throw new InvalidEntryException("Error: List of colValues cannot be empty");
            }
            else {
                PreparedStatement stmt = buildCreateStatement(tableName, colNames);
                for(int i=0; i<colValues.size(); i++){
                    stmt.setObject(i+1, colValues.get(i));
                }    
                stmt.executeUpdate();
            }
        }
        catch (InvalidEntryException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private PreparedStatement buildCreateStatement(String tableName, List<String> colNames) throws SQLException {
        
        String sql = "INSERT INTO " + tableName;
        StringJoiner sj = new StringJoiner(", ", " (",")");
        for(String colName : colNames){
            sj.add(colName);
        }
        sql += sj.toString();
        sql += " VALUES ";
        StringJoiner sj2 = new StringJoiner(", ", " (",")");
        for(String colName : colNames){
            sj2.add("?");
        }
        sql += sj2.toString();
        PreparedStatement stmt = conn.prepareStatement(sql);  
        return stmt;
    }
    
    //UPDATE method for updating a record in the database
    @Override
    public void updateRecord(String tableName, String primaryKeyName, Object primaryValue, List<String> colNames, 
            List<Object> colValues) throws SQLException {
        
        try {
            if(tableName.isEmpty()) { 
                throw new InvalidEntryException("Error: tableName cannot be empty");
            }
            if(primaryKeyName.isEmpty()) {
                throw new InvalidEntryException("Error: primaryKeyName cannot be empty");
            }
            if(primaryValue == null) {
                throw new InvalidEntryException("Error: primaryValue cannot be null");
            }
            if(colNames.isEmpty()) {
                throw new InvalidEntryException("Error: List of colNames cannot be empty");
            }
            if(colValues.isEmpty()) {
                throw new InvalidEntryException("Error: List of colValues cannot be empty");
            }
            else {
                PreparedStatement stmt = buildUpdateStatement(tableName, primaryKeyName, colNames);  
                for(int i=0; i<colValues.size(); i++){
                    stmt.setObject(i+1, colValues.get(i));
                }
                stmt.setObject(colValues.size()+1, primaryValue);
                stmt.executeUpdate();
            }
        }
        catch(InvalidEntryException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private PreparedStatement buildUpdateStatement(String tableName, String primaryKeyName, 
            List<String> colNames) throws SQLException{
        
        String sql = "UPDATE " + tableName + " SET ";  
        StringJoiner sj = new StringJoiner(", ");       
        for(String colName : colNames){
            sj.add(colName + "= ?");
        }
        sql += sj.toString();
        sql += " WHERE " + primaryKeyName + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);  
        return stmt;
    }
    
    
//    public static void main(String[] args) throws Exception {
//        DBStrategy db = new MySqlDBStrategy();
//        
//        db.openConnection("com.mysql.jdbc.Driver", 
//                "jdbc:mysql://localhost:3306/book", "root", "admin");
//        // delete a record
////        db.deleteRecord("author", "author_id", "3");
//        
//        // create a record
////        List<String> colNames = new ArrayList<>();
////        colNames.add("author_name");
////        List<Object> colValues = new ArrayList<>();
////        colValues.add("John Harding");
////        db.createRecord("author", colNames, colValues);
//        
//        // update a record
////        List<String> colNames = new ArrayList<>();
////        colNames.add("author_name");
////        List<Object> colValues = new ArrayList<>();
////        colValues.add("John Harding");
////        db.updateRecord("author", "author_id", "6", colNames, colValues);
//        
//        // find all records
//        List<Map<String,Object>> records = db.findAllRecords("author", 500);
//        System.out.println(records);
//        db.closeConnection();
//    }
    
}
