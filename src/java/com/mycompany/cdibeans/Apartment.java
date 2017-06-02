/*
 * Created by Seth Chen on 2017.04.26  * 
 * Copyright © 2017 Seth Chen. All rights reserved. * 
 */
package com.mycompany.cdibeans;
import java.math.BigDecimal;
/**
 * Apartment object
 * @author Seth
 */
public class Apartment {
    private Integer apartmentid;
    private String complexName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String officeAddress;
    private int roomsMin;
    private int roomsMax;
    private int rentPerPersonMin;
    private int rentPerPersonMax;
    private String petFriendly;
    private String includedUtilities;
    private String smokeFree;
    private double campusDistance;
    private String busStops;
    private String gym;
    private String pool;
    private String contactInfo;
    private String washerDryer;
    private String shoppingAvailability;
    
    public Apartment() {
    }

    public Apartment(Integer apartmentid) {
        this.apartmentid = apartmentid;
    }
    
    public Apartment(Integer apartmentid, String complexName, BigDecimal latitude, BigDecimal longitude, 
            String officeAddress, int roomsMin, int roomsMax, int rentPerPersonMin, int rentPerPersonMax,
            String petFriendly, String includedUtilities, String smokeFree, double campusDistance, 
            String busStops, String gym, String pool, String contactInfo, String washerDryer, 
            String shoppingAvailability) {
        this.apartmentid = apartmentid;
        this.complexName = complexName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.officeAddress = officeAddress;
        this.roomsMin = roomsMin;
        this.roomsMax = roomsMax;
        this.rentPerPersonMin = rentPerPersonMin;
        this.rentPerPersonMax = rentPerPersonMax;
        this.petFriendly = petFriendly;
        this.includedUtilities = includedUtilities;
        this.smokeFree = smokeFree;
        this.campusDistance = campusDistance;
        this.busStops = busStops;
        this.gym = gym;
        this.pool = pool;
        this.contactInfo = contactInfo;
        this.washerDryer = washerDryer;
        this.shoppingAvailability = shoppingAvailability;
    }

    public Integer getApartmentsid() {
        return apartmentid;
    }

    public void setApartmentsid(Integer apartmentid) {
        this.apartmentid = apartmentid;
    }

    public String getComplexName() {
        return complexName;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public int getRoomsMin() {
        return roomsMin;
    }

    public void setRoomsMin(int roomsMin) {
        this.roomsMin = roomsMin;
    }

    public int getRoomsMax() {
        return roomsMax;
    }

    public void setRoomsMax(int roomsMax) {
        this.roomsMax = roomsMax;
    }

    public int getRentPerPersonMin() {
        return rentPerPersonMin;
    }

    public void setRentPerPersonMin(int rentPerPersonMin) {
        this.rentPerPersonMin = rentPerPersonMin;
    }

    public int getRentPerPersonMax() {
        return rentPerPersonMax;
    }

    public void setRentPerPersonMax(int rentPerPersonMax) {
        this.rentPerPersonMax = rentPerPersonMax;
    }

    public String getPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(String petFriendly) {
        this.petFriendly = petFriendly;
    }

    public String getIncludedUtilities() {
        return includedUtilities;
    }

    public void setIncludedUtilities(String includedUtilities) {
        this.includedUtilities = includedUtilities;
    }

    public String getSmokeFree() {
        return smokeFree;
    }

    public void setSmokeFree(String smokeFree) {
        this.smokeFree = smokeFree;
    }

    public double getCampusDistance() {
        return campusDistance;
    }

    public void setCampusDistance(double campusDistance) {
        this.campusDistance = campusDistance;
    }

    public String getBusStops() {
        return busStops;
    }

    public void setBusStops(String busStops) {
        this.busStops = busStops;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getWasherDryer() {
        return washerDryer;
    }

    public void setWasherDryer(String washerDryer) {
        this.washerDryer = washerDryer;
    }

    public String getShoppingAvailability() {
        return shoppingAvailability;
    }

    public void setShoppingAvailability(String shoppingAvailability) {
        this.shoppingAvailability = shoppingAvailability;
    }
    
    /**
     * Some key information of apartment and store in array
     * @return arr 
     */
    public String[] briefSumary() {
        String[] arr = {complexName, String.valueOf(latitude),String.valueOf(longitude), officeAddress};
        return arr;
    }
}
