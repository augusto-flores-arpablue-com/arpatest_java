/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.core;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.files.inifiles.IniFile;
import com.arpablue.arpatest.files.inifiles.Section;
import com.arpablue.arpatest.files.logs.LogObject;
import com.arpablue.arpatest.files.logs.Logger;
import com.arpablue.arpatest.testlib.TestPlan;
import com.arpablue.tools.StringManager;
import com.arpablue.arpatest.tools.TimeTool;

/**
 *
 * @author user-pc
 */
public class CoreBase {
    protected static ArpaTest GLOBAL = null;
    protected static final String FIELD_TEST_RESULT_FOLDER ="Test.resultFolder";
    protected static final String FIELD_LOG_FOLDER ="Log.Folder";
    protected static final String FIELD_LOG_LEVEL ="Log.level";
    protected static final String FIELD_SETTING_FILE = "settingsFile";
    protected static final String FIELD_LOG_FOLDER_DATE_FORMAT = "Log.logDirFormatDate";
    protected static final String FIELD_exeFile = "exeFile";
    
    protected static int mLOG_LEVEL = -1;
    protected static String SETTINGS_FILE = "configTest.ini";
    protected static String TEST_PLAN_FILE = "TestPlanFile";
    protected LogObject mLogger;
    protected IniFile mParameters;
    
    /**
     * It set the default value in the case that the config.ini file not exists.
     */
    protected void setDefaultParameters(){
        if(!mParameters.fieldExists( FIELD_SETTING_FILE )){
            mParameters.setValue( FIELD_SETTING_FILE, SETTINGS_FILE );
        }
        if(!mParameters.fieldExists(FIELD_LOG_FOLDER)){
            mParameters.setValue(FIELD_LOG_FOLDER,"./logs/");
        }
        if(!mParameters.fieldExists(FIELD_LOG_LEVEL)){
            mParameters.setValue(FIELD_LOG_LEVEL,"5");
        }
        if(!mParameters.fieldExists(FIELD_TEST_RESULT_FOLDER)){
            mParameters.setValue(FIELD_TEST_RESULT_FOLDER,"./arpatest_results");
        }
        if(!mParameters.fieldExists( FIELD_LOG_FOLDER_DATE_FORMAT )){
            mParameters.setValue( FIELD_LOG_FOLDER_DATE_FORMAT, "yyyyMMdd_HHmmss_SSS" );
        }
        if(!mParameters.fieldExists( TEST_PLAN_FILE )){
            mParameters.setValue( TEST_PLAN_FILE, null );
        }
    }
    /**
     * It load the parameters from the config.ini file, if this file not exist then
     * a new config.ini file is created with the default values.
     */
    protected void loadParameters(){
        mParameters = new IniFile();
        if(!mParameters.open(SETTINGS_FILE)){
            setDefaultParameters();
            mParameters.save();
        }else{
            setDefaultParameters();
        }
    }
    /**
     * It save the local parameters in the a file in INi file format.
     * @param iniPathFile It is the path of the INI file.
     */
    public void saveSettingsFromINI( String iniPathFile ){
        if( this.mParameters == null ){
            return;
        }
        this.mParameters.save(iniPathFile);
    }
    /**
     * It return the value of a global parameter, with reference to the section.
     * @param section It is the section to find the parameter.
     * @param parameter It is the name of the field.
     * @return It is the value of the parameter.
     */
    public String getParameter( String section, String parameter){
        return this.mParameters.getField(section, parameter);
    }
    /**
     * It return the value of a global parameter, with reference to the section.
     * @param section It is the section to find the parameter.
     * @param parameter It is the name of the field.
     * @return It is the value of the parameter.
     */
    public String getParameter(  String parameter){
        return this.mParameters.getValue( parameter );
    }
    /**
     * It set a new value to a global parameter, if the parameter and/or section 
     * not exist then the section and the field is created.
     * @param section It section is where the parameter will be parameter.
     * @param parameter It is the parameter to be set the new value.
     * @param value It is the new value for the parameter.
     */
    public void setParameter(String section, String parameter, String value){
        this.mParameters.setField(section,parameter,value);
    }
    /**
     * It set a new value to a global parameter, if the parameter and/or section 
     * not exist then the section and the field is created.
     * @param parameter It is the parameter to be set the new value, the parameter use the format "section.parameter".
     * @param value It is the new value for the parameter.
     */
    public void setParameter( String parameter, String value){
        if( parameter == null ){
            return;
        }
        parameter = parameter.trim();
        this.mParameters.setField( parameter, value );
        if( parameter.equalsIgnoreCase( FIELD_SETTING_FILE )){
            setSettingsFile(value);
        }
    }
    /**
     * It verify that a field exists in the global parameters.
     * @param parameter It is the name of the field to be search. The format used is "section.field".
     * @return It is true if the parameter exists.
     */
    public boolean parameterExist(String section, String parameter){
        return this.mParameters.fieldExists(section, parameter);
    }
    /**
     * It verify that a field exists in the global parameters.
     * @param parameter It is the name of the field to be search, the format used is: "section.parameter".
     * @return It is true if the parameter exists.
     */
    public boolean parameterExist( String parameter ){
        return this.mParameters.fieldExists( parameter );
    }
    /**
     * It return the log object to register or raise messages in the log file.
     * @return It is the Log object to write the messages.
     */
    public LogObject getLog(){
        return mLogger;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * It return the date format used to generate the folder for the test results.
     * @return It is the format date used in the execution of the project.
     */
    public static String getLogFolderDateFormat(){
        return ArpaTest.getInstance().getParameter(FIELD_LOG_FOLDER_DATE_FORMAT);
    }
    /**
     * It specify which is the settings INI file to be used for the current execution
     * @param iniPathFile It is the path file of the INI settings file.
     */
    public static void setSettingsFile( String iniPathFile ){
        ArpaTest.getInstance().step("Loading setttings file.");
        if( iniPathFile == null ){
            ArpaTest.getInstance().fail("It is not possible load a NULL path file.");
            return;
        }
        
        iniPathFile = StringManager.setFormatPath(iniPathFile);
        if( iniPathFile.length() < 1){
            ArpaTest.getInstance().fail("It is not possible load a EMPTY path file.");
            return;
        }
        SETTINGS_FILE = iniPathFile;
        ArpaTest.getInstance().loadSettingsFromINI(iniPathFile);
    }
    /**
     * It return the log folder.
     * @return It is the path of the log folder.
     */
    public static String getLogFolder(){
        return ArpaTest.getInstance().getParameter(ArpaTest.FIELD_LOG_FOLDER);
    }
    /**
     * It return the TestResult folder, where the test results of the automation will be created.
     * @return It is the path of the test results.
     */
    public static String getTestResultsFolder(){
        return ArpaTest.getInstance().getParameter(FIELD_TEST_RESULT_FOLDER);
    }
    /**
     * It divide the the parameters in the parameter and its value.
     * @param parameter It is the parameter in the format "section.parameter=value"
     * @return Return the corresponding array with the parameter and the value.
     */
    protected static String[] splitParameter( String parameter ){
        String[] res =new String[2];
        res[0] = Section.DEFAULT_NAME;
        if( parameter == null ){
            return null;
        }
        parameter = parameter.trim();
        if( parameter.length() < 1 ){
            return null;
        }
        String[] v = parameter.split("=");
        if(v.length < 2){
            res[1] = v[0];
            return res;
        }
        res[0] = v[0];
        res[1] = v[1];
        return res;
    }
    /**
     * It return the 
     * @return 
     */
    public IniFile getParameters(){
        return this.mParameters;
    }
    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * It set the arguments for the current execution.
     * @param args It is the vector of arguments to be executed.
     */
    public static void setArguments(String[] args){
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if( arg.indexOf("=") > -1 ){
                ArpaTest.addParameter(arg);
            }
        }
    }
    /**
     * It add a argument to identify as parameter, these parameters are added to the
     * global arguments.
     * @param parameter It is the parameter to be add to the global settings, the forma used is "section.
     */
    public static void addParameter(String parameter){
        String[] params = splitParameter(parameter);
        if( params == null ){
            return;
        }
        
        ArpaTest.println("Adding a new GLOBAL parameter: "+parameter);
        ArpaTest.getInstance().setParameter(params[0], params[1]);
    }
    
}
