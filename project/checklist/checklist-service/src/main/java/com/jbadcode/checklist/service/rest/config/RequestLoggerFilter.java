/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Juan David Segura
 */
@Provider
@Priority(2)
public class RequestLoggerFilter implements ContainerRequestFilter {

    private static final Logger logger = Logger.getLogger(RequestLoggerFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.
                append("Transaction=").
                append(requestContext.getProperty(RequestProperties.PROCESS_IDENTIFICAROR.name()));
        stringBuilder.append(";REQUEST");
        stringBuilder.
                append("\nCOMPLETE_URL=\n\t").
                append(requestContext.getUriInfo().getRequestUri()).
                append("\n");
        stringBuilder.
                append("METHOD=\n\t").
                append(requestContext.getMethod()).
                append("\n");
        stringBuilder.append("HEADERS=\n");
        for (Map.Entry<String, List<String>> header : requestContext.getHeaders().entrySet()) {
            String key = header.getKey();
            List<String> value = header.getValue();
            stringBuilder.append("\t");
            String headerValue = Arrays.toString(value.toArray());
            stringBuilder.append(key).append(": ").append(headerValue.substring(1, headerValue.length() - 1));
            stringBuilder.append("\n");
        }
        stringBuilder.append("COOKIES=\n");
        for (Map.Entry<String, Cookie> cookie : requestContext.getCookies().entrySet()) {
            String key = cookie.getKey();
            Cookie value = cookie.getValue();
            stringBuilder.append("\t");
            stringBuilder.append(key).append(": ").append(value.getValue());
            stringBuilder.append("\n");
        }
        if (requestContext.hasEntity()) {
            try {
                stringBuilder.append("BODY=\n");
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = requestContext.getEntityStream().read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                requestContext.getEntityStream().close();
                stringBuilder.append(result.toString(StandardCharsets.UTF_8.name()));
                requestContext.setEntityStream(new ByteArrayInputStream(result.toByteArray()));
                result.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "", ex);
            }
        }
        logger.log(Level.INFO, stringBuilder.toString());
    }

}
