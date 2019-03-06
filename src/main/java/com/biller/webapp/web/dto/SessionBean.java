package com.biller.webapp.web.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope(value="session")
public class SessionBean {
    private HashMap<String, String> pages;
    private WebLoginBean systemUser;
    private String currentPage;
    private String currentSection;
    private String oldValue;


    public HashMap<String, String> getPages() {
        return pages;
    }

    public void setPages(HashMap<String, String> pages) {
        this.pages = pages;
    }

    public WebLoginBean getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(WebLoginBean systemUser) {
        this.systemUser = systemUser;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(String currentSection) {
        this.currentSection = currentSection;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }
}
