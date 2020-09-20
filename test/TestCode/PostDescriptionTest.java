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
import sfac.PostDescriptionController;

/**
 *
 * @author Faizun
 */
public class PostDescriptionTest {
    
    PostDescriptionController pdc;
    
    public PostDescriptionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pdc = new PostDescriptionController();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testCheckOrganizationName() {
        assertEquals(false, pdc.checkOrganizationName("A"));   
    
    }
    @Test
    public void testCheckPosition(){
        assertEquals(false, pdc.checkPosition("B"));
        
    }

    @Test
    public void testCheckDescription(){
        assertEquals(false, pdc.checkDescription("ABC"));
    }
    
}