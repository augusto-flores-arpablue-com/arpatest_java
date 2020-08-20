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
import java.util.*;
import java.io.*;
public class Section {
    public static String DEFAULT_NAME = "<NONE>";
    private ArrayList mFields = null;
    private String mName=null;
    
    public Section(){
        this(null);
    }
    public Section(String name) {
        setName(name);
        mFields = new ArrayList();
    }
    /**
     * It set on forma or the corresponding name of the section, depending of the 
     * structure of the name, if the name is null of empty then the method return
     * the default name.
     * @param name It is the name of the section.
     * @return It return the corresponding name for a section.
     */
    public static final String setNameOnFormat( String name ){
        if( name == null){
            name = DEFAULT_NAME;
            return name;
        }
        if (name.indexOf("[") > -1) {
            name = name.substring(name.indexOf("[") + 1);
        }
        if (name.indexOf("]") > -1) {
            name = name.substring(0, name.indexOf("]"));
        }
        name = name.replace("]", " ");
        name = name.replace("[", " ");
        name = name.trim();
        if( name.length() < 1 ){
            name = Section.DEFAULT_NAME;
        }
        return name;
        
    }
    /**
     * It specify the name of the current section.
     * @param name it is the name of the current section, if the name is value then this will be the default section.
     */
    public void setName(String name){
        if(name == null){
            name = DEFAULT_NAME;
        }
        name = name.trim();
        mName = name;
    }
    /**
     * It return the current name of the current section.
     * @return It is the name of the current section.
     */
    public String getName(){
        return mName;
    }
    /**
     * It verify if the current name of the section is has the same name.
     * @param name It is the text to verify if the section has this name.
     * @return It is true if the section has the same name.
     */
    public boolean equalName(String name){
        if( name == null ){
            name = DEFAULT_NAME;
        }
        name = name.trim();
        return name.equalsIgnoreCase( this.getName() );
    }
    /**
     * It add a new field, an empty field to the section.
     * @param field It is the name of the new field.
     */
    public void add(String field) {
        Field dupla = new Field(field);
        add(dupla);
    }
    /**
     * It add  anew field to the section, if a field with the same name exists 
     * then another field is created with the same name.
     * @param field this is the name of the field.
     */
    public void add(Field field) {
        mFields.add(field);
    }
    /**
     * It return the value of a field.
     * @param field It is the name of the field.
     * @return It is the value assigned to the field.
     */
    public String getValue(String field) {
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if ( var.getName().equalsIgnoreCase(field) ) {
                return var.getValue();
            }
            
        }
        return null;

    }
    /**
     * It return an array with the names of the field.
     * @return It is the array with the name of the fields.
     */
    public String [] getFieldList() {
        String [] res = new String [mFields.size()];
        for (int i = 0; i < mFields.size(); i++) {
           res [i] = ((Field) mFields.get(i)).getName();
        }
        return res;
    }
    /**
     * It return the values of all values assigned to a field, in the case that 
     * two or more fields has the same name.
     * @param field It is the name of the field.
     * @return It is the list of values of the fields that has the same name.
     */
    public String [] getValueList(String field){
        String [] res = null;
        ArrayList values =new ArrayList();
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if (var.getName().equalsIgnoreCase(field)) {
                values.add(var.getValue());
            }
        }
        res = new String [values.size()];
        
        for (int i = 0; i < res.length; i++) {
            res[i]=(String)values.get(i);
        }
       
        return res;
    }
    /**
     * This sect a value to a specified field, if the field not exists then a it
     * is created.
     * @param field It is the field where the new value will be assigned.
     * @param value It is the new value.
     */
    public void setValue(String field, String value)
    {
        Field var;
        boolean flag = false;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            flag = var.isEqual(field);
            if (flag) {
                var.setValue(value);
                return;
            }
        }        
        mFields.add(new Field(field,value));
    }
    /**
     * It add a group of field with the same name with each value in the value array,
     * the number of field to assign the new values, it corresponding to the size 
     * of the value array. It the quantity the values is bigger than the number of fields
     * then new fields are added with the other values.
     * @param field It change the current values of the fields with the same name.
     * @param value It is the array with the new values.
     */
    public void setValue(String field, String[] value)
    {
        int pos=0;
        Field var;
        for (int i = 0; i < mFields.size(); i++) {
            var = (Field) mFields.get(i);
            if (var.getName().equalsIgnoreCase(field)) {
                var.setValue(value[pos]);
                pos++;
            }
        }
        for (int i = pos; i < value.length; i++) {
            mFields.add(new Field(field,value[i]));
        }
    }
    /**
     * It add a value to the field, no matter is another field exists with the 
     * same name.
     * @param field It is the name of the field.
     * @param value It is the value assigned to the field.
     */
    public void addValue(String field, String value)
    {
        mFields.add(new Field(field,value));
    }
    /**
     * It add a value to the field, no matter is another field exists with the 
     * same name, the parameters are in a string with the format: field=value, this
     * string is parsed to add the new field.
     * @param field It is the string to be parsed.
     */
    public void addValue(String field)
    {
        mFields.add(new Field(field));
    }
    /**
     * It add a value to the field, no matter is another field exists with the 
     * same name.
     * @param field It is the name of the filed.
     * @param value It is the assigned value.
     */
    public void addValue(String field, String [] value)
    {
        for (int i = 0; i < value.length; i++) {
            mFields.add(new Field(field,value[i]));
        }
    }
    @Override
    public String toString()
    {
        String res = "";
        res=res+mName+": ";
        for (int i = 0; i < mFields.size(); i++) {
            res=res+((Field)mFields.get(i)).toString();
            if(i<mFields.size()-1)
            {
                res=res+",";
            }
        }

        return res;
    }
    /**
     * It write the content of the current section in the RandomAccessFile
     * @param file It is the object where the section will be written.
     */
    public void toFile(RandomAccessFile file)
    {
        try {
            if( file == null ){
                return;
            }
            file.writeBytes("[" + mName + "]\r\n");
        } catch (IOException ex) {
            ArpaTest.getInstance().getLog().error("(Section-toFile): "+ex.getMessage());
        }
        Field elem = null;
        for (int i = 0; i < mFields.size(); i++) {
            elem=(Field)mFields.get(i);
            elem.toFile(file);
        }

    }
    /**
     * It return the quantity if fields that this section contains.
     * @return It is the quantity of fields.
     */
    public int size(){
        return mFields.size();
    }
    /**
     * Verify if a parameter exists in the current level.
     * @param parameter It is the name of the parameter.
     * @return It is true if the parameter exists in the current section.
     */
    public boolean exists(String parameter) {
        if( parameter == null ){
            return false;
        }
        Field field = null;
        parameter = parameter.trim();
        for( Object couple : this.mFields){
            field = (Field) couple;
            if( field.getName().equalsIgnoreCase(parameter) ){
                return true;
            }
        }
        return false;
    }
}
