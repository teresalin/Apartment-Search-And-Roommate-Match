/*
 * Created by Wenjia Song on 2017.04.29  * 
 * Copyright © 2017 Wenjia Song. All rights reserved. * 
 */
package com.mycompany.roommates;

import com.mycompany.roommates.util.JsfUtil;
import com.mycompany.roommates.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("roommatesController")
@SessionScoped
public class RoommatesController implements Serializable {

    @EJB
    private com.mycompany.roommates.RoommatesFacade ejbFacade;
    
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    
    
    private List<Roommates> items = null;
    private Roommates selected;
    private Roommates searchedSelected;

    private List<Roommates> searched = null;
    private String searchName;
    private String searchGender;
    private String searchCollegeStatus;
    private String searchAgeMin;
    private String searchAgeMax;
    private String searchPet;
    private String searchBudgetMin;
    private String searchBudgetMax;
    private String searchNumRmt;

    public RoommatesController() {
    }
    
    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public Roommates getSearchedSelected() {
        return searchedSelected;
    }

    public void setSearchedSelected(Roommates searchedSelected) {
        this.searchedSelected = searchedSelected;
    }

    public List<Roommates> getSearched() {
        return searched;
    }

    public void setSearched(List<Roommates> searched) {
        this.searched = searched;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchGender() {
        return searchGender;
    }

    public void setSearchGender(String searchGender) {
        this.searchGender = searchGender;
    }

    public String getSearchCollegeStatus() {
        return searchCollegeStatus;
    }

    public void setSearchCollegeStatus(String searchCollegeStatus) {
        this.searchCollegeStatus = searchCollegeStatus;
    }

    public String getSearchAgeMin() {
        return searchAgeMin;
    }

    public void setSearchAgeMin(String searchAgeMin) {
        this.searchAgeMin = searchAgeMin;
    }

    public String getSearchAgeMax() {
        return searchAgeMax;
    }

    public void setSearchAgeMax(String searchAgeMax) {
        this.searchAgeMax = searchAgeMax;
    }

    public String getSearchPet() {
        return searchPet;
    }

    public void setSearchPet(String searchPet) {
        this.searchPet = searchPet;
    }

    public String getSearchBudgetMin() {
        return searchBudgetMin;
    }

    public void setSearchBudgetMin(String searchBudgetMin) {
        this.searchBudgetMin = searchBudgetMin;
    }

    public String getSearchBudgetMax() {
        return searchBudgetMax;
    }

    public void setSearchBudgetMax(String searchBudgetMax) {
        this.searchBudgetMax = searchBudgetMax;
    }

    public String getSearchNumRmt() {
        return searchNumRmt;
    }

    public void setSearchNumRmt(String searchNumRmt) {
        this.searchNumRmt = searchNumRmt;
    }

    public Roommates getSelected() {
        return selected;
    }

    public void setSelected(Roommates selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RoommatesFacade getFacade() {
        return ejbFacade;
    }

    public Roommates prepareCreate() {
        selected = new Roommates();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RoommatesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RoommatesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RoommatesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Roommates> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    /**
     * performSearch is the search function that searches for roommates that
     * match the parameters of the user input.
     * If users are looking for a certain person(enter roommate's name), if will
     * look for people whose names containing user's input.
     * Otherwise it will looking for roommates who match the preference of the user.
     * 
     * @return page redirect to RmtSearchResults.xhtml
     */
    public String performSearch() {
        if (!searchName.equals("") || !searchName.isEmpty()) {
            searched = ejbFacade.searchByName(searchName);
            return "RmtSearchResults?faces-redirect=true";
        }

        searched = new ArrayList<>();

        for (Roommates rmt : getItems()) {
            if (!searchGender.equals("all")) {
                if (searchGender.contains("F") && !rmt.getGender().contains("F")
                        || !searchGender.contains("F") && rmt.getGender().contains("F")) {
                    continue;
                }
            }

            Scanner scanner = new Scanner(searchAgeMin);
            int ageMin = scanner.hasNextInt() ? scanner.nextInt() : 0;
            scanner = new Scanner(searchAgeMax);
            int ageMax = scanner.hasNextInt() ? scanner.nextInt() : 100;
            if (ageMin > rmt.getAge() || ageMax < rmt.getAge()) {
                continue;
            }

            if (!searchCollegeStatus.equals("all")) {
                if (searchCollegeStatus.contains("N") && !rmt.getCollegeStatus().contains("N")
                        || !searchCollegeStatus.contains("N") && rmt.getCollegeStatus().contains("N")) {
                    continue;
                }
            }

            if (!searchPet.equals("all")) {
                if (searchPet.contains("y") && !rmt.getPetPreference()
                        || searchPet.contains("n") && rmt.getPetPreference()) {
                    continue;
                }
            }

            scanner = new Scanner(searchBudgetMin);
            int budgetMin = scanner.hasNextInt() ? scanner.nextInt() : 0;
            scanner = new Scanner(searchBudgetMax);
            int budgetMax = scanner.hasNextInt() ? scanner.nextInt() : 5000;
            if (budgetMin > rmt.getBudgetMax() || budgetMax < rmt.getBudgetMin()) {
                continue;
            }

            scanner = new Scanner(searchNumRmt);
            int numRmt = scanner.hasNextInt() ? scanner.nextInt() : -1;
            if (numRmt != -1) {
                if (rmt.getNumberRoommatesWanted() == 0 || numRmt != rmt.getNumberRoommatesWanted()) {
                    continue;
                }
            }

            searched.add(rmt);
        }

        return "RmtSearchResults?faces-redirect=true";
    }

    /**
     * This method clears all search variables. This is to clear
     * values in the search window.
     */
    public void clearSearchFields() {
        searchGender = null;
        searchAgeMin = null;
        searchAgeMax = null;
        searchCollegeStatus = null;
        searchPet = null;
        searchBudgetMin = null;
        searchBudgetMax = null;
        searchNumRmt = null;
    }
    
    /**
     * This function is called when the Go Back button is clicked. It 
     * will redirect to the Apartments Listing page.
     * @return string redirect to Apartments.
     */
    public String goBack() {
        return "Roommates?faces-redirect=true";
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Roommates getRoommates(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Roommates> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Roommates> getItemsAvailableSelectOne() {
        return getFacade().findAll();

    }

    @FacesConverter(forClass = Roommates.class)
    public static class RoommatesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RoommatesController controller = (RoommatesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "roommatesController");
            return controller.getRoommates(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Roommates) {
                Roommates o = (Roommates) object;
                return getStringKey(o.getUserId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Roommates.class.getName()});
                return null;
            }
        }

    }

}
