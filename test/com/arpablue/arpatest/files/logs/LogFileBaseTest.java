/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.logs;

import junit.framework.Assert;
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
public class LogFileBaseTest {
    
    public LogFileBaseTest() {
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
     * Test of setFileName method, of class LogFileBase.
     */
    @Test
    public void testLogFileBase_setFileName() {
        System.out.println("--------- testLogFileBase_setFileName");
        String fileName = "fileName.log";
        String exp = "fileName.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals(exp, instance.toString());
    }

    /**
     * Test of setFileName method, of class LogFileBase.
     */
    @Test
    public void testLogFileBase_setFileName_withNullValue() {
        System.out.println("--------- testLogFileBase_setFileName_withNullValue");
        String fileName = null;
        String exp = "activity.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals(exp, instance.toString());
    }

    /**
     * Test of setFileName method, of class LogFileBase.
     */
    @Test
    public void testLogFileBase_setFileName_withEmptyValue() {
        System.out.println("--------- testLogFileBase_setFileName_withEmptyValue");
        String fileName = "";
        String exp = "activity.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals(exp, instance.toString());
    }

    /**
     * Test of setFileName method, of class LogFileBase.
     */
    @Test
    public void testLogFileBase_withBlanckValue() {
        System.out.println("--------- testLogFileBase_withBlanckValue");
        String fileName = "      ";
        String exp = "activity.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals(exp, instance.toString());
    }
    @Test
    public void testLogFileBase__fileName_withSlash() {
        System.out.println("--------- testLogFileBase__fileName_withSlash");
        String fileName = "/fileName.log";
        String exp = "fileName.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals(exp, instance.toString());
    }
    @Test
    public void testLogFileBase__fileName_withEndSlash() {
        System.out.println("--------- testLogFileBase__fileName_withEndSlash");
        String fileName = "fileName.log/";
        String exp = "fileName.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals(exp, instance.toString());
    }
    @Test
    public void testLogFileBase__fileName_doubleEndSlash() {
        System.out.println("--------- testLogFileBase__fileName_doubleEndSlash");
        String fileName = "/fileName.log/";
        String exp = "fileName.log";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setFileName(fileName);
        assertEquals(exp, instance.getFileName());
        assertEquals("fileName.log", instance.toString());
    }
    /**
     * Test of setFileName method, of class LogFileBase.
     */
    @Test
    public void testLogFileBase_SetDirectory() {
        System.out.println("--------- testLogFileBase_SetDirectory");
        String target = "folderTest";
        String exp = "folderTest/";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
        assertEquals(exp, instance.getDirectory());
        assertEquals("folderTest/activity.log", instance.toString());
    }
    @Test
    public void testLogFileBase_folderName_withSlash() {
        System.out.println("--------- testLogFileBase_folderName_withSlash");
        String target = "/testFolder";
        String exp = "testFolder/";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
            
        assertEquals(exp, instance.getDirectory());
        assertEquals("testFolder/activity.log", instance.toString());
    }
    @Test
    public void testLogFileBase_folderName_withEndSlash() {
        System.out.println("--------- testLogFileBase_folderName_withEndSlash");
        String target = "testFolder/";
        String exp = "testFolder/";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
        assertEquals(exp, instance.getDirectory());
        assertEquals("testFolder/activity.log", instance.toString());
    }
    @Test
    public void testLogFileBase_folderName_doubleEndSlash() {
        System.out.println("--------- testLogFileBase_folderName_doubleEndSlash");
        String target = "/testFolder/";
        String exp = "testFolder/";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
        assertEquals("testFolder/", instance.getDirectory());
        assertEquals("testFolder/activity.log", instance.toString());
    }
    @Test
    public void testLogFileBase_folderName_withNullValue(){
        System.out.println("--------- testLogFileBase_folderName_withNullValue");
        String target = null;
        String exp = "";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
        assertEquals(exp, instance.getDirectory());
        assertEquals("activity.log", instance.toString());
    }
    @Test
    public void testLogFileBase_folderName_withEmptyValue(){
        System.out.println("--------- testLogFileBase_folderName_withEmptyValue");
        String target = "";
        String exp = "";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
        assertEquals("", instance.getDirectory());
        assertEquals("activity.log", instance.toString());
    }
    @Test
    public void testLogFileBase_folderName_withBlankValue(){
        System.out.println("--------- testLogFileBase_folderName_withBlankValue");
        String target = "       ";
        String exp = "";
        LogFileBase instance = new LogFileBaseImpl();
        instance.setDirectory( target );
        assertEquals(exp, instance.getDirectory());
        assertEquals("activity.log", instance.toString());
    }

    public class LogFileBaseImpl extends LogFileBase {

        public boolean write(String text) {
            return true;
        }
    }
    
}
