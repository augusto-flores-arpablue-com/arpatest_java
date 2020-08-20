/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.examples;

import com.arpablue.arpatest.testlib.TestCase;
import com.arpablue.arpatest.testlib.TestPlan;
import com.arpablue.arpatest.testlib.TestSuit;

/**
 * It is a demonstration that how the parameters are loaded.
 * @author user-pc
 */
public class Example2 {
    
    public static void main(String[] args) {
        
        // Create the test suit
        TestSuit testsuit = new TestSuit( "First Suit" );
        TestCase tc = new TestCaseExample2( "Test Case 1" );
        // Add the test case ojects to the test suit.
        testsuit.add( tc );
        testsuit.add( new TestCaseExample2( "Test Case 2" ) );
        testsuit.add( new TestCaseExample2( "Test Case 3" ) );
        // Create the test plan
        TestPlan testplan = new TestPlan("Target Test Plan 2");
        // Add the test suit to the test plan.
        testplan.add( testsuit );
        // Adding parameters for the execution.
        // Adding to the globals parameters
        TestPlan.setGlobalParameter("MySection.globalField", "It is GLOBAL parameter.");
        // Adding to the test plan
        testplan.setParameter("MySection.tpField", "It is in the TEST PLAN.");
        // Adding to the test plan
        testsuit.setParameter("MySection.tsField", "It is in the TEST SUIT.");
        // Adding in the test case
        tc.setParameter("Mysection.tcField", "It is in the TEST CASE");
        // execute the test suit.
        testplan.execute();
    }
    
}
/**
 * Implementation of the Test Case object.
 * @author Augusto Flores
 */
class TestCaseExample2 extends TestCase{
    public TestCaseExample2(){
        super();
    }
    public TestCaseExample2( String name){
        super( name );
    }
    /**
     * It is executed before the execution of the test case.
     */
    @Override
    protected void before() {
        msg("This is executed before the test case");
    }
    /**
     * It is executed after the execute the test case.
     */
    @Override
    protected void after() {
        this.msg("It is after the execution of the test case.");
    }
    /**
     * It is the execution of the steps of the test cases.
     */
    @Override
    protected void testRun() {
        this.step("Getting the test case field from the global parameters.");
        this.msg("Value of the field: "+this.getParameter("MySection.globalField"));
        this.step("Getting the test case field from the TEST PLAN.");
        this.msg("Value of the field: "+this.getParameter("MySection.tpField"));
        this.step("Getting the test case field from the TEST SUIT.");
        this.msg("Value of the field: "+this.getParameter("MySection.tsField"));
        this.step("Getting the test case field from the TEST CASE.");
        this.msg("Value of the field: "+this.getParameter("MySection.tcField"));
        step("Description:");
        message("The getParameter() method, load the value of the from this field exists,");
        message("this call can be for test case, suit, plan or global level.");
        message("The test case parameter only envolve to the current test case and not to other test cases.");
        message("The test suit only cover to the test cases of this suit, this test cases can read the same parameters, this is used to pass data through the test cases.");
        message("The test plan cover the test cases of different test suits that exists in the current test plan.");
        message("The global parameter can be call form any test case in any place.");
        message("The Example3 show how load INI file for each level.");
    }
    
}