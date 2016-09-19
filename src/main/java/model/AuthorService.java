/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    private List<Author> authorList;
    
    public AuthorService() {
                
        Author author1 = new Author();
        author1.setAuthorId(1);
        author1.setAuthorName("Malcolm Gladwell");
        Date date1 = new GregorianCalendar(2016, Calendar.SEPTEMBER, 18).getTime();
        author1.setDateAdded(date1);
        
        Author author2 = new Author();
        author2.setAuthorId(2);
        author2.setAuthorName("John Steinbeck");
        Date date2 = new GregorianCalendar(2016, Calendar.SEPTEMBER, 17).getTime();
        author2.setDateAdded(date2);
        
        Author author3 = new Author();
        author3.setAuthorId(3);
        author3.setAuthorName("Dan Brown");
        Date date3 = new GregorianCalendar(2016, Calendar.SEPTEMBER, 19).getTime();
        author3.setDateAdded(date3);
        
        
        authorList = new ArrayList<>();
        authorList.add(author1);
        authorList.add(author2);
        authorList.add(author3);
    }

    public List<Author> getAuthorList() {
        return authorList;
    }
    
    
}
