/*
 * Created by Teresa Lin on 2017.05.01  * 
 * Copyright © 2017 Osman Balci. All rights reserved. * 
 */
package com.mycompany.roommates;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Teresa Lin
 */
@Entity
@Table(name = "Roommates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roommates.findAll", query = "SELECT r FROM Roommates r")
    , @NamedQuery(name = "Roommates.findByUserId", query = "SELECT r FROM Roommates r WHERE r.userId = :userId")
    , @NamedQuery(name = "Roommates.findByFirstName", query = "SELECT r FROM Roommates r WHERE r.firstName = :firstName")
    , @NamedQuery(name = "Roommates.findByLastName", query = "SELECT r FROM Roommates r WHERE r.lastName = :lastName")
    , @NamedQuery(name = "Roommates.findByUserName", query = "SELECT r FROM Roommates r WHERE r.userName = :userName")
    , @NamedQuery(name = "Roommates.findByPassword", query = "SELECT r FROM Roommates r WHERE r.password = :password")
    , @NamedQuery(name = "Roommates.findByAge", query = "SELECT r FROM Roommates r WHERE r.age = :age")
    , @NamedQuery(name = "Roommates.findByGender", query = "SELECT r FROM Roommates r WHERE r.gender = :gender")
    , @NamedQuery(name = "Roommates.findByEmail", query = "SELECT r FROM Roommates r WHERE r.email = :email")
    , @NamedQuery(name = "Roommates.findByPhone", query = "SELECT r FROM Roommates r WHERE r.phone = :phone")
    , @NamedQuery(name = "Roommates.findByStudentStatus", query = "SELECT r FROM Roommates r WHERE r.studentStatus = :studentStatus")
    , @NamedQuery(name = "Roommates.findByMajor", query = "SELECT r FROM Roommates r WHERE r.major = :major")
    , @NamedQuery(name = "Roommates.findByAgeMin", query = "SELECT r FROM Roommates r WHERE r.ageMin = :ageMin")
    , @NamedQuery(name = "Roommates.findByAgeMax", query = "SELECT r FROM Roommates r WHERE r.ageMax = :ageMax")
    , @NamedQuery(name = "Roommates.findByPreferredLocation", query = "SELECT r FROM Roommates r WHERE r.preferredLocation = :preferredLocation")
    , @NamedQuery(name = "Roommates.findByPetPreference", query = "SELECT r FROM Roommates r WHERE r.petPreference = :petPreference")
    , @NamedQuery(name = "Roommates.findByBudgetMin", query = "SELECT r FROM Roommates r WHERE r.budgetMin = :budgetMin")
    , @NamedQuery(name = "Roommates.findByBudgetMax", query = "SELECT r FROM Roommates r WHERE r.budgetMax = :budgetMax")
    , @NamedQuery(name = "Roommates.findByCollegeStatus", query = "SELECT r FROM Roommates r WHERE r.collegeStatus = :collegeStatus")
    , @NamedQuery(name = "Roommates.findByShopping", query = "SELECT r FROM Roommates r WHERE r.shopping = :shopping")
    , @NamedQuery(name = "Roommates.findByNumberRoommatesWanted", query = "SELECT r FROM Roommates r WHERE r.numberRoommatesWanted = :numberRoommatesWanted")})
public class Roommates implements Serializable {

     /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the Roommates table in the database.
    ========================================================
     */

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "User_Id")
    private Integer userId;
    @Size(max = 12)
    @Column(name = "First_Name")
    private String firstName;
    @Size(max = 12)
    @Column(name = "Last_Name")
    private String lastName;
    @Size(max = 25)
    @Column(name = "User_Name")
    private String userName;
    @Size(max = 25)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Age")
    private int age;
    @Size(max = 12)
    @Column(name = "Gender")
    private String gender;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 25)
    @Column(name = "Email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 25)
    @Column(name = "Phone")
    private String phone;
    @Size(max = 25)
    @Column(name = "Student_Status")
    private String studentStatus;
    @Size(max = 45)
    @Column(name = "Major")
    private String major;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Age_Min")
    private int ageMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Age_Max")
    private int ageMax;
    @Size(max = 255)
    @Column(name = "Preferred_Location")
    private String preferredLocation;
    @Column(name = "Pet_Preference")
    private Boolean petPreference;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Budget_Min")
    private int budgetMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Budget_Max")
    private int budgetMax;
    @Size(max = 12)
    @Column(name = "College_Status")
    private String collegeStatus;
    @Size(max = 5)
    @Column(name = "Shopping")
    private String shopping;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_roommates_wanted")
    private int numberRoommatesWanted;

    /*
    ===============================================================
    Class constructors for instantiating a Roommates entity object to
    represent a row in the Roommates table in the database.
    ===============================================================
     */

    public Roommates() {
    }

    public Roommates(Integer userId) {
        this.userId = userId;
    }

    public Roommates(Integer userId, int age, int ageMin, int ageMax, int budgetMin, int budgetMax, int numberRoommatesWanted) {
        this.userId = userId;
        this.age = age;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.numberRoommatesWanted = numberRoommatesWanted;
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the table in the Roommates database.
    ======================================================
     */

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public Boolean getPetPreference() {
        return petPreference;
    }

    public void setPetPreference(Boolean petPreference) {
        this.petPreference = petPreference;
    }

    public int getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(int budgetMin) {
        this.budgetMin = budgetMin;
    }

    public int getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(int budgetMax) {
        this.budgetMax = budgetMax;
    }

    public String getCollegeStatus() {
        return collegeStatus;
    }

    public void setCollegeStatus(String collegeStatus) {
        this.collegeStatus = collegeStatus;
    }

    public String getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = shopping;
    }

    public int getNumberRoommatesWanted() {
        return numberRoommatesWanted;
    }

    public void setNumberRoommatesWanted(int numberRoommatesWanted) {
        this.numberRoommatesWanted = numberRoommatesWanted;
    }

    /*
    ================
    Instance Methods
    ================
     */
    /**
     * return Generates and returns a hash code value for the object with id
     */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roommates)) {
            return false;
        }
        Roommates other = (Roommates) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cdibeans.Roommates[ userId=" + userId + " ]";
    }
    
}
