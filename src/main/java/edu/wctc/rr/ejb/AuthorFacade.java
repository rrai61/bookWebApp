/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.ejb;

import edu.wctc.rr.model.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ritu
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public List<Author> findByName(String name) {
        List<Author> authorList = null;
        
        // using a numbered query
        String jpql = "select a from Author where a.authorName = ?1";
        TypedQuery<Author> query = getEntityManager().createQuery(jpql, Author.class);
        query.setParameter(1, name);
        authorList = query.getResultList();
        
        // using named parameters
        String jpql2 = "select a from Author where a.authorName = ?name";
        TypedQuery<Author> query2 = getEntityManager().createQuery(jpql2, Author.class);
        query2.setParameter("name", name);
        authorList = query2.getResultList();
        
        // using a named query
        TypedQuery<Author> query3 = getEntityManager().createNamedQuery("Author.findByAuthorName", Author.class);
        query3.setParameter("authorName", name);
        authorList = query3.getResultList();
        
        return authorList;
    }
    
    public void deleteAuthorById(int id) {
        Author author = getEntityManager().find(Author.class, id); 
        getEntityManager().remove(author);
    }
    
    public void convertNamesToUpperCase() {
        List<Author> authorList = findAll();
        List<String> authorNames = null;
        String jpql = "update a from Author set a.authorName = :upperCaseName where a.authorName = :origName";
        
        for(Author a: authorList){
            authorNames.add(a.getAuthorName());
        }
        for(String origName: authorNames){
            TypedQuery<Author> query = getEntityManager().createQuery(jpql, Author.class);
            String upperCaseName = origName.toUpperCase();
            query.setParameter("upperCaseName", upperCaseName);
            query.setParameter("origName", origName);
            query.executeUpdate();
        }
    }
    
}
