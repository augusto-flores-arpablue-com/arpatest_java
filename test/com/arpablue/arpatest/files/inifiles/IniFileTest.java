/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.inifiles;

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
public class IniFileTest {
    protected IniFile mTarget = new IniFile();
    
    public IniFileTest() {
        mTarget = new IniFile();
        mTarget.addField(null, "campoA","1");
        mTarget.addField(null, "campoB","1.2");
        mTarget.addField("", "campoC","Hell0");
        mTarget.addField("", "campoD","World");
        mTarget.addField("First", "campoA","AAAAA");
        mTarget.addField("First", "campoB","BBBBB");
        mTarget.addField("First", "campoC","CCCCC");
        mTarget.addField("First", "campoD","DDDDD");
        mTarget.addField("Second", "campoA","true");
        mTarget.addField("Second", "campoE","C");
        mTarget.addField("Second", "campoF","False");
        mTarget.addField("Second", "campoG","Green");
        mTarget.save("testSetting.ini");
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
    //@Test
    public void test_IniFile_DefaultSection(){
        System.out.println("-------- test_IniFile_DefaultSection");
        IniFile target = new IniFile();
        target.addField(null, "campoA","1");
        target.addField(null, "campoB","1.2");
        target.addField("", "campoC","Hell0");
        target.addField("", "campoD","World");
        target.addField("Second", "campoE","true");
        target.addField("Second", "campoF","C");
        target.addField("Second", "campoG","False");
        target.addField("Second", "campoH","Green");
        target.save("target.ini");
        System.out.println("-->"+target.toString());
    }
    /**
     * Test of getValue method, of class IniFile.
     */
    // @Test
    public void test_IniFile_Save() {
        System.out.println("-------- test_IniFile_Save");
        String field = "";
        IniFile target = new IniFile();
        target.addField("First", "campoA","1");
        target.addField("First", "campoB","1.2");
        target.addField("First", "campoC","Hell0");
        target.addField("First", "campoD","World");
        target.addField("Second", "campoE","true");
        target.addField("Second", "campoF","C");
        target.addField("Second", "campoG","False");
        target.addField("Second", "campoH","Green");
        System.out.println("-->"+target.toString());
        target.save("target.ini");
    }
    //@Test 
    public void test_IniFile_getSectionValue(){
        System.out.println("-------- test_IniFile_getSectionValue");
        String exp = "";
        String cur = mTarget.getValue("campoA");
        exp = "1";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("campoB");
        exp = "1.2";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("campoC");
        exp = "Hell0";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("campoD");
        exp = "World";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("First.campoA");
        exp = "AAAAA";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("First.campoB");
        exp = "BBBBB";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("First.campoC");
        exp = "CCCCC";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("First.campoD");
        exp = "DDDDD";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("Second.campoA");
        exp = "true";
        assertEquals(cur, exp);
        cur = mTarget.getValue("Second.campoE");
        exp = "C";
        assertEquals(cur, exp);
        
        cur = mTarget.getValue("Second.campoF");
        exp = "False";
        assertEquals(cur, exp);

    }
    @Test
    public void test_IniFile_ParameterExists1(){
        System.out.println("--------- test_IniFile_ParameterExists1");
        if( !mTarget.fieldExists("", "campoA") ){
            fail("It is not possible detect the campoA field.");
        }
        if( !mTarget.fieldExists("", "campoB") ){
            fail("It is not possible detect the campoB field.");
        }
        if( !mTarget.fieldExists(null, "campoC") ){
            fail("It is not possible detect the campoC field.");
        }
        if( !mTarget.fieldExists(null, "campoD") ){
            fail("It is not possible detect the campoD field.");
        }
        if( !mTarget.fieldExists("first", "campoA") ){
            fail("It is not possible detect the first.campoA field.");
        }
        if( !mTarget.fieldExists("first", "campoB") ){
            fail("It is not possible detect the first.campoB field.");
        }
        if( !mTarget.fieldExists("first", "campoC") ){
            fail("It is not possible detect the first.campoC field.");
        }
        if( !mTarget.fieldExists("first", "campoD") ){
            fail("It is not possible detect the first.campoD field.");
        }
        if( !mTarget.fieldExists("Second", "campoA") ){
            fail("It is not possible detect the Second.campoA field.");
        }
        if( !mTarget.fieldExists("Second", "campoE") ){
            fail("It is not possible detect the Second.campoE field.");
        }
        if( !mTarget.fieldExists("Second", "campoF") ){
            fail("It is not possible detect the Second.campoF field.");
        }
        if( !mTarget.fieldExists("Second", "campoG") ){
            fail("It is not possible detect the Second.campoG field.");
        }
    }
    @Test
    public void test_IniFile_ParameterExists2(){
        System.out.println("--------- test_IniFile_ParameterExists2");
        if( !mTarget.fieldExists("campoA") ){
            fail("It is not possible detect the first.campoA field.");
        }
        if( !mTarget.fieldExists("campoB") ){
            fail("It is not possible detect the first.campoB field.");
        }
        if( !mTarget.fieldExists("campoC") ){
            fail("It is not possible detect the first.campoC field.");
        }
        if( !mTarget.fieldExists("campoD") ){
            fail("It is not possible detect the first.campoD field.");
        }
        if( !mTarget.fieldExists("first.campoA") ){
            fail("It is not possible detect the first.campoA field.");
        }
        if( !mTarget.fieldExists("first.campoB") ){
            fail("It is not possible detect the first.campoB field.");
        }
        if( !mTarget.fieldExists("first.campoC") ){
            fail("It is not possible detect the first.campoC field.");
        }
        if( !mTarget.fieldExists("first.campoD") ){
            fail("It is not possible detect the first.campoD field.");
        }
        if( !mTarget.fieldExists("Second.campoA") ){
            fail("It is not possible detect the Second.campoA field.");
        }
        if( !mTarget.fieldExists("Second.campoE") ){
            fail("It is not possible detect the Second.campoE field.");
        }
        if( !mTarget.fieldExists("Second.campoF") ){
            fail("It is not possible detect the Second.campoF field.");
        }
        if( !mTarget.fieldExists("Second.campoG") ){
            fail("It is not possible detect the Second.campoG field.");
        }
    }
    @Test
    public void test_IniFile_ParameterExists3(){
        System.out.println("--------- test_IniFile_ParameterExists3");
        if( !mTarget.fieldExists("second.campoe") ){
            fail("It is not possible detect the Second.campoE field.");
        }
    }
}
