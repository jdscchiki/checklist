/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception.list;

/**
 *
 * @author segurajd
 */
public enum ApplicationExceptionList implements ExceptionListEnum {
    /**
     * INVALID INPUT
     */
    AE_000_000,
    /**
     * MULTIPLE USERS WHIT THE SAME NICK
     */
    AE_000_001;

    @Override
    public String getCode() {
        return this.name();
    }
}
