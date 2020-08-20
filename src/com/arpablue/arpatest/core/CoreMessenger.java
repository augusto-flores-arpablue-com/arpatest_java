/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.core;

import com.arpablue.arpatest.ArpaTest;
import com.arpablue.arpatest.interfaces.IMessenger;

/**
 *
 * @author user-pc
 */
public class CoreMessenger extends CoreBase implements IMessenger {
    
    
    @Override
    public void title(String text) {
        this.getLog().title(text);
    }

    @Override
    public void area(String text) {
        this.getLog().area(text);
    }

    @Override
    public void body(String text) {
        this.getLog().body(text);
    }

    @Override
    public void step(String text) {
        this.getLog().step(text);
    }

    @Override
    public void section(String text) {
        this.getLog().section(text);
    }

    @Override
    public void action(String text) {
        this.getLog().action(text);
    }

    @Override
    public void cmd(String text) {
        this.getLog().cmd(text);
    }

    @Override
    public void inst(String text) {
        this.getLog().inst(text);
    }

    @Override
    public void msg(String text) {
        this.getLog().msg(text);
    }

    @Override
    public void message(String text) {
        this.getLog().message(text);
    }

    @Override
    public void err(String text) {
        this.getLog().err(text);
    }

    @Override
    public void wrong(String text) {
        this.getLog().wrong(text);
    }

    @Override
    public void warning(String text) {
        this.getLog().warning(text);
    }

    @Override
    public void oops(String text) {
        this.getLog().oops(text);
    }

    @Override
    public void mistake(String text) {
        this.getLog().mistake(text);
    }

    @Override
    public void success(String text) {
        this.getLog().success(text);
    }

    @Override
    public void complete(String text) {
        this.getLog().complete(text);
    }

    @Override
    public void finished(String text) {
        this.getLog().finished(text);
    }

    @Override
    public void pass(String text) {
        this.getLog().pass(text);
    }

    @Override
    public void fail(String text) {
        this.getLog().fail(text);
    }

    @Override
    public void error(String text) {
        this.getLog().error(text);
    }

    @Override
    public void block(String text) {
        this.getLog().block(text);
    }

    @Override
    public void notApplicable(String text) {
        this.getLog().notApplicable(text);
    }

    @Override
    public void deprecated(String text) {
        this.getLog().deprecated(text);
    }
    
    
}
