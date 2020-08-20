/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

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
public class TestPlanTest {
    
    public TestPlanTest() {
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

    protected TestPlan initTestPlan(){
        TestPlan tp = new TestPlan("TargetTestPlan");
        TestSuit ts = newTestSuit("First Suit 1", false);
        tp.add(ts);
        ts = newTestSuit("First Suit 2", true);
        tp.add(ts);
        return tp;
    }
    protected TestSuit newTestSuit( String name, boolean variety ){
        TestSuit res = new TestSuit(name);
        if( !variety ){
            TestObject tc  = new TestCaseImpl("tc 6",TestBase.TEST_STATUS_PASS);
            res.add( tc );
            tc  = new TestCaseImpl("tc 5",TestBase.TEST_STATUS_PASS);
            res.add( tc );
            tc  = new TestCaseImpl("tc 4",TestBase.TEST_STATUS_PASS);
            res.add( tc );
            tc  = new TestCaseImpl("tc 3",TestBase.TEST_STATUS_PASS);
            res.add( tc );
            tc  = new TestCaseImpl("tc 2",TestBase.TEST_STATUS_PASS);
            res.add( tc );
            tc  = new TestCaseImpl("tc 1",TestBase.TEST_STATUS_PASS);
            res.add( tc );
            return res;
        }
        TestObject tc  = new TestCaseImpl("tc 6",TestBase.TEST_STATUS_PASS);
        res.add( tc );
        tc  = new TestCaseImpl("tc 5",TestBase.TEST_STATUS_FAIL);
        res.add( tc );
        tc  = new TestCaseImpl("tc 4",TestBase.TEST_STATUS_BLOCK);
        res.add( tc );
        tc  = new TestCaseImpl("tc 3",TestBase.TEST_STATUS_ERROR);
        res.add( tc );
        tc  = new TestCaseImpl("tc 2",TestBase.TEST_STATUS_DEPRECATED);
        res.add( tc );
        tc  = new TestCaseImpl("tc 1",TestBase.TEST_STATUS_NOT_APPLICABLE);
        res.add( tc );
        return res;
    }
    /**
     * Test of getType method, of class TestPlan.
     */
    @Test
    public void test_TestPlan_SimpleExecution() {
        System.out.println("------ test_TestPlan_SimpleExecution");
        TestPlan target = initTestPlan();
        target.execute();
    }
    
}
