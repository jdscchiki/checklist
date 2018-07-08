/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.exception;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.list.ExceptionListEnum;
import com.jbadcode.checklist.log.exception.list.UserExceptionList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBTransactionRolledbackException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Juan David Segura
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = Logger.getLogger(ApplicationExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        Throwable cause;
        if (exception instanceof EJBTransactionRolledbackException) {
            cause = exception.getCause();
        } else {
            cause = exception;
        }
        if (cause instanceof ApplicationException) {
            ApplicationException applicationException = (ApplicationException) cause;
            return Response.
                    status(transformCode(applicationException.getExceptionCode())).
                    entity(new ExceptionContainer(applicationException)).
                    build();
        } else {
            logger.log(Level.SEVERE, "", cause);
            throw new WebApplicationException(cause);
        }
    }

    private Response.Status transformCode(ExceptionListEnum exceptionListEnum) {
        if (exceptionListEnum instanceof ApplicationException) {
            return Response.Status.CONFLICT;
        } else if (exceptionListEnum instanceof UserExceptionList) {
            return Response.Status.BAD_REQUEST;
        } else {
            return Response.Status.SERVICE_UNAVAILABLE;
        }
    }
}
