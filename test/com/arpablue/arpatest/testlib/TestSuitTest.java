/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.tools.TimeTool;
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
public class TestSuitTest {
    
    public TestSuitTest() {
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
     * Test of add method, of class TestSuit.
     */
    @Test
    public void testSuit_empty() {
        System.out.println("----- testSuit_empty");
        TestObject target = null;
        TestSuit ins = new TestSuit();
        ins.setName("Test suit 00");
        //ins.add(target);
        ins.execute();
        
        System.out.println(ins.toString());
        

    }
    /**
     * Test of add method, of class TestSuit.
     */
    @Test
    public void testSuit_WithTestBase() {
        System.out.println("----- testSuit_WithTestBase");
        TestObject target = null;
        TestSuit ins = new TestSuit();
        ins.setName("Test suit 01");
        
        TestObject tc  = new TestCaseImpl("tc 6",TestBase.TEST_STATUS_PASS);
        ins.add( tc );
        
        tc  = new TestCaseImpl("tc 5",TestBase.TEST_STATUS_FAIL);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 4",TestBase.TEST_STATUS_BLOCK);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 3",TestBase.TEST_STATUS_ERROR);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 2",TestBase.TEST_STATUS_DEPRECATED);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 1",TestBase.TEST_STATUS_NOT_APPLICABLE);
        ins.add( tc );


        ins.execute();
        
        
        System.out.println(ins.toString());
        

    }
    /**
     * Test of add method, of class TestSuit.
     */
    @Test
    public void testSuit_WithTestPass() {
        System.out.println("----- testSuit_WithTestPass");
        TestObject target = null;
        TestSuit ins = new TestSuit();
        ins.setName("Test suit 02");
        
        TestObject tc  = new TestCaseImpl("tc 6",TestBase.TEST_STATUS_PASS);
        ins.add( tc );
        
        tc  = new TestCaseImpl("tc 5",TestBase.TEST_STATUS_PASS);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 4",TestBase.TEST_STATUS_PASS);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 3",TestBase.TEST_STATUS_PASS);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 2",TestBase.TEST_STATUS_PASS);
        ins.add( tc );
        tc  = new TestCaseImpl("tc 1",TestBase.TEST_STATUS_PASS);
        ins.add( tc );


        ins.execute();
        
        
        System.out.println(ins.toString());
        System.out.println(ins.toJSON());
        

    }


    
}
