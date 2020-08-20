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
public class TestCaseTest {
    
    public TestCaseTest() {
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
     * Test of before method, of class TestCase.
     */
    @Test
    public void test_testCase_Block() {
        System.out.println("---- test_testCase_Before");
        TestCaseImpl instance = new TestCaseImpl(TestBase.TEST_STATUS_BLOCK);
        instance.setName("TryThisTest_01");
        instance.execute();
        System.out.println("  -> "+instance.toString());
        System.out.println("  -> "+instance.toJSON());
    }
//    @Test
//    public void test_testCase_Deprecated() {
//        System.out.println("---- test_testCase_Deprecated");
//        TestCaseImpl instance = new TestCaseImpl(TestBase.TEST_STATUS_DEPRECATED);
//        instance.setName("TryThisTest_02");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//    }
//    @Test
//    public void test_testCase_Error() {
//        System.out.println("---- test_testCase_Error");
//        TestCaseImpl instance = new TestCaseImpl(TestBase.TEST_STATUS_ERROR);
//        instance.setName("TryThisTest_03");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//    }
//    @Test
//    public void test_testCase_Fail() {
//        System.out.println("---- test_testCase_Fail");
//        TestCaseImpl instance = new TestCaseImpl(TestBase.TEST_STATUS_FAIL);
//        instance.setName("TryThisTest_04");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//    }
//    @Test
//    public void test_testCase_Pass() {
//        System.out.println("---- test_testCase_Pass");
//        TestCaseImpl instance = new TestCaseImpl(TestBase.TEST_STATUS_PASS);
//        instance.setName("TryThisTest_05");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//    }
//    @Test
//    public void test_testCase_NotApplicable() {
//        System.out.println("---- test_testCase_NotApplicable");
//        TestCaseImpl instance = new TestCaseImpl(TestBase.TEST_STATUS_NOT_APPLICABLE);
//        instance.setName("TryThisTest_06");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//    }
//    @Test
//    public void test_testCase_AnyError() {
//        System.out.println("---- test_testCase_AnyError");
//        TestCaseImpl instance = new TestCaseImpl(-1);
//        instance.setName("TryThisTest_07");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//    }
//    @Test
//    public void test_testCase_WithoutException() {
//        int status = TestBase.TEST_STATUS_PASS;
//        System.out.println("---- test_testCase_WithoutException");
//        TestCaseImpl instance = new TestCaseImpl(-2);
//        instance.setName("TryThisTest_08");
//        instance.execute();
//        System.out.println("  -> "+instance.toString());
//        if( instance.getStatusCode() != status ){
//            fail("The status");
//        }
//    }
    
    public class TestCaseImpl extends TestCase{
        protected int mStated;
        protected float mSeconds = 1.5f;
        public TestCaseImpl(int state){
            mTestStatus = state;
        }
        public void setWaitingTime( float seconds ){
            mSeconds = seconds;
        }
        public float getWaitingTime(){
            return mSeconds;
        }
        @Override
        protected void before() {}

        @Override
        protected void after() {}
        
        @Override
        protected void testRun() {
            float wait = this.getWaitingTime();
            System.out.println("      Waiting " + wait + " seconds.");
            TimeTool.sleep( wait );
            if( mTestStatus == TestBase.TEST_STATUS_BLOCK){
                block("This test case is blocked.");
                return;
            }
            if( mTestStatus == TestBase.TEST_STATUS_DEPRECATED){
                deprecated("This test case is blocked.");
                return;
            }
            if( mTestStatus == TestBase.TEST_STATUS_ERROR){
                error("This test case is blocked.");
                return;
            }
            if( mTestStatus == TestBase.TEST_STATUS_FAIL){
                fail("This test case is blocked.");
                return;
            }
            if( mTestStatus == TestBase.TEST_STATUS_PASS){
                pass("This test case is blocked.");
                return;
            }
            if( mTestStatus == TestBase.TEST_STATUS_NOT_APPLICABLE){
                this.notApplicable("This test case is blocked.");
                return;
            }
            if( mTestStatus == -1){
                launchError();
            }
            
        }
        protected void launchError(){
            throw new RuntimeException("This is a TEST EXCEPTION, thanks");
        }
    }

 }
