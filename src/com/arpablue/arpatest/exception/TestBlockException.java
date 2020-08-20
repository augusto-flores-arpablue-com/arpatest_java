/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.exception;

import com.arpablue.arpatest.testlib.TestBase;

/**
 *
 * @author user-pc
 */
public class TestBlockException extends TestException{
        public TestBlockException(String message, Throwable cause) {
		super(message, cause);

	}
        public TestBlockException(String message) {
		super(message);

	}

    @Override
    public int getExceptionCode() {
        return TestBase.TEST_STATUS_BLOCK;
    }
}
