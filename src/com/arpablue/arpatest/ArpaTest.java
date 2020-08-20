/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest;

import com.arpablue.arpatest.core.CoreBase;
import com.arpablue.arpatest.core.CoreTestManagement;
import com.arpablue.arpatest.files.logs.LogObject;
import com.arpablue.arpatest.files.logs.Logger;
import com.arpablue.arpatest.files.inifiles.IniFile;
import com.arpablue.arpatest.files.inifiles.Section;
import com.arpablue.arpatest.interfaces.IMessenger;
import com.arpablue.arpatest.testlib.TestPlan;
import com.arpablue.tools.StringManager;
import com.arpablue.arpatest.tools.TimeTool;
import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author user-pc
 */
public class ArpaTest extends CoreTestManagement{
    /**
     * Default constructor;
     */
    private ArpaTest()  {
        loadParameters();
        mLogger = new Logger();
        mLogger.setLogFile("./logs/arpatest_"+TimeTool.getNowFileFormat()+".log");
        mLogger.open();
    }

    /**
     * It return the singleton object.
     * @return It is the unique instance for all application.
     */
    public static ArpaTest getInstance() {
        if( GLOBAL == null ){
            GLOBAL = new ArpaTest();
        }
        return GLOBAL;
    }
    
    /**
     * It write an empty line in the log file.
     */
    public static void println(){
        ArpaTest.getInstance().msg("");
    }
    /**
     * It write a message in the log file.
     * @param text 
     */
    public static void println(String text){
        ArpaTest.getInstance().msg(text);
    }
    /**
     * It load the settings form a INI file, if any of the basic fields are not specified
     * then this will be added with default value.
     * @param iniPathFile It is the full path of INI file to load the parameters for the global settings.
     */
    public void loadSettingsFromINI(String iniPathFile){
        if( iniPathFile == null){
            msg("It is not possible load null ini file.");
            return;
        }
        if( iniPathFile.length() < 1 ){
            msg("It is not possible load a empty ini file.");
            return;
        }
        msg("Loading the ["+iniPathFile+"] ini file for new parameter.");
        SETTINGS_FILE = iniPathFile;
        loadParameters();
    }
    /**
     * It return the log level, it means the quantity and detail level of the messages, 
     * more bigger the level more detail of the messages and the more messages are displayed,
     * the value is between 1 and 5, if the log level is not specified then by default is 5.
     * @return It is the log level of the messages.
     */
    public static int getLogLevel(){
        if( mLOG_LEVEL < 1){
            
            String val =  ArpaTest.getInstance().getParameter(FIELD_LOG_LEVEL);
            int res = 5;
            try{
                res = Integer.parseInt(val);
            }catch(Exception ex){
                res = 5;
            }
            finally{
                mLOG_LEVEL = res;
            }
        }
        return mLOG_LEVEL;
    }
    /**
     * It specify the test plan to be executed by the test application.
     * @param pathFile 
     */
    public static void setTestPlanFile(String pathFile){
        ArpaTest.getInstance().setTestProjectFile( pathFile );
    }
    /**
     * It execute the test plan specified by the [testPlanFile]
     * @return 
     */
    public static boolean execute(){
        return ArpaTest.getInstance().executeTest();
    }
}
