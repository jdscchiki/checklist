/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Juan David Segura
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("com.jbadcode.checklist.service.rest");
        register(JacksonFeature.class);

//        register(com.jbadcode.checklist.service.rest.config.CrossDomainFilter.class);
//        register(com.jbadcode.checklist.service.rest.config.RequestLoggerFilter.class);
//        register(com.jbadcode.checklist.service.rest.config.ResponseLoggerFilter.class);
//        register(com.jbadcode.checklist.service.rest.config.TransactionIdAsignatorFilter.class);
//        register(com.jbadcode.checklist.service.rest.exception.ApplicationExceptionMapper.class);
//        register(com.jbadcode.checklist.service.rest.exception.ValidationExceptionMapper.class);
        
    }
}
