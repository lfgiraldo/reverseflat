/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.exception;

/**
 *
 * @author Pipe
 */
public class NonExistentUserException extends Exception{

    public NonExistentUserException(String message) {
        super(message);
    }

}
