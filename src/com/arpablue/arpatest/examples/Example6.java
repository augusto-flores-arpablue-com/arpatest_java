/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.examples;

import com.arpablue.arpatest.ArpaTest;

/**
 * It show the ScreenShot is used in the execution of a test case.
 * @author Augustio Flores
 */
public class Example6 {
    public static void main(String[] args) {
        // Creating the test plan
        if( !ArpaTest.getInstance().addTestSuit( "com.arpablue.arpatest.examples.tc.screenshottest" ) ){
            System.out.println("It is not possible load the package");
            return;
        }
        
        if( !ArpaTest.getInstance().addTestCase("FullScreenShot") ){
            System.out.println("It is not possible load the test object.");
            return;
        }
        if( !ArpaTest.getInstance().addTestCase("SectionScreenShot") ){
            System.out.println("It is not possible load the test object.");
            return;
        }
        //Executing the test plan
        if( !ArpaTest.getInstance().execute() ){
            System.out.println("It is not possible excute the Test Plan");
            return;
        }

    }
}
