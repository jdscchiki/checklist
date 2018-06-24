/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.util;

import com.jbadcode.checklist.log.exception.ApplicationException;
import java.io.Serializable;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David Segura
 */
@XmlRootElement
public class ExceptionContainer implements Serializable{
    
    private String exceptionCode;
    
    private String log;

    public ExceptionContainer() {
    }
    
    public ExceptionContainer(ApplicationException applicationException) {
        this.exceptionCode = applicationException.getExceptionCode().getCode();
        this.log = Arrays.toString(applicationException.getStackTrace());
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    @XmlTransient
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
