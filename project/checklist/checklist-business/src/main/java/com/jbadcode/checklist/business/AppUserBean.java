/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.business;

import com.jbadcode.checklist.log.LoggerBean;
import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.list.ApplicationExceptionList;
import com.jbadcode.checklist.log.exception.list.SystemExceptionList;
import com.jbadcode.checklist.log.exception.list.UserExceptionList;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.persistence.facede.AppUserFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author Juan David Segura
 */
@Stateless
@LocalBean
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
public class AppUserBean extends BussinesAbstractBean {

    @EJB
    private LoggerBean loggerBean;

    @EJB
    private AppUserFacade appUserFacade;

    @PostConstruct
    public void init() {
        loggerBean.setBaseClazz(AppUserBean.class);
        loggerBean.setTransactionId(getProcessIdentificator());
    }

    public AppUser authenticate(
            @NotNull(message = "nick=null") @Size(min = 1, message = "nick=''") String nick,
            @NotNull(message = "password=null") @Size(min = 1, message = "password=''") String password) 
            throws ApplicationException {
        try {
            AppUser appUser = appUserFacade.getByNick(nick);
            if (appUser.getPassword().equals(password)) {
                return appUser;
            } else {
                throw new ApplicationException(
                        UserExceptionList.UE_000_002);
            }
        } catch (EJBTransactionRolledbackException ex) {
            if (ex.getCause() instanceof NoResultException) {
                throw new ApplicationException(
                        UserExceptionList.UE_000_001);
            } else if (ex.getCause() instanceof NonUniqueResultException) {
                loggerBean.log(this.getProcessIdentificator(),
                        null,
                        new ApplicationException(
                                ApplicationExceptionList.AE_000_001,
                                ex.getCause()));
            } else if (ex.getCause() instanceof PersistenceException) {
                loggerBean.log(this.getProcessIdentificator(),
                        null,
                        new ApplicationException(
                                SystemExceptionList.SE_000_001,
                                ex.getCause()));
            } else {
                loggerBean.log(this.getProcessIdentificator(),
                        null,
                        new ApplicationException(
                                ex.getCause()));
            }
        }
        return null;
    }
}
