/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.inifiles;


/**
 *
 * @author socrates flores
 */
import com.arpablue.arpatest.ArpaTest;
import java.io.*;
/**
 * This class group a field with a specific value
 * @author Socrates augusto Flores Ayala
 */
public class Field {
    protected String mName=null;
    protected String mValue=null;
    /**
     * Create a field with a specific name and value;
     * @param field the field name
     * @param value the value of the field
     */
    public Field(String field, String value)
    {
        this.setName(field);
        this.setValue(value);
    }
    /**
     * Convert the object in a string with specific format: <b>fieldName</b>=<b>fieldValue<b>
     * @return
     */
    @Override
    public String toString()
    {
        if( this.getValue() == null){
            return this.getName()+"=";
        }
        return this.getName()+"="+this.getValue().trim();
    
    }
    public Field(String field)
    {
        parseIn(field);
    }
    public Field()
    {
        this(null);
    }
    /**
     * It return the name of the current field.
     * @return It is the name of the current field.
     */
    public String getName() {
        return mName;
    }
    /**
     * It specify the name of the current field.
     * @param field It is the new name for the current field.
     */
    public void setName(String field) {
        if(field != null){
            field = field.trim();
        }
        this.mName = field;
    }
    /**
     * It return the current value of the field.
     * @return It is the value of the current value.
     */
    public String getValue() {
        return mValue;
    }
    /**
     * It specify the new value for the current field.
     * @param value It is the new value for the current field.
     */
    public void setValue(String value) {
        if( value != null){
            value = value.trim();
            if( value.length() < 1){
                value = null;
            }
        }
        this.mValue = value;
    }
    /**
     * It get the name and the value from a string with the following format: file_name=Field_value.
     * @param target It is the string that is necessary parse.
     * @return It return true if the string has been parsed successfully.
     */
    public boolean parseIn(String target)
    {
        if (target == null)
            return false;
        if(target.length()<1)
            return false;
        int pos=target.indexOf("=");
        if(pos < 0)
        {
            setName(target);
            return true;
        }
        setName(target.substring(0, pos));
        setValue(target.substring(pos+1,target.length()));
        return true;
    }
    /**
     *  It write the file in a RandonmAccessFile object.
     * @param file It is the RandomAccessFile object where the field will be written.
     */
    public void toFile(RandomAccessFile file){
        try {
            if( file == null ){
                return;
            }
            file.writeBytes("\t"+this.toString() + "\r\n");
        } catch (Exception ex) {
            ArpaTest.getInstance().getLog().error("(Field - toFile): "+ex.getMessage());
        }
    }
    /**
     * It verify if the name of the current field is the same of the specified name.
     * @param name If is the name to verify if is the name.
     * @return It is true if the name is the same of the current field.
     */
    public boolean isEqual(String name){
        if ( ( name == null ) && (this.getName() == null) ){
            return true;
        }
        if ( name == null ){
            return false;
        }
        if ( this.getName() == null ){
            return false;
        }
        name = name.trim();
        return this.getName().equalsIgnoreCase( name );
    }
}
