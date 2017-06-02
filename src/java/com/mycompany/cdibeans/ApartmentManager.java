/*
 * Created by Seth Chen on 2017.04.26  * 
 * Copyright © 2017 Seth Chen. All rights reserved. * 
 */
package com.mycompany.cdibeans;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/*
The @SessionScoped annotation preserves the values of the ApartmentManager
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped
@Named(value = "apartmentManager")

/**
 *
 * ApartmentManager manages different lists of apartments, one for the
 * apartment listings and the other is searched, the search results.
 * 
 * @author Seth
 */
public class ApartmentManager implements Serializable{
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private final String apartmentsRESTfulWebServices_url = 
            "http://venus.cs.vt.edu/BlacksburgAptsWS-Team9/webresources/com.mycompany.blacksburgaptsws.apartments/";
    private List<Apartment> apartments;
    private Apartment selected;
    private String statusMessage;
    
    private List<Apartment> searched;
    private Apartment searchSelected;
    
    /*
    "The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done
    to perform any initialization. This method MUST be invoked before the class is put into service." [Oracle]
    */
    @PostConstruct
    public void init() {

        selected = null;
        statusMessage = "";
        
        JSONArray jsonArray;
        apartments = new ArrayList<>();

        /*
        ----------------------------------------------------------
        Create the apartments list containing Apartment objects
        ----------------------------------------------------------
         */
        try {
           
            jsonArray = new JSONArray(readUrlContent(apartmentsRESTfulWebServices_url));
            
            int index = 0;

            // The length is 115 and numberOfCharactersRead = 0,1,2,...,114
            if (jsonArray.length() > index) {

                // Created json array of apartments
                // Create an Apartment object corresponding to each JSON object in jsonArray
                while (jsonArray.length() > index) {

                    // Get the JSONObject at numberOfCharactersRead
                    JSONObject jsonObject = jsonArray.getJSONObject(index);

                    // Obtain the other attributes of a Apartment object
                    Integer id = jsonObject.getInt("apartmentsid");             
                    String name = jsonObject.getString("complexName");
                    BigDecimal latitude = BigDecimal.valueOf(jsonObject.getDouble("latitude"));
                    BigDecimal longitude = BigDecimal.valueOf(jsonObject.getDouble("longitude"));
                    String officeAddress = jsonObject.getString("officeAddress");
                    Integer roomsMin = jsonObject.getInt("roomsMin");
                    Integer roomsMax = jsonObject.getInt("roomsMax");
                    Integer rentPerPersonMin = jsonObject.getInt("rentPerPersonMin");
                    Integer rentPerPersonMax = jsonObject.getInt("rentPerPersonMax");
                    String petFriendly = jsonObject.getString("petFriendly");
                    String includedUtilities = jsonObject.getString("includedUtilities");
                    String smokeFree = jsonObject.optString("smokeFree", "");
                    double campusDistance = jsonObject.optDouble("campusDistance", 0);
                    String busStops = jsonObject.getString("busStops");
                    String gym = jsonObject.getString("gym");
                    String pool = jsonObject.getString("pool");
                    String contactInfo = jsonObject.getString("contactInfo");
                    String washerDryer = jsonObject.getString("washerDryer");
                    String shoppingAvailability = jsonObject.getString("shoppingAvailability");
                    
                    
                    // Create a new apartment with attributes obtained from the json object.
                    Apartment apartment = new Apartment(id, name, latitude, longitude, officeAddress, roomsMin,
                        roomsMax, rentPerPersonMin, rentPerPersonMax, petFriendly, includedUtilities, smokeFree,
                        campusDistance, busStops, gym, pool, contactInfo, washerDryer, shoppingAvailability);

                    // Add the newly created apartment object to the list of apartments
                    apartments.add(apartment);

                    index++;
                }

            } else {
                statusMessage = "The Apartments-Team9 getAll web service is unreachable!";
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(ApartmentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Apartment> getSearched() {
        return searched;
    }

    public void setSearched(List<Apartment> searched) {
        this.searched = searched;
    }

    public Apartment getSearchSelected() {
        return searchSelected;
    }

    public void setSearchSelected(Apartment searchSelected) {
        this.searchSelected = searchSelected;
    }
    
    private String searchName;
    private String searchRoom;
    private String searchRentMin;
    private String searchRentMax;
    private String searchDistance;
    private String searchWD;
    private String searchPet;
    private String searchSmoke;
    private String searchGym;
    private String searchPool;
    private String searchShop;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchRoom() {
        return searchRoom;
    }

    public void setSearchRoom(String searchRoom) {
        this.searchRoom = searchRoom;
    }

    public String getSearchRentMin() {
        return searchRentMin;
    }

    public void setSearchRentMin(String searchRentMin) {
        this.searchRentMin = searchRentMin;
    }

    public String getSearchRentMax() {
        return searchRentMax;
    }

    public void setSearchRentMax(String searchRentMax) {
        this.searchRentMax = searchRentMax;
    }

    public String getSearchDistance() {
        return searchDistance;
    }

    public void setSearchDistance(String searchDistance) {
        this.searchDistance = searchDistance;
    }

    public String getSearchWD() {
        return searchWD;
    }

    public void setSearchWD(String searchWD) {
        this.searchWD = searchWD;
    }

    public String getSearchPet() {
        return searchPet;
    }

    public void setSearchPet(String searchPet) {
        this.searchPet = searchPet;
    }

    public String getSearchSmoke() {
        return searchSmoke;
    }

    public void setSearchSmoke(String searchSmoke) {
        this.searchSmoke = searchSmoke;
    }

    public String getSearchGym() {
        return searchGym;
    }

    public void setSearchGym(String searchGym) {
        this.searchGym = searchGym;
    }

    public String getSearchPool() {
        return searchPool;
    }

    public void setSearchPool(String searchPool) {
        this.searchPool = searchPool;
    }

    public String getSearchShop() {
        return searchShop;
    }

    public void setSearchShop(String searchShop) {
        this.searchShop = searchShop;
    }
    
    public List<Apartment> getApartments() {
        return apartments;
    }
    
    public Apartment getSelected() {
        return selected;
    }

    public void setSelected(Apartment selected) {
        this.selected = selected;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    
    /**
     * Return the content of a given URL as String
     *
     * @param webServiceURL to retrieve the JSON data from
     * @return JSON data from the given URL as String
     * @throws Exception
     */
    public String readUrlContent(String webServiceURL) throws Exception {
        /*
        reader is an object reference pointing to an object instantiated from the BufferedReader class.
        Currently, it is "null" pointing to nothing.
         */
        BufferedReader reader = null;

        try {
            // Create a URL object from the webServiceURL given
            URL url = new URL(webServiceURL);
            /*
            The BufferedReader class reads text from a character-input stream, buffering characters
            so as to provide for the efficient reading of characters, arrays, and lines.
             */
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            // Create a mutable sequence of characters and store its object reference into buffer
            StringBuilder buffer = new StringBuilder();

            // Create an array of characters of size 10240
            char[] chars = new char[10240];

            int numberOfCharactersRead;
            /*
            The read(chars) method of the reader object instantiated from the BufferedReader class
            reads 10240 characters as defined by "chars" into a portion of a buffered array.

            The read(chars) method attempts to read as many characters as possible by repeatedly
            invoking the read method of the underlying stream. This iterated read continues until
            one of the following conditions becomes true:

                (1) The specified number of characters have been read, thus returning the number of characters read.
                (2) The read method of the underlying stream returns -1, indicating end-of-file, or
                (3) The ready method of the underlying stream returns false, indicating that further input requests would block.

            If the first read on the underlying stream returns -1 to indicate end-of-file then the read(chars) method returns -1.
            Otherwise the read(chars) method returns the number of characters actually read.
             */
            while ((numberOfCharactersRead = reader.read(chars)) != -1) {
                buffer.append(chars, 0, numberOfCharactersRead);
            }

            // Return the String representation of the created buffer
            return buffer.toString();

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    /**
     * This method clears all search variables. This is to clear
     * values in the search window.
     */
    public void clearSearchFields() {
        searchName = null;
        searchRoom = null;
        searchRentMin = null;
        searchRentMax = null;
        searchDistance = null;
        searchWD = null;
        searchPet = null;
        searchSmoke = null;
        searchGym = null;
        searchPool = null;
        searchShop = null;
    }
    
    /**
     * performSearch is the search function that searches for apartments that
     * match the parameters of the user input.
     * 
     * @return page redirect to AptSearchResult.xhtml
     */
    public String performSearch() {
        searchSelected = null;
        searched = new ArrayList<>();
        
        /**
         * if there is specific complex name the user is searching for,
         * it will find the apartment and upload it.
         */
        if (!searchName.equals("") && !searchName.isEmpty()) {

            for (Apartment apt : apartments) {
                if (apt.getComplexName().contains(searchName)) {
                    searched.add(apt);
                }
            }
            return "AptSearchResults?faces-redirect=true";
        }

        for (Apartment apt : apartments) {
            //Scans the searchRoom, which is the number of rooms the user asked
            Scanner scanner = new Scanner(searchRoom);
            int numRoom = scanner.hasNextInt() ? scanner.nextInt() : 0;
            if (numRoom > 0) {
                //If the number of rooms fall within range of the apartment, keep going
                //else move to the next apartment in the list.
                if (apt.getRoomsMax() < numRoom || apt.getRoomsMin() > numRoom) {
                    continue;
                }
            }

            scanner = new Scanner(searchRentMin);
            int monthlyRentMin = scanner.hasNextInt() ? scanner.nextInt() : 0;
            scanner = new Scanner(searchRentMax);
            int monthlyRentMax = scanner.hasNextInt() ? scanner.nextInt() : 5000;
            
            //If the rent max and min fall within range of what user asked
            if (apt.getRentPerPersonMax() < monthlyRentMin 
                    || apt.getRentPerPersonMin() > monthlyRentMax){ 
                continue;
            }
            
            //Scans the search distance
            scanner = new Scanner(searchDistance);
            double dist = scanner.hasNextDouble() ? scanner.nextDouble() : -1;
            //-1 if the distance is not available
            if (dist != -1) {
                //skps apartment with distance greaer than dist
                if (apt.getCampusDistance() > dist) {
                    continue;
                }
            }

            if (!searchWD.equals("all")) {
                if (searchWD.equals("inUnit") && !apt.getWasherDryer().contains("In-Unit")
                        || searchWD.equals("inBuidling") && !apt.getWasherDryer().contains("In-Building")
                        || searchWD.equals("onSite") && !apt.getWasherDryer().contains("On-Site")
                        || searchWD.equals("other") && !apt.getWasherDryer().contains("Communal")) {
                    continue;
                }
            }
            //Checks if apartment is petFriendly or not and if it matches searchPet (user input)
            if (!searchPet.equals("all")) {
                boolean yes = !apt.getPetFriendly().contains("No");
                if ((searchPet.equals("yes") && !yes) || (searchPet.equals("no") && yes)) {
                    continue;
                }
            }
            
            //Checks if apartment is smokeFree or not and if it matches searchSmoke (user input)
            if (!searchSmoke.equals("all")) {
                boolean yes = !apt.getSmokeFree().contains("N");
                if ((searchSmoke.equals("yes") && !yes) || (searchSmoke.equals("no") && yes)) {
                    continue;
                }

            }
            
            //Checks if apartment has a gym or not and if it matches searchGym (user input)
            if (!searchGym.equals("all")) {
                boolean yes = !apt.getGym().contains("N");
                if ((searchGym.equals("yes") && !yes) || (searchGym.equals("no") && yes)) {
                    continue;
                }
            }
            
            //Checks if apartment has a pool or not and if it matches searchPool (user input)
            if (!searchPool.equals("all")) {
                boolean yes = !apt.getPool().contains("N");
                if ((searchPool.equals("yes") && !yes) || (searchPool.equals("no") && yes)) {
                    continue;
                }
            }
            
            //Checks if apartment has shopping availability and if it matches searchShop (user input)
            if (!searchShop.equals("all")) {
                boolean yes = !apt.getShoppingAvailability().contains("N");
                if ((searchShop.equals("yes") && !yes) || (searchShop.equals("no") && yes)) {
                    continue;
                }
            }
            //Testing purposes to see values
            System.out.println("Searched Rent:       " + apt.getRentPerPersonMax() + ", " + apt.getRentPerPersonMin());
            //Add the apartment to searched 
            searched.add(apt);

        }

        return "AptSearchResults?faces-redirect=true";
    }
    
    /**
     * This function is called when the Go Back button is clicked. It 
     * will redirect to the Apartments Listing page.
     * @return string redirect to Apartments.
     */
    public String goBack() {
        return "Apartments?faces-redirect=true";
    }
}
