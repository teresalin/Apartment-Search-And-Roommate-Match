/*
 * Created by Teresa Lin on 2017.05.01  * 
 * Copyright © 2017 Osman Balci. All rights reserved. * 
 */
package com.mycompany.managers;

import com.mycompany.roommates.Roommates;
import com.mycompany.managers.Constants;

import com.mycompany.roommates.RoommatesFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

/*
---------------------------------------------------------------------------
JSF Managed Beans annotated with @ManagedBean from javax.faces.bean
is in the process of being deprecated in favor of CDI Beans. Therefore, 
you should use @Named from javax.inject package to designate a managed
bean as a JSF controller class. Contexts and Dependency Injection (CDI) 
beans annotated with @Named is the preferred approach, because CDI 
enables Java EE-wide dependency injection. CDI bean is a bean managed
by the CDI container. 

Within JSF XHTML pages, this bean will be referenced by using the name
'accountManager'. Actually, the default name is the class name starting
with a lower case letter and value = 'accountManager' is optional;
However, we spell it out to make our code more readable and understandable.
---------------------------------------------------------------------------
 */
@Named(value = "accountManager")

/*
The @SessionScoped annotation preserves the values of the AccountManager
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped

/**
 *
 * @author Teresa Lin
 */
/*
--------------------------------------------------------------------------
Marking the AccountManager class as "implements Serializable" implies that
instances of the class can be automatically serialized and deserialized. 

Serialization is the process of converting a class instance (object)
from memory into a suitable format for storage in a file or memory buffer, 
or for transmission across a network connection link.

Deserialization is the process of recreating a class instance (object)
in memory from the format under which it was stored.
--------------------------------------------------------------------------
 */
public class AccountManager implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String username;
    private String password;
    private String newPassword;

    private String firstName;
    private String lastName;
    
    private int age;
    private String gender;
    private String phone;
    private String student_status;
    private String major;
    private int age_min;
    private int age_max;
    private String preferred_location;
    private Boolean pet_preference;
    private int budget_min;
    private int budget_max;
    private String college_status;
    private String shopping;
    private int number_roommates_wanted;

    private String email;

    private String statusMessage;

    private Roommates selected;

    /*
    The instance variable 'userFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference
    of the UserFacade object, after it is instantiated at runtime, into the instance variable 'userFacade'.
     */
    @EJB
    private RoommatesFacade roommatesFacade;

    // Constructor method instantiating an instance of AccountManager
    public AccountManager() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudent_status() {
        return student_status;
    }

    public void setStudent_status(String student_status) {
        this.student_status = student_status;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public String getPreferred_location() {
        return preferred_location;
    }

    public void setPreferred_location(String preferred_location) {
        this.preferred_location = preferred_location;
    }

    public Boolean getPet_preference() {
        return pet_preference;
    }

    public void setPet_preference(Boolean pet_preference) {
        this.pet_preference = pet_preference;
    }

    public int getBudget_min() {
        return budget_min;
    }

    public void setBudget_min(int budget_min) {
        this.budget_min = budget_min;
    }

    public int getBudget_max() {
        return budget_max;
    }

    public void setBudget_max(int budget_max) {
        this.budget_max = budget_max;
    }

    public String getCollege_status() {
        return college_status;
    }

    public void setCollege_status(String college_status) {
        this.college_status = college_status;
    }

    public String getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = shopping;
    }

    public int getNumber_roommates_wanted() {
        return number_roommates_wanted;
    }

    public void setNumber_roommates_wanted(int number_roommates_wanted) {
        this.number_roommates_wanted = number_roommates_wanted;
    }
    
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public RoommatesFacade getRoommatesFacade() {
        return roommatesFacade;
    } 

    public Roommates getSelected() {

        if (selected == null) {
            /*
            user_id (database primary key) was put into the SessionMap
            in the initializeSessionMap() method below or in LoginManager.
             */
            int userPrimaryKey = (int) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("user_id");
            /*
            Given the primary key, obtain the object reference of the User
            object and store it into the instance variable selected.
             */
            selected = getRoommatesFacade().find(userPrimaryKey);
        }
        // Return the object reference of the selected User object
        return selected;
    }

    public void setSelected(Roommates selectedUser) {
        this.selected = selectedUser;
    }

    /*
    ================
    Instance Methods
    ================
     */
    // Return True if a user is logged in; otherwise, return False
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username") != null;
    }

    /*
    Create a new new user account. Return "" if an error occurs; otherwise,
    upon successful account creation, redirect to show the SignIn page.
     */
    public String createAccount() {
        
        // First, check if the entered username is already being used

        // Obtain the object reference of a User object with username
        Roommates aUser = getRoommatesFacade().findByUsername(username);

        if (aUser != null) {
            // A user already exists with the username entered
            username = "";
            statusMessage = "Username already exists! Please select a different one!";
            return "";
        }
        
        // The entered username is available

        if (statusMessage == null || statusMessage.isEmpty()) {
            try {
                // Instantiate a new User object
                Roommates newUser = new Roommates();

                /*
                Set the properties of the newly created newUser object with the values
                entered by the user in the AccountCreationForm in CreateAccount.xhtml
                */
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setUserName(username);
                newUser.setPassword(password);
                newUser.setAge(age);
                newUser.setAgeMax(age_max);
                newUser.setAgeMin(age_min);
                newUser.setBudgetMax(budget_max);
                newUser.setBudgetMin(budget_min);
                newUser.setCollegeStatus(college_status);
                newUser.setGender(gender);
                newUser.setMajor(major);
                newUser.setNumberRoommatesWanted(number_roommates_wanted);
                newUser.setPetPreference(pet_preference);
                newUser.setPhone(phone);
                newUser.setPreferredLocation(preferred_location);
                newUser.setShopping(shopping);
                newUser.setStudentStatus(student_status);
                getRoommatesFacade().create(newUser);
//                Roommates r = new Roommates();
//                r.setUserId(351);
//                r.setAge(45);
//                r.setAgeMin(10);
//                r.setNumberRoommatesWanted(2);
//                r.setAgeMax(50);
//                r.setBudgetMin(100);
//                r.setBudgetMax(1000);
//                getRoommatesFacade().create(r);

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while creating user's account! See: " + e.getMessage();
                return "";
            }
            // Initialize the session map for the newly created User object
            initializeSessionMap();

            /*
            The Profile page cannot be shown since the new User has not signed in yet.
            Therefore, we show the Sign In page for the new User to sign in first.
             */
            return "SignIn.xhtml?faces-redirect=true";
        }
        return "";
    }

    /*
    Update the signed-in user's account profile. Return "" if an error occurs;
    otherwise, upon successful account update, redirect to show the Profile page.
     */
    public String updateAccount() {

        if (statusMessage == null || statusMessage.isEmpty()) {

            // Obtain the signed-in user's username
            String user_name = (String) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("username");

            // Obtain the object reference of the signed-in user
            Roommates editUser = getRoommatesFacade().findByUsername(user_name);

            try {
                /*
                Set the signed-in user's properties to the values entered by
                the user in the EditAccountProfileForm in EditAccount.xhtml.
                */
                editUser.setFirstName(this.selected.getFirstName());
                editUser.setLastName(this.selected.getLastName());

                editUser.setEmail(this.selected.getEmail());

                // It is optional for the user to change his/her password
                String new_Password = getNewPassword();

                if (new_Password == null || new_Password.isEmpty()) {
                    // Do nothing. The user does not want to change the password.
                } else {
                    editUser.setPassword(new_Password);
                    // Password changed successfully!
                    // Password was first validated by invoking the validatePasswordChange method below.
                }

                // Store the changes in the RoommatesDB database
                getRoommatesFacade().edit(editUser);

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while editing user's profile! See: " + e.getMessage();
                return "";
            }
            // Account update is completed, redirect to show the Profile page.
            return "Profile.xhtml?faces-redirect=true";
        }
        return "";
    }

    /*
    Delete the signed-in user's account. Return "" if an error occurs; otherwise,
    upon successful account deletion, redirect to show the index (home) page.
     */
    public String deleteAccount() {

        if (statusMessage == null || statusMessage.isEmpty()) {

            // Obtain the database primary key of the signed-in User object
            int user_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");

            try {

                // Delete the User entity, whose primary key is user_id, from the CloudDriveDB database
                getRoommatesFacade().deleteUser(user_id);

                statusMessage = "Your account is successfully deleted!";

            } catch (EJBException e) {
                username = "";
                statusMessage = "Something went wrong while deleting user's account! See: " + e.getMessage();
                return "";
            }

            logout();
            return "index.xhtml?faces-redirect=true";
        }
        return "";
    }

    // Validate if the entered password matches the entered confirm password
    public void validateInformation(ComponentSystemEvent event) {

        /*
        FacesContext contains all of the per-request state information related to the processing of
        a single JavaServer Faces request, and the rendering of the corresponding response.
        It is passed to, and potentially modified by, each phase of the request processing lifecycle.
         */
        FacesContext fc = FacesContext.getCurrentInstance();

        /*
        UIComponent is the base class for all user interface components in JavaServer Faces. 
        The set of UIComponent instances associated with a particular request and response are organized into
        a component tree under a UIViewRoot that represents the entire content of the request or response.
         */
        // Obtain the UIComponent instances associated with the event
        UIComponent components = event.getComponent();

        /*
        UIInput is a kind of UIComponent for the user to enter a value in.
         */
        // Obtain the object reference of the UIInput field with id="password" on the UI
        UIInput uiInputPassword = (UIInput) components.findComponent("password");

        // Obtain the password entered in the UIInput field with id="password" on the UI
        String entered_password = uiInputPassword.getLocalValue()
                == null ? "" : uiInputPassword.getLocalValue().toString();

        // Obtain the object reference of the UIInput field with id="confirmPassword" on the UI
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");

        // Obtain the confirm password entered in the UIInput field with id="confirmPassword" on the UI
        String entered_confirm_password = uiInputConfirmPassword.getLocalValue()
                == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        if (entered_password.isEmpty() || entered_confirm_password.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }

        if (!entered_password.equals(entered_confirm_password)) {
            statusMessage = "Password and Confirm Password must match!";
        } else {
            statusMessage = "";
        }
    }

    // Validate the new password and new confirm password
    public void validatePasswordChange(ComponentSystemEvent event) {
        /*
        FacesContext contains all of the per-request state information related to the processing of
        a single JavaServer Faces request, and the rendering of the corresponding response.
        It is passed to, and potentially modified by, each phase of the request processing lifecycle.
         */
        FacesContext fc = FacesContext.getCurrentInstance();

        /*
        UIComponent is the base class for all user interface components in JavaServer Faces. 
        The set of UIComponent instances associated with a particular request and response are organized into
        a component tree under a UIViewRoot that represents the entire content of the request or response.
         */
        // Obtain the UIComponent instances associated with the event
        UIComponent components = event.getComponent();

        /*
        UIInput is a kind of UIComponent for the user to enter a value in.
         */
        // Obtain the object reference of the UIInput field with id="newPassword" on the UI
        UIInput uiInputPassword = (UIInput) components.findComponent("newPassword");

        // Obtain the new password entered in the UIInput field with id="newPassword" on the UI
        String new_Password = uiInputPassword.getLocalValue()
                == null ? "" : uiInputPassword.getLocalValue().toString();

        // Obtain the object reference of the UIInput field with id="newConfirmPassword" on the UI
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("newConfirmPassword");

        // Obtain the new confirm password entered in the UIInput field with id="newConfirmPassword" on the UI
        String new_ConfirmPassword = uiInputConfirmPassword.getLocalValue()
                == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        // It is optional for the user to change his/her password
        if (new_Password.isEmpty() || new_ConfirmPassword.isEmpty()) {
            // Do nothing. The user does not want to change the password.
            return;
        }

        if (!new_Password.equals(new_ConfirmPassword)) {
            statusMessage = "New Password and New Confirm Password must match!";
        } else {
            /*
            REGular EXpression (regex) for validating password strength:
            (?=.{8,31})        ==> Validate the password to be minimum 8 and maximum 31 characters long. 
            (?=.*[!@#$%^&*()]) ==> Validate the password to contain at least one special character. 
                                   (all special characters of the number keys from 1 to 0 on the keyboard)
            (?=.*[A-Z])        ==> Validate the password to contain at least one uppercase letter. 
            (?=.*[a-z])        ==> Validate the password to contain at least one lowercase letter. 
            (?=.*[0-9])        ==> Validate the password to contain at least one number from 0 to 9.
             */
            String regex = "^(?=.{8,31})(?=.*[!@#$%^&*()])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$";

            if (!new_Password.matches(regex)) {
                statusMessage = "The password must be minimum 8 "
                        + "characters long, contain at least one special character, "
                        + "contain at least one uppercase letter, "
                        + "contain at least one lowercase letter, "
                        + "and contain at least one number 0 to 9.";
            } else {
                statusMessage = "";
            }
        }
    }

    // Validate if the entered password and confirm password are correct
    public void validateUserPassword(ComponentSystemEvent event) {
        /*
        FacesContext contains all of the per-request state information related to the processing of
        a single JavaServer Faces request, and the rendering of the corresponding response.
        It is passed to, and potentially modified by, each phase of the request processing lifecycle.
         */
        FacesContext fc = FacesContext.getCurrentInstance();

        /*
        UIComponent is the base class for all user interface components in JavaServer Faces. 
        The set of UIComponent instances associated with a particular request and response are organized into
        a component tree under a UIViewRoot that represents the entire content of the request or response.
         */
        // Obtain the UIComponent instances associated with the event
        UIComponent components = event.getComponent();

        /*
        UIInput is a kind of UIComponent for the user to enter a value in.
         */
        // Obtain the object reference of the UIInput field with id="password" on the UI
        UIInput uiInputPassword = (UIInput) components.findComponent("password");

        // Obtain the password entered in the UIInput field with id="password" on the UI
        String entered_password = uiInputPassword.getLocalValue()
                == null ? "" : uiInputPassword.getLocalValue().toString();

        // Obtain the object reference of the UIInput field with id="confirmPassword" on the UI
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");

        // Obtain the confirm password entered in the UIInput field with id="confirmPassword" on the UI
        String entered_confirm_password = uiInputConfirmPassword.getLocalValue()
                == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        if (entered_password.isEmpty() || entered_confirm_password.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }

        if (!entered_password.equals(entered_confirm_password)) {
            statusMessage = "Password and Confirm Password must match!";
        } else {
            // Obtain the logged-in User's username
            String user_name = (String) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("username");

            // Obtain the object reference of the signed-in User object
            Roommates user = getRoommatesFacade().findByUsername(user_name);

            if (entered_password.equals(user.getPassword())) {
                // entered password = signed-in user's password
                statusMessage = "";
            } else {
                statusMessage = "Incorrect Password!";
            }
        }
    }

    // Initialize the session map for the User object
    public void initializeSessionMap() {

        // Obtain the object reference of the User object
        Roommates user = getRoommatesFacade().findByUsername(getUsername());
        if (user == null) {
            return;
        }

        // Put the User's object reference into session map variable user
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("user", user);

        // Put the User's database primary key into session map variable user_id
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().put("user_id", user.getUserId());
    }

    /*
    UIComponent is the base class for all user interface components in JavaServer Faces. 
    The set of UIComponent instances associated with a particular request and response are organized into
    a component tree under a UIViewRoot that represents the entire content of the request or response.
     
    @param components: UIComponent instances associated with the current request and response
    @return True if entered password is correct; otherwise, return False
     */
    private boolean correctPasswordEntered(UIComponent components) {

        // Obtain the object reference of the UIInput field with id="verifyPassword" on the UI
        UIInput uiInputVerifyPassword = (UIInput) components.findComponent("verifyPassword");

        // Obtain the verify password entered in the UIInput field with id="verifyPassword" on the UI
        String verifyPassword = uiInputVerifyPassword.getLocalValue()
                == null ? "" : uiInputVerifyPassword.getLocalValue().toString();

        if (verifyPassword.isEmpty()) {
            statusMessage = "Please enter a password!";
            return false;

        } else if (verifyPassword.equals(password)) {
            // Correct password is entered
            return true;

        } else {
            statusMessage = "Invalid password entered!";
            return false;
        }
    }

    // Show the Home page
    public String showHomePage() {
        return "index?faces-redirect=true";
    }

    // Show the Profile page
    public String showProfile() {
        return "Profile?faces-redirect=true";
    }

    public String logout() {

        // Clear the logged-in User's session map
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        // Reset the logged-in User's properties
        username = password = "";
        firstName = lastName = "";
        email = statusMessage = "";

        // Invalidate the logged-in User's session
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Redirect to show the index (Home) page
        return "index.xhtml?faces-redirect=true";
    }
}
