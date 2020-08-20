/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.core;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.testlib.TestObject;
import com.arpablue.arpatest.testlib.TestPlan;
import com.arpablue.arpatest.testlib.TestSuit;
import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;


/**
 *
 * @author user-pc
 */
public class CoreTestManagement extends CoreMessenger {
    protected TestPlan mTestPlan = null;
    protected TestSuit mTestSuit = null;
    protected String mTestSuitReference = null;
    
    /**
     * It initialize the test plan to be executed.
     */
    protected void initTestPlan(){
        if( mTestPlan == null){
            mTestPlan = new TestPlan();
        }
    }
    /**
     * It specify the name of the current execution.
     * @param name It is the name for the execution.
     */
    public void setTestPlanName( String name ){
            
        ArpaTest.println("Specifying the name of the test plan execution: "+name);
        initTestPlan();
        if( name == null ){
            return;
        }
        this.mTestPlan.setName(name);
    }
    /**
     * It return the name of the current test plan.
     * @return It is the name of the test plan.
     */
    public String  getTestPlanName(){
        initTestPlan();
        return this.mTestPlan.getName();
    }
    /**
     * It return the name of the current active test suit.
     * @return It is the name of the current test suit.
     */
    public String getTestSuitName(){
        if( this.mTestSuit == null ){
            return null;
        }
        return this.mTestSuit.getName();
    }
    /**
     * It verify if a package exists.
     * @param reference It is the reference of the package.
     * @return It is true the 
     */
    protected static boolean packageExists( String reference ){
        try{
            reference = StringManager.replaceAll(reference, ".", "/");
            return Thread.currentThread().getContextClassLoader().getResource( reference ) != null;
//            Package pkg = Package.getPackage(reference);
//            if( pkg == null ){
//                return false;
//            }
//            return true;
        }catch( Exception e ){
            ArpaTest.println("ERROR( CoreTestManagement - packageExists ): "+e.getMessage());
        }
        return false;
    }
    /**
     * It execute the package where the test case object are implemented and 
     * it will be added using the addTestCase() method.
     * @param reference It is the reference of the package.
     * @return It return true if the package has been added.
     */
    public boolean addTestSuit( String reference ){
        ArpaTest.println("Loading package reference: "+reference);
        initTestPlan();
        mTestSuitReference = null;
        if( reference == null ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestSuit ):It is not possible load a package with NULL refrence.");
            return false;
        }
        reference = reference.trim();
        String[] v = getData( reference );
        v[0] = StringManager.getLastString(v[0], "\\.");
        if( reference.length() < 1 ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestSuit ):It is not possible load a empty reference package.");
            return false;
        }
        if( !packageExists(v[1])){
            ArpaTest.println("ERROR( CoreTestManagement - addTestSuit ): The reference package ["+v[1]+"] not exists.");
            return false;
        }
        mTestSuitReference = v[1];
        mTestSuit = new TestSuit( v[0] );
       
        return addTestSuit( mTestSuit );
    }
    /**
     * It add a test suit to the test plan.
     * @param testsuit It is the Test Object to be added.
     * @return It is true if the test object has been added without problems.
     */
    public boolean addTestSuit( TestObject testsuit){
        if( testsuit == null ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestSuit ): It is not possible add a NULL test suit.");
            return false;
        }
        if( this.mTestPlan == null ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestSuit ): It is not possible add the test suit because the test plan has not been specified.");
            return false;
        }
        this.mTestPlan.add(testsuit);
        ArpaTest.println("The ["+testsuit.getName()+"] test case has been added to the ["+this.mTestSuit.getName()+"] test plan.");
        return true;
    }
    /**
     * It add a test case To the current test suit, specified addTestSuit() method.
     * @param testCase It is the test case name to be added.
     * @return It is ture if the test case has been added successfully.
     */
    public boolean addTestCase( String testCase ){
        initTestPlan();
        if( this.mTestSuitReference == null ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestCase ): It is not possible add the ["+testCase+"] test case, because the a package reference not exists.");
            return false;
        }
        TestObject target = newTestObject(testCase);
        if( target == null){
            return false;
        }
        return addTestCase( target );
    }
    /**
     * It add a test case object to the current active test suit.
     * @param testCase It is the test case suit.
     * @return It is true if the test case has been added successfully.
     */
    public boolean addTestCase( TestObject testCase ){
        
        if( testCase == null ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestCase ): It is not possible add a NULL test case.");
            return false;
        }
        if( this.mTestSuit == null ){
            ArpaTest.println("ERROR( CoreTestManagement - addTestCase ): It is not possible add the ["+testCase+"] test case, because the test suit has not been specified.");
            return false;
        }
        ArpaTest.println("Adding the ["+testCase.getName()+"] test case to the ["+this.mTestSuit.getName()+"] test suit.");
        this.mTestSuit.add( testCase );
        return true;
    }
    /**
     * It separate the name and the test object reference from a string and return 
     * an array with the name for the TestObject. The format used is:
     * 
     *  [name]:[package/class reference]
     * 
     * It return an array where, the first position of the array is the name of the 
     * TestObject and the second is the reference to the package or the class. If
     * the name is not specified then the first position is null.
     * 
     * If the : is not present then the text will be take as reference of the class
     * or package.
     * @param target It is the string with the format for the name and reference of the class or name.
     * @return It is an array with the detected arguments
     */
    protected String[] getData( String target ){
        ArrayList<String> v = null;
        String[] res = new String[2];
        res[0] = "noName";
        res[1] = "noName";
        if( target == null){
            return res;
        }
        target = target.trim();
        if( target.length() == 0 ){
            return res;
        }
        v = StringManager.split(target, ":");
        if( v.size() == 0 ){
            return res;
        }
        if( v.size() == 1 ){
            res[0] = v.get(0).trim();
            res[1] = v.get(0).trim();
        }else{
            res[0] = v.get(0).trim();
            res[1] = v.get(1).trim();
        }
        
        if( res[0].isEmpty() && res[1].isEmpty() ){
            res[0] = "noName";
            res[1] = "noName";
            return res;
        }
        if( res[0].isEmpty() ){
            res[0] = res[1];
        }
        if( res[1].isEmpty() ){
            res[1] = res[0];
        }
        return res;
    }
    /**
     * This create a new instance of a Test Object to be added to the test plan.
     * If the class not exists or it is not a TestObject then return null.
     * @param className It is a test object.
     * @return It is the new TestObject created.
     */
    protected TestObject newTestObject( String className ){
        TestObject res = null;
        String[] v =  new String[2];
        String refer = "";
        if( className == null){
            ArpaTest.println("ERROR( CoreTestManagement - newTestObject ): It is not possible add a NULL testcase to the test plan.");
            return null;
        }
        className = className.trim();
        if( className.length()<2){
            ArpaTest.println("ERROR( CoreTestManagement - newTestObject ): It is not possible add an EMPTY testcase to the test plan.");
            return null;
        }
        
        try{
            v = getData( className );
            Class target;
            refer = this.mTestSuitReference+"."+v[1];
            target = Class.forName( refer);
            Object aux = target.newInstance();
            res = (TestObject) aux;
            res.setName(v[0]);
        }catch(Exception e){
            ArpaTest.println("ERROR( CoreTestManagement - newTestObject ): It is not possible found the ["+v[1]+"] class to add to the test plan.");
        }
        ArpaTest.println("The ["+className+"] class has been found and a new object has been instanced.");
        return res;
    }
    /**
     * It return all names of the classes within to a package.
     * @param pkg It is the package to get the names of the classes.
     * @return It is an array list with the name of  classes within to the package.
     */
    protected static ArrayList<String> getClassNames(String pkg){
        ArrayList<String> res = new ArrayList<String>();
        try{
            Collection<Class> classes = getClasses(pkg);
            for( Class c: classes){
                res.add(c.getSimpleName());
            }
        }catch( Exception e){
            ArpaTest.println("ERROR( CoreTestManagement - getClassNames): "+e.getMessage());
        }
        return res;
    }
    /**
     * It return all class objects from an specific package.
     * @param pack It is the package where all classes will be obtained.
     * @return It is the collection with all classes that belong to the class.
     * @throws Exception It is when a problems appears to try to lad the classes form the object.
     */
    public static Collection<Class> getClasses(final String pack) throws Exception {
        final StandardJavaFileManager fileManager = ToolProvider.getSystemJavaCompiler().getStandardFileManager(null, null, null);
        return StreamSupport.stream(fileManager.list(StandardLocation.CLASS_PATH, pack, Collections.singleton(JavaFileObject.Kind.CLASS), false).spliterator(), false)
                .map(javaFileObject -> {
                    try {
                        final String[] split = javaFileObject.getName()
                                .replace(".class", "")
                                .replace(")", "")
                                .split(Pattern.quote(File.separator));

                        final String fullClassName = pack + "." + split[split.length - 1];
                        return Class.forName(fullClassName);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toCollection(ArrayList::new));
    }    
    /**
     * It return the name of the file.
     * @param target It is the file to get the file name.
     * @return It is the file name without the extension.
     */
    protected static String getFileName( File target ){
        if( target == null ){
            return null;
        }
        String name = target.getName();
        name = StringManager.getStringUntilLast(name, ".");
        return name;
    }
    /**
     * It read a file and add the respective test suits and test plans to be executed.
     * @param testFile It is the test plan file.
     * @return It is true if the file has been loaded successfully.
     */
    protected static boolean readTestFile( File testFile ){
        try{
            if( testFile == null){
                return false;
            }
            String planName = getFileName( testFile );
            ArpaTest.println( "Loading the test plan file: " + testFile.getAbsolutePath() );
            ArpaTest.println( "Set the test suit name: " + testFile.getAbsolutePath() );
            ArpaTest.getInstance().setTestPlanName( planName );
            
            RandomAccessFile r = new RandomAccessFile( testFile,"r");
            String line = null;
            do{
                line = r.readLine();
                if( line != null ){
                    line = line.trim();
                    if(line.length() < 1){
                        continue;
                    }
                    if( line.indexOf("#") == 0){
                        continue;
                    }
                    if( line.indexOf("[plan]")> -1){
                        line = line.replace("[plan]", "");
                        ArpaTest.println( "Set the test suit name: " + line );
                        ArpaTest.getInstance().setTestPlanName( line );
                    }else if( line.indexOf("[suit]")> -1){
                        line = line.replace("[suit]", "");
                        ArpaTest.getInstance().addTestSuit(line);
                    }else{
                        ArpaTest.getInstance().addTestCase(line);
                    }
                    
                }
            }while( line != null );
            r.close();
            return true;
        }catch( Exception  e){
            ArpaTest.println("ERROR( Core - readTestFile ): "+e.getMessage());
        }
        return false;
    }
    /**
     * It load a test file to load the test suit and the test case to be executed.
     * this method is calling if the TestProjectFile field is specified in the configTest.ini file or
     * in the command line arguments.
     * @param file It is the path file of the test plan.
     * @return It return true if the file has been uploaded successfully.
     */
    public static boolean setTestProjectFile(String file){
        if( file == null ){
            ArpaTest.println("ERROR( Core - setTestProject ): It is not possible load a NULL file.");
            return false;
        }
        file = file.trim();
        if( file.length() < 1){
            ArpaTest.println("ERROR( Core - setTestProject ): It is not possible load a NULL file.");
            return false;
        }
        file = StringManager.setFormatPath(file);
        try{
            File f = new File( file );
            if( !f.exists() ){
                ArpaTest.println("ERROR( Core - setTestProject ): It is not possible load a Test Project file, because not exists.");
                return false;
            }
            if( !f.isFile() ){
                
                ArpaTest.println("ERROR( Core - setTestProject ): It is not possible load a ["+f.getAbsolutePath()+"] Test Project file, because it is not a file.");
                return false;
            }
            return readTestFile( f );
        }catch( Exception e ){
            ArpaTest.println("ERROR( Core - setTestProject ): "+e.getMessage());
        }
        return false;
    }

    /**
     * It execute a group the test cases.
     * @return It is true if is possible execute the 
     */
    public boolean executeTest(){
        String file  = this.getParameter("TestPlanFile");
        if(file == null ){
            this.warning("( CoreTestManagement - executeTest ): The [TestPlanFile] field is not specified.");
        }else{
            this.setTestProjectFile(file);
        }
        if( this.mTestPlan == null ){
           this.fail("( CoreTestManagement - executeTest ): It is not possible execute the test plan, because the test plan object not exists.");
            return false;
        }
        this.message("Executing the test plan: "+this.mTestPlan.getName());
        this.mTestPlan.execute();
        return true;
    }
}
