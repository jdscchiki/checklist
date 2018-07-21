/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.entityfiltering;

import com.fasterxml.jackson.annotation.JsonView;
import java.lang.annotation.Annotation;

/**
 *
 * @author Juan David Segura
 */
public class JsonViewGenerator {

    public static Annotation getJsonView(final Class[] c) {
        return new JsonView() {
            @Override
            public Class<?>[] value() {
                return c;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return JsonView.class;
            }
        };
    }
}
