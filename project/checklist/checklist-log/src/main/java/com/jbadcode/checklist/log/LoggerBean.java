/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.ApplicationExceptionBuilder;
import com.jbadcode.checklist.log.exception.ApplicationExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * Manage logging logic. The only reason for te existence of this ejb is because
 * in future versions of the application the log will be saved on another
 * aditional place.
 *
 * @author segurajd
 */
@Stateless
@LocalBean
public class LoggerBean {
    
    private Class baseClazz;
    private String transactionId;

    public void setBaseClazz(Class baseClazz) {
        this.baseClazz = baseClazz;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    private String writeMessage(String message) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Transaction=");
        stringBuilder.append(transactionId);
        if (message != null && !message.isEmpty()) {
            stringBuilder.append(";");
            stringBuilder.append("message=");
            stringBuilder.append(message);
        }
        return stringBuilder.toString();
    }

    public void log(String message) {
        message = writeMessage(message);
        if (baseClazz == null) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    message);
        } else {
            Logger.getLogger(
                    baseClazz.getName()
            ).log(
                    Level.WARNING,
                    message);
        }
    }

    public void log(String message,
            ApplicationException applicationException) throws ApplicationException {
        message = writeMessage(message);
        if (baseClazz == null) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    message,
                    applicationException);
            throw applicationException;
        } else {
            Logger.getLogger(
                    baseClazz.getName()
            ).log(
                    Level.WARNING,
                    message,
                    applicationException);
            throw applicationException;
        }
    }

    public void log(String message,
            Class clazz) {
        message = writeMessage(message);
        Logger.getLogger(
                clazz.getName()
        ).log(
                Level.WARNING, message);
    }

    public void log(String message,
            Class clazz,
            ApplicationException applicationException) throws ApplicationException {
        message = writeMessage(message);
        Logger.getLogger(
                clazz.getName()
        ).log(
                Level.WARNING,
                message,
                applicationException);
        throw applicationException;
    }
    
    public ApplicationExceptionBuilder logb(Throwable throwable){
        return new ApplicationExceptionBuilder(throwable);
    }
}
