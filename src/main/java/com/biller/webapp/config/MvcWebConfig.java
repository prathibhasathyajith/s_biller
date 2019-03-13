package com.biller.webapp.config;

import com.biller.webapp.intercepter.CheckAccessInteceptor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.Properties;

@Configuration
@ComponentScan("com.biller.webapp")
@EnableWebMvc
@EnableTransactionManagement
public class MvcWebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//
//    }

    @Bean
    CheckAccessInteceptor localInterceptor() {
        return new CheckAccessInteceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localInterceptor()).addPathPatterns("/biller/**").excludePathPatterns("/logoutNow*").excludePathPatterns("/**/logoutNow*").excludePathPatterns("/login*");
        registry.addInterceptor(localInterceptor()).addPathPatterns("/**").excludePathPatterns("/").excludePathPatterns("/**/login*").excludePathPatterns("/**/LogoutNow*").excludePathPatterns("/**/Logout/**");
    }

    @Bean
    public AnnotationSessionFactoryBean sessionFactory() {
        AnnotationSessionFactoryBean sessionFactoryBean = new AnnotationSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.biller.webapp.mapping");
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("connection.autoReconnect", "true");
        properties.put("connection.autoReconnectForPools", "true");
        properties.put("connection.is-connection-validation-required", "true");

        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        properties.put("hibernate.connection.url", "jdbc:mysqlsql://35.237.13.27:3306/geoloc_db");
//        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/geoloc_db");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/pos");
        properties.put("hibernate.connection.username", "root");
//        properties.put("hibernate.connection.password", "dialog@ets");
        properties.put("hibernate.connection.password", "password");
//        properties.put("hibernate.connection.password", "password");


        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }





    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/assets/img/",
                        "classpath:/assets/css/",
                        "classpath:/assets/js/");
    }

    /**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }


}
