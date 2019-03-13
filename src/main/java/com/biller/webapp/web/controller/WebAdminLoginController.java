package com.biller.webapp.web.controller;

import com.biller.webapp.util.MessageVarList;
import com.biller.webapp.web.dto.SessionBean;
import com.biller.webapp.web.dto.WebLoginBean;
import com.biller.webapp.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prathibha Sathyajith on 9/23/2018.
 */
@Controller
@Scope("request")
@RequestMapping(value = "")
public class WebAdminLoginController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionBean sessionBean;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpServletRequest request,@ModelAttribute WebLoginBean inputBean) throws Exception {
        System.out.println(inputBean);

        HttpSession session = request.getSession(true);
        ServletContext contextSession = request.getSession().getServletContext();
        contextSession.setAttribute(MessageVarList.HTTPSESSION_PAGEMAP , null);

        String message = loginService.validateInputs(inputBean,request);
        System.out.println("--------- login message ------------- ");
        System.out.println("message = " + message);

        ModelAndView andView = new ModelAndView();

        if(message.isEmpty()){

            session.setAttribute(MessageVarList.HTTPSESSION_SYSTERMUSER, inputBean);

            HashMap<String, String> userMap = null;
            userMap = (HashMap<String, String>) contextSession.getAttribute(MessageVarList.HTTPSESSION_USERMAP);
            if (userMap == null) {
                userMap = new HashMap<String, String>();
            }
            userMap.put(inputBean.getUserName(), session.getId());
            contextSession.setAttribute(MessageVarList.HTTPSESSION_USERMAP, userMap);

            andView.setViewName("redirect:/home");
            andView.addObject(inputBean);

        }else{
            inputBean.setMessage(message);
            andView.setViewName("login");
            andView.addObject(inputBean);
        }

       return andView;
    }


    @RequestMapping(value = "/Logout/{message}")
    public ModelAndView logoutUserLogin(HttpServletRequest request, @PathVariable Map<String, String> pathVars, Model model) throws Exception {

        System.out.println("called LoginController : logout with error");
        String msg = "";

        System.out.println("path vari:" + pathVars.get("message"));
        msg = pathVars.get("message");

        if (msg.equalsIgnoreCase("ERROR_ACCESS")) {
            model.addAttribute("errorMessage", "ERROR_ACCESS");
        } else if (msg.equals("ERROR_ACCESSPOINT")) {
            model.addAttribute("errorMessage", "ERROR_ACCESSPOINT");
        } else if (msg.equals("ERROR_USER_INFO")) {
            model.addAttribute("errorMessage", "ERROR_USER_INFO");
        } else if (msg.equals("PASSWORD_CHANGE_SUCCESS")) {
            model.addAttribute("errorMessage", "PASSWORD_CHANGE_SUCCESS");
        } else {
            model.addAttribute("errorMessage", "ERROR_SESSION");
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        System.out.println("logut{msg}----");

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("login");
        modelAndView.addObject("model",model);

        return modelAndView;

    }

    @RequestMapping(value = "/LogoutNow")
    public ModelAndView logoutUserLogin(HttpServletRequest request) throws Exception {

        System.out.println("called LoginController : logout");

        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("in");
            sessionBean = null;
            session.invalidate();
        }

        ModelAndView modelAndView;

        modelAndView = new ModelAndView("login");

        return modelAndView;

    }

}
