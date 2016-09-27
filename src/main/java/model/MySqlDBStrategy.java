/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    public List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        
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
    public void deleteRecord(String tableName, String primaryKey, 
            String primaryValue) throws SQLException {
        
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = " + primaryValue;
        Statement stmt = conn.createStatement();       
        stmt.executeUpdate(sql);   
    }
    
    //CREATE method to create/insert record in table
    @Override
    public void createRecord(String tableName, List<String> colNames, 
            List<Object> colValues) throws SQLException {
        
        String sql = "INSERT INTO " + tableName;
        StringJoiner sj = new StringJoiner(", ", " (",")");
        for(String colName : colNames){
            sj.add(colName);
        }
        sql += sj.toString();
        sql += " VALUES ";
        
        Statement stmt = conn.createStatement();       
        stmt.executeUpdate(sql);
    }
    
     //UPDATE method to update record in table
    @Override
    public void updateRecord(String tableName, String primaryKey, 
            String primaryValue) throws SQLException {
        
        String sql = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = " + primaryValue;        
        Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);      
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        Map<String,Object> record = new LinkedHashMap<>();
            for(int i=0; i < colCount; i++){
                String colName = rsmd.getColumnName(i+1);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
    }
    
    private PreparedStatement buildInsertStatement(Connection conn_loc, String tableName, List colDescriptors)
    throws SQLException {
        StringBuffer sql = new StringBuffer("INSERT INTO ");
        (sql.append(tableName)).append(" (");
        final Iterator i=colDescriptors.iterator();
        while( i.hasNext() ) {
                (sql.append( (String)i.next() )).append(", ");
        }
        sql = new StringBuffer( (sql.toString()).substring( 0,(sql.toString()).lastIndexOf(", ") ) + ") VALUES (" );
        for( int j = 0; j < colDescriptors.size(); j++ ) {
                sql.append("?, ");
        }
        final String finalSQL=(sql.toString()).substring(0,(sql.toString()).lastIndexOf(", ")) + ")";
        //System.out.println(finalSQL);
        return conn_loc.prepareStatement(finalSQL);
    }
    
    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName, List colDescriptors, String whereField)
    throws SQLException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i=colDescriptors.iterator();
        while( i.hasNext() ) {
                (sql.append( (String)i.next() )).append(" = ?, ");
        }
        sql = new StringBuffer( (sql.toString()).substring( 0,(sql.toString()).lastIndexOf(", ") ) );
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL=sql.toString();
        return conn_loc.prepareStatement(finalSQL);
    }
    
    private PreparedStatement buildDeleteStatement(Connection conn_loc, String tableName, String whereField)
    throws SQLException {
        final StringBuffer sql=new StringBuffer("DELETE FROM ");
        sql.append(tableName);

        // delete all records if whereField is null
        if(whereField != null ) {
                sql.append(" WHERE ");
                (sql.append( whereField )).append(" = ?");
        }

        final String finalSQL=sql.toString();
//		System.out.println(finalSQL);
        return conn_loc.prepareStatement(finalSQL);
    }
    
    public static void main(String[] args) throws Exception {
        DBStrategy db = new MySqlDBStrategy();
        
        db.openConnection("com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", "root", "admin");
        // delete a record
//        db.deleteRecord("author", "author_id", "3");
        
        // create a record
        List<String> colNames = new ArrayList<>();
        colNames.add("author_id");
        colNames.add("author_name");
        List<Object> colValues = new ArrayList<>();
        colValues.add(1);
        colValues.add("Mad Max");
        db.createRecord("author", colNames, colValues);
        
        // find all records
        List<Map<String,Object>> records = db.findAllRecords("author", 500);
        System.out.println(records);
        db.closeConnection();
    }
    
}
