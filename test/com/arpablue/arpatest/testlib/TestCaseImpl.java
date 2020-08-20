/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.tools.TimeTool;

    public class TestCaseImpl extends TestCase{
        protected int mStated;
        protected float mSeconds = 1.5f;
        public TestCaseImpl(String name,int state){
            this.setName(name);
            mStated = state;
        }
        public TestCaseImpl(int state){
            mStated = state;
        }
        public void setWaitingTime( float seconds ){
            mSeconds = seconds;
        }
        public float getWaitingTime(){
            return mSeconds;
        }
        @Override
        protected void before() {}

        @Override
        protected void after() {}
        
        @Override
        protected void testRun() {
            float wait = this.getWaitingTime();
            msg("      Waiting " + wait + " seconds.");
            TimeTool.sleep( wait );
            if( mStated == TestBase.TEST_STATUS_BLOCK){
                block("This test case is BLOCKED.");
                return;
            }
            if( mStated == TestBase.TEST_STATUS_DEPRECATED){
                deprecated("This test case is DEPRECATED.");
                return;
            }
            if( mStated == TestBase.TEST_STATUS_ERROR){
                error("This test case has an ERROR.");
                return;
            }
            if( mStated == TestBase.TEST_STATUS_FAIL){
                fail("This test case is FAILED.");
                return;
            }
            if( mStated == TestBase.TEST_STATUS_PASS){
                pass("This test case is PASSED.");
                return;
            }
            if( mStated == TestBase.TEST_STATUS_NOT_APPLICABLE){
                this.notApplicable("This test case is NOT APLICABLE.");
                return;
            }
            if( mStated == -1){
                launchError();
            }
            
        }
        protected void launchError(){
            throw new RuntimeException("This is a TEST EXCEPTION, thanks");
        }

    }
