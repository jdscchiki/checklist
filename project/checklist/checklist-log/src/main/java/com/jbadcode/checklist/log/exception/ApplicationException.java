/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception;

/**
 *
 * @author segurajd
 */
public class ApplicationException extends Exception {

    /**
     * Creates a new instance of <code>ApplicationException</code> without
     * detail message.
     */
    public ApplicationException() {
    }

    /**
     * Constructs an instance of <code>ApplicationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

}
