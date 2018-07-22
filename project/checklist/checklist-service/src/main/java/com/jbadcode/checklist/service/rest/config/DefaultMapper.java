/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Juan David Segura
 */
@Provider
public class DefaultMapper implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public DefaultMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Object.class)); 
        objectMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

}
