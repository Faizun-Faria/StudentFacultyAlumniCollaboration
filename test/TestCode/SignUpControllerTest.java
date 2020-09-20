/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestCode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import sfac.SignUpController;

import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

/**
 *
 * @author Faizun
 */
public class SignUpControllerTest {
    SignUpController sc;
    
    public SignUpControllerTest() {
    }
    @BeforeClass
    public static void setUpClass() {
    }    
    @AfterClass
    public static void tearDownClass() {
    }
    @Before
    public void setUp() {
    
    }
    
    @After
    public void tearDown() {
    }
    
    /* 
    In order to check, uncomment each method and check one at once.    
    */

    
    /*
    @Test 
    public void TestCheckType() {
        sc = spy(new SignUpController());
        doNothing().when(sc).showError("Choose type");
        assertEquals(true,sc.checkType());
    }
    
    */
    
    
    @Test 
    public void TestCheckFirstName() {
        sc = spy(new SignUpController());
        doNothing().when(sc).showError("Enter your first name");
        assertEquals(true,sc.checkFirstName(""));
    }
    
    
    /*
    @Test 
    public void TestCheckLastName() {
        doNothing().when(sc).showError("Enter your last name");
        assertEquals(true,sc.checkLastName(""));
    }
    */
    
    /*
    @Test 
    public void TestCheckPassword() {
        doNothing().when(sc).showError("Choose type");
        assertEquals(true,sc.checkPassword(""));
    }
    */
    
    /*
    @Test 
    public void TestCheckConfirmPassword() {
        doNothing().when(sc).showError("Choose type");
        assertEquals(true,sc.checkConfirmPassword(""));
    }
    */

}
