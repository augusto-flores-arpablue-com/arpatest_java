/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.logs;

import com.arpablue.arpatest.exception.*;
import com.arpablue.arpatest.tools.TimeTool;

/**
 * It class contains the methods that how the message will be displayed in the logger.
 * @author Augusto Flores
 */
public class Logger extends LogObject{
    
    protected LogObject mLogger = null;
    
    public Logger(){}
    /**
     * It receive the path of the log file where will be create the log file.
     * @param logFilePath 
     */
    public Logger( String logFilePath ){
        this.setLogFile(logFilePath);
    }
    
    /** 
     * It write a message in the log file.
     * @param text It is the message to be writer.
     */
    private void println(String  text){
        String line = TimeTool.getNow();
        line = line + ": " + text;
        this.write(line);
    }
    /**
     * It write a white line in the log file.
     */
    private void println(){
        println("");
    }

    @Override
    public void title(String text) {
        println();
        println("#######################################################");
        println("########## TITLE: "+text);
        println("#######################################################");
        println();
    }

    @Override
    public void area(String text) {
        println("*******************************************************");
        println("************ AREA: "+text);
        println("*******************************************************");
    }

    @Override
    public void body(String text) {
       println();
       println("-------------------------------------------------------");
       println("------------- BODY: "+text);
       println("-------------------------------------------------------");
       println();
    }

    @Override
    public void step(String text) {
        println("STEP: "+text);
    }

    @Override
    public void section(String text) {
        println("    SECTION: "+text);
    }

    @Override
    public void action(String text) {
        println("        ACTION: "+text);
    }

    @Override
    public void msg(String text) {
        println("            "+text);
    }

    @Override
    public void cmd(String text) {
        println("                "+text);
    }

    @Override
    public void inst(String text) {
        println("                     "+text);
    }
    

    @Override
    public void message(String text) {
        println("    MESSAGE: "+text);
    }

    @Override
    public void err(String text) {
        println("    ERROR: "+text);
    }

    @Override
    public void wrong(String text) {
        println("    WRONG: "+text);
    }
    
    @Override
    public void warning(String text) {
        println("    WARNING: "+text);
    }
    
    @Override
    public void mistake(String text) {
        println("    MISTAKE: "+text);
    }

    @Override
    public void oops(String text) {
        println("    OOPS: "+text);
    }

    @Override
    public void success(String text) {
        println("    SUCCESS: "+text);
    }

    @Override
    public void complete(String text) {
        println("    COMPLETE: "+text);
    }

    @Override
    public void finished(String text) {
        println("    FINISHED: "+text);
    }

    @Override
    public void pass(String text) {
        println("    PASS: "+text);
        throw new TestPassException(text);
    }

    @Override
    public void fail(String text) {
        println("    FAIL: "+text);  
        throw new TestFailException(text);
    }

    @Override
    public void error(String text) {
        println("    ERROR: "+text);
        throw new TestErrorException(text);
    }

    @Override
    public void block(String text) {
        println("    BLOCK: "+text);
        throw new TestBlockException(text);
    }

    @Override
    public void notApplicable(String text) {
        println("    NOT APPLICABLE: "+text);
        throw new TestNotApplicableException(text);
    }

    @Override
    public void deprecated(String text) {
        println("    DEPRECATED: "+text);
        throw new TestDeprecatedException(text);
    }

    @Override
    protected void before() {}

    @Override
    protected void after() {}
    
    
}
