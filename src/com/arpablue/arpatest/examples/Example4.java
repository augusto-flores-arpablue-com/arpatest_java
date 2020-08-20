/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.examples;

import com.arpablue.arpatest.ArpaTest;

/**
 * It is a example that how the test case are add
 * @author user-pc
 */
public class Example4 {
    public static void main(String[] args) {
        // Creating the test plan
        if( !ArpaTest.getInstance().addTestSuit( "MyCustomNameTestSuit:com.arpablue.arpatest.examples.tc.parameters" ) ){
            System.out.println("It is not possible load the package");
            return;
        }
        
        if( !ArpaTest.getInstance().addTestCase("MycustomName1:TcParametersFile") ){
            System.out.println("It is not possible load the test object.");
            return;
        }
        if( !ArpaTest.getInstance().addTestCase("MycustomName2:TcParametersComd") ){
            System.out.println("It is not possible load the test object.");
            return;
        }
        if( !ArpaTest.getInstance().addTestCase("MycustomName3:TcParametersDirect") ){
            System.out.println("It is not possible load the test object.");
            return;
        }

        
        if( !ArpaTest.getInstance().addTestSuit( "com.arpablue.arpatest.examples.tc.colors" ) ){
            System.out.println("It is not possible load the package");
            return;
        }
        
        if( !ArpaTest.getInstance().addTestCase("Color2") ){
            System.out.println("It is not possible load the test object.");
            return;
        }
        if( !ArpaTest.getInstance().addTestCase("Color1") ){
            System.out.println("It is not possible load the test object.");
            return;
        }
        if( !ArpaTest.getInstance().addTestCase("Color3") ){
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
