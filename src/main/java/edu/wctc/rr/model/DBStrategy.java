/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface for low level MySqlDBStrategy object
 * @author ritu
 */
public interface DBStrategy {

    void closeConnection() throws SQLException;

    List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;
    
    Map<String,Object> findRecordById(String tableName, String primaryKeyName, Object primaryValue) throws ClassNotFoundException, SQLException;

    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    void deleteRecord(String tableName, String primaryKeyName, Object primaryValue) throws ClassNotFoundException, SQLException;
    
    void createRecord(String tableName, List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException;
    
    void updateRecord(String tableName, String primaryKeyName, Object primaryValue, List<String> colNames, 
            List<Object> colValues) throws SQLException;
    
}
