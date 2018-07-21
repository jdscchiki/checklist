/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception;

import com.jbadcode.checklist.log.exception.codes.ExceptionListEnum;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author segurajd
 */
public class ApplicationExceptionBuilder {

    private final Throwable throwable;

    private final String transactionId;

    private static final Logger LOGGER = Logger.getLogger(ApplicationExceptionBuilder.class.getName());

    public ApplicationExceptionBuilder(Throwable throwable) {
        this.throwable = throwable;
        this.transactionId = "";
    }

    public ApplicationExceptionBuilder(Throwable throwable, String transactionId) {
        this.throwable = throwable;
        this.transactionId = transactionId;
    }

    private String writeMessage(String message) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Transaction=");
        stringBuilder.append(transactionId);
        if (message != null && !message.isEmpty()) {
            stringBuilder.append(" ; ");
            stringBuilder.append("message=");
            stringBuilder.append(message);
        }
        return stringBuilder.toString();
    }

    public ApplicationExceptionBuilder handle(Class<? extends Throwable> t, ExceptionListEnum exceptionCode, String message, Level level) throws Throwable {
        if (t.isInstance(throwable)) {
            ApplicationException ae = new ApplicationException(
                    exceptionCode,
                    writeMessage(message),
                    throwable);
            LOGGER.log(level,
                    writeMessage(message),
                    ae);
            throw ae;
        }
        return this;
    }

    public ApplicationExceptionBuilder handle(Class<? extends Throwable> t, ExceptionListEnum exceptionCode, String message) throws ApplicationException {
        if (t.isInstance(throwable)) {
            ApplicationException ae = new ApplicationException(
                    exceptionCode,
                    writeMessage(message),
                    throwable);
            LOGGER.log(Level.WARNING,
                    writeMessage(message),
                    ae);
            throw ae;
        }
        return this;
    }

    public ApplicationExceptionBuilder handle(Class<? extends Throwable> t, ExceptionListEnum exceptionCode) throws ApplicationException {
        if (t.isInstance(throwable)) {
            ApplicationException ae = new ApplicationException(
                    exceptionCode,
                    writeMessage(""),
                    throwable);
            LOGGER.log(Level.WARNING,
                    writeMessage(""),
                    ae);
            throw ae;
        }
        return this;
    }

    public ApplicationExceptionBuilder handle(Class<? extends Throwable> t) throws ApplicationException {
        if (t.isInstance(throwable)) {
            ApplicationException ae = new ApplicationException(
                    writeMessage(""),
                    throwable);
            LOGGER.log(Level.WARNING,
                    writeMessage(""),
                    ae);
            throw ae;
        }
        return this;
    }

    public ApplicationExceptionBuilder handleWithoutLog(Class<? extends Throwable> t, ExceptionListEnum exceptionCode) throws ApplicationException {
        if (t.isInstance(throwable)) {
            throw new ApplicationException(exceptionCode);
        }
        return this;
    }

    public void handleDefault() throws ApplicationException {
        ApplicationException ae = new ApplicationException(
                writeMessage(""),
                throwable);
        LOGGER.log(Level.SEVERE,
                writeMessage(""),
                ae);
        throw ae;
    }

}
