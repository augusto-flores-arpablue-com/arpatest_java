/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user-pc
 */
public class TimeToolsTest {
    
    public TimeToolsTest() {
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

    /**
     * Test of getNow method, of class TimeTool.
     */
    @Test
    public void testTimeTools_GetNow_String() {
        System.out.println("--------- testTimeTools_GetNow_String");
        String result = TimeTool.getNow();
        System.out.println("Time: "+result);
    }

    /**
     * Test of getNow method, of class TimeTool.
     */
    @Test
    public void testTimeTools_GetNow_0args() {
        System.out.println("--------- testTimeTools_GetNow_0args");
        String result = TimeTool.getNow();
        System.out.println("Time: "+result);
    }

    /**
     * Test of getCurrentDate method, of class TimeTool.
     */
    @Test
    public void testTimeTools_GetCurrentDate() {
        System.out.println("--------- testTimeTools_GetCurrentDate");
        String result = TimeTool.getCurrentDate();
        System.out.println("Time: "+result);
    }

    /**
     * Test of getCurrentHour method, of class TimeTool.
     */
    @Test
    public void testTimeTools_GetCurrentHour() {
        System.out.println("--------- testTimeTools_GetCurrentHour");
        String result = TimeTool.getCurrentHour();
        System.out.println("Time: "+result);
    }

    /**
     * Test of getNowFileFormat method, of class TimeTool.
     */
    @Test
    public void testTimeTools_GetNowFileFormat() {
        System.out.println("--------- getNowFileFormat");
        String result = TimeTool.getNowFileFormat();
        System.out.println("Time: "+result);
    }
    
}
