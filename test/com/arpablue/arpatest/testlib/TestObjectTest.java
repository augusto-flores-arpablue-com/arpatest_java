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
 * @author engau
 */
public class TestObjectTest {
    
    public TestObjectTest() {
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
     * Test of onBefore method, of class TestObject.
     */
    @Test
    public void testOnBefore() {
        System.out.println("onBefore");
        TestObject instance = new TestObjectImpl();
        instance.execute();

    }

    public class TestObjectImpl extends TestObject {
        @Override
        public void before() {
            msg("This is before the execution.");
        }
        @Override
        public void after() {
            msg("This is after the execution.");
        }
        @Override
        public void testRun() {
            msg("This is during theexecution.");
        }

        @Override
        protected void testExe() {}

        @Override
        protected String toJSON() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected String getType() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
