/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.util.UUID;
import javax.annotation.Priority;

/**
 *
 * @author Juan David Segura
 */
@Provider
@Priority(1)
public class TransactionIdAsignatorFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        requestContext.setProperty(RequestProperties.PROCESS_IDENTIFICAROR.name(), UUID.randomUUID().toString());
    }

}
