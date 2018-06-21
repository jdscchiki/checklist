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
@javax.ejb.ApplicationException(rollback = true)
public class RollBackApplicationException extends ApplicationException {

    /**
     * Creates a new instance of <code>RollBackException</code> without detail
     * message.
     */
    public RollBackApplicationException() {
    }

    /**
     * Constructs an instance of <code>RollBackException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RollBackApplicationException(String msg) {
        super(msg);
    }

    public RollBackApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollBackApplicationException(Throwable cause) {
        super(cause);
    }
    
    
}
