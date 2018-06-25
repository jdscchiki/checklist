/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Juan David Segura
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.jbadcode.checklist.service.rest.AppUserResource.class);
        resources.add(com.jbadcode.checklist.service.rest.config.CrossDomainFilter.class);
        resources.add(com.jbadcode.checklist.service.rest.config.ProcessIdentificationFilter.class);
        resources.add(com.jbadcode.checklist.service.rest.config.RequestLoggerFilter.class);
        resources.add(com.jbadcode.checklist.service.rest.config.ResponseLoggerFilter.class);
    }
    
}
