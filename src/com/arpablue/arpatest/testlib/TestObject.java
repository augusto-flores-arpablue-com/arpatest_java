/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.exception.TestException;
import com.arpablue.arpatest.files.logs.LogObject;
import com.arpablue.arpatest.files.logs.Logger;
import com.arpablue.arpatest.files.logs.LoggerHtml;

/**
 *
 * @author user-pc
 */
public abstract class TestObject extends TestObjectIni{
    protected long mStartExecutionTime = 0;
    protected double mTestExecutionTime = 0;
    /**
     * 
     */
    public String getExecutionTimeString(){
        String labelTime = TestBase.getTimeString(mTestExecutionTime);
        return labelTime;
    }
    /**
     * It return the execution time of the test object.
     * @return It is the time elapse time in seconds of the test execution, if the 
     * value is 0, then the test object has not been executed
     */
    public double getExecutiontionTime(){
        return mTestExecutionTime;
    }
    /**
     * It take the initial time of the execution time.
     * @author Augusto Flores
     */
    protected void takeStartExecutionTime(){
        mStartExecutionTime = System.nanoTime();
    }
    /**
     * It calculate the time elapsed of the execution time.
     * @return it is the seconds elapse 
     */
    protected void takeExecutionTime(){
        long res = 0;
        res = System.nanoTime();
        if( mStartExecutionTime < 1 ){
            return;
        }
        res = res - mStartExecutionTime;
        this.mTestExecutionTime = res / 1000000; // miliseconds
        

    }
    /**
     * It return the log object to be used.
     * @return It is the log object to be used.
     */
    public LogObject createLogObject(){
        String ext = this.getParameter("Log", "type");
        this.setExtensionFile( ext );
        if( this.getExtensionFile().equalsIgnoreCase(".html")){
            return new LoggerHtml();
        }
        if( this.getExtensionFile().equalsIgnoreCase(".htm")){
            return new LoggerHtml();
        }
        return new Logger();
    }
    /**
     * It is called before all execution, even before the before() method, 
     * the method is reserved for the .
     */
    void onBefore(){
        if(mLog == null){
            mLog = createLogObject();
        }
        if(this.getFolder() == null){
            this.setFolder(TestBase.TEST_RESULT_FOLDER);
        }
        if( this.getName() == null){
            this.setName(this.getClass().getSimpleName());
        }
        mLog.setDirectory(this.getFolder());
        mLog.setFileName(this.getFile()+this.getExtensionFile());
        this.takeStartExecutionTime();
        if(!mLog.open()){
            fail("It is not possible open the ["+mLog+"] file.");
        }
        this.setStatusCode(TestBase.TEST_STATUS_IN_PROGRESS);
    }
    /**
     * It method is called after all, even after the after() method.
     */
    void onAfter(){
        if(!mLog.close()){
            fail("It is not possible open the ["+mLog+"] file.");
        }
        
    }
    /**
     * It is called before execute the test object.
     */
    protected abstract void before();
    /**
     * It is called after execute the test method.
     */
    protected abstract void after();
    protected abstract void testExe();
    /**
     * It is the execution of the test case.
     */
    public void execute(){
        ArpaTest.println("Start the execution of ["+this.getName()+"] test object.");
        onBefore();
        testExe();
        onAfter();
    }
    
    
    
}
