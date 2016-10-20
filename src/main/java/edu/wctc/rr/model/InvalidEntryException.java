/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rr.model;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ritu
 */
public class InvalidEntryException extends Exception {
    
    public InvalidEntryException() {
    }
    
    // Constructor that accepts a message
    public InvalidEntryException(String message)
    {
        super(message);
    }
    
    public InvalidEntryException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
}
