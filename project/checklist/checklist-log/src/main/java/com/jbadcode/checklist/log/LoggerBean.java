/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.ApplicationExceptionBuilder;
import com.jbadcode.checklist.log.exception.codes.ExceptionListEnum;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
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

    private String transactionId;

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public void throwException(ExceptionListEnum exceptionCode) throws ApplicationException{
        throw new ApplicationException(exceptionCode);
    }
    
    public void throwException(ExceptionListEnum exceptionCode, String message) throws ApplicationException{
        throw new ApplicationException(exceptionCode, message);
    }

    public ApplicationExceptionBuilder catchException(Throwable throwable) {
        if ((throwable instanceof EJBTransactionRolledbackException)
                || (throwable instanceof EJBException)) {
            return new ApplicationExceptionBuilder(throwable.getCause(), transactionId);
        } else {
            return new ApplicationExceptionBuilder(throwable, transactionId);
        }
    }
}
