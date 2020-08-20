/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.interfaces;

/**
 *
 * @author user-pc
 */
public interface IChannel {
    /**
     * It open the channel.
     * @return It is a true if the channel can be opened.
     */
    public boolean open();
    /**
     * It close the channel.
     * @return It return true if the channel has been closed successfully.
     */
    public boolean close();
    /**
     * It return the current state of the channel.
     * @return It return true true if the channel is opened.
     */
    public boolean isOpen();
    
}
