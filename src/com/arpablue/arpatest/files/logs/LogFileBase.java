/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.logs;

import com.arpablue.tools.StringManager;

/**
 * It class create a file to write the message.
 * @author Augusto Flores
 */
public abstract class LogFileBase {
    protected String mFileName;
    protected String mDirectory = "";
    protected final static String FILE_NAME = "activity.log";
    /**
     * It specify the name of the file to create the log file.
     * @param fileName It is the name of the log file to be create, if the value is null then the default name will be activity.log.
     */
    public void setFileName(String fileName){
        mFileName = fileName;
        if( mFileName == null){
            mFileName = LogFileBase.FILE_NAME;
            return;
        }
        mFileName = mFileName.trim();
        if( mFileName.length() < 1 ){
            mFileName = LogFileBase.FILE_NAME;
            return;
        }
        mFileName = StringManager.setFormatPath( mFileName );
        mFileName = mFileName.replace(" ", "-_-");
        mFileName = mFileName.replace("/", " ");
        mFileName = mFileName.trim();
        mFileName = mFileName.replace(" ", "/");
        mFileName = mFileName.replace("-_-", " ");
    }
    /**
     * It return the file name of the log file.
     * @return It is the file name of the log file.
     */
    public String getFileName(){
        if( mFileName == null){
            mFileName = LogFileBase.FILE_NAME;
        }
        return mFileName;
    }
    /**
     * It specify the directory where the log file will be created.
     * @param directory 
     */
    public void setDirectory(String directory){
        mDirectory = directory;
        if( mDirectory == null ){
            mDirectory= "";
            return;
        }
        mDirectory = mDirectory.trim();
        if( mDirectory.length() < 1 ){
            mDirectory= "";
            return;
        }
        mDirectory = StringManager.setFormatPath(mDirectory);
        mDirectory = mDirectory + "/";
            
    }
    /**
     * It return the current directory where the log file will be created.
     * @return 
     */
    public String getDirectory(){
        return mDirectory;
    }
    /**
     * It specify the full path of the log file, the last section of the path it 
     * is the file name.
     * @param fullpath 
     */
    public void setLogFile( String fullPath){
        if( fullPath == null){
            this.setFileName(null);
            this.setDirectory(null);
            return;
        }
        fullPath = fullPath.trim();
        if( fullPath.equalsIgnoreCase("") ){
            this.setFileName(null);
            this.setDirectory(null);
            return;
        }
        fullPath = StringManager.setFormatPath(fullPath);
        String [] v = fullPath.split("/");
        if( v.length == 1 ){
            this.setFileName(v[0]);
            this.setDirectory(null);
            return;
        }
        String file = v[v.length - 1];
        String folder = fullPath.replace(file, "");
        this.setFileName(file);
        this.setDirectory(folder);
    }
    public String getFile(){
        String res = this.getDirectory();
        res = res + this.getFileName();
        return res;
    }
            
    /**
     * It return of the full path of the 
     * @return 
     */
    @Override
    public String toString(){
        
        return this.getFile();
    }
    //////// Abstaract classes ///////////////
    public abstract boolean write(String text);
    
}
