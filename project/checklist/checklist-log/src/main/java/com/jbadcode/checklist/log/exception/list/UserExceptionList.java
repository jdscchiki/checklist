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
public interface UserExceptionList extends ExceptionListEnum {
    
    public enum generalException implements ExceptionListEnum{
        /**
         * USER NOT LOGGED
         */
        GUE_000_001,
        /**
         * BAD_INPUT
         */
        GUE_000_002;
        

        @Override
        public String getCode() {
            return this.name();
        }
    }
    
}
