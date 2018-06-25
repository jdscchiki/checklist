/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log;

import com.jbadcode.checklist.log.exception.ApplicationException;
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

    Class baseClazz;

    public void setBaseClazz(Class baseClazz) {
        this.baseClazz = baseClazz;
    }

    public void log(String transactionId, String message) {
        if (baseClazz == null) {
            Logger.getGlobal().log(Level.WARNING, message);
        } else {
            Logger.getLogger(baseClazz.getName()).log(Level.WARNING, message);
        }
    }

    public void log(String transactionId, String message,
            ApplicationException applicationException) throws ApplicationException {
        if (baseClazz == null) {
            Logger.getGlobal().log(Level.WARNING, message, applicationException);
            throw applicationException;
        } else {
            Logger.getLogger(baseClazz.getName()).log(Level.WARNING, message, applicationException);
            throw applicationException;
        }
    }

    public void log(String transactionId,
            String message,
            Class clazz) {
        Logger.getLogger(clazz.getName()).log(Level.WARNING, message);
    }

    public void log(String transactionId,
            String message,
            Class clazz,
            ApplicationException applicationException) throws ApplicationException {
        Logger.getLogger(clazz.getName()).log(Level.WARNING, message, applicationException);
        throw applicationException;
    }
}
