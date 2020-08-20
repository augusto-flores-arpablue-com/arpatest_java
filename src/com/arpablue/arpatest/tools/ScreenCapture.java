/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.tools;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author user-pc
 */
public class ScreenCapture {
        
    /**
     * It method return the rectangle associated to 2 coordinates
     * @param x1 It is the initial X coordinate.
     * @param y1 It is the initial Y coordinate.
     * @param x2 It is the final X coordinate.
     * @param y2 It is the final Y coordinate.
     * @return It is the rectangle that covert the area of the specified points.
     */
    public static Rectangle getRectangle( int x1, int y1, int x2, int y2){
        Rectangle res = null;
        int aux = 0;
        
        if( x1 > x2){
            aux = x1;
            x1 = x2;
            x2 = aux;
        }
        if( y1 > y2){
            aux = y1;
            y1 = y2;
            y2 = aux;
        }
        res = new Rectangle(x1, y1, x2 - x1, y2 - y1);
        return res;
    }
    public static boolean saveImageToFile(String path, BufferedImage bufferedImage){
        try {
            if( bufferedImage == null){
                return false;
            }
            // Save as PNG
            File file = new File(path);
            ImageIO.write(bufferedImage, "png", file);
            return true;
        } catch (IOException e) {
            System.out.println("( CaptureScreen - saveImageToFile ): Could not save small screenshot " + e.getMessage());
        }
        return false;
    }
    /**
     * It capture an area of the screen an save this capture in a PNG file.
     * @param path It is the file where the file will be saved.
     * @param x It is the initial X coordinate.
     * @param y It is the initial Y coordinate.
     * @param xf It is the final X coordinate.
     * @param yf It is the final Y coordinate.
     * @return It return true if the save process finished without problems
     */
    public static boolean getScreenAreaToFile(String path, int x, int y, int xf, int yf){
        Rectangle area = getRectangle(x, y, xf, yf);
        return  getScreenAreaToFile( path, area );
    }
    /**
     * It save an area of the screen in a file.
     * @param path
     * @param area
     * @return 
     */
    public static boolean getScreenAreaToFile(String path, Rectangle area){
        BufferedImage bufferedImage;
        bufferedImage = getScreenArea( area );
        return saveImageToFile( path, bufferedImage );
    }
    
    /**
     * It return the image specified in two points in the screen.
     * @param x It is the initial X coordinate.
     * @param y It is the initial Y coordinate.
     * @param xf It is the final X coordinate.
     * @param yf It is the final Y coordinate.
     * @return It is the area of the screen taken, if problem happens .
     */
    public static BufferedImage getScreenArea(int x, int y, int xf, int yf){
        Rectangle area = getRectangle(x, y, xf, yf);
        return  getScreenArea( area );
    }
    /**
     * It return the image of an area in the screen.
     * @param area It is the area where the image will be taken.
     * @return It is an image of a section of the screen.
     */
    public static BufferedImage getScreenArea(Rectangle area){
        try{
            Robot robot = new Robot();
            BufferedImage bufferedImage = robot.createScreenCapture(area);
            return bufferedImage;
        } catch (AWTException e) {
            System.out.println("( CaptureScreen - getScreenArea ): Could not capture screen " + e.getMessage());
        }
        return null;
    }
    /**
     * It save a image of the screen in a PNG file.
     * @param path It is the path of the file where the image will be saved.
     * @return It is true if the image has been saved successfully.
     */
    public static boolean getFullScreenAreaToFile(String path){
        BufferedImage bufferedImage;
        bufferedImage = getFullScreenArea();
        return saveImageToFile( path, bufferedImage );
    }
    /**
     * It return a image of the current screen.
     * @return It is the image of the screen, if an error happens then the method return null.
     */
    public static BufferedImage getFullScreenArea(){
        try{
            Robot robot = new Robot();
            Rectangle area = new Rectangle( Toolkit.getDefaultToolkit().getScreenSize() );
            BufferedImage bufferedImage = robot.createScreenCapture( area );
            return bufferedImage;
        } catch (AWTException e) {
            System.out.println("( CaptureScreen - getScreenArea ): Could not capture screen " + e.getMessage());
        }
        return null;
    }

}
