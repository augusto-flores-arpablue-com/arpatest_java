/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.tools.TimeTool;
import java.util.HashMap;

/**
 *
 * @author user-pc
 */
public class TestPlan extends TestSuit {
    protected String mTestCurrentFolder = null;
    public TestPlan(){
        super();
    }
    public TestPlan(String name){
        super(name);
    }
    @Override
    protected void before() {}

    @Override
    protected void after() {}

    @Override
    protected void testRun() {}

    @Override
    public String getType() {
        return "Test Plan";
    }
    @Override
    public void onBefore(){
        if(this.getFolder() == null){
            this.setFolder(TestBase.TEST_RESULT_FOLDER);
        }
        mTestCurrentFolder = this.getFolder();
        String folder = this.getFolder() + "/" + TimeTool.getNow(ArpaTest.getLogFolderDateFormat() );
        this.setFolder( folder );
        super.onBefore();
        
    }
    @Override
    public void onAfter(){
        super.onAfter();
        this.setFolder( mTestCurrentFolder );
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

}
