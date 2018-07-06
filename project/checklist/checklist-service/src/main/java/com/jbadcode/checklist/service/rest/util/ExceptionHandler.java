/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.util;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.RollBackApplicationException;
import com.jbadcode.checklist.log.exception.list.ApplicationExceptionList;
import com.jbadcode.checklist.log.exception.list.SystemExceptionList;
import com.jbadcode.checklist.log.exception.list.UserExceptionList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBTransactionRolledbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Juan David Segura
 */
public class ExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getName());

    public static void throwWebApplicationException(Throwable e) throws WebApplicationException {
        if (e instanceof RollBackApplicationException) {
            RollBackApplicationException rbae = (RollBackApplicationException) e;
            if (rbae.getExceptionCode() instanceof UserExceptionList) {
                throw new WebApplicationException(
                        Response.
                                status(Response.Status.CONFLICT).
                                entity(transformAplicationException(rbae)).
                                build());
            } else if (rbae.getExceptionCode() instanceof ApplicationExceptionList) {
                throw new WebApplicationException(
                        Response.
                                status(Response.Status.INTERNAL_SERVER_ERROR).
                                entity(transformAplicationException(rbae)).
                                build());
            } else if (rbae.getExceptionCode() instanceof SystemExceptionList) {
                throw new WebApplicationException(
                        Response.
                                status(Response.Status.SERVICE_UNAVAILABLE).
                                entity(transformAplicationException(rbae)).
                                build());
            }
        } else if (e instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) e;
            if (ae.getExceptionCode() instanceof UserExceptionList) {
                throw new WebApplicationException(
                        Response.
                                status(Response.Status.CONFLICT).
                                entity(transformAplicationException(ae)).
                                build());
            } else if (ae.getExceptionCode() instanceof ApplicationExceptionList) {
                throw new WebApplicationException(
                        Response.
                                status(Response.Status.INTERNAL_SERVER_ERROR).
                                entity(transformAplicationException(ae)).
                                build());
            } else if (ae.getExceptionCode() instanceof SystemExceptionList) {
                throw new WebApplicationException(
                        Response.
                                status(Response.Status.SERVICE_UNAVAILABLE).
                                entity(transformAplicationException(ae)).
                                build());
            }
        } else if ((e instanceof EJBTransactionRolledbackException) && (e.getCause() instanceof ConstraintViolationException)) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            throw new WebApplicationException(
                        Response.
                                status(Response.Status.BAD_REQUEST).
                                entity(transformConstraintViolationException(cve)).
                                build());
        }
        LOGGER.log(Level.SEVERE, "", e);
        throw new WebApplicationException(e);
    }

    public static ExceptionContainer transformAplicationException(ApplicationException e) {
        return new ExceptionContainer(e);
    }
    
    public static ExceptionContainer transformConstraintViolationException(ConstraintViolationException e) {
        return new ExceptionContainer(e);
    }
}
