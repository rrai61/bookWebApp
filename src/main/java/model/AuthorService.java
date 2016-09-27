/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    public static void main(String[] args) throws Exception{
        
        AuthorDaoStrategy dao = new AuthorDao(new MySqlDBStrategy(), 
                "com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book", 
                "root", "admin");
        AuthorService service = new AuthorService(dao);
        List<Author> authors = service.getAuthorList();
        System.out.println(authors);
    }

}
