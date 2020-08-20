/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.exception.*;
import com.arpablue.arpatest.files.logs.LogObject;
import com.arpablue.arpatest.interfaces.IMessenger;
import com.arpablue.tools.StringManager;

/**
 *
 * @author user-pc
 */
public abstract class TestBase{
    public final static String TEST_RESULT_FOLDER = "./arpatest_results";
    public final static int TEST_STATUS_PENDING = 0;
    public final static int TEST_STATUS_IN_PROGRESS = 1;
    public final static int TEST_STATUS_PASS = 2;
    public final static int TEST_STATUS_FAIL = 3;
    public final static int TEST_STATUS_ERROR = 4;
    public final static int TEST_STATUS_BLOCK = 5;
    public final static int TEST_STATUS_NOT_APPLICABLE = 6;
    public final static int TEST_STATUS_DEPRECATED = 7;

    public static float round(double tiempo){
        tiempo = tiempo * 100.0;
        int aux = (int) tiempo;
        float res = ((float)aux) / 100.0f;
        return res;
    }
    public static String getTimeString(double tense) {
        String kind = "milisecond(s)";
        if( tense >= 1000.0){
            tense = tense / 1000.0;
            kind = " second(s)";
            if( tense >= 60.0 ){
                tense = tense / 60.0;
                kind = " minute(s)";
                if( tense >= 60.0 ){
                    tense = tense / 60.0;
                    kind = " hour(s)";
                    if( tense >= 24.0 ){
                        tense = tense / 24.0;
                        kind = " day(s)";
                    }
                }
            }
        }
        return TestBase.round( tense )+ " " + kind;
    }
    protected int mTestStatus = TestBase.TEST_STATUS_PENDING;
    
    protected String mTestMessage = null;
    /***
     * It return a text that corresponding to the current status.
     * @param code It is the code of the current status.
     * @return It is the text of the respective code.
     */
    public static final String getTestStatusLabel( int code){
        if( code == TestBase.TEST_STATUS_PENDING){
            return "PENDING";
        }
        if( code == TestBase.TEST_STATUS_IN_PROGRESS){
            return "IN PROGRESS";
        }
        if( code == TestBase.TEST_STATUS_PASS){
            return "PASS";
        }
        if( code == TestBase.TEST_STATUS_FAIL){
            return "FAIL";
        }
        if( code == TestBase.TEST_STATUS_ERROR){
            return "ERROR";
        }
        if( code == TestBase.TEST_STATUS_BLOCK){
            return "BLOCK";
        }
        if( code == TestBase.TEST_STATUS_NOT_APPLICABLE){
            return "NOT APPLICABLE";
        }
        if( code == TestBase.TEST_STATUS_DEPRECATED){
            return "DEPRECATED";
        }
        return "UNKNOW";
    }
    // These are the different states for a test oibject.
    
    protected LogObject mLog = null;
    protected String mOutputTestFolder = TEST_RESULT_FOLDER;
    protected String mOutputTestFile = null;
    protected String mExtensionFile = "log";

    /**
     * It is the name of the current TestObject.
     */
    protected String mTestName;
    ///////////////////////// abstract
    protected abstract void testRun();
    protected abstract String toJSON();
    protected abstract String getType();
    ///////////////////////////////////
    
    protected TestBase(){}
    protected TestBase( String name){
        this.setName( name );
    }
    /**
     * It return the string with the minimal information of the test object.
     * @return It is a String 
     */
    @Override
    public String toString(){
        String res = this.getClass().getSimpleName();
        res = res + " : " + this.getStatus();
        res = res + " - "+this.getName();
        return res;
    }
    /**
     * It specify the message of the current test object.
     * @param message It is the message of the current test object.
     */
    protected void setMessage(String message){
        this.mTestMessage = message;
    }
    /**
     * It return the message of the current test object.
     * @return It is the message of the current test case, if is null means that
     * the test object has not been executed.
     */
    public String getMessage(){
        return this.mTestMessage;
    }
    /**
     * It specify the current status of the test case.
     * @param status It is the status code of the test case, these codes you can 
     * find static values of the TestBase class.
     */
    protected void setStatusCode(int status){
        this.mTestStatus = status;
    }
            
    /**
     * It return the test case case state according to the static state of the 
     * TestBase class.
     * @return It is an integer number that represent the state of the case.
     */
    public int getStatusCode(){
        return mTestStatus;
    }
    
    /**
     * It return the current status.
     * @return It is the label of the current status.
     */
    public String getStatus(){
        return TestBase.getTestStatusLabel(this.getStatusCode());
    }
    /**
     * It specify the name of the current test object.
     * @param name It is the name of the current name.
     */
    public void setName( String name){
        mTestName = name;
        if( mTestName != null){
            mTestName = mTestName.trim();
        }
    }
    /**
     * It return the name of the current test object.
     * @return It is the name of the current test object.
     */
    public String getName( ){
        if( mTestName == null){
            return this.getClass().getSimpleName();
        }
        return mTestName;
    }
    public void setFolder( String folder){
        this.mOutputTestFolder = folder;
        if( this.mOutputTestFolder == null){
            this.mOutputTestFolder = "./";
        }
    }
    public String getFolder(){
        return this.mOutputTestFolder;
    }
    public void setExtensionFile( String extension){
        this.mExtensionFile = extension;
        if( this.mExtensionFile != null){
            this.mExtensionFile = this.mExtensionFile.trim();
        }
                
    }
    /**
     * It specify the name of the file where the log messages will be raised.
     * @param name It is the full path of the log file.
     */
    public void setFile( String name){
        this.mOutputTestFile = name;
        mOutputTestFile = StringManager.removeWitheSpace(mOutputTestFile,"_");
        if(mOutputTestFile.length() < 1){
            mOutputTestFile = null;
        }
    }
    /**
     * It return the log file where the messages will be created.
     * @return It is the path of the log file.
     */
    public String getFile(){
        if( mOutputTestFile == null){
            mOutputTestFile = this.getName();
            if(mOutputTestFile == null){
                mOutputTestFile = "NoTitle";
            }
            mOutputTestFile = StringManager.removeWitheSpace(mOutputTestFile,"_");
        }
        
        return this.mOutputTestFile;
    }
    /**
     * It return the extension of the log file.
     * @return 
     */
    public String getExtensionFile(){
       if(this.mExtensionFile == null){
           return "";
       }
       return "."+this.mExtensionFile;
    }
    /**
     * It raise a title in the log file.
     * @param text It is the text that will be set as title.
     */
    protected void title(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.title(text);
    }
    /**
     * It specify the beginning of an area in the log file.
     * @param text It is the title of the area raised in the log file.
     */
    protected void area(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.area(text);
    }
    /**
     * It specify the beginning of a body in the log file.
     * @param text It is the title of the body in the log file.
     */
    protected void body(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.body(text);
    }
    /**
     * It specify when using the beginning of an step in the log file.
     * @param text It is a text description about the execution of the step.
     */
    protected void step(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.step(text);
    }
    /**
     * It specify the beginning of a section.
     * @param text It the text to describe the action taken in the current section.
     */
    protected void section(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.section(text);
    }
    /**
     * It specify the action to be applied.
     * @param text It is the action taken.
     */
    protected void action(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.action(text);
    }
    /**
     * It specify the command to be executed, generally is used to show the command to be executed.
     * @param text It is the command to be executed.
     */
    protected void cmd(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.cmd(text);
    }
    /**
     * It specify the instruction to be executed.
     * @param text It text is the instruction to be executed.
     */
    protected void inst(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.inst(text);
    }
    /**
     * This is general message to describe the current action or for more informal messages.
     * @param text It is the message to be raised.
     */
    protected void msg(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.msg(text);
    }
    /**
     * It write n empty line.
     */
    protected void msg() {
        msg("");
    }
    /**
     * It write a horizontal line in the log.
     */
    protected void hr(){
        if( this.mLog == null){
            return;
        }
        this.mLog.msg("______________________________________");
        
    }
    /**
     * It rise a message in the log file, but this message is raised without time stamp.
     * @param text It is the message to raise in the log file.
     */
    protected void write(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.write(text);
    }
    /**
     * It write an array of objects in the log file.
     * @param target It is the array of object to be raised in the log file.
     */
    protected void msg(Object[] target){
        if( target == null){
            return;
        }
        for( Object obj: target){
            msg( obj.toString());
        }
    }
    /**
     * This raise a message to be read in the log file.
     * @param text It is the message to be raised.
     */
    protected void message(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.message(text);
    }
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    protected void err(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.err(text);
    }
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    protected void wrong(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.wrong(text);
    }
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    protected void oops(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.oops(text);
    }
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    protected void mistake(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.mistake(text);
    }
    /**
     * This raise a success message in the log file, but without stop the current execution.
     * @param text It is the text that describe the success.
     */
    protected void success(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.success(text);
    }
    /**
     * It raise a message to specify that son function has been complete without problems.
     * @param text It is the message to notify the the complete of a task.
     */
    protected void complete(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.complete(text);
    }
    /**
     * It raise a message to specify that some task is finished.
     * @param text It is the text to notify that some task has finished.
     */
    protected void finished(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.finished(text);
    }
    /**
     * It raise a success message to indicate the current conditions are taken to finish 
     * the current execution successfully, this stop the current execution.
     * @param text This is the success message to be displayed.
     */
    protected void pass(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.pass(text);
        throw new TestPassException( text );
    }
    /**
     * It raise a fail message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the fail message to be displayed, this should describe 
     * the current conditions for the fail.
     */
    protected void fail(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.fail(text);
        throw new TestFailException( text );
    }
    /**
     * It raise an error message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the error message to be displayed, this should describe 
     * the current conditions for the error.
     */
    protected void error(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.error(text);
        throw new TestErrorException( text );
    }
    /**
     * It raise an block message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the block message to be displayed, this should describe 
     * the current conditions for the block.
     */
    protected void block(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.block(text);
        throw new TestBlockException( text );
    }
    /**
     * It raise an not applicable message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the message to be displayed, this should describe the 
     * current conditions for the not applicable situation.
     */
    protected void notApplicable(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.notApplicable(text);
        throw new TestNotApplicableException( text );
    }
    /**
     * It raise a deprecated message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the message to be displayed, this should describe the 
     * reasons to describe the conditions for current execution is deprecated.
     */
    protected void deprecated(String text) {
        if( this.mLog == null){
            return;
        }
        this.mLog.deprecated(text);
        throw new TestDeprecatedException( text );
    }
    /**
     * It compare 2 integers, and if are different then a FAIL exception is raised.
     * @param exp It is the expected integer value.
     * @param cur It is the current integer value.
     */
    protected void compare( int exp, int cur ){
        step("Comparing the EXPECTED result and CURRENT result");
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
        if( exp != cur ){
            fail("The EXPECTED RESULT ["+exp+"]  is different to the CURRENT RESULT ["+cur+"].");
            return;
        }
        success("The EXPECTED RESULT ["+exp+"]  and the CURRENT RESULT ["+cur+"] are equals.");
    }
    /**
     * It compare 2 boolean values, and if are different then a FAIL exception is raised.
     * @param exp It is the expected boolean value.
     * @param cur It is the current boolean value.
     */
    protected void compare( boolean exp, boolean cur ){
        step("Comparing the EXPECTED result and CURRENT result");
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
        if( exp != cur ){
            fail("The EXPECTED RESULT ["+exp+"]  is different to the CURRENT RESULT ["+cur+"].");
            return;
        }
        success("The EXPECTED RESULT ["+exp+"]  and the CURRENT RESULT ["+cur+"] are equals.");
    }
    /**
     * It compare 2 long values, and if are different then a FAIL exception is raised.
     * @param exp It is the expected long value.
     * @param cur It is the current long value.
     */
    protected void compare( long exp, long cur ){
        step("Comparing the EXPECTED result and CURRENT result");
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
        if( exp != cur ){
            fail("The EXPECTED RESULT ["+exp+"]  is different to the CURRENT RESULT ["+cur+"].");
            return;
        }
        success("The EXPECTED RESULT ["+exp+"]  and the CURRENT RESULT ["+cur+"] are equals.");
    }
    /**
     * It compare 2 float values, and if are different then a FAIL exception is raised.
     * @param exp It is the expected float value.
     * @param cur It is the current float value.
     */
    protected void compare( float exp, float cur ){
        step("Comparing the EXPECTED result and CURRENT result");
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
        if( exp != cur ){
            fail("The EXPECTED RESULT ["+exp+"]  is different to the CURRENT RESULT ["+cur+"].");
            return;
        }
        success("The EXPECTED RESULT ["+exp+"]  and the CURRENT RESULT ["+cur+"] are equals.");
    }
    /**
     * It compare 2 double values, and if are different then a FAIL exception is raised.
     * @param exp It is the expected double value.
     * @param cur It is the current double value.
     */
    protected void compare( double exp, double cur ){
        step("Comparing the EXPECTED result and CURRENT result");
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
        if( exp != cur ){
            fail("The EXPECTED RESULT ["+exp+"]  is different to the CURRENT RESULT ["+cur+"].");
            return;
        }
        success("The EXPECTED RESULT ["+exp+"]  and the CURRENT RESULT ["+cur+"] are equals.");
    }
    /**
     * It compare two Object values, the expected an the current result, if are different then 
     * and fail exception is raised.
     * @param exp It is the Object that represent the expected  result.
     * @param cur It is the Object that represent the current result.
     */
    protected void compare(Object exp, Object cur){
        if(( exp == null)&&( cur == null)){
            msg("The EXPECTED and CURRENT values are NULL.");
            return;
        }
        if( exp == null ){
            msg("Expected result: NULL");
            msg(" Current result: "+cur);
            fail("The EXPECTED RESULT is NULL, but the CURRENT RESULT it is not:["+cur+"] ");
            return;
        }
        if( cur == null ){
            msg("Expected result: "+exp);
            msg(" Current result: NULL");
            fail("The EXPECTED RESULT is not NULL, but the CURRENT RESULT is NULL.");
            return;
        }
        compare(exp.toString(), cur.toString());
    }
    /**
     * It compare two string values, the expected an the current result, if are different then 
     * and fail exception is raised.
     * @param exp It is the string that represent the expected  result.
     * @param cur It is the string that represent the current result.
     */
    protected void compare(String exp, String cur){
        
        step("Comparing the EXPECTED result and CURRENT result");
        if(( exp == null)&&( cur == null)){
            msg("The EXPECTED and CURRENT values are NULL.");
            return;
        }
        if( exp == null ){
            msg("Expected result: NULL");
            msg(" Current result: "+cur);
            fail("The EXPECTED RESULT is NULL, but the CURRENT RESULT it is not:["+cur+"] ");
            return;
        }
        if( cur == null ){
            msg("Expected result: "+exp);
            msg(" Current result: NULL");
            fail("The EXPECTED RESULT is not NULL, but the CURRENT RESULT is NULL.");
            return;
        }
        if( exp.length() != cur.length() ){
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
            fail("The EXPECTED RESULT and the CURRENT RESULT are diffents.");
            return;
        }
            msg("Expected result: "+exp);
            msg(" Current result: "+cur);
        if( exp.length() != cur.length() ){
            fail("The EXPECTED RESULT ant the CURRENT RESULT are diffents.");
            return;
        }
        if( !StringManager.strCompare( exp, cur) ){
            fail("The EXPECTED RESULT ant the CURRENT RESULT are diffents.");
            return;
        }
        success("The EXPECTED and CURRENT values are equals.");
        
    }
}
