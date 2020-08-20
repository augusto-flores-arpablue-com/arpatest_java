/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.testlib;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.exception.*;
import com.arpablue.arpatest.tools.MatTool;
import com.arpablue.tools.StringManager;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/**
 *
 * @author user-pc
 */
public abstract class TestCase extends TestObject{
    public TestCase(){}
    public TestCase( String name){
        this.setName(name);
        
    }

    /**
     * It is the execution of the content of the test case
     */
    protected void testExe() {
        try{
            area("BEFORE THE EXECUTION");
            before();
            area( this.getClass().getSimpleName()+": "+this.getName());
            testRun();
            pass("The test has been executed without problems.");
            
        }catch(TestException e){
            
            this.setStatusCode( e.getExceptionCode() );
            this.setMessage( e.getMessage() );
            
        }catch(Exception e){
            this.setStatusCode( TestBase.TEST_STATUS_FAIL );
            this.setMessage( e.getClass().getSimpleName() + " - " + e.getMessage() );
            err(e.getClass().getSimpleName() + " - " + e.getMessage());
            msg(e.getStackTrace());
        }
        finally{
            try{
                area("AFTER THE EXECUTION");
                after();
            }catch(TestException e){
                this.setStatusCode( e.getExceptionCode() );
                this.setMessage( e.getMessage() );
            }catch(Exception e){
                this.setStatusCode( TestBase.TEST_STATUS_FAIL );
                this.setMessage( e.getMessage() );
                err(e.getMessage());
                msg(e.getStackTrace());
            }
            this.takeExecutionTime();
            area("TEST RESULT");
            msg("TEST FINAL STATUS: "+this.getStatus());
            msg("     TEST MESSAGE: "+this.getMessage());
            msg("      ELAPSE TIME: "+TestBase.getTimeString( this.getExecutiontionTime() ) );
        }
    }
    @Override
    public String toJSON(){
        String res = "{";
        res = res + "\"name\":\""+this.getName()+"\",";
        res = res + "\"status\":\""+this.getStatus()+"\",";
        res = res + "\"message\":\""+this.getMessage()+"\",";
        res = res + "\"executionTime\":\""+this.getExecutionTimeString()+"\",";
        res = res + "\"type\":\""+this.getType()+"\"";
        res = res + "}";
        return res;
    }
    @Override
    public String getType(){
        return "Test Case";
    }
    /**
     * It return image of the screen or all screens.
     * @return It is a image of all screens.
     */
    public static BufferedImage screenShot(){
        try{
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] screens = ge.getScreenDevices();
            Rectangle allScreenBounds = new Rectangle();
            for (GraphicsDevice screen : screens) {
                Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();

                allScreenBounds.width += screenBounds.width;
                allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            }

            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);
            return screenShot;
        }catch( Exception e ){
            ArpaTest.println("ERROR( TestCase - screenShot ): "+e.getMessage());
        }
        return null;
    }
    /**
     * It return a image from a section of the screen.
     * @param x1 It is the first point of the axis X.
     * @param y1 It is the first point of the axis Y.
     * @param x2 It is the second point of the axis X.
     * @param y2 It is the second point of the axis Y.
     * @return It is the image that corresponding to the specified coordinate.
     */
    public static BufferedImage screenShot( int x1, int y1, int x2, int y2){
        BufferedImage res;
        int aux;
        try{
            if( x1 > x2 ) {
                aux = x1;
                x2 = x1;
                x1 = aux;
            }
            if( y1 > y2 ) {
                aux = y1;
                y2 = y1;
                y1 = aux;
            }
            
            int w = MatTool.getQuantity(x1, x2);
            int h = MatTool.getQuantity(y1, y2);
            if( w == 0 ){
                w = 1;
            }
            if( h == 0 ){
                h = 1;
            }
            BufferedImage img = screenShot();
            if( img == null ){
                return null;
            }

            res = img.getSubimage(x1, y1, w, h);
            if( res == null ){
                return null;
            }
            return res;
        }catch( Exception e ){
            ArpaTest.println("ERROR( TestCase - screenShot ): "+e.getMessage());
        }
        return null;
    }
    /**
     * It save a image in a file in the local drive.
     * @param img It s the image object to be saved.
     * @param pathFile It is the path file where the file will be created.
     * @return It is true if the image has been saved without problems.
     */
    public static boolean saveImage( BufferedImage img, String pathFile ){
        try{
            if( img == null ){
                ArpaTest.println("It is not possible save a NULL image.");
                return false;
            }
            if( pathFile == null ){
                ArpaTest.println("It is not possible save a NULL image.");
                return false;
            }
            pathFile = StringManager.setFormatPath( pathFile );
            if( pathFile.length() < 1){
                ArpaTest.println("It is not possible save a NULL image.");
                return false;
            }
            File temp = new File(pathFile+".png");
            // Use the ImageIO API to write the bufferedImage to a temporary file
            ImageIO.write( img, "png", temp );
            return true;
        }catch( Exception e){
            ArpaTest.println("ERROR( TestCase - saveImage ): "+e.getMessage());
        }
        return false;
    }
    
}
