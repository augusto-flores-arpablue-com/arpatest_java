/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.datadriven;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.interfaces.IOpenClose;
import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * It manage the data to be execute in a test case
 * @author user-pc
 */
public class DataDriven implements IOpenClose{
    /**
     * It contains the path of the file of the data driven , the file should be in CSV format.
     */
    protected String mDDfile;
    /**
     * It is the character used as separator. by default the character used is the ",".
     */
    protected String mSeparator;
    /**
     * It get the status of the file.
     */
    protected boolean mIsOpen =  false;
    /**
     * It contains the lines of the file.
     */
    protected ArrayList<ArrayList<String>> maLines;
    /**
     * It contains the title of the columns.
     */
    protected ArrayList<String> mTitles;
    /**
     * It identify when the first line should be processed.
     */
    protected boolean mFirstLine = true;
    /**
     * It is the flag to verify if the file has been processed.
     */
    protected boolean mProcessed = false;
    /**
     * It contain the end of the line, if is -1, the file has not been processed.
     */
    protected int mLineIndex = -1;
    /**
     * It contains the name of the fields.
     */
    protected ArrayList<String> maColumns;
    /**
     * Default constructor.
     */
    public DataDriven(){
        mSeparator = ",";
        maLines = new ArrayList<ArrayList<String>>();
        mLineIndex = -1;
        maColumns = null;
    }
    /**
     * It is the constructor receive the CSV file used for the data driven process.
     * @param csvFile It is the CSV file used to DATA Driven process.
     */
    public DataDriven(String csvFile){
        this();
        this.setDataDrivenFile( csvFile );
    }
    /**
     * It specify the file used for the data driven, the file should be on CSV format to be read, the first line should be contain the 
     * title of the columns and use the "," as separator, if the "," is between double quotes then this be used as part of the value.
     * @param csvFile It is the CSV file used for the data driven.
     */
    public void setDataDrivenFile( String csvFile){
        mDDfile = csvFile;
        if( mDDfile == null ){
            return;
        }
        mDDfile = StringManager.setFormatPath(csvFile);
    }
    /**
     * It return the current CSV file used for the 
     * @return 
     */
    public String getDataDrivenFile(){
        return  mDDfile;
    }
    /**
     * It open and process the CSV file.
     * @return It is true if the file has been processed successfully.
     */
    @Override
    public boolean open() {
        try{
            mLineIndex = -1;
            this.mFirstLine = true;
            mIsOpen = false;
            if( this.getDataDrivenFile() == null ){
                ArpaTest.println("ERROR ( DataDriven - open ):  It is not possible open ta NULL reference.");
                return false;
            }
            File f = new File( this.getDataDrivenFile() );
            if(!f.exists()){
                ArpaTest.println("ERROR ( DataDriven - open ):  It is not possible open a file that not exists.");
                return false;
            }
            if( !f.isFile() ){
                ArpaTest.println("ERROR ( DataDriven - open ):  It is not possible open a reference that is not a file.");
                return false;
            }
            RandomAccessFile reader = new RandomAccessFile(f, "r");
            mIsOpen = true;
            String line = "";
            while( line != null){
                line = reader.readLine();
                if(line != null){
                    processLine( line );
                }
            }
            reader.close();
            mLineIndex = 0;
        }catch( Exception e){
            ArpaTest.println("ERROR ( DataDriven - open ):  "+e.getMessage());
            
        }
        mIsOpen = false;
        return true;
    }
    /**
     * It close the file.
     * @return It is true the file has been closed.
     */
    @Override
    public boolean close() {
       return true;
    }
    /**
     * It return current state of the file.
     * @return It return current file status.
     */
    @Override
    public boolean isOpen() {
            return mIsOpen;
    }
    /**
     * It process the current line to add data to be process.
     * @param line Its is the line to be processed.
     */
    protected void processLine(String line){
        if( line == null ){
            return;
        }
        line = line.trim();
        ArrayList<String> data = getDataField(line);
        if( this.mFirstLine ){
            this.mFirstLine = false;
            this.maColumns = data;
            return;   
        }else{
            this.maLines.add( data );
        }
    }
    /**
     * It process the line to return the fields detected in the lines
     * @param line It is the string with the fields separate with a special characters.
     * @return It is the quantity of fields founded in the line.
     */
    protected ArrayList<String> getDataField( String line ){
        String field = "";
        ArrayList<String> res = new ArrayList<String>();
        char c;
        boolean flag = false;
        for( int i = 0; i <= line.length() - 1; i++){
            c = line.charAt(i);
            if( c == '"'){
                flag = !flag;
            }
            if( !flag ){
                if(c == ','){
                   if( field.length() < 1 ){
                       res.add( null );
                   }else{
                       res.add( field );
                   }
                   field = "";
                }else{
                    field = field + c;
                }
            }else{
                field = field + c;
            }
        }
        res.add( field );
        return res;
    }
    /**
     * It verify that all lines has been read.
     * @return It return true if all lines of the file has been read.
     */
    public boolean isEOF(){
        if(mLineIndex < 0){
            if( ! open() ){
                return true;
            }
        }
        if( mLineIndex >= this.maLines.size() - 1){
            return false;
        }
        return true;
    }
    /**
     * It go to the next line of data.
     * @return It is true if the next line exists.
     */
    public boolean next(){
        if( this.mLineIndex < 0){
            if( ! open() ){
                return false;
            }
        }
        if( this.mLineIndex >= this.maLines.size() -1 ){
            return false;
        }
        this.mLineIndex++;
        if( this.mLineIndex >= this.maLines.size() ){
            return false;
        }
        
        return true;
    }
    /**
     * It return the data corresponding to the index of the column.
     * @param index It is the index column of the data to be get.
     * @return It is the data of the specified column, if the column not exists then return null.
     */
    public String get(int index){
        if( this.mLineIndex < 0 ){
            if( !this.open() ){
                return null;
            }
        }
            
        if( this.isEOF() ){
            return null;
        }
        ArrayList<String> data = this.maLines.get(this.mLineIndex);
        if( index < 0 ){
            return null;
        }
        if( data == null ){
            return null;
        }
        if( index >= data.size() ){
            return null;
        }
        return data.get(index);
    }
    /**
     * 
     * @param column
     * @return 
     */
    public String get( String column ){
        
        if( this.mLineIndex < 0 ){
            if( !this.open() ){
                return null;
            }
        }
        int pos = getColumnIndex( column );
        if( pos < -1 ){
            return null;
        }
        if( this.isEOF() ){
            return null;
        }
        String data = this.maColumns.get(pos);
        return data;
    }
    /**
     * It return the index of a name of column.
     * @param columnName It is the name of the column.
     * @return It is the index of the column, if the column not exists then return -1.
     */
    public int getColumnIndex( String columnName ){
        if( columnName == null ){
            return -1;
        }
        columnName = columnName.trim();
        if( this.maColumns == null ){
            return -1;
        }
        int pos = 0;
        for( String e : maColumns ){
            if( e == null ){
                continue;
            }
            e = e.trim();
            if( e.equalsIgnoreCase( columnName ) ){
                return pos;
            }
            pos++;
        }
        return -1;
    }
}
