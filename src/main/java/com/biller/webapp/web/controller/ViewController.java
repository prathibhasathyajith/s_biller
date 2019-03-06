package com.biller.webapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public String invoice() {
        return "invoice";
    }

    @RequestMapping(value = "/billing", method = RequestMethod.GET)
    public String billing() {
        return "billing";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report() {
        return "report";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "user";
    }


}
