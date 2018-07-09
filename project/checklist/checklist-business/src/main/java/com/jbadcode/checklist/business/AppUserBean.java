/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.business;

import com.jbadcode.checklist.business.security.HashManager;
import com.jbadcode.checklist.log.LoggerBean;
import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.list.ApplicationExceptionList;
import com.jbadcode.checklist.log.exception.list.SystemExceptionList;
import com.jbadcode.checklist.log.exception.list.UserExceptionList;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.persistence.facede.AppUserFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    public enum userExceptionCode implements UserExceptionList {
        /**
         * USER NOT FOUND
         */
        UE_000_001,
        /**
         * BAD PASSWORD
         */
        UE_000_002;

        @Override
        public String getCode() {
            return this.name();
        }
    }

    @EJB
    private LoggerBean loggerBean;

    @EJB
    private AppUserFacade appUserFacade;

    @PostConstruct
    public void init() {
        loggerBean.setTransactionId(getProcessIdentificator());
    }

    public AppUser authenticate(
            @NotNull(message = "empty nick") @Size(min = 1, message = "empty nick") String nick,
            @NotNull(message = "empty password") @Size(min = 1, message = "empty password") String password)
            throws ApplicationException {
        try {
            AppUser appUser = appUserFacade.getByNick(nick);
            if (HashManager.verifyPassword(password, appUser.getPassword())) {
                return appUser;
            } else {
                throw new ApplicationException(
                        userExceptionCode.UE_000_002);
            }
        } catch (PersistenceException
                | HashManager.CannotPerformOperationException
                | HashManager.InvalidHashException ex) {
            loggerBean.catchException(ex).
                    handleWithoutLog(
                            NoResultException.class,
                            userExceptionCode.UE_000_001).
                    handle(
                            NonUniqueResultException.class,
                            ApplicationExceptionList.AE_000_001).
                    handle(
                            PersistenceException.class,
                            SystemExceptionList.SE_000_001).
                    handle(
                            HashManager.CannotPerformOperationException.class,
                            SystemExceptionList.SE_000_002).
                    handle(
                            HashManager.InvalidHashException.class,
                            SystemExceptionList.SE_000_002).
                    handleDefault();
            return null;
        }
    }
}
