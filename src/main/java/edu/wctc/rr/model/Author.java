/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ritu
 */
public class Author {
    private int authorId;
    private String authorName;
    private Date dateAdded;
    
    public Author(){  
    }

    public Author(int authorId) {
        this.authorId = authorId;
    }

    public Author(int authorId, String authorName, Date dateAdded) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

    public final int getAuthorId() {
        return authorId;
    }

    public final void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public final String getAuthorName() {
        return authorName;
    }

    public final void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public final Date getDateAdded() {
        return dateAdded;
    }

    public final void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.authorId;
        hash = 53 * hash + Objects.hashCode(this.authorName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "Author ID= " + authorId + ", Author Name= " + authorName + ", Date Added= " + dateAdded + '}';
    }
    
    
    
}
