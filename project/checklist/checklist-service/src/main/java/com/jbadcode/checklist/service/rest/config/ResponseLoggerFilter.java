/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

/**
 *
 * @author Juan David Segura
 */
@Provider
public class ResponseLoggerFilter implements ContainerResponseFilter {

    @Context
    private Providers providers;

    private static final Logger logger = Logger.getLogger(ResponseLoggerFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.
                append("Transaction=").
                append(requestContext.getProperty(RequestProperties.PROCESS_IDENTIFICAROR.name()));
        stringBuilder.append(";RESPONSE");
        stringBuilder.
                append("\nSTATUS=\n\t").
                append(responseContext.getStatus()).
                append("\n");
        stringBuilder.append("HEADERS=\n");
        for (Map.Entry<String, List<String>> header : responseContext.getStringHeaders().entrySet()) {
            String key = header.getKey();
            List<String> value = header.getValue();
            stringBuilder.append("\t");
            String headerValue = Arrays.toString(value.toArray());
            stringBuilder.append(key).append(": ").append(headerValue.substring(1, headerValue.length() - 1));
            stringBuilder.append("\n");
        }
        stringBuilder.append("COOKIES=\n");
        for (Map.Entry<String, NewCookie> cookie : responseContext.getCookies().entrySet()) {
            String key = cookie.getKey();
            Cookie value = cookie.getValue();
            stringBuilder.append("\t\t");
            stringBuilder.append(key).append(": ").append(value.getValue());
            stringBuilder.append("\n");
        }
        if (responseContext.hasEntity()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                stringBuilder.append("BODY=\n");
                Class<?> entityClass = responseContext.getEntityClass();
                Type entityType = responseContext.getEntityType();
                Annotation[] entityAnnotations = responseContext.getEntityAnnotations();
                MediaType mediaType = responseContext.getMediaType();
                @SuppressWarnings("unchecked")
                MessageBodyWriter<Object> bodyWriter = (MessageBodyWriter<Object>) providers.getMessageBodyWriter(entityClass,
                        entityType,
                        entityAnnotations,
                        mediaType); // I retrieve the bodywriter
                bodyWriter.writeTo(responseContext.getEntity(),
                        entityClass,
                        entityType,
                        entityAnnotations,
                        mediaType,
                        responseContext.getHeaders(),
                        baos);
            } catch (IOException | WebApplicationException ex) {
                Logger.getLogger(ResponseLoggerFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            stringBuilder.append(new String(baos.toByteArray()));
        }
        logger.log(Level.INFO, stringBuilder.toString());
    }

}
