/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest;

import com.arpablue.arpatest.files.inifiles.IniFile;
import com.arpablue.arpatest.files.logs.LogObject;
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
public class ArpaTestTest {
    
    public ArpaTestTest() {
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
     * Test of getInstance method, of class ArpaTest.
     */
//    @Test
    public void test_arpatest_setGetParameter() {
        String pkg = "com.arpablue.arpatest.examples.tc.parameters";
        String expName = "TryTestPlan";
        ArpaTest.getInstance().setTestPlanName( expName );
        String curName = ArpaTest.getInstance().getTestPlanName();
        System.out.println(" - test Plan name: "+curName);
        if( !expName.equalsIgnoreCase( curName )){
            fail("The name of test plan is not the expected: Exp["+expName+"] - cur["+curName+"]");
        }
        boolean flag = false;
        flag = ArpaTest.getInstance().addTestSuit( pkg+".toFail" );
        if( flag ){
            fail("The ["+pkg+".toFail] package verify that exists, when this package should not exists.");
        }
         flag = ArpaTest.getInstance().addTestSuit( pkg );
        if( !flag ){
            fail("The ["+pkg+"] package not exists.");
        }
        System.out.println("The ["+pkg+"] package exists.");
        if(!ArpaTest.getInstance().addTestCase("TcParametersFile")){
            fail("The [TcParametersFile] text case cannot be added to the test plan.");
        }
        
    }
    @Test
    public void test_arpatest_CreateAndExecute_a_TestPlan(){
        ArpaTest.setSettingsFile("config.ini");
        // Creating the test plan
        if( !ArpaTest.getInstance().addTestSuit( "com.arpablue.arpatest.examples.tc.parameters" ) ){
            fail("It is not possible load the package");
        }
        
        if( !ArpaTest.getInstance().addTestCase(":TcParametersFile") ){
            fail("It is not possible load the test objec.");
        }
        if( !ArpaTest.getInstance().addTestCase("customName:TcParametersComd") ){
            fail("It is not possible load the test objec.");
        }
        if( !ArpaTest.getInstance().addTestCase("TcParametersDirect") ){
            fail("It is not possible load the test objec.");
        }

        
        if( !ArpaTest.getInstance().addTestSuit( "com.arpablue.arpatest.examples.tc.colors" ) ){
            fail("It is not possible load the package");
        }
        
        if( !ArpaTest.getInstance().addTestCase("Color2") ){
            fail("It is not possible load the test objec.");
        }
        if( !ArpaTest.getInstance().addTestCase("RedColor:Color1") ){
            fail("It is not possible load the test objec.");
        }
        if( !ArpaTest.getInstance().addTestCase("Color3") ){
            fail("It is not possible load the test objec.");
        }
        //Executing the test plan
        if( !ArpaTest.getInstance().execute() ){
            fail("It is not possible excute the Test Plan");
        }
    }
}
