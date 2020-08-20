/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.exception;

import com.arpablue.arpatest.testlib.TestObject;

/**
 *
 * @author user-pc
 */
public class TestPassException extends TestException{
    public TestPassException(String message, Throwable cause) {
        super(message, cause);
    }
    public TestPassException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return TestObject.TEST_STATUS_PASS;
    }
    
    
}
