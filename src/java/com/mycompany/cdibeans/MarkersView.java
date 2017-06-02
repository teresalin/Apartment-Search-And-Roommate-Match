/*
 * Created by Seth Chen on 2017.04.29  * 
 * Copyright © 2017 Seth Chen. All rights reserved. * 
 */
package com.mycompany.cdibeans;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.map.OverlaySelectEvent;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
@ManagedBean
/**
 * ViewScoped is important here. It is needed such that the simple
 * model does not save markers from previous results. That way 
 * the map only displays either the currently selected marker
 * or the most recent search results.
 */
@ViewScoped
/**
 * MarkersView contains a map model that will be used to 
 * show markers on the map.
 */
public class MarkersView implements Serializable {
    private Marker marker;
    private MapModel simpleModel;
    @PostConstruct
    /**
     * Initialize a simpleModel with default values.
     */
    public void init() {
        simpleModel = new DefaultMapModel();
    }
    
    /**
     * Gets the current marker 
     * @return marker
     */
    public Marker getMarker() {
        return marker;
    }
    
    /**
     * This is click event method that is activated whenever 
     * a marker is clicked on the map. This will get the overlay
     * of the event and convert it to a marker.
     * @param event 
     */
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
  
    /**
     * Gets the current map model after an apartment is added.
     * @param apt is new apartment to add to map
     * @return simpleModel
     */
    public MapModel getSimpleModel(Apartment apt) {
        addAptToMap(apt);
        return simpleModel;
    }
    
    /**
     * This method adds an apartment to the map
     * @param apt is apartment to add.
     */
    public void addAptToMap(Apartment apt) {
        double latitude = apt.getLatitude().doubleValue();
        double longitude = apt.getLongitude().doubleValue();
        LatLng coord1 = new LatLng(latitude, longitude);
        //Sets marker to coord1, with title of the apartment's complex name and 
        //and the brief summary of apartment (lat, long, office address)
        simpleModel.addOverlay(new Marker(coord1, apt.getComplexName(), apt.briefSumary()));
    }
        
    /**
     * This method is used in the ApartmentSearchResults. The passed in list
     * of apartments from the search results will be added to the map
     * @param list is a list of apartments to be added
     * @return simpleModel
     */
    public MapModel getAdvancedModel(List<Apartment> list) {
        for (Apartment apt: list) {
            addAptToMap(apt);
        }        
        return simpleModel;
    }
}
