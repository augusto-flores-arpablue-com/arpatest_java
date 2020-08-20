/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.examples.tc.screenshottest;

import com.arpablue.arpatest.testlib.TestCase;
import java.awt.image.BufferedImage;

/**
 *
 * @author user-pc
 */
public class SectionScreenShot extends TestCase{

    @Override
    protected void before() {}

    @Override
    protected void after() {}

    @Override
    protected void testRun() {
        step("Taking a screenshot of all screen.");
        BufferedImage img = TestCase.screenShot(200,200,400,400);
        if( img == null ){
            fail("It is not possible take an screenshot of a section of the screen.");
        }
        step("Saving the image.");
        if(!TestCase.saveImage(img, "Example6SubImage")){
            fail("It is not possible save the image.");
        }
        pass("The image has been saved successfully.");
    }
    
}
