package com.biller.webapp.intercepter;

import com.biller.webapp.util.MessageVarList;
import com.biller.webapp.web.dto.SessionBean;
import com.biller.webapp.web.dto.WebLoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class CheckAccessInteceptor implements HandlerInterceptor {

    private SessionBean sessionBean;

    /**
     * @return the sessionBean
     */
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean status = false;

        System.out.println("------- access controller --------");
        HttpSession session = request.getSession(false);

        if (session != null) {

            if (session.getAttribute(MessageVarList.HTTPSESSION_SYSTERMUSER) != null) {

                WebLoginBean user = new WebLoginBean();
                user = (WebLoginBean)session.getAttribute(MessageVarList.HTTPSESSION_SYSTERMUSER);

                ServletContext context = request.getSession().getServletContext();
                HashMap<String, String> userMap = (HashMap<String, String>) context.getAttribute(MessageVarList.HTTPSESSION_USERMAP);
                String sessionId = userMap.get(user.getUserName());

                if (sessionId.equals(request.getSession(false).getId())) {
                    status = true;
                } else {//multi access
                    response.sendRedirect("Logout/ERROR_ACCESSPOINT");
                    System.out.println("multi access denied :");

                    status = false;
                }
            } else {//session expire

                response.sendRedirect("Logout/ERROR_USER_INFO");
                System.out.println("session expire :");

                status = false;
            }
        } else {
            status = false;
        }
        return status;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("called post handler");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("called After Completion");
    }
}
