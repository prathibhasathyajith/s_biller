package com.biller.webapp.listner;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by gayan_s on 8/8/2018.
 */
@Component
public class ContextListner implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context initialized..!");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("Context destroyed..!");
    }
}
