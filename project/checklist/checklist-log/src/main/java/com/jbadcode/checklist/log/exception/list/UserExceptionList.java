/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception.list;

/**
 *
 * @author Juan David Segura
 */
public enum UserExceptionList implements ExceptionListEnum {
    /**
     * USER NOT FOUND
     */
    UE_000_001,
    /**
     * BAD PASSWORD
     */
    UE_000_002,
    /**
     * NO LOGED USER
     */
    UE_000_003;

    @Override
    public String getCode() {
        return this.name();
    }

}
