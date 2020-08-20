/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.examples;

import com.arpablue.arpatest.ArpaTest;

/**
 *
 * @author user-pc
 */
public class Example5 {
    public static void main(String[] args) {
        ArpaTest.setTestPlanFile("testData\\testPlan.test");
        ArpaTest.execute();
    }
    
}
