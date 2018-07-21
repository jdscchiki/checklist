/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest.ejb;

import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.codes.UserExceptionCode;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.service.rest.util.SessionAttributes;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 *
 * @author Juan David Segura
 */
@Stateless
public class SessionBean {

    @Context
    private HttpServletRequest request;

    public void startSession(AppUser appUser) {
        request.getSession().setAttribute(SessionAttributes.USER, appUser);
    }

    public AppUser getLoggedAppUser() throws ApplicationException {
        AppUser appUser = (AppUser) request.getSession().getAttribute(SessionAttributes.USER);
        if(appUser != null){
            return appUser;
        }else{
            throw new ApplicationException(
                        UserExceptionCode.GUE_000_001);
        }
    }

    public void endSession() {
        request.getSession().removeAttribute(SessionAttributes.USER);
    }
}
