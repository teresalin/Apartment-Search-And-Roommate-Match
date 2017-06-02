/*
 * Created by Teresa Lin on 2017.04.30  * 
 * Copyright © 2017 Osman Balci. All rights reserved. * 
 */
package com.mycompany.roommates;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author WenjiaSong
 */
@Stateless
public class RoommatesFacade extends AbstractFacade<Roommates> {

    @PersistenceContext(unitName = "Apartments-Team9PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoommatesFacade() {
        super(Roommates.class);
    }
    
    public List<Roommates> searchByName(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager()
                .createQuery("SELECT c FROM Roommates c WHERE c.firstName LIKE :searchString OR c.lastName LIKE :searchString")
                .setParameter("searchString", searchString).getResultList();
    }
    
    /**
     * @param username is the username attribute (column) value of the user
     * @return object reference of the User entity whose username is username
     */
    public Roommates findByUsername(String username) {
        if (em.createQuery("Select a from Roommates a where a.userName = '" 
                + username + "'", Roommates.class).getResultList().isEmpty())
            return null;
        else
            return (Roommates) em.createQuery("Select a from Roommates a where a.userName = '" 
                + username + "'", Roommates.class).getSingleResult();
    }
    
        /**
     * Deletes the User entity whose primary key is id
     * @param id is the Primary Key of the User entity in a table row in the CloudDriveDB database.
     */
    public void deleteUser(int id) {
        
        // The find method is inherited from the parent AbstractFacade class
        Roommates user = em.find(Roommates.class, id);
        
        // The remove method is inherited from the parent AbstractFacade class
        em.remove(user); 
    }
    
}
