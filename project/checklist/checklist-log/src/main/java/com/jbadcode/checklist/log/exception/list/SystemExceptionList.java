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
public enum SystemExceptionList implements ExceptionListEnum {

    /**
     * DATABASE ERROR
     */
    SE_000_001,
    /**
     * ENCRYPTION ERROR
     */
    SE_000_002,
    ;

    @Override
    public String getCode() {
        return this.name();
    }
}
