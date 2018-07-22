/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception;

import com.jbadcode.checklist.log.exception.codes.ExceptionListEnum;
import java.util.ArrayList;
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

    private ArrayList<Class<? extends Throwable>> passThrowable;

    public ApplicationExceptionBuilder(Throwable throwable) {
        this.throwable = throwable;
        this.transactionId = "";
        this.passThrowable = new ArrayList<>();
    }

    public ApplicationExceptionBuilder(Throwable throwable, String transactionId) {
        this.throwable = throwable;
        this.transactionId = transactionId;
        this.passThrowable = new ArrayList<>();
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
        if (t.isInstance(throwable)
                && !passThrowable.contains(throwable.getClass())) {
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
        if (t.isInstance(throwable)
                && !passThrowable.contains(throwable.getClass())) {
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
        if (t.isInstance(throwable)
                && !passThrowable.contains(throwable.getClass())) {
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
        if (t.isInstance(throwable)
                && !passThrowable.contains(throwable.getClass())) {
            ApplicationException ae = new ApplicationException(
                    writeMessage(""),
                    throwable);
            LOGGER.log(Level.WARNING,
                    writeMessage(""),
                    ae);
        }
        return this;
    }

    public ApplicationExceptionBuilder pass(Class<? extends Throwable> t) throws ApplicationException {
        if (t.isInstance(throwable)
                && !passThrowable.contains(throwable.getClass())) {
            passThrowable.add(t);
        }
        return this;
    }
    
    public ApplicationExceptionBuilder transform(ExceptionListEnum from, ExceptionListEnum to) throws ApplicationException {
        if (throwable instanceof ApplicationException) {
            ApplicationException currentException = (ApplicationException)throwable;
            if(currentException.getExceptionCode().equals(from)){
                throw new ApplicationException(to, throwable);
            }
        }
        return this;
    }

    public ApplicationExceptionBuilder rethrow(ExceptionListEnum... exceptionCodes) throws ApplicationException {
        if (throwable instanceof ApplicationException) {
            ApplicationException currentException = (ApplicationException)throwable;
            for (ExceptionListEnum exceptionCode : exceptionCodes) {
                if(currentException.getExceptionCode().equals(exceptionCode)){
                    throw currentException;
                }
            }
        }
        return this;
    }

    public ApplicationExceptionBuilder handleWithoutLog(Class<? extends Throwable> t, ExceptionListEnum exceptionCode) throws ApplicationException {
        if (t.isInstance(throwable)
                && !passThrowable.contains(throwable.getClass())) {
            throw new ApplicationException(exceptionCode);
        }
        return this;
    }

    public void handleDefault() throws ApplicationException {
        if (!passThrowable.contains(throwable.getClass())) {
            ApplicationException ae = new ApplicationException(
                    writeMessage(""),
                    throwable);
            LOGGER.log(Level.SEVERE,
                    writeMessage(""),
                    ae);
            throw ae;
        }
    }

}
