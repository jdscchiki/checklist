/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.util;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.list.ApplicationExceptionList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David Segura
 */
@XmlRootElement
public class ExceptionContainer implements Serializable {

    private String exceptionCode;

    private ArrayList<String> log = new ArrayList<>();

    public ExceptionContainer() {
    }

    public ExceptionContainer(ApplicationException applicationException) {
        this.exceptionCode = applicationException.getExceptionCode().getCode();
        if (applicationException.getCause() != null) {
            for (StackTraceElement stackTraceElement : applicationException.getStackTrace()) {
                log.add(stackTraceElement.toString());
            }
        } else {
            log = null;
        }

    }

    public ExceptionContainer(ConstraintViolationException constraintViolationException) {
        this.exceptionCode = ApplicationExceptionList.AE_000_000.getCode();
        for (ConstraintViolation<?> constraintViolation : constraintViolationException.getConstraintViolations()) {
            log.add(constraintViolation.getMessage());
        }
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void setLog(ArrayList<String> log) {
        this.log = log;
    }

}
