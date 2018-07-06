/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log.exception;

import com.jbadcode.checklist.log.exception.list.ApplicationExceptionList;

/**
 *
 * @author segurajd
 */
public class ApplicationExceptionBuilder {
    
    private Throwable throwable;

    public ApplicationExceptionBuilder(Throwable throwable) {
        this.throwable = throwable;
    }
    
    public ApplicationExceptionBuilder handle(Class<? extends Throwable> t , ApplicationExceptionList exceptionCode){
        if(t.isInstance(throwable)){
            
        }
        return this;
    }
    
}
