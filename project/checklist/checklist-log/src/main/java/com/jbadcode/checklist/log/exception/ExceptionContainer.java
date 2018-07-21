/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.codes.UserExceptionCode;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Segura
 */
@XmlRootElement
public class ExceptionContainer implements Serializable {

    private String exceptionCode;

    private ArrayList<String> log = new ArrayList<>();

    private String server;

    public ExceptionContainer() {
    }

    public ExceptionContainer(ApplicationException applicationException) {
        this.exceptionCode = applicationException.getExceptionCode().getCode();
        if (applicationException.getCause() != null) {
            this.server = getServerAddresses();
            for (StackTraceElement stackTraceElement : applicationException.getStackTrace()) {
                log.add(stackTraceElement.toString());
            }
        } else {
            log = null;
        }

    }

    public ExceptionContainer(ConstraintViolationException constraintViolationException) {
        this.exceptionCode = UserExceptionCode.GUE_000_002.getCode();
        for (ConstraintViolation<?> constraintViolation : constraintViolationException.getConstraintViolations()) {
            log.add(constraintViolation.getMessage());
        }
    }

    private String getServerAddresses() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                if (!n.isLoopback() && n.isUp()) {
                    Enumeration ee = n.getInetAddresses();
                    while (ee.hasMoreElements()) {
                        InetAddress i = (InetAddress) ee.nextElement();
                        if (!i.isLinkLocalAddress()) {
                            sb.append(i.getHostAddress()).append(";");
                        }
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return sb.toString();
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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

}
