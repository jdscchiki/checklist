/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.business;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Juan David Segura
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public abstract class BussinesAbstractBean {

    private String processIdentificator;

    public String getProcessIdentificator() {
        return processIdentificator;
    }

    public void setProcessIdentificator(String processIdentificator) {
        this.processIdentificator = processIdentificator;
    }

}
