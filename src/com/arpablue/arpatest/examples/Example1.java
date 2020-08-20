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
 * It is an example that show how build a simple test plan
 * @author Augusto Flores
 */
public class Example1 {
    
    public static void main(String[] args) {
        
        // Create the test suit
        TestSuit testsuit = new TestSuit( "First Suit" );
        // Add the test case ojects to the test suit.
        testsuit.add( new TestCaseExample1( "Test Case 1" ) );
        testsuit.add( new TestCaseExample1( "Test Case 2" ) );
        testsuit.add( new TestCaseExample1( "Test Case 3" ) );
        // Create the test plan
        TestPlan testplan = new TestPlan("Target Test Plan");
        // Add the test suit to the test plan.
        testplan.add( testsuit );
        // execute the test suit.
        testplan.execute();
    }
    
}
/**
 * Implementation of the Test Case object.
 * @author Augusto Flores
 */
class TestCaseExample1 extends TestCase{
    public TestCaseExample1(){
        super();
    }
    public TestCaseExample1( String name){
        super( name );
    }
    /**
     * It is executed before the execution of the test case.
     */
    @Override
    protected void before() {
        this.msg("It is before the execution of the test case.");
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
        this.msg("It is during the execution of the test case.");
    }
    
}