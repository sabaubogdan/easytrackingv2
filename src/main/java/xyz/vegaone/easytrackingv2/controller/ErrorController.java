package xyz.vegaone.easytrackingv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.vegaone.easytrackingv2.exception.UserNotFoundException;

@Controller
public class ErrorController {

    @RequestMapping(value = "{/type:.+}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("type") String type) throws Exception {
        if ("error".equals(type)) {
            throw new UserNotFoundException("404", "User not found.");
        }

        return new ModelAndView("index").addObject("msg", type);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleGenericException(UserNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("/error/generic_error");
        modelAndView.addObject("errCode", ex.getErrCode());
        modelAndView.addObject("errMsg", ex.getErrMsg());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex){
        ModelAndView modelAndView = new ModelAndView(("error/generic/error"));
        modelAndView.addObject("errMsg", "this is Exception.class");

        return modelAndView;
    }
}
