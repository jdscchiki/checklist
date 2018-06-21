/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.log;

import com.jbadcode.checklist.log.exception.ApplicationException;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author segurajd
 */
@Stateless
@LocalBean
public class LoggerBean {

    
    
    public void log(String message){
        
    }
    
    public void log(String message, ApplicationException applicationException){
        
    }
    
    public void log(String message, Class clazz){
        
    }
    
    public void log(String message, Class clazz, ApplicationException applicationException){
        
    }
}
