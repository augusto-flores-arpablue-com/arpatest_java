/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.inifiles;

/**
 * It object manage the values from a INI file, hasa method to update create or remove field
 * and sections, the reference to the parameter use the following format:
 *  <strong>Section_Name</strong>.<strong>Field_Name</strong>
 * but is possible use only the field name, similar to: <strong>Field_Name</strong>, this means
 * that the field is in the default/none section.
 * @author Socrates Flores
 */
import com.arpablue.arpatest.ArpaTest;
import com.arpablue.tools.StringManager;
import java.util.Vector;
import java.io.*;
import java.util.ArrayList;

public class IniFile {

    ArrayList mSections = null;
    String mFile = null;
    String mError = null;
    /**
     * This constructor receive the INI file to be loaded in the structure.
     * @param file It is the full path of the INI file.
     */
    public IniFile(String file) {
        setFile(file);

    }
    /**
     * It is the default constructor.
     */
    public IniFile() {
        this(null);
    }
    /**
     * It return the respective section name, it depending the value if the name, 
     * if is null or empty then return the default value.
     * @param section It is the name of the section.
     * @return It is the correct name section.
     */
    public static String applySectionName(String section){
        if (section == null) {
            return Section.DEFAULT_NAME;
        }
        section = IniFile.getSectionName( section );
        section = section.trim();
        if( section.length() < 1 ){
            return Section.DEFAULT_NAME;
        }
        return section;
    }
    /**
     * It divide the current string in parts divide by "." character, it using the format"section.parameter".
     * @param target It is the string character.
     * @return It is an array with the substring.
     */
    public static String[] getSectionField(String target){
        String[] res = new String[2];
        if( target == null){
            return res;
        }
        ArrayList<String> v = StringManager.split( target, ".");
        if(v.size() == 0){
            return res;
        }
        if(v.size() == 1){

            res[0] = Section.DEFAULT_NAME;
            res[1] = v.get(0);
            return res;
        }else{
            res[0] = v.get(0);
            res[1] = v.get(1);
        }
        return res;
    }
    /**
     * It set on format the section. If the section is null or is an empty string, 
     * then the section will be the default section.
     * @param section It is the name of the section.
     * @return 
     */
    protected static String getSectionName(String section){
        if (section == null) {
            section = Section.DEFAULT_NAME;
        } else {
            section = StringManager.replaceAll(section,"[", "");
            section = StringManager.replaceAll(section,"]", "");
            section = section.trim();
            if( section.length() < 1 ){
                section = Section.DEFAULT_NAME;
            }
        }
        return section;
    }
    /**
     * It return any error that happen during some method execution.
     * @return It is the error message about the error, if not 
     */
    public String getError() {
        return mError;
    }
    /**
     * It specify the INI file do be manage.
     * @param file It is a full path of the INI file.
     */
    public void setFile(String file) {
        mFile = file;
        mSections = new ArrayList();
    }
    /**
     * It open the INI file.
     * @return It is true if the file has been opened without problems.
     */
    public boolean open() {
        File fFile = null;
        RandomAccessFile rLoad = null;
        String sLine = null;
        String sSection = Section.DEFAULT_NAME;
        try {
            if (mFile == null) {
                return false;
            }
            fFile = new File(mFile);
            if (!fFile.exists()) {
                return false;
            }
            rLoad = new RandomAccessFile(fFile, "r");
            
            do {
                sLine = rLoad.readLine();
                if (sLine != null) {
                    sLine = sLine.trim();
                    if(sLine.length() > 0)
                    {
                        if (sLine.indexOf("#") == 0) {
                            continue;
                        }

                        if (sLine.matches("^\\[.*\\].*")) {
                            sSection = addSection(sLine);
                        } else {
                            addField(sSection, sLine);
                        }
                    }
                }
            } while (sLine != null);
        } catch (Exception ex) {
            mError = ex.getMessage();
            ArpaTest.getInstance().getLog().error("(IniFile - open)" + mError);
            return false;
        } finally {
            try {
                if (rLoad != null) {
                    rLoad.close();
                }
            } catch (Exception ex2) {
                ArpaTest.getInstance().getLog().error("(IniFile - open): " + ex2.getMessage());
            }
        }
        return true;
    }
    /**
     * It open a INI file,
     * @param file It is the full path of the INI file.
     */
    public boolean open(String file) {
        mFile = file;
        boolean res = open();
        
        return res;
    }
    /**
     * It save the INI structure in a specific INI file.
     * @param file It is the full path of the INI file.
     */
    public void save(String file) {
        mFile = file;
        save();
    }
    /**
     * It remove the current INI file.
     * @return It is true if the file has been deleted successfully.
     */
    protected boolean removeFile(){
        try{
            if( mFile == null ){
                return false;
            }
            File f = new File( mFile );
            if( f.exists() && f.isFile() ){
                return f.delete();
            }
        }catch( Exception e){
            
        }
        return false;
    }
    /**
     * It save the structure of the current INI file, in the current INI file.
     */
    public void save() {
        try {
            this.removeFile();
            RandomAccessFile f = new RandomAccessFile(mFile, "rw");
            Section elem = null;
            for (int i = 0; i < mSections.size(); i++) {
                elem = (Section) mSections.get(i);
                elem.toFile(f);
            }
            f.close();
        } catch (Exception ex) {
            ArpaTest.getInstance().getLog().error("(IniFile - save): " + ex.getMessage());
        }
    }
    /**
     * It verify the section exists in the current IniFile Structure.
     * @param section It is the name of the section.
     * @return It is true if the section exists.
     */
    public boolean exists(String section){
        if( section == null){
            section = Section.DEFAULT_NAME;
        }else{
            section.replace("[", " ");
            section.replace("]", " ");
            section = section.trim();
            if( section.length() < 1){
                section = Section.DEFAULT_NAME;
            }
        }
        Section sec = this.getSection();
        if( sec == null ){
            return false;
        }
            
        return false;
    }
    
    /**
     * It add a section to the current structure, if the another section exists 
     * then it is not add.
     * @param line this contain the current section.
     * @return It return the current name of the section.
     */
    public String addSection(String line) {
        String sSection = null;
        Section section = null;
        
        sSection = Section.setNameOnFormat(line);
        section = this.getSection(sSection);
        if( section != null ){
            return sSection;
        }
        section = new Section( sSection );
        this.addSection(section);
        return sSection;
            
    }
    
    protected void addSection( Section section){
        if( section ==null){
            return;
        }
        this.mSections.add( section );
        
    }
    /**
     * It add a field in an specific field.
     * @param section It is the name of the section.
     * @param line It is the filed a its value to added to the section.
     */
    public void addField(String section, String line) {
        Section object = null;
        section = IniFile.applySectionName( section );
        line = line.trim();
        if (line.indexOf("#") > -1) {
            line = line.substring(0, line.indexOf("#") - 1);
        }
        if (line.length() < 1) {
            return;
        }
        for (int i = 0; i < mSections.size(); i++) {
            object = (Section) mSections.get(i);
            if (object.getName().equalsIgnoreCase(section)) {
                object.addValue(line);
                return;
            }

        }
        Section sec = new Section();
        sec.addValue(line);
        this.addSection(sec);
    }
    /**
     * It added a field to the respective section.
     * @param section It is the name of the current section.
     * @param field It is the name of the field.
     * @param value It is the new value for the field.
     */
    public void addField(String section, String field, String value)  {
        section = IniFile.applySectionName( section );
        if (field == null) {
            return;
        } else {
            field = field.trim();
        }
        if (value == null) {
            value = "";
        }
        Section object = null;
        for (int i = 0; i < mSections.size(); i++) {
            object = (Section) mSections.get(i);
            if (object.getName().equalsIgnoreCase(section)) {
                object.addValue(field, value);
                return;
            }

        }
        Section sec = new Section( section );
        sec.addValue(field, value);
        this.addSection(sec);
        
        
    }
    /**
     * It set a new value to a field in a section, if the section or the field 
     * not exist then the new section and/or field are created with the value.
     * @param section It is the section where the field will be search.
     * @param field It is the field to be search in the section.
     * @param value It is the new value for the field.
     */
    public void setField( String section, String field, String value){
        if( field == null ){
            return;
        }
        section = IniFile.applySectionName( section );
        
        Section sec = this.getSection(section);
        if( sec == null ){
            this.addField(section, field, value);
            return;
        }
        sec.setValue(field, value);
    }
    /**
     * It set a new value to a field in a section, if the section or the field 
     * not exist then the new section and/or field are created with the value.
     * @param field It is the field to be search in the section, this is in the following format: "section.field".
     * @param value It is the new value for the field.
     */
    public void setField( String field, String value){
        if( field == null ){
            return;
        }
        String[] v = IniFile.getSectionField(field);
        this.setField(v[0],v[1], value);
    }
    
    /**
     * It return the list of sections in the current structure.
     * @return It is an array with the current sections.
     */
    public String[] getSections() {
        String[] res = new String[mSections.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ((Section) mSections.get(i)).getName();
        }
        return res;

    }
    /**
     * It return the structure in a string format.
     * @return It is the structure of the section.
     */
    @Override
    public String toString() {
        String res = "";
        String car = "\r\n\t";
        if( mFile != null ){
            res = "File: " + mFile + car;
            
        }
        res = res +"Content: ";
        for (int i = 0; i < mSections.size(); i++) {
            res = res + car + "    " + ((Section) mSections.get(i)).toString();
        }
        return res;
    }
    /**
     * It return in a string an specific structure.
     * @param section It is the name of the section.
     * @return It is the string that contain the structure of the section.
     */
    public String toString(String section) {

        Section sec = getSection(section);
        if (sec != null) {
            return sec.toString();
        }
        return "";

    }
    /**
     * It return the section object from specific index.
     * @param index It is the position of the section.
     * @return It is the section objection, if is null then the section not exists.
     */
    public Section getSection(int index) {
        if (index < mSections.size()) {
            return ((Section) mSections.get(index));
        }
        return null;
    }
    /**
     * It return a section objection that corresponding to the name.
     * @param section It is the name of the section to be search.
     * @return It is the section object specified by the name, if the section 
     * not exists then the method return null.
     */
    public Section getSection(String section) {
        Section sec;
        section = IniFile.applySectionName( section );
        
        for (int i = 0; i < mSections.size(); i++) {
            sec = ((Section) mSections.get(i));
            if (sec.getName().equalsIgnoreCase(section)) {
                return sec;
            }
        }
        return null;

    }
    /**
     * It return the default section or the section of the field without a specific section.
     * @return It return the default section.
     */
    public Section getSection() {
        return getSection(null);
    }
    /** 
     * It return the value from a section and field specified.
     * @param section It is the section.
     * @param field It is the field.
     * @return It is the value if the section and field, if the value not exists then return null.
     */
    public String getField(String section, String field) {
        section = IniFile.applySectionName( section );
        Section s = this.getSection(section);
        
        if (s == null) {
            return null;
        }
        String res = s.getValue(field);
        return res;
    }
    /**
     * It return the number of the sections in the current INI structure.
     * @return 
     */
    public int size() {
        return mSections.size();
    }
    /**
     * It return the first occurrence of the field with the specific name, the field is search in all sections.
     * @param field It is the to be search in the structure.
     * @return It is the value corresponding to the first match of the field, if the field not exists then return null.
     */        
    protected String getValueByNameParameter( String field ){
        if( field == null){
            return null;
        }
        Section section = null;
        String val = null;
        for( int  i = 0; i < this.size(); i++){
            section = this.getSection(i);
            val = section.getValue(field);
            if( val != null){
                return val;
            }
        }
        return null;
    }
    /**
     * It return the value of specific parameter with format or without format. The field name can be
     * the following format: "section.field", this has reference to the the section and the field, ex:
     * 
     *      String value = getValue("<strong>MySection</strong>.<strong>MyField</strong>");
     * 
     * If the section is not specified then the value of the first field with the same name will 
     * be returned, ex.
     * 
     *      String value = getValue("<strong>MyField</strong>");
     * 
     * @param field IIt is the reference to the section and field or the on the field name.
     * @return It is the current value of the field, if is null then the field could not exists.
     */
    public String getValue(String field){
        String res = null;
        if( field == null ){
            return res;
        }
        field = field.trim();
        if( field.length() < 1 ){
            return null;
        }
        
        String[] v = IniFile.getSectionField(field);
        if( v == null ){
            return null;
        }
        return this.getField(v[0], v[1]);
    }
    /**
     * It set a value in a field from a section specified, if the section or 
     * field not exists then this is created and added.
     * @param field It is the field to be set the value, the format is [section].[field]
     * or is used [field], where is saved in the default section.
     * @param value It is the value assigned to the field.
     */
    public void setValue( String field, String value){
        if( field == null ){
            return;
        }
        String[] v = IniFile.getSectionField(field);
        this.setField(v[0],v[1], value);
    }
    /**
     * It return true if a parameter exists in a section.
     * @param section It is the name of the section.
     * @param parameter It is the name of the parameter.
     * @return It is true f the parameter exists.
     */
    public boolean fieldExists( String section, String parameter ) {
        section = IniFile.applySectionName( section );
        Section sec = this.getSection( section );
        if( sec == null ){
            return false;
        }
        return sec.exists( parameter );
    }
    /**
     * It return true if a parameter exists in a section.
     * @param parameter It is the name of the parameter.
     * @return It is true f the parameter exists.
     */
    public boolean fieldExists( String parameter ) {
        if( parameter == null ){
            return false;
        }
        String[] v = IniFile.getSectionField( parameter );
        if( v == null ){
            return false;
        }
        return this.fieldExists(v[0], v[1]);
    }
    
}
