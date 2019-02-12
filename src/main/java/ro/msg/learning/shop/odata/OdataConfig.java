package ro.msg.learning.shop.odata;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OdataConfig {
    @Bean
    public ServletRegistrationBean<MyOdataServlet> odataServlet(MyOdataServlet servlet) {
        return new ServletRegistrationBean<>(servlet, "/odata/*");
    }
}
