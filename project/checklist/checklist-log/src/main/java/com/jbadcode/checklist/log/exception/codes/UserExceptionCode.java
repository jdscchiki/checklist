/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception.codes;

/**
 *
 * @author Juan David Segura
 */
public enum UserExceptionCode implements ExceptionListEnum {

    /**
     * USER NOT LOGGED
     */
    GUE_000_001,
    /**
     * BAD_INPUT
     */
    GUE_000_002,
    /**
     * USER NOT FOUND
     */
    UE_000_001,
    /**
     * BAD PASSWORD
     */
    UE_000_002,
    /**
     * NICK ALREADY EXIST
     */
    UE_000_003;

    @Override
    public String getCode() {
        return this.name();
    }

}
