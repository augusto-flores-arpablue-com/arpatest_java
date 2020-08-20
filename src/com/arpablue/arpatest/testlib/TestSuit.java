/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.exception.TestException;
import com.arpablue.arpatest.files.logs.LogObject;
import com.arpablue.arpatest.files.logs.Logger;
import com.arpablue.arpatest.tools.TimeTool;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author user-pc
 */
public class TestSuit extends TestObject {
    public String mReference;
    protected ArrayList<TestObject> mTestObjects;
    protected  HashMap<String,Integer> mTestResults;
    /**
     * Default constructor.
     */
    public TestSuit(){
        initTestObject();
    }
    /**
     * It specify the name of the test suit.
     * @param name It is the name of the
     */
    public TestSuit(String name){
        this.setName(name);
        initTestObject();
    }
    /**
     * It is the reference of the package that contains the test cases.
     * @param reference 
     */
    public void setReference( String reference ){
        mReference = reference;
    }
    /**
     * It return the reference or the package where the test cases are.
     * @return It is the String with the reference of the test cases
     */
    public String getReference(){
        return mReference;
    }
    protected void initTestObject(){
        mTestObjects = new ArrayList<TestObject>();
        this.setStatusCode(TestBase.TEST_STATUS_PENDING);
    }
    @Override
    public String getType(){
        return "Test Suit";
    }
    /**
     * It add test case to execute in the test suit. The null elements are not added.
     * @param target It is the test case.
     */
    public void add(TestObject target){
        if( target != null ){
            this.mTestObjects.add(target);
            target.setTestObjectParent(this);
        }
    }
    /**
     * It return the quantity of test objects.
     * @return It is the quantity of the test objects.
     */
    public int size(){
        if( this.mTestObjects == null){
            return 0;
        }
        return this.mTestObjects.size();
    }
    /**
     * It return the test objects in an specific position.
     * @param index It is the position in the list of the test objects.
     * @return It is the test objects specified in an index, if is null then the index is out of the range.
     */
    public TestObject get(int index){
        if( this.size() < 1){
            return null;
        }
        if( index >= this.size()){
            return null;
        }
            
        if( index < 0){
            index = index * -1;
            index = this.size() % index;
            index = this.size() - index;
        }
        return this.mTestObjects.get(index);
    }
    /**
     * It change the name of the the test objects to add the position of the execution.
     */
    public void setDirectoryContent(){
        String dir = this.getFolder();
        dir = dir + "/" + this.getFile();
        if( this.mTestObjects == null ){
            return;
        }
        int pos = 0;
        String file = null;
        for(TestObject e: this.mTestObjects ){
            if( e != null ){
                pos++;
                file = pos + "_" + e.getName();
                e.setFile( file );
                e.setFolder(dir);
            }
        }
        
        
    }
    /**
     * It is execute before the execution of the test case.
     */
    @Override
    protected void onBefore(){
        if(mLog == null){
            mLog = new Logger();
        }
        this.takeStartExecutionTime();
        if(this.getFolder() == null){
            this.setFolder(TestBase.TEST_RESULT_FOLDER);
        }
        if( this.getName() == null){
            this.setName(this.getClass().getSimpleName());
        }
        mLog.setDirectory(this.getFolder());
        mLog.setFileName(this.getName()+this.getExtensionFile());
        this.takeStartExecutionTime();
        
        this.setStatusCode(TestBase.TEST_STATUS_IN_PROGRESS);
        this.setDirectoryContent();
        super.onBefore();

    }
    /**
     * 
     * @param hmap 
     */
    protected static String writeData(HashMap hmap){
        String res = null;
        if( hmap == null ){
            return res;
        }
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        res = "\r\n";
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
           res = res + "        "+ mentry.getKey() + ": "+mentry.getValue()+"\r\n";
        }
        
        return res;
    }
    /**
     * It write the status of the test cases.
     */
    protected void writeStatusContent(){
        this.takeExecutionTime();
        mLog.open();
        this.write(this.toString());
        mLog.close();
    }
    /**
     * It evaluate the current test results of the test object, collect the current estates and the quantity of 
     * the TestObjects in the respective state.
     */
    protected void evaluateTestResults(){
       mTestResults = new HashMap<String,Integer>();
        String status = null;
        for( TestObject e: this.mTestObjects ){
            if( e != null){
                status = e.getStatus();
                Integer  value = mTestResults.get(status);
                if( value == null ) {
                    value = 0;
                }
                value++;
                mTestResults.put(status, value);
            }
        }
    }
    /**
     * It return the current test results in an JSON format. 
     * @return It is the string with the JSON structure.
     */
    protected String testResultsToJSON(){
        String res = "[";
        if( this.mTestResults == null ){
            return "[]";
        }
        Set set = mTestResults.entrySet();
        Iterator iterator = set.iterator();
        boolean flag = true;
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
           if( flag ){
                res = res + " {\""+ mentry.getKey() + "\": "+mentry.getValue()+"}";
                flag = false;
           }else{
               res = res + ", {\""+ mentry.getKey() + "\": "+mentry.getValue()+"}";
           }
        }
        res = res + "]";
        return res;
    }
    /**
    * It return the test results of the test suit, the current states and the quantity of the test objects in the respective state.
    * @return This a is a HashMap that contain the state and the quantity of TestObjects in the respective state.
    */
    public HashMap<String,Integer>  getTestResults(){
        evaluateTestResults();
        return this.mTestResults;
    }
    /**
     * It return the current state and execution detail of the test plan in a string
     * @return 
     */
    @Override
    public String toString(){
        
        String res = null;
        String label = "";
        res = "-------------------------------------------------------------\n";
        res = res + "------------- "+this.getType().toUpperCase()+": " + this.getName() + "\n";

        res = res + "    TOTAL TEST(S): "+this.size()+"\r\n";
        int pos = 0;
        HashMap<String,Integer> states = new HashMap<String,Integer>();
        String status = null;
        int counter = 0;
        for( TestObject e: this.mTestObjects ){
            if( e != null){
                pos++;
                status = e.getStatus();
                if( e instanceof TestSuit){
                    label = e.toString();
                }else{
                    label = "            " + pos + ") " + status +": " + e.getName();
                    if(e.getExecutiontionTime() > 0.0){
                        label = label + " : " + TestBase.getTimeString( e.getExecutiontionTime() );
                        label = label + " : " + e.getMessage();

                    }
                }
                res = res + label + "\n";
                Integer  value = states.get(status);
                if( value == null ) {
                    value = 0;
                }
                value++;
                states.put(status, value);
            }
        }

        res = res + "    "+this.getType().toUpperCase()+" STATE: "+this.getStatus()+"\n";
        res = res + "    QUANTITY OF TEST CASES: "+this.size();
        res = res + this.writeData(states);
        res = res + "    ELAPSE TIME: "+getExecutionTimeString()+"\n";
        res = res + "-------------------------------------------------------------\n";
        return res;
    }
    /**
     * It verify the current state of the test suit.
     */
    protected void updateState(){
        boolean flag = true;
        
        for( TestObject e: this.mTestObjects){
            flag = flag && ( e.getStatusCode() == TestBase.TEST_STATUS_PASS );
        }
        this.setStatusCode(TestBase.TEST_STATUS_FAIL);
        if( flag ){
            this.setStatusCode(TestBase.TEST_STATUS_PASS);
        }
    }
            
    /**
     * It is execute after execute all test case.
     */
    @Override
    protected void onAfter(){
        super.onAfter();
        this.updateState();
        this.writeStatusContent();
    }
    /**
     * It execute the test suit.
     */
    protected void testExe() {
        try{
            before();
            this.testRunVersion0();
            after();

        }catch(TestException e){
            
        }catch(Exception e){
            
        }
        finally{
            
        }
    }

    @Override
    protected void before() {}

    @Override
    protected void after() {}
    
    @Override
    protected void testRun() {}
    
    protected void testRunVersion0() {
        this.writeStatusContent();
        if(this.mTestObjects == null){
            return;
        }
        for( TestObject e : this.mTestObjects){
            e.setStatusCode( TestBase.TEST_STATUS_IN_PROGRESS );
            writeStatusContent();
            e.execute();
        }
        writeStatusContent();
    }

    public String toJSON(){
        String res = "{";
        boolean other = true;
        res = "{\"name\":\"" + this.getName() + "\",";
        res = res + "\"status\":\"" + this.getStatus() + "\",";
        res = res + "\"type\":\""+this.getType()+"\"";
        if(this.size() > 0 ){
            res = res + ",\"testobjects\":[";
            for(TestObject e : this.mTestObjects){
                
                if( other ){
                    other = false;
                    res = res + e.toJSON();
                }else{
                    res = res + "," +e.toJSON();
                }
                
            }
            res = res + "]";
            
        }
        res = res + ",\"testResult\":"+this.testResultsToJSON();
        res = res + "}";
        return res;
           
    }

    protected boolean saveJSON(){
        String json = this.toJSON();
        Logger l = new Logger();
        l.setLogFile(this.getFile()+".json");
        if( !l.open() ) {
            return false;
        }
       if( !l.write(json) ){
           return false;
       }
       if( !l.close() ){
           return false;
       }
       return true;
    }
    
}
