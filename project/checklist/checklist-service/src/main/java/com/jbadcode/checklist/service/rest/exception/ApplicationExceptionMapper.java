/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.exception;

import com.jbadcode.checklist.log.exception.ExceptionContainer;
import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.codes.ExceptionListEnum;
import com.jbadcode.checklist.log.exception.codes.UserExceptionCode;
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
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    private static final Logger logger = Logger.getLogger(ApplicationExceptionMapper.class.getName());

    @Override
    public Response toResponse(ApplicationException exception) {
        ApplicationException applicationException = (ApplicationException) exception;
        return Response.
                status(transformCode(applicationException.getExceptionCode())).
                entity(new ExceptionContainer(applicationException)).
                build();
    }

    private Response.Status transformCode(ExceptionListEnum exceptionListEnum) {
        if (exceptionListEnum instanceof ApplicationException) {
            return Response.Status.CONFLICT;
        } else if (exceptionListEnum instanceof UserExceptionCode) {
            return Response.Status.BAD_REQUEST;
        } else {
            return Response.Status.SERVICE_UNAVAILABLE;
        }
    }
}
