package ro.msg.learning.shop;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<>(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}