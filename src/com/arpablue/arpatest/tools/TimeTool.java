/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author user-pc
 */
public class TimeTool {
    /**
     * It return the current instant time in an specific format, using the SimpleDateFormat.
     * @param format It is the format used for the time.
     * @return It is a current time in the specific format.
     */
    public static String getNow(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return (dateFormat.format(date)).toString();       
    }
    /**
     * It return the current time.
     * @return It return the current instant time in a string format.
     */
    public static String getNow(){
        return getNow("yyyy/MM/dd HH:mm:ss.SS");
    }
    /**
     * It return the current date.
     * @return It return the current instant time in a string format.
     */
    public static String getCurrentDate(){
        return getNow("yyyy/MM/dd");
    }
    /**
     * It return the current time.
     * @return It is a string with the current string format.
     */
    public static String getCurrentHour(){
        return getNow("HH:mm:ss.SS");
    }
    /**
     * It return the current time in the string format.
     * @return It is the current format in a string format.
     */
    public static String getNowFileFormat(){
        //return getNow("yyyyMMdd_HHmmss_SS");
        return getNow("yyyyMMdd");
    }
    /**
     * It stop the program for an specific miliseconds.
     * @param milis It is the quantity of miliseconds to stop the execution of the program.
     */
    public static void sleep(int milis){
        try{
            Thread.sleep(milis);
        }catch( Exception e){
            
        }
    }
    /**
     * It stop the execution of the program by specified seconds.
     * @param seconds It is the quantity of seconds to stop the execution of the program.
     */
    public static void sleep( float seconds ){
        seconds = seconds * 1000;
        int milis = (int)seconds;
        sleep( milis );
    }

}
