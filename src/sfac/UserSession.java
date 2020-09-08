/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfac;

/**
 *
 * @author Faizun
 */

public class UserSession {
    String userType, email;
    static UserSession instance;
    
    public String getUserType(){
        return userType;
    }
    
    public String getEmail(){
        return email;
    }
    
    
    private UserSession( String email, String userType){
        this.userType = userType;
        this.email = email;
    }
    public static UserSession getInstance(){
        return instance;
    }
    public static UserSession getInstance(String email, String userType){
        if(instance == null)    instance = new UserSession(email, userType);
        return instance;
    }
    public void logoutSession(){
        userType = "";
        email = "";
    }
}
