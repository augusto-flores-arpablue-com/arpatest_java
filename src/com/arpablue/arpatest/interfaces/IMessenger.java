/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.interfaces;

/**
 *
 * @author user-pc
 */
public interface IMessenger {
    //**********************dividers 
    /**
     * It raise a title in the log file.
     * @param text It is the text that will be set as title.
     */
    public void title(String text);
    /**
     * It specify the beginning of an area in the log file.
     * @param text It is the title of the area raised in the log file.
     */
    public void area(String text);
    /**
     * It specify the beginning of a body in the log file.
     * @param text It is the title of the body in the log file.
     */
    public void body(String text);
    // actions
    /**
     * It specify when using the beginning of an step in the log file.
     * @param text It is a text description about the execution of the step.
     */
    public void step(String text);
    /**
     * It specify the beginning of a section.
     * @param text It the text to describe the action taken in the current section.
     */
    public void section(String text);
    /**
     * It specify the action to be applied.
     * @param text It is the action taken.
     */
    public void action(String text);
    /**
     * It specify the command to be executed, generally is used to show the command to be executed.
     * @param text It is the command to be executed.
     */
    public void cmd(String text);
    /**
     * It specify the instruction to be executed.
     * @param text It text is the instruction to be executed.
     */
    public void inst(String text);
    // msg
    /**
     * This is general message to describe the current action or for more informal messages.
     * @param text It is the message to be raised.
     */
    public void msg(String text);
    // adverticing
    /**
     * This raise a message to be read in the log file.
     * @param text It is the message to be raised.
     */
    public void message(String text);
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    public void err(String text);
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    public void wrong(String text);
    /**
     * This raise an warning  message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    public void warning(String text);
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    public void oops(String text);
    /**
     * This raise an error message in the log file, but without stop the current execution.
     * @param text It is the text that describe the error.
     */
    public void mistake(String text);
    /**
     * This raise a success message in the log file, but without stop the current execution.
     * @param text It is the text that describe the success.
     */
    public void success(String text);
    /**
     * It raise a message to specify that son function has been complete without problems.
     * @param text It is the message to notify the the complete of a task.
     */
    public void complete(String text);
    /**
     * It raise a message to specify that some task is finished.
     * @param text It is the text to notify that some task has finished.
     */
    public void finished(String text);
    // ent operation
    /**
     * It raise a success message to indicate the current conditions are taken to finish 
     * the current execution successfully, this stop the current execution.
     * @param text This is the success message to be displayed.
     */
    public void pass( String text);
    /**
     * It raise a fail message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the fail message to be displayed, this should describe 
     * the current conditions for the fail.
     */
    public void fail( String text);
    /**
     * It raise an error message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the error message to be displayed, this should describe 
     * the current conditions for the error.
     */
    public void error( String text);
    /**
     * It raise an block message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the block message to be displayed, this should describe 
     * the current conditions for the block.
     */
    public void block( String text);
    /**
     * It raise an not applicable message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the message to be displayed, this should describe the 
     * current conditions for the not applicable situation.
     */
    public void notApplicable( String text);
    /**
     * It raise a deprecated message to indicate the current conditions are taken to finish 
     * the current execution with problems, this stop the current execution.
     * @param text This is the message to be displayed, this should describe the 
     * reasons to describe the conditions for current execution is deprecated.
     */
    public void deprecated( String text);
   
    
}
