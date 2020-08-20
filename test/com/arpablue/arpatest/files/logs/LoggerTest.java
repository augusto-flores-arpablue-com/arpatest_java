/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.logs;

import com.arpablue.arpatest.exception.*;
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
public class LoggerTest {
    
    public LoggerTest() {
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
     * Test of title method, of class Logger.
     */
    @Test
    public void testTitle() {
        System.out.println("title");
        String text = "This is a test line.";
        Logger instance = new Logger();
        instance.setLogFile("./test_results/targetLogFile.log");
        instance.open();
        
        instance.title(text);
        instance.area(text);
        instance.body(text);
        instance.step(text);
        instance.section(text);
        instance.action(text);
        instance.msg(text);
        instance.cmd(text);
        instance.message(text);
        instance.err(text);
        instance.mistake(text);
        instance.oops(text);
        instance.success(text);
        instance.complete(text);
        instance.finished(text);
        
        try{
            instance.pass(text);
        }catch( TestPassException ex){
            
        }catch( Exception e){
            fail("It is not a TestPassException exception.");
        }
        try{
            instance.fail(text);
        }catch( TestFailException ex){
            
        }catch( Exception e){
            fail("It is not a aa exception.");
        }
        try{
            instance.error(text);
        }catch( TestErrorException ex){
            
        }catch( Exception e){
            fail("It is not a aa exception.");
        }
        try{
            instance.block(text);
        }catch( TestBlockException ex){
            
        }catch( Exception e){
            fail("It is not a aa exception.");
        }
        try{
            instance.notApplicable(text);
        }catch( TestNotApplicableException ex){
            
        }catch( Exception e){
            fail("It is not a aa exception.");
        }
        try{
            instance.deprecated(text);
        }catch( TestDeprecatedException ex){
            
        }catch( Exception e){
            fail("It is not a aa exception.");
        }
        
        instance.close();
    }
}
