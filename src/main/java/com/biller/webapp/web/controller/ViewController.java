package com.biller.webapp.web.controller;

import com.biller.webapp.util.MessageVarList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


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

    @RequestMapping(value = "/biller", method = RequestMethod.GET)
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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

//    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
//    public ModelAndView dashboard(HttpServletRequest request,Model model) {
//
//        ServletContext contextSession = request.getSession().getServletContext();
//        HttpSession session = request.getSession(true);
//
//        HashMap<String, String> pageMap = (HashMap<String, String>) contextSession.getAttribute(MessageVarList.HTTPSESSION_PAGEMAP);
//        session.setAttribute("TESTS",pageMap);
//
//        model.addAttribute("page",pageMap);
//
//        ModelAndView andView = new ModelAndView();
//        andView.setViewName("dashboard");
//        andView.addObject("test",model);
//
//        return andView;
//    }

    @RequestMapping(value = "/userManagement", method = RequestMethod.GET)
    public String user() {
        return "user";
    }


}
