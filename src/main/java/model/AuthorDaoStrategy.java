/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for low level AuthorDao object
 * @author ritu
 */
public interface AuthorDaoStrategy {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    
    void deleteAuthor(String columnValue, String columnName) throws ClassNotFoundException, SQLException;
    
}
