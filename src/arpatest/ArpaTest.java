/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arpatest;

/**
 *
 * @author user-pc
 */
public class ArpaTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        com.arpablue.arpatest.ArpaTest.setArguments(args);
//        ArpaTest.getInstance().saveSettingsFromINI("config.test.ini");
        com.arpablue.arpatest.ArpaTest.getInstance().execute();
    }
    
}
