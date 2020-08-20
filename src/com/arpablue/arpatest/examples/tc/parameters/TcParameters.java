/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.examples.tc.parameters;

import com.arpablue.arpatest.testlib.TestCase;
import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author user-pc
 */
public class TcParameters extends TestCase{

    public TcParameters(){
        super();
    }
    @Override
    protected void before() {}

    @Override
    protected void after() {
    }

    @Override
    protected void testRun() {
        msg( "The current execution is: "+this.getName() );
        File f = new File( this.getClass().getResource("/com/arpablue/arpatest/examples/res/example.js").getFile() );
        try{
        RandomAccessFile  read = new RandomAccessFile(f,"r");
        String line = "";
        do{
            line = read.readLine();
            cmd( line );
        } while ( line != null);
            
        }catch(Exception e){
            
        }
        msg("Exists: "+f.exists());
        msg("Is a file: "+f.isFile());
        
        msg("Resource folder: "+f.toString());
    }
    
}
