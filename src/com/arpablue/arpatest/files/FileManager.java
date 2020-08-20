/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author user-pc
 */
public class FileManager {
    /**
     * Delete a existing file.
     * @param pathFile It is the full path of the file.
     * @return It is true if the file has been deleted without problems
     */
    public static synchronized boolean delete(String pathFile) {
        File f = new File(pathFile);
        if( exists(f)){
                if(!f.delete()){
                    System.out.println("ERROR: It is not possible delete the "+pathFile+" file.");
                    return false;
                }
                return true;
        }
        return true;
    }
    /**
     * It Verify if a file exists.
     * @param pathFile It is the full path of the file.
     * @return It is true if the file exists.
     */
    public static synchronized boolean exists(String pathFile) {
        File f = new File(pathFile);
        return exists(f);
    }
    /**
     * It Verify is a File object exist.
     * @param f It is the file object with the reference to a file.
     * @return It is true if the file exists.
     */
    public static synchronized boolean exists(File f) {
        if( f.exists()){
            if( f.isFile() ){
                return true;
            }
        }
        return false;
    }
    /**
     * It add a line text in a file.
     * @param pathFile It is the full path of the file.
     * @param text It is the text to be add to the end of the file.
     * @return It is true if the text has been added without problems
     */
    public static synchronized boolean addln(String pathFile, String text) {

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;      
        try{
            
            fw = new FileWriter(pathFile, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println( text );
            out.close();            
        }catch( Exception e){
            System.out.println("ERROR(Logger - println): " + e.getMessage());
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
                System.out.println("ERROR IO(Logger - println): " + e.getMessage());
                return false;
            }
        }
        return true;
    }    
}
