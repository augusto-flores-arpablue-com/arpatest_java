/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.tools;

/**
 *
 * @author user-pc
 */
public class MatTool {
    /**
     * It return the positive value of a number, if the number is negative then 
     * is changed to positive, if the number is positive then return the same number.
     * @param n It is the number to get its positive value.
     * @return It is the positive value.
     */
    public static int absoluteValue( int n ){
        if( n < 0 ){
            n = n * -1;
        }
        return n;
    }
    /**
     * It return the difference between two values.
     * @param a It is the first value
     * @param b It is the second value.
     * @return It is the difference between two values.
     */
    public static int getQuantity( int a, int b){
        int res = a - b;
        res = absoluteValue( res );
        return res;
    }

}
