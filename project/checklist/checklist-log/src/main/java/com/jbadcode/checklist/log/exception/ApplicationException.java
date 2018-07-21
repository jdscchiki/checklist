/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception;

import com.jbadcode.checklist.log.exception.codes.ExceptionListEnum;

/**
 *
 * @author segurajd
 */
public class ApplicationException extends Exception {

    private ExceptionListEnum exceptionCode;

    /**
     * Creates a new instance of <code>ApplicationException</code> without
     * detail message.
     */
    public ApplicationException() {
        super();
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

    public ApplicationException(ExceptionListEnum exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ApplicationException(ExceptionListEnum exceptionCode, String message, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public ApplicationException(ExceptionListEnum exceptionCode, Throwable cause) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public ApplicationException(ExceptionListEnum exceptionCode) {
        super();
        this.exceptionCode = exceptionCode;
    }

    public ExceptionListEnum getExceptionCode() {
        return exceptionCode;
    }
}
