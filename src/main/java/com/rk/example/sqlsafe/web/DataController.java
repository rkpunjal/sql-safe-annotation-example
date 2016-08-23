package com.rk.example.sqlsafe.web;

import com.rk.example.sqlsafe.util.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/data")
public class DataController extends BasicController {

    protected static final String APPLICATION_JSON_UTF_8 = "application/json;charset=UTF-8";

    private final Logger logger = LoggerFactory.getLogger(DataController.class);


    @RequestMapping(value = "/getById", produces=APPLICATION_JSON_UTF_8)
    public @ResponseBody
    WebResponse getById(
            @Valid @ModelAttribute() IdWrapper idWrapper
    ) {

        String id = idWrapper.getId();

        try {
            Object dataObject = "that's my data"; // could you any data you fetch using the id
            WebResponse response = new WebResponse(true);
            response.setData(dataObject);
            return response;

        } catch (Exception e) {

            logger.warn("Failure fetching getById for  id : " + id
                    , e
            );

            return new WebResponse(false,
                    "Failure fetching getById for id : " + id
            );
        }

    }


//    @ResponseStatus(value= HttpStatus.BAD_REQUEST,
//            reason="Invalid data provided")
    @ExceptionHandler(BindException.class)
    public @ResponseBody
    WebResponse handleBindException(BindException be ){

        return new WebResponse(false,
                getBindExceptionMessage(be)
        );

    }



}
