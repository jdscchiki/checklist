/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import com.jbadcode.checklist.entityfiltering.appuser.PasswordView;
import java.lang.annotation.Annotation;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Juan David Segura
 */
//@javax.ws.rs.ApplicationPath("webresources")
//public class ApplicationConfig extends Application {
//
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new java.util.HashSet<>();
//        addRestResourceClasses(resources);
//        resources.add(EntityFilteringFeature.class);
//        resources.add(new MoxyJsonConfig().setFormattedOutput(true).resolver());
//        return resources;
//    }
//
//
//    
//    /**
//     * Do not modify addRestResourceClasses() method.
//     * It is automatically populated with
//     * all resources defined in the project.
//     * If required, comment out calling this method in getClasses().
//     */
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(com.jbadcode.checklist.service.rest.AppUserResource.class);
//        resources.add(com.jbadcode.checklist.service.rest.config.CrossDomainFilter.class);
//        resources.add(com.jbadcode.checklist.service.rest.config.RequestLoggerFilter.class);
//        resources.add(com.jbadcode.checklist.service.rest.config.ResponseLoggerFilter.class);
//        resources.add(com.jbadcode.checklist.service.rest.config.TransactionIdAsignatorFilter.class);
//        resources.add(com.jbadcode.checklist.service.rest.exception.ApplicationExceptionMapper.class);
//        resources.add(com.jbadcode.checklist.service.rest.exception.ValidationExceptionMapper.class);
//    }
//    
//}
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // Register all resources present under the package.
        packages(false, "com.jbadcode.checklist.service.rest");
        // Entity Data Filtering feature.
        register(EntityFilteringFeature.class);

        // Configure MOXy Json provider. Comment this line to use Jackson. Uncomment to use MOXy.
//        register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
        // Configure Jackson Json provider. Comment this line to use MOXy. Uncomment to use Jackson.
        register(JacksonFeature.class);
        

        register(com.jbadcode.checklist.service.rest.AppUserResource.class);
        register(com.jbadcode.checklist.service.rest.config.CrossDomainFilter.class);
        register(com.jbadcode.checklist.service.rest.config.RequestLoggerFilter.class);
        register(com.jbadcode.checklist.service.rest.config.ResponseLoggerFilter.class);
        register(com.jbadcode.checklist.service.rest.config.TransactionIdAsignatorFilter.class);
        register(com.jbadcode.checklist.service.rest.exception.ApplicationExceptionMapper.class);
        register(com.jbadcode.checklist.service.rest.exception.ValidationExceptionMapper.class);
        
    }
}
