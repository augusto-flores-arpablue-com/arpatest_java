/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.logs;
import com.arpablue.arpatest.interfaces.IChannel;
import com.arpablue.arpatest.interfaces.IMessenger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author user-pc
 */
public abstract class LogObject extends LogFileBase implements IChannel,IMessenger{
    protected boolean mIsOpen = false;
    /**
     * It method create all directories of the path, if the directory not exists.
     * @return 
     */
    protected static boolean mkdirs(String dir){
        if(dir == null){
            return true;
        }
        if(dir.length() < 1){
            return true;
        }
        if( dir.equalsIgnoreCase("./") ){
            return true;
        }
        File f = new File(dir);
        if( f.exists() && f.isDirectory()){
            return true;
        }
        return f.mkdirs();
    }
    /**
     * It is called after create and open the file. 
     * this method is executed during the open method.
     */
    protected abstract void before();
    /** 
     * It is called during th e execution of the close method, is is executed before 
     * close the file.
     */
    protected abstract void after();
    /**
     * It open or/and create a file to write the log file.
     * @return It return true if the file has been create without problems.
     */
    @Override
    public boolean open(){
        mIsOpen = true;
        String f = this.getFile();
        File file = new File( f );
        if( file.exists() && file.isFile() ){
            if(!file.delete()){
                return false;
            }
        }
        LogObject.mkdirs(this.getDirectory());
        before();
        return true;
    }
    
    @Override
    public synchronized boolean write(String text) {
        if(!this.isOpen()){
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;        
        try{
            
            fw = new FileWriter(this.getFile(), true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println( text );
            out.close();            
        }catch( Exception e){
            System.out.println("ERROR(LogFile - write): " + e.getMessage());
            return false;
        }
        finally {
            try {
                if(out != null)
                    out.close();
                if(bw != null)
                    bw.close();
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                System.out.println("ERROR IO(LogFile - write): " + e.getMessage());
                return false;
            }
        }
        return true;
        
    }

    @Override
    public boolean close() {
        after();
        mIsOpen = false;
        return true;
    }
    @Override
    public boolean isOpen() {
        return mIsOpen;
    }
    //////////////////Abstract methods
    ///Separation

}
