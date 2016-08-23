package com.rk.example.sqlsafe.web;

import com.rk.example.sqlsafe.util.SQLInjectionSafe;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class BasicController {

    public static final String INVALID_DATA_PROVIDED = "Invalid data provided";
    public static final String ID_WRAPPER = "idWrapper";

    public static class IdWrapper{

        private @SQLInjectionSafe String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    protected String getBindExceptionMessage(BindException be){

        if(be==null && be.getBindingResult()==null){
            return INVALID_DATA_PROVIDED;
        }

        List<ObjectError> errors = be.getBindingResult().getAllErrors();

        if(errors==null || errors.isEmpty()){
            return INVALID_DATA_PROVIDED;
        }

        for(ObjectError objectError : errors){
            if(objectError instanceof FieldError){
                if(ID_WRAPPER.equalsIgnoreCase(objectError.getObjectName())){
                    return "Invalid 'id' specified";
                }
            }
        }

        return INVALID_DATA_PROVIDED;

    }

}
