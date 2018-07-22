/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.business;

import com.jbadcode.checklist.business.security.HashManager;
import com.jbadcode.checklist.log.LoggerBean;
import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.log.exception.codes.ApplicationExceptionCode;
import com.jbadcode.checklist.log.exception.codes.SystemExceptionCode;
import com.jbadcode.checklist.log.exception.codes.UserExceptionCode;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.persistence.facede.AppUserFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
        loggerBean.setTransactionId(getProcessIdentificator());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
                        UserExceptionCode.UE_000_002);
            }
        } catch (Exception ex) {
            loggerBean.catchException(ex).
                    handleWithoutLog(NoResultException.class,
                            UserExceptionCode.UE_000_001).
                    handle(NonUniqueResultException.class,
                            ApplicationExceptionCode.AE_000_001).
                    handle(PersistenceException.class,
                            SystemExceptionCode.SE_000_001).
                    handle(HashManager.CannotPerformOperationException.class,
                            SystemExceptionCode.SE_000_002).
                    handle(HashManager.InvalidHashException.class,
                            SystemExceptionCode.SE_000_002).
                    handleDefault();
            return null;
        }
    }
    
    public AppUser register(AppUser appUser) throws ApplicationException{
        
        try{
            appUserFacade.getByNick(appUser.getNick());
            loggerBean.throwException(UserExceptionCode.UE_000_003);
        }catch(Exception ex){
            loggerBean.catchException(ex).
                    pass(NoResultException.class).
                    rethrow(UserExceptionCode.UE_000_003).
                    handle(NonUniqueResultException.class,
                            UserExceptionCode.UE_000_002).
                    handle(PersistenceException.class,
                            SystemExceptionCode.SE_000_001).
                    handleDefault();
        }
        try{
            appUser.setPassword(
                    HashManager.createHash(
                            appUser.getPassword()));
            appUserFacade.create(appUser);
            return appUser;
        }catch(Exception e){
            loggerBean.catchException(e).
                    handle(PersistenceException.class,
                            SystemExceptionCode.SE_000_001).
                    handle(HashManager.CannotPerformOperationException.class,
                            SystemExceptionCode.SE_000_002).
                    handleDefault();
        }
        return null;
    }
}
