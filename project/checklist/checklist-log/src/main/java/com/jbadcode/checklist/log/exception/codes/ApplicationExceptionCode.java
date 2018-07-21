/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception.codes;

/**
 *
 * @author segurajd
 */
public enum ApplicationExceptionCode implements ExceptionListEnum {
    /**
     * MULTIPLE USERS WHIT THE SAME NICK
     */
    AE_000_001;

    @Override
    public String getCode() {
        return this.name();
    }
}
