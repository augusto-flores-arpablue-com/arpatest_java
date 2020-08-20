/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.files.inifiles.IniFile;

/**
 * It class implement the method to load a manage the current parameter for the 
 * execution of the test case.
 * @author Augusto Flores
 */
public abstract class TestObjectIni extends TestBase{
    /**
     * It contains the reference to the file to load the parameters for the 
     * current test object.
     */
    private String mIniFilePath = null;
    /**
     * It contains the parameters to the execution of the current test object.
     */
    private IniFile mParameters = null;
    /**
     * It is the reference to the parent TestObject, this is different to null, 
     * when the current object is in a container or in another TestObject, this variable
     * contain the reference to this object.
     */
    protected TestObjectIni mTestObjectParent;
    public void setIniFilePath(String iniFilePath){
        this.mIniFilePath = iniFilePath;
    }
    public String getIniFilePath(){
        return this.mIniFilePath;
    }
    /**
     * It specify the testObject that contain the current TestObject.
     * @param parent It is the container test object.
     */
    void setTestObjectParent( TestObjectIni parent ){
        this.mTestObjectParent = parent;
    }
    /**
     * It return the current container object, if no object contain the current 
     * @return It is 
     */
    TestObjectIni getTestObjectParent(){
        return this.mTestObjectParent;
    }
    /** 
     * It return the parameters of the current test object.
     * @return It is a IniFile that contain the parameters of the current test object.
     */
    IniFile getParameters(){
        if( mParameters == null ){
            mParameters = new IniFile();
        }
        return mParameters;
    } 
    /**
     * It load the INI file with the parameters and value for the current execution 
     * of the TestObject.
     * @param iniPath It is the path of the INI file to load the parameters file.
     * @return It is true if the parameters has been loaded from the INI file without problems
     */
    public boolean loadParametersFromIniFile( String iniFilePath ){
        this.setIniFilePath(iniFilePath);
        
        return loadParametersFromIniFile();
    }
    /**
     * It save the current parameters in a specific file.
     * @param iniFilePath It is the path of the INI file.
     */
    public void saveParametersToIniFile(String iniFilePath){
        this.setIniFilePath(iniFilePath);
        saveParametersFromIniFile();
    }
    /**
     * It save the parameters in the current INI file, 
     */
    public void saveParametersFromIniFile(){
        if( this.getIniFilePath() == null ){
            return;
        }
        this.getParameters().save(this.getIniFilePath());
    }
    /**
     * It load The parameters from the specified ini file.
     * @return If the Ini file has been loaded without problems then return true.
     */
    public boolean loadParametersFromIniFile(){
        if( this.getIniFilePath() == null ){
            return false;
        }
        return this.getParameters().open( this.getIniFilePath() );
    }
    /**
     * It return the value of a parameter in a specified section, if the parameter 
 not exists in the local parameters, then  search the parameter in the test Object father,
 if the Test Object Parent not exists or the parameter not exist then the 
 parameter is search in the global parameters, in the ArpaTest object.
     * @param section This is the section where the parameter will be search.
     * @param parameter This is the name of the parameter to get the value.
     * @return It is the value of the parameter specified.
     */
    public String getParameter(String section, String parameter){
        
        String res =  this.getParameters().getField(section, parameter);
        if( res != null){
            return res;
        }
        if( this.mTestObjectParent != null ){
            res = this.mTestObjectParent.getParameter(section, parameter);
            if( res != null ){
                return res;
            }
        }
        res = getGlobalParameter(section, parameter);
        return res;
    }
    /**
     * It return the value of a parameter in a specified section, if the parameter 
 not exists in the local parameters, then  search the parameter in the test Object father,
 if the Test Object Parent not exists or the parameter not exist then the 
 parameter is search in the global parameters, in the ArpaTest object.
     * @param parameter This is the name of the parameter to get the value, it use the format "section.field".
     * @return It is the value of the parameter specified.
     */
    public String getParameter( String parameter ){
        String[] v = IniFile.getSectionField(parameter);
        String res = this.getParameter( v[0], v[1]);
       
        return res;
    }
    /**
     * This set a value in the parameter in the specified section, if the parameter 
 not exists in the current parameters, the parameters is search in the parent test object,
 but if the parent test object is null or the parameter not exists then the 
 parameter is search in the global parameters, in the ArpaTest object, if not 
 exist in the global parameters then the section and/or the parameter are created
 with the new value.
     * @param section It is the section where the parameter will be searched.
     * @param parameter It is the name of the parameter.
     * @param value It is the new value set tot he parameter.
     */
    public void setParameter( String section, String parameter, String value ){
        if( this.getParameters().fieldExists(section, parameter)){
            this.getParameters().setField(section, parameter, value);
            return;
        }
        if( this.getTestObjectParent() != null ){
            if( getTestObjectParent().getParameters().fieldExists(section, parameter)){
                getTestObjectParent().getParameters().setField(section, parameter, value);
                return;
            }
        }
        ArpaTest.getInstance().setParameter(section, parameter, value);
    }
    /**
     * This set a value in the parameter in the specified section, if the parameter 
 not exists in the current parameters, the parameters is search in the parent test object,
 but if the parent test object is null or the parameter not exists then the 
 parameter is search in the global parameters, in the ArpaTest object, if not 
 exist in the global parameters then the section and/or the parameter are created
 with the new value.
     * @param parameter It is the name of the parameter, it is in the format "section.parameter".
     * @param value It is the new value set tot he parameter.
     */
    public void setParameter( String parameter, String value ){
        String[] v = IniFile.getSectionField(parameter);
        this.setParameter(v[0], v[1], value);
    }
    /**
     * It return the value of a parameter in a specified section.
     * @param section This is the section where the parameter will be search.
     * @param parameter This is the name of the parameter to get the value.
     * @return It is the value of the parameter specified.
     */
    public static String getGlobalParameter(String section, String parameter){
        
        return ArpaTest.getInstance().getParameter(section, parameter);
    }
    /**
     * It return the value of a parameter in a specified section.
     * @param parameter This is the name of the parameter to get the value, it use the format "section.parameter".
     * @return It is the value of the parameter specified.
     */
    public static String getGlobalParameter( String parameter ){
        return ArpaTest.getInstance().getParameter( parameter);
    }
    /**
     * It set a value to the global variables.
     * @param section It is the section of the parameter will be search.
     * @param parameter It is the parameter to set the new value.
     * @param value It is the new value to be set to the parameter.
     */
    public static void setGlobalParameter( String section, String parameter, String value ){
        ArpaTest.getInstance().setParameter( section, parameter, value );
    }
    /**
     * It set a value to the global variables.
     * @param parameter It is the parameter to set the new value, it use the format "section.parameter".
     * @param value It is the new value to be set to the parameter.
     */
    public static void setGlobalParameter( String parameter, String value ){
        ArpaTest.getInstance().setParameter( parameter, value );
    }
    /**
     * It verify that a parameter exist in specific section.
     * @param section It is the section where the parameter will be search.
     * @param parameter It is the name of the parameter to verify if exists.
     * @return It is true if the parameter exists.
     */
    public static boolean globalParameterExists( String section, String parameter){
        return ArpaTest.getInstance().parameterExist( section, parameter );
    }
    /**
     * It verify that a parameter exist in specific section.
     * @param parameter It is the name of the parameter to verify if exists, It use the format "section.parameter".
     * @return It is true if the parameter exists.
     */
    public static boolean globalParameterExists( String parameter ){
        return ArpaTest.getInstance().parameterExist( parameter );
    }
    
}
