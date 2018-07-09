/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import com.jbadcode.checklist.persistence.entity.entityfiltering.AppUser.PasswordView;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;

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
        resources.add(EntityFilteringFeature.class);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> prop = new HashMap<>(super.getProperties());
        prop.put(EntityFilteringFeature.ENTITY_FILTERING_SCOPE, new Annotation[] {
            PasswordView.Factory.get()});
        return prop;
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
        resources.add(com.jbadcode.checklist.service.rest.config.RequestLoggerFilter.class);
        resources.add(com.jbadcode.checklist.service.rest.config.ResponseLoggerFilter.class);
        resources.add(com.jbadcode.checklist.service.rest.config.TransactionIdAsignatorFilter.class);
        resources.add(com.jbadcode.checklist.service.rest.exception.ApplicationExceptionMapper.class);
        resources.add(com.jbadcode.checklist.service.rest.exception.ValidationExceptionMapper.class);
    }
    
}
